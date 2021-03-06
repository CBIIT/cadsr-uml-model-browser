/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.service;

import gov.nih.nci.cadsr.domain.ClassificationScheme;
import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.SearchPreferences;

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
   
   public List<Context> getAllContexts(SearchPreferences searchPreferences) throws Exception;

    public List<Project> getAllProjects() throws Exception;
    
    public List<Project> getAllProjects(SearchPreferences searchPreferences) throws Exception;
    
    public List<Project> getProjectForContext(Context context) throws Exception;
    
    public List<SubProject> getAllSubProjects() throws Exception;
    
    public List<SubProject> getAllSubProjectsForProject(Project project) throws Exception ;
    
    public List<SubProject> getAllSubProjects(SearchPreferences searchPreferences) throws Exception;
    
    public List<UMLPackageMetadata> getAllPackages() throws Exception;
    
    public List<UMLPackageMetadata> getAllPackagesForSubProject(SubProject subProject) throws Exception ;
    
    public List<UMLPackageMetadata> getAllPackages(SearchPreferences searchPreferences) throws Exception;
    
    public List<UMLClassMetadata> getAllClasses() throws Exception;
    
    public List<UMLClassMetadata> getAllClasses(SearchPreferences searchPreferences) throws Exception;
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
   
   public List findUmlClass(UMLClassMetadata umlClass, SearchPreferences searchPreferences)throws Exception;
   
   public List findUmlAttributes(UMLAttributeMetadata umlAttribute);
   
   public List findUmlAttributes(UMLAttributeMetadata umlAttribute, SearchPreferences searchPreferences)throws Exception;
      
   public List<UMLPackageMetadata> getAllPackageForProject(Project project);
   
   public List<UMLClassMetadata> getClassesForContext(String contextId);
   
   public List<Project> findProject(Project project);
   public List<ClassificationScheme> findAllCSContainers() throws Exception ;
   public List findUmlClassForContainer(String csId) throws Exception;
   
}
