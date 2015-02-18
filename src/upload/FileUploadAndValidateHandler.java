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
 * Servlet to handle File upload request from Client
 * Code adopted from @author Javin Paul
 */

//Read more: http://javarevisited.blogspot.com/2013/07/ile-upload-example-in-servlet-and-jsp-java-web-tutorial-example.html#ixzz3KqfMFZi8

@WebServlet("/FileUploadAndValidateHandler")

public class FileUploadAndValidateHandler extends HttpServlet {
	
//	private final String UPLOAD_DIRECTORY = "/home/miao/Documents/Software/apache-tomcat-7.0.57/webapps/data/cmapraw";
//	private final String VALIDATE_DIRECTORY = "/home/miao/Documents/Software/apache-tomcat-7.0.57/webapps/data/validated";
	private final String UPLOAD_DIRECTORY = getUploadDirectory ("UPLOAD_DIRECTORY");
	private final String VALIDATW_DIRECTORY = getUploadDirectory ("VALIDATE_DIRECTORY");
	
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
		config.getPropertyValue(key);
	}
	

}
