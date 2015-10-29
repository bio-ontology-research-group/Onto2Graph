import commands.VisualizerCommand
import org.apache.commons.cli.*
import org.semanticweb.HermiT.Reasoner
import org.semanticweb.elk.owlapi.ElkReasonerFactory
import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.io.FileDocumentSource
import org.semanticweb.owlapi.model.MissingImportHandlingStrategy
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration
import org.semanticweb.owlapi.model.OWLOntologyManager
import org.semanticweb.owlapi.reasoner.BufferingMode
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory
import org.semanticweb.owlapi.reasoner.SimpleConfiguration
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner
import view.FormatterType

import java.util.regex.Matcher
import java.util.regex.Pattern


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
 * Main class
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */

public class GraphicGeneratorTool {
    /**
     * Constant that represent the ELK reasoner.
     */
    public static final String ELK_REASONER="ELK";
    /**
     * Constant that represent the Hermit reasoner.
     */
    public static final String HERMIT_REASONER="HERMIT";
    /**
     * Constant that represent the structural reasoner.
     */
    public static final String STRUCTURAL_REASONER="STRUCTURAL_REASONER";

    /**
     * Constructor of the class
     */
    public GraphicGeneratorTool(){

    }

    /**
     * Basically, this method is responsible to create the VisualizerCommand and execute it.
     * @param reasoner The reasoner that will be used during the inference process.
     * @param ontology The ontology that will be transformed.
     * @param setFormatterType A set of formatter that will be used during the parse process.
     * @param outputPath File path where the graph will be serialized.
     * @param objectProperties A set of objects properties that will be used during the parse process.
     */
    public static void runGraphGenerator(OWLReasoner reasoner, OWLOntology ontology, Set<FormatterType> setFormatterType,
                                         String outputPath,String[] objectProperties){
        VisualizerCommand command = new VisualizerCommand(reasoner,ontology,setFormatterType,outputPath,objectProperties);
        command.executeCommand();
    }

