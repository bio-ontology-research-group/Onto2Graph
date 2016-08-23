package view


import graph.RelationshipEdge
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

    protected void serializeRDFModel(Graph graph,HashMap<String,HashMap> properties, String extension, String format){
        Model model = ModelFactory.createDefaultModel();
        int edgesCount = graph.edgeSet().size();
        int edgesIndex = 0;
        Iterator its = graph.edgeSet().iterator();
        RelationshipEdge edge;
        HashMap rootEdge, subEdge;
        Resource rootClass,subClass;
        Property objProperty;
        //first we add the whole graph.
        while (its.hasNext()) {
            edge = (RelationshipEdge)its.next();
            edgesIndex++;
            rootEdge = graph.getEdgeSource(edge);
            subEdge = graph.getEdgeTarget(edge);
            progressBar.printProgressBar((int) Math.round((edgesIndex * 100) / (edgesCount)), "serializing the graph...");

            rootClass = model.createResource(rootEdge.get("classURI"));
            subClass = model.createResource(subEdge.get("classURI"));

            Literal rootLabel = model.createLiteral(rootEdge.get("label"), "en" );
            Literal subLabel = model.createLiteral(subEdge.get("label"), "en" );
            objProperty = model.createProperty(edge.getURI());
            rootClass.addLiteral(RDFS.label,rootLabel);
            subClass.addLiteral(RDFS.label,subLabel);

            rootEdge.get("annotations").each { ArrayList<String> annotation ->
                if(annotation.size()==2) {
                    Property annProperty = model.createProperty(annotation[0])
                    Literal literal = model.createLiteral(annotation[1], "en" )
                    rootClass.addLiteral(annProperty, literal);
                }
            }
            subEdge.get("annotations").each{ ArrayList<String> annotation->
                if(annotation.size()==2) {
                    Property annProperty = model.createProperty(annotation[0])
                    Literal literal = model.createLiteral(annotation[1], "en" )
                    subClass.addLiteral(annProperty, literal)
                }
            }

            if(properties.containsKey(edge.getURI())){
                HashMap value = properties.get(edge.getURI());
                Literal propertyLabel = model.createLiteral(value.get("label"), "en" );
                objProperty.addLiteral(RDFS.label,propertyLabel);
                value.get("annotations").each { ArrayList<String> annotation ->
                    if(annotation.size()==2) {
                        Property annProperty = model.createProperty(annotation[0])
                        Literal literal = model.createLiteral(annotation[1], "en" )
                        objProperty.addLiteral(annProperty, literal);
                    }
                }
            }else{//subClassOf relationship
                Literal propertyLabel = model.createLiteral(RDFS.subClassOf.getLocalName(), "en" );
                objProperty.addLiteral(RDFS.label,propertyLabel);
            }
            model.add(subClass, objProperty, rootClass);
        }

        progressBar.printProgressBar(100, "serializing the graph...");
        model.write(new FileOutputStream(fileOutPath+"."+extension), format);

    }

    /**
     * It serializes the graph in a RDFXML File Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph, HashMap<String,HashMap> properties){
        try {
            if ((graph != null)&&(fileOutPath!=null)) {
                serializeRDFModel(graph,properties,"rdfxml", "RDF/XML");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("There was an error: "+e.getMessage());
        }
    }

}