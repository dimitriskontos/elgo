package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ayiuda.db;
import beans.profile;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;





public class mathima extends Action {

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  throws Exception {
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
        	String sql=null;
            PreparedStatement st=null;
            sql="insert into mathimata(onoma) values(?)";
            try{
            	
            	st=dbname.getCon().prepareStatement(sql);
           	 	st.setString(1,enteredText);
           	 	st.executeUpdate();
                st.close();
            	
            }catch(Exception e){}
            
            sql="select id_mathima,onoma from mathimata where onoma like \"" + enteredText + "%\";";
            dbname.getRecordset(sql);
            ResultSet onomata=dbname.getRs();
            while(onomata.next())
            {
            	
            	sb.append(onomata.getInt(1) +"," + onomata.getString(2));
            	
            }
        }
      
        
        out.println(sb.toString());
        
        out.flush();
        return null;
    }
}
        
        	
        	 
