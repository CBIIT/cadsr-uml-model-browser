/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.cadsr.domain.AdministeredComponentContact;
import gov.nih.nci.cadsr.domain.ClassificationScheme;
import gov.nih.nci.cadsr.domain.ReferenceDocument;
import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.ncicb.cadsr.service.CaDSRQueryService;
import gov.nih.nci.ncicb.cadsr.service.UMLBrowserQueryService;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ReferenceDocumentAttachment;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;
import gov.nih.nci.ncicb.cadsr.util.AdministeredComponentContactComparator;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

public class DetailsPageAction extends BaseDispatchAction {
    public DetailsPageAction() {
    }
    
    public ActionForward projectDetailsPage(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception 
     {
      try {
        DynaActionForm dynaForm = (DynaActionForm) form;
        String projectIdseq = (String)dynaForm.get(UMLBrowserFormConstants.PROJECT_IDSEQ);
        if ((projectIdseq == null)||(projectIdseq.equals(""))){
           saveError("cadsr.umlbrowser.projectDetails.idmissing", request);
           log.error("cadsr.umlbrowser.projectDetails.idmissing");
           return mapping.findForward("failure");
         }
         UMLBrowserQueryService queryService = getAppServiceLocator().findQuerySerivce();
         Project project = new Project();
         project.setId(projectIdseq);
         List<Project> projects =queryService.findProject(project);
         if (projects.size() == 0) {             
             saveError(new ActionMessage("cadsr.umlbrowser.projectDetails.projectnotfound",projectIdseq),request);
             log.error("cadsr.umlbrowser.projectDetails.projectnotfound");
             return mapping.findForward("failure");             
         }
         setSessionObject(request,UMLBrowserFormConstants.PROJECT_DETAILS, projects.get(0));
         Project prj = projects.get(0);
         ClassificationScheme cs =prj.getClassificationScheme();
         setSessionObject(request,UMLBrowserFormConstants.PROJECT_DETAILS_CS,cs);
         Collection<AdministeredComponentContact> contacts = cs.getAdministeredComponentContactCollection();
         List<AdministeredComponentContact> lc= new ArrayList(contacts.size());
         lc.addAll(contacts);
         Collections.sort(lc, new AdministeredComponentContactComparator());
         setSessionObject(request, UMLBrowserFormConstants.PROJECT_DETAILS_CONTACTS,lc);
         //Retrieve ReferenceDocumentAttachment
         Collection<ReferenceDocument> rds = cs.getReferenceDocumentCollection();
         Map rdas = new HashMap(rds.size());
         CaDSRQueryService caDSRQueryService = getAppServiceLocator().findCaDSRQueryService();
         for(ReferenceDocument rd:rds){
             rdas.put(rd.getId(),caDSRQueryService.getReferenceDocumentAttachments(rd.getId()));
         }
         setSessionObject(request, UMLBrowserFormConstants.PROJECT_DETAILS_RDAS, rdas);
         return mapping.findForward("success");
      }
      catch(Exception e){
          log.error("Error getting project details.",e);
          throw e;
      }
    }  
    
    public ActionForward viewReferenceDocumentAttachment(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws Exception
     {
      try {    
          DynaActionForm dynaForm = (DynaActionForm) form;
          String rdaName = (String)dynaForm.get(UMLBrowserFormConstants.PROJECT_REFERENCE_DOCUMENT_ATT_NAME);
          if ((rdaName == null)||(rdaName.equals(""))) {
              saveError("cadsr.umlbrowser.referenceDocumentAttachment.namemissing",request);
              log.error("cadsr.umlbrowser.referenceDocumentAttachment.namemissing");
              return mapping.findForward("failure");
          }
          CaDSRQueryService caDSRQueryService = getAppServiceLocator().findCaDSRQueryService();
          ReferenceDocumentAttachment rda = caDSRQueryService.getReferenceDocumentAttachment(rdaName);
          if (rda == null) {
              saveError("cadsr.umlbrowser.referenceDocumentAttachment.notfound",request);
              log.error("cadsr.umlbrowser.referenceDocumentAttachment.notfound");
              return mapping.findForward("failure");
          }
          Blob blob = rda.getBlobContent();
          response.setContentType(rda.getMimeType());
          InputStream is = blob.getBinaryStream();
          response.setBufferSize(4*1024);
          if (is != null) {
             byte [] buf = new byte[4 * 1024]; // 4K buffer               
             int bytesRead;
             OutputStream out= response.getOutputStream();
             while ((bytesRead = is.read(buf)) != -1) {
                 out.write(buf, 0, bytesRead);
             }
           }
           response.setStatus(HttpServletResponse.SC_OK);
           return null;
      }
    catch(Exception e){
        log.error("Error getting reference document attachment.",e);
        throw e;
    }
    }    
    
}
