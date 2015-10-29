package view

import edu.uci.ics.jung.graph.DirectedSparseGraph
import edu.uci.ics.jung.graph.Graph
import org.semanticweb.owlapi.model.OWLClass
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
     * Constructor of the class
     */
    public ViewFormat(){
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
    public void parseOntology(OWLOntology ontology,OWLReasoner reasoner, String[] properties,String fileOutPath) {
        if((ontology!=null)&&(reasoner!=null)) {
            properties = checkObjectProperties(ontology,properties);
            RequestManager.getInstance().computedSubClases(ontology,reasoner,properties);
            Graph graph = this.buildGraph(ontology, properties);
            this.serializeGraph(fileOutPath,graph);
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
        DirectedSparseGraph graph = new DirectedSparseGraph<>();
        Set<OWLClass> classes = ontology.getClassesInSignature(true);
        int classesIndex = 0;
        int classesCounter = ontology.getClassesInSignature(true).size();
        classes.remove(ontology.getOWLOntologyManager().getOWLDataFactory().getOWLNothing());
        classes.each { clazz ->
            ProgressBar.getInstance().printProgressBar((int) Math.round((classesIndex * 100) / (classesCounter)), "building the graph...");
            classesIndex++;
            HashMap root = RequestManager.getInstance().class2info(clazz,ontology)
            if((root!=null)&&(!root.isEmpty())) {
                graph.addVertex(root);
                Set<HashMap> subClasses = RequestManager.getInstance().subClassesQuery(root.get("owlClass"), ontology);
                String edge = "";
                if(subClasses!=null){
                    subClasses.each { subClass ->
                        graph.addVertex(subClass);
                        edge = root.get("classURI") + subClass.get("classURI");
                        if(!graph.containsEdge(edge)) {
                            graph.addEdge(edge, root, subClass);
                        }
                    }
                }
                if ((properties != null) && (properties.length > 0)) {
                    String objectProperty;
                    String edgeProperty;
                    for (int i = 0; i < properties.length; i++) {
                        objectProperty = properties[i];
                        if (objectProperty != null) {
                            Set<HashMap> result = RequestManager.getInstance().relationQuery(objectProperty, root.get("owlClass").toString(), ontology);
                            if(result!=null){
                                result.each { objectPropertyClass ->
                                    graph.addVertex(objectPropertyClass);
                                    edgeProperty = root.get("classURI") + objectPropertyClass.get("classURI") + "&&" + objectProperty;
                                    if(!graph.containsEdge(edgeProperty)) {
                                        graph.addEdge(edgeProperty, root, objectPropertyClass);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ProgressBar.getInstance().printProgressBar(100, "building the graph...");
        return(graph);
    }

    /**
     * It serializes the built graph in a file.
     * @param fileOutPath The file path where the graph will be saved.
     * @param graph The graph built that it will be serialised in a file.
     */
    protected void serializeGraph(String fileOutPath,Graph graph){
        if((graph!=null)&&(fileOutPath!=null)&&(!fileOutPath.isEmpty())){
            BufferedWriter output;
            try {
                File file = new File(fileOutPath+getExtension());
                output = new BufferedWriter(new FileWriter(file));

                if(output!=null){
                    if(graph!=null){
                        output.append(this.getHeader());
                        int edgesCount = graph.getEdgeCount();
                        int edgesIndex = 0;
                        Iterator it = graph.getEdges().iterator();
                        Object edge;
                        HashMap source,destiny;
                        while(it.hasNext()){
                            edge = it.next();
                            edgesIndex++;
                            source = graph.getSource(edge);
                            destiny = graph.getDest(edge);
                            ProgressBar.getInstance().printProgressBar((int) Math.round((edgesIndex * 100) / (edgesCount)), "serializing the graph...");
                            String[] objectProperty = edge.split("&&");
                            if (objectProperty.length == 2) {
                                output.append(formatter(source,destiny, objectProperty[1]));
                            }else{
                                output.append(formatter(source,destiny));
                            }
                        }
                        output.append(this.getFooter());
                        ProgressBar.getInstance().printProgressBar(100,"serializing the graph...");
                        System.out.println();
                    }
                }

            } catch ( IOException e ) {
                System.out.println("There was an error: "+e.getMessage());
            } finally {
                if ( output != null ) {
                    output.close();
                }
            }
        }else{
            System.out.println("The file was not created");
            System.exit(-1);
        }
    }

    /**
     * It is in charge of obtaining the text from the label.
     * @param label The label that will be filtered.
     * @return The label filtered.
     */
    protected String filterLabel(String label){
        if(label!=null) {
            label = label.replace("<", "");
            label = label.replace(">", "");
        }
        return(label);
    }

    /**
     * It retrieves the header of the specific format
     * @return The header of the specific format
     */
    public abstract String getHeader();

    /**
     * This method will provide the footer of a specific format.
     * @return The footer of the specific formatter.
     */
    public abstract String getFooter();

    /**
     * It is in charge of transforming into a specific format a root class given and its subclass.     * @param rootClass
     * @param rootClass The root class that will be formatted in the correct format.
     * @param subClass The subclass that will be formatted in the correct format.
     * @return The classes transformed into the correct format.
     */
    public abstract String formatter(HashMap rootClass,HashMap subClass)

    /**
     * It is in charge of transforming into a specific format a complete relation given (root->object_property->class)
     * @param rootClass The root clas that will be formatted in the correct format.
     * @param subClass The subclass that will be formatted in the correct format.
     * @param objectProperty The object property that will be formatted.
     * @return The whole transformation of the root class, subclass and object property that related both.
     */
    public abstract String formatter(HashMap rootClass,HashMap subClass, String objectProperty)

    /**
     * It provides the extension of the file.
     * @return The extension of the file
     */
    public abstract String getExtension();

}
