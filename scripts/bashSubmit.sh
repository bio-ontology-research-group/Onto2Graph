#!/bin/bash

#
#This script aims at performing the semantic similarity analysis by using both databases
#Gene Annotations and BioGrid. The analysis is performed over each organism (fly, worm,
#fish, yeast and mouse) and each type of interaction (Genetic and Physical).
#

ontologies=("resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_tFalse.rdfxml"
      "resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_tTrue.rdfxml"
      "resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_tFalse.rdfxml"
      "resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_tTrue.rdfxml")
      "resources/ontologies_mod/go_sr_rdfxml_eqFalse_tFalse.rdfxml"
      "resources/ontologies_mod/go_sr_rdfxml_eqFalse_tTrue.rdfxml"
      "resources/ontologies_mod/go_elk_rdfxml_eqFalse_tFalse.rdfxml"
      "resources/ontologies_mod/go_elk_rdfxml_eqFalse_tTrue.rdfxml"
      "resources/ontologies_mod/go_sr_rdfxml_eqFalse_property_tFalse.rdfxml"
      "resources/ontologies_mod/go_sr_rdfxml_eqFalse_property_tTrue.rdfxml"
      "resources/ontologies_mod/go_elk_rdfxml_eqFalse_property_tFalse.rdfxml"
      "resources/ontologies_mod/go_elk_rdfxml_eqFalse_property_tTrue.rdfxml"
      "resources/ontologies_mod/go_sr_rdfxml_eqFalse_properties_tFalse.rdfxml"
      "resources/ontologies_mod/go_sr_rdfxml_eqFalse_properties_tTrue.rdfxml"
      "resources/ontologies_mod/go_elk_rdfxml_eqFalse_properties_tFalse.rdfxml"
      "resources/ontologies_mod/go_elk_rdfxml_eqFalse_properties_tTrue.rdfxml"
      "resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_property_tFalse.rdfxml"
      "resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_property_tTrue.rdfxml"
      "resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_property_tFalse.rdfxml"
      "resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_property_tTrue.rdfxml"
      "resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_properties_tFalse.rdfxml"
      "resources/ontologies_mod/goplus_sr_rdfxml_eqFalse_properties_tTruerdfxml"
      "resources/ontologies_mod/goplus_elk_rdfxml_eqFalse_properties_tFalse.rdfxml"
      "resources/ontologies_mod/goplus_elk_rdfxml_eaFalse_properties_tTrue.rdfxml")

ontofiles=("goplus_sr_rdfxml_eqFalse_tFalse.rdfxml"
      "goplus_sr_rdfxml_eqFalse_tTrue.rdfxml"
      "goplus_elk_rdfxml_eqFalse_tFalse.rdfxml"
      "goplus_elk_rdfxml_eqFalse_tTrue.rdfxml")
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


annotations=("resources/gene_association/fish/gene_association.zfin"
	     "resources/gene_association/fly/gene_association.fb.gz.txt"
	     "resources/gene_association/mouse/gene_association.mgi"
	     "resources/gene_association/worm/gene_association.wb"
             "resources/gene_association/yeast/gene_association.sgd")

organisms=("fish" "fly" "mouse" "worm" "yeast")

geneInteractions=("resources/gene_association/fish/gene_association.zfin"
	              "resources/gene_association/fly/gene_association.fb.gz.txt"
	              "resources/gene_association/mouse/gene_association.mgi"
	              "resources/gene_association/worm/gene_association.wb"
                  "resources/gene_association/yeast/gene_association.sgd")

bgInteractions=("resources/biogrid/BIOGRID-ORGANISM-Danio_rerio-3.4.130.tab2.txt"
	            "resources/biogrid/BIOGRID-ORGANISM-Drosophila_melanogaster-3.4.130.tab2.txt"
	            "resources/biogrid/BIOGRID-ORGANISM-Mus_musculus-3.4.130.tab2.txt"
	            "resources/biogrid/BIOGRID-ORGANISM-Caenorhabditis_elegans-3.4.130.tab2.txt"
                "resources/biogrid/BIOGRID-ORGANISM-Saccharomyces_cerevisiae_S288c-3.4.130.tab2.txt")

smconfs=("SIM_GROUPWISE_DAG_GIC" "SIM_GROUPWISE_BMA")

smconfId=("gic" "bma")

geneTypeInteractions=("IPI" "IGI")
bgTypeInteractions=("PHYSICAL" "GENETIC")

for ont in ${!ontologies[*]}
do
  for ann in ${!annotations[*]}
  do
  for smconf in ${!smconfs[*]}
  do
##################################### GENE ANNOTATIONS

  for gaType in ${!geneTypeInteractions[*]}
  do
      #echo "Creating job for ./outputs/${ontofiles[$ont]}_${organisms[$ann]}_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}"
      # Create a job file, I overwrite it here but you could save tehm with individual names if you wanted.
      cat > job.sh << EOF
#!/bin/bash
#SBATCH --time=06:00:00
#SBATCH --nodes=1
#SBATCH --exclusive
#SBATCH --output=./outputs/${ontofiles[$ont]}_${organisms[$ann]}_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}.out
module load compilers-extra
module load oracle-java/1.8.0.51
module load groovy
module load apache-groovy
echo "Executing... ./outputs/${ontofiles[$ont]}_${organisms[$ann]}_GA_${geneTypeInteractions[$gaType]_${smconfId[$smconf]}"
java -jar SemanticSimilarityAnalysis.jar \
   -ont \"${ontologies[$ont]}\" \
   -ann \"${annotations[$ann]}\" \
   -i \"${geneInteractions[$ann]}\" \
   -td \"GA\" \
   -ti \"${geneTypeInteractions[$gaType]}\" \
   -out \"./outputs/${ontofiles[$ont]}_${organisms[$ann]}_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}\" \
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
         echo "...failed. Saving job file as ${ontofiles[$ont]}_${organisms[$ann]}_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}.job.sh"
         mv job.sh ${ontofiles[$ont]}_${organisms[$ann]}_GA_${geneTypeInteractions[$gaType]}_${smconfId[$smconf]}.job.sh
       fi
  done
################################# BIO GRID ANNOTATIONS

  for bgType in ${!bgTypeInteractions[*]}
  do
      echo "Creating job for ./outputs/${ontofiles[$ont]}_${organisms[$ann]}_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}"
      # Create a job file, I overwrite it here but you could save tehm with individual names if you wanted.
      cat > job.sh << EOF
#!/bin/bash
#SBATCH --time=06:00:00
#SBATCH --nodes=1
#SBATCH --exclusive
#SBATCH --output=./outputs/${ontofiles[$ont]}_${organisms[$ann]}_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}.out
module load compilers-extra
module load oracle-java/1.8.0.51
module load groovy
module load apache-groovy
echo "Executing... ./outputs/${ontofiles[$ont]}_${organisms[$ann]}_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}"
java -jar SemanticSimilarityAnalysis.jar \
   -ont \"${ontologies[$ont]}\" \
   -ann \"${annotations[$ann]}\" \
   -i \"${bgInteractions[$ann]}\" \
   -td \"BG\" \
   -ti \"${bgTypeInteractions[$bgType]}\" \
   -out \"./outputs/${ontofiles[$ont]}_${organisms[$ann]}_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}\" \
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
         echo "...failed. Saving job file as ${ontofiles[$ont]}_${organisms[$ann]}_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}.job.sh"
         mv job.sh ${ontofiles[$ont]}_${organisms[$ann]}_BG_${bgTypeInteractions[$bgType]}_${smconfId[$smconf]}.job.sh
       fi
    done
    done
  done
done