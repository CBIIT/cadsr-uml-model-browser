package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata;
import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;
import gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.SearchPreferences;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants;
import gov.nih.nci.ncicb.cadsr.util.BeanPropertyComparator;
import gov.nih.nci.ncicb.cadsr.util.UMLBrowserParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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
     * This Action forwards to the default umlbrowser home.
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
	  //added for New search method to clear the attributes search result from session
	  removeSessionObject(request, UMLBrowserFormConstants.CLASS_NAME);
	  removeSessionObject(request, UMLBrowserFormConstants.ATTRIBUT_NAME);
	  removeSessionObject(request, UMLBrowserFormConstants.PROJECT_IDSEQ);
	  removeSessionObject(request, UMLBrowserFormConstants.PROJECT_VERSION);
	  removeSessionObject(request, UMLBrowserFormConstants.SUB_PROJECT_IDSEQ);
	  removeSessionObject(request, UMLBrowserFormConstants.PACKAGE_IDSEQ);
	  //removeSessionObject(request, "TreeContext");
	  //Set the lookup values in the session
	  setInitLookupValues(request);
	  setSessionObject(request,  UMLBrowserFormConstants.SUBPROJECT_OPTIONS,
			  getSessionObject(request, UMLBrowserFormConstants.ALL_SUBPROJECTS ),true);
	  setSessionObject(request,  UMLBrowserFormConstants.PACKAGE_OPTIONS,
			  getSessionObject(request, UMLBrowserFormConstants.ALL_PACKAGES ),true);

	  return mapping.findForward("umlSearch");
  }

    public ActionForward classSearch(
    	ActionMapping mapping,
    	ActionForm form,
    	HttpServletRequest request,
    	HttpServletResponse response) throws Exception {
    	
    	DynaActionForm dynaForm = (DynaActionForm) form;
    	String resetCrumbs = (String) dynaForm.get(UMLBrowserFormConstants.RESET_CRUMBS);

    	UMLClassMetadata umlClass = this.populateClassFromForm(request,dynaForm);
    	SearchPreferences searchPreferences = (SearchPreferences)getSessionObject(request,UMLBrowserFormConstants.SEARCH_PREFERENCES);    	
    	this.findClassesLike(umlClass, searchPreferences, request);    	

    	return mapping.findForward("umlSearch");
    }

    public ActionForward attributeSearch(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception {

//      removeSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);


      DynaActionForm dynaForm = (DynaActionForm) form;
      Collection<UMLAttributeMetadata> umlAttributes= new ArrayList();
      UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
      UMLAttributeMetadata umlAtt = new UMLAttributeMetadata();
      String attName = ((String) dynaForm.get("attributeName")).trim();
      if (attName !=null && attName.length()>0)
         umlAtt.setName(attName.replace('*','%') );
      UMLClassMetadata umlClass = this.populateClassFromForm(request,dynaForm);
      if (umlClass != null)
         umlAtt.setUMLClassMetadata(umlClass);
      SearchPreferences searchPreferences = (SearchPreferences)getSessionObject(request, UMLBrowserFormConstants.SEARCH_PREFERENCES);
      umlAttributes = queryService.findUmlAttributes(umlAtt, searchPreferences);

      setupSessionForAttributeResults(umlAttributes, request);
      return mapping.findForward("showAttributes");
    }
    /*
     * newSearch method added for newSearch Button.
     * The method invokes the initSearch method
     */
    /*public ActionForward newSearch(
    		ActionMapping mapping,
    		ActionForm form,
    		HttpServletRequest request,
    		HttpServletResponse response) throws IOException, ServletException {
    	return initSearch(mapping, form, request, response);
    }*/

   public ActionForward getAttributesForClass(
     ActionMapping mapping,
     ActionForm form,
     HttpServletRequest request,
     HttpServletResponse response) throws IOException, ServletException {
     
     String breadCrumb = "";
     int selectedClassIndex = Integer.parseInt(request.getParameter("selectedClassIndex"));
     Collection umlClasses = (Collection) getSessionObject(request,  UMLBrowserFormConstants.CLASS_SEARCH_RESULTS);
     Iterator it = umlClasses.iterator();
     int umlClassesCounter = 0;
     UMLClassMetadata umlClass = null;
     while(it.hasNext() && (umlClassesCounter <= selectedClassIndex)){    	 
    	 umlClass = (UMLClassMetadata)it.next();    	    	 
    	 umlClassesCounter++;    	 
     }     
     //UMLClassMetadata umlClass =(UMLClassMetadata) umlClasses.toArray()[selectedClassIndex];     
     breadCrumb = umlClass.getObjectClass().getContext().getName() + ">>"
      + umlClass.getProject().getLongName() + ">>"
      + umlClass.getUMLPackageMetadata().getName() + ">>"
      + umlClass.getName();
     DynaActionForm dynaForm = (DynaActionForm) form;
     Collection<UMLAttributeMetadata> umlAttributes= umlClass.getUMLAttributeMetadataCollection();
     this.setupSessionForAttributeResults(umlAttributes, request);
     setFormValues(request,dynaForm); //GF 2579
     //String cName = (String) getSessionObject(request,UMLBrowserFormConstants.CLASS_NAME);
     //dynaForm.set("className", umlClass.getName());
     //dynaForm.set("className", cName);
     request.setAttribute(UMLBrowserFormConstants.ATTRIBUTE_CRUMB, breadCrumb);

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
   //getLazyAssociationsForClass(umlClasses);
   BeanPropertyComparator comparator = (BeanPropertyComparator)getSessionObject(request,UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR);
   comparator.setRelativePrimary(sortField);
   comparator.setOrder(sortOrder.intValue());
   //Initialize and add the PagenationBean to the Session
   PaginationBean pb = new PaginationBean();
   if (umlClasses != null) {
     pb.setListSize(umlClasses.size());
   }
   Collections.sort(umlClasses,comparator);   
   //GF 2579
   setFormValues(request,searchForm);
     
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
   Collections.sort(umlClasses, comparator);
   //GF 2579   
   setFormValues(request,searchForm);
   
   setSessionObject(request, UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION, pb,true);
   setSessionObject(request, ANCHOR, "results",true);
   return mapping.findForward(SUCCESS);
   }

   private void findClassesLike (UMLClassMetadata  umlClass, SearchPreferences searchPreferences,HttpServletRequest request ) throws Exception {
      Collection<UMLClassMetadata> umlClasses = new ArrayList();
      UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
      umlClasses = queryService.findUmlClass(umlClass, searchPreferences);

      setupSessionForClassResults(umlClasses, request);
   }

   private void setupSessionForAttributeResults(Collection<UMLAttributeMetadata> umlAttributes, HttpServletRequest request){
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW, false, true);
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_ATTRIBUTES, umlAttributes,true);

      PaginationBean pb = new PaginationBean();

      if (umlAttributes != null) {
         pb.setListSize(umlAttributes.size());
      }

      UMLAttributeMetadata anAttribute = null;
      if(umlAttributes.size()>0)        {
        //Object[] attArr = umlAttributes.toArray();
        anAttribute=(UMLAttributeMetadata)umlAttributes.iterator().next();
        BeanPropertyComparator comparator = new BeanPropertyComparator(anAttribute.getClass());
        comparator.setPrimary("name");
        comparator.setOrder(comparator.ASCENDING);
        Collections.sort((List)umlAttributes,comparator);
        setSessionObject(request,UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR,comparator);
        }

        setSessionObject(request, UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION, pb,true);

   }
   private void setupSessionForClassResults(Collection umlClasses, HttpServletRequest request){
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_SEARCH_RESULTS, umlClasses,true);
      setSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW, true, true);

      PaginationBean pb = new PaginationBean();

        if (umlClasses != null) {
          pb.setListSize(umlClasses.size());

        UMLClassMetadata aClass = null;
        if(umlClasses.size()>0)
        {
          aClass=(UMLClassMetadata)umlClasses.iterator().next();
          BeanPropertyComparator comparator = new BeanPropertyComparator(aClass.getClass());
          comparator.setPrimary("name");
          comparator.setOrder(comparator.ASCENDING);
          Collections.sort((List)umlClasses,comparator);
          setSessionObject(request,UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR,comparator);
          //getLazyAssociationsForClass(umlClasses);
        }
        }
        setSessionObject(request, UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION, pb,true);

   }

   private UMLClassMetadata populateClassFromForm(HttpServletRequest request,DynaActionForm dynaForm) {
      UMLClassMetadata umlClass = null;
      Project project = null;

      String className = ((String) dynaForm.get("className")).trim();
      if (className != null && className.length() >0) {
         umlClass = new UMLClassMetadata();
         className = className.replace('*','%');
         umlClass.setName(className);
      }
      setSessionObject(request,UMLBrowserFormConstants.CLASS_NAME,dynaForm.getString(UMLBrowserFormConstants.CLASS_NAME));
      
      String attributeName = ((String) dynaForm.get("attributeName")).trim();
      setSessionObject(request,UMLBrowserFormConstants.ATTRIBUT_NAME,dynaForm.getString(UMLBrowserFormConstants.ATTRIBUT_NAME));
      
      String projectId = ((String) dynaForm.get(UMLBrowserFormConstants.PROJECT_IDSEQ)).trim();
      if (projectId != null && projectId.length() >0) {
         if (umlClass == null)
            umlClass = new UMLClassMetadata();

         project = new Project();
         project.setId(projectId);
         umlClass.setProject(project);
      }
      setSessionObject(request,UMLBrowserFormConstants.PROJECT_IDSEQ,dynaForm.getString(UMLBrowserFormConstants.PROJECT_IDSEQ));
       
      String projectVersion = ((String) dynaForm.get(UMLBrowserFormConstants.PROJECT_VERSION)).trim();
      if (projectVersion != null && projectVersion.length() >0) {
        if (umlClass == null)
           umlClass = new UMLClassMetadata();
        if (project == null) {
            project = new Project();
            umlClass.setProject(project);
        }
        projectVersion = projectVersion.replace('*','%');
        project.setVersion(projectVersion);
      }
      setSessionObject(request,UMLBrowserFormConstants.PROJECT_VERSION,dynaForm.getString(UMLBrowserFormConstants.PROJECT_VERSION));

      UMLPackageMetadata packageMetadata = null;
      String subprojectId = ((String) dynaForm.get(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ)).trim();
      if (projectId != null && subprojectId.length() >0) {
         if (umlClass == null)
             umlClass = new UMLClassMetadata();
         SubProject subproject = new SubProject();
         subproject.setId(subprojectId);
         packageMetadata = new UMLPackageMetadata();
         packageMetadata.setSubProject(subproject);
      }
      setSessionObject(request,UMLBrowserFormConstants.SUB_PROJECT_IDSEQ,dynaForm.getString(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ));

      String packageId = ((String) dynaForm.get(UMLBrowserFormConstants.PACKAGE_IDSEQ)).trim();
      if (packageId != null && packageId.length() >0) {
         if (umlClass == null)
             umlClass = new UMLClassMetadata();

         if (packageMetadata == null)
            packageMetadata = new UMLPackageMetadata();
         packageMetadata.setId(packageId);
      }
      setSessionObject(request,UMLBrowserFormConstants.PACKAGE_IDSEQ,dynaForm.getString(UMLBrowserFormConstants.PACKAGE_IDSEQ));

      if (packageMetadata != null)
          umlClass.setUMLPackageMetadata(packageMetadata);
      return umlClass;
   }
   
   private void setFormValues(HttpServletRequest request, DynaActionForm form){
	      
	   form.set(UMLBrowserFormConstants.CLASS_NAME,(String)getSessionObject(request,UMLBrowserFormConstants.CLASS_NAME) );
	   form.set(UMLBrowserFormConstants.ATTRIBUT_NAME, (String) getSessionObject(request,UMLBrowserFormConstants.ATTRIBUT_NAME));
	   form.set(UMLBrowserFormConstants.PROJECT_IDSEQ,(String) getSessionObject(request,UMLBrowserFormConstants.PROJECT_IDSEQ));
	   form.set(UMLBrowserFormConstants.PROJECT_VERSION,(String) getSessionObject(request,UMLBrowserFormConstants.PROJECT_VERSION));
	   form.set(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ,(String) getSessionObject(request,UMLBrowserFormConstants.SUB_PROJECT_IDSEQ));
	   form.set(UMLBrowserFormConstants.PACKAGE_IDSEQ,(String) getSessionObject(request,UMLBrowserFormConstants.PACKAGE_IDSEQ));
	   
	   return;
   }
   
   private void reSetFormValues(HttpServletRequest request, DynaActionForm form){
	      
	   form.set(UMLBrowserFormConstants.CLASS_NAME, "" );
	   form.set(UMLBrowserFormConstants.ATTRIBUT_NAME,"");
	   form.set(UMLBrowserFormConstants.PROJECT_IDSEQ,"");
	   form.set(UMLBrowserFormConstants.PROJECT_VERSION,"");
	   form.set(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ,"");
	   form.set(UMLBrowserFormConstants.PACKAGE_IDSEQ,"");	   
	   return;
   }


   public ActionForward resetSubProjPkgOptions(
     ActionMapping mapping,
     ActionForm form,
     HttpServletRequest request,
     HttpServletResponse response) throws IOException, ServletException {

     DynaActionForm dynaForm = (DynaActionForm) form;

     String projectId = ((String) dynaForm.get(UMLBrowserFormConstants.PROJECT_IDSEQ)).trim();

     if (projectId == null || projectId.length() == 0) {
        setSessionObject(request,  UMLBrowserFormConstants.SUBPROJECT_OPTIONS,
           getSessionObject(request, UMLBrowserFormConstants.ALL_SUBPROJECTS ),true);
        setSessionObject(request,  UMLBrowserFormConstants.PACKAGE_OPTIONS,
           getSessionObject(request, UMLBrowserFormConstants.ALL_PACKAGES ),true);

     } else {
        Project project = setPackageOptionsForProjectId(request, projectId);

        if (project != null ){
           setSessionObject(request,  UMLBrowserFormConstants.SUBPROJECT_OPTIONS,
              project.getSubProjectCollection(), true);
        }

     }

     Object classView = getSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW);
     String showClass = null;
     if (classView != null)
        showClass=classView.toString();
     if (showClass == null ||  showClass.equalsIgnoreCase("true"))
        return mapping.findForward("umlSearch");

     return mapping.findForward("showAttributes");

     }

   public ActionForward resetPkgOptions(
     ActionMapping mapping,
     ActionForm form,
     HttpServletRequest request,
     HttpServletResponse response) throws IOException, ServletException {

     DynaActionForm dynaForm = (DynaActionForm) form;
     String projectId = ((String) dynaForm.get(UMLBrowserFormConstants.PROJECT_IDSEQ)).trim();
     String subprojId = ((String) dynaForm.get(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ)).trim();

     if (subprojId == null || subprojId.length() == 0) {
     // if subProject is ALL, set package options by project
        setPackageOptionsForProjectId(request, projectId);
     } else {
        SubProject subproject = null;
        Collection<SubProject> allSubProjects = (Collection) getSessionObject(request, UMLBrowserFormConstants.ALL_SUBPROJECTS);
        for (Iterator subprojIter =allSubProjects.iterator(); subprojIter.hasNext(); ) {
            subproject = (SubProject) subprojIter.next();
            if (subproject.getId().equalsIgnoreCase(subprojId))
               break;
        }

        if (subproject != null ){
           setSessionObject(request,  UMLBrowserFormConstants.PACKAGE_OPTIONS,
              subproject.getUMLPackageMetadataCollection(), true);

        }

     }
     String showClass = null;
     if (getSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW) != null)
        showClass=getSessionObject(request,  UMLBrowserFormConstants.CLASS_VIEW).toString();
     if (showClass == null ||  showClass.equalsIgnoreCase("true"))
        return mapping.findForward("umlSearch");

     return mapping.findForward("showAttributes");

     }

     private Project setPackageOptionsForProjectId (HttpServletRequest request, String projectId){
        Project project = null;
        Collection<Project> allProjects = (Collection) getSessionObject(request, UMLBrowserFormConstants.ALL_PROJECTS);
        for (Iterator projIter =allProjects.iterator(); projIter.hasNext(); ) {
            project = (Project) projIter.next();
            if (project.getId().equalsIgnoreCase(projectId))
               break;
        }

        if (project != null ){

           UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
           setSessionObject(request,  UMLBrowserFormConstants.PACKAGE_OPTIONS,
              queryService.getAllPackageForProject(project),true);
        }

        return project;

     }
     
     /*
      * Retrieves form values from the TreeBreadCrumb and sets them in session
      */
     private void getFormValuesFromTreeCrumb(HttpServletRequest request, UMLBrowserQueryService queryService){
    	 String treeBreadCrumb = request.getParameter(TreeConstants.TREE_BREADCRUMBS);
		 log.info("Setting formValues from TreeBreadCrumb: "+treeBreadCrumb);
		 String[] treeCrumbs = null;
		 String caDSRContext ="";
		 String context = "";
		 String projectsText = "";
		 String projectName ="";
		 String treeCrumbProjectId = "";
		 String subProjectName = "";
		 String treeCrumbSubProjectId = "";
		 String packageName = "";
		 String treeCrumbPackageId = "";
		 String className = "";    		 
		 if(treeBreadCrumb != null){
			 treeCrumbs = treeBreadCrumb.split(">>");    			 
			 for (int i=0; i<treeCrumbs.length; i++){
				 if(i==0)
					 caDSRContext = treeCrumbs[0];
				 if(i==1)
					 context = treeCrumbs[1];
				 if(i==2)
					 projectsText = treeCrumbs[2];
				 if(i==3)
					 projectName = treeCrumbs[3];
				 if(i==4)
					 subProjectName = treeCrumbs[4];
				 if(i==5)
					 packageName = treeCrumbs[5];
				 if(i==6)
					 className = treeCrumbs[6];    				 
			 }
		 }
		 List<Project> formProjects = new ArrayList<Project>();
		 List<SubProject> formSubProjects = new ArrayList<SubProject>();
		 List<UMLPackageMetadata> formPackages = new ArrayList<UMLPackageMetadata> ();
		 if(projectName != null || !projectName.equals("")){
			 Project formProject = new Project();
			 formProject.setLongName(projectName);
			 formProjects = queryService.findProject(formProject);
			 //System.out.println(".... found "+formProjects.size()+" projects with LongName "+projectName);
			 for (Object obj: formProjects){
				 Project proj = (Project)obj;
				 treeCrumbProjectId = proj.getId();				////Get Project Id to set in form				 
				 //System.out.println("........ProjectId: "+proj.getId());
				 formSubProjects = (List<SubProject>) proj.getSubProjectCollection();    			 
				 log.debug("--- "+formSubProjects.size()+" subProjects for the project "+projectName);
				 if(formSubProjects.size() != 0){
					 for(Object oSub: formSubProjects){
						 SubProject formSub = (SubProject) oSub;
						 if(subProjectName.equalsIgnoreCase(formSub.getName())){
							 treeCrumbSubProjectId = formSub.getId();							 
							 //System.out.println("........Sub_ProjectId: "+formSub.getId());    				 
						 }
					 }//Get subproject Id to set in form
				 }else{   //If subproject does not exist for a Project, Rearrange breadcrumbs
					 log.debug("---Rearranging BreadCrumbs");					 
					 className = packageName;
					 packageName = subProjectName;
				 }
				 formPackages = (List<UMLPackageMetadata>) proj.getUMLPackageMetadataCollection();
				 if(!packageName.equals("")){
					 for(Object oPackage: formPackages){
						 UMLPackageMetadata formPackage = (UMLPackageMetadata)oPackage;
						 if(packageName.equalsIgnoreCase(formPackage.getName())){
							 treeCrumbPackageId = formPackage.getId();

							 //System.out.println("........PackageId: "+formPackage.getId());
						 }
					 }
				 } //Get package Id to set in form
			 }
		 }
		 setSessionObject(request,UMLBrowserFormConstants.PROJECT_IDSEQ,treeCrumbProjectId);
		 setSessionObject(request,UMLBrowserFormConstants.SUB_PROJECT_IDSEQ,treeCrumbSubProjectId);
		 setSessionObject(request,UMLBrowserFormConstants.PACKAGE_IDSEQ,treeCrumbPackageId);
		 setSessionObject(request,UMLBrowserFormConstants.CLASS_NAME,className);
		 setSessionObject(request,UMLBrowserFormConstants.ATTRIBUT_NAME, "");
		 setSessionObject(request,UMLBrowserFormConstants.PROJECT_VERSION,"");		 
     }

     public ActionForward treeClassSearch(
    		 ActionMapping mapping,
    		 ActionForm form,
    		 HttpServletRequest request,
    		 HttpServletResponse response) throws IOException, ServletException {

    	 try {
    		 String searchType = request.getParameter("P_PARAM_TYPE");
    		 String searchId = request.getParameter("P_IDSEQ");    		 
    		 UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
    		 Collection umlClasses = null;
    		 //GF: 2579    		 
    		 DynaActionForm dynaForm = (DynaActionForm)form;
    		 reSetFormValues(request, dynaForm);
    		 getFormValuesFromTreeCrumb(request,queryService);  //This method will retrieve values from TreeBreadCrumb and set it in Session
    		 
    		 //GF 2579
    		 if (searchType.equalsIgnoreCase("Class")  ) {
    			 UMLClassMetadata umlClass = new UMLClassMetadata();
    			 umlClass.setId(searchId);
    			 UMLAttributeMetadata umlAttribute = new UMLAttributeMetadata();
    			 umlAttribute.setUMLClassMetadata(umlClass);
    			 Collection umlAttributes = queryService.findUmlAttributes(umlAttribute);
    			 //System.out.println("---Class Id: "+searchId);    			 
    			 dynaForm.set(UMLBrowserFormConstants.CLASS_NAME,(String)getSessionObject(request, UMLBrowserFormConstants.CLASS_NAME));
    			 dynaForm.set(UMLBrowserFormConstants.PROJECT_IDSEQ,(String)getSessionObject(request, UMLBrowserFormConstants.PROJECT_IDSEQ));
    			 dynaForm.set(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ, (String)getSessionObject(request, UMLBrowserFormConstants.SUB_PROJECT_IDSEQ));
    			 dynaForm.set(UMLBrowserFormConstants.PACKAGE_IDSEQ, (String)getSessionObject(request, UMLBrowserFormConstants.PACKAGE_IDSEQ));
    			 setupSessionForAttributeResults(umlAttributes, request);
    			 return mapping.findForward("showAttributes");
    		 }
    		 if (searchType.equalsIgnoreCase("Context")  ) {
    			 //System.out.println("---Context Id: "+searchId);    			 
    			 umlClasses = queryService.getClassesForContext(searchId);
    		 }
    		 if (searchType.equalsIgnoreCase("Project")  ) {
    			 UMLClassMetadata umlClass = new UMLClassMetadata();
    			 Project project = new Project();
    			 project.setId(searchId);
    			 //System.out.println("---Project Id: "+searchId);
    			 dynaForm.set(UMLBrowserFormConstants.PROJECT_IDSEQ,searchId);
    			 umlClass.setProject(project);
    			 umlClasses = queryService.findUmlClass(umlClass);    			 
    		 }
    		 if (searchType.equalsIgnoreCase("Container")  ) {
    			 umlClasses = queryService.findUmlClassForContainer(searchId);
    		 }
    		 if (searchType.equalsIgnoreCase("SubProject")  ) {
    			 UMLClassMetadata umlClass = new UMLClassMetadata();
    			 SubProject subproject = new SubProject();
    			 subproject.setId(searchId);
    			 //System.out.println("---SubProject Id: "+searchId);
    			 dynaForm.set(UMLBrowserFormConstants.PROJECT_IDSEQ,(String)getSessionObject(request, UMLBrowserFormConstants.PROJECT_IDSEQ));
    			 dynaForm.set(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ,searchId);
    			 UMLPackageMetadata packageMetadata= new UMLPackageMetadata();
    			 packageMetadata.setSubProject(subproject);
    			 umlClass.setUMLPackageMetadata(packageMetadata);
    			 umlClasses = queryService.findUmlClass(umlClass);
    		 }
    		 if (searchType.equalsIgnoreCase("Package")  ) {
    			 UMLClassMetadata umlClass = new UMLClassMetadata();
    			 UMLPackageMetadata packageMetadata= new UMLPackageMetadata();
    			 packageMetadata.setId(searchId);
    			 //System.out.println("---Package Id: "+searchId);
    			 dynaForm.set(UMLBrowserFormConstants.PROJECT_IDSEQ,(String)getSessionObject(request, UMLBrowserFormConstants.PROJECT_IDSEQ));
    			 dynaForm.set(UMLBrowserFormConstants.SUB_PROJECT_IDSEQ, (String)getSessionObject(request, UMLBrowserFormConstants.SUB_PROJECT_IDSEQ));
    			 dynaForm.set(UMLBrowserFormConstants.PACKAGE_IDSEQ,searchId);
    			 umlClass.setUMLPackageMetadata(packageMetadata);
    			 umlClasses = queryService.findUmlClass(umlClass);
    		 }
    		 setupSessionForClassResults(umlClasses, request);
    	 } catch (Exception e) {
    		 log.error(e);
    		 throw new ServletException("Error occurred while performing tree search", e);

    	 }
    	 return mapping.findForward("umlSearch");
     }
  private void getLazyAssociationsForClass(Collection classList)
  {
     if(classList==null) return;

     int itemPerPage = UMLBrowserParams.getInstance().getItemPerPage();
     int count = 0;
      for (Iterator resultsIterator = classList.iterator();
              resultsIterator.hasNext();) {

          UMLClassMetadata returnedClass = (UMLClassMetadata) resultsIterator.next();
              for (Iterator mdIterator = returnedClass.getSemanticMetadataCollection().iterator();
                      mdIterator.hasNext();) {
                      SemanticMetadata metaData = (SemanticMetadata) mdIterator.next();                      
                      }
              }
          ++count;
          if(itemPerPage<=count) return;

      }
}