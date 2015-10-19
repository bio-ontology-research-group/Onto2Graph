package view;

import org.semanticweb.owlapi.model.OWLClass;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by marg27 on 04/10/15.
 */
public class RDFXMLFormatter extends ViewFormat {

    public RDFXMLFormatter(){
        super();
    }

    public String getHeader(){
        return("<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<rdf:RDF\n" +
                "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                "xmlns:si=\"http://www.w3schools.com/rdf/\">\n");
    }

    public String getFooter() {
        return("</rdf:RDF>");
    }

    public String formatter(HashMap rootClass,Set<HashMap> subClasses){
        String content="";
        content="<rdf:Description rdf:about=\""+rootClass.get("owlClass")+"\">";
        Iterator<HashMap> it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+="<rdfs:subClassOf rdf:resource=\""+subClass.get("owlClass")+"\"/>";
            }
        }
        content+="</rdf:Description>";
        return(content);
    }

    public String formatter(HashMap rootClass,Set<HashMap> subClasses, String objectProperty){
        String content="";
        content="<rdf:Description rdf:about=\""+rootClass.get("owlClass")+"\">";
        Iterator<HashMap> it = subClasses.iterator();
        HashMap subClass;
        while(it.hasNext()){
            subClass = it.next();
            if(subClass!=null){
                content+="<rdfs:"+objectProperty+" rdf:resource=\""+subClass.get("owlClass")+"\"/>";
            }
        }
        content+="</rdf:Description>";
        return(content);
    }

    public String getExtension(){
        return(".rdfxml");
    }
}
