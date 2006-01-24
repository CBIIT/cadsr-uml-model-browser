package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.BaseTreeNode;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeIdGenerator;
import gov.nih.nci.cadsr.domain.Context;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ContextHolder;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.PackageHolder;
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
  private Map<String,List<DefaultMutableTreeNode>> allProjectByContextId = null;
  private Map<String,List<DefaultMutableTreeNode>> allSubProjectByProjectId = null;
  private PackageHolder packageHolder = null;
  private Map<String,List<DefaultMutableTreeNode>> allClassesByPackageId = null;
  private List<ContextHolder> allContextHolders = null;
  private TreeIdGenerator idGen = new TreeIdGenerator();
  private static ApplicationServiceLocator appServiceLocator = null;

  public static UMLBrowserTreeCache getAnInstance() throws Exception
  {

      UMLBrowserTreeCache cache = new UMLBrowserTreeCache();
      return cache;
  }


  public void init(BaseTreeNode baseTree,Hashtable treeParams) throws Exception
  {
    log.info("Init start"+TimeUtils.getEasternTime());

    UMLBrowserTreeService treeService = appServiceLocator.findTreeService();
    allContextHolders = treeService.getContextNodeHolders(baseTree,idGen);
    allProjectByContextId = treeService.getProjectNodesByContextId(baseTree,idGen);
    allSubProjectByProjectId = treeService.getSubProjectNodesByProjectId(baseTree,idGen);
    packageHolder = treeService.getPackageNodeHolder(baseTree,idGen);
    allClassesByPackageId = (Map)treeService.getClassNodesByPackageId(baseTree,idGen);
    
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

    public void setAllProjectByContextId(Map<String, List<DefaultMutableTreeNode>> allProjectByContextId)
    {
        this.allProjectByContextId = allProjectByContextId;
    }

    public Map<String, List<DefaultMutableTreeNode>> getAllProjectByContextId()
    {
        return allProjectByContextId;
    }

    public void setAllSubProjectByProjectId(Map<String, List<DefaultMutableTreeNode>> allSubProjectByProjectId)
    {
        this.allSubProjectByProjectId = allSubProjectByProjectId;
    }

    public Map<String, List<DefaultMutableTreeNode>> getAllSubProjectByProjectId()
    {
        return allSubProjectByProjectId;
    }

    public void setPackageHolder(PackageHolder packageHolder)
    {
        this.packageHolder = packageHolder;
    }

    public PackageHolder getPackageHolder()
    {
        return packageHolder;
    }

    public void setAllClassesByPackageId(Map<String, List<DefaultMutableTreeNode>> allClassesByPackageId)
    {
        this.allClassesByPackageId = allClassesByPackageId;
    }

    public Map<String, List<DefaultMutableTreeNode>> getAllClassesByPackageId()
    {
        return allClassesByPackageId;
    }

    public void setIdGen(TreeIdGenerator idGen)
    {
        this.idGen = idGen;
    }
}
