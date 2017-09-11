package marg.graph

import org.jgrapht.graph.DefaultEdge

/**
 * Internal class that represents a new kind of edge that includes labels
 * @param < V >
 */
public class RelationshipEdge<V> extends DefaultEdge {
    /**
     * First vertex.
     */
    private V v1;
    /**
     * Second vertex.
     */
    private V v2;
    /**
     * URI given.
     */
    private String uri;

    /**
     * Label given.
     */
    private String label;


    /**
     * Constructor of the class.
     * @param v1 Source vertex.
     * @param v2 Destiny vertex.
     * @param uri Uri of the edge.
     * @param label Label of the edge.
     */
    public RelationshipEdge(V v1, V v2, String uri,String label) {
        this.v1 = v1;
        this.v2 = v2;
        this.uri = uri;
        this.label = label;
    }

    /**
     * It retrieves the source vertex.
     * @return Source vertex.
     */
    public V getV1() {
        return v1;
    }

    /**
     * It retrieves the destiny vertex
     * @return Destiny vertex
     */
    public V getV2() {
        return v2;
    }

    /**
     * It retrieves the id of vertex
     * @return Id of the vertex
     */
    public String getURI() {
        return uri;
    }

    /**
     * It retrieves the label of vertex
     * @return Label of the vertex
     */
    public String getLabel() {
        return label;
    }


    /**
     * It retrieves the label of the vertex.
     * @return Label of the vertex.
     */
    public String toString() {
        return label;
    }
}
