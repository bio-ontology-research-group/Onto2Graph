package view

import org.semanticweb.owlapi.model.OWLClass;

/**
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in Flatfile format.
 *
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class FlatFileFormatter extends ViewFormat {

    public FlatFileFormatter(){
        super();
    }

    public String getHeader(){
        return("\tNodes\t\tEdges+\n");
    }

    public String getFooter(){
        return("");
    }

    String formatter(HashMap rootClass,Set<HashMap> subClasses) {
        String content= "";
        Iterator<HashMap> it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+="\t"+rootClass.get("label")+","+subClass.get("label")+"\t\t\n";
            }
        }
        return (content);
    }

    public String formatter(HashMap rootClass,Set<HashMap> subClasses, String objectProperty){
        String content = "";
        Iterator<HashMap> it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+="\t"+rootClass.get("label")+","+subClass.get("label")+"\t\t"+objectProperty+"\n";
            }
        }
        return(content);
    }

    public String getExtension(){
        return(".flatfile");
    }
}