package actions;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.swing.JOptionPane;
import ayiuda.db;
import beans.element;
import beans.profile;
import forms.ep_parusiologioForm;
import forms.kathigitesForm;
import forms.spoudesForm;
import forms.ep_parusiologioForm.orolo;

import java.util.*;
import com.google.gson.*;

public class prog_spoudon extends Action 
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession s=null;
        profile p=null;
        s= request.getSession();
        p=(profile)s.getAttribute("profile");
        PrintWriter out = response.getWriter();
        if(p==null)
            return new ActionForward(".kyria");
        db<spoudesForm.mathProg> dbname;
        dbname=(db)s.getAttribute("db");
        if(dbname==null)
        {   dbname=new db<spoudesForm.mathProg>();
            s.setAttribute("db", dbname);
        } 
        String action=request.getParameter("action"); 
        if(action.equals("keni"))
        {
	 	   //PARE taxi
        	int id_pediu=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'");
        	String taxi=dbname.getName("select perigrafi from dummies where id='" + id_pediu + "'");
        	//epas_sxol_etos_eidik_tax
        	int ypagete=dbname.getId("select ypagete from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'");
        	int id_epas_sxol_etos_eidik=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.toString(ypagete) +"'");
        	int id_epas_sxol_etos_eidik_tax=dbname.getId("select id_epas_sxol_etos_eidik_tax from epas_sxol_etos_eidik_tax where id_epas_sxol_etos_eidik='" + Integer.toString(id_epas_sxol_etos_eidik) + "' and taxi=\"" + taxi + "\"" );
        	spoudesForm lForm=(spoudesForm)form;
        	try{
    			if(lForm.getId_epas_sxol_etos_eidik_tax()!=0) 
    				lForm=new spoudesForm();
    		}catch(Exception e){ 
    			lForm=new spoudesForm();
    		}
        	try{
        		if(lForm.getId_epas_sxol_etos_eidik_tax()==0){  
        			lForm.setMathimata(dbname.fillMap("select id_mathima,perigrafi from mathimata")); 
        			Map<String,String> typoi=new HashMap<String,String>();
                    typoi.put("ergastirio", "ΕΡΓΑΣΤΗΡΙΟ");
                    typoi.put("theoria","ΘΕΩΡΙΑ");
                    lForm.setN_typos(typoi);
                    
        		}
        	}catch(Exception e){ 
        		JOptionPane.showMessageDialog(null,e.getMessage());
        		
        	}
        	if(lForm.getId_epas_sxol_etos_eidik_tax()!=id_epas_sxol_etos_eidik_tax) 
        	{
        		lForm.setId_epas_sxol_etos_eidik_tax(id_epas_sxol_etos_eidik_tax);
        		lForm.setTaxi(taxi);
        	}
        	
        	update(lForm,dbname);
        	
        	String mess=new String("progspoudon_keni");
            s.setAttribute("mess", mess);             
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria");  
        }else if(action.equals("add"))
        {
        	spoudesForm lForm=(spoudesForm)s.getAttribute("form");
        	lForm.setMathimata(dbname.fillMap("select id_mathima,perigrafi from mathimata")); 
        	List<String>pedia=new ArrayList<String>();
        	pedia.add("Id_mathima");
        	pedia.add("Id_epas_sxol_etos_eidik_tax");
        	pedia.add("Type");
        	pedia.add("Ores"); 
        	String table="epas_sxol_etos_eidik_tax_mathimata";
        	int id_epas_sxol_etos_eidik_tax_mathimata=0; 
        	try{
        		id_epas_sxol_etos_eidik_tax_mathimata=dbname.insert(table, pedia, lForm);
        	}catch(Exception e){}
        	update(lForm,dbname);
        	String mess=new String("progspoudon_keni");
            s.setAttribute("mess", mess);             
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria");
        	
        	
        }
		
		return super.execute(mapping, form, request, response);
	}
	
	
	private void update(spoudesForm lForm, db<spoudesForm.mathProg> dbname) 
	{
		
		List<spoudesForm.mathProg> lst=null;
    	try{
    		
    		dbname=new db<spoudesForm.mathProg>(); 
    		String table="epas_sxol_etos_eidik_tax_mathimata";
    		String field="id_epas_sxol_etos_eidik_tax";
    		List<String> fields=new ArrayList<String>(); 
    		fields.add("setId_mathima");
    		fields.add("setType"); 
    		fields.add("setOres");
    		try{
    			lst=(dbname.fillList(table,field,lForm.getId_epas_sxol_etos_eidik_tax(),fields,new String("forms.spoudesForm$mathProg"))); 
    			lForm.setMaths(lst);
    		}catch(Exception e){
    			JOptionPane.showMessageDialog(null, "pp2"+e.getMessage());  
    		} 
    		   			 
    	}catch(Exception e){ }
	
	
	
	}
	
	
	

}
