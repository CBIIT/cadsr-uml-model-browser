package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.BaseTreeNode;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeIdGenerator;
import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service.UMLBrowserTreeService;
import gov.nih.nci.ncicb.cadsr.util.TimeUtils;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UMLBrowserTreeCache
{

  private final String CTEP="CTEP";
  protected Log log =  LogFactory.getLog(UMLBrowserTreeCache.class.getName());

  public UMLBrowserTreeCache()
  {
  }
  //All crf by contextId - Protocols(List) -crf(Collection)
  private Map allProjectByContext = null;
  private Map allSubProjectByCsid = null;
  private Map allPackagesByCSid = null;
  private Map allPackagesByCsiId = null;
  private List allContextHolders = null;
  private TreeIdGenerator idGen = new TreeIdGenerator();
  private Map allClassificationNodes = null;
  private static ApplicationServiceLocator appServiceLocator = null;

  public static UMLBrowserTreeCache getAnInstance() throws Exception
  {

      UMLBrowserTreeCache cache = new UMLBrowserTreeCache();
      return cache;
  }

 public DefaultMutableTreeNode getClassificationNodes(String contextIdSeq)
  {
    return (DefaultMutableTreeNode)allClassificationNodes.get(contextIdSeq);
  }

  public void init(BaseTreeNode baseTree,Hashtable treeParams) throws Exception
  {
    log.info("Init start"+TimeUtils.getEasternTime());
    String contextExcludeListStr = (String)treeParams.get(TreeConstants.BR_CONTEXT_EXCLUDE_LIST_STR);
    UMLBrowserTreeService treeService = appServiceLocator.findTreeService();
    allContextHolders = treeService.getContextNodeHolders(baseTree,idGen,contextExcludeListStr);
    //allProjectByContext = treeService.getProjectNodesByContextId(baseTree,idGen,contextExcludeListStr);
    //allSubProjectByCsid = treeService.getSubProjectNodesByCSId(baseTree,idGen,contextExcludeListStr);
    //allPackagesByCSid = (Map)treeService.getPackageNodes(baseTree,idGen,contextExcludeListStr).get(0);
    //allPackagesByCsiId = (Map)treeService.getPackageNodes(baseTree,idGen,contextExcludeListStr).get(1);
    
    log.info("Init end"+TimeUtils.getEasternTime());
  }

  private void clearCache()
  {

  }


  public void setAllContextHolders(List allContextHolders)
  {
    this.allContextHolders = allContextHolders;
  }


  public List getAllContextHolders()
  {
    return allContextHolders;
  }

  public void set_allContextHolders(List allContextHolders)
  {
    this.allContextHolders = allContextHolders;
  }


  public List get_allContextHolders()
  {
    return allContextHolders;
  }


  public void setAppServiceLocator(ApplicationServiceLocator appServiceLocator)
  {
    this.appServiceLocator = appServiceLocator;
  }


  public ApplicationServiceLocator getAppServiceLocator()
  {
    return appServiceLocator;
  }


  public TreeIdGenerator getIdGen()
  {
    return idGen;
  }


   public Map getAllProjectByContext() {
      return allProjectByContext;
   }

   public Map getAllSubProjectByCsid() {
      return allSubProjectByCsid;
   }

   public Map getAllPackagesByCSid() {
      return allPackagesByCSid;
   }

   public Map getAllPackagesByCsiId() {
      return allPackagesByCsiId;
   }
}
