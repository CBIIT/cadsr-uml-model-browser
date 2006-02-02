package gov.nih.nci.ncicb.cadsr.servicelocator.spring;


import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.servicelocator.ServiceLocatorException;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.service.UMLBrowserTreeService;
import gov.nih.nci.ncicb.cadsr.util.UMLBrowserParams;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;


public class ApplicationServiceLocatorImpl implements ApplicationServiceLocator {

   private UMLBrowserTreeService treeService = null;

   private UMLBrowserQueryService queryService = null;

   private ApplicationService cabioAppService = null;
   
   private String caCoreAPIUrl = null;



   public ApplicationServiceLocatorImpl() {
   }

   public UMLBrowserTreeService findTreeService() throws ServiceLocatorException {
      if (treeService == null) {
         try {
            Object obj =
               new SpringObjectLocatorImpl().findObject("treeService");
            treeService = (UMLBrowserTreeService)(obj);
         } catch (Exception e) {
            throw new ServiceLocatorException("Exp while locating tree serice",
                                              e);
         }
      }
      return treeService;
   }

   public ApplicationService findCaCoreAPIService() throws ServiceLocatorException {
      if (cabioAppService == null)
        cabioAppService = ApplicationService.getRemoteInstance(getCaCoreAPIUrl());
      return cabioAppService;
   }

   public UMLBrowserQueryService findQuerySerivce() throws ServiceLocatorException {
      if (queryService == null) {
         try {
            Object obj =
               new SpringObjectLocatorImpl().findObject("umlBrowserQueryService");
            queryService = (UMLBrowserQueryService)(obj);
         } catch (Exception e) {
            throw new ServiceLocatorException("Exp while locating query serice",
                                              e);
         }
      }
      return queryService;
   }

    /**
   public UmlDomainModelQueryService findServiceLayerQueryService() throws ServiceLocatorException {
      if (serviceLayerQueryService == null) {
         try {
            Object obj =
               new SpringObjectLocatorImpl().findObject("umlServiceLayerQueryService");
            serviceLayerQueryService = (UmlDomainModelQueryService)(obj);
         } catch (Exception e) {
            throw new ServiceLocatorException("Exp while locating query serice",
                                              e);
         }
      }
      return serviceLayerQueryService;

   }
**/

    public void setCaCoreAPIUrl(String caCoreAPIUrl)
    {
        this.caCoreAPIUrl = caCoreAPIUrl;
    }

    public String getCaCoreAPIUrl()
    {
        return caCoreAPIUrl;
    }
}
