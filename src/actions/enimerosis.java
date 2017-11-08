package actions;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.struts.action.Action;
import ayiuda.db;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




public class enimerosis extends Action {

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  throws Exception 
	{
			
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		StringBuffer sb=new StringBuffer(1024);
		PrintWriter out = response.getWriter();
		HttpSession s=null;
		s=request.getSession();
		db dbname;
		dbname=(db)s.getAttribute("db");
		String akrwnymio=null;
		String onoma=null;
		String table=null;
		int result=-1;
		if(dbname==null)
		{   
			return null;
		}
		try{
			akrwnymio=(String)request.getParameter("akrwnymio");
		
		}catch(Exception e){
			return null;
		}
		try{
			onoma=(String)request.getParameter("onoma");
		}catch(Exception e){
			return null;
		}
		try{
			if(onoma.equals("thris"))
				table="thriskeyma";
			else if(onoma.equals("ypik"))
				table="ypikootita";
			else if(onoma.equals("epag"))
				table="epaggelmata"; 
			else if(onoma.equals("maths"))
				table="mathimata";
			result=dbname.inserts(table,akrwnymio);
			if(result>0)
			{ 
				sb.append(Integer.toString(result) +"," + akrwnymio);
				//JOptionPane.showMessageDialog(null,sb.toString());
				out.println(sb.toString());
				
			}else
				JOptionPane.showMessageDialog(null,"else"+Integer.toString(result));
			
		}catch(Exception e){
			
		}
		out.flush();
		return null;
	}
	
	
	
	
	
	
}
