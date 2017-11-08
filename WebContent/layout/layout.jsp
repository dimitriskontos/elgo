<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<% response.setContentType("UTF-8");%>

<html>
    <head>
        <meta content="content-type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/epiloges.css">
        <title>ΓΕΝΙΚΗ ΔΙΕΥΘΥΝΣΗ ΕΚΠΑΙΔΕΥΣΗΣ ΚΑΙ ΚΑΤΑΡΤΙΣΗΣ</title>
        <!--Requirement jQuery-->
		
    </head>   
    <body>
        
        <div id="d1">    
      
        <table id="te1" height="10%" width="100%">
            <tr id="te11">
                <td width="90%" ><tiles:insert attribute="heads"/></td><td id="log1"><tiles:insert attribute="syndesi" /></td>
            </tr>
        </table>
        <table width="100%" height="90%">
            <tr>
                <td width="25%" id="ep1" valign="top"><tiles:insert attribute="men"/></td>
                <td id="ef1" width="75%" valign="top"><tiles:insert attribute="efarm"/></td>
            </tr>   
            
        </table>
    </div>  
    </body>
      
</html>
