<%-- 
    Document   : men
    Created on : 4 Φεβ 2015, 1:36:00 μμ
    Author     : Administrator
--%>

<%@page pageEncoding="utf8" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="beans.profile" %>
  
<tiles:importAttribute />

 
<logic:notPresent name="title">
    <div style="background-color:white;display:table-cell">
    <bean:message key="menu.title"/><br> 
    </div>
</logic:notPresent> 
	 
    <logic:present name="title">
       <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/s1.css"/> 
       <div style="background-color:white;width:100%;height:100%">
        <strong><tiles:getAsString name="title"/><%="\n"%> </strong><br><br>
        
     <table id="mytable">
       
       <logic:iterate id="item" name="items" type="beans.simplemen">
           <bean:define id="n" name="item" property="tooltip" />
                         
               		<logic:match name="item" property="link" value="yes">
               	      <tr data-depth="<%=item.getTooltip()%>" class="collapse level<%=item.getTooltip()%>"> 
               			<td><logic:equal name="item" property="hasChild" value="true"><span class="toggle collapse"></span></logic:equal>
               			<form action="<%=request.getContextPath()%>/allagi.do" method="post" style="display:inline"><a style="text-decoration: none;color: black"><span style="font-weight: bold; background-color: red"><%=item.getValue()%></span></a>
               					<input type="hidden" name="active" value="<%=item.getLink()%>"/><input type="hidden" name="onoma" value="<%=item.getIcon()%>" />
               				</form>
               			</td>
               		  </tr>
               		</logic:match>
               		<logic:notMatch name="item" property="link" value="yes">
               			<tr data-depth="<%=item.getTooltip()%>" class="collapse level<%=item.getTooltip()%>"> 
               			<td><logic:equal name="item" property="hasChild" value="true"><span class="toggle collapse"></span></logic:equal>
               			<form action="<%=request.getContextPath()%>/allagi.do" method="post" style="display:inline"><a style="text-decoration: none;color: black"><%=item.getValue()%></a>
               			   		<input type="hidden" name="active" value="<%=item.getLink()%>"/><input type="hidden" name="onoma" value="<%=item.getIcon()%>" />
               			   		</form>
               		    </td>
               		  </tr>
               		</logic:notMatch>
                      
              
           
           
        </logic:iterate>
       </table>
     	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script>
			$(function() {
    			$('#mytable').on('click', '.toggle', function () {
        		//Gets all <tr>'s  of greater depth
        		//below element in the table
        		var findChildren = function (tr) {
            	var depth = tr.data('depth');
           	 			return tr.nextUntil($('tr').filter(function () {
                		return $(this).data('depth') <= depth;
            		}));
        		};
				var el = $(this);
        		var tr = el.closest('tr'); //Get <tr> parent of toggle button
        		var children = findChildren(tr);
				//Remove already collapsed nodes from children so that we don't
        		//make them visible. 
        		//(Confused? Remove this code and close Item 2, close Item 1 
        		//then open Item 1 again, then you will understand)
        		var subnodes = children.filter('.expand');
        		subnodes.each(function () {
            		var subnode = $(this);
            		var subnodeChildren = findChildren(subnode);
            		children = children.not(subnodeChildren);
        		});
				//Change icon and hide/show children
        		if (tr.hasClass('collapse')) {
            		tr.removeClass('collapse').addClass('expand');
            		children.hide();
        		} else {
            		tr.removeClass('expand').addClass('collapse');
            		children.show();
        		}
        			return children;
    			});
			
    	        $('a').click(function(event) {
    	        	var frm=$(this).closest("form");
    	        	frm.submit();
    	           
    	        });
    						
			});
			
		</script>
		</div>
     </logic:present>
     


