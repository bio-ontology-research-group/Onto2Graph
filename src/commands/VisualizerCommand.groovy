package commands

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner
import view.FlatFileFormatter
import view.FormatterType
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
    protected String[] properties = null;
    protected OWLOntology ontology = null;
    protected String formatter = null;
    protected String outpath = null;
    public VisualizerCommand(OWLReasoner reasoner,OWLOntology ontology,String formatter,String outpath, String properties){
        this.reasoner = reasoner;
        this.ontology = ontology;
        this.properties = properties;
        this.formatter = formatter;
        this.outpath = outpath;

    }
    public VisualizerCommand(OWLReasoner reasoner,OWLOntology ontology,String formatter,String outpath){
        this.reasoner = reasoner;
        this.ontology = ontology;
        this.formatter = formatter;
        this.outpath = outpath;
        properties = null;
    }
    public void executeCommand(){
        ViewFormat viewFormat = null;
        switch(formatter){
            case FormatterType.RDFXML_FORMATTER:
                viewFormat = new RDFXMLFormatter();
                break;
            case FormatterType.GRAPHVIZ_FORMATTER:
                viewFormat = new GraphVizFormatter();
                break;
            case FormatterType.GRAPHML_FORMATTER:
                viewFormat = new GraphVizFormatter();
                break;
            case FormatterType.FLATFILE_FORMATTER:
                viewFormat = new FlatFileFormatter();
                break;
        }
        if(viewFormat!=null){
            viewFormat.formatter(reasoner,ontology,properties);
            viewFormat.buildFile(outpath);
        }

    }
}
