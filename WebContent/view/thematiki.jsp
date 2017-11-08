<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<form action="<%=request.getContextPath()%>/thematiki.do?action=add" enctype="multipart/form-data;charset=UTF-8" method="post">
	<h2 align="center">Φόρμα Εισαγωγής Θεματικής Ενότητας</h2>
	<table>
		<tr>
		<td>Τίτλος Θεματικής ενότητας</td><td><input type=text name="perigrafi"  size="70"/></td>
		</tr>
		<tr>


		<td><input type="submit" value="Αποθήκευση"/></td>
        <td><input type="reset" value="Αναίρεση"></td>
        </tr>
     </table>
     
</form>