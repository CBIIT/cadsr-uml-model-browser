package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service.impl;

import gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem;
import gov.nih.nci.cadsr.domain.Context;

import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ContextHolder;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.PackageHolder;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeFunctions;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeIdGenerator;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service.UMLBrowserTreeService;
import gov.nih.nci.ncicb.webtree.WebNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;


public class UMLBrowserTreeServiceImpl
  implements UMLBrowserTreeService {

  /**
   * Currently the locator classname is hard code need to be refactored
   */
  public UMLBrowserTreeServiceImpl() { }
   private ApplicationServiceLocator serviceLocator = null;
  /**
   * @returns a Context holder that contains a contect object and context we node
   */
  public List<ContextHolder> getContextNodeHolders(TreeFunctions treeFunctions, TreeIdGenerator idGen) throws Exception {
 List<ContextHolder> holders = new ArrayList<ContextHolder>();
 UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();

    List contexts = queryService.getAllContexts();
    ListIterator it = contexts.listIterator();

    while (it.hasNext()) {
      Context context = (Context)it.next();

      ContextHolder holder = new ContextHolder();
      holder.setContext(context);
      holder.setNode(getContextNode(idGen.getNewId(), context, treeFunctions));
      holders.add(holder);
    }

    return holders;
  }
                                   


   private DefaultMutableTreeNode getWebNode(String name, String id) {
    return new DefaultMutableTreeNode(new WebNode(id, name));
  }

   private DefaultMutableTreeNode getContextNode(String nodeId, Context context,
                                                 TreeFunctions treeFunctions) throws Exception {
     String currContextId = context.getId();

     String name = context.getName();
     String desc = context.getDescription();

     DefaultMutableTreeNode
        contextNode = new DefaultMutableTreeNode(
                         new WebNode(nodeId,
                                     name + " (" + desc + ")",
                                     "javascript:" + treeFunctions.getJsFunctionName()
                                        + "('P_PARAM_TYPE=CONTEXT&P_IDSEQ=" + currContextId + "&P_CONTE_IDSEQ="
                                        + currContextId + treeFunctions.getExtraURLParameters() + "')",
                                     desc + " (" + name + ")"));
     return contextNode;
   }


   public Map<String,List<DefaultMutableTreeNode>> getProjectNodesByContextId(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception{
      Map<String,List<DefaultMutableTreeNode>> projectNodeMap = new HashMap<String,List<DefaultMutableTreeNode>>();
      UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
      List projects = queryService.getAllProjects();   
      Iterator<Project> iter = projects.iterator();
      
      while (iter.hasNext()) {
         Project proj = iter.next();
         String contextId = proj.getClassificationScheme().getContext().getId();
         if (projectNodeMap.get(contextId) == null) {
            projectNodeMap.put(contextId, new ArrayList<DefaultMutableTreeNode>() );
         }
          List<DefaultMutableTreeNode> list = projectNodeMap.get(contextId);
          list.add(getProjectNode(idGen.getNewId(), proj, treeFunctions));
   
      }
      return projectNodeMap;
   }
   


   public Map<String,List<DefaultMutableTreeNode>> getSubProjectNodesByProjectId(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception{
      
      Map<String,List<DefaultMutableTreeNode>> subprojectNodeMap = new HashMap<String,List<DefaultMutableTreeNode>>();
      UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
      List subprojects = queryService.getAllSubProjects();
      Iterator<SubProject> iter = subprojects.iterator();
      while (iter.hasNext()) {
         SubProject subproj = iter.next();
         Project project = subproj.getProject();

         if (subprojectNodeMap.get(project.getId()) == null) {
            subprojectNodeMap.put(project.getId(), new ArrayList<DefaultMutableTreeNode>() );
         }
          List<DefaultMutableTreeNode> list = subprojectNodeMap.get(project.getId());         
          list.add(getSubProjectNode(idGen.getNewId(), subproj, treeFunctions));
   
      }
      return subprojectNodeMap;
   }

   
   public PackageHolder getPackageNodeHolder(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception{
     
      Map<String,List<DefaultMutableTreeNode>> packageByWithSubProjectsMap = new HashMap<String,List<DefaultMutableTreeNode>>();      
      Map<String,List<DefaultMutableTreeNode>> packageByWithNoSubProjectsMap = new HashMap<String,List<DefaultMutableTreeNode>>();
       
      UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
      List packages = queryService.getAllPackages();
      
      Iterator<UMLPackageMetadata> iter = packages.iterator();

      ClassSchemeClassSchemeItem cscsi = null;
      while (iter.hasNext()) {
         UMLPackageMetadata pkg = iter.next();
         if(pkg.getSubProject()!=null)
         {
             SubProject sub = pkg.getSubProject();
             if (packageByWithSubProjectsMap.get(sub.getId()) == null) {
                packageByWithSubProjectsMap.put(sub.getId(), new ArrayList<DefaultMutableTreeNode>() );
             }
              List<DefaultMutableTreeNode> list = packageByWithSubProjectsMap.get(sub.getId());         
              list.add(getPackageNode(idGen.getNewId(), pkg, treeFunctions));             
         }
         else
         {
             Project project = pkg.getProject();
             if (packageByWithNoSubProjectsMap.get(project.getId()) == null) {
                packageByWithNoSubProjectsMap.put(project.getId(), new ArrayList<DefaultMutableTreeNode>() );
             }
              List<DefaultMutableTreeNode> list = packageByWithNoSubProjectsMap.get(project.getId());         
              list.add(getPackageNode(idGen.getNewId(), pkg, treeFunctions));              
         }

      }
      PackageHolder holder = new PackageHolder();
       holder.setPackagesWithNoSubProjectsMap(packageByWithNoSubProjectsMap);
       holder.setPackagesWithSubProjectsMap(packageByWithSubProjectsMap);
      return holder;
   }

    public Map<String,List<DefaultMutableTreeNode>> getClassNodesByPackageId(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception{
       
       Map<String,List<DefaultMutableTreeNode>> classNodeMap = new HashMap<String,List<DefaultMutableTreeNode>>();
       UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
       List<UMLClassMetadata> classes = queryService.getAllClasses();
       Iterator<UMLClassMetadata> iter = classes.iterator();
       while (iter.hasNext()) {
          UMLClassMetadata aClass = iter.next();
          UMLPackageMetadata pkg = aClass.getUMLPackageMetadata();

          if (classNodeMap.get(pkg.getId()) == null) {
             classNodeMap.put(pkg.getId(), new ArrayList<DefaultMutableTreeNode>() );
          }
           List<DefaultMutableTreeNode> list = classNodeMap.get(pkg.getId());         
           list.add(getClassNode(idGen.getNewId(), aClass, treeFunctions));
    
       }
       return classNodeMap;
    }
    
   public void setServiceLocator(ApplicationServiceLocator serviceLocator) {
      this.serviceLocator = serviceLocator;
   }

   public ApplicationServiceLocator getServiceLocator() {
      return serviceLocator;
   }
   

   private DefaultMutableTreeNode getSubProjectNode(String nodeId, 
   SubProject subproj, TreeFunctions treeFunctions) throws Exception {
     return new DefaultMutableTreeNode(
               new WebNode(nodeId,
                           subproj.getName(),
                           "javascript:" + treeFunctions.getJsFunctionName() 
                           + "('P_PARAM_TYPE=SUBPROJECT&P_IDSEQ="
                              + subproj.getId()
                              + treeFunctions.getExtraURLParameters() + "')",
                           subproj.getId()));
   }


   private DefaultMutableTreeNode getProjectNode(String nodeId, 
   Project proj, TreeFunctions treeFunctions) throws Exception {
     return new DefaultMutableTreeNode(
               new WebNode(nodeId,
                           proj.getShortName(),
                           "javascript:" + treeFunctions.getJsFunctionName() 
                           + "('P_PARAM_TYPE=PROJECT&P_IDSEQ="
                              + proj.getClassificationScheme().getId()
                              + treeFunctions.getExtraURLParameters() + "')",
                           proj.getClassificationScheme().getId()));
   }
   

   private DefaultMutableTreeNode getPackageNode(String nodeId, 
   UMLPackageMetadata pkg, TreeFunctions treeFunctions) throws Exception {
     return new DefaultMutableTreeNode(
               new WebNode(nodeId,
                           pkg.getName(),
                           "javascript:" + treeFunctions.getJsFunctionName() 
                           + "('P_PARAM_TYPE=PACKAGE&P_IDSEQ="
                              + pkg.getId()
                              + treeFunctions.getExtraURLParameters() + "')",
                           pkg.getId()));
   }
   
    private DefaultMutableTreeNode getClassNode(String nodeId, 
    UMLClassMetadata aClass, TreeFunctions treeFunctions) throws Exception {
      return new DefaultMutableTreeNode(
                new WebNode(nodeId,
                            aClass.getName(),
                            "javascript:" + treeFunctions.getJsFunctionName() 
                            + "('P_PARAM_TYPE=CLASS&P_IDSEQ="
                               + aClass.getId()
                               + treeFunctions.getExtraURLParameters() + "')",
                            aClass.getId()));
    }   

}
