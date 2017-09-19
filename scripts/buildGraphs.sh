#!bin/bash

#
# This script convert the ontologies that were used for the experiments that have been published in the paper.
#

module load compilers-extra
module load oracle-java/1.8.0.51
module load groovy
module load apache-groovy

ontologies=("GO_36.owl"
			"GO-PLUS_1.owl")
reasoners=("ELK"
		   "SYNTACTIC_REASONER")
transitive=("true"
		   "false")

idOntologies=("go"
			 "goplus")
idReasoners=("elk"
			 "sr")			 		   
idTransitive=("True"
			  "False")		 

for ont in ${!ontologies[*]}
do
	for r in ${!reasoners[*]}
	do
		for tr in ${!transitive[*]}
		do
 			nohup java -jar Onto2Graph.jar \
 			-ont ${ontologies[$ont]} \
 			-out outputs/${idOntologies[$ont]}_${idReasoners[$r]}_rdfxml_eqFalse_t${idTransitive[$tr]}.rdfxml \
 			-r ${reasoners[$r]} \
 			-f RDFXML \
 			-eq false \
 			-t ${transitive[$tr]} \
 			-nt 4 > outputs/${idOntologies[$ont]}_${idReasoners[$r]}_rdfxml_eqFalse_t${idTransitive[$tr]}.output &

 			nohup java -jar Onto2Graph.jar \
 			-ont ${ontologies[$ont]} \
 			-out outputs/${idOntologies[$ont]}_${idReasoners[$r]}_rdfxml_eqFalse_property_t${idTransitive[$tr]}.rdfxml \
 			-r ${reasoners[$r]} \
 			-f RDFXML \
 			-eq false \
 			-op [\"part of\"] \
 			-t ${transitive[$tr]} \
 			-nt 4 > outputs/${idOntologies[$ont]}_${idReasoners[$r]}_rdfxml_eqFalse_property_t${idTransitive[$tr]}.output &

 			nohup java -jar Onto2Graph.jar \
 			-ont ${ontologies[$ont]} \
 			-out outputs/${idOntologies[$ont]}_${idReasoners[$r]}_rdfxml_eqFalse_properties_t${idTransitive[$tr]}.rdfxml \
 			-r ${reasoners[$r]} \
 			-f RDFXML \
 			-eq false \
 			-op [\"part of\",\"regulates\"] \
 			-t ${transitive[$tr]} \
 			-nt 4 > outputs/${idOntologies[$ont]}_${idReasoners[$r]}_eqFalse_properties_t${idTransitive[$tr]}.output &
 		done	
 	done	
done
