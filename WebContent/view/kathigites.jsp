<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.List" %>
<%@ page import="forms.kathigitesForm" %>
<bean:define id="form" name="form" />

<div align=center>
	<script type="text/javascript">
		var top=0;
		function chk()
		{
			var epwnymo=encodeURIComponent(document.getElementById("epwnymo").value);
			if(epwnymo.length<2)
				return;
			if(window.XMLHttpRequest)
	        {
	            xmlhttp=new XMLHttpRequest();
	            
	        }else{
	            xmlhttp=new AxtiveXObject("Microsoft.XMLHTTP");
	        }
			xmlhttp.onreadystatechange=function()
	        {
				document.getElementById("suggestions").innerHTML="";
				document.getElementById("suggestions").style.visibility="hidden";
				if(xmlhttp.readyState==XMLHttpRequest.DONE)
	            {
	            	
	            	while(xmlhttp.responseText===undefined || xmlhttp.responseText==="")
	            		return;
	            	var s=xmlhttp.responseText;
	            	s=s.trim();
	                var res=s.split("\n");
	                var div=document.getElementById("suggestions");
	                div.style.visibility="visible";
	                div.innerHTML="";
	                for(var i=0;i<res.length;i++)
	                {
	                    var tel=res[i].split(",");
	                    if(!(tel[0]===undefined))
	                    {
	                    	if(div.style.visibility==="hidden")
	                            div.style.visibility="visible";
	                    	div.innerHTML=div.innerHTML+"<div align=center id=\"suggestion" + tel[0] +"\" style=\"width:400px;visibility:visible;position:relative;top:" +top +";background-color:white;z-index:500\" >" +tel[1] +"</div>";
	                    	var sug=document.getElementById("suggestion" +tel[0]);
	                    	sug.setAttribute("ondblclick","dbclk(" + tel[0] +",\"" + tel[1]+"\")");
	                    	top+=20;
	                        
	                    }
	                    
	                }
	             
	        	}
			};
			
			var parameters="action=query";
			parameters+="&epwnymo="+epwnymo; 
			var is_ypal=encodeURIComponent(getCheckedRadioValue());
			parameters+="&is_ypal="+is_ypal;
	    	xmlhttp.open("POST","<%=request.getContextPath()%>/kathigites.do",true);
	    	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    	var responseTypeAware = 'responseType' in xmlhttp;
	    	try{
	    		if(responseTypeAware){
	    			xmlhttp.responseType = "text";
	    		}	
	    	}catch(e){}
	    	xmlhttp.send(parameters);
		};
		function getCheckedRadioValue() 
		{
		   var rads = document.getElementsByName("is_ypal");
		   for (var i=0; i < rads.length; i++)
		   if (rads[i].checked)
		   	return rads[i].value;
		   return null; // or undefined, or your preferred default for none checked
		}

		function dbclk(timi,perigrafi)
		{
	    	
			if(window.XMLHttpRequest)
	        {
	            xmlhttp=new XMLHttpRequest();
	            
	        }else{
	            xmlhttp=new AxtiveXObject("Microsoft.XMLHTTP");
	        }
			xmlhttp.onreadystatechange=function()
	        {
	            if(xmlhttp.readyState==XMLHttpRequest.DONE)
	            {
	            	while(xmlhttp.responseText==undefined)
                		return;
                	var s=xmlhttp.responseText;
                	if(s.indexOf("ok")==0)
                	{
                		location.href = "<%=request.getContextPath()%>/kathigites.do?action=keni";
                		return;	
                	}else
                	{
                		alert(s);
                		return;
                	} 
	        	}
			};
			
			var parameters="action=insert";
			parameters+="&id="+timi; 
			parameters+="&perigrafi="+perigrafi;
			
			xmlhttp.open("POST","<%=request.getContextPath()%>/kathigites.do",true);
	    	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    	var responseTypeAware = 'responseType' in xmlhttp;
	    	try{
	    		if(responseTypeAware){
	    			xmlhttp.responseType = "text";
	    		}	
	    	}catch(e){}
	    	xmlhttp.send(parameters);
		}	 
	    



	</script>
