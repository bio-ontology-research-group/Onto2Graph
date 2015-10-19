package view

import org.semanticweb.owlapi.model.OWLClass;

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

    public String formatter(HashMap rootClass,Set<HashMap> subClasses) {
        String content="";
        content = "<" + rootClass.get("label") + "> [label=<\""+ rootClass.get("label") +"\">, shape=\"circle\" style=\"filled\" color=\""+color+"\"];\n";
        Iterator<HashMap> it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+= "<" +subClass.get("label")+ "> [label=<\"" + subClass.get("label") + "\">, shape=\"circle\" style=\"filled\" color=\""+color+"\"];\n";
                content+=" <" + rootClass.get("label") + "> -> <" + subClass.get("label") + ">;\n ";
            }
        }
        return(content);
    }

    public String formatter(HashMap rootClass,Set<HashMap> subClasses, String objectProperty){
        String content = "";
        content = "<" + rootClass.get("label") + "> [label=<" + rootClass.get("label") + ">, shape=\"circle\" style=\"filled\" color=\"" + color+"\"];\n";
        Iterator<HashMap> it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+= "<" + rootClass.get("label") + "> [label=<\"" + subClass.get("label") + "\">, shape=\"circle\" style=\"filled\" color=\""+propertyColor+"\"];\n";
            }
        }
        return(content);
    }

    public String getExtension(){
        return(".dot");
    }
}
