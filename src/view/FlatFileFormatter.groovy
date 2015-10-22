package view
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
        return("\tNodes\t\t\t\tEdges+\n");
    }

    public String getFooter(){
        return("");
    }

    String formatter(HashMap rootClass,HashMap subClass) {
        String content= "";
        if((rootClass!=null)&&(subClass!=null)){
            content+="\t"+rootClass.get("remainder")+",\t"+subClass.get("remainder")+"\n";
        }
        return (content);
    }

    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content = "";
        if((rootClass)&&(subClass!=null)){
            content+="\t"+rootClass.get("remainder")+",\t"+subClass.get("remainder")+"\t\t"+objectProperty+"\n";
        }
        return(content);
    }

    public String getExtension(){
        return(".flatfile");
    }
}