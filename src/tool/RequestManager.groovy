package tool

import groovyx.gpars.GParsPool
import org.apache.jena.riot.web.LangTag
import org.semanticweb.owlapi.model.*
import org.semanticweb.owlapi.reasoner.BufferingMode
import org.semanticweb.owlapi.reasoner.NodeSet
import org.semanticweb.owlapi.reasoner.OWLReasoner
import org.semanticweb.owlapi.reasoner.SimpleConfiguration
import org.semanticweb.owlapi.reasoner.impl.OWLClassNodeSet
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner
import org.semanticweb.owlapi.search.EntitySearcher
import show.ProgressBar

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

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
     * List of computed subclasses.
     */
    private ConcurrentHashMap<String,Set> preComputedSubClasses = null;

    /**
     * List of equivalent classes that have not been included.
     *
     */
    private ConcurrentHashMap<String,String> equivalentList;

    /**
     * This flag controls whether the graph will contain equivalent classes or not.
     */
    private boolean equivalentClasses=true;

    /*
     * An instance of ProgressBar to show the progress of the execution.
     */
    private ProgressBar progressBar;
    /**
     * Private constructor
     */
    private RequestManager(boolean equivalentClasses){
        progressBar = new ProgressBar();
        preComputedSubClasses = new ConcurrentHashMap<String,HashSet>();
        this.equivalentClasses = equivalentClasses;
        if(!equivalentClasses){
            equivalentList = new ConcurrentHashMap<String,String>();
        }
    }

    /**
     * It computes the subclases from an ontology given.
     * @param ontology The ontology used by the reasoner to compute the subclases.
     * @param reasoner The reasoner used to infer subclasses.
     * @param arrayProperties The object properties of the ontology that will be used during the compute process.
     */
    public void computedSemanticSubClasses(OWLOntology ontology,List<OWLReasoner> reasoners, String[] arrayProperties,int nThreads){
        //The OWL:Thing class is contained in the OWL language itself, that is why we have to be sure that the
        // axiom has been included.
        HashSet<OWLClass> classes = ontology.getClassesInSignature(false);
        ArrayList<String> properties = null;
        //We convert the two list in synchronizedlist for being accessible for different threads at the same time.
        if(arrayProperties!=null) {
            properties = Collections.synchronizedList(new ArrayList<String>(arrayProperties.toList()));
        }
        reasoners = Collections.synchronizedList(reasoners);

        GParsPool.withPool(nThreads) {
            int classesCounter = classes.size();
            int reasonersCounter = reasoners.size();
            AtomicInteger classesIndex = new AtomicInteger(0);

            classes.eachWithIndexParallel { clazz, index ->
                progressBar.printProgressBar((int) Math.round((classesIndex.getAndIncrement() * 100) / (classesCounter)),"precomputing classes...");

                OWLReasoner reasoner = reasoners.get(index % reasonersCounter);

                OWLDataFactory factory = reasoner.getRootOntology().getOWLOntologyManager().getOWLDataFactory();
                OWLClass nothing = factory.getOWLNothing();
                OWLClass thing = factory.getOWLClass(IRI.create(ontology.getOntologyID().getOntologyIRI() + "/owl:Thing"))

                //we check if is a top class
                //We check if classes is related to owlThing to include this relationship.
                Set<OWLClass> superClasses = null;
                synchronized (reasoner) {
                    superClasses = reasoner.getSuperClasses(clazz, true).getFlattened();
                }
                if ((superClasses != null) && (superClasses.size() == 1) && (superClasses.contains(factory.getOWLThing()))) {
                    HashSet<OWLClass> subClasses;
                    if (preComputedSubClasses.containsKey(thing.toString())) {
                        subClasses = preComputedSubClasses.get(thing.toString());
                    } else {
                        //subClasses = new HashSet<OWLClass>()
                        subClasses = Collections.synchronizedSet(new HashSet<OWLClass>());
                    }
                    subClasses.add(clazz);
                    preComputedSubClasses.put(thing.toString(), subClasses)
                }

                if (!equivalentClasses) {
                    Set<OWLClass> equivalentEntities = null;
                    synchronized (reasoner) {
                        equivalentEntities = reasoner.getEquivalentClasses(clazz).getEntities();
                    }
                    equivalentEntities.remove(nothing);
                    equivalentEntities.each { entity ->
                        if (entity != clazz) {
                            equivalentList.put(entity.toString(), clazz);
                        }
                    }
                }
                Set<OWLClass> subClasses=null;
                synchronized (reasoner) {
                    subClasses = reasoner.getSubClasses(clazz, true).getFlattened();
                }

                if ((subClasses != null) && (!subClasses.isEmpty())) {
                    preComputedSubClasses.put(clazz.toString(), subClasses);
                }
                if ((properties != null) && (!properties.isEmpty())) {
                    for (String property : properties) {
                        if (property != null) {
                            OWLObjectProperty objectProperty = factory.getOWLObjectProperty(IRI.create(property));
                            OWLObjectSomeValuesFrom query = factory.getOWLObjectSomeValuesFrom(objectProperty, clazz);

                            //NodeSet<OWLClass> nodeSubClassesProperty = reasoner.getSubClasses(query, true);
                            if (!equivalentClasses) {
                                Set<OWLClass> equivalentPropEntities = null;
                                synchronized (reasoner){
                                    equivalentPropEntities = reasoner.getEquivalentClasses(query).getEntities();
                                }
                                equivalentPropEntities.remove(nothing);
                                equivalentPropEntities.each { entity ->
                                    if (entity != clazz) {
                                        equivalentList.put(entity.toString(), clazz);
                                    }
                                }
                            }

                            Set<OWLClass> subClassesProperty =null;
                            synchronized (reasoner) {
                                subClassesProperty = reasoner.getSubClasses(query, true).getFlattened();
                            }
                            if ((subClassesProperty != null) && (!subClassesProperty.isEmpty())) {
                                preComputedSubClasses.put(clazz.toString() + property, subClassesProperty);
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * It computes the subclases using the axioms of the ontology given.
     * @param ontology The ontology used by the reasoner to compute the subclases.
     * @param properties The object properties of the ontology that will be used during the compute process.
     */
    public void computedSyntacticSubClasses(OWLOntology ontology,String[] properties,int nThreads){
        //NOTE THAT THE DEPRECATED CLASSES ARE NOT INLCLUDED.
        //The OWL:Thing class is contained in the OWL language itself, that is why we have to be sure that the
        // axiom has been included.
        Set axioms = ontology.getAxioms();
        OWLDataFactory factory = ontology.getOWLOntologyManager().getOWLDataFactory();
        OWLClass thing = factory.getOWLClass(IRI.create(ontology.getOntologyID().getOntologyIRI()+"/owl:Thing"))
        int axiomsCounter = axioms.size();
        AtomicInteger classesIndex = new AtomicInteger(0);

        nThreads=2;

        GParsPool.withPool(nThreads) {
            axioms.eachWithIndexParallel { OWLAxiom axiom,axiomsIndex ->
            //axioms.eachWithIndex{ OWLAxiom axiom,axiomsIndex ->
                /*if(axiom.getAxiomType()==AxiomType.EQUIVALENT_CLASSES ){
                    System.out.println(axiom.toString());
                }*/
                /*if(axiom.getAxiomType()==AxiomType.DECLARATION){
                    OWLDeclarationAxiom decAxiom = (OWLDeclarationAxiom)axiom;
                    if(decAxiom.getEntity().isOWLClass()){
                        counter++;
                    }
                }*/
                progressBar.printProgressBar((int) Math.round((classesIndex.getAndIncrement() * 100) / (axiomsCounter)),"precomputing axioms...");
                if (axiom.getAxiomType() == AxiomType.SUBCLASS_OF) {
                    OWLSubClassOfAxiom subAxiom = (OWLSubClassOfAxiom) axiom;
                    OWLClass superClass = null;
                    OWLClass subClass = null;
                    //We avoid OWLObjectUnionOf, OWLObjectIntersection and OWLObjectEnumeration axioms.
                    if (subAxiom.getSubClass().getClassExpressionType() == ClassExpressionType.OWL_CLASS) {
                        subClass = (OWLClass) subAxiom.getSubClass();
                    }
                    if(subClass!=null) {
                        if (subAxiom.getSuperClass().getClassExpressionType() == ClassExpressionType.OWL_CLASS) {
                            superClass = (OWLClass) subAxiom.getSuperClass();
                            Set<OWLClass> set;
                            if (preComputedSubClasses.get(superClass.toString()) != null) {
                                set = preComputedSubClasses.get(superClass.toString());
                            } else {
                                //set = new HashSet<OWLClass>();
                                set = Collections.synchronizedSet(new HashSet<OWLClass>());
                            }
                            set.add(subClass);
                            preComputedSubClasses.put(superClass.toString(), set);

                        } else if (subAxiom.getSuperClass().getClassExpressionType() == ClassExpressionType.OBJECT_SOME_VALUES_FROM) {
                            OWLObjectSomeValuesFrom someAxiom = (OWLObjectSomeValuesFrom) subAxiom.getSuperClass();
                            //We avoid OWLObjectUnionOf, OWLObjectIntersection and OWLObjectEnumeration
                            if (someAxiom.getFiller() instanceof OWLClass) {
                                superClass = someAxiom.getFiller();
                                properties.each { property ->
                                    if (property == someAxiom.getProperty().getNamedProperty().getIRI().toString()) {
                                        HashSet<OWLClass> set;
                                        if (preComputedSubClasses.get(superClass.toString() + property) != null) {
                                            set = preComputedSubClasses.get(superClass.toString() + property);
                                        } else {
                                            //set = new HashSet<OWLClass>();
                                            set = Collections.synchronizedSet(new HashSet<OWLClass>());
                                        }
                                        set.add(subClass);
                                        preComputedSubClasses.put(superClass.toString() + property, set);
                                    }
                                }
                            }
                        }
                    }
                    if ((superClass!=null)&&(superClass.getSuperClasses(ontology).isEmpty())) {
                        HashSet<OWLClass> subClasses;
                        if (preComputedSubClasses.containsKey(thing.toString())) {
                            subClasses = preComputedSubClasses.get(thing.toString());
                        } else {
                            //subClasses = new HashSet<OWLClass>()
                            subClasses = Collections.synchronizedSet(new HashSet<OWLClass>());
                        }
                        subClasses.add(superClass);
                        preComputedSubClasses.put(thing.toString(), subClasses);
                    }
                }
            }
        }
    }

    public void serializeEquivalentClassesList(String fileOutPut){
        if((equivalentList!=null)&&(!equivalentList.isEmpty())){
            System.out.println("Printing the equivalent classes...");
            BufferedWriter output;
            try{
                output = new BufferedWriter(new FileWriter(fileOutPut));
                output.append("Equivalent entity\t\tRepresentative entity\n");
                equivalentList.keySet().each { key ->
                    OWLClass eClass = equivalentList.get(key);
                    output.append(key.toString() + "\t\t"+eClass.toString()+"\n");
                }
            } catch ( IOException e ) {
                System.out.println("There was an error: "+e.getMessage());
            } finally {
                if ( output != null ) {
                    output.close();
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
        if(!equivalentClasses){
            if(equivalentList.containsKey(c)){
                c = equivalentList.get(c);
            }
        }
        def info = [
                "owlClass": c.toString(),
                "classURI": c.getIRI().toString(),
                "ontologyURI": o.getOntologyID().getOntologyIRI().toString(),
                "remainder": c.getIRI().getFragment(),
                "label": null,
                "annotations" :[],
                "definition": null,
                "deprecated": false
        ];

        for (OWLAnnotation annotation : EntitySearcher.getAnnotations(c, o)) {
            if(annotation.isDeprecatedIRIAnnotation()) {
                info['deprecated'] = true;
            }
            if(annotation.getValue() instanceof IRI){
                IRI propIri = annotation.getProperty().getIRI();
                IRI iri = (IRI)annotation.getValue();
                info['annotations'].add([propIri.toString(),iri.toString()])
            }else if(annotation.getValue() instanceof OWLLiteral) {
                OWLLiteral val = (OWLLiteral) annotation.getValue();
                if(annotation.getProperty().isLabel()){
                    info['label'] = val.getLiteral();
                }
                IRI propIri = annotation.getProperty().getIRI();
                //we control the misdefined languages.
                if ((!val.getLang().isEmpty())&&(LangTag.parse(val.getLang())!=null)){
                    info['annotations'].add([propIri.toString(), val.getLiteral()+"@"+val.getLang()]);
                } else {
                    info['annotations'].add([propIri.toString(), val.getLiteral()]);
                }
            }
        }
        if (!info['deprecated']) {
            if (info['label'] != null) {
                return(info);
            }
            //To include the owlThing node in the graph.
            if(info["remainder"] == "owl:Thing"){
                info['label'] = "owl:Thing";
                return(info);
            }
        }
        return(null);

    }

}
