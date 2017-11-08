<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<% response.setContentType("UTF-8");%>

<html:form action="/log_in.do" enctype="multipart/form-data;charset=UTF-8" method="post" >
    
    <TABLE id="te4">
        <TR id="te5">
            <td><bean:message key="login.user"  /></td>
            <td id="te6"><input type="text" id="user" name="user" value="" class="cssUserInput" style="width:200px;">
                <br>
                <div id="suggestions" class="cssSuggestionDiv"></div>
                <!-- <html:text property="user"/>--></td><html:errors property="user" />
        </tr>
        <TR>
            <td><bean:message key="login.psd" /></td>
            <td><html:password property="psd" value=""/></td><html:errors property="psd" />
        </tr>
        <tr>
            <td><html:submit><bean:message key="login.prompt.submit"/></html:submit></td>
            <td><html:reset><bean:message key="login.prompt.reset"/></html:reset></td>
        </tr>
        <html:errors name="err"/> <html:errors name="err1"/> 
    </TABLE>
    <html:hidden property="status" value="${LoginForm.statusRegular}"/>
   
</html:form>
  
 

