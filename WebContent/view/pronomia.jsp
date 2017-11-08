<%-- 
    Document   : pronomia
    Created on : 15 Μαϊ 2015, 2:20:23 μμ
    Author     : Administrator
--%>

<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<logic:notEmpty name="form">
    <h3 style="text-align:center">Εισαγωγή προνομίων σε υπαλλήλους</h3>
    <div width="33%" style="display: inline;" >
        <TABLE align="left">
            <th>Επιλογές</th>
            <logic:iterate id="epiloges_id" name="form" property="epiloges">
                    <tr>
                        <td><bean:write name="epiloges_id" property="value"/> </td>
                        
                    </tr>
                </logic:iterate>
        </table>
                
        
            
                
            
        
        
    </div>
    <div width="33%"  style="display: inline">
        <TABLE align="left">
            <th>Προσωπικό</th>
            <logic:iterate id="prosopiko_id" name="form" property="xristes">
                    <tr>
                        <td><bean:write name="prosopiko_id" property="value"/> </td>
                        
                    </tr>
                </logic:iterate>
        </table>
        
        
        
        
        
        
    </div>
    <div style="display:inline" width="33%">
        <TABLE align="left">
            <th>Προνόμια</th>
            <logic:iterate id="menu_id" name="form" property="pronomia">
                    <tr>
                        <td><bean:write name="menu_id" property="value"/> </td>
                        
                    </tr>
                </logic:iterate>
        </table>
        
        
    </div>
    
</logic:notEmpty>