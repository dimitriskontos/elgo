package actions;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ayiuda.db;
import beans.element;
import beans.profile;
import forms.ep_orologioForm;


import javax.swing.JOptionPane;
public class ep_orologio extends Action {

	
	 /* forward name="success" path="" */
    private static final String SUCCESS = "success"; 

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
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
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
    	db<ep_orologioForm> dbname=null;
    	dbname=new db<ep_orologioForm>();
    	s.setAttribute("db", dbname);
    	String action=request.getParameter("action");
    	if(action.equals("keni"))
    	{
    		ep_orologioForm lForm=(ep_orologioForm)form;
    		try{
    			if(lForm.getId_orol_tmima()!=0) 
    				lForm=new ep_orologioForm(); 
    		}catch(Exception e){ 
    			lForm=new ep_orologioForm(); 
    		}
    		lForm.setId_epas_sxol_etos_eidik_tax_tmima(dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'"));
    		lForm.setId_epas_sxol_etos_eidik_tax(dbname.getId("select id_epas_sxol_etos_eidik_tax from epas_sxol_etos_eidik_tax_tmima where id_epas_sxol_etos_eidik_tax_tmima='" + Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax_tmima())+"'")); 
    		if(lForm.getId_orol_tmima()==0)
    		{
    			Map<String,String> imeres=new  LinkedHashMap<String,String>();
    			imeres.put("Monday", "Δευτέρα");
    			imeres.put("Tuesday","Τρίτη");
    			imeres.put("Wednesday", "Τετάρτη");
    			imeres.put("Thursday","Πέμπτη");
    			imeres.put("Friday", "Παρασκευή");
    			lForm.setImeres(imeres);
    			Map<String,String> ores=new LinkedHashMap<String, String>();
    			ores.put("1h","1η ώρα"); 
    			ores.put("2h","2η ώρα");
    			ores.put("3h","3η ώρα");
    			ores.put("4h","4η ώρα");
    			ores.put("5h","5η ώρα"); 
    			lForm.setOres(ores); 
    		}
    		lForm.setId_epas_sxol_etos(dbname.getId("select see.id_epas_sxol_etos from epas_sxol_etos_eidik as see, epas_sxol_etos_eidik_tax as seet where see.id_epas_sxol_etos_eidik=seet.id_epas_sxol_etos_eidik and seet.id_epas_sxol_etos_eidik_tax='" +lForm.getId_epas_sxol_etos_eidik_tax() +"'"));
    		try{
    			lForm.setMathimata(dbname.fillMap("select tm.id_epas_sxol_etos_eidik_tax_mathimata, concat(m.perigrafi, \" \", tm.type) from epas_sxol_etos_eidik_tax_mathimata as tm, mathimata as m where tm.id_epas_sxol_etos_eidik_tax='" + Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax()) + "' and tm.id_mathima=m.id_mathima"));
    		}catch(Exception e){}
    		try{
    			lForm.setKathigites(dbname.fillMap("(select kese.id_kathigites_epas_sxol_etos, kese.perigrafi from kathigites_epas_sxol_etos as kese, ipalliloi as ip where kese.is_ypal='1' and kese.id_ekpaideyti=ip.id_name and kese.id_epas_sxol_etos='" +lForm.getId_epas_sxol_etos()+ "') union (select kese1.id_kathigites_epas_sxol_etos, kese1.perigrafi from kathigites_epas_sxol_etos as kese1, ekpedeytes as ek where kese1.is_ypal='0' and kese1.id_ekpaideyti=ek.id_ekpaideyti and kese1.id_epas_sxol_etos='" +lForm.getId_epas_sxol_etos()+ "')")); 
    		}catch(Exception e){}
    		List<ep_orologioForm.point> lst=null;
    		dbname=null;
    		db<ep_orologioForm.point> dbname1=null;
        	dbname1=new db<ep_orologioForm.point>();
        	s.setAttribute("db", dbname1);
    		try{
        		
        		String table="orologio_tmima";
        		String field="id_epas_sxol_etos_eidik_tax_tmima";
        		List<String> fields=new ArrayList<String>(); 
        		fields.add("setId_epas_sxol_etos_eidik_tax_mathimata");
        		fields.add("setId_orol_tmima");
        		fields.add("setImera"); 
        		fields.add("setOra");
        		fields.add("setId_kathigites_epas_sxol_etos");
        		try{
        			lst=dbname1.fillList(table,field,lForm.getId_epas_sxol_etos_eidik_tax_tmima(),fields,new String("forms.ep_orologioForm$point")); 
        			lForm.setProgramma(lst);
        		}catch(Exception e){
        			lForm.setProgramma(new ArrayList<ep_orologioForm.point>());
        			
        		} 
        		   			 
        	}catch(Exception e){  
        	}
    		String mess=new String("eporol_keni");
            s.setAttribute("mess", mess);              
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria"); 
    	}else if(action.equals("add_math")) 
    	{ 
    		
    		ep_orologioForm lForm=(ep_orologioForm)s.getAttribute("form"); 
    		lForm.setId_epas_sxol_etos_eidik_tax_mathimata(Integer.parseInt(request.getParameter("id_mathima")));
    		lForm.setId_kathigitis(Integer.parseInt(request.getParameter("id_kathigitis")));
    		lForm.setImera(request.getParameter("imera"));
    		lForm.setOra(request.getParameter("ora"));
    		String sql="insert into orologio_tmima(id_epas_sxol_etos_eidik_tax_mathimata,id_epas_sxol_etos_eidik_tax_tmima,imera,ora,id_kathigites_epas_sxol_etos) values('" +Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax_mathimata()) +"', '" +Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax_tmima()) +"',";
    		sql+= "\""+ lForm.getImera()+ "\"," ;
    		sql+= "\"" +lForm.getOra() + "\",'"+lForm.getId_kathigitis()+"')";
    		
    		try{ 
    			if(dbname.updateRecord(sql)){}
    		}catch(Exception e){
    			//JOptionPane.showMessageDialog(null,e.getMessage()); 
    			  
    		}
    		List<ep_orologioForm.point> lst=null;
    		dbname=null;
    		db<ep_orologioForm.point> dbname1=null;
        	dbname1=new db<ep_orologioForm.point>();
        	s.setAttribute("db", dbname1);
    		try{
        		
        		String table="orologio_tmima";
        		String field="id_epas_sxol_etos_eidik_tax_tmima";
        		List<String> fields=new ArrayList<String>(); 
        		fields.add("setId_epas_sxol_etos_eidik_tax_mathimata");
        		fields.add("setId_orol_tmima");
        		fields.add("setImera"); 
        		fields.add("setOra");
        		fields.add("setId_kathigites_epas_sxol_etos");
        		try{
        			lst=dbname1.fillList(table,field,lForm.getId_epas_sxol_etos_eidik_tax_tmima(),fields,new String("forms.ep_orologioForm$point")); 
        			lForm.setProgramma(lst);
        		}catch(Exception e){
        			//JOptionPane.showMessageDialog(null, e.getMessage());  
        		} 
        		   			  
        	}catch(Exception e){  
        	}
    		String mess=new String("eporol_keni");
            s.setAttribute("mess", mess);             
            s.setAttribute("form", lForm); 
            return new ActionForward(".kyria");
    	}
    	return new ActionForward(".kyria");
    }
    
   
    
    
}
