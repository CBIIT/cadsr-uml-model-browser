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
import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeState;

/**
 * @author Jane Jiang
 * @version: $Id: TreeBacker.java,v 1.1 2006-07-26 18:55:58 jiangj Exp $
 */

public class TreeBacker implements Serializable {
   private static final long serialVersionUID = 1L;

   protected Log log = LogFactory.getLog(TreeBacker.class.getName());

   private TreeModelBase _treeModel;

   private HtmlTree _tree;

   private String selectedNode;
   private TreeState treeState;

   private static UMLBrowserTreeData treeData = null;

   public TreeBacker() {
      // Initialize the tree with the root node
      //       String[] path = _tree.getPathInformation("0");
      //       _tree.expandPath(path);

   }

   public TreeModel getTreeModel() {
      if (_treeModel == null) {
         _treeModel = new TreeModelBase(treeData.getTreeData());
         _treeModel.getTreeState().toggleExpanded("0");
         _treeModel.getTreeState().setTransient(true);   
      }

      return _treeModel;
   }
   
   public String refreshTree()   {
       treeData.refreshTree();
       _treeModel = new TreeModelBase(treeData.getTreeData());
       _treeModel.getTreeState().toggleExpanded("0");
       _treeModel.getTreeState().setTransient(true);      
       return null;
   }

   /**
   public void selectedNode() {
           this.selectedNode = this.getTreeModel().getNode().getDescription();
   }
*/
   public String getSelectedNode() {
      return selectedNode;
   }


   public void setTree(HtmlTree tree) {
      this._tree = tree;
   }

   public HtmlTree getTree() {
      return _tree;
   }

   public void setTreeData(UMLBrowserTreeData treeData) {
      this.treeData = treeData;
   }

   public UMLBrowserTreeData getTreeData() {
      return treeData;
   }
}
