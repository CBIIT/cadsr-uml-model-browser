

<%
    String cdeDetailUrl = UMLBrowserParams.getInstance().getCdebrowserURL() +"search?dataElementDetails=9&PageId=DataElementsGroup&queryDE=yes&p_de_idseq=";
%>   
<%@ include file="showMessages.jsp" %>
       <jsp:include page="mltitab_inc.jsp" flush="true">
         <jsp:param name="label" value="Attributes"/>
       </jsp:include>
       
   <logic:notEmpty name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>">
     <A NAME="results"></A>
 
 <table width="100%" align="center" cellpadding="1" cellspacing="1" border="0">
       <tr>
         <td align="left" class="OraTableColumnHeaderNoBG" width="10%" nowrap>Sort order :</td>
         <td align="left" class="CDEBrowserPageContext">
          <cde:sorableColumnHeaderBreadcrumb
                  sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
                  separator=">>" 
                  showDefault="Y"
                  labelMapping="name, Attribute Name, dataElement.version, Version, dataElement.context.name, Context, dataType, Data Type, dataElement.longName, DE Name, dataElement.publicID, DE Public ID, description, Definition, project.longName, Project Name, UMLPackageMetadata.subProject.name, Sub Project Name, UMLClassMetadata.UMLPackageMetadata.name, Package Name "
                  defaultText=" (Default) "
                  ascendingText=" [Ascending]"
                  descendingText=" [Descending]"                          
           />           
         </td> 
   
       </tr>
</table>     
        
        <bean:define id="pageBean" name="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION%>" 
        	type="gov.nih.nci.ncicb.cadsr.jsp.bean.PaginationBean"/>
        <cde:pagination name="top" textClassName="OraFieldText" selectClassName="LOVField" formIndex="0" pageSize="40" 
                     beanId = "<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION%>" 
                     actionURL="AttributePageAction.do"
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
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Attribute Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "name"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Version" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "dataElement.version"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Context" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "dataElement.context.name"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Data Type" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "dataType"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Definition" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "description"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="DE Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "dataElement.longName"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="DE Public ID" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "dataElement.publicID"
		   	   	target="_self"
            />  
            </th>             

            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Project Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "project.longName"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Sub Project" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "UMLPackageMetadata.subProject.name"
		   	   	target="_self"
            />   
            </th>             
            <th class="OraTableColumnHeader" nowrap>
		        <cde:sortableColumnHeader
            sortableColumnHeaderBeanId="<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULT_COMPARATOR%>" 
		       	actionUrl='<%="/umlbrowser/sortAttributesAction.do?"+UMLBrowserNavigationConstants.METHOD_PARAM+"=sortAttributes"%>' 
		   	   	columnHeader="Package Name" 
                                orderParamId="sortOrder" 
		   	   	sortFieldId="sortField"
		   	   	sortFieldValue = "UMLClassMetadata.UMLPackageMetadata.name"
		   	   	target="_self"
            />   
            </th>             
          </tr>        
          <logic:iterate id="umlAttribute" name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>" 
          	type="gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata"
                offset="<%=Integer.toString(pageBean.getOffset())%>"
                length="<%=Integer.toString(pageBean.getPageSize())%>">
            <tr class="OraTabledata">  
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="name"/><br>
          	</td>
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="dataElement.version"/><br>
          	</td>
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="dataElement.context.name"/><br>
          	</td>
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="attributeTypeMetadata.valueDomainDataType"/><br>

          	</td>             
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="description"/><br>
          	</td>               
          	<td class="OraFieldText">
                <html:link href='<%=cdeDetailUrl+umlAttribute.getDataElement().getId()%>' target="CDEDetails">
                <bean:write name="umlAttribute" property="dataElement.longName"/>
                </html:link>
          		<br>
          	</td>               
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="dataElement.publicID"/><br>
          	</td>               

          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="project.longName"/><br>
          	</td>   
          	<td class="OraFieldText">
          	 <logic:present name="umlAttribute" property="UMLPackageMetadata.subProject">
          		<bean:write name="umlAttribute" property="UMLPackageMetadata.subProject.name" ignore="true"/><br>
          	</logic:present>
          		<br>
          	</td>               
          	<td class="OraFieldText">
          		<bean:write name="umlAttribute" property="UMLClassMetadata.UMLPackageMetadata.name"/><br>
          	</td>               
            </tr>
          </logic:iterate>
        </table>
        <cde:pagination name="bottom" textClassName="OraFieldText" selectClassName="LOVField" formIndex="0" pageSize="40" 
                     beanId = "<%=UMLBrowserFormConstants.ATTRIBUTE_SEARCH_RESULTS_PAGINATION%>"
                     actionURL="AttributePageAction.do"
        	     previousOnImage="i/prev_on.gif"
        	     previousOffImage="i/prev_off.gif"
        	     nextOnImage="i/next_on.gif"
        	     nextOffImage="i/next_off.gif"
        	     urlPrefix="<%=urlPrefix%>"
        	     /> 
       
        </logic:notEmpty>
   
        <logic:empty name="<%=UMLBrowserFormConstants.CLASS_ATTRIBUTES%>">
	       <table width="100%" align="center" cellpadding="1" cellspacing="1" border="0" class="OraBGAccentVeryDark">
  	      <tr class="OraTableColumnHeader">
          	<th class="OraTableColumnHeader" nowrap>Attribute Name</th>
          	<th class="OraTableColumnHeader" nowrap>Data Type</th>
           	<th class="OraTableColumnHeader" nowrap>DE Name</th>
          	<th class="OraTableColumnHeader" nowrap>DE Public ID</th>
             <th class="OraTableColumnHeader" nowrap>Property Name</th>
          	<th class="OraTableColumnHeader" nowrap>Property Public ID</th>
          	<th class="OraTableColumnHeader" nowrap>DEC Name</th>
            <th class="OraTableColumnHeader" nowrap>DEC Public ID</th>
          </tr>
      <tr class="OraTabledata" >
         	<td colspan="8" ><bean:message key="cadsr.umlbrowser.empty.attributes.search.results"/></td>
  	  </tr>
  	</table>        
                 
        </logic:empty>
