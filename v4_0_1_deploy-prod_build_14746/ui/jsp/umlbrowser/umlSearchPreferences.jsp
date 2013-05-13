<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/cdebrowser.tld" prefix="cde"%>
<%@page import="gov.nih.nci.ncicb.cadsr.util.* " %>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserFormConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserNavigationConstants" %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants" %>

<html>
<head>
<title>UML Model Browser Search Preferences</title>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<LINK rel="stylesheet" TYPE="text/css" HREF="<html:rewrite page='/css/blaf.css' />">
<SCRIPT LANGUAGE="JavaScript1.1" SRC='<html:rewrite page="/jsLib/checkbox"/>'></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.1" SRC='<html:rewrite page="/jsLib/JavaScript.js"/>'></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.1">
function save() {
  document.forms[0].method.value="setSearchPreferences";
  document.forms[0].submit();
}
function reset() {
  document.forms[0].method.value="resetSearchPreferences";
  document.forms[0].submit();
}
</SCRIPT>
</head>

<body topmargin="0" bgcolor="#ffffff">

<TABLE valign="top" width=100% Cellpadding=0 Cellspacing=0 border=0>
  <tr>

    <td valign="top" align="left" nowrap>

    <html:img page="/i/umlbrowser_banner_full.gif" border="0" />
    </td>

    <td align=right valign="top" colspan=2 nowrap>
      <TABLE Cellpadding=0 Cellspacing=0 border=0 >
        <TR>
          <TD valign="TOP" align="CENTER" width="1%" colspan=1>
           <html:link page="/" target="_top">
              <html:img page="/i/icon_home.gif" alt="Home" border="0"  width="32" height="32"/>
          </html:link><br><font color=brown face=verdana size=1>&nbsp;Home&nbsp;</font></TD>
          <TD valign="TOP" align="CENTER" width="1%" colspan=1><A HREF="javascript:newBrowserWin('/help/','helpWin',700,600)"><html:img page="/i/icon_help.gif" alt="Task Help" border="0"  width="32" height="32" /></A><br><font color=brown face=verdana size=1>&nbsp;Help&nbsp;</font></TD>
        </TR>
      </TABLE>
    </td>
  </tr>
 </table>
   <table>
   <logic:present name="nciUser">
   <tr>
     <td valign="top" align="left" class="OraInlineInfoText" nowrap>
         <bean:message key="user.greet" />
     	<bean:write name="nciUser" property="username"  scope="session"/>
     </td>
   </tr>
  </logic:present>
   <logic:notPresent name="nciUser">
   <tr>
     <td height="2" align="left"  nowrap>
 		&nbsp;
     </td>
   </tr>
  </logic:notPresent>
</TABLE>

<jsp:include page="/common/tab_inc.jsp" flush="true">
	<jsp:param name="label" value="Search&nbsp;Preferences" />
	<jsp:param name="urlPrefix" value="../" />
</jsp:include>

<html:form action="/umlbrowser/setSearchPreferencesAction.do">
   <html:hidden value="" property="<%=UMLBrowserNavigationConstants.METHOD_PARAM%>"/>
   
<table align="center" width=20% Cellpadding=0 Cellspacing=4 border=0>
    <tr>
        <TD valign="TOP" align="CENTER"  colspan=1>
          <a href="javascript:save()">
             <html:img page="/i/save.gif" border="0" alt="Save"/>
          </a>     
        </td>
       <td valign="BOTTOM" align="CENTER">
          <html:link page="/" target="_top">
             <html:img page="/i/back.gif" border="0" alt="Cancel"/>
          </html:link>  
        </td>         
    </tr>
</table>

 <table width="100%">
  <tbody><tr align="left">
     <td class="OraHeaderSubSub" align="left" nowrap="nowrap" width="50%">UML Model Browser search preferences for this session</td>
     <td width="50%" align="right" nowrap>
          <a href="javascript:reset()">
                 Reset to default search preferences
          </a>    
    </td>
  </tr>  
  <tr>
    <td colspan="2" align="center"><html:img page="/i/beigedot.gif" align="top" border="0" height="1" width="99%"/> </td>
   </tr>
 </tbody></table>

  <table align="center" width="80%">
    <tbody><tr>
         <td valign="top" width="100%">
          <table class="OraBGAccentVeryDark" cellpadding="1" cellspacing="1" width="100%">
            <tbody><tr>
              <td colspan="2" class="OraTableColumnHeaderNoBG">
                <html:checkbox property="<%=UMLBrowserFormConstants.EXCLUDE_TEST_CONTEXT%>" value="true"  >
                   <bean:message key="cadsr.umlbrowser.excludeTestContext.label"/>
                </html:checkbox>              
              </td>
            </tr>
            <tr>
              <td colspan="2" class="OraTableColumnHeaderNoBG">
                <html:checkbox property="<%=UMLBrowserFormConstants.EXCLUDE_TRAINING_CONTEXT%>" value="true"  >
                   <bean:message key="cadsr.umlbrowser.excludeTrainingContext.label"/>
                </html:checkbox>
              </td>
            </tr>
          </tbody></table>
         </td>
     </tr>
  </tbody></table>
<br>
<br>

 <table width="100%">
  <tbody><tr>
    <td align="center"><html:img page="/i/beigedot.gif" align="top" border="0" height="1" width="99%"/> </td>
   </tr>
 </tbody></table>


<table align="center" width=20% Cellpadding=0 Cellspacing=4 border=0>
    <tr>
        <TD valign="TOP" align="CENTER"  colspan=1>
          <a href="javascript:save()">
             <html:img page="/i/save.gif" border="0" alt="Save"/>
          </a>     
        </td>
       <td valign="BOTTOM" align="CENTER">
          <html:link page="/" target="_top">
             <html:img page="/i/back.gif" border="0" alt="Cancel"/>
          </html:link>         
        </td>         
    </tr>
</table>

</html:form>
<%@ include file="/common/common_bottom_border.jsp"%>

</body></html>