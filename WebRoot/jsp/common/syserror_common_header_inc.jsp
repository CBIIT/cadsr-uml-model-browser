<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>
<%
	String dest = pageContext.getRequest().getParameter("loginDestination");
%>

<script LANGUAGE="JavaScript1.1"
	SRC='<html:rewrite page="/js/newWinJS.js"/>' type="text/javascript"></script>

<%@ include file="/jsp/common/topHeader.jsp"%>

<head>
	<title>Welcome to UML Browser..</title>
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<link rel="stylesheet" TYPE="text/css"
		HREF="<html:rewrite page='/css/blaf.css' />">

</head>
<body topmargin="0" bgcolor="#ffffff">

	<table width="100%" Cellpadding="0" Cellspacing="0" border="0">
		<tr>

			<td align="left" nowrap>
				<%String imgSrc = request.getContextPath()+"/i/umlbrowser_banner_full.gif";%>
				<html:img page="<%= imgSrc%>" border="0" />
			</td>

		</tr>
		<tr>
			<td valign="TOP" align="right" width="1%" colspan="1">
				<a HREF='<%=request.getContextPath()%>' TARGET="_top">
				<%String imgSrc2 = request.getContextPath()+"/i/umlbrowser_banner_full.gif";%> 
				<html:img
						page="<%= imgSrc2%>" alt="Home" border="0" width="32"
						height="32" /> </a>
				<br>
				<font color="brown" face="verdana" size="1">&nbsp;Home&nbsp;</font>
			</td>
			<td valign="TOP" align="right" width="1%" colspan="1">
				<a HREF="javascript:newBrowserWin('/help/','helpWin',700,600)">
					<%String imgSrc3 = request.getContextPath()+"/i/icon_help.gif";%>				
					<html:img page="<%= imgSrc3%>" alt="Help" border="0" width="32"
						height="32" /> </a>
				<br>
				<font color="brown" face="verdana" size="1">&nbsp;Help&nbsp;</font>
			</td>
		</tr>

	</table>
</body>