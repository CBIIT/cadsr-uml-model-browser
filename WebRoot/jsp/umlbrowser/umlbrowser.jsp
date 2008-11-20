<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="gov.nih.nci.ncicb.cadsr.util.* "%>
<%@ page import="gov.nih.nci.ncicb.cadsr.CaDSRConstants"%>
<%@ page import="java.util.HashMap "%>
<%@ page import="gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants "%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/cdebrowser.tld" prefix="cde"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<HTML>
	<HEAD>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1252">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<TITLE>UML Model Browser</TITLE>
	</HEAD>
	<jsp:useBean id="requestMap" scope="request" class="java.util.HashMap" />
	<frameset rows="8%,*">
		<html:frame page="/jsp/common/topHeader.jsp" frameborder="0"
			scrolling="no" frameName="tree_header" />
		<frameset cols="25%,*">
			<frameset rows="15%,*">
			    <%System.out.println("getContext URL: " + request.getContextPath());%>
				<%String treeUrl = "/jsp/umlbrowser/tree2.jsf"; %>
				<html:frame page="/jsp/common/tree_hdr.jsp" name="requestMap"
					frameborder="0" frameName="tree_header" scrolling="no" />
				<html:frame page='<%= treeUrl%>' name="requestMap" frameborder="0" frameName="tree" />
			</frameset>
			<html:frame action="/umlbrowser/umlSearchAction?method=initSearch"
				name="requestMap" frameborder="0" frameName="body" />
		</frameset>
	</frameset>
</HTML>
