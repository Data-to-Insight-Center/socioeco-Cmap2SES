package cmap;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

/***
 * It converts Cmap OWl to a valid socio-eco RDF/OWL
 * 
 * @author miao
 *
 */

public class CmapConverter {
	
//	This is the model exported from the Cmap, i.e. a single SES case/instance
	Model mFromCmap = null; 
//	This is the output, validated model
	Model mOut = null;
//	This is the ses-core ontology, an abstract ontology without SES cases
	Model mCore = null;
	
	CmapConverter () {
		
	}
	
	public CmapConverter (Model mFromCmap) {
		
		this.mFromCmap = mFromCmap;
//		read in the SESCore ontology
		mCore = SESCore.getModel();
//		instantiate the mOut model, the output rdf model
		mOut = ModelFactory.createDefaultModel();
		mOut.setNsPrefixes(NameSpace.SESInstancePrefixes);
	}
	
	/***
	 * It converts the mFromCmap to a valid and OWL&semantically meaningful model, 
	 * which can be merged with the sescore ontology. 
	 */
	public void convert () {
//		get all the triple statements in the model from Cmap (mFromCmap)
		StmtIterator iter = mFromCmap.listStatements();
		while (iter.hasNext()) {
			
			Statement stmt = iter.nextStatement(); // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property predicate = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object
			
			if (object instanceof Resource) {
				
				if (predicate.equals(SKOS.member)) {
//					e.g. a skos:member b, means ConceptGraph a has_member b
					mOut.add(subject, RDF.type, mCore.getResource(NameSpace.ns_sescore + "ConceptGraph"));
					mOut.add(subject, predicate, object);
//					The implication is that b belongs to the a LocalConcept in SESCore
					mOut.add(subject, RDF.type, mCore.getResource(NameSpace.ns_sescore + "LocalConcept"));
				} else if (predicate.equals(SKOS.broaderTransitive)) {
//					e.g. a skos:broaderTransitive, means a is broader_term of b
					if (!mOut.contains(subject, RDF.type, mCore.getResource(NameSpace.ns_sescore + "LocalConcept"))) {
//						the statement can already be added to the rdf model due the previous IF block
						mOut.add(subject, RDF.type, mCore.getResource(NameSpace.ns_sescore + "LocalConcept"));
					}	
					mOut.add(subject, predicate, object);
				} else if (predicate.equals(SKOS.narrowerTransitive)) {
//					e.g. a skos:narrowerTransitive, means a is broader_term of b
					if (!mOut.contains(subject, RDF.type, mCore.getResource(NameSpace.ns_sescore + "LocalConcept"))) {
//						the statement can already be added to the rdf model due the previous IF block
						mOut.add(subject, RDF.type, mCore.getResource(NameSpace.ns_sescore + "LocalConcept"));
					}					
					mOut.add(subject, predicate, object);
				} else if (predicate.equals(mCore.getResource(NameSpace.ns_sescore + "refers_to_concept"))) {
//					e.g. a sescore:refers_to_concept b, means a is a local version of Global_Concept b
					mOut.add(subject, predicate, object);
				} else if (predicate.equals(mCore.getResource(NameSpace.ns_sescore
						+ "described_by"))) {
					mOut.add(subject, predicate, object);
				}
				

			} else {
				// object is a literal, not doing anything for now. It's not a big issue.
			}
			
		}
		
	}
	
	public void convertAndOutput (String outfile) {
		
		convert ();
		ModelWriter.toFile(mOut, outfile);
		
	}
	
	
	public static void main (String[] args) {
		
		String owlFromCmap = "/home/miao/Documents/Software/apache-tomcat-7.0.57/webapps/data/cmapraw/ses-ostrom2014-instance-nov14.owl";
		String outfile = "/home/miao/Documents/Software/apache-tomcat-7.0.57/webapps/data/validated/ses-oneinstance-dec16";
		
		ModelReader mReader = new ModelReader();
		Model mFromCmap = mReader.readFileToModel(owlFromCmap);
		
		CmapConverter converter = new CmapConverter(mFromCmap);
		converter.convertAndOutput(outfile);
		
	}
	

}
