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
