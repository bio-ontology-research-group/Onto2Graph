package commands

import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner
import view.*

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
 * This command is in charge of generating an output file that will contain a graphic representation of an ontology given.
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class VisualizerCommand implements Command{
    /**
     * The reasoner that is used to infer the subclasses.
     */
    protected OWLReasoner reasoner = null;
    /**
     * The ontology that will be parsed in a specific format.
     */
    protected OWLOntology ontology = null;
    /**
     * This flag controls whether or not the resulting graph will contain equivalent classes.
     */
    protected boolean equivalentClass;
    /**
     * The list of Object Properties that will be used during the transformation.
     */
    protected String[] properties = null;
    /**
     * The list of formatters that will be used to transform the ontology.
     */
    protected Set<FormatterType> setFormatterType = null;
    /**
     * The outpath of the file
     */
    protected String outpath = null;
    /**
     * Constructor of the class.
     * @param reasoner The reasoner that will be used to infer the subclasses.
     * @param ontology the ontology that will be parsed in a specific format.
     * @param setFormatterType The list of formatters that will be used to transform the ontology.
     * @param outpath The outpath of file.
     * @param equivalentClass Flag that contols whether (TRUE) or not (FALSE) the equivalent classes are going to be merged as unique node.
     * @param properties The list of Object Properties that will be used during the transformation.
     */
    public VisualizerCommand(OWLReasoner reasoner,OWLOntology ontology,Set<FormatterType> setFormatterType,String outpath,
                             boolean equivalentClasses, String[] properties){
        this.reasoner = reasoner;
        this.ontology = ontology;
        this.setFormatterType = setFormatterType;
        this.equivalentClass = equivalentClasses;
        this.outpath = outpath;
        this.properties = properties;
    }

    /**
     * The aim of this method is to execute the functions necesary to parse the ontology. In order to do that, firstly the
     * formatter is built and the ontology parsing process is accomplished.
     */
    public void executeCommand() {
        ViewFormat viewFormat = null;
        Iterator<FormatterType> it = setFormatterType.iterator();
        FormatterType formatterType;
        while(it.hasNext()){
            formatterType = it.next();
            if (formatterType == FormatterType.RDFXML_FORMATTER) {
                viewFormat = new RDFXMLFormatter(outpath,equivalentClass);
            } else if (formatterType == FormatterType.GRAPHVIZ_FORMATTER) {
                viewFormat = new GraphVizFormatter(outpath,equivalentClass);
            } else if (formatterType == FormatterType.GRAPHML_FORMATTER) {
                viewFormat = new GraphMLFormatter(outpath,equivalentClass);
            } else if (formatterType == FormatterType.FLATFILE_FORMATTER) {
                viewFormat = new FlatFileFormatter(outpath,equivalentClass);
            }
            if (viewFormat != null) {
                viewFormat.parseOntology(ontology, reasoner, properties);
            }
        }

    }
}
