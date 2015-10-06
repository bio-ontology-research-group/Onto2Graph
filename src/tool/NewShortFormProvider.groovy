package tool

/*
 * Copyright 2014 Robert Hoehndorf (leechuck@leechuck.de).
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

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValueVisitorEx;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologySetProvider;
import org.semanticweb.owlapi.util.* ;
import org.semanticweb.owlapi.search.*;

/**
 * Modified version of the standard short form provider to add quotations around
 *   multi-word label names.
 *
 * @see ShortFormProvider
 * @author OWLAPI, Robert Hoehndorf (leechuck@leechuck.de)
 */
public class NewShortFormProvider implements ShortFormProvider {
    private final OWLOntologySetProvider ontologySetProvider;
    private final ShortFormProvider alternateShortFormProvider;
    private IRIShortFormProvider alternateIRIShortFormProvider;
    private final List<OWLAnnotationProperty> annotationProperties;
    private final Map<OWLAnnotationProperty, List<String>> preferredLanguageMap;
    private final OWLAnnotationValueVisitorEx<String> literalRenderer;

    /** Constructs an annotation value short form provider. Using
     * {@code SimpleShortFormProvider} as the alternate short form provider
     *
     * @param annotationProperties
     *            A {@code List} of preferred annotation properties. The list is
     *            searched from start to end, so that annotations that have a
     *            property at the start of the list have a higher priority and
     *            are selected over annotations with properties that appear
     *            towards or at the end of the list.
     * @param preferredLanguageMap
     *            A map which maps annotation properties to preferred languages.
     *            For any given annotation property there may be a list of
     *            preferred languages. Languages at the start of the list have a
     *            higher priority over languages at the end of the list. This
     *            parameter may be empty but it must not be {@code null}.
     * @param ontologySetProvider
     *            An {@code OWLOntologySetProvider} which provides a set of
     *            ontology from which candidate annotation axioms should be
     *            taken. For a given entity, all ontologies are examined. */
    public NewShortFormProvider(
            List<OWLAnnotationProperty> annotationProperties,
            Map<OWLAnnotationProperty, List<String>> preferredLanguageMap,
            OWLOntologySetProvider ontologySetProvider) {
        this(annotationProperties, preferredLanguageMap, ontologySetProvider,
                new SimpleShortFormProvider());
    }

    /** Constructs an annotation short form provider.
     *
     * @param annotationProperties
     *            A {@code List} of preferred annotation properties. The list is
     *            searched from start to end, so that annotations that have a
     *            property at the start of the list have a higher priority and
     *            are selected over annotations with properties that appear
     *            towards or at the end of the list.
     * @param preferredLanguageMap
     *            A map which maps annotation properties to preferred languages.
     *            For any given annotation property there may be a list of
     *            preferred languages. Languages at the start of the list have a
     *            higher priority over languages at the end of the list. This
     *            parameter may be empty but it must not be {@code null}.
     * @param ontologySetProvider
     *            An {@code OWLOntologySetProvider} which provides a set of
     *            ontology from which candidate annotation axioms should be
     *            taken. For a given entity, all ontologies are examined.
     * @param alternateShortFormProvider
     *            A short form provider which will be used to generate the short
     *            form for an entity that does not have any annotations. This
     *            provider will also be used in the case where the value of an
     *            annotation is an {@code OWLIndividual} for providing the short
     *            form of the individual. */
    public NewShortFormProvider(
            List<OWLAnnotationProperty> annotationProperties,
            Map<OWLAnnotationProperty, List<String>> preferredLanguageMap,
            OWLOntologySetProvider ontologySetProvider,
            ShortFormProvider alternateShortFormProvider) {
        this(ontologySetProvider, alternateShortFormProvider,
                new SimpleIRIShortFormProvider(), annotationProperties,
                preferredLanguageMap);
    }

    /** Constructs an annotation short form provider.
     *
     * @param annotationProperties
     *            A {@code List} of preferred annotation properties. The list is
     *            searched from start to end, so that annotations that have a
     *            property at the start of the list have a higher priority and
     *            are selected over annotations with properties that appear
     *            towards or at the end of the list.
     * @param preferredLanguageMap
     *            A map which maps annotation properties to preferred languages.
     *            For any given annotation property there may be a list of
     *            preferred languages. Languages at the start of the list have a
     *            higher priority over languages at the end of the list. This
     *            parameter may be empty but it must not be {@code null}.
     * @param ontologySetProvider
     *            An {@code OWLOntologySetProvider} which provides a set of
     *            ontology from which candidate annotation axioms should be
     *            taken. For a given entity, all ontologies are examined.
     * @param alternateShortFormProvider
     *            A short form provider which will be used to generate the short
     *            form for an entity that does not have any annotations. This
     *            provider will also be used in the case where the value of an
     *            annotation is an {@code OWLIndividual} for providing the short
     *            form of the individual.
     * @param alternateIRIShortFormProvider
     *            the alternate IRI short form provider */
    public NewShortFormProvider(ontologySetProvider,
                                alternateShortFormProvider,
                                alternateIRIShortFormProvider,
                                annotationProperties,
                                preferredLanguageMap) {
        this(ontologySetProvider, alternateShortFormProvider,
                alternateIRIShortFormProvider, annotationProperties,
                preferredLanguageMap, new OWLAnnotationValueVisitorsExNew<String>());
        /*new OWLAnnotationValueVisitorEx<String>() {
            @Override
            public String visit(IRI iri) {
                // TODO refactor the short form providers in here
                return '';
            }
            @Override
            public String visit(OWLAnonymousIndividual individual) {
                return '';
            }
            @Override
            public String visit(OWLLiteral literal) {
                return literal.getLiteral();
            }
        });*/
    }

