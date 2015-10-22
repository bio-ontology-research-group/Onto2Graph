package view

import edu.uci.ics.jung.graph.DirectedSparseGraph
import edu.uci.ics.jung.graph.Graph
import edu.uci.ics.jung.graph.util.Pair
import groovyx.gpars.GParsPool
import org.semanticweb.owlapi.model.OWLAnnotation
import org.semanticweb.owlapi.model.OWLClass
import org.semanticweb.owlapi.model.OWLLiteral
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.search.EntitySearcher
import show.ProgressBar
import tool.RequestManager

/**
 * Interface ViewFormat that should be implemented for all formatters
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public abstract class ViewFormat {
    protected BufferedWriter output=null;

    public ViewFormat(){
    }
    /**
     * String mOwlQuery
     * String type
     * String ontUri
     * @return
     */
    public void parseOntology(OWLOntology ontology,OWLReasoner reasoner, String[] properties,String fileOutPath) {
        if((ontology!=null)&&(reasoner!=null)) {
            properties = checkObjectProperties(ontology,properties);
            RequestManager.getInstance().computedSubClases(ontology,reasoner,properties);
            Graph graph = this.buildGraph(ontology, reasoner, properties);
            this.serializeGraph(fileOutPath,graph);
        }
    }

    protected String[] checkObjectProperties(ontology,String[] properties){
        if((properties.length==1)&&(properties[0]=="*")){
            HashSet<String> objectProperties = RequestManager.getInstance().getObjectProperties(ontology);
            properties = objectProperties.toArray(new String[objectProperties.size()]);
        }
        return(properties);
    }

    protected HashMap buildNode(OWLClass rootClass,OWLOntology ontology) {
        def root = null;
        if (rootClass != null) {
            root = [
                "owlClass" : rootClass.toString(),
                "classURI" : rootClass.getIRI().toString(),
                "ontologyURI": ontology.getOntologyID().getOntologyIRI().toString(),
                "remainder": rootClass.getIRI().getFragment(),
                "label"    : null,
                "definition": null,
                "deprecated": false
            ];

            for (OWLAnnotation annotation : EntitySearcher.getAnnotations(rootClass, ontology)) {
                if (annotation.isDeprecatedIRIAnnotation()) {
                    root['deprecated'] = true;
                }
                if (annotation.getProperty().isLabel()) {
                    OWLLiteral val = (OWLLiteral) annotation.getValue();
                    root['label'] = val.getLiteral();
                }
            }
            if (root['deprecated']) {
                root = [];
            }
        }
        return root;
    }

    protected Graph buildGraph(OWLOntology ontology,OWLReasoner reasoner,String[] properties) {
        DirectedSparseGraph graph = new DirectedSparseGraph<>();
        Set<OWLClass> classes = ontology.getClassesInSignature(true);
        int classesIndex = 0;
        int classesCounter = ontology.getClassesInSignature(true).size();
        classes.remove(ontology.getOWLOntologyManager().getOWLDataFactory().getOWLNothing());
        Iterator<OWLClass> it = classes.iterator();
        //HashMap subClass;
        GParsPool.withPool {
            classes.eachParallel { clazz ->
                ProgressBar.getInstance().printProgressBar((int) Math.round((classesIndex * 100) / (classesCounter)), "building the graph...");
                classesIndex++;
                HashMap root = new HashMap(buildNode(clazz,ontology))
                graph.addVertex(root);
                if((root!=null)&&(!root.isEmpty())) {
                    Set<HashMap> subClasses = RequestManager.getInstance().subClassesQuery(clazz, ontology, reasoner, true);
                    if(subClasses!=null){
                        subClasses.each { subClass ->
                            graph.addVertex(subClass);
                            graph.addEdge(root.get("classURI")+subClass.get("classURI"), root, subClass);
                        }
                    }
                    if ((properties != null) && (properties.length > 0)) {
                        String objectProperty;
                        for (int i = 0; i < properties.length; i++) {
                            objectProperty = properties[i];
                            if (objectProperty != null) {
                                Set<HashMap> result = RequestManager.getInstance().relationQuery(objectProperty, root.get("classURI").toString(), ontology, reasoner);
                                if(result!=null){
                                    result.each { objectPropertyClass ->
                                        graph.addVertex(objectPropertyClass);
                                        graph.addEdge(root.get("classURI")+"_"+objectPropertyClass, root, objectPropertyClass);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ProgressBar.getInstance().printProgressBar(100, "building the graph...");
        return(graph);
    }

    protected void serializeGraph(String fileOutPath,Graph graph){
        if((graph!=null)&&(fileOutPath!=null)&&(!fileOutPath.isEmpty())){
            try {
                int vertexCounter = graph.getVertexCount();
                int vertexIndex = 0;
                File file = new File(fileOutPath+getExtension());
                output = new BufferedWriter(new FileWriter(file));
                if(output!=null){
                    if(graph!=null){
                        output.append(this.getHeader());
                        Iterator it = graph.getVertices().iterator();
                        HashMap vertex;
                        while(it.hasNext()){
                            vertex = (HashMap)it.next();
                            Iterator jt = graph.getOutEdges(vertex).iterator();
                            Object edge;
                            vertexIndex++;
                            ProgressBar.getInstance().printProgressBar((int)Math.round((vertexIndex*100)/(vertexCounter)),"serializing the graph...")
                            while(jt.hasNext()){
                                edge = jt.next();
                                Pair pair = graph.getEndpoints(edge);
                                if(pair!=null) {
                                    if (edge.toString().indexOf("_") > 0) {//It does mean the edge is not a object property
                                        if (pair.getFirst().hashCode() != vertex.hashCode()) {
                                            output.append(formatter(vertex, pair.getFirst()));
                                        } else {
                                            output.append(formatter(vertex, pair.getSecond()));
                                        }
                                    } else {
                                        String[] objectProperty = edge.toString().split("_");
                                        if (objectProperty.length > 0) {
                                            if (pair.getFirst().hashCode() != vertex.hashCode()) {
                                                output.append(formatter(vertex, pair.getFirst(), objectProperty[0]));
                                            } else {
                                                output.append(formatter(vertex, pair.getSecond(), objectProperty[0]));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        output.append(this.getFooter());
                        ProgressBar.getInstance().printProgressBar(100,"serializing the graph...");
                        System.out.println();
                    }
                }

            } catch ( IOException e ) {
                System.out.println("There was an error: "+e.getMessage());
            } finally {
                if ( output != null ) {
                    output.close();
                }
            }
        }else{
            System.out.println("The file was not created");
            System.exit(-1);
        }
    }

    protected String filterLabel(String label){
        if(label!=null) {
            label = label.replace("<", "");
            label = label.replace(">", "");
        }
        return(label);
    }

    public abstract String getHeader();
    public abstract String getFooter();

    public abstract String formatter(HashMap rootClass,HashMap subClass)

    public abstract String formatter(HashMap rootClass,HashMap subClass, String objectProperty)

    public abstract String getExtension();

}
