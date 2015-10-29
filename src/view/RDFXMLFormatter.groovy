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
 * in RDFXML format.
 *
 * @author Miguel Angel Rodríguez-García
 * @version 1.0
 */
public class RDFXMLFormatter extends ViewFormat{

    /**
     * Constructor of the class.
     */
    public RDFXMLFormatter(){
        super();
    }

    /**
     * It provides the header of the RDFXML file.
     * @return The header of the RDFXML file.
     */
    public String getHeader(){
        return("<?xml version=\"1.0\"?>\n" +
                "<rdf:RDF\n" +
                "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\">\n");
    }

    /**
     * It provides the footer of the RDFXML file.
     * @return The header of the RDFXML file.
     */
    public String getFooter() {
        return("</rdf:RDF>");
    }

    /**
     * Its aim is to transform a root class and its subclass in RDFXML format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @return A string that contains a representation of the root class and the subclass given in RDFXML
     */
    public String formatter(HashMap rootClass,HashMap subClass){
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            content="<rdf:Description rdf:about=\""+subClass.get("classURI")+"\">\n";
            content+="\t<rdf:type resource=\"http://www.w3.org/2000/01/rdf-schema#Class\"/>\n"
            content+="\t<rdfs:subClassOf rdf:resource=\""+rootClass.get("classURI")+"\"/>\n";
            content+="</rdf:Description>\n";
        }
        return(content);
    }

    /**
     * Its aim is to transform a root class, its subclass and the object property that relation both in RDFXML format.
     * @param rootClass Root class that will be parsed.
     * @param subClass Subclass that will be parsed.
     * @param objectProperty Object Property that will be parsed.
     * @return A string that contains a representation of the root class and its subclass related to the object property given in RDFXML.
     */
    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            content="<rdf:Description rdf:about=\""+subClass.get("classURI")+"\">\n";
            content+="\t<rdf:type resource=\"http://www.w3.org/2000/01/rdf-schema#Class\"/>\n"
            content+="\t<rdfs:"+objectProperty+" rdf:resource=\""+rootClass.get("classURI")+"\"/>\n";
            content+="</rdf:Description>\n";
        }

        return(content);
    }

    /**
     * It provides the extension of the RDFXML file.
     * @return The extension of the RDFXML file that is ".rdfxml".
     */
    public String getExtension(){
        return(".rdfxml");
    }
}
