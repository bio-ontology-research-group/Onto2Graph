package view


/**
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in SVG format.
 *
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class GraphVizFormatter extends ViewFormat{
    protected String color="#BDBDBD";
    protected String propertyColor = "#5FB404";

    public GraphVizFormatter(){
        super();
    }

    public String getHeader(){
        return("digraph{\n");
    }

    public String getFooter(){
        return("}");
    }

    public String formatter(HashMap rootClass,HashMap subClass) {
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<" + rootLabel + "> [label=<\""+ rootLabel +"\">, shape=\"circle\" style=\"filled\" color=\""+color+"\"];\n";
            content+= "<" + classLabel + "> [label=<\"" + classLabel + "\">, shape=\"circle\" style=\"filled\" color=\""+color+"\"];\n";
            content+=" <" + rootLabel + "> -> <" + classLabel + ">;\n ";
        }
        return(content);
    }

    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content = "";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<" + rootLabel + "> [label=<\"" + rootLabel + "\">, shape=\"circle\" style=\"filled\" color=\"" + color+"\"];\n";
            content+= "<" + classLabel + "> [label=<\"" + classLabel + "\">, shape=\"circle\" style=\"filled\" color=\""+propertyColor+"\"];\n";
        }
        return(content);
    }

    public String getExtension(){
        return(".dot");
    }
}
