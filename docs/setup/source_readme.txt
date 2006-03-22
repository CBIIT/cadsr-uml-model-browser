
===============================================================================
			UML Model Browser @umlmodelbrowser.version@
===============================================================================

Contains the complete application source for UMLModelBrowser @umlmodelbrowser.version@

<JBOSS_HOME> refers to Jboss home directory.
<HOME> refers to directory to which "umlmodelbrowser_src_@umlmodelbrowser.version.suffix@.zip" is unzipped.
<INSTALL_HOME> refers to directory  <HOME>/umlmodelbrowser_@umlmodelbrowser.version.suffix@

Content of  <INSTALL_HOME> directory
=============================
lib\java		- Third party libraries.
config		 	-  Property and xml files that should be in the application 
		            classpath.
config\META-INF         -  application.xml.
config\properties       -  Property files used within the application.
config\WEB-INF     	-  web module discriptors and struts config files.
src\java		-  Java source code.
\ui	            	-  The jsps,html,javascript and stylesheets.


Building the Application  
=========================

Pre-requisites
---------------
1.  JBOSS 4.0.2 installed. Available from http://www.jboss.org
2.  JDK 1.5.0_05  installed
3.  Ant 1.6.2 or above installed. Available from http://ant.apache.org/
4.  caDSR @cdebrowser.version@ repository installed. Please refer to caDSR installation document

An ear file need to be build before the application can be installed, follow
the steps below to build an ear file. An ear file is not provided with the
download because the environment properties could be different for each deployment.

 1. Update  umlbrowser.properties located in directory  <INSTALL_HOME>\config\properties
  - Update value for property CDEBROWSER_URL to point to the CDE browser URL that provides access
  to your caDSR database
  e.g., CDEBROWSER_URL=http://cdebrowser.nci.nih.gov/CDEBrowser/
              
  - Update value for property CADSR_URL to point to the caCore API remote service URL
  e.g., CADSR_URL=http://cabio.nci.nih.gov/cacore/http/remoteService

 2. Update the URLs for other CaDsr tools
   e.g., ADMIN_TOOL_URL=http://cadsr-dev.nci.nih.gov
         CURATION_TOOL_URL=http://cdecurate-dev.nci.nih.gov
         NCI_METATHESAURUS_URL=http://ncimeta.nci.nih.gov
         NCI_TERMINOLOGY_SERVER_URL=http://nciterms.nci.nih.gov
         SENTINEL_TOOL_URL=http://cadsrsentinel-dev.nci.nih.gov
         CDEBROWSER_TOOL_URL=http://cdebrowser-dev.nci.nih.gov
 
 3. Run the build script (build.xml) from  directory src/java with target "dist"
	eg. ant -f  build.xml  dist
	
 4. umlmodelbrowser.ear will be created under directory <INSTALL_HOME>/dist

Deploying Application
=====================
Pre-requisites
---------------
1.  JBOSS 4.0.2 installed. Available from http://www.jboss.org

Deployment Procedure
--------------------
1. Shutdown Jboss instance.


5. Copy umlmodelbrowser.ear from <INSTALL_HOME>/dist to 
	<JBOSS_HOME>\server\default\deploy
	
6. Startup the Jboss instance
    
7. Access the application using url http://<HOST>:<POST>/umlmodelbrowser/
    eg.
    	http://localhost:8080/umlmodelbrowser/



The source code has been compiled and tested using  JDK 1.5.0_05 , struts 1.1 and JBOSS 4.0.2
CDEBrowser @cdebrowser.version@ works with caDSR repository @cdebrowser.version@ and is not 
compatible with earlier versions of caDSR repository