<logic:equal name="form" property="id_ekpaideyti" value="0">	
	<div align="center">
	<h2 align="center">Καρτέλα εγγραφής Καθηγητή </h2> 
	<p align=center>
	<form action="<%=request.getContextPath()%>/kathigites.do?action=add" enctype="multipart/form-data;charset=UTF-8" method="post">
	  <div style="border:1px solid #000000;">
	  	<h3>Στοιχεία Καθηγητή</h3>
	  		<link type="text/css" href="<%=request.getContextPath()%>/css/jquery.simple-dtpicker.css" rel="stylesheet" /> 
	  	<table>
	  		<tr><td>Υπάλληλος ΕΛΓΟ:</td><td><input type="radio" name="is_ypal" value="ypal" checked/></td><td>Εκτός ΕΛΓΟ:</td><td><input type="radio" name="is_ypal" value="not_ypal"/></td></tr>
	  		
	  		<tr><td>Επώνυμο:</td><td><html:text name="form" property="epwnymo"  onkeyup='chk()' styleId="epwnymo" size="40"/></td><td>Όνομα:</td><td><html:text name="form" property="onoma" size="40"/></td></tr>
	    	<tr><td>Πατρώνυμο:</td><td><html:text name="form" property="patrwnymo" value="" size="40"/></td>
	    		<td>Φύλο:</td><td><html:select property="sex"  value="${form.sex}">
	  	 		<html:optionsCollection name="form" property="nsex" label="value" value="key"/>
	  	 		</html:select></td>
	  	 	</tr>
			<tr><td>ΑΦΜ:</td><td><html:text name="form" property="afm" size="40"/></td>
				<td>Αρ. ταυτότητας:</td><td><html:text name="form" property="ar_taytotitas" size="30"/></td>
			</tr>
			<tr><td>Email:</td><td><html:text name="form" property="email" size="40"/></td>
				<td>Τηλέφωνο:</td><td><html:text name="form" property="tilefono" size="30"/></td>
			</tr>
			<tr><td>Κατηγορία:</td><td><html:select property="id_ekpaideyseis" value="0" >
	  	 		<html:option value="0">Επιλέξτε Κατηγορία</html:option>
	  	 		<html:optionsCollection name="form" property="nid_katigoria" label="value" value="key"/></html:select></td>
				<td>Ειδικότητα:</td><td><html:select property="id_eidikotita" styleId="id_eidikotita" value="0" >
	  	 		<html:option value="0">Επιλέξτε Ειδικότητα</html:option>
	  	 		<html:optionsCollection name="form" property="nid_eidikotita" label="value" value="key"/></html:select>
	  	 	</tr>	
	  	 	<tr><td>Διδακτορικό:</td><td><html:select property="phd" value="${form.phd}" >
	  	 		<html:optionsCollection name="form" property="pol" label="value" value="key"/></html:select></td>
	  	 		 <td>Μεταπτυχιακό:</td><td><html:select property="msc" value="${form.msc}" >
	  	 		<html:optionsCollection name="form" property="pol" label="value" value="key"/></html:select></td>
	  	 	</tr>	
	  	 	<tr>
	  	 		</td><td>Τρόπος πρόσληψης:</td><td><html:select property="id_proslipsis" value="0" >
	  	 		<html:option value="0">Επιλέξτε τρόπο πρόσληψης</html:option>
	  	 		<html:optionsCollection name="form" property="nid_proslipsis" label="value" value="key"/></html:select></td>
	  	 		<td>Λογαριασμός:</td><td><html:text name="form" property="logariasmos" value="" size="30"/></td>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>Πόλη (διαμονή):</td><td><html:text name="form" property="poli"  size="30"/></td>
	  	 		<td>Φορέας:</td><td><html:select property="id_forea" value="0" >
	  	 		<html:option value="0">Επιλέξτε Φορέα</html:option>
	  	 		<html:optionsCollection name="form" property="foreis" label="value" value="key"/></html:select></td>
	  	 	</tr>
	  	 	<tr><td>Διεύθυνση:</td><td><html:text name="form" property="address"  size="40"/></td>
	  	 		<td>Ταχ. Κώδικας:</td><td><html:text name="form" property="zip"  size="10"/></td>
	  	 	</tr>  	    
	  	</table>
	  	<div name="suggestions" id="suggestions" ALIGN="CENTER" ></div><br>
			<table align="center">
        		<tr>
            		<td><input type="submit" value="Αποθήκευση"/></td><td><input type="reset" value="Αναίρεση" /></td>
        		</tr>
    		</table>
	  	
	  	
	</form>
	
	
	</div>
</logic:equal>

