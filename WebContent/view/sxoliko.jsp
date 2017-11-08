<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.List" %>
<%@ page import="forms.sxolikoForm" %>
<bean:define id="form" name="form" />

<div align="center">
	<h2 align="center">Δημιουργία Σχολικής Χρονιάς για την ΕΠΑΣ  <bean:write name="form" property="name"/> </h2>
	<p align=center>
	<div style="border:1px solid #000000;">
	<form action="<%=request.getContextPath()%>/sxoliko.do?action=add" enctype="multipart/form-data;charset=UTF-8" method="post">
	  <link type="text/css" href="<%=request.getContextPath()%>/css/jquery.simple-dtpicker.css" rel="stylesheet" /> 
	  <table>
	  	<tr>
	  	 <td>Ειδικότητα Σχολής:</td>
	  	 <td>
	  	 	<html:select property="eidikotita" value="${form.eidikotita}">
	  	 	<html:option value="0">Επιλέξτε Ειδικότητα Σχολής:</html:option>
	  	 	<html:optionsCollection name="form" property="n_eidikotites" label="value" value="key"/>
	  	 	</html:select>
	  	 </td>
	  	 	<html:errors property="id_eidikotita" />
	  	 
	  	 <td>Σχολικό Έτος:</td>
	  	 <td>
	  	 	<html:select property="id_sxol_etos" value="${form.id_sxol_etos}">
	  	 	<html:option value="0">Επιλέξτε σχολική χρονιά:</html:option>
	  	 	<html:optionsCollection name="form" property="n_sxolika_eti" label="value" value="key"/>
	  	 	</html:select>
	  	 </td>
	  	 	<html:errors property="sxolika_eti" />
	   	</tr>
	   	<tr>
	   		<td>Τάξη Α:<input type="checkbox" name="taxiA" checked disabled/></td><td>Αριθμός Τμημάτων:<html:text name="form" property="tmimaA" value="${form.tmimaA}"/></td><html:errors property="tmimaA"  />
	   		<td>Τάξη Β:<input type="checkbox" name="taxiΒ" checked disabled/></td><td>Αριθμός Τμημάτων:<html:text name="form" property="tmimaB" value="${form.tmimaB}"/></td><html:errors property="tmimaB" />
	   	</tr>
	   	<tr>
	   	<td><bean:message key="ypoprogramma.apo"/></td>
       	<td>
       		<html:text property="apo" name="form" value="${form.apo}"/>
       		<script type="text/javascript">
				$(function(){
					$('*[name=apo]').appendDtpicker({
						"onHide":  function(handler){
							handler.show();
						}, 
						"dateOnly": true,
						"autodateOnStart": false,
						"dateFormat": "DD/MM/YYYY",
						"locale": "gr",
						"closeOnSelected": true
					});
			    });
			</script> 
			
		</td>
		<td><bean:message key="ypoprogramma.ews"/></td>
       	<td>
       		<html:text property="eos" value="${form.eos}"/>
       		<script type="text/javascript">
				$(function(){
					$('*[name=eos]').appendDtpicker({
						"onHide":  function(handler){
							handler.show();
						},
						"dateOnly": true,
						"autodateOnStart": false,
						"dateFormat": "DD/MM/YYYY",
						"locale": "gr",
						"closeOnSelected": true
					});
			    });
			</script> 
			
		</td>
	   	
	   	
	   	<tr>
	   	  	<td></td><td><input type="submit" value="Αποθήκευση"/></td><td><input type="reset" value="Αναίρεση" /></td><td></td>
        </tr>
	   	
	   	
	   	
	   	
	   	
	   	
	  </table>
	  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.simple-dtpicker.js"></script>
	</form>
	</div>
</div>