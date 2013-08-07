<%--L
  Copyright Oracle Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
L--%>

<%@ page errorPage="/common/systemError.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="org.owasp.esapi.*" %>
<%@ page import="java.util.regex.Pattern" %>

<%!     
      private static final Pattern AUTOSCROLL_PATTERN = Pattern.compile("[0-9]*[,][0-9]*");
      private static final Pattern NAVCMD_PATTERN = Pattern.compile("[0-9:]*");
      private static final Pattern INT_PATTERN = Pattern.compile("[0-9]*");
      private static final Pattern LINK_HIDDEN_PATTERN = Pattern.compile("[umlBrowserTree:]*[serverTree:]*([[0-9]*[:]]*(t2g|_idJsp[0-9]))");
      private static final Pattern TREE_PARAMS_PATTERN = Pattern.compile("[[a-zA-Z0-9]*[;]?[:]?[a-zA-Z0-9]*]*");
      private static final Pattern DOC_NUM_PATTERN = Pattern.compile("[[a-fA-F0-9]*[-]]*");
      																	
      private void filterHiddenVariables(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException{
            boolean valid = true;
            
            String autoScroll = request.getParameter("autoScroll");
            String jsfSeq = request.getParameter("jsf_sequence");
            String linkHidden = request.getParameter("umlBrowserTree:_link_hidden_");
            String navCmd = request.getParameter("serverTree:org.apache.myfaces.tree.NAV_COMMAND");
            String submit = request.getParameter("umlBrowserTree_SUBMIT");
            String treeParams = request.getParameter("treeParams");
            String docNum = request.getParameter("docNum");
            
            if (autoScroll != null && !AUTOSCROLL_PATTERN.matcher(autoScroll).matches()) {
                  System.out.println("Auto Scroll:"+autoScroll);
            	  valid = false;
      		}
            if (jsfSeq != null && valid && !INT_PATTERN.matcher(jsfSeq).matches()) {
                  System.out.println("JSF Seq:"+jsfSeq);
                  valid = false;
            }
            if (linkHidden != null && valid && !LINK_HIDDEN_PATTERN.matcher(linkHidden).matches()) {
                  System.out.println("Link Hidden:"+linkHidden);
                  valid = false;
            }
            if (navCmd != null && valid && !NAVCMD_PATTERN.matcher(navCmd).matches()) {
                  System.out.println("Nav Cmd:"+navCmd);
                  valid = false;
            }
            if (submit != null && valid && !INT_PATTERN.matcher(submit).matches()) {
                  System.out.println("Submit:"+submit);
                  valid = false;
            }
            if (treeParams != null && valid && !TREE_PARAMS_PATTERN.matcher(treeParams).matches()) {
                  System.out.println("Tree params:"+treeParams);
                  valid = false;
            }
            if (docNum != null && valid && !DOC_NUM_PATTERN.matcher(docNum).matches()) {
                  System.out.println("docNum:"+docNum);
                  valid = false;
            }
            
            if (!valid) {
                  response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
      }     

%>


<f:view>

<t:document>
<t:documentHead>
<% filterHiddenVariables(request, response); %>	

<LINK rel="stylesheet" TYPE="text/css" HREF="../css/TreeBrowser.css"/>
  <script language="JavaScript1.2" src="../jsLib/JavaScript.js"></script>
  <script language="JavaScript1.2">
  <!--
function classSearchAction(urlParams){
    var frm = findFrameByName('body');
    document.body.style.cursor = "wait";
    frm.document.body.style.cursor = "wait";
    frm.document.location = "<%=ESAPI.encoder().encodeForJavaScript(request.getContextPath())%>/umlbrowser/umlSearchAction.do?method=treeClassSearch&"+urlParams;
}
  //-->
  </script>
</t:documentHead>

<t:documentBody>



<h:form id="umlBrowserTree">
<br>
 <bean:message key="cadsr.umlbrowser.tree.resultNotFound"/>
<br/>
<br/>
    <h:commandLink value="Refresh tree" action="#{treeBacker.refreshTree}"/>
    <br/><br/>


    <t:tree2 id="serverTree" value="#{treeBacker.treeModel}" var="node" varNodeToggler="t" clientSideToggle="false" binding="#{treeBacker.tree}">
        <f:facet name="Context Folder">
            <h:panelGroup >
                <t:graphicImage alt="Context" title="Context" value="/i/yellow-folder-open.png" rendered="#{t.nodeExpanded}" border="0"/>
                <t:graphicImage value="/i/yellow-folder-closed.png" alt="Context" title="Context" rendered="#{!t.nodeExpanded}" border="0"/>
                <h:outputLink id="contextLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode"/>
                </h:outputLink>
            </h:panelGroup>
        </f:facet>
        <f:facet name="Context">
            <h:panelGroup style="white-space:nowrap;">
                <h:commandLink immediate="true"
                               action="#{treeBacker.selectedNode}"
                               actionListener="#{t.setNodeSelected}">
                    <t:graphicImage value="/i/yellow-folder-closed.png"
                                    border="0"/>
                    <f:param name="docNum"
                    		 value="#{node.identifier}"/>
                </h:commandLink>
                <h:outputLink 
                              value="#{node.action}">
                    <h:outputText value="#{node.description}"
                                  styleClass="treeNode"/>
                </h:outputLink>
            </h:panelGroup>
       </f:facet>
        <f:facet name="Container">
        <h:panelGroup style="white-space:nowrap;">
                <t:graphicImage value="/i/container.png" alt="Project" title="Container" border="0"/>
                 <h:outputLink id="containerLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode" title="#{node.toolTip}"/>
                </h:outputLink>
         </h:panelGroup>
        </f:facet>
        <f:facet name="Project Folder">
        <h:panelGroup style="white-space:nowrap;">
                <t:graphicImage value="/i/yellow-folder-open.png" alt="Project" title="Project" rendered="#{t.nodeExpanded}" border="0"/>
                <t:graphicImage value="/i/yellow-folder-closed.png" alt="Project" title="Project" rendered="#{!t.nodeExpanded}" border="0"/>
                 <h:outputLink id="projectLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode" title="#{node.toolTip}"/>
                </h:outputLink>
         </h:panelGroup>
        </f:facet>
        <f:facet name="SubProject Folder">
        <h:panelGroup style="white-space:nowrap;">
                <t:graphicImage value="/i/yellow-folder-open.png" alt="SubProject" title="SubProject" rendered="#{t.nodeExpanded}" border="0"/>
                <t:graphicImage value="/i/yellow-folder-closed.png" alt="SubProject" title="SubProject" rendered="#{!t.nodeExpanded}" border="0"/>
                 <h:outputLink id="subProjLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode" title="#{node.toolTip}"/>
                </h:outputLink>
            </h:panelGroup>
        </f:facet>
        <f:facet name="Package Folder">
         <h:panelGroup style="white-space:nowrap;">
           <t:graphicImage value="/i/tree-package.gif" alt="Package" title="Package" border="0"/>
                <h:outputLink id="pkgLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode" title="#{node.toolTip}"/>
                </h:outputLink>
            </h:panelGroup>
        </f:facet>
        <f:facet name="Class Node">
         <h:panelGroup style="white-space:nowrap;">
                 <t:graphicImage value="/i/tree-class.gif"  alt="Class" title="Class" border="0"/>
                <h:outputLink id="classLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode"/>
                </h:outputLink>
            </h:panelGroup>
        </f:facet>
    </t:tree2>
                <if you="you" dont="don’t" see="see" a="a" recently="recently"
                    loaded="loaded" model="model," please="please" click="click"
                    refresh="“Refresh" tree="tree”"/>
            </h:form>

</t:documentBody>

</t:document>

</f:view>

<script type="text/javascript"> 

	function addLoadEvent(func) {
	  var oldonload = window.onload;
	  if (typeof window.onload != 'function') {
	    window.onload = func;
	  } else {
	    window.onload = function() {
	      if (oldonload) {
	        oldonload();
	      }
	      func();
	    }
	  }
	}

  // Fix autoscroll for frame
  <%
    String autoScroll = ESAPI.encoder().encodeForJavaScript(request.getParameter("autoScroll"));
    if (autoScroll != null && !"".equals(autoScroll)) {
        %>
	    addLoadEvent(function() {
  			parent.frames['tree'].scrollTo(<%=autoScroll%>);
  		});
        <%
    }
  %>
</script>
