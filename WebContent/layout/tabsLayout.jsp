<%@page import="org.apache.struts.tiles.taglib.GetAttributeTag"%>
<%@page pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="org.apache.struts.tiles.beans.*" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.*" %>

<tiles:importAttribute/>
<tiles:useAttribute id="selectedIndexStr" name="selectedIndex" ignore="true" classname="java.lang.String" />
<tiles:useAttribute name="items" classname="java.util.List" />

<%
    String selectedColor="#98ABC7";
    String notSelectedColor="#C0C0C0";
    String tmp=request.getContextPath();
    
    int index=0;
    int selectedInd=0;
    try {
        selectedInd = Integer.parseInt(selectedIndexStr);
        //selectedInd = Integer.parseInt(request.getParameter(selectedIndexStr ));
    } catch( java.lang.NumberFormatException ex )
    { // do nothing
        selectedInd=0;
    }
    if( selectedInd < 0 || selectedInd > items.size() ) selectedInd = 0;
        String selectedBody =((org.apache.struts.tiles.beans.MenuItem)items.get(selectedInd)).getLink(); // Selected body
    Iterator<MenuItem> it=((List<MenuItem>)(request.getSession().getAttribute("menu"))).iterator();
	while(it.hasNext())
	{
		MenuItem t1=(MenuItem) it.next();
		if(t1.getValue().contains("Εισαγωγή"))
		{
			tmp=request.getContextPath() +"/pk.do?action=keni";
		
		}
		else if (t1.getValue().contains("Επεξεργασία"))
			tmp=request.getContextPath() +"/pk.do?action=edit";
		
	}
				
		
        
%>

<table border="0" cellspacing="0" cellpadding="0">
    <%-- Draw tabs --%>
    <tr>
        <td width="10">&nbsp;</td>
        <td>
            <table border="0" cellspacing="0" cellpadding="5">
                <tr>
                    
                    
                    <logic:iterate id="item" name="items" type="org.apache.struts.tiles.beans.MenuItem" >
                        <% // compute href
                           
                        	String href =tmp+ "&parameterName=" + index;
                        	String color = notSelectedColor;
                            if( index == selectedInd )
                            {
                                selectedBody = item.getLink();
                                color = selectedColor;
                            } // enf if
                            index++;
                        %>
                    <td bgcolor="<%=color%>">
                        <a href="<%=href%>" /><%=item.getValue()%></a>
                       
                    </td>
                    <td width="1" ></td>
                    </logic:iterate>
                </tr>
            </table>
        </td>
        <td width="10" >&nbsp</td>
    </tr>
    <tr>
        <td height="5" bgcolor="<%=selectedColor%>" colspan="3" >&nbsp;</td>
    </tr>
    <%-- Draw body --%>
    <tr>
        <td width="10" bgcolor="<%=selectedColor%>">&nbsp;</td>
        <td bgcolor="<%=selectedColor%>">
           <tiles:insert name="<%=selectedBody%>" flush="true" />
        </td>
        <td width="10" bgcolor="<%=selectedColor%>">&nbsp;</td>
    </tr>
    <tr>
        <td height="5" bgcolor="<%=selectedColor%>" colspan="3" >&nbsp;</td>
    </tr>
</table>