package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.ncicb.cadsr.CaDSRConstants;
import gov.nih.nci.ncicb.cadsr.CommonNavigationConstants;
import gov.nih.nci.ncicb.cadsr.exception.FatalException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import javax.servlet.ServletContext;

import gov.nih.nci.cadsr.service.UmlDomainModelQueryService;

import gov.nih.nci.cadsr.service.impl.AdminComponentQueryService;

import gov.nih.nci.cadsr.service.impl.ConceptQueryService;

import gov.nih.nci.cadsr.service.impl.DataElementConceptQueryService;

import gov.nih.nci.cadsr.service.impl.DataElementQueryService;

import gov.nih.nci.cadsr.service.impl.ObjectClassQueryService;

import gov.nih.nci.cadsr.service.impl.PropertyQueryService;
import org.apache.struts.action.ActionServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Base DispatchAction for all  DispatchActions
 */
abstract public class BaseDispatchAction extends DispatchAction implements CaDSRConstants,CommonNavigationConstants
    {
  protected static Log log = LogFactory.getLog(BaseDispatchAction.class.getName());

 private WebApplicationContext webAppContext;

  private UmlDomainModelQueryService umlDomainModelQueryService;
  private AdminComponentQueryService adminComponentQueryService;
  private ConceptQueryService conceptQueryService;
  private DataElementConceptQueryService dataElementConceptQueryService;
  private DataElementQueryService dataElementQueryService;
  private ObjectClassQueryService objectClassQueryService;
  private PropertyQueryService propertyQueryService;




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
  /**
   * Retrieve an object from the application scope by its name. This is a
   * convience method.
   */


  /**
   * Retrieve a session object based on the request and the attribute name.
   */
  protected Object getSessionObject(
    HttpServletRequest req,
    String attrName) {
    Object sessionObj = null;
    HttpSession session = req.getSession(false);

    if (session != null) {
      sessionObj = session.getAttribute(attrName);
    }

    return sessionObj;
  }

  /**
   * Remove a session object based on the request and the attribute name.
   */
  protected void removeSessionObject(
    HttpServletRequest req,
    String attrName) {
    HttpSession session = req.getSession(false);

    if (session != null) {
      session.removeAttribute(attrName);
    }
  }

  /**
   * Add an object to session based on the request and the attribute name.
   */
  protected void setSessionObject(
    HttpServletRequest req,
    String attrName,
    Object sessionObject) {
    HttpSession session = req.getSession(false);

    if (session != null) {
      session.setAttribute(attrName, sessionObject);
    }
  }

  /**
   * Add an object to session based on the request and the attribute name.
   * Reset praram label the attribute to be removed when logged out or system
   * error
   */
  protected void setSessionObject(
    HttpServletRequest req,
    String attrName,
    Object sessionObject,
    boolean clear) {
    HttpSession session = req.getSession(false);

    if (session != null) {
      session.setAttribute(attrName, sessionObject);

      if (clear) {
        setObjectsForClear(session,attrName);
      }
      else {
        setSessionObject(req, attrName, sessionObject);
      }
    }
  }




  protected void saveError(
      String key,
       HttpServletRequest request) {
    if (key != null) {
      ActionError errorMessage = new ActionError(key);
      ActionErrors errorMessages = null;
      errorMessages = (ActionErrors)request.getAttribute(Globals.ERROR_KEY);
      if(errorMessages==null)
        errorMessages = new ActionErrors();

      errorMessages.add(errorMessages.GLOBAL_ERROR, errorMessage);
      saveErrors(request,errorMessages);
    }
  }
  protected void saveMessage(
    String key,
    HttpServletRequest request) {
    if (key != null) {
      ActionMessage message = new ActionMessage(key);

      ActionMessages messages = null;
      messages = (ActionMessages)request.getAttribute(Globals.MESSAGE_KEY);
      if(messages==null)
        messages = new ActionMessages();

      messages.add(messages.GLOBAL_MESSAGE, message);
      saveMessages(request, messages);
    }
  }

  /**
   * This Action forwards to the default  home.
   *
   * @param mapping The ActionMapping used to select this instance.
   * @param form The optional ActionForm bean for this request.
   * @param request The HTTP Request we are processing.
   * @param response The HTTP Response we are processing.
   *
   * @return
   *
   * @throws IOException
   * @throws ServletException
   */
   public ActionForward sendHome;

  /**
   * Sets default method name if no method is specified
   *
   * @return ActionForward
   *
   * @throws Exception
   */
  protected ActionForward dispatchMethod(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response,
    String name) throws Exception {
    if ((name == null) || name.equals("")) {
      name = DEFAULT_METHOD;
    }

    try {
      return super.dispatchMethod(mapping, form, request, response, name);
    }
    catch (Throwable throwable) {
      HttpSession session = request.getSession();
      String userName = request.getRemoteUser();
      if(userName==null)
        userName="";
      Collection keys = (Collection)session.getAttribute(this.CLEAR_SESSION_KEYS);
      if(keys!=null)
      {
        Iterator it  = keys.iterator();
        while(it.hasNext())
        {
          session.removeAttribute((String)it.next());
        }
      }
      if(log.isFatalEnabled())
      {
        log.fatal(userName+": Exception in dispatchMethod in method "+name,throwable);
      }
      saveError(ERROR_FATAL, request);
      throw new FatalException(throwable);
    }
  }
  /**
   * Used by the action to check if a input form field has a value
   * @param str
   * @return
   */
  protected boolean hasValue(String str)
  {
    if(str==null)
      return false;
    if(str.equals(""))
      return false;
    return true;
  }



  private void setObjectsForClear(HttpSession session, String attrName)
  {
        Collection keys =
          (Collection) session.getAttribute(
            CLEAR_SESSION_KEYS);

        if (keys == null) {
          keys = new ArrayList();
        }

        keys.add(attrName);
        session.setAttribute(CLEAR_SESSION_KEYS, keys);

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
