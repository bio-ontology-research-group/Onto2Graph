import slib.graph.io.loader.rdf.RDFLoader
import slib.utils.impl.SetUtils
import sun.security.provider.certpath.Vertex

import java.net.URI;
import org.openrdf.model.vocabulary.*
import slib.sml.sm.core.metrics.ic.utils.*
import slib.sml.sm.core.utils.*
import org.openrdf.model.URI
import slib.graph.algo.extraction.rvf.instances.*
import slib.graph.algo.extraction.utils.*
import slib.graph.model.graph.*
import slib.graph.model.repo.*
import slib.graph.model.impl.graph.memory.*
import slib.sml.sm.core.engine.*
import slib.graph.io.conf.*
import slib.graph.model.impl.graph.elements.*
import slib.graph.algo.extraction.rvf.instances.impl.*
import slib.graph.model.impl.repo.*
import slib.graph.io.util.*
import groovyx.gpars.GParsPool

import java.util.concurrent.atomic.AtomicInteger

def annotations = [];
def proteins = [];
def interactions;
def interactionsCounter =0;
def annotationsCounter =0;
def rocCoordinates;

/**
 * The script performs semantic similarity analysis by using Gene Annotation
 * and BioGRID annotations taking into account each organism (fly, worm, fish, yeast and mouse)
 * and Genetic and Physical interaction independently.
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */


def getManual = {
    String info="These are the parameters that should be provided: \n";
    info+="-ont This parameter should contain the ontology that will ben analysed. \n";
    info+="-ann The annotation ontology that ill be used. \n";
    info+="-i The interaction file that ill be used. \n";
    info+="-td The type of the datasource that will be use (GA, BG)"
    info+="-ti The type of the interaction that will be analysed (IGI, IPI, physical, genetic) \n";
    info+="-out The path of the output file where the statistics, matrix and ROC coordinates will be stored. \n";
    info+="-smconf The different statistics available (SIM_GROUPWISE_DAG_GIC[DEFAULT],SIM_GROUPWISE_BMA) (Optional). \n";
    return(info);
}

def loadOntologyFile = {ontologyPath ->
    File file = null;
    if((ontologyPath!=null)&&(!ontologyPath.isEmpty())){
        file = new File(ontologyPath);
        if(!file.exists()){//To control that the file exists.
            file = null;
        }
    }
    return(file);
}

def printProgressBar = {int percent,String message ->
    StringBuilder bar = new StringBuilder("[");

    for(int i = 0; i < 50; i++){
        if( i < (percent/2)){
            bar.append("=");
        }else if( i == ((int)percent/2)){
            bar.append(">");
        }else{
            bar.append(" ");
        }
    }

    bar.append("]   " + percent + "%\t"+message);
    System.out.print("\r" + bar.toString());
}

def serializeSimilarityMatrix = {String outPath,Set<URI> instances,Protein[][] similarityMatrix ->
    BufferedWriter output = null;
    try {
        System.out.println("Serialising the similarity matrix... ")
        fOutput = new File(outPath+"_sim_matrix.txt");
        output = new BufferedWriter(new FileWriter(fOutput));
        String row ="\t\t";
        for(int i = 0; i < instances.size();i++) {
            row += instances.getAt(i).getLocalName()+"\t\t";
        }
        output.append(row+"\n");
        for(int i = 0; i < similarityMatrix.length;i++){
            row=instances.getAt(i).getLocalName();
            printProgressBar((int) ((i * 100) / similarityMatrix.length), "Serialising the similarity matrix...");
            for(int j = 0 ; j<similarityMatrix.length;j++){
                row += "\t\t"+similarityMatrix[i][j].getSimilarity();
            }
            output.append(row+"\n");
        }
    }catch(Exception e){
        System.out.println("There was an error: "+e.getMessage());
        e.printStackTrace();
    } finally {
        if ( output != null ) {
            output.close();
        }
    }
}

def getSimGroupWiseConfiguration = { sSmConf ->
    switch (sSmConf){
        case "SIM_GROUPWISE_DAG_GIC":
            SMconf smConf = new SMconf("SimGIC", SMConstants.FLAG_SIM_GROUPWISE_DAG_GIC);
            ICconf icConf = new IC_Conf_Corpus("Resnik", SMConstants.FLAG_IC_ANNOT_RESNIK_1995_NORMALIZED);
            smConf.setICconf(icConf);
            return(smConf);//Just for testing
        case "SIM_GROUPWISE_BMA":
            SMconf smConf = new SMconf("BMA",SMConstants.FLAG_SIM_GROUPWISE_BMA);
            return(smConf);
        default:
            SMconf smConf = new SMconf("SimGIC", SMConstants.FLAG_SIM_GROUPWISE_DAG_GIC);
            ICconf icConf = new IC_Conf_Corpus("Resnik", SMConstants.FLAG_IC_ANNOT_RESNIK_1995_NORMALIZED);
            smConf.setICconf(icConf);
            return(smConf);
    }
    return(null)
}

