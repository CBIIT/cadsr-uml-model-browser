<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/cdebrowser.tld" prefix="cde"%>
<%@page import="oracle.clex.process.jsp.GetInfoBean " %>
<%@page import="gov.nih.nci.ncicb.cadsr.html.* " %>
<%@page import="gov.nih.nci.ncicb.cadsr.util.* " %>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlbrowser.struts.common.UMLBrowserFormConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlbrowser.struts.common.UMLBrowserNavigationConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants" %>

<HTML>
<%
  String urlPrefix = "../";

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
