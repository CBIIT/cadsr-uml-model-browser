package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean;
import gov.nih.nci.ncicb.cadsr.struts.common.BaseDispatchAction;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLAttribute;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UmlClass;

import gov.nih.nci.ncicb.cadsr.util.StringPropertyComparator;

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
      Collection umlClasses = new ArrayList();
      UmlClass umlCls = new UmlClass("Agent","gov.nih.nci.cabio.domain", "Cancer Bioinformatics Infrastructure Objects (caBIO)",
      "caCore", "Agent", "1", "2223333");
      umlClasses.add(umlCls);
      umlCls = new UmlClass("Anomaly","gov.nih.nci.cabio.domain", "Enterprise Vocabulary Services (EVS)",
              "caCore", "Molecular Genetic Abnormality", "1", "2223332");
      umlClasses.add(umlCls);
        umlCls = new UmlClass("Clone","gov.nih.nci.cabio.domain", "Cancer Bioinformatics Infrastructure Objects (caBIO)",
                "caCore", "IMAGE Clone Object", "1", "2223315");
        umlClasses.add(umlCls);

      umlCls = new UmlClass("Tissue","gov.nih.nci.evs.domain", "Enterprise Vocabulary Services",
              "caCore", "Tissue", "1", "2192778");
      umlClasses.add(umlCls);

      setSessionObject(request,  UMLBrowserFormConstants.CLASS_SEARCH_RESULTS, umlClasses,true);    
        setSessionObject(request,  UMLBrowserFormConstants.CLASS_ATTRIBUTE_VIEW, true, true);    

        PaginationBean pb = new PaginationBean();

        if (umlClasses != null) {
          pb.setListSize(umlClasses.size());
        }
        UmlClass aClass = null;
        if(umlClasses.size()>0)
        {
          Object[] classArr = umlClasses.toArray();
          aClass=(UmlClass)classArr[0];
          StringPropertyComparator comparator = new StringPropertyComparator(aClass.getClass());
          comparator.setPrimary("className");
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

      UMLAttribute umlAtt = new UMLAttribute("name", "java.lang.String", "2178533",  "Name",
      "2222841", "Agent Name", "Agent Name java.lang.String", "2223871");
      umlAttributes.add(umlAtt);

        umlAtt = new UMLAttribute("id", "java.lang.Long", "2178534",  "Identifier",
              "2222848", "Agent Identifier","Agent Identifier java.lang.Long", "2223865");
              umlAttributes.add(umlAtt);
        umlAtt = new UMLAttribute("comment", "java.lang.String", "2178533",  "Comment",
              "2223274", "Agent Comment", "Agent Comment java.lang.String", "2223869");
              umlAttributes.add(umlAtt);
        umlAtt = new UMLAttribute("NSCNumber", "java.lang.Long", "2178534",  "NSC Code",
              "2223294", "Agent NSC Code","Agent NSC Code java.lang.Long", "2223866");
              umlAttributes.add(umlAtt);


                umlAtt = new UMLAttribute("isCMAPAgent", "java.lang.Boolean", "2178538",  "CMAP Ontology ID",
                      "2223295", "Agent CMAP Ontology ID","Agent CMAP Ontology ID java.lang.Boolean", "2223867");
                      umlAttributes.add(umlAtt);
        umlAtt = new UMLAttribute("EVSId", "java.lang.String", "2178533",  "NCI Concept Code",
              "2223296", "Agent NCI Concept Code","Agent NCI Concept Code java.lang.String", "2223868");
              umlAttributes.add(umlAtt);
              
        umlAtt = new UMLAttribute("source", "java.lang.String", "2178533",  "Source",
              "2223297", "Agent Source","Agent Source java.lang.String", "2223870");
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
          StringPropertyComparator comparator = new StringPropertyComparator(anAttribute.getClass());
          comparator.setPrimary("attributeName");
          comparator.setOrder(comparator.ASCENDING);
          Collections.sort((List)umlAttributes,comparator);
          setSessionObject(request,UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR,comparator);      
        }
          
        setSessionObject(request, UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION, pb,true);


      return mapping.findForward("showAttributes");
    }
    
}