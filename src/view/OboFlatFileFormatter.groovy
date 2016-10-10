package view

import graph.RelationshipEdge
import org.apache.jena.vocabulary.RDFS
import org.coode.owlapi.obo.parser.OBOOntologyFormat
import org.jgrapht.Graph
import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.model.*

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
    public OboFlatFileFormatter(String fileOutPath,boolean equivalentClass,boolean transitiveFlag){
        super(fileOutPath,equivalentClass,transitiveFlag);
    }

    /**
     * It serializes the graph in a Flat File Format file.
     * @param graph The graph that will be saved.
     */
    public void serializeGraph(Graph graph,HashMap<String,HashMap> properties){
        BufferedWriter output;
        try{
            if ((graph != null)&&(fileOutPath!=null)) {
                OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
                OWLDataFactory factory = manager.getOWLDataFactory();
                OWLOntology ontology = null;
                int edgesIndex =0;
                int edgesCount = graph.edgeSet().size();
                Iterator eIt = graph.edgeSet().iterator();
                RelationshipEdge edge;
                HashMap rootEdge, subEdge;
                HashSet<String> relations = new HashMap<String>();
                while(eIt.hasNext()){
                    edge = eIt.next()
                    edgesIndex++;
                    rootEdge = graph.getEdgeSource(edge);
                    subEdge = graph.getEdgeTarget(edge);
                    if(ontology==null){
                        ontology = manager.createOntology(IRI.create(rootEdge.get("ontologyURI")));
                    }
                    progressBar.printProgressBar((int) Math.round((edgesIndex * 100) / (edgesCount)), "serializing the graph...");
                    OWLClass sourceClass = factory.getOWLClass(IRI.create(rootEdge.get("classURI")));
                    OWLClass destClass = factory.getOWLClass(IRI.create(subEdge.get("classURI")));

                    manager.addAxiom(ontology,factory.getOWLDeclarationAxiom(sourceClass));
                    manager.addAxiom(ontology,factory.getOWLDeclarationAxiom(destClass));

                    rootEdge.get("annotations").each { ArrayList<String> annotation ->
                        if(annotation.size()==2) {
                            OWLAnnotationProperty annProperty = factory.getOWLAnnotationProperty(IRI.create(annotation[0]));
                            OWLAnnotationValue annValue = factory.getOWLLiteral(annotation[1]);
                            manager.addAxiom(ontology,factory.getOWLAnnotationAssertionAxiom(annProperty,sourceClass.getIRI(),annValue));
                        }
                    }
                    subEdge.get("annotations").each{ ArrayList<String> annotation->
                        if(annotation.size()==2) {
                            OWLAnnotationProperty annProperty = factory.getOWLAnnotationProperty(IRI.create(annotation[0]));
                            OWLAnnotationValue annValue = factory.getOWLLiteral(annotation[1]);
                            manager.addAxiom(ontology,factory.getOWLAnnotationAssertionAxiom(annProperty,destClass.getIRI(),annValue));
                        }
                    }

                    if(edge.getURI().compareTo(RDFS.subClassOf.getURI())==0){
                        OWLAxiom subClassOf = factory.getOWLSubClassOfAxiom(destClass,sourceClass);
                        manager.addAxiom(ontology,subClassOf);
                    }else{
                        OWLProperty property = factory.getOWLObjectProperty(IRI.create(edge.getURI()));
                        OWLObjectSomeValuesFrom someValuesFrom = factory.getOWLObjectSomeValuesFrom(property,sourceClass)
                        manager.addAxiom(ontology,factory.getOWLDeclarationAxiom(property));
                        manager.addAxiom(ontology,factory.getOWLSubClassOfAxiom(destClass,someValuesFrom));
                        HashMap value = properties.get(edge.getURI());
                        value.get("annotations").each { ArrayList<String> annotation ->
                            if(annotation.size()==2) {
                                OWLAnnotationProperty annProperty = factory.getOWLAnnotationProperty(IRI.create(annotation[0]));
                                OWLAnnotationValue annValue = factory.getOWLLiteral(annotation[1]);
                                manager.addAxiom(ontology,factory.getOWLAnnotationAssertionAxiom(annProperty,property.getIRI(),annValue));
                            }
                        }
                    }

                }
                manager.saveOntology(ontology,new OBOOntologyFormat(),IRI.create(new File(fileOutPath+".obo")));
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