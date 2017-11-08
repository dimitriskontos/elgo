<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.List" %>
<%@ page import="forms.mathitesForm" %>
<bean:define id="form" name="form" type="forms.mathitesForm"/>

<div align=center>
<script type="text/javascript">
	
	
	
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
		xmlhttp.open("POST","<%=request.getContextPath()%>/mathites.do",true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
		var responseTypeAware = 'responseType' in xmlhttp;
		try{ 
			if(responseTypeAware){
				xmlhttp.responseType = "text";
			}		
		}catch(e){}
		xmlhttp.send(parameters);
	};
	
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
            		location.href = "<%=request.getContextPath()%>/mathites.do?action=keni";
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
		
		xmlhttp.open("POST","<%=request.getContextPath()%>/mathites.do",true);
    	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	var responseTypeAware = 'responseType' in xmlhttp;
    	try{
    		if(responseTypeAware){
    			xmlhttp.responseType = "text";
    		}	
    	}catch(e){}
    	xmlhttp.send(parameters);
	}	
	
	function child_open(c)
	{ 
		if(c.id==='epag')
		{
			document.getElementById("div_epag").style.display="block";
		
		}else if(c.id==='ypik')
		{
			document.getElementById("div_ypik").style.display="block";
		}else if(c.id==='thris')
		{
			document.getElementById("div_thris").style.display="block";
		}
	}
	
	//submit
	function subm(c)
	{
		if(c.id==='b_thriskeyma')
			document.getElementById("div_thris").style.display="none";
		else if(c.id==='b_ypikootita')
			document.getElementById("div_ypik").style.display="none";
		else if(c.id==='b_epaggelma')
			document.getElementById("div_epag").style.display="none";
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
        		var s=decodeURIComponent(xmlhttp.responseText);
        		var token=s.toString().split(",");
        		var option=document.createElement("option");
            	option.text=token[1];
            	option.value=token[0];
            	var sel=document.getElementById("id_thriskeyma");
            	if(c.id==='b_epaggelma'){
        			sel=document.getElementById("id_epaggelma_patera");
        		}
        		else if(c.id==='b_ypikootita'){
        			sel=document.getElementById("id_ypikootita");
        		}
        		sel.appendChild(option); 
        	}
        
    	};
    	
    	var akrwnymio="";
    	var onoma="thris";
    	if(c.id==='b_thriskeyma'){
    		akrwnymio=encodeURIComponent(document.getElementById("in_thriskeyma").value);
    	}
		else if(c.id==='b_epaggelma'){
			akrwnymio=encodeURIComponent(document.getElementById("in_epaggelma").value);
			onoma="epag";
		}
		else if(c.id==='b_ypikootita'){
			akrwnymio=encodeURIComponent(document.getElementById("in_ypikoootita").value);
			onoma="ypik";
		}
    	parameters="akrwnymio="+akrwnymio;
    	parameters+="&onoma="+onoma;
    	xmlhttp.open("POST","<%=request.getContextPath()%>/enimerosis.do",true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var responseTypeAware = 'responseType' in xmlhttp;
		try{
			if(responseTypeAware){
				xmlhttp.responseType = "text";
			}	
		}catch(e){}
		xmlhttp.send(parameters);
	}
	
	
	
	
	function nosub(c)
	{
		document.getElementById(c).style.display="none";
	}
		
	
	
	
</script>


