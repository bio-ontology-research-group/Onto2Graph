package view


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

    public String formatter(HashMap rootClass,HashMap subClass) {
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<node id=\""+rootLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<node id=\""+classLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<edge id=\"e"+(edgeCounter+1)+"\" source=\""+rootLabel+"\" target=\""+classLabel+"\"/>\n";
        }
        return content;
    }


    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<node id=\""+rootLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<node id=\""+classLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<edge id=\"e"+(edgeCounter+1)+"\" source=\""+rootLabel+"\" target=\""+classLabel+"\"/>\n";
            content+="\t<data key=\"d1\">"+objectProperty+"</data>\n";
        }
        return(content);
    }


    public String getExtension(){
        return(".graphml");
    }
}