package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeFunctions;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeIdGenerator;

import java.util.List;
import java.util.Map;

/**
 * This service provivides operations for building the UMLBrowser tree
 */
public interface UMLBrowserTreeService
{

  /**
   * Returns all Context nodes as webnodes
   */
  public List getContextNodeHolders(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception;
  public Map getProjectNodesByContextId(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception;
  public Map getSubProjectNodesByCSId(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception;
  public List getPackageNodes(TreeFunctions treeFunctions,TreeIdGenerator idGen,String excludeList) throws Exception;

}