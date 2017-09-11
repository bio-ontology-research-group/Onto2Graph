package marg.view

import marg.graph.RelationshipEdge
import marg.view.provider.EdgeLabelProvider
import marg.view.provider.VertexLabelProvider
import org.jgrapht.Graph
import org.jgrapht.ext.GraphMLExporter
import org.jgrapht.ext.IntegerEdgeNameProvider
import org.jgrapht.ext.IntegerNameProvider

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
 * in GraphML format.
 *
 * @author Miguel Angel Rodríguez-García
 * @version 1.0
 */
public class GraphMLFormatter extends ViewFormat {

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public GraphMLFormatter(String fileOutPath,boolean equivalentClass,boolean transitiveFlag){
        super(fileOutPath,equivalentClass,transitiveFlag);
    }

    /**
     * It serializes the graph in a GraphML Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph,HashMap<String,HashMap> properties){
        try{
            if ((graph != null)&&(fileOutPath!=null)) {
                progressBar.printProgressBar(100, "serializing the graph...");
                System.out.println();
                GraphMLExporter<HashMap, RelationshipEdge> exporter = new GraphMLExporter<HashMap, RelationshipEdge>(
                        new IntegerNameProvider(),new VertexLabelProvider(),new IntegerEdgeNameProvider(),new EdgeLabelProvider());

                FileWriter writer = new FileWriter(fileOutPath+".graphml");
                exporter.export(writer, graph);
            }
        } catch (IOException e) {
            System.out.println("There was an error: "+e.getMessage());
        }
    }


}