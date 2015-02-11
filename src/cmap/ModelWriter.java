package cmap;

import java.io.File;
import java.io.FileOutputStream;

import com.hp.hpl.jena.rdf.model.Model;


/***
 * Given an RDF model, it outputs the model an output file
 * in a serialized format such as RDF
 * 
 * @author miao
 *
 */
public class ModelWriter {
	
	public static void toFile (Model m, String outfile) {
		
		try {
			FileOutputStream fout = new FileOutputStream(new File(outfile));
			m.write(fout);
			System.out.println("Finished writing to the output owl file.");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}
	

}
