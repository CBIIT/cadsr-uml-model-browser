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
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants" %>

<HTML>
<%
  String urlPrefix = "../";
  String pageContextInfo = "";
  String paramIdseq = request.getParameter("P_PARAM_TYPE");
  
  if (paramIdseq == null) paramIdseq = "";
  String paramType = (String)request.getParameter("P_IDSEQ");
  if (paramType == null) paramType = "";
  
  if (!paramIdseq.equals("") && !paramType.equals("")){
    pageContextInfo = request.getParameter(TreeConstants.TREE_BREADCRUMBS);
  }

%>
<HEAD>
<TITLE>Welcome to UML Browser..</TITLE>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<LINK rel="stylesheet" TYPE="text/css" HREF="<html:rewrite page='/css/blaf.css' />">

</HEAD>
<BODY topmargin=0 bgcolor="#ffffff">
<%@ include  file="tab_include_search.jsp" %>


<jsp:include page="/common/tab_inc.jsp" flush="true">
	<jsp:param name="label" value="Class&nbsp;Search" />
	<jsp:param name="urlPrefix" value="../" />
</jsp:include>



<html:form action="/umlbrowser/umlSearchAction.do">
 <%@ include  file="/umlbrowser/umlSearch_inc.jsp" %>
  <P>
     
<logic:present name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">  
  <%@ include  file="/umlbrowser/umlResults_inc.jsp" %>
</logic:present> 
<logic:notPresent name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">  
	<table width="100%" align="center" cellpadding="1" cellspacing="1" border="0" class="OraBGAccentVeryDark">
  	  <tr class="OraTabledata">
         	<td ><bean:message key="cadsr.umlbrowser.search.message"/></td>
  	  </tr>
  	</table>   
</logic:notPresent>
 </P>
</html:form> 
<%@ include file="/common/common_bottom_border.jsp"%>
</BODY>
</HTML>
