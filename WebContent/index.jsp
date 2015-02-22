<!-- 
Read more: http://javarevisited.blogspot.com/2013/07/ile-upload-example-in-servlet-and-jsp-java-web-tutorial-example.html#ixzz3KqoAjks9
- -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
    </head>
 
    <body> 
        <div>
            <!-- - not in use now
            <h3> Choose File to Upload in Server </h3>
            <form action="FileUploadHandler" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="Upload" />
            </form>
             -->
             
            <h3> OWL Uploading and Validating</h3>
            <form action="FileUploadAndValidateHandler" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="Upload & Validate" />
            </form>           
        </div>
      
    </body>
</html>


