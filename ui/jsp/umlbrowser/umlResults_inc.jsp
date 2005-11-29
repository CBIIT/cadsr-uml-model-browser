

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
%>   
   <logic:notEmpty name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>">
       <jsp:include page="mltitab_inc.jsp" flush="true">
         <jsp:param name="label" value="Classes"/>
       </jsp:include>
   
         <table width="100%" align="center" cellpadding="1" cellspacing="1" border="0">
               <tr>
                 <td align="left" class="OraTableColumnHeaderNoBG" width="10%" nowrap>Sort order :</td>
                 <td align="left" class="CDEBrowserPageContext">
                  <cde:sorableColumnHeaderBreadcrumb
                          sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
                          separator=">>" 
                          showDefault="Y"
                          labelMapping="className, Class Name, projectName Project Name,subProjectName, Sub Project Name, packageName, Package Name, ocName, OC Name, ocVersion, OC Version, ocPublicId, OC Public ID "
                          defaultText=" (Default) "
                          ascendingText=" [Ascending]"
                          descendingText=" [Descending]"                          
                   />           
                 </td> 
           
               </tr>
        </table>     
        
        <bean:define id="pageBean" name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS_PAGINATION%>" 
        	type="gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean"/>
        <cde:pagination name="top" textClassName="OraFieldText" selectClassName="LOVField" formIndex="0" pageSize="40" 
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
          	<th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Class Name" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "className"
		   	   	target="_parent"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Project Name" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "projectName"
		   	   	target="_parent"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Sub Project Name" 
            orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "subProjectName"
		   	   	target="_parent"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="Package Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "packageName"
		   	   	target="_parent"
            />   
            </th>    
            <!--
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="OC Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "ocName"
		   	   	target="_parent"
            />  
            </th>      -->        
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="OC Public ID" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "ocPublicId"
		   	   	target="_parent"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/umlSortSearchAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortResult"%>' 
		   	   	columnHeader="OC Version" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "ocVersion"
		   	   	target="_parent"
            />   
            </th>             
          </tr>        
          <logic:iterate id="umlClass" name="<%=UMLBrowserFormConstants.CLASS_SEARCH_RESULTS%>" 
          	type="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common.UmlClass"
                offset="<%=Integer.toString(pageBean.getOffset())%>"
                length="<%=Integer.toString(pageBean.getPageSize())%>">
            <tr class="OraTabledata">  
          	<td class="OraFieldText">
                <a href='<%=attributeurl%>'><bean:write name="umlClass" property="className"/></a>

          		<br>
          	</td>
          	<td class="OraFieldText">
       		<bean:write name="umlClass" property="projectName"/><br>
          	</td>             
          	<td class="OraFieldText">
          		<bean:write name="umlClass" property="subProjectName"/><br>
          	</td>          
          	<td class="OraFieldText">
          		<bean:write name="umlClass" property="packageName"/><br>
          	</td>   
          	<!--
          	<td class="OraFieldText">
                     <a href="javascript:newBrowserWin('/CDEBrowser/ocbrowser/ocDetailsAction.do?method=getObjectClass&resetCrumbs=true&objectClassIdseq=F37D0428-F46E-6787-E034-0003BA3F9857','OCDetails',800,600)">
                     <bean:write name="umlClass" property="ocName"/></a>
                <br>
          	</td>   -->            
          	<td class="OraFieldText">
          	                     <a href="javascript:newBrowserWin('/CDEBrowser/ocbrowser/ocDetailsAction.do?method=getObjectClass&resetCrumbs=true&objectClassIdseq=F37D0428-F46E-6787-E034-0003BA3F9857','OCDetails',800,600)">
		                     <bean:write name="umlClass" property="ocPublicId"/></a>
          		<br>
          	</td>               
          	<td class="OraFieldText">
          		<bean:write name="umlClass" property="ocVersion"/><br>
          	</td>               
            </tr>
          </logic:iterate>
        </table>
        <cde:pagination name="bottom" textClassName="OraFieldText" selectClassName="LOVField" formIndex="0" pageSize="40" 
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
            <th class="OraTableColumnHeader" nowrap>Sub Project</th>
          	<th class="OraTableColumnHeader" nowrap>Package</th>
          	<th class="OraTableColumnHeader" nowrap>Object Class Name</th>
            <th class="OraTableColumnHeader" nowrap>OC Version</th>
          	<th class="OraTableColumnHeader" nowrap>OC Public ID</th>
          </tr>
      <tr class="OraTabledata" >
         	<td colspan="8" ><bean:message key="cadsr.umlbrowser.empty.search.results"/></td>
  	  </tr>
  	</table>        
                 
        </logic:empty>
