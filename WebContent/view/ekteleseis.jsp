<%-- 
    Document   : ekteleseis
    Created on : 16 Δεκ 2014, 12:12:58 μμ
    Author     : Administrator
--%>

<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<logic:iterate name="ektelesiForm" property="m" id="mId">
   <form method="post" action="/ogeka/<bean:write name="mId" property="link" />" > 
        <input type="submit" value="<bean:write name="mId" property="value"/>" />
   </form>
</logic:iterate>
    


     
