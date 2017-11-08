/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import ayiuda.db;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author olga
 */
public class askname extends org.apache.struts.action.Action {

    
     /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        StringBuffer sb=new StringBuffer(1024);
        int numSuggestions=0;
        PrintWriter out = response.getWriter();
        int top=0;
        HttpSession s=null;
        s=request.getSession();
        db dbname;
        dbname=(db)s.getAttribute("db");
        String enteredText=(String)request.getParameter("enteredText"); 
        
        
        if(dbname==null)
        {   dbname=new db();
            s.setAttribute("db", dbname);
        }
        if(enteredText!=null && !enteredText.equalsIgnoreCase(""))
        {
            enteredText=enteredText.toLowerCase();
            enteredText=Character.toString(enteredText.charAt(0)).toUpperCase()+enteredText.substring(1);
            
            String sql="select id_name,epwnymo,onoma from ipalliloi where epwnymo like \"" + enteredText + "%\";";
            dbname.getRecordset(sql);
            ResultSet onomata=dbname.getRs();
            onomata.beforeFirst();
            while(onomata.next())
            {
                sb.append(onomata.getInt(1) +"," + onomata.getString(2) + "," +onomata.getString(3)+"\n");
                           
            } 
        }
      
        
        out.println(sb.toString());
        //JOptionPane.showMessageDialog(null,sb.toString());
        
        out.flush();
        return null;
    }
}
