<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="forms.ep_parusiologioForm" %>
<%@ page import="forms.ep_parusiologioForm.orolo" %>
<%@ page import="java.util.*" %>
<bean:define id="form" name="form" />
<bean:define name="form" id="ores" property="ores" type="java.util.Map"/>
<bean:define name="form" id="mathites" property="mathites" type="java.util.Map"/>
<bean:define name="form" id="kathigites" property="kathigites" type="java.util.Map"/>
<bean:define name="form" id="mathimata" property="mathimata" type="java.util.Map"/>

<bean:size id="s" name="form" property="lst" /> 
<div align="center">
		<a id="paru" href='#' onclick="child_open(this)" title='Παρουσίες'>Δώσε Ημερομηνία</a>
	  	<form action="<%=request.getContextPath()%>/ep_parusiologio.do?action=query" method="post">
	  	 <link type="text/css" href="<%=request.getContextPath()%>/css/jquery.simple-dtpicker.css" rel="stylesheet" /> 
	  	 
	  	 <div style="display:none;border:1px solid black" id="div_paru" align=center>
			
			<table>
				<th>Ημερομηνία</th><th>Εφαρμογή</th>
			  <tr>
				<td>
					<html:text property="imerominia" name="form" value="${form.imerominia}"/>
       				<script type="text/javascript">
						$(function(){
							$('*[name=imerominia]').appendDtpicker({
								"onHide":  function(handler){
								handler.show();
							}, 
							"dateOnly": true,
							"autodateOnStart": false,
							"dateFormat": "DD/MM/YYYY ",
							"locale": "gr",
							"closeOnSelected": true
							});
			    		});
					</script> 
	  	 		</td>
	  	 		<td>
				   	Ωρολογίου<input type="radio" name="is_orol" value="orol" checked/><br>Εκδρομή:<input type="radio" name="is_orol" value="ekdromi"/>
	  	 	   </td>
	  	 	  </tr>
			 <tr>
				<td><input type="submit" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_paru')" value="Κλείσιμο φόρμας"/></td>
			 </tr>
		   </table>
	     </div>
	     	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.simple-dtpicker.js"></script>
	    </form>
	    <script>
   			function child_open(c)
			{ 
				if(c.id==='paru')
				{
					document.getElementById("div_paru").style.display="block";
				}
			}		
   			function nosub(c)
   			{
   				document.getElementById(c).style.display="none";
   				//location.href = "<%=request.getContextPath()%>/kath_math.do?action=anath_math";
   			}
    	</script>
	
		
	
	
		<logic:greaterThan name="s" value="0"> 
		<h3>Παρουσιολόγιο για την <bean:write name="form" property="imerominia"/></h3>
		<logic:iterate name="form" id="lst1" property="lst" type="forms.ep_parusiologioForm.orolo">
			<logic:equal name="lst1" property="isEdit" value="false">
				<form action="<%=request.getContextPath()%>/ep_parusiologio.do?action=add" enctype="multipart/form-data;charset=UTF-8" method="post" name="<%=lst1.getOra()%>" id="<%=lst1.getOra()%>">
					<input type="hidden" name="id_orol_tmima" value="<%=lst1.getId_orol_tmima()%>"/>
					<input type="hidden" name="id_parusiol_kathig" value="<%=lst1.getId_parusiol_kathig()%>"/>
				</form>
			</logic:equal>
			<logic:equal name="lst1" property="isEdit" value="true">
				<form action="<%=request.getContextPath()%>/ep_parusiologio.do?action=edit" enctype="multipart/form-data;charset=UTF-8" method="post" name="<%=lst1.getOra()%>" id="<%=lst1.getOra()%>">
					<input type="hidden" name="id_orol_tmima" value="<%=lst1.getId_orol_tmima()%>"/>
					<input type="hidden" name="id_parusiol_kathig" value="<%=lst1.getId_parusiol_kathig()%>"/>
				</form>
			</logic:equal>	 	     		  
		</logic:iterate>
		
		
		<table  border=1 frame=void rules=rows>
		<thead><th>Ώρα</th><th>Μάθημα</th><th>Καθηγητής</th><th>Παρουσίες</th><th>Αποθήκευση</th></thead>
			<logic:iterate name="form" id="lst" property="lst" type="forms.ep_parusiologioForm.orolo" indexId="curr">
				
				
     		  
     			 <tr>
     			
     			 	<td><% String ora=null;
     					try{
     						ora=(String)ores.get(lst.getOra());
     					}catch(Exception e){ora="";}%>
     				 <%=ora%> 
     				 </td>
     				 
     				 <td><% String mathima=null;
     				 		try{
     				 			mathima=(String)mathimata.get(Integer.toString(lst.getId_epas_sxol_etos_eikik_tax_mathimata()));
     				 			if(mathima.endsWith("ergastirio"))
							 		mathima=mathima.substring(0, mathima.length()-10)+"(Ε)";	
							  	else 
							 		mathima=mathima.substring(0, mathima.length()-7)+ "(Θ)";
     				 		}catch(Exception e){} %>
     				 		<%=mathima%>					
     				 </td>
     				
     				<td>
     					<select form="<%=lst.getOra()%>" name="id_kathigites_epas_sxol_etos"  value="<%=Integer.toString(lst.getId_kathigites_epas_sxol_etos())%>" >
     						<logic:iterate id="kath" name="form" property="kathigites">
     							<bean:define id="timi" name="kath" property="key"/>
     							<option value="<bean:write name="kath" property="key"/>" <%=(Integer.toString(lst.getId_kathigites_epas_sxol_etos()).equals(timi)?"selected":"")%>> <bean:write name="kath" property="value"/></option>	
     						</logic:iterate> 
     					</select>
     					
     				</td>
	  	 			<td>
	  	 				<logic:iterate id="imer" name="form" property="mathites" indexId="current">
	  	 				  <table>
	  	 					<tr>
	  	 					<td>
	  	 						<bean:write name="imer" property="value" />
	  	 					</td>	
	  	 					<td>
	  	 						 <logic:equal name="lst" property="isEdit" value="false">
	  	 					        <input form="<%=lst.getOra()%>" type="checkbox" name="parusies" value="<bean:write name="imer" property="key"/>" checked/> 
	  	 					    </logic:equal>
	  	 					    <logic:equal name="lst" property="isEdit" value="true">
	  	 					    	<bean:define id="absent" name="imer" property="key"/>
	  	 					    	 <% String par="1";
	  	 					    	    try{
	  	 					    	 	for (Map.Entry<String, String> entry : lst.getApontes().entrySet())
	  	 					    		{
	  	 					    	    	if(entry.getKey().equals(absent))
	  	 					    	    	{
	  	 					    	    		par="0";
	  	 					    	    		break;
	  	 					    	    	}
	  	 					    	    }  
	  	 					    	    }catch(Exception e){}
	  	 					    	%>
	  	 					    	
	  	 					    	<input form="<%=lst.getOra()%>" type="checkbox" name="parusies" value="<bean:write name="imer" property="key"/>"  <%=(par.equals("1")?"checked":"")%> /> 
	  	 					    </logic:equal>
	  	 					
	  	 					</td>
	  	 					</tr>
	  	 				</table>
	  	 				</logic:iterate>
	  	 			</td> 
	  	 			<td>
	  	 				<logic:equal name="lst" property="isEdit" value="true">
	  	 					<input type="submit" value="Αλλαγή" form="<%=lst.getOra()%>"/>
	  	 				</logic:equal>
	  	 				<logic:equal name="lst" property="isEdit" value="false">
	  	 					<input type="submit" value="1η Αποθήκευση" form="<%=lst.getOra()%>"/>
	  	 				</logic:equal>
	  	 			</td>
	  	 			
     			</tr>
     		  	
     		  	
			</logic:iterate> 
		 </table>
		 
		</logic:greaterThan>
		<logic:notEmpty name="form" property="imerominia">
			<logic:equal name="s" value="0">
				<h3>Πρέπει να συμπληρώσετε το ωρολόγιο </h3>
			</logic:equal>
		</logic:notEmpty>
	
</div>