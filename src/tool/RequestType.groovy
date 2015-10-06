/* 
 * Copyright 2014 Luke Slater (lus11@aber.ac.uk).
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
 * The type of class to return when querying for matches to a class description
 * in an OWL ontology.
 *
 * @author Luke Slater
 */

package tool;

enum RequestType {


    /**
     * All types of relevant classes will be returned.
     */
    ALL,

    /**
     * Only subclasses of the class matches will be returned.
     */
    SUBCLASS,

    /**
     * Only superclasses of the class matches will be returned.
     */
    SUPERCLASS,

    /**
     * Only classes equivalent to the class matches will be returned.
     */
    EQUIVALENT,

    SUBEQ,
    SUPEQ,
    REALIZE,
    EQUIVALENTOP, // equivalent object properties
    SUBOP, // sub object properties
    SUPOP //
}