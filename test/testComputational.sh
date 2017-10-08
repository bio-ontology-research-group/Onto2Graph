#!/bin/bash

#elk flag = true
echo "subclassof"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml1 -r ELK -f RDFXML -eq true -t true -nt 4
echo "subclassof part of"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml2 -r ELK -f RDFXML -eq true -op ["part of"] -t true -nt 4
echo "subclassof part of regulates"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml3 -r ELK -f RDFXML -eq true -op ["part of","regulates"] -t true -nt 4
echo "all properties"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml4 -r ELK -f RDFXML -eq true -op ["*"] -t true \ -nt 4


#sc flag = true

echo "subclassof"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_sc_rdfxml1 -r SYNTACTIC_REASONER -f RDFXML -eq true -t true -nt 4
echo "subclassof part of"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_sc_rdfxml2 -r SYNTACTIC_REASONER -f RDFXML -eq true -op ["part of"] -t true -nt 4
echo "subclassof part of regulates"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_sc_rdfxml3 -r SYNTACTIC_REASONER -f RDFXML -eq true -op ["part of","regulates"] -t true -nt 4
echo "all properties"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml4 -r SYNTACTIC_REASONER -f RDFXML -eq true -op ["*"] -t true -nt 4  		 		

###########################

#elk flag = false
echo "subclassof"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml1 -r ELK -f RDFXML -eq true -t false -nt 4
echo "subclassof part of"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml2 -r ELK -f RDFXML -eq true -op ["part of"] -t false -nt 4
echo "subclassof part of regulates"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml3 -r ELK -f RDFXML -eq true -op ["part of","regulates"] -t false -nt 4
echo "all properties"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml4 -r ELK -f RDFXML -eq true -op ["*"] -t false -nt 4

#sc flag = true

echo "subclassof"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_sc_rdfxml1 -r SYNTACTIC_REASONER -f RDFXML -eq true -t false -nt 4
echo "subclassof part of"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_sc_rdfxml2 -r SYNTACTIC_REASONER -f RDFXML -eq true -op ["part of"] -t false -nt 4
echo "subclassof part of regulates"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_sc_rdfxml3 -r SYNTACTIC_REASONER -f RDFXML -eq true -op ["part of","regulates"] -t false -nt 4 
echo "all properties"
time java -jar Onto2Graph-1.0.jar -ont GO_36.owl -out go_elk_rdfxml4 -r SYNTACTIC_REASONER -f RDFXML -eq true -op ["*"] -t false -nt 4