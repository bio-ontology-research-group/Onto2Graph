package view;

/**
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in Flatfile format.
 *
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class GraphMLFormatter implements ViewFormat {
    private String head="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"  \n" +
            "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "    xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns\n" +
            "     http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd\">";

    public void formatter(){

    }

    public void serialize(String outpath){


    }
}