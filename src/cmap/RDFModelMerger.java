package cmap;

import java.io.File;

import com.hp.hpl.jena.rdf.model.Model;

/***
 * Given a set of RDF files and owl files, in the same folder
 * this will read into all the files into one model
 * 
 * !!!!!!!!!!!!!!!!
 * It would be better to output an OWL model here
 * 
 * @author miao
 *
 */

public class RDFModelMerger {
	
	RDFModelMerger () {
		
	}
	
	public Model readInModel (String infolder) {
		
		String[] filenames = new File(infolder).list();
		Model m = SESCore.getModel();
		
		ModelReader mReader = new ModelReader();
				
		for (String filename : filenames) {
			
			Model mFromFile = mReader.readFileToModel(infolder + File.separator + filename);						
			m.add(mFromFile);
			
		}
		
		return m;
	}
	
	public static void main (String[] args) {
		
		String infolder = "onto/instances/";
		RDFModelMerger modelMerger = new RDFModelMerger ();
		
		Model m = modelMerger.readInModel(infolder); 
		m.write(System.out);
		
	}
	

}
