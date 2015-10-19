package view

import org.semanticweb.owlapi.model.OWLClass;

/**
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in Flatfile format.
 *
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class GraphMLFormatter extends ViewFormat {
    private int edgeCounter = 0;
    private String nodeColor ="blue";

    public GraphMLFormatter(){
        super();
    }

    public String getHeader(){
        return"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"  \n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n" +
                "     http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n"+
                "<graph id=\"G\" edgedefault=\"undirected\">\n";
    }
    public String getFooter(){
        return("</graph>\n" +
                "</graphml>");
    }

    public String formatter(HashMap rootClass,Set<HashMap> subClasses) {
        String content="";
        content = "<node id=\""+rootClass.get("label")+"\"/>\n";
        content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
        Iterator<HashMap>it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+="<node id=\""+subClass.get("label")+"\"/>\n";
                content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
                content+="<edge id=\"e"+(edgeCounter+1)+"\" source=\""+rootClass.get("label")+"\" target=\""+subClass.get("label")+"\"/>\n";
            }
        }
        return content;
    }


    public String formatter(HashMap rootClass,Set<HashMap> subClasses, String objectProperty){
        String content="";
        content = "<node id=\""+rootClass.get("label")+"\"/>\n";
        content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
        Iterator<HashMap>it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+="<node id=\""+subClass.get("label")+"\"/>\n";
                content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
                content+="<edge id=\"e"+(edgeCounter+1)+"\" source=\""+rootClass.get("label")+"\" target=\""+subClass.get("label")+"\"/>\n";
                content+="\t<data key=\"d1\">"+objectProperty+"</data>\n";
            }
        }
        return(content);
    }

    public String getExtension(){
        return(".graphml");
    }
}