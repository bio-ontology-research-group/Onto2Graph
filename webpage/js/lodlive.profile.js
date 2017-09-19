$.jStorage.set('profile', {
	// parametri di connessione agli endpoint
	'connection' : {
		// base degli about dei documenti non dell'ontologia
		'Elk Reasoner 1st Algorithm (t:False)' : {
			description : {
				en : 'Graphs generated using ELK reasoner (1st Algorithm)'
			},
			useForInverseSameAs : false,
			sparql : {
				documentUri : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?concept ?property ?subject .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property',
					inverse : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?subject ?property ?concept .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property LIMIT 100',
			},
			endpoint : 'http://www.cbrc.kaust.edu.sa/onto/sparql/',
			examples : [
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_ELK_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_ELK (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_ELK_PROPERTY_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_ELK_PROPERTY (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_ELK_PROPERTIES_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_ELK_PROPERTIES (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_ELK_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GO_ELK (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_ELK_PROPERTY_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GO_ELK_PROPERTY (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_ELK_PROPERTIES_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GO_ELK_PROPERTIES (t=false)'
						},
						{ uri: 'http://aber-owl.net/AAO_60_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'AAO version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ABA-AMB_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ABA-AMB version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ADAR_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ADAR version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ADO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ADO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ADW_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ADW version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/AEO_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'AEO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/AERO_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'AERO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ANCESTRO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ANCESTRO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/APO_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'APO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ASDPTO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ASDPTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ATO_61_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ATO version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/AURA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'AURA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BAO-GPCR_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BAO-GPCR version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BCGO_11_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BCGO version 11 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BCO_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BCO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BCTEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BCTEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BDO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BFO_72_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BFO version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BILA_72_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BILA version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BIOMO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BIOMO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BIRNLEX_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BIRNLEX version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BMO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BMO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BMT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BMT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BNO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BNO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BSAO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BSAO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BSPO_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BSPO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BTO_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'BTO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CANCO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CANCO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CANONT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CANONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CAO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CAO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CARELEX_4_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CARELEX version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CARO_67_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CARO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CBO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CBO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CCON_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CCON version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CDAO_65_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CDAO version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CEPH_63_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CEPH version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CHEMBIO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CHEMBIO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CL_72_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CL version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CMO_71_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CMO version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CMPO_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CMPO version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CNO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CNO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CO-WHEAT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CO-WHEAT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/COGAT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'COGAT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/COGPO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'COGPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CPRO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CPRO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CSEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CSEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CSO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CSSO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CSSO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CTCAE_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CTCAE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CTENO_15_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'CTENO version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DCO-DEBUGIT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DCO-DEBUGIT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DDANAT_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DDANAT version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DDI_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DDI version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DDPHENO_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DDPHENO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DERMO_9_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DERMO version 9 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAB_11_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DIAB version 11 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAGONT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DIAGONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DIDEO_70_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DIDEO version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DOID_81_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DOID version 81 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DPO_21_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'DPO version 21 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECG_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ECG version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECO_80_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ECO version 80 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECP_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ECP version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECSO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ECSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAM_4_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EDAM version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAMTO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EDAMTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EDDA_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EDDA version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EFO_17_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EFO version 17 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EGO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EGO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDA_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EHDA version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EHDAA version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA2_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EHDAA2 version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ELIG_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ELIG version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAP_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EMAP version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAPA_58_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EMAPA version 58 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ENVO_82_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ENVO version 82 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EO_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EO version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EOL_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EOL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ERO_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ERO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EXACT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'EXACT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FAO_123_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FAO version 123 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-BT_8_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-BT version 8 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-CV_4_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-CV version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-DV_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-DV version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-SP_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-SP version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBBT_63_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FBBT version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBCV_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FBCV version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBDV_63_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FBDV version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBSP_75_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FBSP version 75 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FIRE_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FIRE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FIX_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FIX version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FLOPO_28_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FLOPO version 28 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FLU_63_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FLU version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FTC_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FTC version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FYPO_94_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'FYPO version 94 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GBM_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GBM version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GCO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GCO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GENE-CDS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GENE-CDS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GENO_10_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GENO version 10 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GEO_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GEO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GEOSPECIES_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GEOSPECIES version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GFO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GFO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GFVO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GFVO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GLYCO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCORDF_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GLYCORDF version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GMO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GMO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GO_48_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GO version 48 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GO-EXT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GO-EXT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GPML_43_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GPML version 43 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPD_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GRO-CPD version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPGA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'GRO-CPGA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HAO_23_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'HAO version 23 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HFO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'HFO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HINO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'HINO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HOM_70_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'HOM version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HRDO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'HRDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HSAPDV_60_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'HSAPDV version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IAO_67_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IAO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ICECI_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ICECI version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ICF_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ICF version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ICO_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ICO version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDO_65_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IDO version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOBRU_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IDOBRU version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDODEN_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IDODEN version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOMAL_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IDOMAL version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDQA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IDQA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IEV_58_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IEV version 58 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IMR_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IMR version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/INO_4_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'INO version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/INSECTH_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'INSECTH version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/INTERNANO_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'INTERNANO version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ISO-ANNOTATIONS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ISO-ANNOTATIONS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IXNO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'IXNO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/JLVO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'JLVO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LBO_4_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'LBO version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LDA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'LDA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LHN_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'LHN version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LOGGERHEAD_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'LOGGERHEAD version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LPT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'LPT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MA_65_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MA version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MAT_60_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MAT version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEDO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MEDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEGO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MEGO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEOWL_10_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MEOWL version 10 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MFMO_72_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MFMO version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MFOEM_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MFOEM version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MHC_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MHC version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MI_74_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MI version 74 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIAPA_59_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MIAPA version 59 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MICRO_14_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MICRO version 14 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIRO_77_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MIRO version 77 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MIXS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXSCV_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MIXSCV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MMO_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MMO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MMUSDV_60_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MMUSDV version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MOD_118_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MOD version 118 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MP_13_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MP version 13 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MPATH_78_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MPATH version 78 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MRO_52_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MRO version 52 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MS_83_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MS version 83 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MSO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MSV_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'MSV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NATPRO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NATPRO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NBO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NBO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NCRO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NCRO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NEMO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NEMO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NGSONTO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NGSONTO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIDM-RESULTS_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NIDM-RESULTS version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFCELL_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NIFCELL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFDYS_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NIFDYS version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFSUBCELL_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NIFSUBCELL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIGO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NIGO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIHSS_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NIHSS version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NMOSP_4_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NMOSP version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NMR_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NMR version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NONRCTO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NONRCTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NPI_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NPI version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NPO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NTDO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'NTDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OAE_82_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OAE version 82 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBA_18_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OBA version 18 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBCS_76_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OBCS version 76 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBI_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OBI version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIB_70_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OBIB version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIWS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OBIWS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBOE-SBC_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OBOE-SBC version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OCHV_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OCHV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ODNAE_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ODNAE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OFSMR_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OFSMR version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMD_5_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OGMD version 5 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMS_70_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OGMS version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OLATDV_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OLATDV version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OMP_70_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OMP version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OMRSE_67_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OMRSE version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONLIRA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ONLIRA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-CORE_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ONTODM-CORE version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-KDD_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ONTODM-KDD version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODT_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ONTODT version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OOEVV_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OOEVV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OPB_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OPB version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OPE_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OPE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OPL_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OPL version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ORDO_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ORDO version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTH_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ORTH version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTHO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ORTHO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OVAE_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'OVAE version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PAE_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PAE version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PATEST_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PATEST version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PATO_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PATO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PCO_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PCO version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PDO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PDO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PDON_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PDON version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PDUMDV_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PDUMDV version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PECO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PECO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PHARE_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PHARE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PMA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PMA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PO_65_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PO version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PORO_67_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PORO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PROTEASIX_11_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PROTEASIX version 11 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PROVO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PROVO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PSDS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PSDS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PSEUDO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PSEUDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PSIMOD_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PSIMOD version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PTO_31_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PTO version 31 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PTRANS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PTRANS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PTS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PTS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PW_74_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'PW version 74 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/QIBO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'QIBO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/QUDT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'QUDT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RB_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RB version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RCTONT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RCTONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/REPO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'REPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/REX_121_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'REX version 121 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RH-MESH_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RH-MESH version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RNAO_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RNAO version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RO_61_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RO version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ROLEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ROLEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RS_71_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RS version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RSA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'RSA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SAO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SAO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SBO_95_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SBO version 95 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SBOL_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SBOL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SCHEMA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SCHEMA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SDO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SDO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SEP_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SEP version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SIBO_96_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SIBO version 96 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SIO_15_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SIO version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SNPO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SNPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SO_72_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SO version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SOPHARM_15_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SOPHARM version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SOY_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SOY version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SPD_68_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SPD version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SPO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SPTO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SPTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SSE_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SSE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/STATO_76_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'STATO version 76 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SWO_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SWO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SYMP_69_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SYMP version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SYN_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'SYN version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TADS_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TADS version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TAO_101_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TAO version 101 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TAXRANK_61_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TAXRANK version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TEDDY_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TEDDY version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TESTOLOGY_3_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TESTOLOGY version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TGMA_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TGMA version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-CONST_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-CONST version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-MER_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-MER version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-OTHER-FACTORS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-OTHER-FACTORS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-SIGNS-AND-SYMPTS_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-SIGNS-AND-SYMPTS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TMO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TMO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TO_58_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TO version 58 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TOK_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TOK version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TRAK_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TRAK version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TRANS_60_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TRANS version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TRON_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TRON version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TTO_50_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TTO version 50 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TYPON_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'TYPON version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/UNITSONT_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'UNITSONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/UO_59_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'UO version 59 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/UPHENO_67_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'UPHENO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VARIO_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VARIO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VHOG_61_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VHOG version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VICO_2_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VICO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VIVO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO-ISF_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VIVO-ISF version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VO_51_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VO version 51 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VSAO_61_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VSAO version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VSO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VT_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VT version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VTO_19_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'VTO version 19 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-BT_13_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WB-BT version 13 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-LS_15_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WB-LS version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-PHENOTYPE_17_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WB-PHENOTYPE version 17 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WBBT_65_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WBBT version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WBLS_71_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WBLS version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WBPHENOTYPE_65_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WBPHENOTYPE version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WIKIPATHWAYS_22_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WIKIPATHWAYS version 22 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WSIO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'WSIO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/XAO_71_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'XAO version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/XCO_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'XCO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/XEO_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'XEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/YPO_64_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'YPO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ZEA_1_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ZEA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFA_66_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ZFA version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFS_62_ELK_FALSE',
						 root: 'owl:Thing',
						 label: 'ZFS version 62 ELK reasoner'
						},
			]
		},
		'Elk Reasoner 2nd Algorithm (t:True)' : {
			description : {
				en : 'Graphs generated using Syntactic analyzer'
			},
			useForInverseSameAs : false,
			sparql : {
				documentUri : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?concept ?property ?subject .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property',
					inverse : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?subject ?property ?concept .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property LIMIT 100',
			},
			endpoint : 'http://www.cbrc.kaust.edu.sa/onto/sparql/',
			examples : [
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_ELK_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_ELK (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_ELK_PROPERTY_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_ELK_PROPERTY (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_ELK_PROPERTIES_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_ELK_PROPERTIES (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_ELK_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GO_ELK (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_ELK_PROPERTY_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GO_ELK_PROPERTY (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_ELK_PROPERTIES_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GO_ELK_PROPERTIES (t=true)'
						},
						{ uri: 'http://aber-owl.net/AAO_60_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'AAO version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ABA-AMB_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ABA-AMB version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ADAR_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ADAR version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ADO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ADO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ADW_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ADW version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/AEO_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'AEO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/AERO_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'AERO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ANCESTRO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ANCESTRO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/APO_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'APO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ASDPTO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ASDPTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ATO_61_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ATO version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/AURA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'AURA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BAO-GPCR_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BAO-GPCR version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BCGO_11_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BCGO version 11 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BCO_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BCO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BCTEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BCTEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BDO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BFO_72_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BFO version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BILA_72_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BILA version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BIOMO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BIOMO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BIRNLEX_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BIRNLEX version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BMO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BMO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BMT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BMT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BNO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BNO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BSAO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BSAO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BSPO_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BSPO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/BTO_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'BTO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CANCO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CANCO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CANONT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CANONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CAO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CAO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CARELEX_4_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CARELEX version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CARO_67_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CARO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CBO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CBO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CCON_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CCON version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CDAO_65_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CDAO version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CEPH_63_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CEPH version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CHEMBIO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CHEMBIO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CL_72_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CL version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CMO_71_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CMO version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CMPO_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CMPO version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CNO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CNO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CO-WHEAT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CO-WHEAT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/COGAT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'COGAT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/COGPO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'COGPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CPRO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CPRO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CSEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CSEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CSO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CSSO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CSSO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CTCAE_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CTCAE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/CTENO_15_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'CTENO version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DCO-DEBUGIT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DCO-DEBUGIT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DDANAT_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DDANAT version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DDI_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DDI version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DDPHENO_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DDPHENO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DERMO_9_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DERMO version 9 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAB_11_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DIAB version 11 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAGONT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DIAGONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DIDEO_70_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DIDEO version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DOID_81_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DOID version 81 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/DPO_21_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'DPO version 21 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECG_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ECG version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECO_80_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ECO version 80 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECP_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ECP version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ECSO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ECSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAM_4_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EDAM version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAMTO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EDAMTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EDDA_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EDDA version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EFO_17_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EFO version 17 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EGO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EGO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDA_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EHDA version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EHDAA version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA2_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EHDAA2 version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ELIG_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ELIG version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAP_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EMAP version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAPA_58_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EMAPA version 58 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ENVO_82_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ENVO version 82 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EO_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EO version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EOL_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EOL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ERO_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ERO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/EXACT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'EXACT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FAO_123_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FAO version 123 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-BT_8_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-BT version 8 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-CV_4_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-CV version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-DV_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-DV version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-SP_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-SP version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBBT_63_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FBBT version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBCV_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FBCV version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBDV_63_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FBDV version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FBSP_75_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FBSP version 75 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FIRE_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FIRE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FIX_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FIX version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FLOPO_28_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FLOPO version 28 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FLU_63_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FLU version 63 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FTC_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FTC version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/FYPO_94_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'FYPO version 94 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GBM_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GBM version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GCO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GCO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GENE-CDS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GENE-CDS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GENO_10_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GENO version 10 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GEO_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GEO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GEOSPECIES_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GEOSPECIES version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GFO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GFO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GFVO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GFVO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GLYCO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCORDF_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GLYCORDF version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GMO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GMO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GO_48_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GO version 48 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GO-EXT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GO-EXT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GPML_43_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GPML version 43 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPD_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GRO-CPD version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPGA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'GRO-CPGA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HAO_23_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'HAO version 23 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HFO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'HFO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HINO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'HINO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HOM_70_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'HOM version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HRDO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'HRDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/HSAPDV_60_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'HSAPDV version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IAO_67_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IAO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ICECI_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ICECI version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ICF_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ICF version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ICO_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ICO version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDO_65_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IDO version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOBRU_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IDOBRU version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDODEN_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IDODEN version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOMAL_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IDOMAL version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IDQA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IDQA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IEV_58_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IEV version 58 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IMR_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IMR version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/INO_4_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'INO version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/INSECTH_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'INSECTH version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/INTERNANO_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'INTERNANO version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ISO-ANNOTATIONS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ISO-ANNOTATIONS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/IXNO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'IXNO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/JLVO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'JLVO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LBO_4_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'LBO version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LDA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'LDA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LHN_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'LHN version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LOGGERHEAD_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'LOGGERHEAD version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/LPT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'LPT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MA_65_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MA version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MAT_60_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MAT version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEDO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MEDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEGO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MEGO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MEOWL_10_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MEOWL version 10 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MFMO_72_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MFMO version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MFOEM_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MFOEM version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MHC_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MHC version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MI_74_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MI version 74 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIAPA_59_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MIAPA version 59 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MICRO_14_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MICRO version 14 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIRO_77_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MIRO version 77 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MIXS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXSCV_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MIXSCV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MMO_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MMO version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MMUSDV_60_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MMUSDV version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MOD_118_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MOD version 118 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MP_13_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MP version 13 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MPATH_78_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MPATH version 78 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MRO_52_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MRO version 52 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MS_83_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MS version 83 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MSO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/MSV_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'MSV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NATPRO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NATPRO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NBO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NBO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NCRO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NCRO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NEMO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NEMO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NGSONTO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NGSONTO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIDM-RESULTS_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NIDM-RESULTS version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFCELL_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NIFCELL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFDYS_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NIFDYS version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFSUBCELL_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NIFSUBCELL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIGO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NIGO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NIHSS_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NIHSS version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NMOSP_4_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NMOSP version 4 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NMR_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NMR version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NONRCTO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NONRCTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NPI_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NPI version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NPO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/NTDO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'NTDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OAE_82_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OAE version 82 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBA_18_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OBA version 18 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBCS_76_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OBCS version 76 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBI_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OBI version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIB_70_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OBIB version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIWS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OBIWS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OBOE-SBC_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OBOE-SBC version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OCHV_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OCHV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ODNAE_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ODNAE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OFSMR_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OFSMR version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMD_5_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OGMD version 5 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMS_70_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OGMS version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OLATDV_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OLATDV version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OMP_70_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OMP version 70 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OMRSE_67_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OMRSE version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONLIRA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ONLIRA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-CORE_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ONTODM-CORE version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-KDD_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ONTODM-KDD version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODT_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ONTODT version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OOEVV_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OOEVV version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OPB_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OPB version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OPE_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OPE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OPL_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OPL version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ORDO_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ORDO version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTH_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ORTH version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTHO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ORTHO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/OVAE_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'OVAE version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PAE_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PAE version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PATEST_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PATEST version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PATO_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PATO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PCO_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PCO version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PDO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PDO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PDON_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PDON version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PDUMDV_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PDUMDV version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PECO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PECO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PHARE_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PHARE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PMA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PMA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PO_65_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PO version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PORO_67_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PORO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PROTEASIX_11_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PROTEASIX version 11 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PROVO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PROVO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PSDS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PSDS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PSEUDO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PSEUDO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PSIMOD_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PSIMOD version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PTO_31_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PTO version 31 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PTRANS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PTRANS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PTS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PTS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/PW_74_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'PW version 74 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/QIBO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'QIBO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/QUDT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'QUDT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RB_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RB version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RCTONT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RCTONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/REPO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'REPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/REX_121_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'REX version 121 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RH-MESH_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RH-MESH version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RNAO_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RNAO version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RO_61_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RO version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ROLEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ROLEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RS_71_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RS version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/RSA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'RSA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SAO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SAO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SBO_95_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SBO version 95 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SBOL_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SBOL version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SCHEMA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SCHEMA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SDO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SDO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SEP_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SEP version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SIBO_96_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SIBO version 96 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SIO_15_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SIO version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SNPO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SNPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SO_72_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SO version 72 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SOPHARM_15_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SOPHARM version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SOY_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SOY version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SPD_68_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SPD version 68 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SPO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SPO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SPTO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SPTO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SSE_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SSE version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/STATO_76_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'STATO version 76 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SWO_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SWO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SYMP_69_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SYMP version 69 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/SYN_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'SYN version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TADS_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TADS version 62 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TAO_101_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TAO version 101 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TAXRANK_61_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TAXRANK version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TEDDY_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TEDDY version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TESTOLOGY_3_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TESTOLOGY version 3 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TGMA_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TGMA version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-CONST_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-CONST version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-MER_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-MER version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-OTHER-FACTORS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-OTHER-FACTORS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-SIGNS-AND-SYMPTS_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-SIGNS-AND-SYMPTS version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TMO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TMO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TO_58_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TO version 58 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TOK_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TOK version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TRAK_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TRAK version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TRANS_60_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TRANS version 60 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TRON_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TRON version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TTO_50_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TTO version 50 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/TYPON_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'TYPON version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/UNITSONT_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'UNITSONT version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/UO_59_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'UO version 59 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/UPHENO_67_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'UPHENO version 67 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VARIO_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VARIO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VHOG_61_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VHOG version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VICO_2_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VICO version 2 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VIVO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO-ISF_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VIVO-ISF version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VO_51_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VO version 51 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VSAO_61_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VSAO version 61 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VSO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VSO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VT_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VT version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/VTO_19_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'VTO version 19 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-BT_13_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WB-BT version 13 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-LS_15_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WB-LS version 15 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-PHENOTYPE_17_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WB-PHENOTYPE version 17 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WBBT_65_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WBBT version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WBLS_71_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WBLS version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WBPHENOTYPE_65_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WBPHENOTYPE version 65 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WIKIPATHWAYS_22_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WIKIPATHWAYS version 22 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/WSIO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'WSIO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/XAO_71_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'XAO version 71 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/XCO_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'XCO version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/XEO_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'XEO version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/YPO_64_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'YPO version 64 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ZEA_1_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ZEA version 1 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFA_66_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ZFA version 66 ELK reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFS_62_ELK_TRUE',
						 root: 'owl:Thing',
						 label: 'ZFS version 62 ELK reasoner'
						},
			]
		},
		'Syntactic Analyzer 1st Algorithm (t:False)' : {
			description : {
				en : 'Graphs generated using Syntactic analyzer'
			},
			useForInverseSameAs : false,
			sparql : {
				documentUri : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?concept ?property ?subject .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property',
					inverse : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?subject ?property ?concept .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property LIMIT 100',
			},
			endpoint : 'http://www.cbrc.kaust.edu.sa/onto/sparql/',
			examples : [
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_SR_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_SR (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_SR_PROPERTY_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_SR_PROPERTY (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_SR_PROPERTIES_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_SR_PROPERTIES (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_SR_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GO_SR (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_SR_PROPERTY_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GO_SR_PROPERTY (t=false)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_SR_PROPERTIES_FALSE',
						 root: 'owl:Thing',
						 label: '* experiment GO_SR_PROPERTIES (t=false)'
						},
						{ uri: 'http://aber-owl.net/AAO_60_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'AAO version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ABA-AMB_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ABA-AMB version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ADAR_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ADAR version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ADO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ADO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ADW_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ADW version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/AEO_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'AEO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/AERO_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'AERO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ANCESTRO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ANCESTRO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/APO_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'APO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ASDPTO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ASDPTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ATO_61_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ATO version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/AURA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'AURA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BAO-GPCR_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BAO-GPCR version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BCGO_11_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BCGO version 11 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BCO_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BCO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BCTEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BCTEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BDO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BFO_72_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BFO version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BILA_72_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BILA version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BIOMO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BIOMO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BIRNLEX_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BIRNLEX version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BMO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BMO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BMT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BMT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BNO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BNO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BSAO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BSAO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BSPO_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BSPO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BTO_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'BTO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CANCO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CANCO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CANONT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CANONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CAO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CAO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CARELEX_4_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CARELEX version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CARO_67_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CARO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CBO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CBO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CCON_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CCON version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CDAO_65_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CDAO version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CEPH_63_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CEPH version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CHEMBIO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CHEMBIO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CL_72_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CL version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CMO_71_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CMO version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CMPO_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CMPO version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CNO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CNO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CO-WHEAT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CO-WHEAT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/COGAT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'COGAT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/COGPO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'COGPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CPRO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CPRO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CSEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CSEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CSO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CSSO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CSSO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CTCAE_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CTCAE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CTENO_15_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'CTENO version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DCO-DEBUGIT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DCO-DEBUGIT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DDANAT_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DDANAT version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DDI_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DDI version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DDPHENO_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DDPHENO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DERMO_9_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DERMO version 9 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAB_11_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DIAB version 11 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAGONT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DIAGONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DIDEO_70_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DIDEO version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DOID_81_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DOID version 81 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DPO_21_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'DPO version 21 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECG_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ECG version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECO_80_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ECO version 80 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECP_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ECP version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECSO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ECSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAM_4_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EDAM version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAMTO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EDAMTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EDDA_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EDDA version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EFO_17_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EFO version 17 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EGO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EGO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDA_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EHDA version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EHDAA version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA2_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EHDAA2 version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ELIG_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ELIG version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAP_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EMAP version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAPA_58_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EMAPA version 58 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ENVO_82_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ENVO version 82 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EO_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EO version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EOL_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EOL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ERO_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ERO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EXACT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'EXACT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FAO_123_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FAO version 123 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-BT_8_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-BT version 8 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-CV_4_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-CV version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-DV_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-DV version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-SP_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FB-SP version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBBT_63_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FBBT version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBCV_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FBCV version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBDV_63_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FBDV version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBSP_75_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FBSP version 75 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FIRE_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FIRE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FIX_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FIX version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FLOPO_28_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FLOPO version 28 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FLU_63_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FLU version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FTC_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FTC version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FYPO_94_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'FYPO version 94 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GBM_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GBM version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GCO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GCO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GENE-CDS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GENE-CDS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GENO_10_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GENO version 10 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GEO_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GEO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GEOSPECIES_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GEOSPECIES version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GFO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GFO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GFVO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GFVO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GLYCO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCORDF_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GLYCORDF version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GMO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GMO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GO_48_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GO version 48 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GO-EXT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GO-EXT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GPML_43_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GPML version 43 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPD_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GRO-CPD version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPGA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'GRO-CPGA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HAO_23_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'HAO version 23 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HFO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'HFO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HINO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'HINO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HOM_70_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'HOM version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HRDO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'HRDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HSAPDV_60_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'HSAPDV version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IAO_67_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IAO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ICECI_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ICECI version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ICF_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ICF version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ICO_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ICO version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDO_65_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IDO version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOBRU_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IDOBRU version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDODEN_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IDODEN version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOMAL_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IDOMAL version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDQA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IDQA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IEV_58_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IEV version 58 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IMR_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IMR version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/INO_4_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'INO version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/INSECTH_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'INSECTH version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/INTERNANO_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'INTERNANO version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ISO-ANNOTATIONS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ISO-ANNOTATIONS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IXNO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'IXNO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/JLVO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'JLVO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LBO_4_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'LBO version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LDA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'LDA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LHN_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'LHN version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LOGGERHEAD_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'LOGGERHEAD version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LPT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'LPT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MA_65_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MA version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MAT_60_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MAT version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEDO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MEDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEGO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MEGO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEOWL_10_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MEOWL version 10 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MFMO_72_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MFMO version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MFOEM_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MFOEM version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MHC_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MHC version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MI_74_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MI version 74 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIAPA_59_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MIAPA version 59 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MICRO_14_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MICRO version 14 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIRO_77_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MIRO version 77 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MIXS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXSCV_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MIXSCV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MMO_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MMO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MMUSDV_60_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MMUSDV version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MOD_118_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MOD version 118 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MP_13_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MP version 13 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MPATH_78_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MPATH version 78 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MRO_52_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MRO version 52 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MS_83_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MS version 83 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MSO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MSV_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'MSV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NATPRO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NATPRO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NBO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NBO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NCRO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NCRO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NEMO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NEMO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NGSONTO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NGSONTO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIDM-RESULTS_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NIDM-RESULTS version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFCELL_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NIFCELL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFDYS_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NIFDYS version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFSUBCELL_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NIFSUBCELL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIGO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NIGO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIHSS_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NIHSS version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NMOSP_4_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NMOSP version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NMR_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NMR version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NONRCTO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NONRCTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NPI_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NPI version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NPO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NTDO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'NTDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OAE_82_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OAE version 82 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBA_18_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OBA version 18 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBCS_76_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OBCS version 76 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBI_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OBI version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIB_70_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OBIB version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIWS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OBIWS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBOE-SBC_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OBOE-SBC version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OCHV_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OCHV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ODNAE_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ODNAE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OFSMR_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OFSMR version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMD_5_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OGMD version 5 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMS_70_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OGMS version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OLATDV_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OLATDV version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OMP_70_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OMP version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OMRSE_67_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OMRSE version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONLIRA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ONLIRA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-CORE_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ONTODM-CORE version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-KDD_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ONTODM-KDD version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODT_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ONTODT version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OOEVV_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OOEVV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OPB_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OPB version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OPE_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OPE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OPL_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OPL version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ORDO_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ORDO version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTH_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ORTH version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTHO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ORTHO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OVAE_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'OVAE version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PAE_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PAE version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PATEST_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PATEST version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PATO_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PATO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PCO_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PCO version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PDO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PDO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PDON_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PDON version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PDUMDV_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PDUMDV version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PECO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PECO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PHARE_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PHARE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PMA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PMA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PO_65_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PO version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PORO_67_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PORO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PROTEASIX_11_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PROTEASIX version 11 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PROVO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PROVO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PSDS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PSDS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PSEUDO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PSEUDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PSIMOD_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PSIMOD version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PTO_31_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PTO version 31 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PTRANS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PTRANS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PTS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PTS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PW_74_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'PW version 74 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/QIBO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'QIBO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/QUDT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'QUDT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RB_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RB version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RCTONT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RCTONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/REPO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'REPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/REX_121_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'REX version 121 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RH-MESH_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RH-MESH version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RNAO_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RNAO version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RO_61_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RO version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ROLEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ROLEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RS_71_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RS version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RSA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'RSA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SAO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SAO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SBO_95_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SBO version 95 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SBOL_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SBOL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SCHEMA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SCHEMA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SDO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SDO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SEP_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SEP version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SIBO_96_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SIBO version 96 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SIO_15_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SIO version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SNPO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SNPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SO_72_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SO version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SOPHARM_15_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SOPHARM version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SOY_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SOY version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SPD_68_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SPD version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SPO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SPTO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SPTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SSE_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SSE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/STATO_76_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'STATO version 76 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SWO_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SWO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SYMP_69_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SYMP version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SYN_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'SYN version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TADS_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TADS version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TAO_101_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TAO version 101 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TAXRANK_61_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TAXRANK version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TEDDY_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TEDDY version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TESTOLOGY_3_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TESTOLOGY version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TGMA_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TGMA version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-CONST_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-CONST version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-MER_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-MER version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-OTHER-FACTORS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-OTHER-FACTORS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-SIGNS-AND-SYMPTS_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TM-SIGNS-AND-SYMPTS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TMO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TMO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TO_58_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TO version 58 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TOK_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TOK version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TRAK_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TRAK version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TRANS_60_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TRANS version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TRON_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TRON version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TTO_50_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TTO version 50 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TYPON_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'TYPON version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/UNITSONT_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'UNITSONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/UO_59_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'UO version 59 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/UPHENO_67_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'UPHENO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VARIO_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VARIO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VHOG_61_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VHOG version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VICO_2_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VICO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VIVO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO-ISF_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VIVO-ISF version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VO_51_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VO version 51 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VSAO_61_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VSAO version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VSO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VT_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VT version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VTO_19_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'VTO version 19 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-BT_13_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WB-BT version 13 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-LS_15_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WB-LS version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-PHENOTYPE_17_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WB-PHENOTYPE version 17 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WBBT_65_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WBBT version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WBLS_71_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WBLS version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WBPHENOTYPE_65_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WBPHENOTYPE version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WIKIPATHWAYS_22_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WIKIPATHWAYS version 22 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WSIO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'WSIO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/XAO_71_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'XAO version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/XCO_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'XCO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/XEO_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'XEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/YPO_64_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'YPO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ZEA_1_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ZEA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFA_66_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ZFA version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFS_62_SR_FALSE',
						 root: 'owl:Thing',
						 label: 'ZFS version 62 SR reasoner'
						},
			]
		},
		'Syntactic Analyzer 2nd Algorithm (t:True)' : {
			description : {
				en : 'Graphs generated using Syntactic analyzer'
			},
			useForInverseSameAs : false,
			sparql : {
				documentUri : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?concept ?property ?subject .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property',
					inverse : 'SELECT DISTINCT ?property ?relation ?object FROM <{URI}> WHERE {' +
							  '?subject <http://www.w3.org/2000/01/rdf-schema#label> "{ROOT}"@en .' +
							  '?subject ?property ?concept .' +
							  '?property <http://www.w3.org/2000/01/rdf-schema#label> ?relationLabel .'+
							  'FILTER ((langMatches(lang(?relationLabel),"en"))) .'+
							  'BIND (str(?relationLabel) as ?relation) .'+
							  '?concept <http://www.w3.org/2000/01/rdf-schema#label> ?label .' +
							  'FILTER ((langMatches(lang(?label),"en"))) .' +
							  'BIND (str(?label) as ?object) } ORDER BY ?property LIMIT 100',
			},
			endpoint : 'http://www.cbrc.kaust.edu.sa/onto/sparql/',
			examples : [
						{ uri: 'http://aber-owl.net/paper_goplus_sr_true',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_SR (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_SR_PROPERTY_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_SR_PROPERTY (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GOPLUS_SR_PROPERTIES_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GOPLUS_SR_PROPERTIES (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_SR_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GO_SR (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_SR_PROPERTY_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GO_SR_PROPERTY (t=true)'
						},
						{ uri: 'http://aber-owl.net/PAPER_GO_SR_PROPERTIES_TRUE',
						 root: 'owl:Thing',
						 label: '* experiment GO_SR_PROPERTIES (t=true)'
						},
						{ uri: 'http://aber-owl.net/AAO_60_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'AAO version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ABA-AMB_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ABA-AMB version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ADAR_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ADAR version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ADO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ADO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ADW_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ADW version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/AEO_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'AEO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/AERO_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'AERO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ANCESTRO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ANCESTRO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/APO_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'APO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ASDPTO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ASDPTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ATO_61_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ATO version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/AURA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'AURA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BAO-GPCR_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BAO-GPCR version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BCGO_11_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BCGO version 11 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BCO_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BCO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BCTEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BCTEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BDO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BFO_72_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BFO version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BILA_72_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BILA version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BIOMO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BIOMO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BIRNLEX_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BIRNLEX version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BMO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BMO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BMT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BMT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BNO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BNO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BSAO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BSAO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BSPO_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BSPO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/BTO_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'BTO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CANCO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CANCO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CANONT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CANONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CAO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CAO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CARELEX_4_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CARELEX version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CARO_67_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CARO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CBO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CBO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CCON_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CCON version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CDAO_65_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CDAO version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CEPH_63_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CEPH version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CHEMBIO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CHEMBIO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CL_72_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CL version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CMO_71_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CMO version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CMPO_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CMPO version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CNO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CNO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CO-WHEAT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CO-WHEAT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/COGAT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'COGAT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/COGPO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'COGPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CPRO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CPRO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CSEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CSEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CSO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CSSO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CSSO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CTCAE_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CTCAE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/CTENO_15_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'CTENO version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DCO-DEBUGIT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DCO-DEBUGIT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DDANAT_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DDANAT version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DDI_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DDI version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DDPHENO_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DDPHENO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DERMO_9_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DERMO version 9 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAB_11_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DIAB version 11 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DIAGONT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DIAGONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DIDEO_70_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DIDEO version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DOID_81_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DOID version 81 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/DPO_21_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'DPO version 21 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECG_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ECG version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECO_80_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ECO version 80 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECP_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ECP version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ECSO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ECSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAM_4_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EDAM version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EDAMTO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EDAMTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EDDA_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EDDA version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EFO_17_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EFO version 17 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EGO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EGO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDA_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EHDA version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EHDAA version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EHDAA2_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EHDAA2 version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ELIG_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ELIG version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAP_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EMAP version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EMAPA_58_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EMAPA version 58 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ENVO_82_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ENVO version 82 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EO_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EO version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EOL_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EOL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ERO_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ERO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/EXACT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'EXACT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FAO_123_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FAO version 123 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-BT_8_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-BT version 8 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-CV_4_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-CV version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-DV_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-DV version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FB-SP_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FB-SP version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBBT_63_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FBBT version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBCV_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FBCV version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBDV_63_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FBDV version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FBSP_75_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FBSP version 75 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FIRE_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FIRE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FIX_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FIX version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FLOPO_28_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FLOPO version 28 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FLU_63_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FLU version 63 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FTC_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FTC version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/FYPO_94_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'FYPO version 94 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GBM_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GBM version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GCO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GCO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GENE-CDS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GENE-CDS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GENO_10_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GENO version 10 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GEO_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GEO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GEOSPECIES_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GEOSPECIES version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GFO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GFO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GFVO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GFVO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GLYCO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GLYCORDF_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GLYCORDF version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GMO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GMO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GO_48_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GO version 48 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GO-EXT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GO-EXT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GPML_43_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GPML version 43 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPD_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GRO-CPD version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/GRO-CPGA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'GRO-CPGA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HAO_23_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'HAO version 23 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HFO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'HFO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HINO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'HINO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HOM_70_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'HOM version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HRDO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'HRDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/HSAPDV_60_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'HSAPDV version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IAO_67_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IAO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ICECI_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ICECI version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ICF_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ICF version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ICO_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ICO version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDO_65_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IDO version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOBRU_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IDOBRU version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDODEN_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IDODEN version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDOMAL_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IDOMAL version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IDQA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IDQA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IEV_58_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IEV version 58 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IMR_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IMR version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/INO_4_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'INO version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/INSECTH_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'INSECTH version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/INTERNANO_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'INTERNANO version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ISO-ANNOTATIONS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ISO-ANNOTATIONS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/IXNO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'IXNO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/JLVO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'JLVO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LBO_4_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'LBO version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LDA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'LDA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LHN_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'LHN version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LOGGERHEAD_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'LOGGERHEAD version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/LPT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'LPT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MA_65_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MA version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MAT_60_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MAT version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEDO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MEDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEGO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MEGO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MEOWL_10_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MEOWL version 10 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MFMO_72_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MFMO version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MFOEM_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MFOEM version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MHC_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MHC version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MI_74_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MI version 74 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIAPA_59_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MIAPA version 59 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MICRO_14_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MICRO version 14 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIRO_77_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MIRO version 77 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MIXS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MIXSCV_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MIXSCV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MMO_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MMO version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MMUSDV_60_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MMUSDV version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MOD_118_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MOD version 118 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MP_13_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MP version 13 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MPATH_78_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MPATH version 78 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MRO_52_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MRO version 52 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MS_83_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MS version 83 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MSO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/MSV_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'MSV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NATPRO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NATPRO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NBO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NBO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NCRO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NCRO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NEMO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NEMO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NGSONTO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NGSONTO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIDM-RESULTS_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NIDM-RESULTS version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFCELL_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NIFCELL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFDYS_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NIFDYS version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIFSUBCELL_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NIFSUBCELL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIGO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NIGO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NIHSS_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NIHSS version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NMOSP_4_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NMOSP version 4 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NMR_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NMR version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NONRCTO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NONRCTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NPI_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NPI version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NPO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/NTDO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'NTDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OAE_82_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OAE version 82 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBA_18_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OBA version 18 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBCS_76_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OBCS version 76 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBI_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OBI version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIB_70_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OBIB version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBIWS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OBIWS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OBOE-SBC_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OBOE-SBC version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OCHV_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OCHV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ODNAE_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ODNAE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OFSMR_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OFSMR version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMD_5_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OGMD version 5 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OGMS_70_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OGMS version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OLATDV_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OLATDV version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OMP_70_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OMP version 70 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OMRSE_67_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OMRSE version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONLIRA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ONLIRA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-CORE_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ONTODM-CORE version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODM-KDD_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ONTODM-KDD version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ONTODT_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ONTODT version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OOEVV_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OOEVV version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OPB_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OPB version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OPE_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OPE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OPL_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OPL version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ORDO_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ORDO version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTH_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ORTH version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ORTHO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ORTHO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/OVAE_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'OVAE version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PAE_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PAE version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PATEST_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PATEST version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PATO_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PATO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PCO_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PCO version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PDO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PDO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PDON_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PDON version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PDUMDV_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PDUMDV version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PECO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PECO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PHARE_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PHARE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PMA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PMA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PO_65_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PO version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PORO_67_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PORO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PROTEASIX_11_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PROTEASIX version 11 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PROVO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PROVO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PSDS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PSDS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PSEUDO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PSEUDO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PSIMOD_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PSIMOD version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PTO_31_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PTO version 31 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PTRANS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PTRANS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PTS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PTS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/PW_74_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'PW version 74 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/QIBO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'QIBO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/QUDT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'QUDT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RB_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RB version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RCTONT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RCTONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/REPO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'REPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/REX_121_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'REX version 121 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RH-MESH_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RH-MESH version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RNAO_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RNAO version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RO_61_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RO version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ROLEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ROLEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RS_71_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RS version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/RSA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'RSA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SAO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SAO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SBO_95_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SBO version 95 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SBOL_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SBOL version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SCHEMA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SCHEMA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SDO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SDO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SEP_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SEP version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SIBO_96_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SIBO version 96 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SIO_15_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SIO version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SNPO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SNPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SO_72_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SO version 72 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SOPHARM_15_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SOPHARM version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SOY_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SOY version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SPD_68_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SPD version 68 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SPO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SPO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SPTO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SPTO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SSE_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SSE version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/STATO_76_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'STATO version 76 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SWO_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SWO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SYMP_69_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SYMP version 69 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/SYN_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'SYN version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TADS_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TADS version 62 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TAO_101_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TAO version 101 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TAXRANK_61_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TAXRANK version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TEDDY_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TEDDY version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TESTOLOGY_3_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TESTOLOGY version 3 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TGMA_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TGMA version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-CONST_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-CONST version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-MER_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-MER version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-OTHER-FACTORS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-OTHER-FACTORS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TM-SIGNS-AND-SYMPTS_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TM-SIGNS-AND-SYMPTS version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TMO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TMO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TO_58_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TO version 58 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TOK_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TOK version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TRAK_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TRAK version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TRANS_60_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TRANS version 60 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TRON_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TRON version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TTO_50_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TTO version 50 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/TYPON_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'TYPON version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/UNITSONT_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'UNITSONT version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/UO_59_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'UO version 59 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/UPHENO_67_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'UPHENO version 67 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VARIO_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VARIO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VHOG_61_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VHOG version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VICO_2_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VICO version 2 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VIVO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VIVO-ISF_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VIVO-ISF version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VO_51_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VO version 51 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VSAO_61_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VSAO version 61 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VSO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VSO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VT_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VT version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/VTO_19_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'VTO version 19 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-BT_13_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WB-BT version 13 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-LS_15_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WB-LS version 15 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WB-PHENOTYPE_17_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WB-PHENOTYPE version 17 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WBBT_65_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WBBT version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WBLS_71_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WBLS version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WBPHENOTYPE_65_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WBPHENOTYPE version 65 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WIKIPATHWAYS_22_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WIKIPATHWAYS version 22 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/WSIO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'WSIO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/XAO_71_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'XAO version 71 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/XCO_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'XCO version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/XEO_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'XEO version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/YPO_64_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'YPO version 64 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ZEA_1_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ZEA version 1 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFA_66_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ZFA version 66 SR reasoner'
						},
						{ uri: 'http://aber-owl.net/ZFS_62_SR_TRUE',
						 root: 'owl:Thing',
						 label: 'ZFS version 62 SR reasoner'
						},
			]
		},
	},	
	arrows : {
		'http://www.w3.org/2002/07/owl#sameAs' : 'isSameAs',
		'http://purl.org/dc/terms/isPartOf' : 'isPartOf',
		'http://purl.org/dc/elements/1.1/type' : 'isType',
		'http://www.w3.org/1999/02/22-rdf-syntax-ns#type' : 'isType',
		'http://www.w3.org/2000/01/rdf-schema#subClassOf' : 'SubClassOf'
	},
	uriSubstitutor : [{
		findStr : 'mpii.de/yago/resource/',
		replaceStr : 'yago-knowledge.org/resource/'
	}],

	// configurazione standard per la rappresentazione di un documento
	// utilizzata nel caso manchi una specifica configurazione per la classe
	'default' : {
		sparql : {
				allClasses : 'select ?s ?p ?o from <http://aber-owl.net/DIAB> where {?s ?p ?o}'
		},
		endpoint : 'http://10.73.106.41:8890/sparql/',
		document : {
			className : 'standard',
			titleProperties : ['owl:Thing']
		}, // http://www.w3.org/2000/01/rdf-schema#label
		images : {
			properties : ['http://www.w3.org/2006/vcard/ns#photo', 'http://xmlns.com/foaf/0.1/depiction', 'http://dbpedia.org/ontology/thumbnail', 'http://dbpedia.org/property/logo', 'http://linkedgeodata.org/ontology/schemaIcon']
		},
		maps : {
			longs : ['http://www.w3.org/2003/01/geo/wgs84_pos#long'],
			lats : ['http://www.w3.org/2003/01/geo/wgs84_pos#lat'],
			points : ['http://www.georss.org/georss/point']
		},
		weblinks : {
			properties : ['http://www.w3.org/ns/dcat#accessURL', 'http://xmlns.com/foaf/0.1/mbox', 'http://rdfs.org/sioc/ns#links_to', 'http://it.dbpedia.org/property/url', 'http://data.nytimes.com/elements/search_api_query', 'http://www.w3.org/2000/01/rdf-schema#isDefinedBy', 'http://xmlns.com/foaf/0.1/page', 'http://xmlns.com/foaf/0.1/homepage', 'http://purl.org/dc/terms/isReferencedBy', 'http://purl.org/dc/elements/1.1/relation', 'http://dbpedia.org/ontology/wikiPageExternalLink', 'http://data.nytimes.com/elements/topicPage']
		}
	},

	'http://www.w3.org/2002/07/owl#Class' : {
		document : {
			className : 'Class'/*,
			 titleProperties : ['http://purl.org/dc/elements/1.1/title', 'http://www.w3.org/2000/01/rdf-schema#label']*/
		}
	},
	'http://www.w3.org/2002/07/owl#ObjectProperty' : {
		document : {
			className : 'ObjectProperty'
		}
	},
	'http://www.w3.org/2002/07/owl#Restriction' : {
		document : {
			className : 'DatatypeProperty'
		}
	},
	'http://www.w3.org/2002/07/owl#DatatypeProperty' : {
		document : {
			className : 'DatatypeProperty'
		}
	},
	'http://www.w3.org/2002/07/owl#Property' : {
		document : {
			className : 'Property'
		}
	},
	'http://data.oceandrilling.org/core/1/ODP' : {
		document : {
			titleProperties : ['expedition', 'http://data.oceandrilling.org/core/1/expedition', 'site', 'http://data.oceandrilling.org/core/1/site', 'hole', 'http://data.oceandrilling.org/core/1/hole']
		}
	},
	'http://www.w3.org/ns/locn#Address' : {
		document : {
			titleProperties : ['http://www.w3.org/ns/locn#fullAddress']
		}
	},
	'http://www.cnr.it/ontology/cnr/personale.owl#UnitaDiPersonaleInterno' : {
		document : {
			titleProperties : ['http://www.cnr.it/ontology/cnr/personale.owl#cognome', ' ', 'http://www.cnr.it/ontology/cnr/personale.owl#nome']
		}
	}

});
if (!document.lodliveVars) {
	document.lodliveVars = {};
}

$.jStorage.set('boxTemplate', '<div class="boxWrapper" id="first"><div class="box sprite"></div></div>');
$.jStorage.set('relationsLimit', 25);
$.jStorage.set('doStats', $.jStorage.get('doStats', true));
$.jStorage.set('doInverse', $.jStorage.get('doInverse', true));
$.jStorage.set('doAutoExpand', $.jStorage.get('doAutoExpand', true));
$.jStorage.set('doAutoSameas', $.jStorage.get('doAutoSameas', true));
$.jStorage.set('doCollectImages', $.jStorage.get('doCollectImages', true));
$.jStorage.set('doDrawMap', $.jStorage.get('doDrawMap', true));
$.jStorage.set('showInfoConsole', $.jStorage.get('showInfoConsole', true));

$.jStorage.set('endpoints', {
	all : 'output=json&format=application/json&timeout=0',
	arcSparql : 'output=json&jsonp=lodlive',
	sesame : 'Accept=application/sparql-results%2Bjson'
});
