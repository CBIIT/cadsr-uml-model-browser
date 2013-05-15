package gov.nih.nci.ncicb.cadsr.servicelocator;


import gov.nih.nci.ncicb.cadsr.service.CaDSRQueryService;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.system.applicationservice.ApplicationService;

import javax.sql.DataSource;

public interface ApplicationServiceLocator
{
  public static final String APPLICATION_SERVICE_LOCATOR_CLASS_KEY = "ApplicationServiceLocatorClassName";


  public ApplicationService findCaCoreAPIService() throws ServiceLocatorException;
  public UMLBrowserQueryService findQuerySerivce() throws ServiceLocatorException;
  public CaDSRQueryService findCaDSRQueryService() throws ServiceLocatorException;
  //public UmlDomainModelQueryService findServiceLayerQueryService() throws ServiceLocatorException;

}