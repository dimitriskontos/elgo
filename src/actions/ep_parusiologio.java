package actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
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
import forms.ep_parusiologioForm;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.sql.ResultSet;
public class ep_parusiologio extends Action {

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
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
    	db<ep_parusiologioForm> dbname=null;
    	dbname=new db<ep_parusiologioForm>();
    	s.setAttribute("db", dbname);
    	String action=request.getParameter("action");
    	if(action.equals("keni"))
    	{
    		ep_parusiologioForm lForm=(ep_parusiologioForm)form;
    		
    		int id_epas_sxol_etos_eidik_tax_tmima=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'");
    		int id_epas_sxol_etos_eidik_tax=dbname.getId("select id_epas_sxol_etos_eidik_tax from epas_sxol_etos_eidik_tax_tmima where id_epas_sxol_etos_eidik_tax_tmima='" + Integer.toString(id_epas_sxol_etos_eidik_tax_tmima)+"'");
    		int id_epas_sxol_etos=dbname.getId("select see.id_epas_sxol_etos from epas_sxol_etos_eidik as see, epas_sxol_etos_eidik_tax as seet where see.id_epas_sxol_etos_eidik=seet.id_epas_sxol_etos_eidik and seet.id_epas_sxol_etos_eidik_tax='" +Integer.toString(id_epas_sxol_etos_eidik_tax) +"'");
    		if(lForm.getId_epas_sxol_etos_eidik_tax()!=id_epas_sxol_etos_eidik_tax)
    			lForm.setId_epas_sxol_etos_eidik_tax(id_epas_sxol_etos_eidik_tax);
    		if(lForm.getId_epas_sxol_etos_eidik_tax_tmima()!=id_epas_sxol_etos_eidik_tax_tmima)
    			lForm.setId_epas_sxol_etos_eidik_tax_tmima(id_epas_sxol_etos_eidik_tax_tmima);
    		if(lForm.getId_kathigites_epas_sxol_etos()!=id_epas_sxol_etos)
    			lForm.setId_epas_sxol_etos(id_epas_sxol_etos);
    		try{
    			lForm.setKathigites(dbname.fillMap("(select kese.id_kathigites_epas_sxol_etos, kese.perigrafi from kathigites_epas_sxol_etos as kese, ipalliloi as ip where kese.is_ypal='1' and kese.id_ekpaideyti=ip.id_name and kese.id_epas_sxol_etos='" +lForm.getId_epas_sxol_etos()+ "') union (select kese1.id_kathigites_epas_sxol_etos, kese1.perigrafi from kathigites_epas_sxol_etos as kese1, ekpedeytes as ek where kese1.is_ypal='0' and kese1.id_ekpaideyti=ek.id_ekpaideyti and kese1.id_epas_sxol_etos='" +lForm.getId_epas_sxol_etos()+ "')"));
    		}catch(Exception e){
    			lForm.setKathigites(new HashMap<String,String>());
    		}
    		try{
    			lForm.setMathites(dbname.fillMap("select id_mathiti_tmima,perigrafi from mathites_tmima where id_epas_sxol_etos_eidik_tax_tmima='"+lForm.getId_epas_sxol_etos_eidik_tax_tmima() +"' and apo is NULL"));
    		}catch(Exception e){
    			lForm.setMathites(new HashMap<String,String>());
    		}
    		try{
    			lForm.setMathimata(dbname.fillMap("select tm.id_epas_sxol_etos_eidik_tax_mathimata, concat(m.perigrafi, \" \", tm.type) from epas_sxol_etos_eidik_tax_mathimata as tm, mathimata as m where tm.id_epas_sxol_etos_eidik_tax='" + Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax()) + "' and tm.id_mathima=m.id_mathima"));
    			
    		}catch(Exception e){
    			lForm.setMathimata(new HashMap<String,String>());
    		}
    		
