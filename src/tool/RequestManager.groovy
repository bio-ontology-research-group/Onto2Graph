package tool

import org.semanticweb.owlapi.apibinding.OWLManager
import org.semanticweb.owlapi.model.IRI
import org.semanticweb.owlapi.model.OWLAnnotation
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom
import org.semanticweb.owlapi.model.OWLClass
import org.semanticweb.owlapi.model.OWLLiteral
import org.semanticweb.owlapi.model.OWLObjectProperty
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression
import org.semanticweb.owlapi.model.OWLOntology
import org.semanticweb.owlapi.reasoner.BufferingMode
import org.semanticweb.owlapi.reasoner.SimpleConfiguration
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner
import org.semanticweb.owlapi.search.EntitySearcher;

/**
 * Singleton class that contains the tools needed to work with the ontology.
 * @author Miguel Angel Rodríguez Garcia
 * @version 1.0
 */
public class RequestManager {
    public static RequestManager instance = null;
    /**
     * Private constructor
     */
    private RequestManager(){}

    public static RequestManager getInstance(){
        if(instance==null){
            instance = new RequestManager();
        }
        return(instance);
    }

    /** This returns the direct R-successors of a class C in O
     class and relations are given as String-IRIs
     */
    Set relationQuery(String relation, String clazz, OWLOntology ontology) {
        Set classes = new HashSet<>();
        OWLManager manager = ontology.getOWLOntologyManager()
        def sForm = new NewShortFormProvider(aProperties, preferredLanguageMap, manager);
        QueryEngine queryEngine = new QueryEngine(oReasoner, sForm);

        // get the direct subclasses of cl
        Set<OWLClass> subclasses = queryEngine.getClasses(cl, RequestType.SUBCLASS, true, false)
        // These are all the classes for which the R some C property holds
        String query1 = "<$relation> SOME $cl"
        Set<OWLClass> mainResult = queryEngine.getClasses(query1, RequestType.SUBCLASS, true, false)
        // Now remove all classes that are not specific to cl (i.e., there is a more specific class in which the R-edge can be created)
        subclasses.each { sc ->
            String query2 = "$relation SOME "+sc.toString()
            def subResult = queryEngine.getClasses(query2, RequestType.SUBCLASS, true, false)
            mainResult = mainResult - subResult
        }
        classes.addAll(classes2info(mainResult, ontology, ontUri))
        return classes
    }

    /**
     * Gets the sub object properties from the ontology given
     * oString This paramater represents the id of the ontology.
     * rootObjectProperty This parameter represents the root object property asked.
     */
    Set getObjectProperties(String oString,String rootObjectProperty){
        Set objectProperties = new HashSet();
        if((oString!=null)&&(oString.length()>0)&&(rootObjectProperty!=null)&&(rootObjectProperty.length()>0)){
            if(ontologies.containsKey(oString)) {
                OWLOntology ontology = ontologies.get(oString);
                StructuralReasoner structuralReasoner = new StructuralReasoner(ontology,new SimpleConfiguration(), BufferingMode.NON_BUFFERING);
                OWLObjectProperty objectProperty = df.getOWLObjectProperty(IRI.create(rootObjectProperty));
                Set<OWLObjectPropertyExpression> properties = structuralReasoner.getSubObjectProperties(objectProperty,true).getFlattened();

                for(OWLObjectPropertyExpression expression : properties) {
                    Iterator<OWLAnnotationAssertionAxiom> jt = EntitySearcher.getAnnotationAssertionAxioms(expression.getNamedProperty(), structuralReasoner.getRootOntology()).iterator();
                    OWLAnnotationAssertionAxiom axiom;
                    HashMap subProperty = new HashMap<String,String>();
                    while (jt.hasNext()) {
                        axiom = jt.next();
                        if (axiom.getProperty().isLabel()) {
                            OWLLiteral value = (OWLLiteral) axiom.getValue();
                            subProperty.put('classURI',axiom.getSubject().toString());
                            subProperty.put('label',value.getLiteral().toString());
                            objectProperties.add(subProperty);
                        }
                    }
                }
            }
        }
        return objectProperties;
    }

    /**
     * Iterate the query engines, collecting results from each and collating them into a single structure.
     *
     * @param mOwlQuery Class query in Manchester OWL Syntax.
     * @param requestType Type of class match to be performed. Valid values are: subclass, superclass, equivalent or all.
     * @return Set of OWL Classes.
     */
    Set runQuery(String mOwlQuery, String type, String ontUri,int version, boolean direct, boolean labels) {
        def start = System.currentTimeMillis()

        type = type.toLowerCase()
        def requestType
        switch(type) {
            case "superclass": requestType = RequestType.SUPERCLASS; break;
            case "subclass": requestType = RequestType.SUBCLASS; break;
            case "equivalent": requestType = RequestType.EQUIVALENT; break;
            case "supeq": requestType = RequestType.SUPEQ; break;
            case "subeq": requestType = RequestType.SUBEQ; break;
            case "realize": requestType = RequestType.REALIZE; break;
            default: requestType = RequestType.SUBEQ; break;
        }

        Set classes = new HashSet<>();
        QueryEngine queryEngine = queryEngines.get(ontUri);

        Set<OWLClass> resultSet = queryEngine.getClasses(mOwlQuery, requestType, direct, labels)
        resultSet.remove(df.getOWLNothing())
        resultSet.remove(df.getOWLThing())
        classes.addAll(classes2info(resultSet, ontology, ontUri))

        return classes;
    }

    Set classes2info(Set<OWLClass> classes, OWLOntology o, String uri) {
        ArrayList result = new ArrayList<HashMap>();
        for(def c : classes) {
            def info = [
                    "owlClass": c.toString(),
                    "classURI": c.getIRI().toString(),
                    "ontologyURI": uri.toString(),
                    "remainder": c.getIRI().getFragment(),
                    "label": null,
                    "definition": null,
                    "deprecated": false
            ];

            for (OWLAnnotation annotation : EntitySearcher.getAnnotations(c, o)) {
                if(annotation.isDeprecatedIRIAnnotation()) {
                    info['deprecated'] = true
                }
            }
            if (!info['deprecated']) { // ignore all deprecated classes! TODO: trigger this by query flag

                def scoreDocs = searcher.search(bq, 1).scoreDocs
                if((scoreDocs!=null)&&(scoreDocs.length>0)) {
                    def dResult = scoreDocs[0]
                    def hitDoc = searcher.doc(dResult.doc)
                    hitDoc.each {
                        info['label'] = hitDoc.get('first_label')
                    }
                    hitDoc.getFields().each { field ->
                        def fName = field.name()
                        if (fName!="AberOWL-catch-all") {
                            info[fName] = new LinkedHashSet()
                            hitDoc.getValues(fName).each { fVal ->
                                info[fName].add(fVal)
                            }
                        }
                    }
                    info['definition'] = hitDoc.get('definition')
                }

                if (info['label'] != null) {
                    result.add(info);
                }
            }
        }
        return result
    }

}
