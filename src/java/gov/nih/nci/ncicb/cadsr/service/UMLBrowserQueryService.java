package gov.nih.nci.ncicb.cadsr.service;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.domain.impl.ContextImpl;
import gov.nih.nci.cadsr.service.UmlDomainModelQueryService;
import gov.nih.nci.cadsr.uml.UmlPackageMetaData;
import gov.nih.nci.cadsr.uml.UmlProjectMetaData;
import gov.nih.nci.cadsr.uml.UmlSubProjectMetaData;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

public class UMLBrowserQueryService
{
   public UMLBrowserQueryService() {
   }
   private ApplicationService service = null;
   private ApplicationServiceLocator serviceLocator = null;
   private UmlDomainModelQueryService serviceLayerQuerySvc = null;


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
     List results = getService().query(contextCriteria, ContextImpl.class.getName());
    return results;
  }

  public List<UmlProjectMetaData> getAllProjects() throws Exception {
     List projects = getServiceLayerQuerySvc().findAllProjects();
     return projects;
  }
 
   public List<UmlSubProjectMetaData> getAllSubProjects() throws Exception {
      List subprojects = getServiceLayerQuerySvc().findAllSubProjects();
      return subprojects;
   }
   
   public List<UmlPackageMetaData> getAllPackages() throws Exception {
      List packages = getServiceLayerQuerySvc().findAllPackages();
      return packages;
   }
   
   public void setServiceLocator(ApplicationServiceLocator serviceLocator) {
      this.serviceLocator = serviceLocator;
   }

   public ApplicationServiceLocator getServiceLocator() {
      return serviceLocator;
   }
   
   protected ApplicationService getService() {
      if (service == null)
         service = serviceLocator.findCaBioSerivce();
      return service;
   }

   public UmlDomainModelQueryService getServiceLayerQuerySvc() {
      if (serviceLayerQuerySvc == null)
      serviceLayerQuerySvc = serviceLocator.findServiceLayerQueryService();
      return serviceLayerQuerySvc;
   }
}