def getSimPairWiseConfiguration = { sSmConf ->
    switch(sSmConf){
        case "SIM_GROUPWISE_BMA":
            SMconf smConfPairWise = new SMconf("SimGIC",SMConstants.FLAG_SIM_PAIRWISE_DAG_NODE_RESNIK_1995);
            ICconf icConf =  new IC_Conf_Corpus("Resnik", SMConstants.FLAG_IC_ANNOT_RESNIK_1995_NORMALIZED);
            smConfPairWise.setICconf(icConf);
            return(smConfPairWise)
    }
    return(null);
}

def printSimilarityMatrix = {Set<URI> instances,double[][] similarityMatrix ->
    if((instances!=null)&&(similarityMatrix!=null)) {
        for (int i = 0; i < instances.size(); i++) {
            System.out.print(instances.getAt(i));
            for (int j = 0; j < instances.size(); j++) {
                System.out.println("\t" + similarityMatrix[i][j]);
            }
            System.out.println();
        }
    }
}

def parseAnnotationFile = { fAnnotations,proteinColumn ->
    if((fAnnotations!=null)) {
        try {
            fAnnotations.splitEachLine("\t") { line ->
                if (!line[0].startsWith("!")) {
                    String protein = line[proteinColumn];
                    String goClass = line[4];
                    if ((protein != null) && (!protein.isEmpty()) && ((goClass != null) && (!goClass.isEmpty()))) {
                        goClass = goClass.replace("GO:", "GO_");
                        goClass = goClass.trim().toUpperCase();
                        protein = protein.trim().toUpperCase();
                        if(proteins.contains(protein)) {
                            annotations.add(new Expando(protein: protein, goClass: goClass));
                        }
                    }
                }
            }
            System.out.println("Number of annotations: "+annotations.size());
        } catch (Exception e) {
            System.out.println("There was an error loading the annotation file");
            e.printStackTrace();
        }
    }
}

def parseGeneAssociationInteractionFile = { fInteractions, interactionType ->
    if((fInteractions!=null)&&(interactionType!=null)){
        interactions = [:].withDefault { new TreeSet() }
        fInteractions.splitEachLine("\t") { line ->
            if ((line!=null)&&(!line[0].startsWith("!"))) {
                if ((line[6] != null) && (line[6].toString().trim().toUpperCase().compareTo(interactionType) == 0)) {
                    String id = line[1].trim().toUpperCase();
                    line[7].split("\\|").each { String name ->
                        if (name.indexOf(":") > 0) {
                            name = name.substring(name.indexOf(":") + 1, name.length());
                        }
                        interactions[id].add(name.trim().toUpperCase());
                    }
                    interactionsCounter++;
                    proteins.add(id);
                }
            }
        }
        System.out.println("Number of interactions: "+interactionsCounter);
        System.out.println("Number of proteins: "+proteins.size());
    }
}

def parseBioGRIDInteractionFile = { fInteractions,interactionType ->
    if ((fInteractions != null)&&(interactionType!=null)){
        interactions = [:].withDefault { new TreeSet() }
        fInteractions.splitEachLine("\t") { line ->
            if ((line!=null)&&(!line[0].startsWith("#"))) {
                if((line[12]!=null)&&(line[12].trim().toUpperCase().compareTo(interactionType)==0)) {
                    String id = line[7].trim().toUpperCase();//Official Symbol Interactor A
                    interactions[id].add(line[8].trim().toUpperCase());//Official Symbol Interactor B
                    interactions[id].add(line[6].trim().toUpperCase());//Systematic Name Interactor B
                    line[10].split("\\|").each { String name ->
                        interactions[id].add(name.trim().toUpperCase());//Synonyms Interactor B
                    }
                    interactionsCounter++;
                    proteins.add(line[7].trim().toUpperCase());
                    proteins.add(line[8].trim().toUpperCase());
                }
            }
        }
        System.out.println("Number of interactions: "+interactionsCounter);
        System.out.println("Number of proteins: "+proteins.size());
    }
}

