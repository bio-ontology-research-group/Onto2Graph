package tool

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

import org.semanticweb.owlapi.manchestersyntax.parser.ManchesterOWLSyntaxClassExpressionParser
import org.semanticweb.owlapi.expression.ShortFormEntityChecker;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.BidirectionalShortFormProvider;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;

/**
 * Parses Manchester OWL Syntax strings into a normalised ontology class description.
 *
 * @author Luke Slater
 */
public class QueryParser {
    private final BidirectionalShortFormProvider biSFormProvider;
    private final OWLOntology ontology;

    public QueryParser(ontology, sProvider) {
        this.ontology = ontology;
        biSFormProvider = new BidirectionalShortFormProviderAdapter(
                ontology.getOWLOntologyManager(),
                ontology.getImportsClosure(),
                sProvider
        );
    }

    /**
     * Convert a Manchester OWL Syntax query into a generalised class description.
     *
     * @param mOwl String containing a class expression in Manchester OWL Syntax.
     * @return An OWLClassExpression generated from mOwl
     */
    public OWLClassExpression parse(String mOwl, boolean labels) {
        def result = null

        try {
            OWLDataFactory dFactory = this.ontology.getOWLOntologyManager().getOWLDataFactory();
            def eChecker = new BasicEntityChecker(dFactory, ontology)
            def parser = new ManchesterOWLSyntaxClassExpressionParser(dFactory, eChecker);

            if(labels) {
                eChecker = new ShortFormEntityChecker(biSFormProvider)
                parser = new ManchesterOWLSyntaxClassExpressionParser(dFactory, eChecker);
            }

            //       parser.setDefaultOntology(ontology);

            result = parser.parse(mOwl);
        } catch(Exception e) {
            e.printStackTrace()
            result = null
        }

        return result
    }
}