package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.actions;

import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;

import gov.nih.nci.ncicb.cadsr.service.CaDSRQueryService;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Collection;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class XmlDownloadAction extends BaseDispatchAction {
    protected static Log log = LogFactory.getLog(XmlDownloadAction.class.getName());
    protected static SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_hhmmss");
    public XmlDownloadAction() {
    }
    
    public ActionForward xmlDownloadPopup(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {
          Collection<UMLAttributeMetadata> attributes = (Collection<UMLAttributeMetadata>)getSessionObject(request,UMLBrowserFormConstants.CLASS_ATTRIBUTES);
          if (attributes.size()>0){
              return mapping.findForward("success");
          }
          else {
              return mapping.findForward("nodata");
          }
      }
      
    public ActionForward xmlDownload(
      ActionMapping mapping,
      ActionForm form,
      HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {
       try {
        Collection<UMLAttributeMetadata> attributes = (Collection<UMLAttributeMetadata>)getSessionObject(request,UMLBrowserFormConstants.CLASS_ATTRIBUTES);        
        CaDSRQueryService caDSRQueryService = getAppServiceLocator().findCaDSRQueryService();
        String xmlString = caDSRQueryService.getXMLForAttributes(attributes);
        response.setContentType("application/octet-stream"); 
        String fileName="DataElementDownload_"+sdf.format(new Date())+".xml";
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        response.getWriter().write(xmlString);
        return null;
       }
       catch(Exception e) {
           log.error("Error getting data element xml for attributes.",e);
           throw new ServletException("Error getting data element xml for attributes.",e);
       }
    }
}
