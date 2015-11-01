package view

import org.jgrapht.Graph
import show.ProgressBar


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
public class FlatFileFormatter extends ViewFormat {

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public FlatFileFormatter(String fileOutPath){
        super(fileOutPath);
    }

    public void serializeGraph(Graph graph){
        BufferedWriter output;
        try{
            if ((graph != null)&&(fileOutPath!=null)) {
                int edgesCount = graph.edgeSet().size();
                int edgesIndex = 0;
                Iterator its = graph.edgeSet().iterator();
                Object edge;
                HashMap source, destiny;
                output = new BufferedWriter(new FileWriter(fileOutPath+".flatfile"));
                output.append("\tNodes\t\t\t\tEdges+\n");
                while (its.hasNext()) {
                    edge = its.next();
                    edgesIndex++;
                    source = graph.getEdgeSource(edge);
                    destiny = graph.getEdgeTarget(edge);
                    ProgressBar.getInstance().printProgressBar((int) Math.round((edgesIndex * 100) / (edgesCount)), "serializing the graph...");
                    String[] objectProperty = edge.toString().split("&&");
                    if (objectProperty.length == 2) {
                        output.append("\t"+source.get("remainder")+",\t"+destiny.get("remainder")+"\t\t"+objectProperty[1]+"\n");
                    } else {
                        output.append("\t"+source.get("remainder")+",\t"+destiny.get("remainder")+"\t\tsubClassOf\n");

                    }
                }
                ProgressBar.getInstance().printProgressBar(100, "serializing the graph...");
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