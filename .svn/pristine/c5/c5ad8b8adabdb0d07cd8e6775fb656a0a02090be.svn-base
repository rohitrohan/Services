This is an example client for use with the Manheim "BlobServerWebService" Web Services. 

This client is based on Apache Axis 1.4 by automatic generation using the service WSDL.

If you are receiving this as a distributed client example then the "src/generated-client" directory
should exist and have service related source code.  In addition the "target/classes" directory
should exist and have compiled classes.  

If you are using this example within Manheim and it is not in distributed form you can generate the 
required client source files using "maven axis-cook:genclient".  This will create the aforementioned
src and class directories (which can THEN be distributed) and then once you have the appropriate changes
run the "maven axis-cook:genclient:compile."
(Note* This project includes a "lib" folder by design such that the client can be distributed and run
without access to or use of a Maven repository.)

*NOTE* The username and password stored in source control with this example client are
fake examples and WILL NOT WORK (before the example client is distributed to users the username
and password should be changed to working credentials for the respective user).  
You must change the user:pass part of the URL in the project.properties file to generate the client 
from WSDL.  Also you must change the axisStub.setUsername and axisStub.setPassword in the TestServiceClient
class in order to use the TestServiceClient. 

The generated files includes the Axis SOAP binding stub, service locator and the generated 
schema objects for use with the service.

The client can be run from the bin/runTest.bat or bin/runTest.sh scripts
(dependent upon environment, requires JAVA_HOME to be set, also requires CWD to be project root). 
The client can also be run within an IDE by running src/generated-client/TestServiceClient 
(project and classpath files for Eclipse are included).

Normally this client is provided with additional documentation such as the Usage document
(which contains high level information and an annotated XML store example). 
and the WSDL - if this was not supplied then please be advised it is available - just ask. 


http://cvsdev1.imanheim.com/cgi-bin/cvsweb/cvsweb.cgi/AuctionECR/ECRImageProcessing/com/manheim/webservices/

