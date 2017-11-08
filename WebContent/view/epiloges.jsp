<%-- 
    Document   : epiloges
    Created on : 17 Δεκ 2014, 10:03:28 πμ
    Author     : Administrator
--%>

<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<div>
<logic:notEmpty name="ar">
  <table>
    <logic:iterate name="ar" id="arId">
       <tr><td><bean:write name="arId" property="label"/> </td></tr>
    </logic:iterate>
  </table>       
</logic:notEmpty></div>


    
    

  
    