def parseFiles = {File fAnnotations, File fInteraction,String dataSourceType, String interactionType->
    if(dataSourceType!=null){
        switch (dataSourceType) {
            case "GA"://Gene Association
                parseGeneAssociationInteractionFile(fInteraction, interactionType);
                parseAnnotationFile(fAnnotations,1);//In this case we use the DB Object ID for relate the proteins with theirs interactions.
                return;
            case "BG"://BioGRID
                parseBioGRIDInteractionFile(fInteraction, interactionType);
                parseAnnotationFile(fAnnotations,2);//In this case we use the DB Object Symbol for relating the proteins with theirs interactions.
                return;
        }
    }
}

def calculateROCCoordinates = {String outPath, List results ->
    if(results!=null){
        BufferedWriter output = null;
        try {
            File fileOutput = new File(outPath+"_roc.txt");
            output = new BufferedWriter(new FileWriter(fileOutput));
            rocCoordinates = new ArrayList<ROCCoordinate>();
            System.out.println("Computing ROC coordinates...");
            output.append("True Positive Rate\t False Positive Rate\n");
            output.println("0\t0")
            rocCoordinates.add(new ROCCoordinate(0,0));
            for (int i = 0; i < results.size(); i++) {
                if(results[i]!=null){
                    def fp = (double)results[i];
                    def tp = (double)(i / results.size());
                    rocCoordinates.add(new ROCCoordinate(tp, fp));
                    output.println("$tp\t$fp")
                }
            }
            rocCoordinates.add(new ROCCoordinate(1.00,1.00));
            output.println("1\t1")
        } catch ( IOException e ) {
            System.out.println("There was an error: "+e.getMessage());
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
    }
}

def calculateAUC = {String outPath,int truePositives, int falsePositives ->
    if(rocCoordinates!=null) {
        System.out.println("Computing AUC...");
        def a = 0
        def b = 0
        def fa = 0
        def fb = 0
        def sum = 0
        rocCoordinates.each { ROCCoordinate coordinate ->
            //def vala = coordinate.getTruePositive();
            //def valb = coordinate.getFalsePositive();
            def vala = coordinate.getFalsePositive();
            def valb = coordinate.getTruePositive();
            a = b
            fa = fb
            b = vala
            fb = valb
            sum += (b - a) * (fa + fb) / 2;
        }
        System.out.println("Printing the results ...");
        BufferedWriter output;
        try {
            File fileOutput = new File(outPath + "_auc.txt");
            output = new BufferedWriter(new FileWriter(fileOutput));
            output.println("$outPath--> AUC:\t" + sum + "\n");
            output.println("True Positives: "+truePositives);
            output.println("False positives: "+falsePositives);
            output.println("Number of annotations: "+annotationsCounter);
            output.println("Number of interactions: "+interactionsCounter);

        } catch (IOException e) {
            System.out.println("There was an error: " + e.getMessage());
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}

def cli = new CliBuilder()
cli.with {
    usage: 'Self'
    h longOpt:'help', 'Options: -ont (The ontology) -ann (The ontology annotation) -out (output File) -smconf (the statistic to use)'
    ont longOpt:'ont', 'Ontology', args:1, required:true
    ann longOpt: 'ann', 'The annotation ontology that ill be used', args:1, required:true
    i longOpt: 'i', 'The interaction file that ill be used', args:1, required:true
    td longOpt:'td', 'Type of the datasource. (GA, BG)', args:1, required:true
    ti longOpt:'ti', 'Type of the interaction. (ICI, IGI)', args:1, required:true
    out longOpt:'out', 'output file',args:1, required:false
    smconf longOpt:'smconf', 'The configuration of a semantic measure. (SIM_GROUPWISE_DAG_GIC[DEFAULT],SIM_GROUPWISE_BMA)',args:1, required:false

}
def opt = cli.parse(args)
if( !opt ) {
    getManual();
    return
}
if( opt.h ) {
    cli.usage()
    return
}

File fOnto = null;
File fAnnotations = null;
File fInteractions = null;
String outPath = null;
String sSmConf = null;
String dataSourceType = null;
String interactionType = null;
if (opt.ont) {
    System.out.println("Loading the ontologies");
    fOnto = loadOntologyFile(opt.ont);
}
if(opt.ann){
    System.out.println("Loading the annotation ontology");
    fAnnotations = loadOntologyFile(opt.ann);
}
if(opt.i){
    System.out.println("Loading the interaction ontology");
    fInteractions = loadOntologyFile(opt.i);
}
if(opt.td){
    dataSourceType = opt.td.toString().trim().toUpperCase();
}
if(opt.ti){
    interactionType = opt.ti.toString().trim().toUpperCase();
}
if(opt.out){
    outPath = opt.out;
}else{
    outPath = System.getProperty("user.dir")+System.getProperty("file.separator");
}
if(opt.smconf){
    sSmConf = opt.smconf;
}
if((fOnto!=null)&&(fAnnotations!=null)&&(fInteractions!=null)&&(dataSourceType!=null)&&(interactionType!=null)&&(sSmConf!=null)){

    //0. Parse the annotation and the interaction file.
    parseFiles(fAnnotations,fInteractions,dataSourceType,interactionType);

    if((annotations!=null)&&(interactions!=null)){

        //1. Create the rdf graph
        String sUri = "http://phenomebrowser.net/smltest/"
        URIFactory factory = URIFactoryMemory.getSingleton();
        URI graphURI = factory.getURI(sUri);
        factory.loadNamespacePrefix("GO", graphURI.toString());
        G graph = new GraphMemory(graphURI);


        //2. Load RDF graph

        GDataConf goConf = new GDataConf(GFormat.RDF_XML, fOnto.getPath());
        RDFLoader rdfLoader = new RDFLoader();
        rdfLoader.populate(goConf, graph);

        graph.removeE(RDF.TYPE)

        //4. Enrich RDF graph
        annotationsCounter =0;
        annotations.each { expando ->
            def protein = expando.protein;
            def goClass = expando.goClass;
            URI proteinUri = factory.getURI(sUri + protein);
            URI goUri = factory.getURI("http://purl.obolibrary.org/obo/" + goClass);
            if((graph.containsVertex(goUri))) {
                Edge interaction = new Edge(proteinUri, RDF.TYPE, goUri);
                graph.addE(interaction);
                annotationsCounter++;
            }
        }

        //3. Add virtual root.
        URI virtualRoot = factory.getURI("http://phenomebrowser.net/smltest/virtualRoot");
        graph.addV(virtualRoot);
        GAction rooting = new GAction(GActionType.REROOTING);
        rooting.addParameter("root_uri", virtualRoot.stringValue());
        GraphActionExecutor.applyAction(factory, rooting, graph);


        def smConfGroupWise = getSimGroupWiseConfiguration(sSmConf);
        def smConfPairWise = getSimPairWiseConfiguration(sSmConf);
        boolean pairWiseFlag = false;
        if(smConfPairWise!=null){
            pairWiseFlag = true;
        }

        SM_Engine engine = new SM_Engine(graph)

        InstancesAccessor ia = new InstanceAccessor_RDF_TYPE(graph)

        int proteinsIndex = 0;
        int proteinsCounter = engine.getInstances().size();

        List results = [];

        System.out.println("Number of proteins: " + proteinsCounter);
        AtomicInteger truePositives = new AtomicInteger(0);
        AtomicInteger falsePositives = new AtomicInteger(0);

        GParsPool.withPool {
            engine.getInstances().eachWithIndexParallel { protein1, posX ->
            //engine.getInstances().eachWithIndex{ protein1, posX ->
                //println "Processing $protein1..."
                printProgressBar((int) ((proteinsIndex * 100) / proteinsCounter), "Building similarity matrix...");
                proteinsIndex++;
                Set set1 = ia.getDirectClass(protein1)
                List<Protein> l = []
                engine.getInstances().eachWithIndex { protein2, posY ->
                    Set set2 = ia.getDirectClass(protein2)
                    if ((!set1.isEmpty()) && (!set2.isEmpty())) {
                        def sim = 0.0;
                        if(pairWiseFlag){
                            sim = engine.compare(smConfGroupWise, smConfPairWise, set1, set2);
                        }else {
                            sim = engine.compare(smConfGroupWise, set1, set2);
                        }
                        Expando exp = new Expando()
                        exp.sim = sim
                        exp.id = protein2.getLocalName().toUpperCase();
                        l << exp
                    }
                }
                l = l.sort { it.sim }.reverse()
                for (int i = 0 ; i < l.size() ; i++) {
                    def exp = l[i]
                    if (exp.id in interactions[protein1.getLocalName().toUpperCase()]) {
                        synchronized (results) {
                            results.add(i / l.size())
                            truePositives.getAndIncrement();
                        }
                    }else{
                        falsePositives.getAndIncrement();
                    }
                }
            }
            printProgressBar(100, "Building similarity matrix...\n");
        }
        results = results.sort();
        calculateROCCoordinates(outPath,results);
        calculateAUC(outPath,truePositives.get(),falsePositives.get());
    }
}

