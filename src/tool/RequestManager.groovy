package tool

import groovyx.gpars.GParsPool
import org.semanticweb.owlapi.model.*
import org.semanticweb.owlapi.reasoner.BufferingMode
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.SimpleConfiguration
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner
import org.semanticweb.owlapi.search.EntitySearcher
import show.ProgressBar

/**
 * Singleton class that contains the tools needed to work with the ontology.
 * @author Miguel Angel Rodríguez Garcia
 * @version 1.0
 */
public class RequestManager {
    public static RequestManager instance = null;
    private HashMap<String,HashSet> preComputedSubClasses = null;
    /**
     * Private constructor
     */
    private RequestManager(){
        preComputedSubClasses = new HashMap<String,HashSet>();
    }

    public static RequestManager getInstance(){
        if(instance==null){
            instance = new RequestManager();
        }
        return(instance);
    }

    public void computedSubClases(OWLOntology ontology,OWLReasoner reasoner,String[] properties){

        HashSet<OWLClass> classes = ontology.getClassesInSignature(true);
        String property;
        int classesIndex=0;
        int classesCounter = classes.size();
        GParsPool.withPool {
            OWLDataFactory factory = ontology.getOWLOntologyManager().getOWLDataFactory();
            classes.eachParallel { clazz ->
                ProgressBar.getInstance().printProgressBar((int) Math.round((classesIndex * 100) / (classesCounter)), "precomputing classes...");
                classesIndex++;
                HashSet<OWLClass> subClasses = reasoner.getSubClasses(clazz,true).getFlattened();
                subClasses.remove(factory.getOWLNothing());
                if((subClasses!=null)&&(!subClasses.isEmpty())){
                    this.preComputedSubClasses.put(clazz.toString(),subClasses);
                }
                if((properties!=null)&&(properties.length>0)){
                    for (int i = 0; i < properties.length; i++) {
                        property = properties[i];
                        if (property != null) {
                            OWLObjectProperty objectProperty = factory.getOWLObjectProperty(IRI.create(property));
                            OWLObjectSomeValuesFrom query = factory.getOWLObjectSomeValuesFrom(objectProperty, clazz);
                            HashSet<OWLClass> subClassesProperty = reasoner.getSubClasses(query, true).getFlattened();
                            subClassesProperty.remove(factory.getOWLNothing());
                            subClassesProperty.remove(factory.getOWLThing());
                            if ((subClassesProperty != null) && (!subClassesProperty.isEmpty())) {
                                this.preComputedSubClasses.put(clazz.toString() + property, subClassesProperty);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieve the list of objects properties
     */
    Set<String> getObjectProperties(OWLOntology ontology){
        HashSet<String> objectProperties = new HashSet<String>();
        if(ontology!=null) {
            OWLDataFactory factory = ontology.getOWLOntologyManager().getOWLDataFactory();
            StructuralReasoner structuralReasoner = new StructuralReasoner(ontology,new SimpleConfiguration(), BufferingMode.NON_BUFFERING);
            getRecursiveObjectProperties(objectProperties,factory.getOWLTopObjectProperty(),structuralReasoner);
        }
        return objectProperties;
    }

    private void getRecursiveObjectProperties(HashSet objectProperties,OWLObjectProperty rootObjectProperty, OWLReasoner reasoner){
        Set<OWLObjectPropertyExpression> properties = reasoner.getSubObjectProperties(rootObjectProperty,true).getFlattened();

        if(properties.empty){
            return;
        }
        for(OWLObjectPropertyExpression expression : properties) {
            if((expression instanceof OWLObjectProperty)&&(!expression.isAnonymous())&&
                    (!expression.isOWLTopObjectProperty())&&(!expression.isOWLBottomObjectProperty())) {
                objectProperties.add(expression.getNamedProperty().getIRI().toString());
                getRecursiveObjectProperties(objectProperties, expression.getNamedProperty(), reasoner);
            }
        }
        return;
    }

    /**
     * Iterate the query engines, collecting results from each and collating them into a single structure.
     *
     * @param mOwlQuery Class query in Manchester OWL Syntax.
     * @param requestType Type of class match to be performed. Valid values are: subclass, superclass, equivalent or all.
     * @return Set of OWL Classes.
     */
    public Set<HashMap> subClassesQuery(OWLClass clazz,OWLOntology ontology) {
        Set result = new HashSet();
        String ontUri = ontology.getOntologyID().getOntologyIRI().toString();
        Set classes = this.preComputedSubClasses.get(clazz.toString());
        result.addAll(classes2info(classes, ontology, ontUri))
        return(result);
    }

    /**
     * This returns the direct R-successors of a class C in O
     * class and relations are given as String-IRIs
     */
    public Set relationQuery(String relation, String sClazz, OWLOntology ontology) {
        Set classes = new HashSet<>();
        def manager = ontology.getOWLOntologyManager();
        def factory = manager.getOWLDataFactory();
        String ontUri = ontology.getOntologyID().getOntologyIRI().toString();

        // get the direct subclasses of cl
        Set<OWLClass> subclasses = preComputedSubClasses.get(sClazz);

        if(preComputedSubClasses.containsKey(sClazz+relation)) {
            Set<OWLClass> mainResult = new HashSet(preComputedSubClasses.get(sClazz + relation));

            // Now remove all classes that are not specific to cl (i.e., there is a more specific class in which the R-edge can be created)
            subclasses.each { subClass ->
                if(preComputedSubClasses.containsKey(subClass.toString()+relation)) {
                    def subResult = new HashSet(preComputedSubClasses.get(subClass.toString() + relation));
                    mainResult = mainResult - subResult;
                }
            }
            mainResult.remove(factory.getOWLNothing())
            mainResult.remove(factory.getOWLThing())
            classes.addAll(classes2info(mainResult, ontology, ontUri))
        }
        return classes;
    }

    public Set classes2info(Set<OWLClass> classes, OWLOntology o, String uri) {
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
                    info['deprecated'] = true;
                }
                if(annotation.getProperty().isLabel()){
                    OWLLiteral val = (OWLLiteral) annotation.getValue();
                    info['label'] = val.getLiteral();
                }
            }
            if (!info['deprecated']) {
                if (info['label'] != null) {
                    result.add(info);
                }
            }
        }
        return result
    }

}