    		Map<String,String> ores=new LinkedHashMap<String, String>();
			ores.put("1h","1η ώρα"); 
			ores.put("2h","2η ώρα");
			ores.put("3h","3η ώρα");
			ores.put("4h","4η ώρα");
			ores.put("5h","5η ώρα"); 
			lForm.setOres(ores); 
			lForm.setLst(new ArrayList<ep_parusiologioForm.orolo>());
			String mess=new String("epparus_keni");
            s.setAttribute("mess", mess);              
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria"); 
    		
    		 
    	}else if(action.equals("query")){
    		//ΘΑ ΦΈΡΩ ΑΠΟ ΩΡΟΛΟΓΙΟ, ΗΜΕΡΑ
    		//ResultSet orol=null;
    		ep_parusiologioForm lForm=(ep_parusiologioForm)s.getAttribute("form");
    		update(lForm,dbname);
    		String mess=new String("epparus_keni"); 
            s.setAttribute("mess", mess);               
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria"); 
    	
    	}else if(action.equals("add")){
    		ep_parusiologioForm lForm=(ep_parusiologioForm)s.getAttribute("form");
    		lForm.setId_orol_tmima(Integer.parseInt(request.getParameter("id_orol_tmima")));
    		lForm.setId_kathigites_epas_sxol_etos(Integer.parseInt(request.getParameter("id_kathigites_epas_sxol_etos")));
    		List<String>pedia=new ArrayList<String>();
    		String[] parusies=null;
    		String table="parusiol_kathig";
    		try{
    			parusies=request.getParameterValues("parusies");
    		}catch(Exception e){}
    		pedia.add("Id_orol_tmima");
    		pedia.add("Imera");
    		pedia.add("Id_kathigites_epas_sxol_etos");
    		//JOptionPane.showMessageDialog(null,"1");
    		lForm.setId_parusiol_kathig(dbname.insert(table, pedia, lForm));  
    		//JOptionPane.showMessageDialog(null,"2");
    		pedia=new ArrayList<String>();;
    		pedia.add("Id_parusiol_kathig");
    		pedia.add("Parusia");
    		pedia.add("Id_mathiti_tmima");
    		table="epas_parusiologio";
    		for (Map.Entry<String, String> entry : lForm.getMathites().entrySet())
    		{	lForm.setParusia("0");
    			if(parusies!=null)
    				for (int i=0;i<parusies.length;i++)
    				{
    					if(entry.getKey().equals(parusies[i]))
    					{
    						lForm.setParusia("1");
    						break;
    					}
    				}
    			lForm.setId_mathiti_tmima(Integer.parseInt(entry.getKey()));
    			dbname.insert(table, pedia, lForm);    			
    		}
    		update(lForm,dbname);
    		s.setAttribute("form", lForm);    
    		String mess=new String("epparus_keni"); 
            s.setAttribute("mess", mess);               
            return new ActionForward(".kyria"); 
    		
    	}else if(action.equals("edit")){
    		ep_parusiologioForm lForm=(ep_parusiologioForm)s.getAttribute("form");
    		lForm.setId_orol_tmima(Integer.parseInt(request.getParameter("id_orol_tmima")));
    		lForm.setId_kathigites_epas_sxol_etos(Integer.parseInt(request.getParameter("id_kathigites_epas_sxol_etos")));
    		lForm.setId_parusiol_kathig(Integer.parseInt(request.getParameter("id_parusiol_kathig")));
    		//JOptionPane.showMessageDialog(null, request.getPar;ameter("id_kathigites_epas_sxol_etos"));
    		SimpleDateFormat  newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		String[] parusies=null;
    		String sql=null;//"";
    		try{
    			parusies=request.getParameterValues("parusies");
    		}catch(Exception e){}
    		sql="update parusiol_kathig set id_kathigites_epas_sxol_etos='" + lForm.getId_kathigites_epas_sxol_etos() + "' where id_orol_tmima='" + lForm.getId_orol_tmima() + "' and imera='" + newDateFormat.format(lForm.getImera()) +"' and id_parusiol_kathig='" + lForm.getId_parusiol_kathig() +"'";
		    try{
				dbname.updateRecord(sql);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage()); 
			}
    		for (Map.Entry<String, String> entry : lForm.getMathites().entrySet())
    		{	lForm.setParusia("0");
    		    if(parusies!=null)
    				for (int i=0;i<parusies.length;i++)
    				{
    					if(entry.getKey().equals(parusies[i]))
    					{
    						lForm.setParusia("1");
    						break;
    					}
    				}
    		    	lForm.setId_mathiti_tmima(Integer.parseInt(entry.getKey())); 
    		    	sql="update epas_parusiologio set parusia='" + lForm.getParusia() + "' where id_parusiol_kathig='" + lForm.getId_parusiol_kathig() + "' and id_mathiti_tmima='" + lForm.getId_mathiti_tmima() +"'";
    			    try{
    					dbname.updateRecord(sql);
    				}catch(Exception e){
    					//JOptionPane.showMessageDialog(null, e.getMessage()); 
    				}
    				
    			
    		} 
    		
    		update(lForm,dbname);
    		String mess=new String("epparus_keni"); 
            s.setAttribute("mess", mess);               
            return new ActionForward(".kyria"); 
    		
    	}
		return new ActionForward(".kyria");
	}
	
	private void update(ep_parusiologioForm lForm,db<ep_parusiologioForm> dbname) 
	{
		ResultSet orol=null;
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		Date MyDate=null;
		try{
		  MyDate = newDateFormat.parse(lForm.getImerominia());
		}catch(Exception e){}
		newDateFormat.applyPattern("EEEE");
		String imera = newDateFormat.format(MyDate);
		newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sq="select par.id_mathiti_tmima, par.parusia, ot.ora,pk.id_kathigites_epas_sxol_etos,ot.id_epas_sxol_etos_eidik_tax_mathimata,pk.id_orol_tmima,pk.id_parusiol_kathig from epas_parusiologio as par, parusiol_kathig as pk, orologio_tmima as ot where par.id_parusiol_kathig=pk.id_parusiol_kathig and pk.id_orol_tmima=ot.id_orol_tmima and pk.imera='" + newDateFormat.format(lForm.getImera()) + "' and ot.id_epas_sxol_etos_eidik_tax_tmima='" +lForm.getId_epas_sxol_etos_eidik_tax_tmima() +"' and ot.imera='" +imera +"' order by ot.ora" ;
		//String sq="select par.id_mathiti_tmima, par.parusia, ot.ora,par.id_kathigites_epas_sxol_etos,ot.id_epas_sxol_etos_eidik_tax_mathimata,par.id_orol_tmima from epas_parusiologio as par, orologio_tmima as ot where par.id_orol_tmima=ot.id_orol_tmima and par.imera='" + newDateFormat.format(lForm.getImera()) + "' and ot.id_epas_sxol_etos_eidik_tax_tmima='" +lForm.getId_epas_sxol_etos_eidik_tax_tmima() +"' and ot.imera='" +imera +"' order by ot.ora" ;
		//JOptionPane.showMessageDialog(null,sq);
		try{
		  dbname.getRecordset(sq);
		  orol=dbname.getRs();
		}catch(Exception e){
			//JOptionPane.showMessageDialog(null, "1");
		} 
	    String or="6h";
	    ep_parusiologioForm.orolo tmp=null;
	    List<ep_parusiologioForm.orolo> lst=new ArrayList<ep_parusiologioForm.orolo>();
	    String lista="(";
	    boolean telos=false;
	    try{
	    	if(orol.first())
	    	{
	    		
	    		while(true)
	    		{
	    			or=orol.getString(3);
	    			tmp=lForm.new orolo();
	    			tmp.setApontes(new HashMap<String,String>()); 
	    			tmp.setIsEdit(true); 
	    			tmp.setOra(or);
	    			tmp.setId_orol_tmima(orol.getInt(6));
	    			tmp.setId_kathigites_epas_sxol_etos(orol.getInt(4));
	    			tmp.setId_epas_sxol_etos_eikik_tax_mathimata(orol.getInt(5));
	    			tmp.setId_parusiol_kathig(orol.getInt(7));
	    			lista+="\"" + or +"\",";
	    			while(or.equals(orol.getString(3)))
	    			{
	    				
	    				if(orol.getString(2).equals("0"))
	    				{
	    					tmp.getApontes().put(Integer.toString(orol.getInt(1)), orol.getString(2));
	    				}
	    				if(!orol.next())
	    				{
	    					telos=true;
	    					break;
	    				}
	    				if(!or.equals(orol.getString(3)))
	    				{   
	    					lst.add(tmp);
	    					break;
	    				}
	    			}
	    			if(telos)
	    			{
	    				lst.add(tmp);
	    				break;
	    			}
	    		} 
	    	}
	    }catch(Exception e){
	    	//JOptionPane.showMessageDialog(null, "error1:"+e.getMessage());
	    }
	    if(!lista.equals("(")) 
	    		lista=lista.substring(0, lista.length()-1);
	    lista=lista + ")";
	    //JOptionPane.showMessageDialog(null,lista);
		orol=null; 
		String sql=null; 
		
		if(lista.equals("()"))
	    	sql="select id_orol_tmima,ora,id_epas_sxol_etos_eidik_tax_mathimata,id_kathigites_epas_sxol_etos from orologio_tmima where id_epas_sxol_etos_eidik_tax_tmima='"+lForm.getId_epas_sxol_etos_eidik_tax_tmima()+"' and imera='" +imera + "'";
		else
			sql="select id_orol_tmima,ora,id_epas_sxol_etos_eidik_tax_mathimata,id_kathigites_epas_sxol_etos from orologio_tmima where id_epas_sxol_etos_eidik_tax_tmima='"+lForm.getId_epas_sxol_etos_eidik_tax_tmima()+"' and imera='" +imera + "' and ora not in " + lista;
	    try{
		dbname.getRecordset(sql);
	    orol=dbname.getRs();
	    orol.beforeFirst();
	    while(orol.next()) 
	    {
	    	tmp=lForm.new orolo();
	    	tmp.setId_orol_tmima(orol.getInt(1));
	    	tmp.setOra(orol.getString(2)); 
	    	tmp.setId_epas_sxol_etos_eikik_tax_mathimata(orol.getInt(3));
	    	tmp.setId_kathigites_epas_sxol_etos(orol.getInt(4));
	    	tmp.setIsEdit(false);
	    	lst.add(tmp);
	    } 
		Collections.sort(lst);
	    lForm.setLst(lst);
	    orol.close();
	    }catch(Exception e){
	    	//JOptionPane.showMessageDialog(null, "er"+e.getMessage());
	    }
	
	
	
	}
	
	
}
