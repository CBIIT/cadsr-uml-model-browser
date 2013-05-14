<%@ page contentType="text/html;charset=windows-1252"%>
<%@page import="gov.nih.nci.ncicb.cadsr.util.* " %>
<%@page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>

<%@page import="java.util.HashMap " %>
<%@page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants " %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/cdebrowser.tld" prefix="cde"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1252">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<TITLE>
UML Model Browser
</TITLE>

</HEAD>
  <jsp:useBean id="requestMap" scope="request" class="java.util.HashMap" />
  
  
<frameset rows="8%,*">
  <html:frame page="/common/topHeader.jsp" frameborder="0" scrolling = "no" frameName="tree_header" title="tree_header"/>
  <frameset cols="25%,*">
    <frameset rows="15%,*">
       <html:frame page="/common/tree_hdr.html"
              name="requestMap"
              frameborder="0"
              frameName="tree_header"
              title="tree_header"
              scrolling = "no"/>
       <html:frame page='/umlbrowser/tree2.jsf'
              name="requestMap"
              frameborder="0"
              frameName="tree"
              title="tree"
              />              
    </frameset>
 
       <html:frame action="/umlbrowser/umlSearchAction?method=initSearch"
              name="requestMap"
              frameborder="0"
              frameName="body"
              title="body"
              />      
  </frameset>  
   
</HTML>
