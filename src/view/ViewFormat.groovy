package view

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
    protected int classesIndex=0;
    protected int classesCounter = 0;
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
        if((fileOutPath!=null)&&(!fileOutPath.isEmpty())){
            try {
                classesIndex = 0;
                classesCounter = ontology.getClassesInSignature(true).size();
                HashMap rootClass = buildRoot(ontology.getOWLOntologyManager().getOWLDataFactory().getOWLThing());
                File file = new File(fileOutPath+getExtension());
                output = new BufferedWriter(new FileWriter(file));
                if(output!=null){
                    System.out.println(classesCounter);
                    output.append(this.getHeader());
                    /*if ((properties != null) && (properties.length != 0)){
                        this.recursiveQueryProperties(ontology,reasoner,rootClass,properties);
                    }else{*/
                        this.recursiveQuery(ontology, reasoner,rootClass,0);
                    //}
                    output.append(this.getFooter());
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

    private HashMap buildRoot(OWLClass rootClass){
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

    private void recursiveQuery(OWLOntology ontology,OWLReasoner reasoner,HashMap root,int level){
        Set<HashMap> subClasses = RequestManager.getInstance().runQuery(root.get("classURI").toString(),ontology,reasoner,"subclass",true,false);
        if((subClasses.isEmpty())||(level>1)){
            return;
        }
        output.append(formatter(root,subClasses));
        HashMap child;
        for(int i=0;i<subClasses.size();i++){
            child = subClasses.getAt(i);
            if(child!=null){
                classesIndex++;
                recursiveQuery(ontology,reasoner,child,level+1);
                System.out.print("\r"+"Number of classes transformed:"+classesIndex);
            }
        }
    }

    private void recursiveQueryProperties(OWLOntology ontology,OWLReasoner reasoner, OWLClass rootClass,String[] objectProperties){
        String objectProperty;
        Set<OWLClass>subClasses = new HashSet<OWLClass>();
        Set<OWLClass>result = null;
        for(int i=0;i<objectProperties.length;i++){
            objectProperty = objectProperties[i];
            result = RequestManager.getInstance().relationQuery(objectProperty,rootClass.getIRI().toString(),ontology);
            if((result!=null)&&(!result.isEmpty())){
                output.append(formatter(rootClass,result,objectProperty))
                subClasses.addAll(result);
            }
        }
        if((subClasses==null)||(subClasses.isEmpty())){
            return;
        }

        classesIndex++;
        printProgressBar((int)Math.round((classesIndex*100)/(ontology.getClassesInSignature(true).size())));


        Iterator<OWLClass> it = subClasses.iterator();
        OWLClass child;
        while(it.hasNext()){
            child = it.next();
            if(child!=null){
                recursiveQueryProperties(ontology,reasoner,child,objectProperties);
            }
        }
        return;
    }

    private static void printProgressBar(int percent){
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

        bar.append("]   " + percent + "%     ");
        System.out.print("\r" + bar.toString());
    }

    public abstract String getHeader();
    public abstract String getFooter();

    public abstract String formatter(HashMap rootClass,Set<HashMap> subClasses)

    public abstract String formatter(HashMap rootClass,Set<HashMap> subClasses, String objectProperty)

    public abstract String getExtension();

}
