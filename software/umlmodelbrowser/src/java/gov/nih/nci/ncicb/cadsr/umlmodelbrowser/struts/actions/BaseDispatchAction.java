/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.ncicb.cadsr.CaDSRConstants;
import gov.nih.nci.ncicb.cadsr.CommonNavigationConstants;
import gov.nih.nci.ncicb.cadsr.exception.FatalException;

import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;

import gov.nih.nci.ncicb.cadsr.servicelocator.ServiceLocatorException;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.SearchPreferences;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import javax.servlet.ServletContext;


import org.apache.struts.action.ActionServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Base DispatchAction for all  DispatchActions
 */
public class BaseDispatchAction extends DispatchAction implements CaDSRConstants,CommonNavigationConstants
    {
  protected static Log log = LogFactory.getLog(BaseDispatchAction.class.getName());

  private WebApplicationContext webAppContext;
  private ApplicationServiceLocator appServiceLocator = null;


  public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		ServletContext servletContext = actionServlet.getServletContext();
		WebApplicationContext wac =
			WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);


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
        saveError(new ActionMessage(key),request);
    }
  }
  
  protected void saveError(ActionMessage errorMessage, HttpServletRequest request) {
        if (errorMessage != null) {
          ActionErrors errorMessages = null;
          errorMessages = (ActionErrors)request.getAttribute(Globals.ERROR_KEY);
          if(errorMessages==null)
            errorMessages = new ActionErrors();

          errorMessages.add(ActionMessages.GLOBAL_MESSAGE, errorMessage);
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

   /**
    *
    * @return ApplicationServiceLocator
    *
    * @throws ServiceStartupException
    */
   protected ApplicationServiceLocator getApplicationServiceLocator()
     throws ServiceLocatorException {
      return appServiceLocator;
   }

   public void setAppServiceLocator(ApplicationServiceLocator appServiceLocator) {
      this.appServiceLocator = appServiceLocator;
   }

   public ApplicationServiceLocator getAppServiceLocator() {
      if(appServiceLocator==null)
      appServiceLocator =
        (ApplicationServiceLocator) getApplicationObject(
          ApplicationServiceLocator.APPLICATION_SERVICE_LOCATOR_CLASS_KEY);
      if(appServiceLocator==null)
        throw new ServiceLocatorException("Could no find ApplicationServiceLocator with key ="+ ApplicationServiceLocator.APPLICATION_SERVICE_LOCATOR_CLASS_KEY);
      return appServiceLocator;
   }
   
   protected Object getApplicationObject(String attrName) {
     return servlet.getServletContext().getAttribute(attrName);
   }
   
   /**
    * Initializes the lookupvalues(projects, subprojects, package into session)
    *
    * @return ActionForward
    *
    * @throws Exception
    */
   protected void setInitLookupValues(HttpServletRequest req) {
      
     SearchPreferences searchPreferences = (SearchPreferences)getSessionObject(req, UMLBrowserFormConstants.SEARCH_PREFERENCES);
       if (searchPreferences == null) {
          searchPreferences = new SearchPreferences();
          searchPreferences.reset();
          setSessionObject(req, UMLBrowserFormConstants.SEARCH_PREFERENCES, searchPreferences);
       } 
    Object obj = getSessionObject(req, UMLBrowserFormConstants.ALL_PROJECTS);
    if (obj == null) {
       try {
       obj = getAppServiceLocator().findQuerySerivce().getAllProjects(searchPreferences);
       } catch (Exception e) {
         log.error("Exception occurred while retrieving all projects", e);
         e.printStackTrace();
       }
       setSessionObject(req, UMLBrowserFormConstants.ALL_PROJECTS, obj);
     }
     
      obj = getSessionObject(req, UMLBrowserFormConstants.ALL_SUBPROJECTS);

           if (obj == null) {
             try {
             obj = getAppServiceLocator().findQuerySerivce().getAllSubProjects(searchPreferences);
             } catch (Exception e) {
               log.error("Exception occurred while retrieving all projects", e);
                
             }
             setSessionObject(req, UMLBrowserFormConstants.ALL_SUBPROJECTS, obj);
           }     
           
      obj = getSessionObject(req, UMLBrowserFormConstants.ALL_PACKAGES);

           if (obj == null) {
             try {
             obj = getAppServiceLocator().findQuerySerivce().getAllPackages(searchPreferences);
             } catch (Exception e) {
               log.error("Exception occurred while retrieving all projects", e);
                
             }
             setSessionObject(req, UMLBrowserFormConstants.ALL_PACKAGES, obj);
           }     
           
   }   
   
    protected void removeInitLookupValues(HttpServletRequest req) { 
      setSessionObject(req, UMLBrowserFormConstants.ALL_PROJECTS, null);
      setSessionObject(req, UMLBrowserFormConstants.ALL_PACKAGES, null);
      setSessionObject(req, UMLBrowserFormConstants.ALL_SUBPROJECTS, null);
    }
   
}
