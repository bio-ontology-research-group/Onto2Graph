package marg.commands

import marg.view.*
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner

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
     * The list of reasoners that is used to infer the subclasses.
     */
    protected List<OWLReasoner> reasoners = null;
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
     * Transitive flag
     */
    protected boolean transitiveFlag;
    /**
     * The list of formatters that will be used to transform the ontology.
     */
    protected Set<FormatterType> setFormatterType = null;
    /**
     * The outpath of the file
     */
    protected String outpath = null;

    /**
     * The number of the threads that the application is going to use.
     */
    protected int nThreads;

    /**
     * Constructor of the class.
     * @param reasoners The list of reasoners that will be used to infer the subclasses in parallel.
     * @param ontology the ontology that will be parsed in a specific format.
     * @param setFormatterType The list of formatters that will be used to transform the ontology.
     * @param outpath The outpath of file.
     * @param equivalentClass Flag that contols whether (TRUE) or not (FALSE) the equivalent classes are going to be merged as unique node.
     * @param transitiveFlag that controls whether (TRUE) or not (FALSE) which version of the algorithm will be used to infer the transitive object properties.
     * @param properties The list of Object Properties that will be used during the transformation.
     */
    public VisualizerCommand(List<OWLReasoner> reasoners,OWLOntology ontology,Set<FormatterType> setFormatterType,String outpath,
                             boolean equivalentClasses, String[] properties, boolean transitiveFlag, int nThreads){
        this.reasoners = reasoners;
        this.ontology = ontology;
        this.setFormatterType = setFormatterType;
        this.equivalentClass = equivalentClasses;
        this.outpath = outpath;
        this.properties = properties;
        this.transitiveFlag = transitiveFlag;
        this.nThreads = nThreads;
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
            if (formatterType == FormatterType.JSONLD_FORMATTER) {
                viewFormat = new JSONLDFormatter(outpath,equivalentClass,transitiveFlag);
            } else if (formatterType == FormatterType.RDFXML_FORMATTER) {
                viewFormat = new RDFXMLFormatter(outpath,equivalentClass,transitiveFlag);
            } else if (formatterType == FormatterType.GRAPHVIZ_FORMATTER) {
                viewFormat = new GraphVizFormatter(outpath,equivalentClass,transitiveFlag);
            } else if (formatterType == FormatterType.GRAPHML_FORMATTER) {
                viewFormat = new GraphMLFormatter(outpath,equivalentClass,transitiveFlag);
            } else if (formatterType == FormatterType.OBOFLATFILE_FORMATTER) {
                viewFormat = new OboFlatFileFormatter(outpath,equivalentClass,transitiveFlag);
            } else if (formatterType == FormatterType.ONTOFUNC_FORMATTER){
                viewFormat = new OntoFuncFormatter(outpath, equivalentClass,transitiveFlag);
            }
            if (viewFormat != null) {
                if((reasoners == null)||(reasoners.isEmpty())){
                //if(reasoners == null){//SYNTACTIC REASONER
                    viewFormat.parseOntology(ontology,properties,nThreads);
                }else{
                    viewFormat.parseOntology(ontology, reasoners, properties,nThreads);
                }
            }
        }

    }
}
