package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto;

import gov.nih.nci.cadsr.domain.Context;

import javax.swing.tree.DefaultMutableTreeNode;

public interface ContextHolder
{

  public void setContext(Context context);


  public Context getContext();

  public void setNode(DefaultMutableTreeNode node);


  public DefaultMutableTreeNode getNode();
}