<logic:equal name="form" property="id_mathiti" value="0">
<div align="center">
	<h2 align="center">Καρτέλα εγγραφής μαθητή </h2> 
	<p align=center>
	<form action="<%=request.getContextPath()%>/mathites.do?action=add" enctype="multipart/form-data;charset=UTF-8" method="post">
	  <div style="border:1px solid #000000;">
	  <h3>Στοιχεία Μαθητή</h3>
	  <link type="text/css" href="<%=request.getContextPath()%>/css/jquery.simple-dtpicker.css" rel="stylesheet" />     
	  <table>
	  	<tr><td>Όνομα:</td><td><html:text name="form" property="onoma" size="40" /></td><html:errors property="onoma" /><td>Επώνυμο:</td><td><html:text name="form" property="epwnymo" onkeyup='chk()' styleId="epwnymo" size="40" /></td><html:errors property="epwnymo" /></tr>
	    <tr><td>Όνομα Πατέρα:</td><td><html:text name="form" property="onoma_patera" size="40"/></td><td>Επώνυμο Πατέρα:</td><td><html:text name="form" property="epwnymo_patera" size="40"/></td></tr>
	    <tr><td>Όνομα Μητέρας:</td><td><html:text name="form" property="onoma_miteras" size="40"/></td><td>Επώνυμο Μητέρας:</td><td><html:text name="form" property="epwnymo_miteras" size="40"/></td></tr>
	    <tr><td>Τόπος Γεννησης:</td><td><html:text name="form" property="topos_gennisis" size="50"/></td><td>Ημερομη. Γέννησης:</td><td><html:text name="form" property="im_gennisi" size="10"/></td></tr>
	  	<tr><td>Γένος:</td><td><html:text name="form" property="genos" size="40"/></td><td>Δήμος ή κοινότητα.:</td><td><html:text name="form" property="dimos" size="10"/></td></tr>
	  	<tr><td>Θρήσκευμα:</td><td><html:select property="id_thriskeyma" styleId="id_thriskeyma" value="0" >
	  	 	<html:option value="0">Επιλέξτε Θρήσκευμα</html:option>
	  	 	<html:optionsCollection name="form" property="nid_thriskeyma" label="value" value="key"/>
	  	 	</html:select></td><td>Υπηκοότητα:</td><td><html:select property="id_ypikootita" styleId="id_ypikootita" value="0" >
	  	 	<html:option value="0">Επιλέξτε Υπηκόοτητα</html:option>
	  	 	<html:optionsCollection name="form" property="nid_ypikootita" label="value" value="key"/>
	  	    </html:select> </td>
	  	 </tr>
	  	 <tr><td>Επάγγελμα Πατέρα:</td><td><html:select property="id_epaggelma_patera" styleId="id_epaggelma_patera" value="0">
	  	 	<html:option value="0">Επιλέξτε Επάγγελμα</html:option>
	  	 	<html:optionsCollection name="form" property="nid_epaggelma_patera" label="value" value="key"/>
	  	 	</html:select></td>
	  	 	<td>Νομός:</td><td><html:select property="id_nomos" value="0">
	  	 	<html:option value="0">Επιλέξτε Νομό</html:option>
	  	 	<html:optionsCollection name="form" property="nid_nomos" label="value" value="key"/>
	  	 	</html:select></td>
	  	 </tr>
	  	 <tr>
	  	 	<td>Φύλο:</td><td><html:select property="sex"  value="${form.sex}">
	  	 	<html:optionsCollection name="form" property="nsex" label="value" value="key"/>
	  	 	</html:select></td>
	  	 	<td>Πόλη διαμονής:</td>
	  	 	<td> <html:text name="form" property="id_poli_diamoni" size="40"/></td>	 
	  	 </tr>
	  	 <tr>
	  	 	<td>Όνομα κηδεμόνα:</td><td><html:text name="form" property="onoma_kidemona" size="40"/></td><td>Επώνυμο κηδεμόνα:</td><td><html:text name="form" property="epwnymo_kidemona" size="40"/></td>
	  	 </tr>
	  	 <tr>
	  	 	<td>Διεύθυνση:</td><td><html:text name="form" property="address" size="40"/></td><td>Ταχυδρ. κώδικας:</td><td><html:text name="form" property="zip" size="10"/></td>
	  	 </tr>
	  	 <tr>
	  	 	<td>Τηλέφωνο:</td><td><html:text name="form" property="tilefono" size="20"/></td><td>Κινητό:</td><td><html:text name="form" property="mobile" size="20"/></td>
	  	 </tr>
	  	 <tr>
	  	  <td></td><td><input type="submit" value="Αποθήκευση"/></td><td><input type="reset" value="Αναίρεση" /></td><td></td>
	  	 </tr>
	  </table>
	  <div name="suggestions" id="suggestions" ALIGN="CENTER" ></div><br>
	  </div>
	  </form>
	    
	  
	
		
