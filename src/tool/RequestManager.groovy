package tool

import groovyx.gpars.GParsPool
import org.semanticweb.owlapi.model.*
import org.semanticweb.owlapi.reasoner.BufferingMode
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.SimpleConfiguration
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner
import org.semanticweb.owlapi.search.EntitySearcher
import show.ProgressBar

import java.util.concurrent.ConcurrentHashMap

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
 * Singleton class that contains the tools needed to parse the ontology.
 * @author Miguel Ángel Rodríguez-García
 * @version 1.0
 */
public class RequestManager {

    /**
     * The unique instance
     */
    public static RequestManager instance = null;
    /**
     * List of computed subclasses.
     */
    private ConcurrentHashMap<String,HashSet> preComputedSubClasses = null;

    /**
     * Private constructor
     */
    private RequestManager(){
        preComputedSubClasses = new ConcurrentHashMap<String,HashSet>();
    }

    /**
     * Static method to access to the singleton instance.
     * @return The singleton instance built.
     */
    public static RequestManager getInstance(){
        if(instance==null){
            instance = new RequestManager();
        }
        return(instance);
    }

    /**
     * It computes the subclases from an ontology given.
     * @param ontology The ontology used by the reasoner to compute the subclases.
     * @param reasoner The reasoner used to infer subclasses.
     * @param properties The object properties of the ontology that will be used during the compute process.
     */
    public void computedSubClases(OWLOntology ontology,OWLReasoner reasoner,String[] properties){

        HashSet<OWLClass> classes = ontology.getClassesInSignature(true);
        int classesIndex=0;
        int classesCounter = classes.size();
        GParsPool.withPool {
            OWLDataFactory factory = ontology.getOWLOntologyManager().getOWLDataFactory();
            classes.eachParallel { clazz ->
                if(clazz.toString().contains("GO_0031654")){
                    System.out.println("");
                }
                ProgressBar.printProgressBar((int) Math.round((classesIndex * 100) / (classesCounter)), "precomputing classes...");
                classesIndex++;
                HashSet<OWLClass> subClasses = reasoner.getSubClasses(clazz,true).getFlattened();
                subClasses.remove(factory.getOWLNothing());
                if((subClasses!=null)&&(!subClasses.isEmpty())){
                    this.preComputedSubClasses.put(clazz.toString(),subClasses);
                }
                if((properties!=null)&&(properties.size()>0)){
                    String property;
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
     * It retrieves the list of objects properties from an ontology given.
     * @param ontology The ontology from where the object properties are obtained.
     * @return A set of objects properties.
     */
    public Set<String> getObjectProperties(OWLOntology ontology){
        HashSet<String> objectProperties = new HashSet<String>();
        if(ontology!=null) {
            OWLDataFactory factory = ontology.getOWLOntologyManager().getOWLDataFactory();
            StructuralReasoner structuralReasoner = new StructuralReasoner(ontology,new SimpleConfiguration(), BufferingMode.NON_BUFFERING);
            getRecursiveObjectProperties(objectProperties,factory.getOWLTopObjectProperty(),structuralReasoner);
        }
        return objectProperties;
    }

    /**
     * it is a recursive method that go across the ontology in order to retrieve its object properties.
     * @param objectProperties A set of object properties that are retrieved.
     * @param rootObjectProperty The root of the object property that will be expanded.
     * @param reasoner The reasoner used to go across the ontology.
     */
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
     * @param mOwlQuery Class query in Manchester OWL Syntax.
     * @param requestType Type of class match to be performed. Valid values are: subclass, superclass, equivalent or all.
     * @return Set of classes retreived.
     */
    public Set<HashMap> subClassesQuery(String sClazz,OWLOntology ontology) {
        Set result = new HashSet();
        Set classes = this.preComputedSubClasses.get(sClazz);
        result.addAll(classes2info(classes, ontology))
        return(result);
    }

    /**
     * This returns the direct R-successors of a class C in O
     * class and relations are given as String-IRIs
     * @param relation It represents the object property.
     * @param sClazz The class from where R-successors will be retrieved.
     * @param ontology The ontology used to retrieve the R-successors.
     * @return A set of R-successors retrieved.
     */
    public Set relationQuery(String relation, String sClazz, OWLOntology ontology) {
        Set classes = new HashSet<>();
        def manager = ontology.getOWLOntologyManager();
        def factory = manager.getOWLDataFactory();

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
            classes.addAll(classes2info(mainResult, ontology))
        }
        return classes;
    }

    /**
     * It transforms a set of class that are instance of OWLClass in a Set of HashMap that just contain relevant information
     * such as URI, lable, reminder and so on.
     *
     * @param classes A set of classes that will be transformed into HashMaps
     * @param classes A set of classes that will be transformed into HashMaps
     * @param o The ontology that the set of classes belong to.
     * @return Set of classes transformed
     */
    public Set classes2info(Set<OWLClass> classes, OWLOntology o) {
        ArrayList result = new ArrayList<HashMap>();
        HashMap info;
        for(def c : classes) {
            info = class2info(c,o);
            if(info!=null){
                result.add(info);
            }
        }
        return result
    }

    /**
     * It transforms a class that is instance of OWLClass in a HashMap that will contain relevant information such as
     * URI, label, reminder, and so on.
     *
     * @param c The class that will be transformed.
     * @param o The ontology that the class belongs.
     * @return A HasMap that contains information such as: URI, labels, reminder and so on.
     */
    public HashMap class2info(OWLClass c, OWLOntology o){
        def info = [
                "owlClass": c.toString(),
                "classURI": c.getIRI().toString(),
                "ontologyURI": o.getOntologyID().getOntologyIRI().toString(),
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
                return(info);
            }
        }
        return(null);

    }

}