    /** @param ontologySetProvider
     *            ontologies
     * @param alternateShortFormProvider
     *            short form provider
     * @param alternateIRIShortFormProvider
     *            iri short form provider
     * @param annotationProperties
     *            annotation properties
     * @param preferredLanguageMap
     *            preferred language map
     * @param literalRenderer
     *            literal renderer */
    public NewShortFormProvider(OWLOntologySetProvider ontologySetProvider,
                                ShortFormProvider alternateShortFormProvider,
                                IRIShortFormProvider alternateIRIShortFormProvider,
                                List<OWLAnnotationProperty> annotationProperties,
                                Map<OWLAnnotationProperty, List<String>> preferredLanguageMap,
                                OWLAnnotationValueVisitorEx<String> literalRenderer) {
        this.ontologySetProvider = ontologySetProvider;
        this.alternateShortFormProvider = alternateShortFormProvider;
        this.alternateIRIShortFormProvider = alternateIRIShortFormProvider;
        this.annotationProperties = annotationProperties;
        this.preferredLanguageMap = preferredLanguageMap;
        this.literalRenderer = literalRenderer;
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public String getShortForm(OWLEntity entity) {
        for (OWLAnnotationProperty prop : annotationProperties) {
            // visit the properties in order of preference
            AnnotationLanguageFilter checker = new AnnotationLanguageFilter(prop,
                    preferredLanguageMap.get(prop));
            for (OWLOntology ontology : ontologySetProvider.getOntologies()) {
                for (OWLAnnotationAssertionAxiom ax : EntitySearcher.getAnnotationAssertionAxioms(entity, ontology)) {
                    ax.accept(checker);
                }
            }
            if(checker.getMatch() != null) {
                String res = getRendering(checker.getMatch());
                if (res.indexOf(" ")>-1) {
                    res = "'"+res+"'" ;
                }
                return res.toLowerCase() ;
            }
        }
        return alternateShortFormProvider.getShortForm(entity).toLowerCase();
    }

    /** Obtains the rendering of the specified object. If the object is a
     * constant then the rendering is equal to the literal value, if the object
     * is an individual then the rendering is equal to the rendering of the
     * individual as provided by the alternate short form provider
     *
     * @param object
     *            The object to the rendered
     * @return The rendering of the object. */
    private String getRendering(OWLObject object) {
        // We return the literal value of constants or use the alternate
        // short form provider to render individuals.
        if (object instanceof OWLLiteral) {
            // TODO refactor this method to use the annotation value visitor
            return literalRenderer.visit((OWLLiteral) object);
        } else if (object instanceof IRI) {
            return alternateIRIShortFormProvider.getShortForm((IRI) object);
        } else {
            return alternateShortFormProvider.getShortForm((OWLEntity) object);
        }
    }

    /** @return the annotation URIs that this short form provider uses. */
    public List<OWLAnnotationProperty> getAnnotationProperties() {
        return annotationProperties;
    }

    /** @return the preferred language map */
    public Map<OWLAnnotationProperty, List<String>> getPreferredLanguageMap() {
        return preferredLanguageMap;
    }

    /**
     *
     */
    @Override
    public void dispose() {}

    private static class AnnotationLanguageFilter extends OWLObjectVisitorAdapter {
        private final OWLAnnotationProperty prop;
        private final List<String> preferredLanguages;
        OWLObject candidateValue;
        int lastLangMatchIndex = Integer.MAX_VALUE;

        AnnotationLanguageFilter(OWLAnnotationProperty prop,
                                 List<String> preferredLanguages) {
            this.prop = prop;
            this.preferredLanguages = preferredLanguages;
        }

        public OWLObject getMatch() {
            return candidateValue;
        }

        @Override
        public void visit(OWLAnnotationAssertionAxiom anno) {
            if (lastLangMatchIndex > 0 && anno.getProperty().equals(prop)) {
                // a perfect match - no need to carry on search
                anno.getValue().accept(this);
            }
        }

        @Override
        public void visit(OWLLiteral node) {
            if (preferredLanguages == null || preferredLanguages.isEmpty()) {
                // if there are no languages just match the first thing
                lastLangMatchIndex = 0;
                candidateValue = node;
            } else {
                final int index = preferredLanguages.indexOf(node.getLang());
                if (index >= 0 && index < lastLangMatchIndex) {
                    lastLangMatchIndex = index;
                    candidateValue = node;
                }
            }
        }

        @Override
        public void visit(IRI iri) {
            // No language
            candidateValue = iri;
        }
    }
}

public class OWLAnnotationValueVisitorsExNew implements OWLAnnotationValueVisitorEx {
    @Override
    public String visit(IRI iri) {
        // TODO refactor the short form providers in here
        return null;
    }

    @Override
    public String visit(OWLAnonymousIndividual individual) {
        return null;
    }

    @Override
    public String visit(OWLLiteral literal) {
        return literal.getLiteral();
    }
}