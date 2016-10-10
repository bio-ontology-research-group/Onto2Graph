package view

import org.jgrapht.Graph

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
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in Flatfile format.
 *
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */
public class OntoFuncFormatter extends ViewFormat {

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public OntoFuncFormatter(String fileOutPath,boolean equivalentClass,boolean transitiveFlag){
        super(fileOutPath,equivalentClass,transitiveFlag);
    }

    /**
     * It serializes the graph in a Flat File Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph,HashMap<String,HashMap> properties){
        BufferedWriter graphPathOutput;
        BufferedWriter term2termOutput;
        BufferedWriter termOutput
        try{
            if ((graph != null)&&(fileOutPath!=null)) {
                graphPathOutput = new BufferedWriter(new FileWriter(fileOutPath+"_graph_graph.txt"));
                term2termOutput = new BufferedWriter(new FileWriter(fileOutPath+"_term2term.txt"));
                termOutput = new BufferedWriter(new FileWriter(fileOutPath+"_term.txt"));
                int counter = graph.vertexSet().size();
                int index =0;
                Iterator vIt = graph.vertexSet().iterator();
                Object vertex;
                Object subEdge;
                int id=1;
                while(vIt.hasNext()){
                    vertex = vIt.next()
                    Set edges = graph.edgesOf(vertex);
                    index++;
                    progressBar.printProgressBar((int) Math.round((index * 100) / (counter)), "serializing the graph...");
                    String relation;
                    for(Object edge : edges){
                        String[] objectProperty = edge.toString().split("&&");
                        subEdge = graph.getEdgeTarget(edge);
                        termOutput.println(vertex.get("remainder").replace("_",":")+"\t"+vertex.get("label")+
                                "\towl:Thing\t"+subEdge.getAt("remainder").replace("_",":")+"\tend");
                        if (objectProperty.length == 2) {
                            graphPathOutput.println(id+"\t"+vertex.get("remainder").replace("_",":")+
                                    "\t"+subEdge.getAt("remainder").replace("_",":")+"\t"+objectProperty[1]+"\t0\t0");
                            term2termOutput.println(id+"\t"+objectProperty[1]+"\t"+vertex.get("remainder").replace("_",":")+
                                    "\t"+subEdge.getAt("remainder").replace("_",":"));
                        } else {
                            graphPathOutput.println(id+"\t"+vertex.get("remainder").replace("_",":")+
                                    "\t"+subEdge.getAt("remainder").replace("_",":")+"\tsubClassOf\t0\t0");
                            term2termOutput.println(id+"\tsubClassOf\t"+vertex.get("remainder").replace("_",":")+
                                    "\t"+subEdge.getAt("remainder").replace("_",":"));
                        }
                        id++;
                    }
                }

                progressBar.printProgressBar(100, "serializing the graph...");
                graphPathOutput.close();
                term2termOutput.close();
                termOutput.close();
            }
        } catch ( IOException e ) {
            System.out.println("There was an error: "+e.getMessage());
        } finally {
            if ( graphPathOutput != null ) {
                graphPathOutput.close();
            }
            if (term2termOutput != null) {
                term2termOutput.close();
            }
            if( termOutput != null) {
                termOutput.close();
            }
        }
    }

}