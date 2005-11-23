package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;


import gov.nih.nci.cadsr.service.UmlDomainModelQueryService;

import gov.nih.nci.cadsr.service.impl.AdminComponentQueryService;

import gov.nih.nci.cadsr.service.impl.ConceptQueryService;

import gov.nih.nci.cadsr.service.impl.DataElementConceptQueryService;

import gov.nih.nci.cadsr.service.impl.DataElementQueryService;

import gov.nih.nci.cadsr.service.impl.ObjectClassQueryService;

import gov.nih.nci.cadsr.service.impl.PropertyQueryService;

import javax.servlet.ServletContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public abstract class BaseAction extends Action {
  private WebApplicationContext webAppContext;

  private UmlDomainModelQueryService umlDomainModelQueryService;
  private AdminComponentQueryService adminComponentQueryService;
  private ConceptQueryService conceptQueryService;
  private DataElementConceptQueryService dataElementConceptQueryService;
  private DataElementQueryService dataElementQueryService;
  private ObjectClassQueryService objectClassQueryService;
  private PropertyQueryService propertyQueryService;

  public ActionForward execute(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception {


    return executeAction(mapping, form, request, response);
  }

  public abstract ActionForward executeAction(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws Exception;

  public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		ServletContext servletContext = actionServlet.getServletContext();
		WebApplicationContext wac =
			WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

    umlDomainModelQueryService = (UmlDomainModelQueryService)wac.getBean("umlDomainModelQueryService");
    adminComponentQueryService = (AdminComponentQueryService)wac.getBean("adminComponentQueryService");
    conceptQueryService = (ConceptQueryService)wac.getBean("conceptQueryService");
    dataElementConceptQueryService = (DataElementConceptQueryService)wac.getBean("dataElementConceptQueryService");
    dataElementQueryService = (DataElementQueryService)wac.getBean("dataElementQueryService");
    objectClassQueryService = (ObjectClassQueryService)wac.getBean("objectClassQueryService");
    propertyQueryService = (PropertyQueryService)wac.getBean("propertyQueryService");

	}


    public UmlDomainModelQueryService getUmlDomainModelQueryService()
    {
        return umlDomainModelQueryService;
    }

    public AdminComponentQueryService getAdminComponentQueryService()
    {
        return adminComponentQueryService;
    }

    public ConceptQueryService getConceptQueryService()
    {
        return conceptQueryService;
    }

    public DataElementConceptQueryService getDataElementConceptQueryService()
    {
        return dataElementConceptQueryService;
    }

    public DataElementQueryService getDataElementQueryService()
    {
        return dataElementQueryService;
    }

    public ObjectClassQueryService getObjectClassQueryService()
    {
        return objectClassQueryService;
    }

    public PropertyQueryService getPropertyQueryService()
    {
        return propertyQueryService;
    }
}
