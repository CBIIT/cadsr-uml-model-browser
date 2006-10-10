package gov.nih.nci.ncicb.cadsr.service;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;

import java.util.List;


public interface UMLBrowserQueryService {
   /**
    * Retrieves all contexts
    * 
    * @param
    * 
    * @return List of Context objects
    * 
    * @throws Exception
    */
   public List<Context> getAllContexts() throws Exception;

    public List<Project> getAllProjects() throws Exception;
    public List<Project> getProjectForContext(Context context) throws Exception;
    
    public List<SubProject> getAllSubProjects() throws Exception;
    
    public List<UMLPackageMetadata> getAllPackages() throws Exception;
    
    public List<UMLClassMetadata> getAllClasses() throws Exception;
   /**
    * public List<UmlSubProjectMetaData> getAllSubProjects() throws Exception {
    * List subprojects = getServiceLayerQuerySvc().findAllSubProjects();
    * return subprojects;
    * }
    * 
    * public List<UmlPackageMetaData> getAllPackages() throws Exception {
    * List packages = getServiceLayerQuerySvc().findAllPackages();
    * return packages;
    * }
    */
   public void setServiceLocator(ApplicationServiceLocator serviceLocator);

   public ApplicationServiceLocator getServiceLocator();
   
   public List findUmlClass(UMLClassMetadata umlClass);
   
   public List findUmlAttributes(UMLAttributeMetadata umlAttribute);
   public List<UMLPackageMetadata> getAllPackageForProject(Project project);
   public List<UMLClassMetadata> getClassesForContext(String contextId);
   
   public List<Project> findProject(Project project);
   
}
