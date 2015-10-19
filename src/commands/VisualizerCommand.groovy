package commands

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner
import view.FlatFileFormatter
import view.FormatterType
import view.GraphMLFormatter
import view.GraphVizFormatter
import view.RDFXMLFormatter
import view.ViewFormat;

/**
 * This commando is in charge of generating an output file that will contain a graphic representation of an ontology given.
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class VisualizerCommand implements Command{
    protected OWLReasoner reasoner = null;
    protected OWLOntology ontology = null;
    protected String[] properties = null;
    protected FormatterType formatterType = null;
    protected String outpath = null;
    public VisualizerCommand(OWLReasoner reasoner,OWLOntology ontology,FormatterType formatterType,String outpath, String[] properties){
        this.reasoner = reasoner;
        this.ontology = ontology;
        this.properties = properties;
        this.formatterType = formatterType;
        this.outpath = outpath;

    }
    public VisualizerCommand(OWLReasoner reasoner,OWLOntology ontology,FormatterType formatterType,String outpath){
        this.reasoner = reasoner;
        this.ontology = ontology;
        this.formatterType = formatterType;
        this.outpath = outpath;
        properties = null;
    }
    public void executeCommand() {
        ViewFormat viewFormat = null;
        if (formatterType == FormatterType.RDFXML_FORMATTER){
            viewFormat = new RDFXMLFormatter();
        }else if(formatterType == FormatterType.GRAPHVIZ_FORMATTER) {
            viewFormat = new GraphVizFormatter();
        }else if(formatterType == FormatterType.GRAPHML_FORMATTER) {
            viewFormat = new GraphMLFormatter();
        }else if(formatterType == FormatterType.FLATFILE_FORMATTER) {
            viewFormat = new FlatFileFormatter();
        }
        if(viewFormat!=null){
            viewFormat.parseOntology(ontology,reasoner,properties,outpath);
        }

    }
}
