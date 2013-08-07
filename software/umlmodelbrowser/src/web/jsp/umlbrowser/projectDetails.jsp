<%--L
  Copyright Oracle Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/cdebrowser.tld" prefix="cde"%>

<%@page import="gov.nih.nci.ncicb.cadsr.util.* " %>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserNavigationConstants" %>
<%
  UMLBrowserParams params = UMLBrowserParams.getInstance();
%>
<HEAD>
<TITLE>Project Details..</TITLE>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<LINK rel="stylesheet" TYPE="text/css" HREF="<html:rewrite page='/css/blaf.css' />">
</HEAD>
<BODY topmargin=0 bgcolor="#ffffff">
<SCRIPT LANGUAGE="JavaScript1.1" SRC='<html:rewrite page="/jsLib/JavaScript.js"/>'></SCRIPT>

<TABLE valign="top" width=100% Cellpadding=0 Cellspacing=0 border=0>
  <tr>

    <td valign="top" align="left" nowrap>

    <html:img page="/i/umlbrowserbanner.gif" border="0" />
    </td>

    <td align=right valign="top" colspan=2 nowrap>
      <TABLE Cellpadding=0 Cellspacing=0 border=0 >
        <TR>
          <TD valign="TOP" align="CENTER" width="1%" colspan=1><a href="javascript:window.close()" target="_top"><html:img page="/i/icon_return.gif" alt="Back" border="0"  width="32" height="32"/></a><br><font color=brown face=verdana size=1>&nbsp;Home&nbsp;</font></TD>
          <TD valign="TOP" align="CENTER" width="1%" colspan=1><A HREF="javascript:newBrowserWin('/help/','helpWin',700,600)"><html:img page="/i/icon_help.gif" alt="Task Help" border="0"  width="32" height="32" /></A><br><font color=brown face=verdana size=1>&nbsp;Help&nbsp;</font></TD>
        </TR>
      </TABLE>
    </td>
  </tr>
 </table>
  <table>
  <logic:present name="nciUser">
  <tr>
    <td valign="top" align="left" class="OraInlineInfoText" nowrap>
        <bean:message key="user.greet" />
    	<bean:write name="nciUser" property="username"  scope="session"/>
    </td>
  </tr>
 </logic:present>
  <logic:notPresent name="nciUser">
  <tr>
    <td height="2" align="left"  nowrap>
		&nbsp;
    </td>
  </tr>
 </logic:notPresent>
</TABLE>
<jsp:include page="/common/tab_inc.jsp" flush="true">
	<jsp:param name="label" value="Project" />
	<jsp:param name="urlPrefix" value="../" />
</jsp:include>

<table align="center" cellpadding="0" cellspacing="0" width="80%">
  <tbody><tr>
    <td class="OraHeaderSubSub" width="100%">Selected Project</td>
  </tr>
  <tr>
    <td width="100%"><img src="/i/beigedot.gif" align="top" border="0" height="1" width="99%"> </td>
  </tr>
</tbody></table>

<bean:define id="project" name="<%=UMLBrowserFormConstants.PROJECT_DETAILS%>" 
       type="gov.nih.nci.cadsr.umlproject.domain.Project"/>
<!--bean:define id="classificationScheme" name="project" property="classificationScheme"
       type="gov.nih.nci.cadsr.domain.ClassificationScheme" /-->
<bean:define id="classificationScheme" name="<%=UMLBrowserFormConstants.PROJECT_DETAILS_CS%>"
              type="gov.nih.nci.cadsr.domain.ClassificationScheme" />

