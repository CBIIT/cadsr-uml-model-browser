package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
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
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW, true, true);

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

//      removeSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);

      DynaActionForm dynaForm = (DynaActionForm) form;
      Collection<UMLAttributeMetadata> umlAttributes= new ArrayList();
      UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
      UMLAttributeMetadata umlAtt = new UMLAttributeMetadata();
      umlAtt.setName( (String) dynaForm.get("attributeName"));

      umlAttributes = queryService.findUmlAttributes(umlAtt);  

      setSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW, false, true);
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_ATTRIBUTES, umlAttributes,true);

      PaginationBean pb = new PaginationBean();

      if (umlAttributes != null) {
         pb.setListSize(umlAttributes.size());
      }

      UMLAttributeMetadata anAttribute = null;
      if(umlAttributes.size()>0)        {
        Object[] attArr = umlAttributes.toArray();
        anAttribute=(UMLAttributeMetadata)attArr[0];
        BeanPropertyComparator comparator = new BeanPropertyComparator(anAttribute.getClass());
        comparator.setPrimary("name");
        comparator.setOrder(comparator.ASCENDING);
        Collections.sort((List)umlAttributes,comparator);
        setSessionObject(request,UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR,comparator);
        }

        setSessionObject(request, UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION, pb,true);


      return mapping.findForward("showAttributes");
    }
    
   public ActionForward getAttributesForClass(
     ActionMapping mapping,
     ActionForm form,
     HttpServletRequest request,
     HttpServletResponse response) throws IOException, ServletException {

     int selectedClassIndex = Integer.parseInt(request.getParameter("selectedClassIndex"));
     Collection umlClasses = (Collection) getSessionObject(request,  UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);
     UMLClassMetadata umlClass =(UMLClassMetadata) umlClasses.toArray()[selectedClassIndex];
     DynaActionForm dynaForm = (DynaActionForm) form;
     Collection<UMLAttributeMetadata> umlAttributes= umlClass.getUMLAttributeMetadataCollection();

     setSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW, false, true);
     setSessionObject(request,  UMLBrowserFormConstants.CLASS_ATTRIBUTES, umlAttributes,true);

     PaginationBean pb = new PaginationBean();

     if (umlAttributes != null) {
        pb.setListSize(umlAttributes.size());
     }

     UMLAttributeMetadata anAttribute = null;
     if(umlAttributes.size()>0)        {
       Object[] attArr = umlAttributes.toArray();
       anAttribute=(UMLAttributeMetadata)attArr[0];
       BeanPropertyComparator comparator = new BeanPropertyComparator(anAttribute.getClass());
       comparator.setPrimary("name");
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
   public ActionForward sortAttributes(
   ActionMapping mapping,
   ActionForm form,
   HttpServletRequest request,
   HttpServletResponse response) throws IOException, ServletException {
   //Set the lookup values in the session
   DynaActionForm searchForm = (DynaActionForm) form;
   String sortField = (String) searchForm.get("sortField");
   Integer sortOrder = (Integer) searchForm.get("sortOrder");
   List umlClasses = (List)getSessionObject(request, UMLBrowserFormConstants.CLASS_ATTRIBUTES);
   BeanPropertyComparator comparator = (BeanPropertyComparator)getSessionObject(request,UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR);
   comparator.setRelativePrimary(sortField);
   comparator.setOrder(sortOrder.intValue());
   //Initialize and add the PagenationBean to the Session
   PaginationBean pb = new PaginationBean();
   if (umlClasses != null) {
     pb.setListSize(umlClasses.size());
   }
   Collections.sort(umlClasses,comparator);
   setSessionObject(request, UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION, pb,true);
   setSessionObject(request, ANCHOR, "results",true);  
   return mapping.findForward(SUCCESS);
   }
   
}