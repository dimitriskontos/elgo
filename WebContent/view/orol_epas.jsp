<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="forms.ep_orologioForm" %>
<%@ page import="forms.ep_orologioForm.point" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %> 
<bean:define id="form" name="form" /> 
<bean:size id="s" name="form" property="mathimata" />
<bean:size id="ss" name="form" property="programma" />   
<bean:define name="form" id="mat" property="mathimata" type="java.util.Map"/>
<bean:define name="form" id="kath" property="kathigites" type="java.util.Map"/>
   <div align="center">
     <logic:greaterThan name="ss" value="0"> 
     	<h3 align="center">Ωρολόγιο τμήματος</h3>
     		<table id="tef" >
     			<tr><td></td> 
     				<logic:iterate id="imer" name="form" property="imeres">
     					<td><bean:write name="imer" property="value"/></td>
     				</logic:iterate>
     			</tr>
     				<logic:iterate id="ores" name="form" property="ores">
     					<tr><td><bean:write name="ores" property="value"/><bean:define id="or" name="ores" property="key" type="java.lang.String" /></td>
     						<td> 
     							<logic:iterate name="form" id="lst" property="programma" type="forms.ep_orologioForm.point">
     								<logic:match name="lst" property="imera" value="Monday">
     									<logic:equal name="lst" property="ora" value="<%=or%>" >
     										 <% 
     											String val=null;
     										 	String kat=null;
     										 	try{
     										 		val=(String)mat.get(lst.getId_epas_sxol_etos_eidik_tax_mathimata());
     										 		kat=(String)kath.get(Integer.toString(lst.getId_kathigites_epas_sxol_etos()));
     												if(val.endsWith("ergastirio"))
     										 			val=val.substring(0, val.length()-10)+"(Ε)";	
     										  		else 
     										 			val=val.substring(0, val.length()-7)+ "(Θ)";
     											}catch(Exception e){val="lat";}
     										 %>
     										<%=val%><br><%=kat%>
     									</logic:equal>	
     								</logic:match>
     							</logic:iterate> 
     						
     						</td>
     						<td>
     							<logic:iterate name="form" id="lst" property="programma" type="forms.ep_orologioForm.point">
     								<logic:match name="lst" property="imera" value="Tuesday">
     									<logic:equal name="lst" property="ora" value="<%=or%>" >
     										 <% 
     											String val=null;
     										 	String kat=null;
     										 	try{
     										 		val=(String)mat.get(lst.getId_epas_sxol_etos_eidik_tax_mathimata());
     										 		kat=(String)kath.get(Integer.toString(lst.getId_kathigites_epas_sxol_etos()));
     												if(val.endsWith("ergastirio"))
     										 			val=val.substring(0, val.length()-10)+"(Ε)";	
     										  		else 
     										 			val=val.substring(0, val.length()-7)+ "(Θ)";
     											}catch(Exception e){val="lat";}
     										 %>
     										<%=val%><br><%=kat%>
     									</logic:equal>	
     								</logic:match>
     							</logic:iterate> 
     						</td>
     						<td>
     							<logic:iterate name="form" id="lst" property="programma" type="forms.ep_orologioForm.point">
     								<logic:match name="lst" property="imera" value="Wednesday">
     									<logic:equal name="lst" property="ora" value="<%=or%>" >
     										 <% 
     											String val=null;
     										 	String kat=null;
     										 	try{
     										 		val=(String)mat.get(lst.getId_epas_sxol_etos_eidik_tax_mathimata());
     										 		kat=(String)kath.get(Integer.toString(lst.getId_kathigites_epas_sxol_etos()));
     												if(val.endsWith("ergastirio"))
     										 			val=val.substring(0, val.length()-10)+"(Ε)";	
     										  		else 
     										 			val=val.substring(0, val.length()-7)+ "(Θ)";
     											}catch(Exception e){val="lat";}
     										 %>
     										<%=val%><br><%=kat%>
     									</logic:equal>	
     								</logic:match>
     							</logic:iterate> 
     						</td>
     						<td>
     							<logic:iterate name="form" id="lst" property="programma" type="forms.ep_orologioForm.point">
     								<logic:match name="lst" property="imera" value="Thursday">
     									<logic:equal name="lst" property="ora" value="<%=or%>" >
     										 <% 
     											String val=null;
     										 	String kat=null;
     										 	try{
     										 		val=(String)mat.get(lst.getId_epas_sxol_etos_eidik_tax_mathimata());
     										 		kat=(String)kath.get(Integer.toString(lst.getId_kathigites_epas_sxol_etos()));
     												if(val.endsWith("ergastirio"))
     										 			val=val.substring(0, val.length()-10)+"(Ε)";	
     										  		else 
     										 			val=val.substring(0, val.length()-7)+ "(Θ)";
     											}catch(Exception e){val="lat";}
     										 %>
     										<%=val%><br><%=kat%>
     									</logic:equal>	
     								</logic:match>
     							</logic:iterate> 	
     						</td>
     						<td>
     							<logic:iterate name="form" id="lst" property="programma" type="forms.ep_orologioForm.point">
     								<logic:match name="lst" property="imera" value="Friday">
     									<logic:equal name="lst" property="ora" value="<%=or%>" >
     										<% 
     											String val=null;
     										 	String kat=null;
     										 	try{
     										 		val=(String)mat.get(lst.getId_epas_sxol_etos_eidik_tax_mathimata());
     										 		kat=(String)kath.get(Integer.toString(lst.getId_kathigites_epas_sxol_etos()));
     												if(val.endsWith("ergastirio"))
     										 			val=val.substring(0, val.length()-10)+"(Ε)";	
     										  		else 
     										 			val=val.substring(0, val.length()-7)+ "(Θ)";
     											}catch(Exception e){val="lat";}
     										 %>
     										<%=val%><br><%=kat%>
     									</logic:equal>	
     								</logic:match>
     							</logic:iterate> 	
     						</td>
     						
     					</tr>
     				</logic:iterate> 		
     		</table>
     </logic:greaterThan>
     
     
     
     
       
     
     
     <a id="math" href='#' onclick="child_open(this)" title='Μαθήματα'>Εισαγωγή μαθήματος στο Ωρολόγιο</a>
	  	<form action="<%=request.getContextPath()%>/ep_orologio.do?action=add_math" method="post">
	  	 <div style="display:none;border:1px solid black" id="div_math" align=center>
			<table>
			  <tr>
				<td>
					<html:select name="form" property="imera" styleId="imera" value="0" >
	  	 			<html:option value="0">Επιλέξτε ημέρα</html:option>
	  	 			<html:optionsCollection name="form" property="imeres" label="value" value="key"/>
	  	 			</html:select>
	  	 		</td>
	  	 		<td>
					<html:select name="form" property="ora" styleId="ora" value="0" >
	  	 			<html:option value="0">Επιλέξτε ώρα</html:option>
	  	 			<html:optionsCollection name="form" property="ores" label="value" value="key"/>
	  	 			</html:select>
	  	 		</td>
	  	 		<td>
					<html:select name="form" property="id_mathima" styleId="id_mathima" value="0" >
	  	 			<html:option value="0">Επιλέξτε μάθημα</html:option>
	  	 			<html:optionsCollection name="form" property="mathimata" label="value" value="key"/>
	  	 			</html:select>
	  	 		</td>
	  	 		<td>
					<html:select name="form" property="id_kathigitis" styleId="id_kathigitis" value="0" >
	  	 			<html:option value="0">Επιλέξτε Καθηγητή</html:option>
	  	 			<html:optionsCollection name="form" property="kathigites" label="value" value="key"/>
	  	 			</html:select>
	  	 		</td>
	  	 	  </tr>
			 <tr>
				<td></td><td><input type="submit" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_math')" value="Κλείσιμο φόρμας"/></td><td></td>
			 </tr>
		   </table>
	     </div>
	    </form>
	    <script>
   			function child_open(c)
			{ 
				if(c.id==='math')
				{
					document.getElementById("div_math").style.display="block";
				}
			}		
   			function nosub(c)
   			{
   				document.getElementById(c).style.display="none";
   				//location.href = "<%=request.getContextPath()%>/kath_math.do?action=anath_math";
   			}
    	</script>
	    
	    
	    
    </div>
 











