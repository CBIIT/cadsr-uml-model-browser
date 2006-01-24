<%@ page contentType="text/html;charset=windows-1252"%>
<%@page import="gov.nih.nci.ncicb.cadsr.util.* " %>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>

<%@page import="java.util.HashMap " %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants " %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/cdebrowser.tld" prefix="cde"%>


  
<%
   String ctepUser = (String)pageContext.getAttribute("accessValue");
   String treeURL;
/*
  String umlTreeURL;
    umlTreeURL = 
      "common/WebTreeLoader.jsp?treeClass=gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.UMLBrowserTree"+
      "&treeParams="+TreeConstants.TREE_TYPE_URL_PARAM +":" + 
      TreeConstants.CLASS_SEARCH_TREE + ";" +
      TreeConstants.FUNCTION_NAME_URL_PARAM + ":" +
      TreeConstants.CLASS_SEARCH_FUNCTION + ";" +
      TreeConstants.CTEP_USER_FLAG + ":" +
      ctepUser +
      "&treeName=umlClassTree" +
      "&skin=umlbrowser";
 
 */
 
     treeURL = 
       "common/WebTreeLoader.jsp?treeClass=gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.UMLBrowserTree"+
       "&treeParams="+TreeConstants.TREE_TYPE_URL_PARAM +":" + 
       TreeConstants.CLASS_SEARCH_TREE + ";" +
       TreeConstants.FUNCTION_NAME_URL_PARAM + ":" +
       TreeConstants.CLASS_SEARCH_FUNCTION + ";" +
       TreeConstants.CTEP_USER_FLAG + ":" +
       ctepUser +
       "&treeName=umlClassTree" +
       "&skin=umlbrowser";

%>
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1252">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<TITLE>
UML Model Browser
</TITLE>
</HEAD>
  <jsp:useBean id="requestMap" scope="request" class="java.util.HashMap" />
  
  <frameset cols="25%,*">
    <frameset rows="15%,*">
       <html:frame page="/common/tree_hdr.html"
              name="requestMap"
              frameborder="0"
              frameName="tree_header"
              scrolling = "no"/>
       <html:frame page='<%="/"+treeURL%>'
              name="requestMap"
              frameborder="0"
              frameName="tree"
              />              
    </frameset>
       <html:frame action="/umlbrowser/umlSearchAction?method=initSearch"
              name="requestMap"
              frameborder="0"
              frameName="body"
              />      
  </frameset>
   
</HTML>
