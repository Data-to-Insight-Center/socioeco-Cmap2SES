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
