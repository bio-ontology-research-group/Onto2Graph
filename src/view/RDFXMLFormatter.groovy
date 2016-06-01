package view

import org.apache.jena.rdf.model.*
import org.apache.jena.riot.web.LangTag
import org.apache.jena.vocabulary.RDFS
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
    public RDFXMLFormatter(String fileOutPath,boolean equivalentClass){
        super(fileOutPath,equivalentClass);
    }

    private Literal createLiteral(Model model, String literal){
        Literal literal1 = null;
        if(literal.contains("@")){
            String[] props = literal.split("@");
            //We control the misdefined languages.
            if((props.length==2)&&(!props[1].isEmpty())&&(LangTag.parse(props[1]))) {
                literal1 = model.createLiteral(props[0], props[1])
            }else{
                literal1 = model.createLiteral(literal)
            }
        }else{
            literal1 = model.createLiteral(literal)
        }
        return(literal1);
    }

    /**
     * It serializes the graph in a RDFXML File Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph){
        try {
            if ((graph != null)&&(fileOutPath!=null)) {
                Model model = ModelFactory.createDefaultModel();
                int edgesCount = graph.edgeSet().size();
                int edgesIndex = 0;
                Iterator its = graph.edgeSet().iterator();
                Object edge;
                HashMap rootEdge, subEdge;
                Resource rootClass,subClass;
                while (its.hasNext()) {
                    edge = its.next();
                    edgesIndex++;
                    rootEdge = graph.getEdgeSource(edge);
                    subEdge = graph.getEdgeTarget(edge);
                    progressBar.printProgressBar((int) Math.round((edgesIndex * 100) / (edgesCount)), "serializing the graph...");
                    String[] objectProperty = edge.toString().split("&&");
                    Property objProperty = null
                    if (objectProperty.length == 2) {
                        rootClass = model.createResource(rootEdge.get("classURI"));
                        subClass = model.createResource(subEdge.get("classURI"));
                        objProperty = model.createProperty(objectProperty[1]);
                    } else {
                        rootClass = model.createResource(rootEdge.get("classURI"));
                        subClass = model.createResource(subEdge.get("classURI"));
                        objProperty = RDFS.subClassOf;
                    }
                    Literal rootLabel = model.createLiteral(rootEdge.get("label"), "en" );
                    Literal subLabel = model.createLiteral(subEdge.get("label"), "en" );
                    rootClass.addLiteral(RDFS.label,rootLabel)
                    subClass.addLiteral(RDFS.label,subLabel)

                    rootEdge.get("annotations").each { ArrayList<String> annotation ->
                        if(annotation.size()==2) {
                            Property annProperty = model.createProperty(annotation[0])
                            Literal literal = createLiteral(model,annotation[1])
                            rootClass.addLiteral(annProperty, literal);
                        }
                    }
                    subEdge.get("annotations").each{ ArrayList<String> annotation->
                        if(annotation.size()==2) {
                            Property annProperty = model.createProperty(annotation[0])
                            Literal literal = createLiteral(model,annotation[1])
                            subClass.addLiteral(annProperty, literal)
                        }
                    }

                    model.add(subClass, objProperty, rootClass);

                }
                progressBar.printProgressBar(100, "serializing the graph...");
                model.write(new FileOutputStream(fileOutPath+".rdfxml"), "RDF/XML");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There was an error: "+e.getMessage());
        }
    }

}