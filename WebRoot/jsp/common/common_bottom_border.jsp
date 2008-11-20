<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td valign="bottom" width="99%">
			<html:img page="/i/bottom_shade.gif" height="6" width="100%" />
		</td>
		<td valign="bottom" width="1%" align="right">
			<html:img page="/i/bottomblueright.gif" />
		</td>
	</tr>
</table>
<table width="100%" cellspacing="0" cellpadding="0" bgcolor="#336699"
	border="0">
	<tr>
		<td width="20%" align="LEFT">
			&nbsp;
			<font face="Arial" color="WHITE" size="-2">User: </font>
			<font face="Arial" size="-1" color="#CCCC99"> <logic:present
					name="nciUser">
					<bean:write name="nciUser" property="username" scope="session" />
				</logic:present> <logic:notPresent name="nciUser">
    Public User    
  </logic:notPresent> </font>
		</td>
		<td width="30%" align="right">
			<font color="white" size="-2" face="arial">Version
				@umlmodelbrowser.version@&nbsp;&nbsp;Build @umlmodelbrowser.build@ </font>
		</td>

		<td td width="70%" align="right">
			<font color="white" size="-3" face="arial"> Please send
				comments and suggestions to <a href="mailto:ncicb@pop.nci.nih.gov">ncicb@pop.nci.nih.gov</a>

			</font> &nbsp; &nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<html:img page="/i/bottom_middle.gif" height="6" width="100%" />
		</td>
	</tr>
</table>