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
 * in SVG format.
 *
 * @author Miguel Angel Rodríguez-García
 * @version 1.0
 */
public class GraphVizFormatter extends ViewFormat{
    /**
     * Node's color
     */
    protected String color="#BDBDBD";
    /**
     * Object properties' color
     */
    protected String propertyColor = "#5FB404";

    /**
     * Constructor of the class.
     */
    public GraphVizFormatter(){
        super();
    }

    /**
     * It provides the header of the GraphViz file.
     * @return The header of the GraphViz file.
     */
    public String getHeader(){
        return("digraph{\n");
    }

    /**
     * It provides the header of the GraphViz file.
     * @return The header of the GraphViz file.
     */
    public String getFooter(){
        return("}");
    }

    /**
     * Its aim is to transform a root class and its subclass in GraphViz format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @return A string that contains a representation of the root class and the subclass given in GraphViz
     */
    public String formatter(HashMap rootClass,HashMap subClass) {
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<" + rootLabel + "> [label=<\""+ rootLabel +"\">, shape=\"circle\" style=\"filled\" color=\""+color+"\"];\n";
            content+= "<" + classLabel + "> [label=<\"" + classLabel + "\">, shape=\"circle\" style=\"filled\" color=\""+color+"\"];\n";
            content+=" <" + classLabel + "> -> <" + rootLabel + ">;\n ";
        }
        return(content);
    }

    /**
     * Its aim is to transform a root class, its subclass and the object property that relation both in GraphViz format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @param objectProperty Object Property that will be parsed.
     * @return A string that contains a representation of the root class and its subclass related to the object property given in GraphViz.
     */
    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content = "";
        if((rootClass!=null)&&(subClass!=null)){
            String rootLabel = filterLabel(rootClass.get("remainder"));
            String classLabel = filterLabel(subClass.get("remainder"));

            content = "<" + rootLabel + "> [label=<\"" + rootLabel + "\">, shape=\"circle\" style=\"filled\" color=\"" + color+"\"];\n";
            content+= "<" + classLabel + "> [label=<\"" + classLabel + "\">, shape=\"circle\" style=\"filled\" color=\""+propertyColor+"\"];\n";
            content+=" <" + classLabel + "> -> <" + rootLabel + ">;\n ";
        }
        return(content);
    }

    /**
     * It provides the extension of the GraphViz file.
     * @return The extension of the GraphViz file that is ".dot".
     */
    public String getExtension(){
        return(".dot");
    }
}
