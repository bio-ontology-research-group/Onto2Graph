package view

import edu.uci.ics.jung.graph.Graph
import javafx.beans.property.ObjectProperty
import org.apache.jena.assembler.assemblers.DefaultModelAssembler
import org.apache.jena.ontology.OntClass
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource
import org.apache.jena.riot.RDFFormat
import org.apache.jena.vocabulary.RDFS
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
 * in RDFXML format.
 *
 * @author Miguel Angel Rodríguez-García
 * @version 1.0
 */
public class RDFXMLFormatter extends ViewFormat{

    /**
     * Constructor of the class
     * @param fileOutPath The file path where the graph will be serialized.
     */
    public RDFXMLFormatter(String fileOutPath){
        super(fileOutPath);
    }

    /**
     * It serializes the graph in a RDFXML File Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph){
        try {
            if ((graph != null)&&(fileOutPath!=null)) {
                OntModel model = ModelFactory.createOntologyModel();
                int edgesCount = graph.edgeSet().size();
                int edgesIndex = 0;
                Iterator its = graph.edgeSet().iterator();
                Object edge;
                HashMap source, destiny;
                while (its.hasNext()) {
                    edge = its.next();
                    edgesIndex++;
                    source = graph.getEdgeSource(edge);
                    destiny = graph.getEdgeTarget(edge);
                    ProgressBar.printProgressBar((int) Math.round((edgesIndex * 100) / (edgesCount)), "serializing the graph...");
                    String[] objectProperty = edge.toString().split("&&");
                    if (objectProperty.length == 2) {
                        OntClass rootClass = model.createClass(source.get("classURI"));
                        OntClass subClass = model.createClass(destiny.get("classURI"));
                        Property objProperty = model.createProperty(objectProperty[1]);
                        model.add(rootClass,objProperty,subClass)
                    } else {
                        OntClass rootClass = model.createClass(source.get("classURI"));
                        OntClass subClass = model.createClass(destiny.get("classURI"));
                        model.add(rootClass, RDFS.subClassOf, subClass);
                    }
                }
                ProgressBar.printProgressBar(100, "serializing the graph...");
                System.out.println();
                model.write(new FileOutputStream(fileOutPath+".rdfxml"), "RDF/XML");
            }
        }catch(Exception e){
            System.out.println("There was an error: "+e.getMessage());
        }
    }

}