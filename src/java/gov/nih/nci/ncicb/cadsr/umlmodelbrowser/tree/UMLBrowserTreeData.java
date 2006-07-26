package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree;

import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.webtree.LazyActionTreeNode;

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.tree2.TreeModelBase;

public class UMLBrowserTreeData implements Serializable {
   private static final long serialVersionUID = 1L;

   protected Log log = LogFactory.getLog(UMLBrowserTreeData.class.getName());
   private static ApplicationServiceLocator appServiceLocator = null;
   LazyActionTreeNode treeData = null;
   
   public UMLBrowserTreeData() {
   }
   
   private LazyActionTreeNode buildTree() {
      log.info("Building UML Browser tree start ....");
      LazyActionTreeNode contextFolder =
         new LazyActionTreeNode("Context Folder", "caDSR Contexts",
             "javascript:classSearchAction('P_PARAM_TYPE=Context')",
             false);
      //get the node path

      //first build a text folder node context.
      try {

         UMLBrowserQueryService queryService =
            appServiceLocator.findQuerySerivce();
         List<Context> contexts = queryService.getAllContexts();
         for (Iterator iter = contexts.iterator(); iter.hasNext(); ) {
            Context context = (Context)iter.next();
            LazyActionTreeNode contextNode =
               new LazyActionTreeNode("Context Folder", context.name,
                 "javascript:classSearchAction('P_PARAM_TYPE=Context&P_IDSEQ=" +
                 context
                 .getId() +
                 "&treeBreadCrumbs=caDSR Contexts>>" +
                 context
                 .getName() +
                 " ')",
                 context
                 .getId(),
                 false);
            contextFolder.getChildren().add(contextNode);

            // Build project nodes
            List<Project> projects =
               queryService.getProjectForContext(context);
            for (Iterator projIter = projects.iterator(); projIter.hasNext();
            ) {
               Project project = (Project)projIter.next();
               LazyActionTreeNode projectNode =
                  new LazyActionTreeNode("Project Folder",
                    project.getLongName(),
                    "javascript:classSearchAction('P_PARAM_TYPE=PROJECT&P_IDSEQ=" +
                    project.getId() +
                    "&treeBreadCrumbs=caDSR Contexts>>" +
                    context.getName() +
                    ">>Projects>>" + project.getLongName() +
                    " ')",  project.getId(),
                    false);
               contextNode.getChildren().add(projectNode);

               // build sub project nodes

               Collection<SubProject> subProjects =
                  project.getSubProjectCollection();
               for (Iterator subprojIter = subProjects.iterator();
                    subprojIter.hasNext(); ) {
                  SubProject subProject = (SubProject)subprojIter.next();
                  LazyActionTreeNode subprojectNode =
                     new LazyActionTreeNode("SubProject Folder",
                    subProject.getName(),
                    "javascript:classSearchAction('P_PARAM_TYPE=SUBPROJECT&P_IDSEQ=" +
                    subProject.getId() +
                    "&treeBreadCrumbs=caDSR Contexts>>" +
                    context.getName() +
                    ">>Projects>>" + project.getLongName() +
                    ">>" +  subProject.getName() +
                    " ')",
                    subProject.getId(),
                    false);
                  projectNode.getChildren().add(subprojectNode);
                  
                  // build package nodes under sub project
                  addPackageNodes(subProject.getUMLPackageMetadataCollection(),
                  subprojectNode);
      
               }
               // then build package nodes directly under project
               addPackageNodes(project.getUMLPackageMetadataCollection(), projectNode);

            }

         }

      } catch (Exception e) {
         log.error("Exception caught when building the tree", e);
         throw new RuntimeException(e);
      }
      log.info("Finished Building UML Browser tree");
      return contextFolder;

   }
   public void setAppServiceLocator(ApplicationServiceLocator appServiceLocator) {
      this.appServiceLocator = appServiceLocator;
   }

   public ApplicationServiceLocator getAppServiceLocator() {
      return appServiceLocator;
   }

   private void addPackageNodes(Collection<UMLPackageMetadata> packages, 
   LazyActionTreeNode parentNode){
      //build class nodes
       int bcIndex = parentNode.getAction().indexOf("&treeBreadCrumbs=");
       String parentBreadCrumb = parentNode.getAction().subSequence(bcIndex, 
              parentNode.getAction().length()-3).toString();
      
      for (Iterator pkgIter = packages.iterator(); pkgIter.hasNext(); ) {
         UMLPackageMetadata pkg = (UMLPackageMetadata)pkgIter.next();
         LazyActionTreeNode pkgNode =
            new LazyActionTreeNode("Package Folder", pkg.getName(),
          "javascript:classSearchAction('P_PARAM_TYPE=PACKAGE&P_IDSEQ=" +
          pkg.getId() + parentBreadCrumb + ">>" + pkg.getName() + " ')", 
          pkg.getId(), false);
          parentNode.getChildren().add(pkgNode);
         
          //addClassNodes to each package node
          addClassNodes(pkg.getUMLClassMetadataCollection(), pkgNode );
      }
      
   }

   private void addClassNodes(Collection<UMLClassMetadata> umlClasses, 
   LazyActionTreeNode pkgNode){
      //build class nodes
       int bcIndex = pkgNode.getAction().indexOf("&treeBreadCrumbs=");
       String pkgBreadCrumb = pkgNode.getAction().subSequence(bcIndex, pkgNode.getAction().length()-3).toString();
      
       for (Iterator clsIter = umlClasses.iterator(); clsIter.hasNext(); ) {
          UMLClassMetadata umlClass = (UMLClassMetadata)clsIter.next();
          LazyActionTreeNode clsNode =
             new LazyActionTreeNode("Class Node", umlClass.getName(),
           "javascript:classSearchAction('P_PARAM_TYPE=CLASS&P_IDSEQ=" +
           umlClass.getId() +  pkgBreadCrumb + ">>" + umlClass.getName() +" ')",
           umlClass.getId(), false);
           pkgNode.getChildren().add(clsNode);
         }
      
   }
   public void refreshTree()   {
      setTreeData(this.buildTree());
   }

   public synchronized  void setTreeData(LazyActionTreeNode treeData) {
      this.treeData = treeData;
   }

   public LazyActionTreeNode getTreeData() {
      if (treeData == null)
         setTreeData(buildTree());
      return treeData;
   }
}
