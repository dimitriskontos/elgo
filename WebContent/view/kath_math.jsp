<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="forms.spoudesForm" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %> 
<bean:define id="form" name="form" /> 
<bean:size id="s" name="form" property="in_maths" />
<bean:size id="ss" name="form" property="kath_maths"/>

<div align=center>
<h3 style="text-align:center">Ανάθεση μαθημάτων στον καθ. <bean:write name="form" property="perigrafi"/>
	
   <TABLE align="center" >
        	<th>Μάθημα</th> 
      <logic:iterate name="form" property="kath_maths" id="lsmath">
         <tr>
           	<td>
          		<bean:write name="lsmath" property="value" /><a href='<%=request.getContextPath()%>/kathigites.do?action=math_del&id=<bean:write name="lsmath" property="key"/>'>Διαγραφή</a>			
			</td>          	
         </tr>
      </logic:iterate>
   </TABLE>		
	 
     
    <logic:greaterThan name="s" value="0" > 
     <a id="math" href='#' onclick="child_open(this)" title='Μαθήματα'>Μαθήματα που μπορεί να διδάξει</a>
	  	<form action="<%=request.getContextPath()%>/kathigites.do?action=add_math" method="post">
	  	<div name="d_math" style="display:none;border:1px solid black" id="div_math" align=center>
			<h3>Εισαγωγή Μαθήματος</h3>
		<table>
			<tr>
				<html:select name="form" property="id_epas_sxol_etos_eidik_tax_mathimata" value="0" >
	  	 		<html:option value="0">Επιλέξτε Μάθημα</html:option>
	  	 		<html:optionsCollection name="form" property="in_maths" label="value" value="key"/>
	  	 		</html:select>
			</tr>
			<tr>
				<td><input type="submit" value="Εισαγωγή" /></td><td align="center"><input type=button name="rev_mathima"  onclick="nosub('div_math')" value="Κλείσιμο φόρμας"/></td>
			</tr>
		</table>
	    </div>
	    </form>
	</logic:greaterThan> 
		
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