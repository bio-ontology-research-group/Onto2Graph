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

/**
 * Main class
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */


public class GraphicGeneratorTool {
    public static final String ELK_REASONER="ELK";
    public static final String HERMIT_REASONER="HERMIT";
    public static final String STRUCTURAL_REASONER="STRUCTURAL_REASONER";

    /**
     * Constructor
     */
    public GraphicGeneratorTool(){

    }

    public static void runGraphGenerator(OWLReasoner reasoner, OWLOntology ontology, Set<FormatterType> setFormatterType,
                                         String outputPath,String[] objectProperties){
        VisualizerCommand command = new VisualizerCommand(reasoner,ontology,setFormatterType,outputPath,objectProperties);
        command.executeCommand();
    }

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

    public static Set<FormatterType> getVisualizationFormatter(String typeFormatter){
        if((typeFormatter!=null)&&(!typeFormatter.isEmpty())){
            typeFormatter = typeFormatter.trim().toUpperCase();
            Set<FormatterType> setFormatterType = new HashSet<FormatterType>();
            switch(typeFormatter){
                case "RDFXML": return(setFormatterType.add(FormatterType.RDFXML_FORMATTER));
                case "GRAPHVIZ": return(setFormatterType.add(FormatterType.GRAPHVIZ_FORMATTER));
                case "FLATFILE": return(setFormatterType.add(FormatterType.FLATFILE_FORMATTER));
                case "GRAPHML": return(setFormatterType.add(FormatterType.GRAPHML_FORMATTER));
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
