package view.provider

import org.jgrapht.ext.VertexNameProvider

/**
 * Created by marg27 on 01/11/15.
 */
public class VertexLabelProvider implements VertexNameProvider
{
    public LabelNameProvider()
    {}

    public String getVertexName(Object vertex)
    {
        if(vertex instanceof HashMap){
            return(vertex["label"]);
        }
        return vertex.toString();
    }

}
