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
