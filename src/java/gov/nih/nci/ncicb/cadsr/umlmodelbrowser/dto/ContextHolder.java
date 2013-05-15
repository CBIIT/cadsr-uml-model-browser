package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto;

import gov.nih.nci.cadsr.domain.Context;

import javax.swing.tree.DefaultMutableTreeNode;

public class ContextHolder
{

    private Context context;
    private DefaultMutableTreeNode node; 
    

    public void setContext(Context context)
    {
      this.context = context;
    }


    public Context getContext()
    {
      return context;
    }


    public void setNode(DefaultMutableTreeNode node)
    {
      this.node = node;
    }


    public DefaultMutableTreeNode getNode()
    {
      return node;
    }
}