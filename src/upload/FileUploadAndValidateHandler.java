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

package upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.Configuration;
import cmap.CmapConverter;
import cmap.ModelReader;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Servlet to upload and validate OWL files exported by Cmap COE
 */

//Read more: http://javarevisited.blogspot.com/2013/07/ile-upload-example-in-servlet-and-jsp-java-web-tutorial-example.html#ixzz3KqfMFZi8

@WebServlet("/FileUploadAndValidateHandler")

public class FileUploadAndValidateHandler extends HttpServlet {
	
	private final String UPLOAD_DIRECTORY = getUploadDirectory ("UPLOAD_DIRECTORY");
	private final String VALIDATE_DIRECTORY = getUploadDirectory ("VALIDATE_DIRECTORY");
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        
                        generateSESValidatedRDF(UPLOAD_DIRECTORY + File.separator + name, 
                        		VALIDATE_DIRECTORY + File.separator + name);
                    }
                }
           
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded and validated Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload and validated Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
        request.getRequestDispatcher("result.jsp").forward(request, response);
     
    }

	
	void generateSESValidatedRDF (String cmapRDFDir, String validRDFDir) {
		
		ModelReader mReader = new ModelReader();
		Model mFromCmap = mReader.readFileToModel(cmapRDFDir);
				
		CmapConverter converter = new CmapConverter(mFromCmap);
		converter.convertAndOutput(validRDFDir);
		
	}
	
	private static String getUploadDirectory (String key) {
		Configuration config = new Configuration ();
		return config.getPropertyValue(key);
	}
	

}
