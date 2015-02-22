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
