package cmap;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/***
 * The class reads an .RDF or .OWL file, into a Model in the Jena package
 * 
 * 
 */

public class ModelReader {
	
	public ModelReader () {
		
	}

	public Model readFileToModel(String filepath) {

		Model m = ModelFactory.createDefaultModel();

		InputStream in = FileManager.get().open(filepath);
		if (in == null) {
			throw new IllegalArgumentException("File: " + filepath
					+ " not found");
		}
		m.read(in, null);

		return m;

	}

	public static void main(String[] args) {

		String infile = "/home/miao/Documents/Projects/SocioEcoInfo/CmapOntology/sescore";

		ModelReader mReader = new ModelReader();
		Model m = mReader.readFileToModel(infile);

		m.add(m.createResource(NameSpace.ns_sescore + "sub"),
				m.createProperty(NameSpace.ns_sescore + "pred"),
				m.createResource(NameSpace.ns_sescore + "obj"));
		
		m.add(m.createResource(NameSpace.ns_sescore + "sub"),
				m.createProperty(NameSpace.ns_sescore + "pred"),
				m.createResource(NameSpace.ns_sescore + "obj"));
		
		m.write(System.out);

	}

}
