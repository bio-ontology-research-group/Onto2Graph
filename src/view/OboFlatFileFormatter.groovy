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
public class OboFlatFileFormatter extends ViewFormat {

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public OboFlatFileFormatter(String fileOutPath,boolean equivalentClass){
        super(fileOutPath,equivalentClass);
    }

    /**
     * It serializes the graph in a Flat File Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph){
        BufferedWriter output;
        try{
            if ((graph != null)&&(fileOutPath!=null)) {
                output = new BufferedWriter(new FileWriter(fileOutPath+".obo"));
                output.append("format-version: 1.2\n");
                output.append("data-version: releases/2016-02-04\n");
                output.append("date: 03:02:2016 07:51\n");
                output.append("saved-by: bc\n");
                output.append("auto-generated-by: Onto2Graph 1.0\n");
                int counter = graph.vertexSet().size();
                int index =0;
                Iterator vIt = graph.vertexSet().iterator();
                Object vertex;
                Object rootEdge, subEdge;
                HashSet<String> relations = new HashMap<String>();
                while(vIt.hasNext()){
                    vertex = vIt.next()
                    Set edges = graph.edgesOf(vertex);
                    index++;
                    progressBar.printProgressBar((int) Math.round((index * 100) / (counter)), "serializing the graph...");
                    output.println("[Term]");
                    output.println("id: "+vertex.get("remainder").replace("_",":"));
                    output.println("name: "+vertex.get("label"));
                    //output.println("def: ");
                    //output.println("synonym:")
                    String relation="";
                    for(Object edge : edges){
                        relation="";
                        String[] objectProperty = edge.toString().split("&&");
                        rootEdge = graph.getEdgeSource(edge);
                        subEdge = graph.getEdgeTarget(edge);
                        if (objectProperty.length == 2) {
                            output.println(objectProperty[1]+": "+subEdge.get("remainder").replace("_",":")+" ! "+objectProperty[1]);
                            relation="[Typedef]\n";
                            relation+="id: "+objectProperty+"\n";
                            relation+="name: "+objectProperty+"\n";
                        } else {
                            output.println("subClassOf: "+subEdge.get("remainder").replace("_",":")+" ! "+subEdge.get("label"));
                            relation="[Typedef]\n";
                            relation+="id: subClassOf\n";
                            relation+="name: subClassOf\n";
                        }
                        relations.add(relation);
                    }
                    output.println();
                }

                for(String relation : relations){
                    output.println(relation)
                }

                progressBar.printProgressBar(100, "serializing the graph...");
                System.out.println();
            }
        } catch ( IOException e ) {
            System.out.println("There was an error: "+e.getMessage());
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }

}