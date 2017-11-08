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
import forms.kathigitesForm;
import javax.swing.JOptionPane;

public class kathigites extends Action {

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
    	db<kathigitesForm> dbname=null;
    	dbname=new db<kathigitesForm>();
    	s.setAttribute("db", dbname);
    	String action=request.getParameter("action");
    	if(action.equals("keni"))
    	{
    		kathigitesForm lForm=(kathigitesForm)form;
    		try{
    			if(lForm.getId_ekpaideyti()!=0) 
    				lForm=new kathigitesForm();
    		}catch(Exception e){ 
    			lForm=new kathigitesForm();
    		}
    		//βρες id epilogis epas_etos
    		int id_epilogi_epas_etos=dbname.getId("select ypagete from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'");
    		//bres kai bale epas_etos
    		lForm.setId_epas_sxol_etos(dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + id_epilogi_epas_etos +"'"));
    		String mess=new String("kathigites_keni");
    		try{
    			lForm.setNid_katigoria((Map<String,String>)dbname.fillMap("select id_ekpaideyseis, onoma from kat_ekpedeyseis"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_eidikotita((Map<String,String>)dbname.fillMap("select id_eidikotita, perigrafi from eidikotites"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_proslipsis((Map<String,String>)dbname.fillMap("select id_proslipsis, perigrafi from pros_kathig_epas"));
    		}catch(Exception e){}
    		try{
    			Map<String,String> pol=new LinkedHashMap<String, String>();
    			pol.put("1","Όχι"); 
    			pol.put("2","Ναι");  
    			lForm.setPol(pol); 
    		}catch(Exception e){} 
    		try{
    			Map<String,String> sex=new LinkedHashMap<String, String>();
    			sex.put("1","Άνδρας"); 
    			sex.put("2","Γυναίκα");  
    			lForm.setNsex(sex); 
    		}catch(Exception e){} 
    		try{
    			lForm.setForeis((Map<String,String>)dbname.fillMap("select f.id_kentro, concat(k.onoma,\" \",f.onoma) from foreis as f, kat_foreon as k where f.id_katigorias=k.id_katigorias or f.id_katigorias is null order by k.onoma,f.onoma"));
    		}catch(Exception e){}
    		s.setAttribute("mess", mess);            
    		s.setAttribute("form",lForm);
    		return new ActionForward(".kyria");  
    	}else if(action.equals("query")){ 
    		StringBuffer sb=new StringBuffer(1024); 
    		String epwnymo=null; 
    		kathigitesForm lForm=(kathigitesForm)s.getAttribute("form");
    		try{
    			epwnymo=request.getParameter("epwnymo"); 
    		}catch(Exception e){} 
    		epwnymo=epwnymo.toLowerCase();
            epwnymo=Character.toString(epwnymo.charAt(0)).toUpperCase()+epwnymo.substring(1);
            String is_ypal=request.getParameter("is_ypal");
    		Map<String,String> lst=null;
    		//JOptionPane.showMessageDialog(null,"2");
    		if (is_ypal.matches("ypal"))
    		{
    			lst=dbname.fillMap("select id_name,concat(epwnymo,' ',onoma) from ipalliloi where epwnymo like \"" + epwnymo + "%\" order by epwnymo;");
    			lForm.setIs_ypal(1);
    			
    		}else{
    			lst=dbname.fillMap("select id_ekpaideytis,concat(epwnymo,' ',onoma) from ekpedeytes where epwnymo like \"" + epwnymo + "%\";");
    			lForm.setIs_ypal(0);
    		} 
    		if(lst.size()>0)
    		{
    			for (Map.Entry<String,String> entry : lst.entrySet()) 
    				sb.append(entry.getKey()+ "," +entry.getValue()+"\n");
    			out.println(sb.toString());
    			out.flush();
    		} 
    	    s.setAttribute("form", lForm);
    	    return null;	
    		
    		
    		
    	}else if(action.equals("add")){
    		kathigitesForm lForm=(kathigitesForm)form;
    		lForm.setId_epas_sxol_etos(((kathigitesForm)s.getAttribute("form")).getId_epas_sxol_etos());
    		String is_ypal=request.getParameter("is_ypal");
    		List<String> pedia=new ArrayList<String>(); 
    		pedia.add("Epwnymo");
    		pedia.add("Onoma");
    		pedia.add("Patrwnymo");
    		pedia.add("Afm");
    		pedia.add("Ar_taytotitas");
    		pedia.add("Address");
    		pedia.add("Logariasmos"); 
    		pedia.add("Zip");
    		pedia.add("Sex");
    		pedia.add("Phd");
    		pedia.add("Msc");
    		pedia.add("Tilefono");
    		pedia.add("Poli");
    		pedia.add("Email");
    		pedia.add("Id_ekpaideyseis");
    		pedia.add("Id_eidikotita");
    		String table=null; 
    		if (is_ypal.matches("ypal"))
    		{
    			table="ipalliloi";
    			lForm.setIs_ypal(1); 
    			 
    		}
    		else{
    			table="ekpedeytes"; 
    			lForm.setIs_ypal(0);   
    		} 
    		int id_ekpedeyti=0;
    		try{
    			id_ekpedeyti=dbname.insert(table, pedia, lForm);
    			JOptionPane.showMessageDialog(null,  Integer.toString(id_ekpedeyti));
    		}catch(Exception e)
    		{
    			JOptionPane.showMessageDialog(null, "yp"+e.getMessage());
    			
    		}
    		if(id_ekpedeyti>0) 
    		{
    			lForm.setId_ekpaideyti(id_ekpedeyti); 
    			lForm.setPerigrafi(lForm.getEpwnymo()+" " + lForm.getOnoma());
    			pedia=new ArrayList<String>(); 
    			pedia.add("Id_ekpaideyti"); 
    			pedia.add("Is_ypal");
    			pedia.add("Id_epas_sxol_etos");
    			pedia.add("Perigrafi");
    			table="kathigites_epas_sxol_etos";
    			int id_ekpeyd_epas_etos=dbname.insert(table,pedia, lForm);
    			int id_epilogi_kath_epas_etos=dbname.add_epilogi(Integer.parseInt(((element)p.getViews().getActive()).getOnoma()), "kathigites_epas_sxol_etos","id_kathigites_epas_sxol_etos",id_ekpeyd_epas_etos);//
    			dbname.add_epilogi_action(p.getUser(),id_epilogi_kath_epas_etos ,25 , 13);
    			dbname.add_epilogi_action(p.getUser(),id_epilogi_kath_epas_etos ,26 , 14);
    			dbname.add_epilogi_action(p.getUser(),id_epilogi_kath_epas_etos ,27 , 15);//ανάθεση μαθημάτων
    			dbname.updateprofile(p,s,request);
    		}
			String mess=new String("kathigites_keni");
			s.setAttribute("mess", mess);            
    		s.setAttribute("form",lForm);
    		return new ActionForward(".kyria"); 
    	}   	
    	else if(action.equals("insert")){
    		//υπάρχουν τα στοιχεία του εκπαιδευτή, ενημερώνεται μόνο
    		kathigitesForm lForm=(kathigitesForm)s.getAttribute("form");
    		lForm.setId_ekpaideyti(Integer.parseInt(request.getParameter("id")));
    		lForm.setPerigrafi(request.getParameter("perigrafi"));
    		List<String> pedia=new ArrayList<String>(); 
    		pedia.add("Id_epas_sxol_etos");
    		pedia.add("Is_ypal");
    		pedia.add("Id_ekpaideyti"); 
    		pedia.add("Perigrafi");
    		String table="kathigites_epas_sxol_etos";
    		int id_ekpeyd_epas_etos=dbname.insert(table,pedia, lForm);
    		int id_epilogi_kath_epas_etos=dbname.add_epilogi(Integer.parseInt(((element)p.getViews().getActive()).getOnoma()), "kathigites_epas_sxol_etos","id_kathigites_epas_sxol_etos",id_ekpeyd_epas_etos);//
			dbname.add_epilogi_action(p.getUser(),id_epilogi_kath_epas_etos ,25 , 13);
			dbname.add_epilogi_action(p.getUser(),id_epilogi_kath_epas_etos ,26 , 14);
			dbname.add_epilogi_action(p.getUser(),id_epilogi_kath_epas_etos ,27 , 15);//ανάθεση μαθημάτων
			dbname.updateprofile(p,s,request);
    		out.println("ok");
    		out.flush();
			return null;
    		
    	}else if(action.equals("anath_math"))
    	{ 
    		//βρες καθηγητή
    		//βρες id epilogis epas_etos
    		kathigitesForm lForm=(kathigitesForm)form; 
    		try{
    			if(lForm.getId_ekpaideyti()!=0)   
    				lForm=new kathigitesForm(); 
    		}catch(Exception e){ 
    			lForm=new kathigitesForm();
    		} 
    		int id_kathigites_epas_sxol_etos=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'");
    		lForm.setId_kathigites_epas_sxol_etos(id_kathigites_epas_sxol_etos);
    		int id_ekpaideyti=dbname.getId("select id_ekpaideyti from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" +id_kathigites_epas_sxol_etos + "'");
    		lForm.setId_ekpaideyti(id_ekpaideyti);
    		lForm.setId_epas_sxol_etos(dbname.getId("select id_epas_sxol_etos from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" +id_kathigites_epas_sxol_etos + "'"));
    		lForm.setPerigrafi(dbname.getName("select perigrafi from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" +id_kathigites_epas_sxol_etos + "'"));
    		lForm.setIn_maths(dbname.fillMap("select e1.id_epas_sxol_etos_eidik_tax_mathimata, concat(e.perigrafi,\" \",e1.type) from epas_sxol_etos_eidik_tax_mathimata as e1 LEFT JOIN  kathigites_epas_sxol_etos_mathima as kesem ON kesem.id_epas_sxol_etos_eidik_tax_mathimata=e1.id_epas_sxol_etos_eidik_tax_mathimata, mathimata as e,epas_sxol_etos_eidik_tax as e2, epas_sxol_etos_eidik as e3 where kesem.id_kathigites_epas_sxol_etos is null and e1.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e1.id_mathima=e.id_mathima and e2.id_epas_sxol_etos_eidik=e3.id_epas_sxol_etos_eidik and e3.id_epas_sxol_etos='" + Integer.toString(lForm.getId_epas_sxol_etos()) + "'"));
    		lForm.setKath_maths(dbname.fillMap("select e1.id_epas_sxol_etos_eidik_tax_mathimata, concat(e.perigrafi,\" \",e1.type) from kathigites_epas_sxol_etos_mathima as kesem, epas_sxol_etos_eidik_tax_mathimata as e1, mathimata as e,epas_sxol_etos_eidik_tax as e2, epas_sxol_etos_eidik as e3 where  kesem.id_epas_sxol_etos_eidik_tax_mathimata=e1.id_epas_sxol_etos_eidik_tax_mathimata AND e1.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e1.id_mathima=e.id_mathima and e2.id_epas_sxol_etos_eidik=e3.id_epas_sxol_etos_eidik and e3.id_epas_sxol_etos='" + Integer.toString(lForm.getId_epas_sxol_etos()) + "'"));
    		lForm.setId_kathigites_epas_sxol_etos_mathimata(0);
    		String mess=new String("anath_math"); 
            s.setAttribute("mess", mess);              
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria");   
    	}else if(action.equals("add_math"))
    	{
    		kathigitesForm lForm=(kathigitesForm)s.getAttribute("form"); 
    		lForm.setId_epas_sxol_etos_eidik_tax_mathimata(Integer.parseInt(request.getParameter("id_epas_sxol_etos_eidik_tax_mathimata")));
    		List<String> pedia=new ArrayList<String>(); 
    		pedia.add("Id_epas_sxol_etos_eidik_tax_mathimata");
    		pedia.add("Id_kathigites_epas_sxol_etos");
    		String table="kathigites_epas_sxol_etos_mathima";
    		int id_ekpeyd_epas_etos=dbname.insert(table, pedia, lForm);
    		lForm.setIn_maths(dbname.fillMap("select e1.id_epas_sxol_etos_eidik_tax_mathimata, concat(e.perigrafi,\" \",e1.type) from epas_sxol_etos_eidik_tax_mathimata as e1 LEFT JOIN  kathigites_epas_sxol_etos_mathima as kesem ON kesem.id_epas_sxol_etos_eidik_tax_mathimata=e1.id_epas_sxol_etos_eidik_tax_mathimata, mathimata as e,epas_sxol_etos_eidik_tax as e2, epas_sxol_etos_eidik as e3 where kesem.id_kathigites_epas_sxol_etos is null and e1.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e1.id_mathima=e.id_mathima and e2.id_epas_sxol_etos_eidik=e3.id_epas_sxol_etos_eidik and e3.id_epas_sxol_etos='" + Integer.toString(lForm.getId_epas_sxol_etos()) + "'"));
    		lForm.setKath_maths(dbname.fillMap("select e1.id_epas_sxol_etos_eidik_tax_mathimata, concat(e.perigrafi,\" \",e1.type) from kathigites_epas_sxol_etos_mathima as kesem, epas_sxol_etos_eidik_tax_mathimata as e1, mathimata as e,epas_sxol_etos_eidik_tax as e2, epas_sxol_etos_eidik as e3 where  kesem.id_epas_sxol_etos_eidik_tax_mathimata=e1.id_epas_sxol_etos_eidik_tax_mathimata AND e1.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e1.id_mathima=e.id_mathima and e2.id_epas_sxol_etos_eidik=e3.id_epas_sxol_etos_eidik and e3.id_epas_sxol_etos='" + Integer.toString(lForm.getId_epas_sxol_etos()) + "'"));
    		String mess=new String("anath_math");  
            s.setAttribute("mess", mess);               
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria");  
            
    	}else if(action.equals("math_del"))
    	{
    		kathigitesForm lForm=(kathigitesForm)s.getAttribute("form");  
    		String id=request.getParameter("id");
    		try{
    			dbname.deleteRecord("delete from kathigites_epas_sxol_etos_mathima where id_kathigites_epas_sxol_etos='" + lForm.getId_kathigites_epas_sxol_etos() + "' and id_epas_sxol_etos_eidik_tax_mathimata='" + id +"'" );
    		}catch(Exception e){
    			
    		}
    		lForm.setIn_maths(dbname.fillMap("select e1.id_epas_sxol_etos_eidik_tax_mathimata, concat(e.perigrafi,\" \",e1.type) from epas_sxol_etos_eidik_tax_mathimata as e1 LEFT JOIN  kathigites_epas_sxol_etos_mathima as kesem ON kesem.id_epas_sxol_etos_eidik_tax_mathimata=e1.id_epas_sxol_etos_eidik_tax_mathimata, mathimata as e,epas_sxol_etos_eidik_tax as e2, epas_sxol_etos_eidik as e3 where kesem.id_kathigites_epas_sxol_etos is null and e1.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e1.id_mathima=e.id_mathima and e2.id_epas_sxol_etos_eidik=e3.id_epas_sxol_etos_eidik and e3.id_epas_sxol_etos='" + Integer.toString(lForm.getId_epas_sxol_etos()) + "'"));
    		lForm.setKath_maths(dbname.fillMap("select e1.id_epas_sxol_etos_eidik_tax_mathimata, concat(e.perigrafi,\" \",e1.type) from kathigites_epas_sxol_etos_mathima as kesem, epas_sxol_etos_eidik_tax_mathimata as e1, mathimata as e,epas_sxol_etos_eidik_tax as e2, epas_sxol_etos_eidik as e3 where  kesem.id_epas_sxol_etos_eidik_tax_mathimata=e1.id_epas_sxol_etos_eidik_tax_mathimata AND e1.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e1.id_mathima=e.id_mathima and e2.id_epas_sxol_etos_eidik=e3.id_epas_sxol_etos_eidik and e3.id_epas_sxol_etos='" + Integer.toString(lForm.getId_epas_sxol_etos()) + "'"));
    		String mess=new String("anath_math");  
            s.setAttribute("mess", mess);               
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria");  
            
    	}
    	else if(action.equals("edit"))
    	{
    		kathigitesForm lForm=new kathigitesForm();
        	int id_epilogi=Integer.parseInt(((element)p.getViews().getActive()).getOnoma());
        	lForm.setId_kathigites_epas_sxol_etos(dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.toString(id_epilogi) +"'"));
    		lForm.setId_epas_sxol_etos(dbname.getId("select id_epas_sxol_etos from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" + Integer.toString(lForm.getId_kathigites_epas_sxol_etos()) +"'"));
        	lForm.setId_ekpaideyti(dbname.getId("select id_ekpaideyti from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" + Integer.toString(lForm.getId_kathigites_epas_sxol_etos()) +"'"));
        	lForm.setIs_ypal(dbname.getId("select is_ypal from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" + Integer.toString(lForm.getId_kathigites_epas_sxol_etos()) +"'"));
        	try{
    			lForm.setNid_katigoria((Map<String,String>)dbname.fillMap("select id_ekpaideyseis, onoma from kat_ekpedeyseis"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_eidikotita((Map<String,String>)dbname.fillMap("select id_eidikotita, perigrafi from eidikotites"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_proslipsis((Map<String,String>)dbname.fillMap("select id_proslipsis, perigrafi from pros_kathig_epas"));
    		}catch(Exception e){}
    		try{
    			Map<String,String> pol=new LinkedHashMap<String, String>();  
    			pol.put("1","Όχι"); 
    			pol.put("2","Ναι");  
    			lForm.setPol(pol);  
    		}catch(Exception e){}
    		try{
    			Map<String,String> sex=new LinkedHashMap<String, String>();
    			sex.put("1","Άνδρας"); 
    			sex.put("2","Γυναίκα");  
    			lForm.setNsex(sex); 
    		}catch(Exception e){} 
    		try{
    			
    			lForm.setForeis((Map<String,String>)dbname.fillMap("select f.id_kentro, concat(k.onoma,\" \",f.onoma) from foreis as f, kat_foreon as k where f.id_katigorias=k.id_katigorias or f.id_katigorias is null order by k.onoma,f.onoma"));
    		}catch(Exception e){}
    		String table=null;
    		List<String> pedia=new ArrayList<String>();
    		pedia.add("setOnoma");
    		pedia.add("setEpwnymo");
    		pedia.add("setPatrwnymo");
    		pedia.add("setEmail");    
    		pedia.add("setPhd");
    		pedia.add("setMsc");
    		pedia.add("setTilefono"); 
    		pedia.add("setAddress");
    		pedia.add("setAfm");
    		pedia.add("setAr_taytotitas");
    		pedia.add("setId_ekpaideyseis"); 
    		pedia.add("setPoli");
    		pedia.add("setZip");
    		pedia.add("setLogariasmos");
			pedia.add("setId_eidikotita"); 
    		int id=0;
    		id=lForm.getId_ekpaideyti();
    		if(lForm.getIs_ypal()==1)
    		{
    			//υπάλληλος 
    			table="ipalliloi"; 
    			try{
    				dbname.edit(table, pedia, lForm, id,"id_name");
    				lForm.setId_forea(dbname.getId("select id_kentro from foreis_ipalliloi where id_name='" + Integer.toString(lForm.getId_ekpaideyti()) +"'"));
    				//JOptionPane.showMessageDialog(null,Integer.toString(lForm.getId_forea()));
    				
    			}catch(Exception e){ 
    				//JOptionPane.showMessageDialog(null, "yp"+e.getMessage());
    			}
    		}else{
    			table="ekpedeytes";
    			try{
    				dbname.edit(table, pedia, lForm,id,"id_ekpaideyti");
    			}catch(Exception e){
    				//JOptionPane.showMessageDialog(null, "or"+e.getMessage());
    			}
    			 
    		}
    		String mess=new String("kathigites_keni");
    		s.setAttribute("mess", mess);            
    		s.setAttribute("form",lForm);
    		return new ActionForward(".kyria");  
    		 
    	}else if(action.equals("save"))
    	{
    		kathigitesForm lForm=null;
    		try{
    			lForm=(kathigitesForm)form;
    			lForm.setId_ekpaideyti(((kathigitesForm)s.getAttribute("form")).getId_ekpaideyti());
    			lForm.setIs_ypal(((kathigitesForm)s.getAttribute("form")).getIs_ypal());
    			lForm.setId_kathigites_epas_sxol_etos(((kathigitesForm)s.getAttribute("form")).getId_kathigites_epas_sxol_etos());
    		}catch(Exception e){}
    		String table=null;   
    		List<String> pedia=new ArrayList<String>();  
    		pedia.add("Onoma"); 
    		pedia.add("Epwnymo");
    		pedia.add("Patrwnymo");
    		pedia.add("Email");     
    		pedia.add("Phd");     
    		pedia.add("Msc");
    		pedia.add("Tilefono"); 
    		pedia.add("Address");
    		pedia.add("Afm");
    		pedia.add("Ar_taytotitas");  
    		pedia.add("Id_ekpaideyseis");   
    		pedia.add("Poli");
    		pedia.add("Zip");
    		pedia.add("Logariasmos");
			pedia.add("Id_eidikotita");
    		int id_kathigiti=0;		
    		if(lForm.getIs_ypal()==0)
    		{
    			table="ekpedeytes";              
    			try{
    				id_kathigiti=dbname.save(table,pedia,lForm,lForm.getId_ekpaideyti(),"id_ekpaideyti");
    					
    			}catch(Exception e){
    				
    			}
    			
    		}else{
    			table="ipalliloi";
    			try{
    				id_kathigiti=dbname.save(table, pedia, lForm, lForm.getId_ekpaideyti(), "id_name");
    				
    				if(((kathigitesForm)s.getAttribute("form")).getId_forea()>0)
    				{
    					if(lForm.getId_forea()>0)
    						dbname.updateRecord("update foreis_ipalliloi set id_kentro='" + Integer.toString(lForm.getId_forea()) + "' where id_name='" + Integer.toString(lForm.getId_ekpaideyti()) +"'" );
    				}else if(lForm.getId_forea()>0){
    					dbname.insertrecord("insert into foreis_ipalliloi(id_name,id_kentro) values('" + Integer.toString(lForm.getId_ekpaideyti()) + "','" + Integer.toString(lForm.getId_forea()) +"')") ;
    				}
    				
    			}catch(Exception e){
    				                     
    			}
    		} 
    		try{
    			lForm.setNid_katigoria((Map<String,String>)dbname.fillMap("select id_ekpaideyseis, onoma from kat_ekpedeyseis"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_eidikotita((Map<String,String>)dbname.fillMap("select id_eidikotita, perigrafi from eidikotites"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_proslipsis((Map<String,String>)dbname.fillMap("select id_proslipsis, perigrafi from pros_kathig_epas"));
    		}catch(Exception e){}
    		try{
    			Map<String,String> pol=new LinkedHashMap<String, String>();  
    			pol.put("1","Όχι"); 
    			pol.put("2","Ναι");  
    			lForm.setPol(pol); 
    		}catch(Exception e){} 
    		try{
    			Map<String,String> sex=new LinkedHashMap<String, String>();
    			sex.put("1","Άνδρας"); 
    			sex.put("2","Γυναίκα");  
    			lForm.setNsex(sex); 
    		}catch(Exception e){} 
    		try{
    			lForm.setForeis((Map<String,String>)dbname.fillMap("select f.id_kentro, concat(k.onoma,\" \",f.onoma) from foreis as f, kat_foreon as k where f.id_katigorias=k.id_katigorias or f.id_katigorias is null order by k.onoma,f.onoma"));
    			//JOptionPane.showMessageDialog(null,Integer.toString(lForm.getId_forea()));
    		}catch(Exception e){
    			//JOptionPane.showMessageDialog(null,"exp"+Integer.toString(lForm.getId_forea())); 
    		}
    		
    		if(!(((kathigitesForm)s.getAttribute("form")).getEpwnymo().equals(lForm.getEpwnymo()) && ((kathigitesForm)s.getAttribute("form")).getOnoma().equals(lForm.getOnoma())))
    		{
    			dbname.updateRecord("update kathigites_epas_sxol_etos set perigrafi=\"" +lForm.getEpwnymo() + " " +lForm.getOnoma() + "\" where id_kathigites_epas_sxol_etos='" +lForm.getId_kathigites_epas_sxol_etos() + "'");
    			dbname.updateprofile(p,s,request);
    		}    
    		String mess=new String("kathigites_keni");
    		s.setAttribute("mess", mess);            
    		s.setAttribute("form",lForm);
    		return new ActionForward(".kyria");   
    		
        	
    	}else if(action.equals("delete"))
    	{
    		int id_epilogi=Integer.parseInt(((element)p.getViews().getActive()).getOnoma());
    		int Id_kathigites_epas_sxol_etos=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + Integer.toString(id_epilogi) +"'");
    		int id=0;
    		try{
    			id=dbname.getId("select count(*) from orologio_tmima where id_kathigites_epas_sxol_etos='" +Integer.toString(Id_kathigites_epas_sxol_etos) + "'");
    		}catch(Exception e){} 
    		if(id<1)
    		{
    			try{ 
    				id=dbname.getId("select count(*) from parusiol_kathig where id_kathigites_epas_sxol_etos='" +Integer.toString(Id_kathigites_epas_sxol_etos) + "'");
    			}catch(Exception e){}
    		}
        	if(id<1)
    		{
    			try{
        			if(dbname.deleteEpilogi(id_epilogi)){ 
        				dbname.updateprofile(p,s,request);
        				dbname.deleteRecord("delete from kathigites_epas_sxol_etos where id_kathigites_epas_sxol_etos='" + Integer.toString(Id_kathigites_epas_sxol_etos) + "'"); 
        			}
        			    
        		}catch(Exception e)
        		{}
        		return new ActionForward(".kyria"); 
    			
    			
    		}
    			
    	}
		return new ActionForward(".kyria");
    }
		
}
