#!/bin/bash

#
# This script aims at performing the semantic similarity analysis for each dataset across all selected organism
#(fly, worm, fish, yeast and mouse) distinguishing by the type of interaction (Physical and genetic).
#

 ontologies=("./resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_tFalse.rdfxml"
       "./resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_tTrue.rdfxml"
       "./resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_tFalse.rdfxml"
       "./resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_tTrue.rdfxml"
       "./resources/ontologies_mod/go_sr_rdfxml_eqFalse_tFalse.rdfxml"
       "./resources/ontologies_mod/go_sr_rdfxml_eqFalse_tTrue.rdfxml"
       "./resources/ontologies_mod/go_elk_rdfxml_eqFalse_tFalse.rdfxml"
       "./resources/ontologies_mod/go_elk_rdfxml_eqFalse_tTrue.rdfxml"
       "./resources/ontologies_mod/go_sr_rdfxml_eqFalse_property_tFalse.rdfxml"
       "./resources/ontologies_mod/go_sr_rdfxml_eqFalse_property_tTrue.rdfxml"
       "./resources/ontologies_mod/go_elk_rdfxml_eqFalse_property_tFalse.rdfxml"
       "./resources/ontologies_mod/go_elk_rdfxml_eqFalse_property_tTrue.rdfxml"
       "./resources/ontologies_mod/go_sr_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "./resources/ontologies_mod/go_sr_rdfxml_eqFalse_properties_tTrue.rdfxml"
       "./resources/ontologies_mod/go_elk_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "./resources/ontologies_mod/go_elk_rdfxml_eqFalse_properties_tTrue.rdfxml"
       "./resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_property_tFalse.rdfxml"
       "./resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_property_tTrue.rdfxml"
       "./resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_property_tFalse.rdfxml"
       "./resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_property_tTrue.rdfxml"
       "./resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "./resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_properties_tTruerdfxml"
       "./resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "./resources/ontologies_mod/goplus_elk_rdfxml_eaFalse_properties_tTrue.rdfxml")

 ontofiles=("goplus_sr_rdfxml_eqFalse_tFalse.rdfxml"
       "goplus_sr_rdfxml_eqFalse_tTrue.rdfxml"
       "goplus_elk_rdfxml_eqFalse_tFalse.rdfxml"
       "goplus_elk_rdfxml_eqFalse_tTrue.rdfxml"
       "go_sr_rdfxml_eqFalse_tFalse.rdfxml"
       "go_sr_rdfxml_eqFalse_tTrue.rdfxml"
       "go_elk_rdfxml_eqFalse_tFalse.rdfxml"
       "go_elk_rdfxml_eqFalse_tTrue.rdfxml"
       "go_sr_rdfxml_eqFalse_property_tFalse.rdfxml"
       "go_sr_rdfxml_eqFalse_property_tTrue.rdfxml"
       "go_elk_rdfxml_eqFalse_property_tFalse.rdfxml"
       "go_elk_rdfxml_eqFalse_property_tTrue.rdfxml"
       "go_sr_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "go_sr_rdfxml_eqFalse_properties_tTrue.rdfxml"
       "go_elk_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "go_elk_rdfxml_eqFalse_properties_tTrue.rdfxml"
       "goplus_sr_rdfxml_eqFalse_property_tFalse.rdfxml"
       "goplus_sr_rdfxml_eqFalse_property_tTrue.rdfxml"
       "goplus_elk_rdfxml_eqFalse_property_tFalse.rdfxml"
       "goplus_elk_rdfxml_eqFalse_property_tTrue.rdfxml"
       "goplus_sr_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "goplus_sr_rdfxml_eqFalse_properties_tTruerdfxml"
       "goplus_elk_rdfxml_eqFalse_properties_tFalse.rdfxml"
       "goplus_elk_rdfxml_eaFalse_properties_tTrue.rdfxml")

 geneTypeInteractions=("IPI" "IGI")
 bgTypeInteractions=("PHYSICAL" "GENETIC")

 smconfs=("SIM_GROUPWISE_DAG_GIC" "SIM_GROUPWISE_BMA")

 smconfId=("gic" "bma")

 for ont in ${!ontologies[*]}
 do
   for smconf in ${!smconfs[*]}
   do
 ##################################### GENE ANNOTATIONS
   for gaType in ${!geneTypeInteractions[*]}
   do
       echo "Creating job for ./outputs_dataset/${ontofiles[$ont]}_ALL_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}"
       # Create a job file, I overwrite it here but you could save tehm with individual names if you wanted.
       cat > job.sh << EOF
 #!/bin/bash
 #SBATCH --time=4-00:00:00
 #SBATCH --nodes=1
 #SBATCH --exclusive
 #SBATCH --output=./outputs_dataset/${ontofiles[$ont]}_ALL_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}.out
 module load compilers-extra
 module load oracle-java/1.8.0.51
 module load groovy
 module load apache-groovy
 echo "Executing... ./outputs_dataset/${ontofiles[$ont]}_ALL_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}"
 java -jar DataSetSemanticSimilarityAnalysis.jar \
    -ont \"${ontologies[$ont]}\" \
    -td \"GA\" \
    -ti \"${geneTypeInteractions[$gType]}\" \
    -out \"./outputs_dataset/${ontofiles[$ont]}_ALL_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}\" \
    -smconf \"${smconfs[$smconf]}\"
 echo "Job complete."
 EOF
       # Submit job
       echo "Submitting job..."
       if sbatch job.sh; then
          echo "...success."
          rm job.sh
        else
          # Could abort here on error if it is warranted. I'll choose to save the job file under a unique name.
          # This will give you a set of job files which can be resubmitted in case teh failure was a transient
          # issue with SLURM rather than a problem with the job script(s).
          echo "...failed. Saving job file as ${ontofiles[$ont]}_ALL_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}.job.sh"
          mv job.sh ${ontofiles[$ont]}_ALL_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}.job.sh
        fi
 done
 ################################# BIO GRID ANNOTATIONS
   for bgType in ${!bgTypeInteractions[*]}
   do
       echo "Creating job for ./outputs_dataset/${ontofiles[$ont]}_ALL_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}"
       # Create a job file, I overwrite it here but you could save tehm with individual names if you wanted.
       cat > job.sh << EOF
 #!/bin/bash
 #SBATCH --time=4-00:00:00
 #SBATCH --exclusive
 #SBATCH --output=./outputs_dataset/${ontofiles[$ont]}_ALL_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}.out
 module load compilers-extra
 module load oracle-java/1.8.0.51
 module load groovy
 module load apache-groovy
 echo "Executing... ./outputs_dataset/${ontofiles[$ont]}_ALL_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}"
 java -jar DataSetSemanticSimilarityAnalysis.jar \
    -ont \"${ontologies[$ont]}\" \
    -td \"BG\" \
    -ti \"${bgTypeInteractions[$bgType]}\" \
    -out \"./outputs_dataset/${ontofiles[$ont]}_ALL_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}\" \
    -smconf \"${smconfs[$smconf]}\"
 echo "Job complete."
 EOF
       # Submit job
       echo "Submitting job..."
       if sbatch job.sh; then
          echo "...success."
          rm job.sh
        else
          # Could abort here on error if it is warranted. I'll choose to save the job file under a unique name.
          # This will give you a set of job files which can be resubmitted in case teh failure was a transient
          # issue with SLURM rather than a problem with the job script(s).
          echo "...failed. Saving job file as ${ontofiles[$ont]}_ALL_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}.job.sh"
          mv job.sh ${ontofiles[$ont]}_ALL_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}.job.sh
        fi
     done
   done
 done