<table align="center" bgcolor="#999966" cellpadding="1" cellspacing="1" width="80%">

 <tbody><tr class="OraTabledata">
    <td class="TableRowPromptText">Public ID:</td>
    <td class="OraFieldText">          		
       <bean:write name="classificationScheme" property="publicID"/>
    </td>
 </tr>

 <tr class="OraTabledata">
    <td class="TableRowPromptText">Version:</td>
    <td class="OraFieldText"><bean:write name="project" property="version"/> </td>
 </tr>

 <tr class="OraTabledata">
    <td class="TableRowPromptText" width="20%">Long Name:</td>
    <td class="OraFieldText"><bean:write name="project" property="longName"/></td>
 </tr>

 <tr class="OraTabledata">
    <td class="TableRowPromptText" width="20%">Short Name:</td>
    <td class="OraFieldText"><bean:write name="project" property="shortName"/></td>
 </tr>

 <tr class="OraTabledata">
    <td class="TableRowPromptText">Definition:</td>
    <td class="OraFieldText"><bean:write name="project" property="description"/></td>
 </tr>
 <tr class="OraTabledata">
    <td class="TableRowPromptText">Workflow Status:</td>
    <td class="OraFieldText"><bean:write name="classificationScheme" property="workflowStatusName" /></td>
 </tr>

 <tr class="OraTabledata">
    <td class="TableRowPromptText">Registration Status:</td>
    <td class="OraFieldText"><bean:write name="classificationScheme" property="registrationStatus" /></td>
 </tr>


</tbody></table>


<br>

<table align="center" cellpadding="0" cellspacing="0" width="80%">
  <tbody><tr>
    <td class="OraHeaderSubSub" width="100%">Reference Documents</td>
  </tr>
  <tr>
    <td><img src="/i/beigedot.gif" align="top" border="0" height="1" width="99%"> </td>
  </tr>
</tbody></table>

<table align="center" bgcolor="#999966" cellpadding="1" cellspacing="1" width="80%">
  <tbody><tr class="OraTableColumnHeader">
   <th>Document Name</th>
   <th>Document Type</th>
   <th>Document Text</th>
   <th>URL</th>
   <th>Attachments</th>
  </tr>
  <bean:define id="referenceDocuments" name="classificationScheme" property="referenceDocumentCollection"
       type="java.util.Collection" />
  <bean:define id="rdaMap" name="<%=UMLBrowserFormConstants.PROJECT_DETAILS_RDAS%>" 
       type="java.util.Map" />
     <logic:empty name="referenceDocuments">
        <tr class="OraTabledata">
            <td class="OraFieldText" colspan="5">No reference documents available. </td>
        </tr>
     </logic:empty>
     <logic:notEmpty name="referenceDocuments">
     <logic:iterate id="referenceDocument" name="referenceDocuments" type="gov.nih.nci.cadsr.domain.ReferenceDocument">
      <tr class="OraTabledata">
        <td class="OraFieldText"><bean:write name="referenceDocument" property="name"/></td>
        <td class="OraFieldText"><bean:write name="referenceDocument" property="type"/></td>
        <td class="OraFieldText">
           <bean:write name="referenceDocument" property="doctext"/>
        </td>
        <td class="OraFieldText">
            <logic:notEmpty name="referenceDocument" property="URL">
               <a href="<%=referenceDocument.getURL()%>" target="ReferenceDocumentURL" >
               <bean:write name="referenceDocument" property="URL"/>
               </a>
            </logic:notEmpty>
         </td>
         <%
            java.util.Collection rdas = (java.util.Collection)((java.util.Map)rdaMap).get(referenceDocument.getId());
            pageContext.setAttribute("rdas", rdas);
         %>
         <td>
            <logic:present name="rdas">
               <logic:iterate id="rda" name="rdas" type="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ReferenceDocumentAttachment">
                  <a href="javascript:newBrowserWin('<%=request.getContextPath()%>/umlbrowser/viewRefDocAttachment.do?method=viewReferenceDocumentAttachment&<%=UMLBrowserFormConstants.PROJECT_REFERENCE_DOCUMENT_ATT_NAME%>=<%=rda.getName()%>','ReferenceDocumentAttachemt')">
       		      <bean:write name="rda" property="name" />
       		   </a>
               </logic:iterate>
            </logic:present>
          </td>
       </tr>
       </logic:iterate>
     </logic:notEmpty>
</tbody></table>

<br>

