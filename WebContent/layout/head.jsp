<%-- 
    Document   : head
    Created on : Feb 7, 2015, 12:17:51 PM
    Author     : olga
--%>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@ page import="beans.*" %>
<%@ page import="org.apache.struts.tiles.beans.*" %>
<%@ page import="java.util.*" %>

    


	<tiles:importAttribute />
    <logic:notPresent name="he">
           <img src='<%=request.getContextPath() +"/images/elgo_logo_gr.jpg"%>' width="100%" height="100%">
    </logic:notPresent> 
    <logic:present name="he">
       	 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menu.css"/> 
	          <div id="sse50">
  				<div id="sses50">
    				<ul>
              			<logic:iterate name="items" id="item" type="org.apache.struts.tiles.beans.MenuItem" >
                		  <li>
                			<form action="<%=item.getLink()%>" method="post" style="display:inline">
                  				<a href="javascript:;" onclick="parentNode.submit();">
                    				<%=item.getValue()%>
                    				
                  				</a> 
                			</form>
                		  <li> 
              			</logic:iterate>
              		</ul>
              	</div>
              </div>
       	<script src="<%=request.getContextPath()%>/js/menu.js"></script>
    </logic:present >         
                   
 
  
