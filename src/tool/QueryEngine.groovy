package tool;

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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.* ;

/**
 * Manchester OWL Syntax query manager for a particular reasoned ontology.
 *
 * @author Luke Slater
 */
public class QueryEngine {
    private OWLReasoner oReasoner;
    private QueryParser parser;
    private sProvider;

    QueryEngine(oReasoner, sProvider) {
        this.oReasoner = oReasoner;
        this.sProvider = sProvider;
        this.parser = new QueryParser(oReasoner.getRootOntology(), sProvider);
    }

    /**
     * Return a set of classes relevant to a class description represented by a
     * Manchester OWL Syntax string. Returned classes can either be superclasses,
     * subclasses, equivalent classes or a combination of all relevant classes.
     *
     * @param mOwl Manchester OWL Syntax query (form of a raw class description)
     * @param requestType Type of class to return.
     * @see RequestType
     * @return A HashSet of classes relevant to the given class description in
     * mOwl corresponding to the type of request.
     */
    public Set<OWLClass> getClasses(String mOwl, RequestType requestType, boolean direct, boolean labels) {
        if(mOwl == null || mOwl.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression cExpression = parser.parse(mOwl, labels);
        Set<OWLClass> classes = new HashSet<>();

        if(cExpression == null) {
            return classes
        }

        switch(requestType) {
            case RequestType.SUPERCLASS:
                classes.addAll(getSuperClasses(cExpression, direct)); break;
            case RequestType.EQUIVALENT:
                classes.addAll(getEquivalentClasses(cExpression)); break;
            case RequestType.SUBCLASS:
                classes.addAll(getSubClasses(cExpression, direct)); break;
            case RequestType.REALIZE:
                classes.addAll(getIndividuals(cExpression, direct)); break;
            case RequestType.SUBEQ:
                classes.addAll(getSubClasses(cExpression, direct));
                classes.addAll(getEquivalentClasses(cExpression)); break;
            case RequestType.SUPEQ:
                classes.addAll(getSuperClasses(cExpression, direct));
                classes.addAll(getEquivalentClasses(cExpression)); break;
            default: // default is a subclass query
                classes.addAll(getSubClasses(cExpression, direct));
                break;
        }
        return classes;
    }

    public Set<OWLClass> getSuperClasses(OWLClassExpression cExpression, boolean direct) {
        return oReasoner.getSuperClasses(cExpression, direct).getFlattened();
    }

    public Set<OWLClass> getEquivalentClasses(OWLClassExpression cExpression) {
        Node<OWLClass> equivalentClasses = oReasoner.getEquivalentClasses(cExpression);
        Set<OWLClass> result;
        result = equivalentClasses.getEntities();
        return result;
    }

    public Set<OWLClass> getSubClasses(OWLClassExpression cExpression, boolean direct) {
        return oReasoner.getSubClasses(cExpression, direct).getFlattened()
    }

    public Set<OWLClass> getIndividuals(OWLClassExpression cExpression, boolean direct) {
        return oReasoner.getInstances(cExpression, direct).getFlattened()
    }

    /**
     * @return the oReasoner
     */
    public OWLReasoner getoReasoner() {
        return oReasoner;
    }

    /**
     * @return the parser
     */
    public QueryParser getParser() {
        return parser;
    }

    /**
     * @return the sProvider
     */
    public getsProvider() {
        return sProvider;
    }
}
