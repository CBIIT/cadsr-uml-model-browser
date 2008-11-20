<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/cdebrowser.tld" prefix="cde"%>

<%@page import="gov.nih.nci.ncicb.cadsr.util.*"%>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>
<%@page
	import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants"%>
<%@page
	import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserNavigationConstants"%>
<%@page
	import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants"%>

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
			pageContextInfo = request
					.getParameter(TreeConstants.TREE_BREADCRUMBS);
		} else {
			pageContextInfo = (String) request
					.getAttribute(UMLBrowserFormConstants.ATTRIBUTE_CRUMB);
		}

		if (pageContextInfo == null)
			pageContextInfo = "";
	%>
	<head>
		<title>Welcome to UML Browser..</title>
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<link rel="stylesheet" TYPE="text/css"
			HREF="<html:rewrite page='/css/blaf.css' />">

	</head>
	<script LANGUAGE="JavaScript" type="text/javascript">
function fileDownloadWin(url,windowName,nwidth,nheight)
{
  if (nwidth == null) {
    nwidth = 440;
  }

  if (nheight == null) {
    nheight = 300;
  }

  screenx = (screen.availWidth - nwidth) / 2;
  screeny =  (screen.availHeight - nheight) / 2;
  var hWnd = window.open(url,windowName,"toolbar=yes,width="+nwidth+",height="+nheight+",screenx="+screenx+",screeny="+screeny+",resizable=no,scrollbars=no,menubar=yes,directories=no, location=no");
  hWnd.focus();
}
</script>
	<body topmargin="0" bgcolor="#ffffff">
		<%@ include file="/jsp/umlbrowser/tab_include_search.jsp"%>


		<jsp:include page="/jsp/common/tab_inc.jsp" flush="true">
			<jsp:param name="label" value="Class&nbsp;Search" />
			<jsp:param name="urlPrefix" value="../" />
		</jsp:include>



		<html:form action="/umlbrowser/umlSearchAction.do">
			<%@ include file="/jsp/umlbrowser/umlSearch_inc.jsp"%>
			<p>

				<logic:present name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>">
					<%@ include file="/jsp/umlbrowser/umlAttributes_inc.jsp"%>
				</logic:present>
				<logic:notPresent
					name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>">
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
