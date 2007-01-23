<%@ page errorPage="/common/systemError.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:view>

<t:document>
<t:documentHead>
<LINK rel="stylesheet" TYPE="text/css" HREF="../css/TreeBrowser.css"/>
  <script language="JavaScript1.2" src="../jsLib/JavaScript.js"></script>
  <script language="JavaScript1.2">
  <!--
function classSearchAction(urlParams){
    var frm = findFrameByName('body');
    document.body.style.cursor = "wait";
    frm.document.body.style.cursor = "wait";
    frm.document.location = "<%=request.getContextPath()%>/umlbrowser/umlSearchAction.do?method=treeClassSearch&"+urlParams;
}
  //-->
  </script>
</t:documentHead>

<t:documentBody>



<h:form id="umlBrowserTree">

    <h:commandLink value="Refresh tree" action="#{treeBacker.refreshTree}"/>
    <br/><br/>

    <!-- Expand/Collapse Handled By Server -->
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
                 <h:outputText value="#{node.description}" styleClass="treeNode"/>
                </h:outputLink>
            </h:panelGroup>
        </f:facet>
        <f:facet name="Package Folder">
         <h:panelGroup style="white-space:nowrap;">
           <t:graphicImage value="/i/tree-package.gif" alt="Package" title="Package" border="0"/>
                <h:outputLink id="pkgLink" value="#{node.action}">
                 <h:outputText value="#{node.description}" styleClass="treeNode"/>
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
    String autoScroll = request.getParameter("autoScroll");
    if (autoScroll != null && !"".equals(autoScroll)) {
        %>
	    addLoadEvent(function() {
  			parent.frames['tree'].scrollTo(<%=autoScroll%>);
  		});
        <%
    }
  %>
</script>
