
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>
<%
	String dest = pageContext.getRequest().getParameter("loginDestination");
%>

<SCRIPT LANGUAGE="JavaScript1.1" SRC='<html:rewrite page="/jsLib/newWinJS.js"/>'></SCRIPT>

<%@ include  file="topHeader.jsp" %>

<HEAD>
<TITLE>Welcome to UML Browser..</TITLE>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<LINK rel="stylesheet" TYPE="text/css" HREF="<html:rewrite page='/css/blaf.css' />">

</HEAD>
<BODY topmargin=0 bgcolor="#ffffff">

<TABLE width=100% Cellpadding=0 Cellspacing=0 border=0>
  <tr>

    <td align="left" nowrap>
    <html:img page="/i/umlbrowser_banner_full.gif" border="0" />
    </td>

  </tr>
        <TR>
         <TD valign="TOP" align="right" width="1%" colspan=1>
             <A HREF='<%=request.getContextPath()%>' TARGET="_top">
               <html:img page="/i/icon_home.gif" alt="Home" border="0"  width="32" height="32" />
             </A><br><font color=brown face=verdana size=1>&nbsp;Home&nbsp;</font>
          </TD>
          <TD valign="TOP" align="right" width="1%" colspan=1>
          <A HREF="javascript:newBrowserWin('<%=request.getContextPath()%>/common/help/UMLModelBrowserHelp.html','helpWin',700,600)">
            <html:img page="/i/icon_help.gif" alt="Help" border="0"  width="32" height="32" />
          </A><br><font color=brown face=verdana size=1>&nbsp;Help&nbsp;</font></TD>
        </TR>
 
</TABLE>
</body>