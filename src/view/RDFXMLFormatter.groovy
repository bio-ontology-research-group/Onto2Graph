package view;

import java.util.HashMap;

/**
 * Created by marg27 on 04/10/15.
 */
public class RDFXMLFormatter extends ViewFormat{

    public RDFXMLFormatter(){
        super();
    }

    public String getHeader(){
        return("<?xml version=\"1.0\"?>\n" +
                "<rdf:RDF\n" +
                "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n" +
                "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\">\n");
    }

    public String getFooter() {
        return("</rdf:RDF>");
    }

    public String formatter(HashMap rootClass,HashMap subClass){
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            content="<rdf:Description rdf:about=\""+rootClass.get("classURI")+"\">\n";
            content+="\t<rdf:type resource=\"http://www.w3.org/2000/01/rdf-schema#Class\"/>\n"
            content+="\t<rdfs:subClassOf rdf:resource=\""+subClass.get("classURI")+"\"/>\n";
            content+="</rdf:Description>\n";
        }
        return(content);
    }

    public String formatter(HashMap rootClass,HashMap subClass, String objectProperty){
        String content="";
        if((rootClass!=null)&&(subClass!=null)){
            content="<rdf:Description rdf:about=\""+rootClass.get("classURI")+"\">\n";
            content+="\t<rdf:type resource=\"http://www.w3.org/2000/01/rdf-schema#Class\"/>\n"
            content+="\t<rdfs:"+extractLabel(objectProperty)+" rdf:resource=\""+subClass.get("classURI")+"\"/>\n";
            content+="</rdf:Description>\n";
        }

        return(content);
    }

    private String extractLabel(String objectProperty){
        if((objectProperty!=null)&&(!objectProperty.isEmpty())){
            int pos = objectProperty.lastIndexOf("#",objectProperty.length());
            if(pos==-1){
                pos = objectProperty.lastIndexOf("/",objectProperty.length());
            }
            if(pos>0){
                return(objectProperty.substring(pos+1,objectProperty.length()));
            }
        }
        return(objectProperty);
    }

    public String getExtension(){
        return(".rdfxml");
    }
}
