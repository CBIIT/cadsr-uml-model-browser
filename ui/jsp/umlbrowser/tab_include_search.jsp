
<%@ page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>
<%@page import="gov.nih.nci.ncicb.cadsr.util.* " %>
<%
	String dest = pageContext.getRequest().getParameter("loginDestination");
  UMLBrowserParams params = UMLBrowserParams.getInstance();
%>

<SCRIPT LANGUAGE="JavaScript1.1" SRC='<html:rewrite page="/jsLib/JavaScript.js"/>'></SCRIPT>

<TABLE valign="top" width=100% Cellpadding=0 Cellspacing=0 border=0>
  <tr>

    <td valign="top" align="left" nowrap>

    <html:img page="/i/umlbrowserbanner.gif" border="0" />
    </td>

    <td align=right valign="top" colspan=2 nowrap>
      <TABLE Cellpadding=0 Cellspacing=0 border=0 >
        <TR>
          <TD valign="TOP" align="CENTER" width="1%" colspan=1><html:link page="/" target="_top"><html:img page="/i/icon_home.gif" alt="Home" border="0"  width="32" height="32"/></html:link><br><font color=brown face=verdana size=1>&nbsp;Home&nbsp;</font></TD>
          <TD valign="TOP" align="CENTER" width="1%" colspan=1><A HREF="javascript:newBrowserWin('/help/','helpWin',700,600)"><html:img page="/i/icon_help.gif" alt="Task Help" border="0"  width="32" height="32" /></A><br><font color=brown face=verdana size=1>&nbsp;Help&nbsp;</font></TD>
        </TR>
      </TABLE>
    </td>
  </tr>
 </table>
  <table valign="top">
   <tr>
    <td  valign="top" width="90%" >
    <TABLE valign="top" align ="left" width="100%" Cellpadding=0 Cellspacing=0 border=0 >
          <tr>
           <td  valign="bottom" align="center" width="15%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
               <a href="<%=params.getCdebrowserToolURL()%>" target="_blank" >CDEBrowser&nbsp;</a> </span></td>          
             <td  valign="bottom" align="center" width="15%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
               <a href="<%=params.getFormBuilderToolUrl()%>" target="_blank" >FormBuilder Tool&nbsp;</a> </span></td>          
           
           <td  valign="bottom" align="center" width="15%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
               <a href="<%=params.getAdminToolUrl()%>" target="_blank" >Admin Tool&nbsp;</a> </span></td>

           <td  align="left" width="15%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
                <a href="<%=params.getCurationToolUrl()%>" target="_blank">
                      Curation Tool&nbsp;</a></span>
            </td>
           <td  align="center"  width="22%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
                <a href="<%=params.getNciMetathesaurusUrl()%>" target="_blank" >
                      NCI Metathesaurus&nbsp;</a></span>
            </td>
           <td  align="center" width="28%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
                <a href="<%=params.getNciTerminologyServerUrl()%>" target="_blank">
                      NCI Terminology Server&nbsp;</a></span>
            </td>
           <td  align="center" width="15%" height="10" nowrap><span style="font-size: 10.0pt; font-family: Arial">
                <a href="<%=params.getSentinelToolUrl()%>" target="_blank">
                      Sentinel Tool&nbsp;</a></span>
            </td>            
        </tr>
      </table>
     </td>
  </tr>
  </tabel>
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