<table align="center" cellpadding="0" cellspacing="0" width="80%">
  <tbody><tr>
    <td class="OraHeaderSubSub" width="100%">Contacts</td>
  </tr>
  <tr>
    <td><img src="/i/beigedot.gif" align="top" border="0" height="1" width="99%"> </td>
  </tr>
</tbody></table>
  <bean:define id="contacts" name="<%=UMLBrowserFormConstants.PROJECT_DETAILS_CONTACTS%>"
       type="java.util.Collection" />
<table align="center" bgcolor="#999966" cellpadding="1" cellspacing="1" width="80%">
  <tbody><tr class="OraTableColumnHeader">
    <th>Rank Order</th>
   <th> Name</th>
   <th> Type</th>
   <th> Contact Role</th>
   <th> Phone</th>
   <th> Email</th>
  </tr>
  <logic:empty name="contacts">
    <tr class="OraTabledata">
        <td class="OraFieldText" colspan="6">No contacts available. </td>
    </tr>
  </logic:empty>  
  <logic:iterate id="contact" name="contacts" type="gov.nih.nci.cadsr.domain.AdministeredComponentContact" >
       <tr class="OraTabledata">
         <td class="OraFieldText">
           <bean:write name="contact" property="rank" />
         </td>
         <logic:present name="contact" property="organization" >
            <bean:define id="organization" name="contact" property="organization" 
                   type="gov.nih.nci.cadsr.domain.Organization" />         
            <td class="OraFieldText">
                <bean:write name="organization" property="name" />
            </td>
            <td class="OraFieldText">    
                Organization
            </td>
            <td class="OraFieldText"><bean:write name="contact" property="contactRole" /></td>            
            <bean:define id="comms" name="organization" property="contactCommunication"
                type="java.util.Collection" />
            <td class="OraFieldText">
              <logic:iterate id="comm" name="comms" type="gov.nih.nci.cadsr.domain.ContactCommunication" >
                  <logic:equal name="comm" property="type" value="PHONE" >
                     <bean:write name="comm" property="value" /><br>
                  </logic:equal>
              </logic:iterate>
            </td>
            <td class="OraFieldText">
              <logic:iterate id="comm" name="comms" type="gov.nih.nci.cadsr.domain.ContactCommunication" >
                  <logic:equal name="comm" property="type" value="EMAIL" >
                     <a href="mailto:<%=comm.getValue()%>" >
                     <bean:write name="comm" property="value" />
                     </a><br>
                  </logic:equal>
              </logic:iterate>
            </td>            
         </logic:present>
         <logic:present name="contact" property="person" >
             <bean:define id="person" name="contact" property="person" 
                 type="gov.nih.nci.cadsr.domain.Person" /> 
            <td class="OraFieldText">
                 <bean:write name="person" property="lastName" />,&nbsp;
                 <bean:write name="person" property="firstName" />&nbsp;
                 <bean:write name="person" property="middleInitial" />
            </td>
            <td class="OraFieldText">    
                Person
            </td>
            <td class="OraFieldText"><bean:write name="contact" property="contactRole" /></td>            
            <bean:define id="comms" name="person" property="contactCommunication"
                type="java.util.Collection" />
            <td class="OraFieldText">
              <logic:iterate id="comm" name="comms" type="gov.nih.nci.cadsr.domain.ContactCommunication" >
                  <logic:equal name="comm" property="type" value="PHONE" >
                     <bean:write name="comm" property="value" /><br>
                  </logic:equal>
              </logic:iterate>
            </td>
            <td class="OraFieldText">
              <logic:iterate id="comm" name="comms" type="gov.nih.nci.cadsr.domain.ContactCommunication" >
                  <logic:equal name="comm" property="type" value="EMAIL" >
                     <a href="mailto:<%=comm.getValue()%>" >
                        <bean:write name="comm" property="value" />
                     </a><br>
                  </logic:equal>
              </logic:iterate>
            </td>                        
         </logic:present>         
       </tr>
  </logic:iterate>
</tbody></table>
<%@ include file="/common/common_bottom_border.jsp"%>
</BODY>
</HTML>