package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;




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

	}



}
