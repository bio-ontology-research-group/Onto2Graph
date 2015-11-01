package view.provider

import org.jgraph.graph.Edge
import org.jgrapht.ext.EdgeNameProvider

/**
 * Created by marg27 on 01/11/15.
 */
public class EdgeLabelProvider implements EdgeNameProvider
{
    public EdgeLabelProvider()
    {}


    public String getEdgeName(Object edge)
    {
        String[] objectProperty = edge.toString().split("&&");
        if(objectProperty.length==2){
            return(objectProperty[1]);
        }
        return "subClassOf";
    }
}
