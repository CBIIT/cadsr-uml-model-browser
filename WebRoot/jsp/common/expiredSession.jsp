<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<html>
	<head>
		<title>Login</title>
		<meta http-equiv="Content-Type"
			content="text/html; charset=iso-8859-1">
		<link REL="STYLESHEET" TYPE="text/css"
			HREF="<%=request.getContextPath()%>/css/blaf.css">
		<script LANGUAGE="JavaScript" type="text/javascript">
<!--
if (parent.frames[1]) 
  parent.location.href = self.location.href; 
-->
</script>
	</head>

	<body text="#000000" topmargin="0">

		<%@ include file="syserror_common_header_inc.jsp"%>

		<br>
		<br>

		<table width="100%" Cellpadding="0" Cellspacing="0" border="0">
			<tr>
				<td align="left" valign="top" width="1%" bgcolor="#336699">
					<img src="<%=request.getContextPath()%>/i/top_left.gif" width="4"
						height="25" alt="">
				</td>
				<td nowrap align="left" valign="top" width="5%" bgcolor="#336699">
					<b><font size="3" face="Arial" color="#FFFFFF">&nbsp;
							&nbsp;Session Expired</font> </b>
				</td>

				<td align="left" valign="top" width="5%" bgcolor="#336699">
					&nbsp;
				</td>

				<td align="left" valign="center" bgcolor="#336699" height="25"
					width="94%">
					&nbsp;
				</td>
			</tr>
		</table>

		<table width="100%" Cellpadding="0" Cellspacing="0" border="0">
			<tr>
				<td align="right" valign="top" width="49" height="21"
					bgcolor="#336699">
					<img src="<%=request.getContextPath()%>/i/left_end_bottom.gif"
						height="21" width="49" alt="">
				</td>
				<td align="right" valign="top" bgcolor="#FFFFFF" height="21"
					width="100%">
					<img src="<%=request.getContextPath()%>/i/bottom_middle.gif"
						height="6" width="100%" alt="">
				</td>
				<td align="LEFT" valign="top" height="21" width="5"
					bgcolor="#FFFFFF">
					<img src="<%=request.getContextPath()%>/i/right_end_bottom.gif"
						height="7" width="5" alt="">
				</td>
			</tr>
		</table>


		<table width="100%" Cellpadding="0" Cellspacing="0" border="0">
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center" class="OraErrorText">
					<b>Your Session has Expired!</b>
				</td>
			</tr>
			<tr class="OraTipLabel">
				<td align="center" class="OraTipLabel">
					Click
					<a target="_top" href='<%=request.getContextPath()%>'>here</a> to
					return to UML Model Browser.
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="OraTipLabel"></td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>

		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td valign="bottom" width="99%">
					<img src="<%=request.getContextPath()%>/i/bottom_shade.gif"
						height="6" width="100%" alt="">
				</td>
				<td valign="bottom" width="1%" align="right">
					<img src="<%=request.getContextPath()%>/i/bottomblueright.gif"
						alt="">
				</td>
			</tr>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" bgcolor="#336699"
			border="0">
			<tr>
				<td width="60%" align="LEFT">
					<font face="Arial" color="WHITE" size="-2"></font>
					<font face="Arial" size="-1" color="#CCCC99"></font> &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp;
					<font color="white" size="-2" face="arial"></font>
				</td>

			</tr>
			<tr>
				<td colspan="2">
					<img src="<%=request.getContextPath()%>/i/bottom_middle.gif"
						height="6" width="100%" alt="">
				</td>
			</tr>
		</table>
	</body>
</html>
