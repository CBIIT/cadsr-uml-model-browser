<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/cdebrowser.tld" prefix="cde"%>
<%@page import="gov.nih.nci.ncicb.cadsr.util.*"%>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants"%>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants"%>

<html>
	<%
		String urlPrefix = "/";
		String pageContextInfo = "";
		String paramIdseq = request.getParameter("P_PARAM_TYPE");

		if (paramIdseq == null)
			paramIdseq = "";
		String paramType = (String) request.getParameter("P_IDSEQ");
		if (paramType == null)
			paramType = "";

		if (!paramIdseq.equals("") && !paramType.equals("")) {
			pageContextInfo = request.getParameter(TreeConstants.TREE_BREADCRUMBS);
		}
	%>
	<head>
		<title>Welcome to UML Browser..</title>
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<link rel="stylesheet" TYPE="text/css"
			HREF="<html:rewrite page='/css/blaf.css' />">

	</head>
	<body topmargin="0" bgcolor="#ffffff">
		<%@ include file="/jsp/umlbrowser/tab_include_search.jsp"%>


		<jsp:include page="/jsp/common/tab_inc.jsp" flush="true">
			<jsp:param name="label" value="Class&nbsp;Search" />
			<jsp:param name="urlPrefix" value="/" />
		</jsp:include>



		<html:form action="/umlbrowser/umlSearchAction.do">
		<% String searchSrc = request.getContextPath()+"/jsp/umlbrowser/umlSearch_inc.jsp";
			System.out.println("SearchSrc"+searchSrc);
		 %>
			<%@ include file = "umlSearch_inc.jsp" %>
			<p>

				<logic:present name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
				<% String resultsSrc = request.getContextPath()+"/jsp/umlbrowser/umlSearch_inc.jsp"; %>
					<%@ include file= "umlResults_inc.jsp" %>
				</logic:present>
				<logic:notPresent
					name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
					<table width="100%" align="center" cellpadding="1" cellspacing="1"
						border="0" class="OraBGAccentVeryDark">
						<tr class="OraTabledata">
							<td>
								<bean:message key="cadsr.umlbrowser.search.message" />
							</td>
						</tr>
					</table>
				</logic:notPresent>
			</p>
		</html:form>
		<%@ include file="/jsp/common/common_bottom_border.jsp"%>
	</body>
</html>
