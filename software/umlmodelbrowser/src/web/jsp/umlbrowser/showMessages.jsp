<%--L
  Copyright Oracle Inc, SAIC-F

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
L--%>

<logic:messagesPresent >
  <table width="100%" align="center">
    <html:messages id="error" >
      <logic:present name="error">
      <tr align="center" >
        <td  align="left" class="OraErrorText" >         
          <b><bean:write  name="error"/></b><br>
        </td>
      </tr>
      </logic:present>      
    </html:messages>           
  </table>
</logic:messagesPresent>  
<logic:messagesPresent message="true">
  <table width="100%" align="center">
    <html:messages id="message" 
      message="true">
      <logic:present name="message">
      <tr align="center" >
        <td  align="left" class="MessageText" >        
          <b><bean:write  name="message"/></b><br>
        </td>
      </tr>
     </logic:present>
    </html:messages>      
  </table>
</logic:messagesPresent>  