</div>
</logic:equal>	




<logic:greaterThan name="form" property="id_mathiti" value="0">
<div align="center">
	<h2 align="center">Καρτέλα ενημέρωσης μαθητή </h2> 
	<p align=center>
	<form action="<%=request.getContextPath()%>/mathites.do?action=save" enctype="multipart/form-data;charset=UTF-8" method="post">
	  <div style="border:1px solid #000000;">
	  <h3>Στοιχεία Μαθητή</h3>
	  <link type="text/css" href="<%=request.getContextPath()%>/css/jquery.simple-dtpicker.css" rel="stylesheet" />     
	  <table>
	  	<tr><td>Όνομα:</td><td><html:text name="form" property="onoma" size="40"  value="${form.onoma}"/></td><html:errors property="onoma" /><td>Επώνυμο:</td><td><html:text name="form" property="epwnymo" onkeyup='chk()' styleId="epwnymo" size="40" value="${form.epwnymo}"/></td><html:errors property="epwnymo" /></tr>
	    <tr><td>Όνομα Πατέρα:</td><td><html:text name="form" property="onoma_patera" size="40" value="${form.onoma_patera}"/></td><td>Επώνυμο Πατέρα:</td><td><html:text name="form" property="epwnymo_patera" size="40" value="${form.epwnymo_patera}"/></td></tr>
	    <tr><td>Όνομα Μητέρας:</td><td><html:text name="form" property="onoma_miteras" value="${form.onoma_miteras}" size="40"/></td><td>Επώνυμο Μητέρας:</td><td><html:text name="form" property="epwnymo_miteras" size="40" value="${form.epwnymo_miteras}"/></td></tr>
	    <tr><td>Τόπος Γεννησης:</td><td><html:text name="form" property="topos_gennisis" value="${form.topos_gennisis}" size="50"/></td><td>Ημερομη. Γέννησης:</td><td><html:text name="form" property="im_gennisi" value="${form.im_gennisi}" size="10"/></td></tr>
	  	<tr><td>Γένος:</td><td><html:text name="form" property="genos" value="${form.genos}" size="40"/></td><td>Δήμος ή κοινότητα.:</td><td><html:text name="form" property="dimos" value="${form.dimos}" size="10"/></td></tr>
	  	<tr><td>Θρήσκευμα:</td><td><html:select property="id_thriskeyma" styleId="id_thriskeyma" value="${form.id_thriskeyma}" >
	  	 	<html:option value="0">Επιλέξτε Θρήσκευμα</html:option>
	  	 	<html:optionsCollection name="form" property="nid_thriskeyma" label="value" value="key"/>
	  	 	</html:select></td><td>Υπηκοότητα:</td><td><html:select property="id_ypikootita" styleId="id_ypikootita" value="${form.id_ypikootita}" >
	  	 	<html:option value="0">Επιλέξτε Υπηκόοτητα</html:option>
	  	 	<html:optionsCollection name="form" property="nid_ypikootita" label="value" value="key"/>
	  	    </html:select> </td>
	  	 </tr>
	  	 <tr><td>Επάγγελμα Πατέρα:</td><td><html:select property="id_epaggelma_patera" styleId="id_epaggelma_patera" value="${form.id_epaggelma_patera}">
	  	 	<html:option value="0">Επιλέξτε Επάγγελμα</html:option>
	  	 	<html:optionsCollection name="form" property="nid_epaggelma_patera" label="value" value="key"/>
	  	 	</html:select></td>
	  	 	<td>Νομός:</td><td><html:select name="form" property="id_nomos" value="<%=Integer.toString(form.getId_nomos())%>">
	  	 	<html:option value="0">Επιλέξτε Νομό</html:option>
	  	 	<html:optionsCollection name="form" property="nid_nomos" label="value" value="key" />
	  	 	</html:select></td>
	  	 </tr>
	  	 <tr>
	  	 	<td>Φύλο:</td><td><html:select property="sex" value="<%=Integer.toString(form.getSex())%>"> 
	  	 	<html:optionsCollection name="form" property="nsex" label="value" value="key"/>
	  	 	</html:select></td>
	  	 	<td>Πόλη διαμονής:</td>
	  	 	<td>	 <html:text name="form" property="id_poli_diamoni" value="${form.id_poli_diamoni}" size="40"/></td>	
	  	 </tr>
	  	 <tr>
	  	 	<td>Όνομα κηδεμόνα:</td><td><html:text name="form" property="onoma_kidemona" value="${form.onoma_kidemona}" size="40"/></td><td>Επώνυμο κηδεμόνα:</td><td><html:text name="form" property="epwnymo_kidemona" value="${form.epwnymo_kidemona}" size="40"/></td>
	  	 </tr>
	  	 <tr>
	  	 	<td>Διεύθυνση:</td><td><html:text name="form" property="address" value="${form.address}" size="40"/></td><td>Ταχυδρ. κώδικας:</td><td><html:text name="form" property="zip" value="${form.zip}"  size="10"/></td>
	  	 </tr>
	  	 <tr>
	  	 	<td>Τηλέφωνο:</td><td><html:text name="form" property="tilefono" value="${form.tilefono}" size="20"/></td><td>Κινητό:</td><td><html:text name="form" property="mobile" value="${form.mobile}" size="20"/></td>
	  	 </tr>
	  	 <tr>
	  	  <td></td><td><input type="submit" value="Ενημέρωση"/></td><td><input type="reset" value="Αναίρεση" /></td><td></td>
	  	 </tr>
	  </table>
	  <div name="suggestions" id="suggestions" ALIGN="CENTER" ></div><br>
	  </div>
	  </form>
