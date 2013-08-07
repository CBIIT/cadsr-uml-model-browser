/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

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