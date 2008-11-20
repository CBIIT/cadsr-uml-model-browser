<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/cdebrowser.tld" prefix="cde"%>
<%
	String source = request.getParameter("src");
%>

<html>
	<head>
		<meta HTTP-EQUIV="Content-Type"
			CONTENT="text/html; charset=windows-1252">
		<link REL="STYLESHEET" TYPE="text/css"
			HREF="<%=request.getContextPath()%>/css/blaf.css">
		<title>XML Download</title>
	</head>
	<body onLoad="goPage()">
		<script LANGUAGE="JavaScript" type="text/javascript">
<!--
function goPage() {
  document.location.href ="<%=request.getContextPath()%>" + "/umlbrowser/xmlDownloadAction.do?method=xmlDownload";
  
}
function closeWindow() {
  close();
}
//-->
</script>
		<form name="downloadForm" action="">

			<br>

			<p class="OraHeaderSubSub">
				Retrieving data elements as XML...
			</p>

			<p>
				<font face="Arial, Helvetica, sans-serif" size="-1" color="#336699">
					Please note that the XML tags in this download use ISO 11179
					terminology. <br> This operation may take a few minutes, so
					please do not close this status window while it is in progress.
					Please wait until the File Save dialog appears, then select "Save"
					(not "Open"). Once the file has been saved to your local drive, you
					may close this status window and open the saved file in any text or
					XML editor. </font>
			</p>

		</form>
	</body>
</html>
