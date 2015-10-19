package view

import edu.uci.ics.jung.graph.DirectedSparseGraph
import edu.uci.ics.jung.graph.Graph
import org.omg.CORBA.Request
import org.semanticweb.owlapi.model.OWLClass
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner
import tool.RequestManager;

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
            properties = null;
            Graph graph = this.buildGraph(ontology, reasoner, properties);
            this.serializeGraph(fileOutPath,graph);
        }
    }

    private HashMap buildNode(OWLClass rootClass){
        HashMap root = null;
        if(rootClass!=null) {
            root = new HashMap();
            root.put("owlClass",rootClass.getIRI().toString());
            root.put("classURI","<"+rootClass.getIRI().toString()+">");
            root.put("remainder",rootClass.getIRI().getFragment().toString());
            root.put("label","owl:Thing");
            root.put("deprecated",false)
        }
        return(root)
    }

    private Graph buildGraph(OWLOntology ontology,OWLReasoner reasoner,String[] properties){
        DirectedSparseGraph graph= new DirectedSparseGraph<>();
        Set<OWLClass> classes = ontology.getClassesInSignature(true);
        int classesIndex = 0;
        int classesCounter = ontology.getClassesInSignature(true).size();
        classes.remove(ontology.getOWLOntologyManager().getOWLDataFactory().getOWLNothing());
        Iterator<OWLClass> it = classes.iterator();
        OWLClass clazz;
        HashMap subClass;
        HashMap root;
        while(it.hasNext()){
            clazz = it.next();
            root = buildNode(clazz);
            classesIndex++;
            printProgressBar((int)Math.round((classesIndex*100)/(classesCounter)),"building the graph...");
            graph.addVertex(root);
            Set<HashMap> subClasses = RequestManager.getInstance().runQuery(root.get("classURI").toString(),ontology,reasoner,"subclass",true,false);
            Iterator<HashMap> jt = subClasses.iterator();
            while(jt.hasNext()){
                subClass = jt.next();
                graph.addVertex(subClass);
                graph.addEdge(graph.edgeCount+1,root,subClass);
            }
            if((properties!=null)&&(properties.length>0)){
                String objectProperty;
                for(int i=0;i<properties.length;i++){
                    objectProperty = properties[i];
                    Set<HashMap> result = RequestManager.getInstance().relationQuery(objectProperty,root.get("classURI").toString(),ontology);
                    Iterator<HashMap> kt = result.iterator();
                    HashMap objectPropertyClass;
                    while(kt.hasNext()){
                        objectPropertyClass = kt.next();
                        graph.addVertex(objectPropertyClass);
                        graph.addEdge(objectProperty,root,objectPropertyClass);
                    }
                }
            }
        }
        return(graph);
    }

    private static void printProgressBar(int percent,String message){
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

    private void serializeGraph(String fileOutPath,Graph graph){
        if((fileOutPath!=null)&&(!fileOutPath.isEmpty())){
            try {
                System.out.println("Edges: "+graph.getEdgeCount());
                System.out.println("Vertex: "+graph.getVertexCount());
                File file = new File(fileOutPath+getExtension());
                output = new BufferedWriter(new FileWriter(file));
                if(output!=null){
                    if(graph!=null){
                        Iterator it = graph.getVertices().iterator();
                        HashMap vertice;
                        while(it.hasNext()){
                            vertice = it.next();
                            Iterator jt = graph.getOutEdges(vertice);
                            Object edge;
                            while(jt.hasNext()){
                                edge = jt.next();
                                System.out.println(vertice+"-->"+edge);
                            }
                        }
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

    public abstract String getHeader();
    public abstract String getFooter();

    public abstract String formatter(HashMap rootClass,Set<HashMap> subClasses)

    public abstract String formatter(HashMap rootClass,Set<HashMap> subClasses, String objectProperty)

    public abstract String getExtension();

}
