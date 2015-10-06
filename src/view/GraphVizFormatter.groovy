package view;

/**
 * Formatter responsible for building a file that contains the representation of an ontology given
 * in SVG format.
 *
 * @author Miguel Angel Rodríguez García
 * @version 1.0
 */
public class GraphVizFormatter implements ViewFormat{
    private String head= "digraph {}";
    public void formatter(){

    }

    public void serialize(){

    }

    function void exportToGrapvhViz(node,stGraph){
        if(node.children==null){
            return (stGraph);
        }
        stGraph= stGraph.concat(createGraphicVizDescription(node.name,node["colour"],node["leaf"]));
        $.each(node.children,function(index,child){
            var name = child.name;
            if((name=="˄˄˄")||(name=="˅˅˅")) {
                name = "...";
            }
            stGraph = stGraph.concat(createGraphicVizDescription(name,child["colour"],child["leaf"]));
            if(child["edge"]!=null){
                result = parseColour(child["edge"]);
                stGraph = stGraph.concat(' <' + node.name + '> -> <' + name + '> [color="' + result[0] + " " + parseFloat(result[1]) + " " + parseFloat(result[2]) + '"];');
            }else {
                stGraph = stGraph.concat(" <" + node.name + "> -> <" + name + ">; ");
            }

            if(name!="...") {
                stGraph = exportToGrapvhViz(child, stGraph);
            }

        });
        return(stGraph);
    };

    function createGraphicVizDescription(name,colour,leaf){
        var description ='';
        if((name!=null)&&((colour!=null)||(leaf!=null))){
            if(leaf!=null){
                result = parseColour(leaf);
            }else{
                result = parseColour(colour);
            }
            if(Array.isArray(result)&&(result.length==3)) {
                description = ' <' + name + '> [label=<"' + name + '">, shape="circle" style="filled" color="' + result[0] + " " + parseFloat(result[1]) + " " + parseFloat(result[2]) + '"];';
            }
        }
        return description;
    };
}
