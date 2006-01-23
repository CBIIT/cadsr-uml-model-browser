package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service.impl;

import gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem;
import gov.nih.nci.cadsr.domain.Context;

import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ContextHolder;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ContextHolderTransferObject;
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
  public List getContextNodeHolders(TreeFunctions treeFunctions, TreeIdGenerator idGen,
                                    String excludeList) throws Exception {
 List holders = new ArrayList();
 UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();

    List contexts = queryService.getAllContexts();
    ListIterator it = contexts.listIterator();

    while (it.hasNext()) {
      Context context = (Context)it.next();

      ContextHolder holder = new ContextHolderTransferObject();
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

/**
   public Map getProjectNodesByContextId(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception{
      Map projectNodeMap = new HashMap();
      UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
      List projects = queryService.getAllProjects();   
      Iterator<UmlProjectMetaData> iter = projects.iterator();
      
      while (iter.hasNext()) {
         UmlProjectMetaData proj = iter.next();
         String contextId = proj.getClassificationScheme().getContext().getId();
         if (projectNodeMap.get(contextId) == null) {
            projectNodeMap.put(contextId, new ArrayList() );
         }
         ((List)projectNodeMap.get(contextId)).add(getProjectNode(idGen.getNewId(), proj, treeFunctions));
   
      }
      return projectNodeMap;
   }
   **/

   /**
   public Map getSubProjectNodesByCSId(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception{
      Map subprojectNodeMap = new HashMap();
      UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
      List subprojects = queryService.getAllSubProjects();
      Iterator<UmlSubProjectMetaData> iter = subprojects.iterator();
      String csId = "";      
      while (iter.hasNext()) {
         UmlSubProjectMetaData subproj = iter.next();
         Iterator cscsiIter = subproj.getCSI().getClassSchemeClassSchemeItemCollection().iterator();
         if (cscsiIter.hasNext()) {
            csId = ((ClassSchemeClassSchemeItem) cscsiIter.next()).getClassificationScheme().getId();
         }
         if (subprojectNodeMap.get(csId) == null) {
            subprojectNodeMap.put(csId, new ArrayList() );
         }
         ((List)subprojectNodeMap.get(csId)).add(getSubProjectNode(idGen.getNewId(), subproj, treeFunctions));
   
      }
      return subprojectNodeMap;
   }
   **/
   /**
   public List getPackageNodes(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception{
      Map pkgNodeByCsidMap = new HashMap();
      Map pkgNodeByCsiIdMap = new HashMap();
      UMLBrowserQueryService queryService = serviceLocator.findQuerySerivce();
      List packages = queryService.getAllPackages();
      Iterator<UmlPackageMetaData> iter = packages.iterator();
      String csiId = "";   
      String csId = "";
      ClassSchemeClassSchemeItem cscsi = null;
      while (iter.hasNext()) {
         UmlPackageMetaData pkg = iter.next();
         Iterator cscsiIter = pkg.getCSI().getClassSchemeClassSchemeItemCollection().iterator();
         if (cscsiIter.hasNext()) {
            cscsi = (ClassSchemeClassSchemeItem) cscsiIter.next();
         
            if (cscsi.getParentClassSchemeClassSchemeItem() != null) {
               csiId = cscsi.getParentClassSchemeClassSchemeItem().getClassificationSchemeItem().getId();
               
               if (pkgNodeByCsiIdMap.get(csiId) == null) 
                  pkgNodeByCsiIdMap.put(csiId, new ArrayList() );
         
               ((List)pkgNodeByCsiIdMap.get(csiId)).add(getPackageNode(idGen.getNewId(), pkg, treeFunctions));
            } else {
               csId = cscsi.getClassificationScheme().getId();
               }
               if (pkgNodeByCsidMap.get(csId) == null) {
               pkgNodeByCsidMap.put(csId, new ArrayList() );
               }
               ((List)pkgNodeByCsidMap.get(csId)).add(getPackageNode(idGen.getNewId(), pkg, treeFunctions));
              
            }
      }
      ArrayList results = new ArrayList();
      results.add(pkgNodeByCsidMap);
      results.add(pkgNodeByCsiIdMap);
      return results;
   }
**/
   public void setServiceLocator(ApplicationServiceLocator serviceLocator) {
      this.serviceLocator = serviceLocator;
   }

   public ApplicationServiceLocator getServiceLocator() {
      return serviceLocator;
   }
   
   /**
   private DefaultMutableTreeNode getSubProjectNode(String nodeId, 
   UmlSubProjectMetaData subproj, TreeFunctions treeFunctions) throws Exception {
     return new DefaultMutableTreeNode(
               new WebNode(nodeId,
                           subproj.getName(),
                           "javascript:" + treeFunctions.getJsFunctionName() 
                           + "('P_PARAM_TYPE=SUBPROJECT&P_IDSEQ="
                              + subproj.getCSI().getId()
                              + treeFunctions.getExtraURLParameters() + "')",
                           subproj.getCSI().getId()));
   }
**/
/**
   private DefaultMutableTreeNode getProjectNode(String nodeId, 
   UmlProjectMetaData proj, TreeFunctions treeFunctions) throws Exception {
     return new DefaultMutableTreeNode(
               new WebNode(nodeId,
                           proj.getName(),
                           "javascript:" + treeFunctions.getJsFunctionName() 
                           + "('P_PARAM_TYPE=PROJECT&P_IDSEQ="
                              + proj.getClassificationScheme().getId()
                              + treeFunctions.getExtraURLParameters() + "')",
                           proj.getClassificationScheme().getId()));
   }
   private DefaultMutableTreeNode getPackageNode(String nodeId, 
   UmlPackageMetaData pkg, TreeFunctions treeFunctions) throws Exception {
     return new DefaultMutableTreeNode(
               new WebNode(nodeId,
                           pkg.getName(),
                           "javascript:" + treeFunctions.getJsFunctionName() 
                           + "('P_PARAM_TYPE=PACKAGE&P_IDSEQ="
                              + pkg.getCSI().getId()
                              + treeFunctions.getExtraURLParameters() + "')",
                           pkg.getCSI().getId()));
   }
   **/
}
