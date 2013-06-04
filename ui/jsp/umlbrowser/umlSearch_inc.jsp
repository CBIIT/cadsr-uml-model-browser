<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UMLBrowserNavigationConstants" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

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
 <%--
    <td class="OraHeaderSubSub" width="60%" align="left" nowrap>Search For Classes</td>
 --%>
   <td valign="top" class="CDEBrowserPageContext"><c:out value="${pageContextInfo}" escapeXml="false"/></td >
  <%--
  <td align="right" width="20%" nowrap><a href="">Advanced search</a></td>
  --%>
    
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
   
   <td align="right" width="20%" nowrap>
           <html:link page="/umlbrowser/gotoSearchPreferencesAction.do?method=gotoSearchPreferences" target="_parent">
             Search preferences
           </html:link>
   </td>
     
         
 </tr>   
 <tr>
   <td  align="center" colspan="3"><html:img page="/i/beigedot.gif" alt="beigedot" border="0"  height="1" width="99%" align="top" /> </td>
  </tr> 
 </table>
 
    <table align="center" valign="top" width="100%" cellpadding="0" cellspacing="1" class="OraBGAccentVeryDark" border="0">
  
            <tr>
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap><label for="className">Class Name</label></td>
              <td class="OraTabledata" nowrap>
                <html:text styleId="className" property="<%=UMLBrowserFormConstants.CLASS_NAME%>" size="35" onkeypress="if (event.keyCode==13){submitForm('classSearch')};"/>
              </td>
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap><label for="attrName">Attribute Name</label></td>
              <td class="OraTabledata" nowrap>
                <html:text property="attributeName" styleId="attrName" size="35" onkeypress="if (event.keyCode==13){submitForm('classSearch')};" />
              </td>
            </tr>
   </table>
 
  <table align="center" valign="top" width="100%" cellpadding="0" cellspacing="1"  border="0" >
    <tr>
      <td width="50%"  valign="top" >
          <table width="100%" cellpadding="0" cellspacing="1" class="OraBGAccentVeryDark" border="0" %>
            <tr>            
                <td width="10%" class="OraTableColumnHeaderNoBG" nowrap><label for="UML Project Name">UML Project Name</label></td>
                <td class="OraTabledata"  nowrap width="50%">
                <html:select styleId="UML Project Name" styleClass = "FreeDropdown" property="<%=UMLBrowserFormConstants.PROJECT_IDSEQ%>"
                onchange="submitForm('resetSubProjPkgOptions')">
                   <html:option key="cadsr.umlbrowser.form.blank" value="<%=UMLBrowserFormConstants.SEARCH_ALL%>" /> 

          <logic:iterate id="umlProj" name="<%=UMLBrowserFormConstants.ALL_PROJECTS%>" 
          	type="gov.nih.nci.cadsr.umlproject.domain.Project">
            <option value="<%=umlProj.getId()%>" 
            <% 
             org.apache.struts.action.DynaActionForm dynaForm = (org.apache.struts.action.DynaActionForm) request.getAttribute("umlSearchForm");

            if (umlProj.getId().equals(dynaForm.get(UMLBrowserFormConstants.PROJECT_IDSEQ))) { %>
            selected="selected" <% } %>  > 
            <%=umlProj.getLongName()+" (v"+umlProj.getVersion() +", "+umlProj.getClassificationScheme().getWorkflowStatusName()+", "+umlProj.getClassificationScheme().getContext().getName() +")"%></option>
	    </logic:iterate> 
          </html:select>

                </td>
                <td width="20%" class="OraTableColumnHeaderNoBG"  nowrap><label for="version">Version</label></td>
                <td class="OraTabledata" nowrap width="20%">
                <html:text styleId="version" property="<%=UMLBrowserFormConstants.PROJECT_VERSION%>" size="10" onkeypress="if (event.keyCode==13){submitForm('classSearch')};"/>
                </td>
             </tr>
            <tr>            
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap><label for="subproject name">Sub Project Name</label></td>
              <td width="70%" class="OraTabledata" nowrap colspan="3">
                <html:select styleId="subproject name" styleClass = "FreeDropdown" property="<%=UMLBrowserFormConstants.SUB_PROJECT_IDSEQ%>" 
                onchange="submitForm('resetPkgOptions')">
                   <html:option key="cadsr.umlbrowser.form.blank" value="<%=UMLBrowserFormConstants.SEARCH_ALL%>" /> 
                   <html:options collection="<%=UMLBrowserFormConstants.SUBPROJECT_OPTIONS%>" 
                     property="id" labelProperty="name" />
                 </html:select>

                </td>
             </tr>  
            <tr>            
              <td width="10%" class="OraTableColumnHeaderNoBG" nowrap><label for="package name">Package Name</label></td>
             <td width="70%" class="OraTabledata" nowrap colspan="3">
                <html:select styleId="package name" styleClass = "FreeDropdown" property="<%=UMLBrowserFormConstants.PACKAGE_IDSEQ%>">
                   <html:option key="cadsr.umlbrowser.form.blank" value="<%=UMLBrowserFormConstants.SEARCH_ALL%>" /> 
                   <html:options collection="<%=UMLBrowserFormConstants.PACKAGE_OPTIONS%>" 
                     property="id" labelProperty="name" />
                 </html:select>

              </td>  
             </tr>             
           </table>      
 
    
</table>
 <table align="center">
  <TR>
    <td   align="center" nowrap >
      <table align="center" >
           <tr>
        <td nowrap>
            <a href="javascript:submitForm('<%=UMLBrowserNavigationConstants.CLASS_SEARCH_METHOD%>')"><img src=<%=urlPrefix%>i/class_search.gif alt="class search" border=0></a>
          </td>       
        <td nowrap>
            <a href="javascript:submitForm('<%=UMLBrowserNavigationConstants.ATTRIBUTE_SEARCH_METHOD%>')"><img src=<%=urlPrefix%>i/attribute_search.gif alt="attribute search" border=0></a>
          </td>       
             <td  >
               <a href="javascript:clearForm()"><img src=<%=urlPrefix%>i/reset.gif alt=-"reset" border=0></a>
              </td>
              
              
           </tr>
      </table>       
    </td>
 </TR>
 </table>
