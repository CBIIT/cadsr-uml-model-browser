package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.BaseTreeNode;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ContextHolder;
import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.PackageHolder;
import gov.nih.nci.ncicb.cadsr.util.TimeUtils;
import gov.nih.nci.ncicb.webtree.WebNode;
import gov.nih.nci.ncicb.webtree.WebTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UMLBrowserTree extends WebTree implements TreeConstants {

    protected Log log = LogFactory.getLog(UMLBrowserTree.class.getName());

    private String treeType;

    private String functionName;

    private String extraURLParameters =
        "&PageId=DataElementsGroup&NOT_FIRST_DISPLAY=1&performQuery=yes";

    private String contextExcludeListStr = null;


    public UMLBrowserTree() {
    }

    public DefaultMutableTreeNode getTree(Hashtable params) throws Exception {
        treeType = (String)params.get("treeType");

        functionName = (String)params.get("functionName");
        contextExcludeListStr =
            (String)params.get(TreeConstants.BR_CONTEXT_EXCLUDE_LIST_STR);
        return buildTree(params);
    }

    public DefaultMutableTreeNode buildTree(Hashtable treeParams) throws Exception {

        Context ctx = null;
        DefaultMutableTreeNode tree = null;

        BaseTreeNode baseNode = null;

        //TimeUtils.recordStartTime("Tree");
        try {

            log.info("Tree Start " + TimeUtils.getEasternTime());

            baseNode = new BaseTreeNode(treeParams);
            UMLBrowserTreeCache cache = UMLBrowserTreeCache.getAnInstance();
            cache.init(baseNode, treeParams);

            WebNode contexts =
                new WebNode(cache.getIdGen().getNewId(), "caDSR Contexts",
                                           "javascript:classSearchAction('P_PARAM_TYPE=Context')");
            tree = new DefaultMutableTreeNode(contexts);
            List<ContextHolder> allContexts = cache.getAllContextHolders();

            if (allContexts == null)
                return tree;

            Map<String,List<DefaultMutableTreeNode>> allProjectsByContextId = cache.getAllProjectByContextId();
            Map<String,List<DefaultMutableTreeNode>> subProjectsByProjectId = cache.getAllSubProjectByProjectId();
            PackageHolder packageHolder = cache.getPackageHolder();
            Map<String,List<DefaultMutableTreeNode>> packagesWithNoSubProject = packageHolder.getPackagesWithNoSubProjectsMap();
            Map<String,List<DefaultMutableTreeNode>> packagesWithSubProject = packageHolder.getPackagesWithSubProjectsMap();
            Map<String,List<DefaultMutableTreeNode>> allClassesByPackageId = cache.getAllClassesByPackageId();

            for (ContextHolder currContextHolder:allContexts) {

                Context currContext = currContextHolder.getContext();
                DefaultMutableTreeNode contextNode =
                    currContextHolder.getNode();

                if (allProjectsByContextId.get(currContext.getId()) != null) {
                   List<DefaultMutableTreeNode> projects = allProjectsByContextId.get(currContext.getId());
                   WebNode projectTexts =
                       new WebNode(cache.getIdGen().getNewId(), "Projects");
                   DefaultMutableTreeNode projectTextNode =
                       new DefaultMutableTreeNode(projectTexts);
                   contextNode.add(projectTextNode);
                   for (int i=0; i<projects.size(); i++) {
                      DefaultMutableTreeNode projNode =projects.get(i);
                      if(projNode!=null)
                      {
                      projectTextNode.add(projNode);
                      String projectId = ((WebNode) (projNode.getUserObject())).getInfo();
                      List<DefaultMutableTreeNode> subProjectNodes = subProjectsByProjectId.get(projectId);
                      if(subProjectNodes!=null)
                        addSubProjectNodes(projNode,subProjectNodes,packagesWithSubProject,allClassesByPackageId);
                      List<DefaultMutableTreeNode> packages = packagesWithNoSubProject.get(projectId);
                      if(packages!=null)
                        addPackageNodes(projNode,packages,allClassesByPackageId);
                      }
                   }
                }


                tree.add(contextNode);
            }

            log.info("Tree End " + TimeUtils.getEasternTime());
        } catch (Exception ex) {
            ex.printStackTrace();

            throw ex;
        }

        return tree;
    }

    private void addSubProjectNodes(DefaultMutableTreeNode parentNode,
                                List<DefaultMutableTreeNode> allSubProjects,
                                Map<String,List<DefaultMutableTreeNode>> packagesWithSubProject,
                                Map<String,List<DefaultMutableTreeNode>> allClassesByPackageId
                                ){

        for(DefaultMutableTreeNode node:allSubProjects)
        {
            parentNode.add(node);
            String subProjectId = ((WebNode) (node.getUserObject())).getInfo();
            if(packagesWithSubProject.get(subProjectId)!=null)
            {
              List<DefaultMutableTreeNode> packages = packagesWithSubProject.get(subProjectId);
              if(packages!=null)
                addPackageNodes(node,packages,allClassesByPackageId);
            }
        }

    }
    private void addPackageNodes(DefaultMutableTreeNode parentNode,
                                List<DefaultMutableTreeNode> packages,
                                Map<String,List<DefaultMutableTreeNode>> allClassesByPackageId
                                )
    {
        for(DefaultMutableTreeNode node:packages)
        {
            parentNode.add(node);
            String packageId = ((WebNode) (node.getUserObject())).getInfo();
            if(allClassesByPackageId.get(packageId)!=null)
            {
                List<DefaultMutableTreeNode> classes = allClassesByPackageId.get(packageId);
                if(classes!=null)
                    addClassNodes(node,classes);
            }
        }
     }

    private void addClassNodes(DefaultMutableTreeNode parentNode,
                                List<DefaultMutableTreeNode> classNodes
                                )
    {
        for(DefaultMutableTreeNode node:classNodes)
        {
            parentNode.add(node);
        }
     }

    private void addChildNode(DefaultMutableTreeNode parentNode,
                              UMLBrowserTreeCache cache,
                              Collection<String> childNodeLabels) {

        for (Iterator<String> i = childNodeLabels.iterator(); i.hasNext(); ) {
            WebNode child = new WebNode(cache.getIdGen().getNewId(), i.next(),
            "javascript:classDetailsAction('')");
            DefaultMutableTreeNode childNode =
                new DefaultMutableTreeNode(child);
            parentNode.add(childNode);

        }
    }
}
