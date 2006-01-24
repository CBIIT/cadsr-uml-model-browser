package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ContextHolder;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.PackageHolder;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeFunctions;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeIdGenerator;

import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This service provivides operations for building the UMLBrowser tree
 */
public interface UMLBrowserTreeService
{

  /**
   * Returns all Context nodes as webnodes
   */
  public List<ContextHolder> getContextNodeHolders(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception;
  public Map<String,List<DefaultMutableTreeNode>> getProjectNodesByContextId(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception;
  public Map<String,List<DefaultMutableTreeNode>> getSubProjectNodesByProjectId(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception;
  public PackageHolder getPackageNodeHolder(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception;
  public Map<String,List<DefaultMutableTreeNode>> getClassNodesByPackageId(TreeFunctions treeFunctions,TreeIdGenerator idGen) throws Exception;

}