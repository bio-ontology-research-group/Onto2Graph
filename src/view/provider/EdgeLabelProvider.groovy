package view.provider

import org.jgraph.graph.Edge
import org.jgrapht.ext.EdgeNameProvider

/*
 * Copyright 2014 Miguel Ángel Rodríguez-García (miguel.rodriguezgarcia@kaust.edu.sa).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class implements EdgeNameProvider and basically it implements a method to provide the name of a edge given.
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */
public class EdgeLabelProvider implements EdgeNameProvider
{
    /**
     * Constructor of the class
     */
    public EdgeLabelProvider()
    {}

    /**
     * It retrieves the name of the edge.
     * @param edge The edge object from where it is going to be extracted the name
     * @return The name of the edge given.
     */
    public String getEdgeName(Object edge)
    {
        String[] objectProperty = edge.toString().split("&&");
        if(objectProperty.length==2){
            return(objectProperty[1]);
        }
        return "subClassOf";
    }
}