</div>
</logic:greaterThan>

<div align="center">
  <br>
	  <a href='#' title='Θρησκε΄υματα' onclick="child_open(this)" id="thris">Άλλα Θρησκεύματα</a>
	  <div name="d_thris" style="display:none;border:1px solid black" id="div_thris" align=center>
		<h3>Εισαγωγή Θρησκεύματος</h3>
		<table>
			<tr>
				<td>Θρήσκευμα:</td><td><input type=text name="" id="in_thriskeyma" size=100></td>
			</tr>
			<tr>
				<td><input type=button name="thriskeyma" id="b_thriskeyma" onclick="subm(this)" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_thris')" value="Κλείσιμο φόρμας"/></td>
			</tr>
		</table>
	  </div>
	  <br>		
	  <a id="ypik" href='#' onclick="child_open(this)" title='Υπηκόοτητες'>Άλλη Υπηκόοτητα</a>
	  <div name="d_ypik" style="display:none;border:1px solid black" id="div_ypik" align=center>
		<h3>Εισαγωγή Υπηκοότητας</h3>
		<table>
			<tr>
				<td>Υπηκοότητα:</td><td><input type=text name="" id="in_ypikoootita" size=100></td>
			</tr>
			<tr>
				<td><input type=button name="ypikootita" id="b_ypikootita" onclick="subm(this)" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_ypik')" value="Κλείσιμο φόρμας"/></td>
			</tr>
		</table>
	  </div>
	  <br>				
	  <a id="epag" href='#' onclick="child_open(this)" title='Επαγγέλματα'>Άλλα Επαγγέλματα</a>
	  <div name="d_epag" style="display:none;border:1px solid black" id="div_epag" align=center>
		<h3>Εισαγωγή Επαγγέλματος</h3>
		<table>
			<tr>
				<td>Επάγγελμα:</td><td><input type=text name="" id="in_epaggelma" size=100></td>
			</tr>
			<tr>
				<td><input type=button name="epaggelma" id="b_epaggelma" onclick="subm(this)" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima" onclick="nosub('div_epag')" value="Κλείσιμο φόρμας"/></td>
			</tr>
		</table>
	  </div>
</div>