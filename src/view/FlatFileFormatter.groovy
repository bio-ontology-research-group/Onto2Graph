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
 * in Flatfile format.
 *
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */
public class FlatFileFormatter extends ViewFormat {

    /**
     * Constructor of the class.
     */
    public FlatFileFormatter(){
        super();
    }

    /**
     * It provides the header of the FlatFile format.
     * @return The header of the FlatFile format.
     */
    public String getHeader(){
        return("\tNodes\t\t\t\tEdges+\n");
    }

    /**
     * It provides the footer of the FlatFile format.
     * @return The footer of the FlatFile format.
     */
    public String getFooter(){
        return("");
    }

    /**
     * Its aim is to transform a root class and its subclass in FlatFile format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @return A string that contains a representation of the root class and the subclass given in FlatFile
     */
    String formatter(HashMap rootClass,HashMap subClass) {
        String content= "";
        if((rootClass!=null)&&(subClass!=null)){
            content+="\t"+rootClass.get("remainder")+",\t"+subClass.get("remainder")+"\n";
        }
        return (content);
    }

    /**
     * Its aim is to transform a root class, its subclass and the object property that relation both in FlatFile format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @param objectProperty Object Property that will be parsed.
     * @return A string that contains a representation of the root class and its subclass related to the object property given in FlatFile.
     */
    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content = "";
        if((rootClass)&&(subClass!=null)){
            content+="\t"+rootClass.get("remainder")+",\t"+subClass.get("remainder")+"\t\t"+objectProperty+"\n";
        }
        return(content);
    }

    /**
     * It provides the extension of the FlatFile file.
     * @return The extension of the FlatFile file that is ".flatfile".
     */
    public String getExtension(){
        return(".flatfile");
    }
}