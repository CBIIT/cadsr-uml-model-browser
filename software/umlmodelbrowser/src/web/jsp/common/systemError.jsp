<%--L
  Copyright Oracle Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
L--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML>
  <HEAD>
    <TITLE>UML Model Browser: System Error</TITLE>
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
    <LINK rel="stylesheet" TYPE="text/css" HREF="<html:rewrite page='/css/blaf.css' />">
<script language="javascript">
if(self.location!=top.location)top.location="../common/systemError.jsp";
</script>
  </HEAD>
  <BODY topmargin=0 bgcolor="#ffffff">
    <% String urlPrefix = "";

%>
    <%@ include file="syserror_common_header_inc.jsp"%>
    <jsp:include page="../common/tab_inc.jsp" flush="true">
      <jsp:param name="label" value="System&nbsp;Error"/>
      <jsp:param name="urlPrefix" value=""/>
    </jsp:include>

    <table>
      <tr>
        <td class="OraErrorHeader">
        	Unexpected System Error has Occured <br><br>
      <logic:messagesPresent >
       <table width="80%" align="center">
        <html:messages id="error" >
          <logic:present name="error">
            <tr align="center" >
               <td  align="left" class="OraErrorText" nowrap>
                <b><bean:write  name="error"/></b>
              </td>
            </tr>
          </logic:present>          
        </html:messages> 
           <tr align="center" >
             <td>
                &nbsp;
            </td>
           </tr>        
       </table>
      </logic:messagesPresent>  
        </td>
      </tr>
    </table>
<%@ include file="/common/common_bottom_border.jsp"%>
  </BODY>
</HTML>
