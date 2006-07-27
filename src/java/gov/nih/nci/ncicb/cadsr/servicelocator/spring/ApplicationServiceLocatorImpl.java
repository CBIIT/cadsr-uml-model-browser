package gov.nih.nci.ncicb.cadsr.servicelocator.spring;


import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.servicelocator.ServiceLocatorException;
import gov.nih.nci.ncicb.cadsr.util.UMLBrowserParams;
import gov.nih.nci.system.applicationservice.ApplicationService;


public class ApplicationServiceLocatorImpl implements ApplicationServiceLocator {

   private UMLBrowserQueryService queryService = null;

   private ApplicationService cabioAppService = null;
   
   public ApplicationServiceLocatorImpl() {
   }

   public ApplicationService findCaCoreAPIService() throws ServiceLocatorException {
      if (cabioAppService == null)
        cabioAppService = ApplicationService.getRemoteInstance(UMLBrowserParams.getInstance().getCadsrURL());
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

}
