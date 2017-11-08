<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div>
<html:form action="/logoff.do" enctype="multipart/form-data: charset=utf-8">
   
    <bean:message key="login.as" arg0="${LoginForm.user}"/>
    <html:submit><bean:message key="login.as.submit"/></html:submit>
    <html:errors name="err"/> 
    <html:hidden property="status" value="${LoginForm.statusLogOff}"/>       
</html:form>
</DIV>