package view

import org.apache.jena.tdb.store.Hash
import org.jgrapht.DirectedGraph
import org.jgrapht.Graph
import org.jgrapht.UndirectedGraph
import org.jgrapht.graph.ClassBasedEdgeFactory
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedMultigraph
import org.jgrapht.graph.SimpleDirectedGraph
import org.jgrapht.graph.SimpleGraph
import org.semanticweb.owlapi.model.OWLClass
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner
import show.ProgressBar
import tool.RequestManager

import javax.management.relation.Relation

/*
 * Copyright 2014 Miguel Ángel Rodríguez-García (miguel.rodriguezgarcia@kaust.edu.sa).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Interface ViewFormat that should be implemented for all formatters
 * @author Miguel Angel Rodríguez-García
 * @version 1.0
 */
public abstract class ViewFormat {

    /**
     * The output file path
     */
    protected String fileOutPath;

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public ViewFormat(String fileOutPath){
        this.fileOutPath = fileOutPath;
    }

    /**
     * This method is responsible of parsing the ontology. Basically, it call other methods to transform the ontology given into
     * the formatter provided.
     *
     * @param ontology The ontology that will be parsed
     * @param reasoner The reasoner that will be used to go across the ontology.
     * @param properties The object properties that belongs to the ontology and they will be used to compute.
     * @param fileOutPath The output file path.
     *
     */
    public void parseOntology(OWLOntology ontology,OWLReasoner reasoner, String[] properties) {
        if((ontology!=null)&&(reasoner!=null)) {
            properties = checkObjectProperties(ontology,properties);
            RequestManager.getInstance().computedSubClases(ontology,reasoner,properties);
            Graph graph = this.buildGraph(ontology, properties);
            this.serializeGraph(graph);
        }
    }

    /**
     * It checks if the objects properties given are defined in the ontology.
     * @param ontology The ontology that will be used to check if the object properties belong to it.
     * @param properties The objects properties that will be checked.
     * @return The list of Objects properties checked.
     */
    protected String[] checkObjectProperties(ontology,String[] properties){
        if((properties!=null)&&(properties.length==1)&&(properties[0]=="*")){
            HashSet<String> objectProperties = RequestManager.getInstance().getObjectProperties(ontology);
            properties = objectProperties.toArray(new String[objectProperties.size()]);
        }else if((properties!=null)&&(properties.length>0)){
            HashSet<String> objectProperties = RequestManager.getInstance().getObjectProperties(ontology);
            properties.each{objectProperty->
                boolean isContained = false;
                objectProperties.each{ op->
                    if(op.compareTo(objectProperty)==0){
                        isContained = true;
                    }
                }
                if(!isContained){
                    System.out.println("The Object Property: "+objectProperty+" is not defined in the ontology");
                    System.exit(-1);
                }
            }

        }
        return(properties);
    }

    /**
     * It builds the graph.
     * @param ontology Ontology used to build the graph.
     * @param properties Object Properties that belong to ontology and they are using to create the graph.
     * @return Graph is built.
     */
    protected Graph buildGraph(OWLOntology ontology,String[] properties) {
        DirectedGraph<String, RelationshipEdge> graph = new DirectedMultigraph<HashMap, RelationshipEdge>(new ClassBasedEdgeFactory<HashMap, RelationshipEdge>(RelationshipEdge.class));
        Set<OWLClass> classes = ontology.getClassesInSignature(true);
        int classesIndex = 0;
        int classesCounter = ontology.getClassesInSignature(true).size();
        classes.remove(ontology.getOWLOntologyManager().getOWLDataFactory().getOWLNothing());
        classes.each { clazz ->
            ProgressBar.printProgressBar((int) Math.round((classesIndex * 100) / (classesCounter)), "building the graph...");
            classesIndex++;
            HashMap root = RequestManager.getInstance().class2info(clazz,ontology)
            if((root!=null)&&(!root.isEmpty())) {
                graph.addVertex(root);
                Set<HashMap> subClasses = RequestManager.getInstance().subClassesQuery(root.get("owlClass"), ontology);
                RelationshipEdge edge = null;
                if(subClasses!=null){
                    subClasses.each { subClass ->
                        graph.addVertex(subClass);
                        edge = new RelationshipEdge<HashMap>(subClass,root,root.get("classURI") + subClass.get("classURI"));
                        if(!graph.containsEdge(edge)) {
                            graph.addEdge(subClass, root, edge);
                        }
                    }
                }
                if ((properties != null) && (properties.length > 0)) {
                    String objectProperty;
                    RelationshipEdge edgeProperty;
                    for (int i = 0; i < properties.length; i++) {
                        objectProperty = properties[i];
                        if (objectProperty != null) {
                            Set<HashMap> result = RequestManager.getInstance().relationQuery(objectProperty, root.get("owlClass").toString(), ontology);
                            if(result!=null){
                                result.each { objectPropertyClass ->
                                    graph.addVertex(objectPropertyClass);
                                    edgeProperty = new RelationshipEdge<HashMap>(objectPropertyClass,root,root.get("classURI") + objectPropertyClass.get("classURI") + "&&" + objectProperty);
                                    if(!graph.containsEdge(edgeProperty)) {
                                        graph.addEdge(objectPropertyClass, root, edgeProperty);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ProgressBar.printProgressBar(100, "building the graph...");
        return(graph);
    }

    /**
     * It serializes the built graph in a file.
     * @param graph The graph built that it will be serialised in a file.
     */
    public abstract void serializeGraph(Graph graph);

    /**
     * Internal class that represents a new kind of edge that includes labels
     * @param < V >
     */
    public static class RelationshipEdge<V> extends DefaultEdge {
        /**
         * First vertex.
         */
        private V v1;
        /**
         * Second vertex.
         */
        private V v2;
        /**
         * Label given.
         */
        private String label;

        /**
         * Constructor of the class.
         * @param v1 Source vertex
         * @param v2 Destiny vertex
         * @param label Label of the edge.
         */
        public RelationshipEdge(V v1, V v2, String label) {
            this.v1 = v1;
            this.v2 = v2;
            this.label = label;
        }

        /**
         * It retrieves the source vertex.
         * @return Source vertex.
         */
        public V getV1() {
            return v1;
        }

        /**
         * It retrieves the destiny vertex
         * @return Destiny vertex
         */
        public V getV2() {
            return v2;
        }

        /**
         * It retrieves the label of the vertex.
         * @return Label of the vertex.
         */
        public String toString() {
            return label;
        }
    }

}
