package gov.nih.nci.ncicb.cadsr.service.impl;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.domain.impl.ContextImpl;

import gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.impl.UMLClassMetadataImpl;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

public class UMLBrowserQueryServiceImpl implements UMLBrowserQueryService
{
   public UMLBrowserQueryServiceImpl() {
   }
   private ApplicationService service = null;
   private ApplicationServiceLocator serviceLocator = null;


  /**
   * Retrieves all contexts
   *
   * @param
   *
   * @return List of Context objects
   *
   * @throws java.lang.Exception
   */
  public List<Context> getAllContexts() throws Exception {
     DetachedCriteria contextCriteria =
       DetachedCriteria.forClass(ContextImpl.class);
     contextCriteria.addOrder(Order.asc("name"));
      ApplicationService caCoreService = getCaCoreAPIService();
     List results = caCoreService.query(contextCriteria, ContextImpl.class.getName());
    return results;
  }

/**
  public List<UmlProjectMetaData> getAllProjects() throws Exception {
     List projects = getServiceLayerQuerySvc().findAllProjects();
     return projects;
  }
 **/
 /**
   public List<UmlSubProjectMetaData> getAllSubProjects() throws Exception {
      List subprojects = getServiceLayerQuerySvc().findAllSubProjects();
      return subprojects;
   }

   public List<UmlPackageMetaData> getAllPackages() throws Exception {
      List packages = getServiceLayerQuerySvc().findAllPackages();
      return packages;
   }
**/
   public void setServiceLocator(ApplicationServiceLocator serviceLocator) {
      this.serviceLocator = serviceLocator;
   }

   public ApplicationServiceLocator getServiceLocator() {
      return serviceLocator;
   }

   protected ApplicationService getCaCoreAPIService() {
      if (service == null)
         service = serviceLocator.findCaCoreAPIService();
      return service;
   }

public List findUmlClass(){
   List resultList =null;
   UMLClassMetadata umlClass = new UMLClassMetadataImpl();
   umlClass.setName("DomainObject");
   
   try {
       resultList = getCaCoreAPIService().search(UMLClassMetadata.class, umlClass);
       System.out.println("result count: " + resultList.size());
       for (Iterator resultsIterator = resultList.iterator();
               resultsIterator.hasNext();) {
           UMLClassMetadata returnedClass = (UMLClassMetadata) resultsIterator.next();
           System.out.println("returnClass Name:" + returnedClass.getName());
          System.out.println("returnClass id:" + returnedClass.getId());
          System.out.println("project name:" + returnedClass.getProject().getLongName());
          System.out.println("Project id:" + returnedClass.getProject().getId());
          System.out.println("Package name:" + returnedClass.getUMLPackageMetadata().getName());
          System.out.println("size of metadata: " + returnedClass.getSemanticMetadataCollection().size());
          for (Iterator mdIterator = returnedClass.getSemanticMetadataCollection().iterator();
                  mdIterator.hasNext();) {
                  SemanticMetadata metaData = (SemanticMetadata) mdIterator.next();
                  System.out.println("concept Name: " + metaData.getConceptName());
          }
          
          
          
           
         
       }
   } catch (Exception e) {
       e.printStackTrace();
   }
  return resultList;
}
/**
   public UmlDomainModelQueryService getServiceLayerQuerySvc() {
      if (serviceLayerQuerySvc == null)
      serviceLayerQuerySvc = serviceLocator.findServiceLayerQueryService();
      return serviceLayerQuerySvc;
   }
**/
}
