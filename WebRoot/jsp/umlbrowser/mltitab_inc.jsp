<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserNavigationConstants"%>
<%@ page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants"%>

<%
	String urlPrefix = request.getContextPath();
	String label = pageContext.getRequest().getParameter("label");
	String[] allLabels = {"Classes","Attributes"};
  String umlclassurl = "javascript:submitForm('classSearch')";
  String umlattributeurl = "javascript:submitForm('attributeSearch')";
  String[] urls = {umlclassurl,umlattributeurl};
%>


<TABLE valign=top width=100% Cellpadding=0 Cellspacing=0 border=0>
  <tr>
    <td width=98%>&nbsp;</td>
    <td valign=bottom align=right>
      <table border=0 cellpadding=0 cellspacing=0>
        <tr>

<% for(int i=0;i<allLabels.length;i++)
 { %>

 <%
    if(allLabels[i].equals(label))
    {
 %>
      <TD bgcolor="#336699" width="1%" align=LEFT valign=TOP><IMG SRC="<%=request.getContextPath()%>/i/ctab_open.gif" height=21 width=18 border=0></TD>
      <TD width=1% bgcolor="#336699"><b><font size="-1" face="Arial" color="#FFFFFF">
            <%=allLabels[i]%></font></b>
      </TD>
      <TD bgcolor="#336699" width="1%" align=RIGHT valign=TOP><IMG SRC="<%=request.getContextPath()%>/i/ctab_close.gif" height=21 width=12 border=0></TD>
 <% } else { %>
      <TD bgcolor="#B6B687" width="1%" align=LEFT valign=TOP><IMG SRC="<%=request.getContextPath()%>/i/tab_open.gif" height=21 width=18 border=0></TD>
      <TD width=1% bgcolor="#B6B687"><font size="-1" face="Arial" color="#000000">
          <a href="<%=urls[i]%>"><%=allLabels[i]%> </a></font></TD>
      <TD bgcolor="#B6B687" width="1%" align=RIGHT valign=TOP><IMG SRC="<%=request.getContextPath()%>/i/tab_close.gif" height=21 width=12 border=0></TD>
<% } %>
<% }%>
</tr>
</table>


</td>
</TR>
</TABLE>

<TABLE valign=top width=100% Cellpadding=0 Cellspacing=0 border=0>
<TR>
<td align=left valign=top width="1%" bgcolor="#336699"><img src="<%=request.getContextPath()%>/i/top_left.gif" width=4 height="25"></td>
<td align=left valign=top width="5%" bgcolor="#336699">&nbsp;</td>


<td align=left valign=top width="5%" bgcolor="#336699">&nbsp;</td>

<!-- add here --->

<TD align=left valign=top valign=center bgcolor="#336699" height=25 width="94%">&nbsp;</TD>
</tr>
</table>

<table  width=100% Cellpadding=0 Cellspacing=0 border=0>
<tr>
<td align=right valign=top width=49 height=21 bgcolor="#336699"><img src="<%=request.getContextPath()%>/i/left_end_bottom.gif" height=21 width=49></td>
<TD align=right valign=top bgcolor="#FFFFFF" height=21 width="100%"><img src="<%=request.getContextPath()%>/i/bottom_middle.gif" height=6 width=100%></TD>
<td align="LEFT" valign=top height=21 width=5  bgcolor="#FFFFFF"><img src="<%=request.getContextPath()%>/i/right_end_bottom.gif" height=7 width=5></td>
</TR>
</TABLE>

