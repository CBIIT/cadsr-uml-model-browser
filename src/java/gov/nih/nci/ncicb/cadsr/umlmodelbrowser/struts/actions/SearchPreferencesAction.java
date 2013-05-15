package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.SearchPreferences;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeBacker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class SearchPreferencesAction extends BaseDispatchAction {
    public SearchPreferencesAction() {
    }
    
    public ActionForward gotoSearchPreferences(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
          DynaActionForm dynaForm = (DynaActionForm) form;
          SearchPreferences searchPreferences = (SearchPreferences)getSessionObject(request, UMLBrowserFormConstants.SEARCH_PREFERENCES);
          if (searchPreferences == null) {
               searchPreferences = new SearchPreferences();
               searchPreferences.reset();
          }
          dynaForm.set(UMLBrowserFormConstants.EXCLUDE_TEST_CONTEXT, Boolean.toString(searchPreferences.isExcludeTestContext()));
          dynaForm.set(UMLBrowserFormConstants.EXCLUDE_TRAINING_CONTEXT, Boolean.toString(searchPreferences.isExcludeTrainingContext()));
          
          return mapping.findForward("success");
      }
      
    public ActionForward resetSearchPreferences(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {
      SearchPreferences searchPreferences = (SearchPreferences)getSessionObject(request, UMLBrowserFormConstants.SEARCH_PREFERENCES);
      if (searchPreferences == null) {
             searchPreferences = new SearchPreferences();
       }
       searchPreferences.reset();
       removeInitLookupValues(request);
       setSessionObject(request, UMLBrowserFormConstants.SEARCH_PREFERENCES, searchPreferences);
       return gotoSearchPreferences(mapping, form, request, response);
    }
    
    public ActionForward setSearchPreferences(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {

      DynaActionForm dynaForm = (DynaActionForm) form;
      String excludeTestContext = (String)dynaForm.get(UMLBrowserFormConstants.EXCLUDE_TEST_CONTEXT);
      String excludeTrainingContext = (String)dynaForm.get(UMLBrowserFormConstants.EXCLUDE_TRAINING_CONTEXT);      
      SearchPreferences searchPreferences = (SearchPreferences)getSessionObject(request, UMLBrowserFormConstants.SEARCH_PREFERENCES);
      if (searchPreferences == null) {
           searchPreferences = new SearchPreferences();
      }
      if ("TRUE".equalsIgnoreCase(excludeTestContext)) {
          searchPreferences.setExcludeTestContext(true);
      }
      else {
          searchPreferences.setExcludeTestContext(false);
      }
      if ("TRUE".equalsIgnoreCase(excludeTrainingContext)) {
          searchPreferences.setExcludeTrainingContext(true);
      }
      else {
          searchPreferences.setExcludeTrainingContext(false);
      }
      removeInitLookupValues(request);
      setSessionObject(request, UMLBrowserFormConstants.SEARCH_PREFERENCES, searchPreferences);
      TreeBacker treeBacker =  (TreeBacker)getSessionObject(request, "treeBacker");
      treeBacker.updateModel(searchPreferences.isExcludeTestContext(),
      searchPreferences.isExcludeTrainingContext());
      return mapping.findForward("success");
    }    
}
