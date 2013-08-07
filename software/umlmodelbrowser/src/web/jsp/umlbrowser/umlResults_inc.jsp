<%--L
  Copyright Oracle Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
L--%>

 <script LANGUAGE="Javascript">
<!---
function actionConfirm(message, url){
if(confirm(message)) location.href = url;
}
// --->
</SCRIPT>
<%@ include file="showMessages.jsp" %>
<%
    String attributeurl = request.getContextPath()+"/umlbrowser/umlSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"="+UMLBrowserNavigationConstants.SHOW_ATTRIBUTE_SEARCH_METHOD;
    String ocbrowserurl = UMLBrowserParams.getInstance().getCdebrowserURL() +"ocbrowser/ocrDetailsAction.do?FirstTimer=0&method=getObjectClassRelationships&resetCrumbs=false&objectClassIdseq=";
%>   
   <logic:notEmpty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
       <jsp:include page="mltitab_inc.jsp" flush="true">
         <jsp:param name="label" value="Classes"/>
       </jsp:include>
    <A NAME="results"></A>
   
         <table width="100%" align="center" cellpadding="1" cellspacing="1" border="0">
               <tr>
                 <td align="left" class="OraTableColumnHeaderNoBG" width="10%" nowrap>Sort order :</td>
                 <td align="left" class="CDEBrowserPageContext">
                  <cde:sorableColumnHeaderBreadcrumb
                          sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
                          separator=">>" 
                          showDefault="Y"
                          labelMapping="name, Class Name, project.longName Project Name, project.version Project Version, UMLPackageMetadata.subProject.name, Sub Project Name, UMLPackageMetadata.name, Package Name, objectClass.longName, OC Name, objectClass.version, OC Version, objectClass.publicID, Class Details "
                          defaultText=" (Default) "
                          ascendingText=" [Ascending]"
                          descendingText=" [Descending]"                          
                   />           
                 </td> 
           
               </tr>
        </table>     
        
        <bean:define id="pageBean" name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION%>" 
        	type="gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean"/>
        <cde:pagination name="top" textClassName="OraFieldText" selectClassName="LOVField" formIndex="0" pageSize="100" 
                     beanId = "<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION%>" 
                     actionURL="pageAction.do"
        	     previousOnImage="i/prev_on.gif"
        	     previousOffImage="i/prev_off.gif"
        	     nextOnImage="i/next_on.gif"
        	     nextOffImage="i/next_off.gif"
        	     urlPrefix="<%=urlPrefix%>"
        	     /> 
                
        <table width="100%" align="center" cellpadding="1" cellspacing="1" border="0" class="OraBGAccentVeryDark">
          <tr class="OraTableColumnHeader">
          	<th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Class Name" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "name"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Project Name" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "project.longName"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Project Version" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "project.version"
		   	   	target="_self"
            />   
            </th>  
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Project Workflow Status" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "classificationScheme.workflowStatusName"
		   	   	target="_self"
            />   
            </th>             

            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Sub Project Name" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "UMLPackageMetadata.subProject.name"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Package Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "UMLPackageMetadata.name"
		   	   	target="_self"
            />   
            </th>    
            <!--
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="OC Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "objectClass.longName"
		   	   	target="_self"
            />  
            </th>      -->        
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Class Details" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "objectClass.publicID"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader">
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="OC Version" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "objectClass.version"
		   	   	target="_self"
            />   
            </th>             
          </tr>        
          <logic:iterate indexId="selectedUmlClassInx" id="umlClass" name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>" 
          	type="gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata"
                offset="<%=Integer.toString(pageBean.getOffset())%>"
                length="<%=Integer.toString(pageBean.getPageSize())%>">
            <tr class="OraTabledata">  
          	<td class="OraFieldText">
                <a href='<%=attributeurl%>&selectedClassIndex=<%=selectedUmlClassInx%>'>
                <bean:write name="umlClass" property="name"/></a>
          		<br>
          	</td>
          	<td class="OraFieldText">
          	<a href="javascript:newBrowserWin('<%=request.getContextPath()%>/umlbrowser/detailsPageAction.do?method=projectDetailsPage&<%=UMLBrowserFormConstants.PROJECT_IDSEQ%>=<%=umlClass.getProject().getId()%>','ProjectDetails',800,600)">
       		   <bean:write name="umlClass" property="project.longName"/>
       		</a>
       		 <br>
          	</td>             
          	<td class="OraFieldText">
       		<bean:write name="umlClass" property="project.version"/><br>
          	</td>             
          	<td class="OraFieldText">
       		<bean:write name="umlClass" property="project.classificationScheme.workflowStatusName"/><br>
          	</td>             
          	<td class="OraFieldText">
          	 <logic:present name="umlClass" property="UMLPackageMetadata.subProject">
          		<bean:write name="umlClass" property="UMLPackageMetadata.subProject.name" ignore="true"/><br>
          	</logic:present>
          	</td>          
          	<td class="OraFieldText">
          		<bean:write name="umlClass" property="UMLPackageMetadata.name"/><br>
          	</td>   
          	
          	<%--<td class="OraFieldText">
                     <a href="javascript:newBrowserWin(<%=ocbrowserurl%>'<%=umlClass.getObjectClass().getId()%>'&cscsiId='<%=umlClass.getUMLPackageMetadata().getClassSchemeClassSchemeItem().getId()%>','OCDetails',800,600)">
                     <bean:write name="umlClass" property="objectClass.publicID"/></a>
                <br>
          	</td>               
          	--%><td class="OraFieldText">
          <html:link href='<%=ocbrowserurl+umlClass.getObjectClass().getId()+"&cscsiId="+umlClass.getUMLPackageMetadata().getClassSchemeClassSchemeItem().getId()%>' target="ocBrowser">
    		                     <bean:write name="umlClass" property="objectClass.publicID"/></a>
          		<br></html:link>
          	</td>               
          	<td class="OraFieldText">
          		<bean:write name="umlClass" property="objectClass.version"/><br>
          	</td>               
            </tr>
          </logic:iterate>
        </table>
        <cde:pagination name="bottom" textClassName="OraFieldText" selectClassName="LOVField" formIndex="0" pageSize="100" 
                     beanId = "<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION%>"
                     actionURL="pageAction.do"
        	     previousOnImage="i/prev_on.gif"
        	     previousOffImage="i/prev_off.gif"
        	     nextOnImage="i/next_on.gif"
        	     nextOffImage="i/next_off.gif"
        	     urlPrefix="<%=urlPrefix%>"
        	     /> 
       
        </logic:notEmpty>
        <logic:empty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
        <table width="100%" align="center" cellpadding="1" cellspacing="1" border="0" class="OraBGAccentVeryDark">
  	      <tr class="OraTableColumnHeader">
          	<th class="OraTableColumnHeader" nowrap>Class Name</th>
          	<th class="OraTableColumnHeader" nowrap>Project</th>
          	<th class="OraTableColumnHeader" nowrap>Project Version</th>
            <th class="OraTableColumnHeader" nowrap>Sub Project</th>
          	<th class="OraTableColumnHeader" nowrap>Package</th>
          	<th class="OraTableColumnHeader" nowrap>Object Class Name</th>
            <th class="OraTableColumnHeader" nowrap>OC Version</th>
          	<th class="OraTableColumnHeader" nowrap>Class Details</th>
          </tr>
      <tr class="OraTabledata" >
         	<td colspan="8" ><bean:message key="cadsr.umlbrowser.empty.search.results"/></td>
  	  </tr>
  	</table>        
                 
        </logic:empty>
