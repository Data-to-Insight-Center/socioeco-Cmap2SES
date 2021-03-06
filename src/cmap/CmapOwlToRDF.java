/*
#
# Copyright 2015 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
*/

package cmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;

/***
 * The class is going to be obsolete
 * 
 * @author miao
 *
 */
public class CmapOwlToRDF {

	static String ns_sescore = "http://localhost:8080/sesonto/sescore#";
	static String ns_sesins = "http://localhost:8080/sesonto/sampleinstances#";
	static String ns_skos = "http://www.w3.org/2004/02/skos/core#";

	static void writeOutput(Model mod, String outPath) {

		try {
			FileOutputStream fout = new FileOutputStream(new File(outPath));
			mod.write(fout);
			System.out.println("Finished writing to the output owl file.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void readInputToModel (String infile) {
		
		
		
		
	}
	

	public static void main(String[] args) {
		// create an empty model
		Model modelFromCmap = ModelFactory.createDefaultModel();
		Model modelout = ModelFactory.createDefaultModel();
		Model modelCore = ModelFactory.createDefaultModel();
	    

		modelout.setNsPrefix("sescore", ns_sescore);
		modelout.setNsPrefix("sampleinstances", ns_sesins);
		modelout.setNsPrefix("skos", "http://www.w3.org/2004/02/skos/core#");

		// use the FileManager to find the input file
		String inputFileName = "/home/miao/Documents/Projects/SocioEcoInfo/CmapOntology/ses-ostrom2014-instance-nov14.owl";
		String outputFileName = "/home/miao/Documents/Projects/SocioEcoInfo/CmapOntology/ses-oneinstance-nov19";
		String sescoreDir = "/home/miao/Documents/Projects/SocioEcoInfo/CmapOntology/sescore";

		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}
		modelFromCmap.read(in, null);
		
		InputStream inCore = FileManager.get().open(sescoreDir);
		if (inCore == null) {
			throw new IllegalArgumentException("File: " + inputFileName
					+ " not found");
		}
		modelCore.read(inCore, null);
		modelCore.getResource(ns_sescore + "Concept");
		

		// list the statements in the Model
		com.hp.hpl.jena.rdf.model.StmtIterator iter = modelFromCmap.listStatements();
		// ResIterator subjects=model.listSubjects();

		// construct some OWL classes here

		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement(); // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property predicate = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object

			if (object instanceof Resource) {
				String predURI = predicate.getURI();
				if (predURI.equalsIgnoreCase(ns_skos + "member")) {
					modelout.add(subject, RDF.type, modelCore.getResource(ns_sescore + "ConceptGraph"));
					modelout.add(subject, predicate, object);

				} else if (predURI
						.equalsIgnoreCase(ns_sescore + "described_by")) {

				} else if (predURI.equalsIgnoreCase(ns_skos
						+ "broaderTransitive")) {
					modelout.add(subject, RDF.type, modelCore.getResource(ns_sescore + "Concept"));
					modelout.add(subject, predicate, object);

				} else if (predURI.equalsIgnoreCase(ns_sescore
						+ "refers_to_concept")) {
					modelout.add(subject, predicate, object);
				} else if (predURI.equalsIgnoreCase(ns_sescore
						+ "described_by")) {
					modelout.add(subject, predicate, object);
				}
				

			} else {
				// object is a literal
				System.out.println(" \"" + object.toString() + "\"");
			}
		}

		// write it to standard out
//		modelout.write(System.out);
		
		writeOutput(modelout, outputFileName);

	}

}
