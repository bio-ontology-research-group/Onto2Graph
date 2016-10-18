import commands.VisualizerCommand
import org.apache.commons.cli.*
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.semanticweb.HermiT.Reasoner
import org.semanticweb.elk.owlapi.ElkReasonerFactory
import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.io.FileDocumentSource
import org.semanticweb.owlapi.model.MissingImportHandlingStrategy
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration
import org.semanticweb.owlapi.model.OWLOntologyManager
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory
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

@Grapes([
        @Grab(group='log4j', module='log4j', version='1.2.17'),
        @Grab(group='jgraph', module='jgraph', version='5.13.0.0'),
        @Grab(group='org.apache.jena', module='apache-jena-libs', version='3.0.0'),
        @Grab(group='org.apache.commons', module='commons-io', version='1.3.1'),
        @Grab(group='com.hermit-reasoner', module='org.semanticweb.hermit', version='1.3.8.4'),
        @Grab(group='commons-cli', module='commons-cli', version='1.3.1'),
        @Grab(group='net.sourceforge.owlapi', module='owlapi-distribution', version='4.2.5'),
        @Grab(group='org.semanticweb.elk', module='elk-reasoner', version='0.4.3')
])


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
     * Constant that represent the syntactic reasoner.
     */
    public static final String SYNTACTIC_REASONER = "SYNTACTIC_REASONER"

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
    public static void runGraphGenerator(List<OWLReasoner> reasoners, OWLOntology ontology, Set<FormatterType> setFormatterType,
                                         String outputPath,boolean equivalentClasses,String[] objectProperties,boolean transitiveFlag,int nThreads){
        System.out.println("PARAMETERS:");
        if((reasoners!=null)&&(!reasoners.isEmpty())) {
            System.out.println("Reasoner:" + reasoners.get(0).toString());
        }
        System.out.println("Formatter:"+setFormatterType);
        System.out.println("EquivalentClasses flag:"+equivalentClasses);
        System.out.println("Transitive flag:"+transitiveFlag);
        System.out.println("Formatter:"+setFormatterType);
        System.out.println("Object properties:"+objectProperties.toString());
        System.out.println("Number of threads:"+nThreads);
        VisualizerCommand command = new VisualizerCommand(reasoners,ontology,setFormatterType,outputPath,equivalentClasses,objectProperties,transitiveFlag,nThreads);
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
                config = config.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);
                FileDocumentSource fSource = new FileDocumentSource(owlFile);
                ontology = manager.loadOntologyFromOntologyDocument(fSource, config);
            }
        }
        return(ontology);
    }

    /**
     * It creates a set of different Formatter type depending of the command line arguments given.
     * @param typeFormatter FormaterType reads from the command line arguments. This arguments have different values assigned:
     * RDFXML, GRAPHVIZ, OBOFLATFILE, GRAPHML, and "*". The "*" represents all format type.
     * @return Set of different formatter type
     */
    public static Set<FormatterType> getVisualizationFormatter(String typeFormatter){
        if((typeFormatter!=null)&&(!typeFormatter.isEmpty())){
            typeFormatter = typeFormatter.trim().toUpperCase();
            Set<FormatterType> setFormatterType = new HashSet<FormatterType>();
            switch(typeFormatter){
                case "JSONLD":
                            setFormatterType.add(FormatterType.JSONLD_FORMATTER);
                            return(setFormatterType);
                case "RDFXML":
                            setFormatterType.add(FormatterType.RDFXML_FORMATTER);
                            return(setFormatterType);
                case "GRAPHVIZ":
                            setFormatterType.add(FormatterType.GRAPHVIZ_FORMATTER);
                            return(setFormatterType);
                case "OBOFLATFILE":
                            setFormatterType.add(FormatterType.OBOFLATFILE_FORMATTER);
                            return(setFormatterType);
                case "GRAPHML":
                            setFormatterType.add(FormatterType.GRAPHML_FORMATTER);
                            return(setFormatterType);
                case "ONTOFUNC":
                            setFormatterType.add(FormatterType.ONTOFUNC_FORMATTER);
                            return(setFormatterType);
                case "*":
                          setFormatterType.add(FormatterType.JSONLD_FORMATTER);
                          setFormatterType.add(FormatterType.RDFXML_FORMATTER);
                          setFormatterType.add(FormatterType.GRAPHVIZ_FORMATTER);
                          setFormatterType.add(FormatterType.OBOFLATFILE_FORMATTER);
                          setFormatterType.add(FormatterType.GRAPHML_FORMATTER);
                          setFormatterType.add(FormatterType.ONTOFUNC_FORMATTER);
                    return(setFormatterType);
                default: return(setFormatterType.add(FormatterType.GRAPHVIZ_FORMATTER));
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
                case HERMIT_REASONER:
                    Reasoner hermit = new Reasoner(ontology);
                    return (hermit);
                case STRUCTURAL_REASONER:
                    OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
                    OWLReasoner structuralReasoner = reasonerFactory.createReasoner(ontology);
                    structuralReasoner.precomputeInferences();
                    return(structuralReasoner);
                case SYNTACTIC_REASONER:
                    return(null);
                default:
                    OWLReasonerFactory reasonerFactory = new ElkReasonerFactory();
                    return(reasonerFactory.createReasoner(ontology));
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
        //args[2] Reasoners to use (ELK, HERMIT, STRUCTURAL_REASONER, SYNTACTIC_REASONER)
        //args[3] Visualization formatter (RDFXML, GRAPHVIZ[DEFAULT], OBOFLATFILE,GRAPHML)
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
        String formatDescription = "The different visualization formats available (RDFXML, GRAPHVIZ[DEFAULT], OBOFLATFILE,GRAPHML) (Optional).\n"+
                                    "In order to generate all formats available just provide : \"*\". \n";
        Option formatOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(formatDescription)
                .create(format);

        String equivalentClasses = "eq";
        String equivalentClassesDescription = "This is a boolean parameter, if it is false then an only one single node in the graph will be created for equivalent classes \n"+
                                                "but if it is true then for each equivalent class a different node will be created ";
        Option equivalentClassesOption = OptionBuilder
                .hasArg(true)
                .isRequired(true)
                .withDescription(equivalentClassesDescription)
                .create(equivalentClasses);

        String objectProperties = "op";
        String objectPropertiesDescription = "This parameter will contain the list of object properties labels that will be used to visualized the ontology (Optional). \n"+
                                        "The object properties should be formatted as array, here you can see an example: [\"first_label\",\"second_label\",\"third_label\"]. \n"+
                                        "In order to include all object properties from an ontology given just provide: [\"*\"]. \n";
        Option objectPropertiesOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(objectPropertiesDescription)
                .create(objectProperties);

        String transitive = "t";
        String transitiveDescription ="This parameter should contain a boolean (TRUE,FALSE[DEFAULT]) in order to check the transitivity of the object properties \n"+
                                                "-t\"==FALSE: Only the first version of the algorithm will be applied across all selected properties. \n"+
                                                "-t\"==TRUE: The second version of the algorithm will be employed over properties which are transitive, and the others will be \n"+
                                                "\t\tinferred by using the first version.";
        Option transitiveOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(transitiveDescription)
                .create(transitive);

        String nThreads = "nt";
        String nThreadsDescription = "This parameter represents the number of the threads that the application is going to use (4[Default]) (Optional).";
        Option nThreadsOption = OptionBuilder
                .hasArg(true)
                .isRequired(false)
                .withDescription(nThreadsDescription)
                .create(nThreads);

        Options options = new Options();
        options.addOption(ontoFileOption);
        options.addOption(outputOption);
        options.addOption(reasonerOption);
        options.addOption(formatOption);
        options.addOption(equivalentClassesOption);
        options.addOption(objectPropertiesOption);
        options.addOption(transitiveOption);
        options.addOption(nThreadsOption)

        return(options);
    }

    /**
     * It builds a info message that contain the description of the parameters that are used in the aplication.
     * @return The message that contains a little description of the all parameters that are needed to execute the application.
     */
    public static String getManual(){
        StringBuilder builder = new StringBuilder();
        builder.append("These are the parameters that should be provided: \n");
        builder.append("-ont This parameter should contain the ontology file or at least the path where the ontology is located. \n");
        builder.append("-out This parameter represents the output path where the files generated by the tool will be saved (Optional). \n");
        builder.append("-r The reasoner will be used (ELK[DEFAULT],HERMIT,STRUCTURAL_REASONER, SYNTACTIC_REASONER). \n");
        builder.append("-f The different visualization formats available (JSONLD, RDFXML, GRAPHVIZ[DEFAULT], OBOFLATFILE,GRAPHML) (Optional). \n");
        builder.append("\tIn order to generate all formats available just provide \"*\". \n");
        builder.append("-eq This parameter should contain a boolean (TRUE,FALSE[DEFAULT]) in order to include the equivalent classes to the graph or not");
        builder.append("-op This parameter will contain the list of object properties labels that will be used to visualized the ontology (Optional). \n");
        builder.append("-t This parameter should contain a boolean (TRUE,FALSE[DEFAULT]) in order to check the transitivity of the object properties");
        builder.append("The object properties should be formatted as array, here you can see an example: [\"first_label\",\"second_label\",\"third_label\"]. \n");
        builder.append("\tIn order to include all object properties from an ontology given just provide: [\"*\"]. \n");
        builder.append("-nt This parameter represents the number of the threads that the application is going to use (4[Default]) (Optional). \n");
        return(builder.toString());
    }

    /**
     * The main of the applications. Basically the aim of this method is to iniciate the parameters passed through command line and then the
     * command in charge of parsing the ontology will be executed.
     * @param args The list of arguments that will be passed through command line.
     */
    public static void main (String[] args){

        try{
            //we disable the logs.
            Logger.getRootLogger().setLevel(Level.OFF);
            OWLOntology ontology = null;
            OWLReasoner reasoner = null;
            List<OWLReasoner> reasoners = new ArrayList<OWLReasoner>();
            String outputPath = null;
            Set<FormatterType> setFormatterType = null;
            String[] properties = null;
            boolean equivalentClasses = false;
            int nThreads =0;
            boolean transitiveFlag = false;
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

            //First we need to know how many threads are going to be used, then we create an array of reasoners in order
            // to enable the paralelization of the subclasses precomputation.
            if(commandLine.hasOption("nt")){
                nThreads = Integer.parseInt(commandLine.getOptionValue("nt"));
            }else{
                nThreads = 4;
            }

            String sReasoner=null;
            if(commandLine.hasOption("r")){
                sReasoner = commandLine.getOptionValue("r");
                //reasoner = getReasoner(sReasoner, ontology);
            }else{
                sReasoner = "DEFAULT"
                //reasoner = getReasoner("DEFAULT",ontology);
            }

            //We create an array of reasoners which will exactly contain the double number of reasoners than configured threads
            for(int i=0;i<(nThreads*2);i++){
                reasoner = getReasoner(sReasoner,ontology);
                if(reasoner!=null){
                    reasoners.add(reasoner);
                }
            }

            if(commandLine.hasOption("f")){
                String sFormatterType = commandLine.getOptionValue("f");
                if((sFormatterType!=null)&&(!sFormatterType.isEmpty())){
                    setFormatterType = getVisualizationFormatter(sFormatterType);
                }
            }else{
                setFormatterType = getVisualizationFormatter("DEFAULT");
            }

            if(commandLine.hasOption("eq")){
                String sEquivalentClasses = commandLine.getOptionValue("eq");
                if((sEquivalentClasses!=null)&&(!sEquivalentClasses.isEmpty())){
                    if(sEquivalentClasses.toLowerCase().compareTo("true")==0){
                        equivalentClasses = true;
                    }
                }
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

            if(commandLine.hasOption("t")){
                String sTransitive = commandLine.getOptionValue("t");
                if((sTransitive!=null)&&(!sTransitive.isEmpty())){
                    if(sTransitive.toLowerCase().compareTo("true")==0){
                        transitiveFlag = true;
                    }
                }
            }

            if((ontology!=null)&&(outputPath!=null)&&(setFormatterType!=null)) {
                runGraphGenerator(reasoners,ontology, setFormatterType,outputPath,equivalentClasses, properties,transitiveFlag,nThreads);
            }else{
                if(ontology==null){
                    System.out.println("The ontology was not given");
                }
                if(outputPath==null){
                    System.out.println("The outpath was not given");
                }
                if(setFormatterType==null){
                    System.out.println("The format was not given")
                }
                System.out.println(getManual());
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error providing the parameters");
            System.out.println(getManual());
        }
    }
}
