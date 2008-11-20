<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/cdebrowser.tld" prefix="cde"%>
<html>
	<head>
		<meta HTTP-EQUIV="Content-Type"
			CONTENT="text/html; charset=windows-1252">
		<link REL="STYLESHEET" TYPE="text/css"
			HREF="<%=request.getContextPath()%>/css/blaf.css">
		<title>XML Download</title>
	</head>
	<body>
		<script LANGUAGE="JavaScript" type="text/javascript">
<!--
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
					There are no data elements matching your search criteria. </font>
			</p>
			<br>
		</form>
	</body>
</html>
