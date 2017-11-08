<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.List" %>
<%@ page import="forms.sxolikoForm" %>
<bean:define id="form" name="form" />
<div align="center">
	<form action="<%=request.getContextPath()%>/sxoliko.do?action=save" enctype="multipart/form-data;charset=UTF-8" method="post">
		<h2 align="center">Επεξεργασία στοιχείων Σχολικής Χρονιάς:<html:select property="id_sxol_etos" value="${form.id_sxol_etos}" name="form">
                <html:option value="0">Επιλέξτε σχολική χρονιά:</html:option>
                <html:optionsCollection name="form" property="n_sxolika_eti" label="value" value="key"/>
                </html:select> για την ΕΠΑΣ  <bean:write name="form" property="name"/> </h2>
		<p align=center>
		<div style="border:1px solid #000000;">
			<table>
				<tr>
					<td><html:text property="apo" value="${form.apo}" name="form"/></td>
				</tr>
			</table>
	</div>
	</form> 
	
</div>