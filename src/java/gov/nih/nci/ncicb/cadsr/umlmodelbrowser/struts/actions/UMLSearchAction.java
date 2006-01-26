package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLAttribute;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;
import gov.nih.nci.ncicb.cadsr.util.BeanPropertyComparator;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;


public class UMLSearchAction extends BaseDispatchAction
{

    protected static Log log = LogFactory.getLog(UMLSearchAction.class.getName());
    public UMLSearchAction()
    {
    }


  /**
   * This Action forwards to the default umplbrowser home.
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
  public ActionForward sendHome(
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException {
      removeSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);
    return mapping.findForward("umlbrowserHome");
  }

    /**
     * This Action forwards to the default umplbrowser home.
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
    public ActionForward initSearch(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {
      removeSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);
      return mapping.findForward("umlSearch");
    }

    public ActionForward classSearch(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {


      DynaActionForm dynaForm = (DynaActionForm) form;
      String resetCrumbs = (String) dynaForm.get(UMLBrowserFormConstants.RESET_CRUMBS);
      Collection<UMLClassMetadata> umlClasses = new ArrayList();
      UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
      UMLClassMetadata umlClass = new UMLClassMetadata();
      umlClass.setName( (String) dynaForm.get("className"));
       
      umlClasses = queryService.findUmlClass(umlClass);  

      setSessionObject(request,  UMLBrowserFormConstants.CLASS_SEARCH_RESULTS, umlClasses,true);
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_ATTRIBUTE_VIEW, true, true);

      PaginationBean pb = new PaginationBean();

        if (umlClasses != null) {
          pb.setListSize(umlClasses.size());
        }
        UMLClassMetadata aClass = null;
        if(umlClasses.size()>0)
        {
          Object[] classArr = umlClasses.toArray();
          aClass=(UMLClassMetadata)classArr[0];
          BeanPropertyComparator comparator = new BeanPropertyComparator(aClass.getClass());
          comparator.setPrimary("name");
          comparator.setOrder(comparator.ASCENDING);
          Collections.sort((List)umlClasses,comparator);
          setSessionObject(request,UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR,comparator);
        }

        setSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION, pb,true);


      return mapping.findForward("umlSearch");
    }

    public ActionForward attributeSearch(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {

      removeSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);

      DynaActionForm dynaForm = (DynaActionForm) form;
      Collection umlAttributes = new ArrayList();

      UMLAttribute umlAtt = new UMLAttribute("name", "java.lang.String", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain",  "Agent Name", "Agent Name java.lang.String", "2223871");
      umlAttributes.add(umlAtt);

        umlAtt = new UMLAttribute("id", "java.lang.Long", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain",  "Agent Identifier","Agent Identifier java.lang.Long", "2223865");
              umlAttributes.add(umlAtt);
        umlAtt = new UMLAttribute("comment", "java.lang.String", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain", "Agent Comment", "Agent Comment java.lang.String", "2223869");
              umlAttributes.add(umlAtt);
        umlAtt = new UMLAttribute("NSCNumber", "java.lang.Long", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain",  "Agent NSC Code","Agent NSC Code java.lang.Long", "2223866");
              umlAttributes.add(umlAtt);


                umlAtt = new UMLAttribute("isCMAPAgent", "java.lang.Boolean", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain",  "Agent CMAP Ontology ID","Agent CMAP Ontology ID java.lang.Boolean", "2223867");
                      umlAttributes.add(umlAtt);
        umlAtt = new UMLAttribute("EVSId", "java.lang.String", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain",  "Agent NCI Concept Code","Agent NCI Concept Code java.lang.String", "2223868");
              umlAttributes.add(umlAtt);

        umlAtt = new UMLAttribute("source", "java.lang.String", "caCore", 
      "caBIO", "gov.nih.nci.cabio.domain",  "Agent Source","Agent Source java.lang.String", "2223870");
              umlAttributes.add(umlAtt);


      setSessionObject(request,  UMLBrowserFormConstants.CLASS_ATTRIBUTES, umlAttributes,true);

        PaginationBean pb = new PaginationBean();

        if (umlAttributes != null) {
          pb.setListSize(umlAttributes.size());
        }

        UMLAttribute anAttribute = null;
        if(umlAttributes.size()>0)
        {
          Object[] attArr = umlAttributes.toArray();
          anAttribute=(UMLAttribute)attArr[0];
          BeanPropertyComparator comparator = new BeanPropertyComparator(anAttribute.getClass());
          comparator.setPrimary("attributeName");
          comparator.setOrder(comparator.ASCENDING);
          Collections.sort((List)umlAttributes,comparator);
          setSessionObject(request,UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR,comparator);
        }

        setSessionObject(request, UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION, pb,true);


      return mapping.findForward("showAttributes");
    }
   /**
   * Sorts search results by fieldName
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
   public ActionForward sortResult(
   ActionMapping mapping,
   ActionForm form,
   HttpServletRequest request,
   HttpServletResponse response) throws IOException, ServletException {
   //Set the lookup values in the session
   DynaActionForm searchForm = (DynaActionForm) form;
   String sortField = (String) searchForm.get("sortField");
   Integer sortOrder = (Integer) searchForm.get("sortOrder");
   List umlClasses = (List)getSessionObject(request,UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);
   BeanPropertyComparator comparator = (BeanPropertyComparator)getSessionObject(request,UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR);
   comparator.setRelativePrimary(sortField);
   comparator.setOrder(sortOrder.intValue());
   //Initialize and add the PagenationBean to the Session
   PaginationBean pb = new PaginationBean();
   if (umlClasses != null) {
     pb.setListSize(umlClasses.size());
   }
   Collections.sort(umlClasses,comparator);
   setSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION, pb,true);
   setSessionObject(request, ANCHOR, "results",true);  
   return mapping.findForward(SUCCESS);
   }
   
}