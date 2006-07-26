package gov.nih.nci.ncicb.cadsr.service.impl;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;

import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;

import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
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
  
  public List<Project> getProjectForContext(Context context) throws Exception{
      ApplicationService caCoreService = getCaCoreAPIService();
      DetachedCriteria projectCriteria =
        DetachedCriteria.forClass(Project.class);
      projectCriteria.addOrder(Order.asc("longName"));

     if (context != null && context.getId().length() >0) {
        DetachedCriteria csCri = projectCriteria.createCriteria("classificationScheme");
        csCri.add(Expression.eq("latestVersionIndicator", "Yes"));
        DetachedCriteria contextCri= csCri.createCriteria("context");
        contextCri.add(Expression.eq("id", context.getId()));
     }
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

   public List<UMLPackageMetadata> getAllPackageForProject(Project project){
      List resultList =null;
      UMLPackageMetadata umlPkg = new UMLPackageMetadata();
      umlPkg.setProject(project);
      try {
         resultList = getCaCoreAPIService().search(UMLPackageMetadata.class, umlPkg);
      }     catch (Exception e) {
       e.printStackTrace();
   }
  return resultList;

   }
   
   public List<UMLClassMetadata> getClassesForContext(String contextId){
      List resultList =null;
      try {
        DetachedCriteria classCriteria = DetachedCriteria.forClass(UMLClassMetadata.class);
 //        classCriteria.addOrder( Order.asc("name").ignoreCase() );
        classCriteria.addOrder( Order.asc("name"));
        if (contextId != null && contextId.length() >0) {
           DetachedCriteria contextCri= classCriteria.createCriteria("project").createCriteria("classificationScheme").createCriteria("context");
           contextCri.add(Expression.eq("id", contextId));
        }
        resultList = getCaCoreAPIService().query(classCriteria, UMLClassMetadata.class.getName());
               
      } catch (Exception e) {
       e.printStackTrace();
   }
   return resultList;

   }
   
   
public List findUmlClass(UMLClassMetadata umlClass){
   List resultList =null;
   
   try {
       resultList = getCaCoreAPIService().search(UMLClassMetadata.class, umlClass);
   } catch (Exception e) {
       e.printStackTrace();
   }
  return resultList;
}

   public List findUmlAttributes(UMLAttributeMetadata umlAttribute){
      List resultList =null;
      
      try {
          resultList = getCaCoreAPIService().search(UMLAttributeMetadata.class, umlAttribute);
      } catch (Exception e) {
          e.printStackTrace();
      }
     return resultList;
   }

}
