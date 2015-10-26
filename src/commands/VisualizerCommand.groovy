package commands

import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.OWLReasoner
import view.*

/**
 * This commando is in charge of generating an output file that will contain a graphic representation of an ontology given.
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class VisualizerCommand implements Command{
    protected OWLReasoner reasoner = null;
    protected OWLOntology ontology = null;
    protected String[] properties = null;
    protected Set<FormatterType> setFormatterType = null;
    protected String outpath = null;
    public VisualizerCommand(OWLReasoner reasoner,OWLOntology ontology,Set<FormatterType> setFormatterType,String outpath, String[] properties){
        this.reasoner = reasoner;
        this.ontology = ontology;
        this.setFormatterType = setFormatterType;
        this.outpath = outpath;
        this.properties = properties;
    }
    public void executeCommand() {
        ViewFormat viewFormat = null;
        Iterator<FormatterType> it = setFormatterType.iterator();
        FormatterType formatterType;
        while(it.hasNext()){
            formatterType = it.next();
            if (formatterType == FormatterType.RDFXML_FORMATTER) {
                viewFormat = new RDFXMLFormatter();
            } else if (formatterType == FormatterType.GRAPHVIZ_FORMATTER) {
                viewFormat = new GraphVizFormatter();
            } else if (formatterType == FormatterType.GRAPHML_FORMATTER) {
                viewFormat = new GraphMLFormatter();
            } else if (formatterType == FormatterType.FLATFILE_FORMATTER) {
                viewFormat = new FlatFileFormatter();
            }
            if (viewFormat != null) {
                viewFormat.parseOntology(ontology, reasoner, properties, outpath);
            }
        }

    }
}
