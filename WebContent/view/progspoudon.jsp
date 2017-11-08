<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="forms.spoudesForm" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %> 
<bean:define id="form" name="form" /> 
<bean:define name="form" id="mathimata" property="mathimata" type="java.util.Map"/>
<bean:define name="form" id="n_typos" property="n_typos" type="java.util.Map"/>
<bean:size id="s" name="form" property="maths" /> 
<div align=center>
	<h3 style="text-align:center">Εβδομαδιαίο Πρόγραμμα Σπουδών της <bean:write name="form" property="taxi"/><br>
		
	 
	<logic:greaterThan name="s" value="0"> 
	
	
	
	<TABLE id="tef" align="center" >
        <th>Μάθημα</th><th>Θεωρία<br>Εργαστήριο</th><th>Ώρες</th><th></th> 
        <logic:iterate name="form" property="maths" id="listoro" indexId="current" type="forms.spoudesForm.mathProg">
            	<tr>
            		<td>
            	       	<%=mathimata.get(Integer.toString(listoro.getId_mathima()))%>
                	</td>
            	    <td>
            	    	<%=n_typos.get(listoro.getType()) %>
            	    </td>
            	    <td> 
            	    	<bean:write name="listoro" property="ores"/>
            	    </td>
            	</tr>		
    	</logic:iterate>
	</TABLE>
	</logic:greaterThan>
	
	<div name="d_prog" style="border:1px solid black" id="div_prog" align=center>
		<form action="<%=request.getContextPath()%>/prog_spoudon.do?action=add" method="post">
		<h3>Εισαγωγή μαθήματος στο Πρόγραμμα</h3>
		<table>
			<tr>
				<td><html:select name="form" property="id_mathima" styleId="id_mathima" value="0" >
	  	 			<html:option value="0">Επιλέξτε μάθημα</html:option>
	  	 			<html:optionsCollection name="form" property="mathimata" label="value" value="key"/>
	  	 			</html:select>
	  	 		</td>
	  	 		<td>    
	  	 		    <html:select name="form" property="type" styleId="type" value="0" >
	  	 			<html:option value="0">Επιλέξτε θεωρ. ή εργαστ.</html:option>
	  	 			<html:optionsCollection name="form" property="n_typos" label="value" value="key"/>
	  	 			</html:select>
	  	 		</td>
	  	 		<td>
	  	 			ώρ./εβδομάδα:<html:text name="form" property="ores"></html:text>
	  	 		</td>
	  	 		<td></td>
			</tr>
			<tr>
				<td></td><td><input type="submit" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_prog')" value="Κλείσιμο φόρμας"/></td>
			</tr>
		</table>
		</form>
	</div>
   <a href='#d_thema' title='Νέα Θέμα' onclick="child_open(this)" id="thema">Νέο Μάθημα</a>
	<div name="d_thema" style="display:none;border:1px solid black" id="div_thema" align=center>
		<h3>Εισαγωγή Μαθήματος</h3>
		<table>
			<tr>
				<td>Μάθημα:</td><td><input type=text name="in_mathima" id="in_mathima" size=100></td>
			</tr>
			<tr>
				<td><input type=button name="mathima"  onclick="sub_mathima()" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_thema')" value="Κλείσιμο φόρμας"/></td>
			</tr>
		</table>
	</div>
	<script>
		
		function child_open(c)
     	{ 
        	 if(c.id==='prog')
     		{
     			document.getElementById("div_prog").style.display="block";
     		
     		}else if(c.id==='thema')
     		{
     			document.getElementById("div_thema").style.display="block";
     	    }
     	}
	
		function nosub(c)
        {
        	document.getElementById(c).style.display="none";
        }
		
		function sub_mathima()//ajax για μάθημα
    	{
       		if(document.getElementById("in_mathima").value=="")
       		{
       			alert("Δεν έχετε ορίσει κάποιο μάθημα");
       			return;
       		}
       		document.getElementById("div_thema").style.display="none";
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
            		var token=s.split(",");  
            		var option=document.createElement("option");
                	option.text=token[1];  
                    option.value=token[0];
                    var sel=document.getElementById("id_mathima");
                    sel.appendChild(option); 
            	    
            	}
        	};
        	var akrwnymio=encodeURIComponent(document.getElementById("in_mathima").value);
        	var onoma="maths";
        	var parameters="akrwnymio="+akrwnymio;
        	parameters+="&onoma="+onoma;
        	//alert(parameters);
        	xmlhttp.open("POST","<%=request.getContextPath()%>/enimerosis.do",true);
    		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    		var responseTypeAware = 'responseType' in xmlhttp;
    		try{
    			if(responseTypeAware){
    				xmlhttp.responseType = "text";
    			}	
    		}catch(e){}
    		xmlhttp.send(parameters);
         };
		
		
             
         
	
	</script>
	
	
	

</div>
