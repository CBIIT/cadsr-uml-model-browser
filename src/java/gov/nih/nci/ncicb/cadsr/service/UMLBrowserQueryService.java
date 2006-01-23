package gov.nih.nci.ncicb.cadsr.service;

import gov.nih.nci.cadsr.domain.Context;

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
}
