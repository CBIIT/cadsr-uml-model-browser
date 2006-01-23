package gov.nih.nci.ncicb.cadsr.servicelocator;

import gov.nih.nci.cadsr.service.UmlDomainModelQueryService;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service.UMLBrowserTreeService;
import gov.nih.nci.system.applicationservice.ApplicationService;

public interface ApplicationServiceLocator
{
  public static final String APPLICATION_SERVICE_LOCATOR_CLASS_KEY = "ApplicationServiceLocatorClassName";


  public UMLBrowserTreeService findTreeService() throws ServiceLocatorException;
  public ApplicationService findCaBioSerivce() throws ServiceLocatorException;
  public UMLBrowserQueryService findQuerySerivce() throws ServiceLocatorException;
  public UmlDomainModelQueryService findServiceLayerQueryService() throws ServiceLocatorException;

}