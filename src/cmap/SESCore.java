package cmap;

import com.hp.hpl.jena.rdf.model.Model;


/***
 * This is specifically designed for loading the SESCore ontology
 * use the getModel() method to read and get the rdf model
 * 
 * @author miao
 *
 */

public class SESCore {

	public static final Model getModel() {
		
		ModelReader mReader = new ModelReader();
		Model m = mReader.readFileToModel(FilePath.SESCORE_PATH);
		
		return m;

	}
	
//	we may need to discard the above getModel() method
//	and make the SESCore a vocabulary, like the SKOS.java method which describes the skos vocabulary
//	in a java class
	

}
