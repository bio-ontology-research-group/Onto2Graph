package view

/*
 * Copyright 2014 Miguel Ángel Rodríguez-García (miguel.rodriguezgarcia@kaust.edu.sa).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in GraphML format.
 *
 * @author Miguel Angel Rodríguez-García
 * @version 1.0
 */
public class GraphMLFormatter extends ViewFormat {
    /**
     * Edge counter
     */
    private int edgeCounter = 0;
    /**
     * Node's color
     */
    private String nodeColor ="blue";

    /**
     * Constructor of the class.
     */
    public GraphMLFormatter(){
        super();
    }

    /**
     * It provides the header of the GraphML file.
     * @return The header of the GraphML file.
     */
    public String getHeader(){
        return"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"  \n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n" +
                "     http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">\n"+
                "<graph id=\"G\" edgedefault=\"undirected\">\n";
    }

    /**
     * It provides the footer of the GraphML file.
     * @return The footer of the GraphML file.
     */
    public String getFooter(){
        return("</graph>\n" +
                "</graphml>");
    }

    /**
     * Its aim is to transform a root class and its subclass in GraphML format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @return A string that contains a representation of the root class and the subclass given in GraphML.
     */
    public String formatter(HashMap rootClass,HashMap subClass) {
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<node id=\""+rootLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<node id=\""+classLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<edge id=\"e"+(edgeCounter+1)+"\" source=\""+classLabel+"\" target=\""+rootLabel+"\"/>\n";
        }
        return content;
    }

    /**
     * Its aim is to transform a root class, its subclass and the object property that relation both in GraphML format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @param objectProperty Object Property that will be parsed.
     * @return A string that contains a representation of the root class and its subclass related to the object property given in GraphML.
     */
    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<node id=\""+rootLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<node id=\""+classLabel+"\"/>\n";
            content += "\t<data key=\"d0\">"+nodeColor+"</data>\n";
            content+="<edge id=\"e"+(edgeCounter+1)+"\" source=\""+classLabel+"\" target=\""+rootLabel+"\"/>\n";
            content+="\t<data key=\"d1\">"+objectProperty+"</data>\n";
        }
        return(content);
    }

    /**
     * It provides the extension of the GraphML file.
     * @return The extension of the GraphML file that is ".graphml".
     */
    public String getExtension(){
        return(".graphml");
    }
}