    /**
     * It is in charge of loading the ontology.
     * @param pathOntology File path where the ontology is located.
     * @return Ontology loaded.
     */
    public static OWLOntology loadOntology(String pathOntology){
        OWLOntology ontology = null;
        if((pathOntology!=null)&&(!pathOntology.isEmpty())){
            File owlFile = new File(pathOntology);
            if(owlFile.exists()) {
                OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
                OWLOntologyLoaderConfiguration config = new OWLOntologyLoaderConfiguration();
                config.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);
                FileDocumentSource fSource = new FileDocumentSource(owlFile);
                ontology = manager.loadOntologyFromOntologyDocument(fSource, config);
            }
        }
        return(ontology);
    }

    /**
     * It creates a set of different Formatter type depending of the command line arguments given.
     * @param typeFormatter FormaterType reads from the command line arguments. This arguments have different values assigned:
     * RDFXML, GRAPHVIZ, FLATFILE, GRAPHML, and "*". The "*" represents all format type.
     * @return Set of different formatter type
     */
    public static Set<FormatterType> getVisualizationFormatter(String typeFormatter){
        if((typeFormatter!=null)&&(!typeFormatter.isEmpty())){
            typeFormatter = typeFormatter.trim().toUpperCase();
            Set<FormatterType> setFormatterType = new HashSet<FormatterType>();
            switch(typeFormatter){
                case "RDFXML":
                            setFormatterType.add(FormatterType.RDFXML_FORMATTER);
                            return(setFormatterType);
                case "GRAPHVIZ":
                            setFormatterType.add(FormatterType.GRAPHVIZ_FORMATTER);
                            return(setFormatterType);
                case "FLATFILE":
                            setFormatterType.add(FormatterType.FLATFILE_FORMATTER);
                            return(setFormatterType);
                case "GRAPHML":
                            setFormatterType.add(FormatterType.GRAPHML_FORMATTER);
                            return(setFormatterType);
                case "*": setFormatterType.add(FormatterType.RDFXML_FORMATTER);
                          setFormatterType.add(FormatterType.GRAPHVIZ_FORMATTER);
                          setFormatterType.add(FormatterType.FLATFILE_FORMATTER);
                          setFormatterType.add(FormatterType.GRAPHML_FORMATTER)
                          return(setFormatterType);
                default: return(FormatterType.GRAPHVIZ_FORMATTER);
            }
        }
        return(null);
    }

    /**
     * It creates the reasoner depending of the comand line arguments given.
     * @param typeReasoner It represents the reasoner to build.
     * @param ontology The ontology used to build the reasoner.
     * @return The reasoner created.
     */
    public static org.semanticweb.owlapi.reasoner.OWLReasoner getReasoner(String typeReasoner,OWLOntology ontology) {
        if ((typeReasoner != null) && (!typeReasoner.isEmpty())&&(ontology!=null)){
            typeReasoner = typeReasoner.trim().toUpperCase();
            switch(typeReasoner){
                case ELK_REASONER:
                    OWLReasonerFactory reasonerFactory = new ElkReasonerFactory();
                    return (reasonerFactory.createReasoner(ontology));
                case HERMIT_REASONER:
                    Reasoner hermit = new Reasoner(ontology);
                    return (hermit);
                case STRUCTURAL_REASONER:
                    StructuralReasoner structuralReasoner = new StructuralReasoner(ontology,new SimpleConfiguration(), BufferingMode.NON_BUFFERING);
                    return(structuralReasoner);
                default:
                    OWLReasonerFactory reasonerFactory = new ElkReasonerFactory();
                    return (reasonerFactory.createReasoner(ontology));
            }
        }
        return(null);
    }

    /**
     * It is responsible to create the different options that are used in command line. In particular, the application admit the following commands:
     * -ont This option represents the ontology.
     * -out This option represents the output path file.
     * -r This option will contain the reasoner that will used during the parse process.
     * -f This option will contain the formatter.
     * -op This option will contain a set of object properties defined on the ontology.
     * @return Options addmited as arguments in command line.
     */
    public static Options buildCommandLineOptions(){
        //args[0] The ontology file
        //args[1] Output path
        //args[2] Reasoners to use (ELK,Hermit)
        //args[3] Visualization formatter (RDFXML, GRAPHVIZ[DEFAULT], FLATFILE,GRAPHML)
        //args[4] List of properties to visualize should be on the format ["first_label", "second_label", "third_label"]

        String ontoFile = "ont";
        String ontoFileDescription = "This parameter should contain the ontology file or at least the path where the ontology is located.";
        Option ontoFileOption = OptionBuilder
                .hasArg(true)
                .isRequired(true)
                .withDescription(ontoFileDescription)
                .create(ontoFile);

        String outputPath = "out";
        String outputPathDescription = "This parameter represents the output path where the files generated by the tool will be saved (Optional)";
        Option outputOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(outputPathDescription)
                .create(outputPath);

        String reasoner = "r";
        String reasonerDescription = "The reasoner will be used (ELK[DEFAULT],HERMIT).";
        Option reasonerOption = OptionBuilder
                .hasArg(true)
                .isRequired(true)
                .withDescription(reasonerDescription)
                .create(reasoner);

        String format = "f";
        String formatDescription = "The different visualization formats available (RDFXML, GRAPHVIZ[DEFAULT], FLATFILE,GRAPHML) (Optional).\n"+
                                    "In order to generate all formats available just provide : \"*\". \n";
        Option formatOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(formatDescription)
                .create(format);

        String objectProperties = "op";
        String objectPropertiesDescription = "args[4] -op This parameter will contain the list of object properties labels that will be used to visualized the ontology (Optional). \n"+
                                        "The object properties should be formatted as array, here you can see an example: [\"first_label\",\"second_label\",\"third_label\"]. \n"+
                                        "In order to include all object properties from an ontology given just provide: [\"*\"]. \n";
        Option objectPropertiesOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(objectPropertiesDescription)
                .create(objectProperties);

        Options options = new Options();
        options.addOption(ontoFileOption);
        options.addOption(outputOption);
        options.addOption(reasonerOption);
        options.addOption(formatOption);
        options.addOption(objectPropertiesOption);

        return(options);
    }

    /**
     * It builds a info message that contain the description of the parameters that are used in the aplication.
     * @return The message that contains a little description of the all parameters that are needed to execute the application.
     */
    public static String getManual(){
        String info="These are the parameters that should be provided: \n";
        info+="-ont This parameter should contain the ontology file or at least the path where the ontology is located. \n";
        info+="-out This parameter represents the output path where the files generated by the tool will be saved (Optional). \n";
        info+="-r The reasoner will be used (ELK[DEFAULT],HERMIT,STRUCTURAL_REASONER). \n";
        info+="-f The different visualization formats available (RDFXML, GRAPHVIZ[DEFAULT], FLATFILE,GRAPHML) (Optional). \n";
        info+="\tIn order to generate all formats available just provide \"*\". \n";
        info+="-op This parameter will contain the list of object properties labels that will be used to visualized the ontology (Optional). \n";
        info+="The object properties should be formatted as array, here you can see an example: [\"first_label\",\"second_label\",\"third_label\"]. \n";
        info+="\tIn order to include all object properties from an ontology given just provide: [\"*\"]. \n";
        return(info);
    }

    /**
     * The main of the applications. Basically the aim of this method is to iniciate the parameters passed through command line and then the
     * command in charge of parsing the ontology will be executed.
     * @param args The list of arguments that will be passed through command line.
     */
    public static void main (String[] args){

        try{
            OWLOntology ontology = null;
            OWLReasoner reasoner = null;
            String outputPath = null;
            Set<FormatterType> setFormatterType = null;
            String[] properties = null;
            CommandLineParser parser = new GnuParser();
            CommandLine commandLine = parser.parse(buildCommandLineOptions(),args);
            if(commandLine.hasOption("ont")){
                String ontoFile = commandLine.getOptionValue("ont");
                if((ontoFile!=null)&&(!ontoFile.isEmpty())){
                    ontology = loadOntology(ontoFile);
                }
            }
            if(commandLine.hasOption("out")){
                outputPath = commandLine.getOptionValue("out");
                if ((outputPath == null) || (outputPath.isEmpty())) {
                    outputPath = System.getProperty("user.dir")+System.getProperty("file.separator")+"graph";
                }
            }
            if(commandLine.hasOption("r")){
                String sReasoner = commandLine.getOptionValue("r");
                reasoner = getReasoner(sReasoner,ontology);
            }else{
                reasoner = getReasoner("DEFAULT",ontology);
            }

            if(commandLine.hasOption("f")){
                String sFormatterType = commandLine.getOptionValue("f");
                if((sFormatterType!=null)&&(!sFormatterType.isEmpty())){
                    setFormatterType = getVisualizationFormatter(sFormatterType);
                }
            }else{
                setFormatterType = getVisualizationFormatter("DEFAULT");
            }
            if(commandLine.hasOption("op")){
                String sProperties = commandLine.getOptionValue("op");
                if((sProperties!=null)&&(!sProperties.isEmpty())){
                    Pattern pattern = Pattern.compile("\\[(.*)\\]");
                    Matcher matcher = pattern.matcher(sProperties);
                    if(matcher.find()&&(matcher.groupCount()>0)) {
                        String sObjectProperties = matcher.group(1);
                        if(sObjectProperties.contains(",")){
                            properties = sObjectProperties.split(",");
                        }else{
                            properties = new String[1];
                            properties[0] = sObjectProperties;
                        }
                    }
                }
            }
            if((ontology!=null)&&(outputPath!=null)&&(reasoner!=null)&&(setFormatterType!=null)) {
                runGraphGenerator(reasoner,ontology, setFormatterType,outputPath, properties);
            }else{
                System.out.println("Error providing the parameters");
                System.out.println(getManual());
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error providing the parameters");
            System.out.println(getManual());
        }
    }
}
