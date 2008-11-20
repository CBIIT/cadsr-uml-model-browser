<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html>
	<head>
		<title>UML Model Browser: System Error</title>
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
		<link rel="stylesheet" TYPE="text/css"
			HREF="<html:rewrite page='/css/blaf.css' />">
		<script language="javascript" type="text/javascript">
if(self.location!=top.location)top.location="/jsp/common/systemError.jsp";
</script>
	</head>
	<body topmargin="0" bgcolor="#ffffff">
		<% String urlPrefix = "";

%>
		<%@ include file="/jsp/common/syserror_common_header_inc.jsp"%>
		<jsp:include page="/jsp/common/tab_inc.jsp" flush="true">
			<jsp:param name="label" value="System&nbsp;Error" />
			<jsp:param name="urlPrefix" value="" />
		</jsp:include>

		<table>
			<tr>
				<td class="OraErrorHeader">
					Unexpected System Error has Occured
					<br>
					<br>
					<logic:messagesPresent>
						<table width="80%" align="center">
							<html:messages id="error">
								<logic:present name="error">
									<tr align="center">
										<td align="left" class="OraErrorText" nowrap>
											<b><bean:write name="error" /> </b>
										</td>
									</tr>
								</logic:present>
							</html:messages>
							<tr align="center">
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</logic:messagesPresent>
				</td>
			</tr>
		</table>
		<%@ include file="/jsp/common/common_bottom_border.jsp"%>
	</body>
</html>
