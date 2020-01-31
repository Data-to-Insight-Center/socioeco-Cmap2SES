### Disclaimer 
<b>This code repository is no longer being actively managed by the <a target="_blank" rel="noopener noreferrer" href="https://pti.iu.edu/centers/d2i/">Data To Insight Center</a> at Indiana University Bloomington. Neither the center nor its principals assume responsibility for vulnerabilities that the code may have acquired over time.</b>

Socioeco-Cmap2SES
======
This is a socio-eco-info project product. It's designed to facilitate social-ecological system (SES) scholars to contribute SES cases in ontology format.

The SES-core ontology
---------------------------
In the project we present SES core concepts and cases using ontology language. Therefore we develop a core ontology, called "SES-core", to represent important classes and properties of SES; and scholars can contribute concrete SES cases according to the vocabulary of the SES ontology.

Using Cmap to create ontology instances
---------------------------
It is non-trivial to write the SES cases in an ontology language such as OWL and RDF, and thus we develop this Cmap2SES tool to assist people to contribute their SES cases to the ontology.

You need to first use the Cmap COE tool for drawing graphs, which can then be converted to an ontology file (OWL) in Cmap COE
Cmap COE can be downloaded at http://cmapdownload.ihmc.us/coe/Web_InstallersV5.0/install.htm
Manual available at http://www.ihmc.us/sandbox/groups/coe/wiki/welcome/attachments/d2a1b/COEmanual06.pdf?sessionID=bdf468bcd250a687fdfe1de90929f86fd4469534

You can draw a graph of nodes and edges to reflect the objects and relations in an SES case.

You are encouraged to use the ontology template we provide to make use the vocabulary in the ontology. 

Using Cmap2SES for validating and converting an ontology instances
---------------------------
After finishing drawing the graph, in Cmap COE export the graph to an OWL file.

However, the OWL file is usually not semantically compatible with the abstract SES ontology, so this servlet helps validate the OWL file to an RDF compatible with the SES ontology. It provides a webpage to let users upload the OWL file and then validate and convert it to an SES-ontology compatible RDF. 
 
The service is a servlet running under Apache Tomcat 7. In your development environment, you need to have an Apache Tomcat 7 running.

Setting uploading and storing directories
Use the WebContent/WEB-INF/config.xml to configure the directory for the uploaded OWL file and the validated file
