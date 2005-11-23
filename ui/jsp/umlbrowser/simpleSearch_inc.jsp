
<%
String  basicSearchType = desb.getBasicSearchType();
String  basicSearchTypeName ="selected";
String  basicSearchTypePublicId="";
if(basicSearchType.equalsIgnoreCase("publicId"))
{
  basicSearchTypePublicId="selected";
}

%>
<INPUT TYPE="HIDDEN" NAME="jspSearchIn" VALUE="ALL">
<INPUT TYPE="HIDDEN" NAME="jspLatestVersion" VALUE="Yes">
<INPUT TYPE="HIDDEN" NAME="contextUse" VALUE="both">
<INPUT TYPE="HIDDEN" NAME="jspStatus" VALUE="ALL">
<INPUT TYPE="HIDDEN" NAME="regStatus" VALUE="ALL">
<INPUT TYPE="HIDDEN" NAME="jspCdeId" >
<INPUT TYPE="HIDDEN" NAME="jspKeyword" >

<table width="100%" >
 
 <tr align="left">
    <td class="OraHeaderSubSub" width="50%" align="left" nowrap>Search for Data Elements</td>
     <td align="right" class="MessageText"  width="10%" nowrap><b>
   <%
   if (deList!=null&&deList.size()==0)
   {
   %>
    No Matches 
   <%
   }
   else if(deList!=null&&deList.size()!=0)
   {
   %>
    <a class="link" href="#results"><%=topScroller.getTotalRecordCount()%> Matches</a>
   <%
   }
   else{%>
    &nbsp;
   <%
   }
   %>
   </b></td>
   
  <td align="right" width="20%"><a href="javascript:gotoUMLBrowser()"> UML Browser </a></td>

  <td align="right" width="20%" nowrap>
        <a href="javascript:gotoCDESearchPrefs()">
          Search preferences</a>
   </td>
  <td align="right" width="20%" nowrap>
        <a href="javascript:changeScreenType(<%="'"+BrowserFormConstants.BROWSER_SCREEN_TYPE_ADVANCED+"'"%>)">
          Advanced search</a>
   </td>
   
 </tr>   
 <tr>
   <td  align="center" colspan="4"><html:img page="/i/beigedot.gif" border="0"  height="1" width="99%" align="top" /> </td>
  </tr> 
 </table>
 
 <table valign="top">
  <tr>
   <td valign="top" class="CDEBrowserPageContext">
     <%=pageContextInfo%>
   </td >
  </tr>
</table>

 <table align="center" width="80%" border="0" cellpadding="0" cellspacing="1"  border="0" >
 <tr>
    <td   width="60%" align="center" nowrap >
      <input type="text" name="jspSimpleKeyword" value="<%=desb.getSimpleSearchStr()%>" size ="65"> 
    </td>
    <td   width="20%" align="left" nowrap >
      <select  name="jspBasicSearchType" class="Dropdown" name="contextIdSeq" >
        <option value="name" <%=basicSearchTypeName%> >Name</option> 
        <option value="publicId" <%=basicSearchTypePublicId%> >Public ID</option> 
      </select>
    </td>
 </tr>
 </table>
 
 
 <table align="center" valign="top"  width="78%" >
     <tr valign="top">    
          <td colspan=2 width="100%" valign="top" align="left" class="AbbreviatedText">
            Tip: This is an exact match search. To search for partial words or phrases use the * as a wildcard.
          </td>
      </tr>       
     <tr valign="top">    
          <td colspan=2 width="100%" valign="top" align="left" class="AbbreviatedText">
            <bean:message key="cadsr.cdebrowser.helpText.results"/>
          </td>
      </tr>      
    <tr>
      <td  align="left" nowrap >
        <table  border="0" >
         <tr>
           <td  colspan=2 nowrap>&nbsp;</td>
         </tr>
        </table>
      </td>
    </tr> 
 </table>
 
<br>

 
<table width="80%" border="0" align="center"> 
<%
  if ("".equals(src)) {
%>
 <table with ="80%" align="center" border="0">
 <TR>
    <td align="center" nowrap><a href="javascript:submitSimpleForm()"><html:img page="/i/search.gif" border="0" /></a></td>
    <td  align="center" nowrap><a href="javascript:clearSimpleForm()"><html:img page="/i/clear.gif" border="0" /></a></td>
    <%
       if(deList!=null){
    %>
   <td  align="center" nowrap><a href="javascript:newSearch()"><html:img page="/i/newSearchButton.gif" border="0" /></a></td>
   <%}%>
 </TR>
 </table>
<%
  }
  else {
%>
  <table with ="80%" align="center">
  <TR>
    <td  nowrap  ><a href="javascript:submitSimpleForm()"><html:img page="/i/SearchDataElements.gif" border="0" /></a>
    </td>
    <td><a href="javascript:clearSimpleForm()"><html:img page="/i/clear.gif" border="0" /></a>
    </td>
    <%
      if(deList!=null){
    %>
    <td><a href="javascript:newSearch()"><html:img page="/i/newSearchButton.gif" border="0" /></a>
    </td>
    <%}%>
    <td><a href="javascript:done()"><html:img page="/i/backButton.gif" border="0" /></a>
    </td>
   </TR>
 </table>
<%
  }
%>