<logic:greaterThan name="form" property="id_ekpaideyti" value="0"> 
	<div align="center">
	<h2 align="center">Καρτέλα ενημέρωσης Καθηγητή </h2>
	<p align=center>
	<form action="<%=request.getContextPath()%>/kathigites.do?action=save" enctype="multipart/form-data;charset=UTF-8" method="post">
	  <div style="border:1px solid #000000;">
	  	<h3>Ενημέρωση Στοιχείων του Καθηγητή</h3>
	  		<link type="text/css" href="<%=request.getContextPath()%>/css/jquery.simple-dtpicker.css" rel="stylesheet" /> 
	  	<table>
	  		<tr>
	  			<td>Επώνυμο:</td><td><html:text name="form" property="epwnymo" value="${form.epwnymo}" size="40"/></td>
	  			<td>Όνομα:</td><td><html:text name="form" property="onoma" value="${form.onoma}" size="40"/></td>
	  		</tr>
	    	<tr>
	    		<td>Πατρώνυμο:</td><td><html:text name="form" property="patrwnymo" value="${form.patrwnymo}" size="40"/></td>
	    		<td>Φύλο:</td><td><html:select property="sex"  value="${form.sex}">
	  	 		<html:optionsCollection name="form" property="nsex" label="value" value="key"/>
	  	 		</html:select></td>
	  	 	</tr>
			<tr>
				<td>ΑΦΜ:</td><td><html:text name="form" property="afm" value="${form.afm}" size="40"/></td>
				<td>Αρ. ταυτότητας:</td><td><html:text name="form" property="ar_taytotitas" value="${form.ar_taytotitas}" size="30"/></td>
			</tr>
			<tr>
				<td>Email:</td><td><html:text name="form" property="email" value="${form.email}" size="40"/></td>
				<td>Τηλέφωνο:</td><td><html:text name="form" property="tilefono" value="${form.tilefono}" size="30"/></td>
			</tr>
			<tr>
				<td>Κατηγορία:</td><td><html:select property="id_ekpaideyseis" value="${form.id_ekpaideyseis}" >
	  	 		<html:option value="0">Επιλέξτε Κατηγορία</html:option>
	  	 		<html:optionsCollection name="form" property="nid_katigoria" label="value" value="key"/></html:select></td>
				<td>Ειδικότητα:</td><td><html:select property="id_eidikotita" value="${form.id_eidikotita}" >
	  	 		<html:option value="0">Επιλέξτε Ειδικότητα</html:option>
	  	 		<html:optionsCollection name="form" property="nid_eidikotita" label="value" value="key"/></html:select>
	  	 	</tr>	
	  	 	<tr>
	  	 		<td>Διδακτορικό:</td><td><html:select property="phd" value="${form.phd}" >
	  	 		<html:optionsCollection name="form" property="pol" label="value" value="key"/></html:select></td>
	  	 		 <td>Μεταπτυχιακό:</td><td><html:select property="msc" value="${form.msc}" > 
	  	 		<html:optionsCollection name="form" property="pol" label="value" value="key"/></html:select></td> 
	  	 	</tr>	
	  	 	<tr>
	  	 		<td>Τρόπος πρόσληψης:</td><td><html:select property="id_proslipsis" value="${form.id_proslipsis}" >
	  	 		<html:option value="0">Επιλέξτε τρόπο πρόσληψης</html:option>
	  	 		<html:optionsCollection name="form" property="nid_proslipsis" label="value" value="key"/></html:select></td>
	  	 		<td>Λογαριασμός:</td><td><html:text name="form" property="logariasmos" value="" size="30"/></td>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>Πόλη (διαμονή):</td><td><html:text name="form" property="poli" value="${form.poli}" size="30"/></td>
	  	 		<td>Φορέας:</td><td><html:select property="id_forea" value="${form.id_forea}" >
	  	 		<html:option value="0">Επιλέξτε Φορέα</html:option>
	  	 		<html:optionsCollection name="form" property="foreis" label="value" value="key"/></html:select></td>
	  	 	</tr>
	  	 	<tr>
	  	 		<td>Διεύθυνση:</td><td><html:text name="form" property="address" value="${form.address}" size="40"/></td>
	  	 		<td>Ταχ. Κώδικας:</td><td><html:text name="form" property="zip" value="${form.zip}" size="10"/></td>    
	  		</tr>
	  		<tr>
	  			<td></td><td><input type="submit" value="Ενημέρωση"/></td><td><input type="reset" value="Αναίρεση" /></td><td></td>
	  		</tr>
    	</table>
    	</div> 
	</form> 
	</div>	 
</logic:greaterThan> 


	
</div>