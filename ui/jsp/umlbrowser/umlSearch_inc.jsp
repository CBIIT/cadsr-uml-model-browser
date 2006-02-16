
<SCRIPT LANGUAGE="JavaScript">
<!--
function submitForm(methodName) {
  document.forms[0].<%=UMLBrowserNavigationConstants.METHOD_PARAM%>.value=methodName;
  document.forms[0].submit();
  }

/* HSK */

function clearProject() {
  document.forms[0].projectIdseq.value = "";
  document.forms[0].subProjectIdseq.value = "";
  document.forms[0].packageIdseq.value = "";
  document.forms[0].className.value = "";
  document.forms[0].attributeName.value = "";
  
}
function clearForm() {
  document.forms[0].reset();
  clearProject();
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

function resetSubProjPackage() {
    alert("hey" +  document.forms[0].subProjectIdseq);

    
}


-->
</SCRIPT>

 <html:hidden value="" property="<%=UMLBrowserNavigationConstants.METHOD_PARAM%>"/>

          
<table width="100%" >
 
 <tr align="left">
 <!--
    <td class="OraHeaderSubSub" width="60%" align="left" nowrap>Search For Classes</td>
 -->
   <td valign="top" class="CDEBrowserPageContext">
     <%=pageContextInfo%>
   </td >
  <!--
  <td align="right" width="20%" nowrap>
         <a href="">
           Advanced search</a>
    </td>
     -->

    <logic:equal name="<%=UMLBrowserFormConstants.CLASS_VIEW%>" value="true"> 
     <logic:present name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>" >
     <bean:size id="listSize" name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>" />
     <logic:notEmpty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
         <a class="link" href="#results"><%=listSize%>  Matches</a>
     </logic:notEmpty>
     <logic:empty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
      No Matches 
     </logic:empty>
     </logic:present>
    </logic:equal>
     
    <logic:notEqual name="<%=UMLBrowserFormConstants.CLASS_VIEW%>" value="true">
     <logic:present name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>" >
     <bean:size id="listSize" name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>" />
     <logic:notEmpty name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>">
         <a class="link" href="#results"><%=listSize%>  Matches</a>
     </logic:notEmpty>
     <logic:empty name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>">
      No Matches 
     </logic:empty>
     </logic:present>
    </logic:notEqual>
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
                <td width="10%" class="OraTableColumnHeaderNoBG" nowrap>UML Project Name</td>
                <td class="OraTabledata"  nowrap width="50%">
                <html:select styleClass = "FreeDropdown" property="<%=UMLBrowserFormConstants.PROJECT_IDSEQ%>"
                onchange="submitForm('resetSubProjPkgOptions')">
                   <html:option key="cadsr.umlbrowser.form.blank" value="<%=UMLBrowserFormConstants.SEARCH_ALL%>" /> 

          <logic:iterate id="umlProj" name="<%=UMLBrowserFormConstants.ALL_PROJECTS%>" 
          	type="gov.nih.nci.cadsr.umlproject.domain.Project">
            <option value="<%=umlProj.getId()%>" 
            <% 
             org.apache.struts.action.DynaActionForm dynaForm = (org.apache.struts.action.DynaActionForm) request.getAttribute("umlSearchForm");

            if (umlProj.getId().equals(dynaForm.get(UMLBrowserFormConstants.PROJECT_IDSEQ))) { %>
            selected="selected" <% } %>  > 
            <%=umlProj.getShortName()+" (v"+umlProj.getVersion() +", " +umlProj.getClassificationScheme().getContext().getName() +")"%></option>
	    </logic:iterate> 
          </html:select>

                </td>
                <td width="20%" class="OraTableColumnHeaderNoBG"  nowrap>Version</td>
                <td class="OraTabledata" nowrap width="20%">
                <html:text property="<%=UMLBrowserFormConstants.PROJECT_VERSION%>" size="10" />
                </td>
             </tr>
            <tr>            
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap>Sub Project Name</td>
              <td width="70%" class="OraTabledata" nowrap colspan="3">
                <html:select styleClass = "FreeDropdown" property="<%=UMLBrowserFormConstants.SUB_PROJECT_IDSEQ%>" 
                onchange="submitForm('resetPkgOptions')">
                   <html:option key="cadsr.umlbrowser.form.blank" value="<%=UMLBrowserFormConstants.SEARCH_ALL%>" /> 
                   <html:options collection="<%=UMLBrowserFormConstants.SUBPROJECT_OPTIONS%>" 
                     property="id" labelProperty="name" />
                 </html:select>

                </td>
             </tr>  
            <tr>            
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap>Package Name</td>
             <td width="70%" class="OraTabledata" nowrap colspan="3">
                <html:select styleClass = "FreeDropdown" property="<%=UMLBrowserFormConstants.PACKAGE_IDSEQ%>">
                   <html:option key="cadsr.umlbrowser.form.blank" value="<%=UMLBrowserFormConstants.SEARCH_ALL%>" /> 
                   <html:options collection="<%=UMLBrowserFormConstants.PACKAGE_OPTIONS%>" 
                     property="id" labelProperty="name" />
                 </html:select>

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
            <a href="javascript:submitForm('<%=UMLBrowserNavigationConstants.ATTRIBUTE_SEARCH_METHOD%>')"><img src=<%=urlPrefix%>i/attribute_search.gif border=0></a>
          </td>       
             <td  >
               <a href="javascript:clearForm()"><img src=<%=urlPrefix%>i/reset.gif border=0></a>
              </td>
              
              
           </tr>
      </table>       
    </td>
 </TR>
 </table>
 <table align="center">

  </table>
