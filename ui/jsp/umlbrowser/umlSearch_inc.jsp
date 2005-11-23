
<SCRIPT LANGUAGE="JavaScript">
<!--
function submitForm(methodName) {
  document.forms[0].<%=UMLBrowserNavigationConstants.METHOD_PARAM%>.value=methodName;
  document.forms[0].submit();
  }

/* HSK */

function clearProject() {
  document.forms[0].projectName.value = "";
  document.forms[0].projectIdseq.value = "";
}
function clearForm() {
  clearProject();
  document.forms[0].reset();
}
-->
</SCRIPT>
<%
  String pageUrl = "&PageId=DataElementsGroup";
  // HSK
  String contextPath = request.getContextPath();

  String csLOVUrl= contextPath + 
       "/search?classificationsLOV=9&idVar=jspClassification&nameVar=txtClassSchemeItem" + pageUrl;

  String projectsUrl = contextPath +
       "/umlbrowser/umlSearchAction.do?method=getProjectsLOV"+pageUrl;

%>


<SCRIPT>
<!--
function gotoProjectsLOV() {
     var dest = '<%= projectsUrl %>' ;
;
     newWin(dest, 'ProjectsLOV', 700, 600);
}
-->
</SCRIPT>

 <html:hidden value="" property="<%=UMLBrowserNavigationConstants.METHOD_PARAM%>"/>

          
<table width="100%" >
 
 <tr align="left">
 <!--
    <td class="OraHeaderSubSub" width="60%" align="left" nowrap>Search For Classes</td>
 -->
   <td align="right" width="20%" nowrap>
         <a href="">
           Advanced search</a>
    </td>
    <logic:present name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>"> 
     <bean:size id="listSize" name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>" />
     <logic:notEmpty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
         <a class="link" href="#results"><%=listSize%>  Matches</a>
     </logic:notEmpty>
     <logic:empty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
      No Matches 
     </logic:empty>
    </logic:present>
     
    <logic:notPresent name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>"> 
      &nbsp;
    </logic:notPresent>
   </b></td>
     
         
 </tr>   
 <tr>
   <td  align="center" colspan="3"><html:img page="/i/beigedot.gif" border="0"  height="1" width="99%" align="top" /> </td>
  </tr> 
 </table>
 
    <table align="center" valign="top" width="100%" cellpadding="0" cellspacing="1" class="OraBGAccentVeryDark" border="0">
  
            <tr>
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap>Class Name</td>
              <td class="OraTabledata" nowrap>
                <html:text property="<%=UMLBrowserFormConstants.CLASS_NAME%>" size="35" />
              </td>
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap>Attribute Name</td>
              <td class="OraTabledata" nowrap>
                <html:text property="attributeName" size="35" />
              </td>
            </tr>
   </table>
 
  <table valign="top" width="100%" >
    <tr>
      <td width="50%"  valign="top" >
          <table width="100%" cellpadding="0" cellspacing="1" class="OraBGAccentVeryDark" border="0" %>
            <tr>            
                <td width="30%" class="OraTableColumnHeaderNoBG" nowrap>Project Name</td>
                <td class="OraTabledata" nowrap>
                  <html:text property="<%=UMLBrowserFormConstants.PROJECT_NAME%>" 
                         readonly="true" 
                         size="19"
                         styleClass="LOVField"
                        />
                  &nbsp;<a href="javascript:gotoProjectsLOV()"><img src="<%=urlPrefix%>i/search_light.gif" border="0" alt="Search for Projects"></a>&nbsp;
                  <a href="javascript:clearProject()"><i>Clear</i></a>
                  <html:hidden  property="<%=UMLBrowserFormConstants.PROJECT_IDSEQ%>"/>
                </td>
             </tr>
            <tr>            
              <td width="30%" class="OraTableColumnHeaderNoBG" nowrap>Sub Project Name</td>
              <td width="70%" class="OraTabledata" nowrap>
                <html:text property="<%=UMLBrowserFormConstants.SUB_PROJECT_NAME%>" 
                       readonly="true" 
                       size="19"
                       styleClass="LOVField"                      
                       />
                &nbsp;<a href="javascript:gotoProjectsLOV()"><img src="<%=urlPrefix%>i/search_light.gif" border="0" alt="Search for Sub Projects"></a>&nbsp;
                <a href="javascript:clearProject()"><i>Clear</i></a>
                <html:hidden  property="<%=UMLBrowserFormConstants.SUB_PROJECT_IDSEQ%>"/>
                </td>
             </tr>  
            <tr>            
              <td width="30%" class="OraTableColumnHeaderNoBG" nowrap>Package Name</td>
             <td width="70%" class="OraTabledata" nowrap>
                <html:text property="<%=UMLBrowserFormConstants.PACKAGE_NAME%>" 
                       readonly="true" 
                       size="19"
                       styleClass="LOVField"                      
                       />
                &nbsp;<a href="javascript:gotoProjectsLOV()"><img src="<%=urlPrefix%>i/search_light.gif" border="0" alt="Search for Sub Projects"></a>&nbsp;
                <a href="javascript:clearProject()"><i>Clear</i></a>
                <html:hidden  property="<%=UMLBrowserFormConstants.PACKAGE_IDSEQ%>"/>
              </td>  
             </tr>             
           </table>      
        </td>            

    </tr>    
 </table>
 
    

 <table align="center">
  <TR>
    <td   align="center" nowrap >
      <table align="center" >
           <tr>
        <td nowrap>
            <a href="javascript:submitForm('<%=UMLBrowserNavigationConstants.CLASS_SEARCH_METHOD%>')"><img src=<%=urlPrefix%>i/class_search.gif border=0></a>
          </td>       
        <td nowrap>
            <a href="javascript:submitForm('<%=UMLBrowserNavigationConstants.SHOW_ATTRIBUTE_SEARCH_METHOD%>')"><img src=<%=urlPrefix%>i/attribute_search.gif border=0></a>
          </td>       
             <td  >
               <a href="javascript:clearForm()"><img src=<%=urlPrefix%>i/reset.gif border=0></a>
              </td>
              
              <logic:present name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
                <td   nowrap>
                <html:link action='<%="/umlbrowser/umlSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"="+UMLBrowserNavigationConstants.NEW_SEARCH_METHOD%>' target="_parent">
                <html:img src='<%=urlPrefix+"i/newSearchButton.gif"%>' border="0" alt="New Search"/>
               </html:link>
              </logic:present>
              
          </td>         
           </tr>
      </table>       
    </td>
 </TR>
 <table>
 <table align="center">

  </table>
