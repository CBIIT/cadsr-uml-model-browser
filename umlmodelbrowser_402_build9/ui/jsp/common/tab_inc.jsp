<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%
	String label = pageContext.getRequest().getParameter("label");
%>

<TABLE width=100% Cellpadding=0 Cellspacing=0 border=0>
  <tr>
    <td width=98%>&nbsp;</td>
    <td valign=bottom align=right>
      <table border=0 cellpadding=0 cellspacing=0>
        <tr>


<TD bgcolor="#336699" width="1%" align=LEFT valign=TOP><html:img page="/i/ctab_open.gif" alt="ctab open" height="21" width="18" border="0"/></TD>
<TD width=1% bgcolor="#336699"><b><font size="-1" face="Arial" color="#FFFFFF"><%=label%></font></b></TD>
<TD bgcolor="#336699" width="1%" align=RIGHT valign=TOP><html:img page="/i/ctab_close.gif" alt="ctab close" height="21" width="12" border="0"/></TD>


</table>
</td>
</TR>
</TABLE>

<TABLE width=100% Cellpadding=0 Cellspacing=0 border=0>
<TR>
<td align=left valign=top width="1%" bgcolor="#336699"><html:img page="/i/top_left.gif" alt="top left" width="4" height="25"/></td>
<td align=left valign=top width="5%" bgcolor="#336699">&nbsp;</td>


<td align=left valign=top width="5%" bgcolor="#336699">&nbsp;</td>

<!-- add here --->

<TD align=left valign=center bgcolor="#336699" height=25 width="94%">&nbsp;</TD>
</tr>
</table>

<table  width=100% Cellpadding=0 Cellspacing=0 border=0>
<tr>
<td align=right valign=top width=49 height=21 bgcolor="#336699"><html:img page="/i/left_end_bottom.gif" alt="left end bottom" height="21" width="49" /></td>
<TD align=left valign=top bgcolor="#FFFFFF" height=21 width=100%><html:img page="/i/bottom_middle.gif" alt="bottom middle" height="6" width="100%" /></TD>
<td align="LEFT" valign=top height=21 width=5  bgcolor="#FFFFFF"><html:img page="/i/right_end_bottom.gif" alt="right end bottom" height="7" width="5" /></td>
</TR>
</TABLE>

