package gov.nih.nci.ncicb.cadsr.service.impl;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;

import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;

import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.system.applicationservice.ApplicationService;

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
       DetachedCriteria.forClass(Context.class);
     contextCriteria.addOrder(Order.asc("name"));
      ApplicationService caCoreService = getCaCoreAPIService();
     List results = caCoreService.query(contextCriteria, Context.class.getName());
    return results;
  }


  public List<Project> getAllProjects() throws Exception {
      ApplicationService caCoreService = getCaCoreAPIService();
      DetachedCriteria projectCriteria =
        DetachedCriteria.forClass(Project.class);
      projectCriteria.addOrder(Order.asc("shortName"));
     List<Project> results = caCoreService.query(projectCriteria, Project.class.getName());;
    return results;
  }
  
    public List<SubProject> getAllSubProjects() throws Exception {
        ApplicationService caCoreService = getCaCoreAPIService();
        DetachedCriteria subProjectCriteria =
          DetachedCriteria.forClass(SubProject.class);
        subProjectCriteria.addOrder(Order.asc("name"));
       List<SubProject> results = caCoreService.query(subProjectCriteria, SubProject.class.getName());;
      return results;
    }
    
    public List<UMLPackageMetadata> getAllPackages() throws Exception {
        ApplicationService caCoreService = getCaCoreAPIService();
        DetachedCriteria subPackageCriteria =
          DetachedCriteria.forClass(UMLPackageMetadata.class);
        subPackageCriteria.addOrder(Order.asc("name"));
       List<UMLPackageMetadata> results = caCoreService.query(subPackageCriteria, UMLPackageMetadata.class.getName());;
      return results;
    }
    
    public List<UMLClassMetadata> getAllClasses() throws Exception {
        ApplicationService caCoreService = getCaCoreAPIService();
        DetachedCriteria umlClassMetadataCriteria =
          DetachedCriteria.forClass(UMLClassMetadata.class);
          umlClassMetadataCriteria.addOrder(Order.asc("name"));
       List<UMLClassMetadata> results = caCoreService.query(umlClassMetadataCriteria, UMLClassMetadata.class.getName());;
      return results;
    }    
    
    
 

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

public List findUmlClass(UMLClassMetadata umlClass){
   List resultList =null;
   
   try {
       resultList = getCaCoreAPIService().search(UMLClassMetadata.class, umlClass);
       /**
       System.out.println("result count: " + resultList.size());
       for (Iterator resultsIterator = resultList.iterator();
               resultsIterator.hasNext();) {
           UMLClassMetadata returnedClass = (UMLClassMetadata) resultsIterator.next();
           System.out.println("returnClass Name:" + returnedClass.getName());
          System.out.println("returnClass id:" + returnedClass.getId());
          System.out.println("project name:" + returnedClass.getProject().getLongName());
          System.out.println("project name:" + returnedClass.getObjectClass().getLongName());
          System.out.println("Project id:" + returnedClass.getProject().getId());
          System.out.println("Package name:" + returnedClass.getUMLPackageMetadata().getName());
          System.out.println("size of metadata: " + returnedClass.getSemanticMetadataCollection().size());
          for (Iterator mdIterator = returnedClass.getSemanticMetadataCollection().iterator();
                  mdIterator.hasNext();) {
                  SemanticMetadata metaData = (SemanticMetadata) mdIterator.next();
                  System.out.println("concept Name: " + metaData.getConceptName());
          }
         
       }*/
   } catch (Exception e) {
       e.printStackTrace();
   }
  return resultList;
}

}
