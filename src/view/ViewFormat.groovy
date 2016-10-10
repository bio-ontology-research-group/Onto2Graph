package view


import graph.RelationshipEdge
import org.apache.jena.vocabulary.RDFS
import org.jgrapht.Graph
import org.jgrapht.graph.ClassBasedEdgeFactory
import org.jgrapht.graph.DirectedPseudograph
import org.semanticweb.owlapi.model.IRI
import org.semanticweb.owlapi.model.OWLClass
import org.semanticweb.owlapi.model.OWLDataFactory
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner
import show.ProgressBar
import tool.RequestManager

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
     * It provides the subclasses
     */
    protected RequestManager requestManager = null;

    /**
     * The progressBar
     */
    protected ProgressBar progressBar;

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public ViewFormat(String fileOutPath,boolean equivalentClass,boolean transitiveFlag){
        this.fileOutPath = fileOutPath;
        requestManager = new RequestManager(equivalentClass,transitiveFlag);
        progressBar = new ProgressBar();
    }

    /**
     * This method is responsible of parsing the ontology using the reasoner to obtain the related classes. Basically, it call other methods to transform the ontology given into
     * the formatter provided.
     *
     * @param ontology The ontology that will be parsed
     * @param reasoners The list of reasoners that will be used to go across the ontology.
     * @param properties The object properties that belongs to the ontology and they will be used to compute.
     *
     */
    public void parseOntology(OWLOntology ontology,List<OWLReasoner> reasoners, String[] properties,int nThreads) {
        if((ontology!=null)&&(reasoners!=null)&&(!reasoners.isEmpty())) {
            HashMap<String,HashMap> objectProperties = checkObjectProperties(ontology,properties);
            //we just need the ids of the object properties.
            requestManager.computedSemanticSubClasses(ontology,reasoners,objectProperties.keySet(),nThreads);
            Graph graph = this.buildGraph(ontology, objectProperties);
            this.serializeGraph(graph,objectProperties);
        }
    }

    /**
     * This method is responsible of parsing the ontology using the axioms to obtain the related classes. Basically, it call other methods to transform the ontology given into
     * the formatter provided.
     *
     * @param ontology The ontology that will be parsed
     * @param properties The object properties that belongs to the ontology and they will be used to compute.
     *
     */
    public void parseOntology(OWLOntology ontology, String[] properties,int nThreads){
        if((ontology!=null)){
            HashMap<String,HashMap> objectProperties = checkObjectProperties(ontology,properties);
            //we just need the ids of the object properties.
            requestManager.computedSyntacticSubClasses(ontology,objectProperties.keySet(),nThreads);
            Graph graph = this.buildGraph(ontology,objectProperties);
            this.serializeGraph(graph,objectProperties)
        }
    }

    /**
     * It checks if the objects properties given are defined in the ontology.
     * @param ontology The ontology that will be used to check if the object properties belong to it.
     * @param properties The objects properties that will be checked.
     * @return The list of Objects properties checked.
     */
    protected HashMap<String,HashMap> checkObjectProperties(ontology,String[] properties){
        HashMap<String,HashMap> objectProperties = new HashMap<String,HashMap>();
        if((properties!=null)&&(properties.length==1)&&(properties[0]=="*")){
            objectProperties = requestManager.getObjectProperties(ontology);
        }else if((properties!=null)&&(properties.length>0)){
            ArrayList<String> listProperties = Arrays.asList(properties);
            objectProperties = requestManager.getObjectProperties(ontology);
            ArrayList<String> keysRemove = new ArrayList<String>();
            objectProperties.each {key, value ->
                if((!listProperties.contains(key))&&(!listProperties.contains(value.get("label")))){
                    keysRemove.add(key);
                }else{
                    if(listProperties.contains(key)) {
                        listProperties.remove(key)
                    }else if(listProperties.contains(value.get("label"))){
                        listProperties.remove(value.get("label"))
                    }
                }
            }
            //we remove the object properties which are not needed.
            keysRemove.each {String key ->
                objectProperties.remove(key);
            }
            if(!listProperties.isEmpty()){
                System.out.println("Object properties not founded:");
                System.out.println(listProperties.toString())
                System.exit(-1);
            }
        }
        return(objectProperties);
    }

    /**
     * It builds the graph.
     * @param ontology Ontology used to build the graph.
     * @param properties Object Properties that belong to ontology and they are using to create the graph.
     * @return Graph is built.
     */
    protected Graph buildGraph(OWLOntology ontology,HashMap<String,HashMap> properties) {
        DirectedPseudograph<String, RelationshipEdge> graph = new DirectedPseudograph<HashMap, RelationshipEdge>(new ClassBasedEdgeFactory<HashMap, RelationshipEdge>(RelationshipEdge.class));
        Set<OWLClass> classes = ontology.getClassesInSignature();
        //The OWL:Thing class is contained in the OWL language itself, that is why we have to be sure that the
        // axiom has been included.
        OWLDataFactory factory = ontology.getOWLOntologyManager().getOWLDataFactory();
        OWLClass thing = factory.getOWLClass(IRI.create(ontology.getOntologyID().getOntologyIRI().get()+"/owl:Thing"))
        classes.add(thing);
        int classesCounter = classes.size();
        int classesIndex = 0;
        classes.each { clazz ->
            progressBar.printProgressBar((int) Math.round((classesIndex * 100) / (classesCounter)), "building the graph...");
            classesIndex++;
            HashMap root = requestManager.class2info(clazz,ontology)
            if((root!=null)&&(!root.isEmpty())) {
                graph.addVertex(root);
                Set<HashMap> subClasses = requestManager.subClassesQuery(root.get("classURI"), ontology);
                RelationshipEdge edge = null;
                if(subClasses!=null){
                    subClasses.each { subClass ->
                        graph.addVertex(subClass);
                        edge = new RelationshipEdge<HashMap>(root,subClass,RDFS.subClassOf.getURI(),RDFS.subClassOf.getLocalName());
                        if(!graph.containsEdge(edge)) {
                            graph.addEdge(root, subClass, edge);
                        }
                    }
                }
                if ((properties != null) && (properties.size() > 0)) {
                    RelationshipEdge edgeProperty;
                    properties.each {String idProperty, HashMap value ->
                        Set<HashMap> result = requestManager.relationQuery(idProperty, root.get("classURI").toString(), ontology);
                        if(result!=null){
                            result.each { objectPropertyClass ->
                                graph.addVertex(objectPropertyClass);
                                edgeProperty = new RelationshipEdge<HashMap>(root,objectPropertyClass,idProperty,value.get("label"));
                                if(!graph.containsEdge(edgeProperty)) {
                                    graph.addEdge(root,objectPropertyClass, edgeProperty);
                                }
                            }
                        }
                    }
                }
            }
        }
        progressBar.printProgressBar(100, "building the graph...\n");
        return(graph);
    }

    /**
     * It serializes the built graph in a file.
     * @param graph The graph built that it will be serialised in a file.
     */
    public abstract void serializeGraph(Graph graph, HashMap<String,HashMap> properties);
}
