#!/bin/bash

#
#This scripts convert ontologies obtained from aberowl repository into graphs.
#

reasoners=("ELK"
		   "SYNTACTIC_REASONER")
idReasoners=("elk"
			 "sr")

ontoPath="./onts/*.ont"

for file in $ontoPath;
do
	name=${file%.*}
	name=${name##*/}
	for reasoner in ${!reasoners[*]}
	do
		cat > job.sh << EOF
#!/bin/bash
#SBATCH --time=20-00:00:00
#SBATCH --nodes=1
#SBATCH --exclusive
#SBATCH --output=./outputs/${name}_${reasoners[$reasoner]}.out
module load compilers-extra
module load oracle-java/1.8.0.51
module load groovy
module load apache-groovy
echo "Executing... ./outputs/${name}_${reasoners[$reasoner]}.out"
java -jar Onto2Graph.jar \
   -ont \"${file}\" \
   -out \"./graphs/${name}_${idReasoners[$reasoner]}.rdfxml\" \
   -r ${reasoners[$reasoner]} \
   -f RDFXML \
   -eq true \
   -op \"[*]\" \
   -nt 64
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
	        echo "...failed. Saving job file as ${file}_${reasoners[$reasoner]}.job.sh"
	        mv job.sh ./fails/${name}_${reasoners[$reasoner]}.job.sh
	    fi
	done
done