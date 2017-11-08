package actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ayiuda.db;
import beans.element;
import beans.profile;
import forms.mathitesForm;


public class mathites extends Action {
	
	
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
        db<mathitesForm> dbname=null;
        dbname=new db<mathitesForm>();
        s.setAttribute("db", dbname);
        String action=request.getParameter("action");
    	if(action.equals("keni"))
    	{
    		mathitesForm lForm=(mathitesForm)form;
    		if(lForm.getId_mathiti()!=0)
        		lForm=new mathitesForm();
    		//βρες id epilogis sto tmima
    		int id_epilogi_tmima=dbname.getId("select ypagete from view_epilogi where id_epilogi='" + Integer.parseInt(((element)p.getViews().getActive()).getOnoma()) +"'");
    		//bres id_tmima
    		lForm.setId_epas_sxol_etos_eidik_tax_tmima(dbname.getId("select id_pediu from view_epilogi where id_epilogi='" + id_epilogi_tmima +"'"));
    		lForm.setId_epas_sxol_etos_eidik_tax(dbname.getId("select id_epas_sxol_etos_eidik_tax from epas_sxol_etos_eidik_tax_tmima where id_epas_sxol_etos_eidik_tax_tmima='" + lForm.getId_epas_sxol_etos_eidik_tax_tmima() +"'"));
    		String mess=new String("mathites_keni");
    		try{
    			lForm.setNid_thriskeyma((Map<String,String>)dbname.fillMap("select id_thriskeyma, perigrafi from thriskeyma"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_ypikootita((Map<String,String>)dbname.fillMap("select id_ypikootita, perigrafi from ypikootita"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_epaggelma_patera((Map<String,String>)dbname.fillMap("select id_epaggelmata, perigrafi from epaggelmata"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_nomos((Map<String,String>)dbname.fillMap("select id_nomos, onoma from nomoi"));
    		}catch(Exception e){}
    		try{
    			Map<String,String> sex=new LinkedHashMap<String, String>();
    			sex.put("1","Άνδρας"); 
    			sex.put("2","Γυναίκα");  
    			lForm.setNsex(sex); 
    		}catch(Exception e){} 
    		s.setAttribute("mess", mess);            
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria"); 
    		
        }else if(action.equals("add")){ 
    		mathitesForm lForm=(mathitesForm)form;
    		ActionErrors errors = lForm.validate(mapping, request);
    		s.setAttribute("lathi", errors);
    		if(!errors.isEmpty()) 
            {
                s.setAttribute("form", lForm);
                String mess=new String("mathites_lathi");
                s.setAttribute("mess", mess);
                return new ActionForward(".kyria");
                 
            }
    		String table="mathiti";
    		List<String> pedia=new ArrayList<String>(); 
    		pedia.add("Onoma");
    		pedia.add("Epwnymo");
    		pedia.add("Topos_gennisis");
    		pedia.add("Onoma_patera");
    		pedia.add("Onoma_miteras");
    		pedia.add("Epwnymo_patera");
    		pedia.add("Epwnymo_miteras");
    		pedia.add("Genos");
    		pedia.add("Dimos");
    		pedia.add("Id_nomos");
    		pedia.add("Sex");
    		if(lForm.getBirthday()!=null)
    			pedia.add("Birthday");
    		pedia.add("Id_epaggelma_patera");
    		pedia.add("Id_ypikootita");
    		pedia.add("Id_thriskeyma");
    		pedia.add("Onoma_kidemona");
    		pedia.add("Epwnymo_kidemona"); 
    		pedia.add("Address");
    		pedia.add("Id_poli_diamoni"); 
    		pedia.add("Tilefono"); 
    		pedia.add("Mobile");
    		pedia.add("Zip");
    		int id_mathiti=0;  
    		try{
    			id_mathiti=dbname.insert(table, pedia, lForm); 
    		}catch(Exception e){
    			JOptionPane.showMessageDialog(null, e.getMessage());
    			id_mathiti=0;
    		} 
    		if(id_mathiti==0)
    		{
    			id_mathiti=dbname.getId("select id_mathiti from mathiti where onoma=\"" +lForm.getOnoma() + "\" and epwnymo=\"" + lForm.getEpwnymo() + "\"");
    			
    			int id_epas_sxol_etos_eidik=0;
        		int id=0;
        		try{
        			id_epas_sxol_etos_eidik=dbname.getId("select id_epas_sxol_etos_eidik from epas_sxol_etos_eidik_tax where id_epas_sxol_etos_eidik_tax='"+Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax()) + "'");
        			id=dbname.getId("select et.id_epas_sxol_etos_eidik from epas_sxol_etos_eidik_tax as et, mathites_taxi as e2 where et.id_epas_sxol_etos_eidik='" +Integer.toString(id_epas_sxol_etos_eidik)+"' and et.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e2.id_mathiti='" +Integer.toString(id_mathiti) + "'");
        		}catch(Exception e){}
        		if(id>0) 
        			return new ActionForward(".kyria");
    			 
    		}
    		lForm.setId_mathiti(id_mathiti);
    		table="mathites_taxi";
    		pedia=null;
    		pedia=new ArrayList<String>();
    		pedia.add("Id_epas_sxol_etos_eidik_tax");  
    		pedia.add("Id_mathiti");
    		lForm.setId_mathiti_taxi(dbname.insert(table,pedia,lForm)); 
    		lForm.setPerigrafi(lForm.getEpwnymo() + " " + lForm.getOnoma()); 
    		table="mathites_tmima"; 
    		pedia=null;  
    		pedia=new ArrayList<String>();
    		pedia.add("Id_epas_sxol_etos_eidik_tax_tmima"); 
    		pedia.add("Id_mathiti_taxi");
    		pedia.add("Perigrafi");
    		int id_mathiti_tmima=dbname.insert(table,pedia, lForm);
    		int id_epilogi_mathiti_tmima=dbname.add_epilogi(Integer.parseInt(((element)p.getViews().getActive()).getOnoma()), "mathites_tmima","id_mathiti_tmima",id_mathiti_tmima);//
    		dbname.add_epilogi_action(p.getUser(),id_epilogi_mathiti_tmima,22,12);
    		dbname.add_epilogi_action(p.getUser(),id_epilogi_mathiti_tmima,23,10);
    		dbname.add_epilogi_action(p.getUser(),id_epilogi_mathiti_tmima,24,11);
    		dbname.updateprofile(p,s,request);
    		return new ActionForward(".kyria");
        }else if(action.equals("edit")){
    		//apo tin epilogi pare id mathiti
        	mathitesForm lForm=new mathitesForm();
        	int id_epilogi=Integer.parseInt(((element)p.getViews().getActive()).getOnoma());
    		int id_mathiti_tmima=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" +Integer.toString(id_epilogi)+ "'");
    		int id_mathiti_taxi=dbname.getId("select id_mathiti_taxi from mathites_tmima where id_mathiti_tmima='" + Integer.toString(id_mathiti_tmima) +"'");
        	int id_mathiti=dbname.getId("select id_mathiti from mathites_taxi where id_mathiti_taxi='" + Integer.toString(id_mathiti_taxi) +"'");
        	lForm.setId_mathiti(id_mathiti);
        	lForm.setId_mathiti_taxi(id_mathiti_taxi);
        	lForm.setId_mathiti_tmima(id_mathiti_tmima);
        	//lForm.setId_epas_sxol_etos_eidik_tax_tmima(dbname.getId(""));
        	
        	String table="mathiti";
    		List<String> pedia=new ArrayList<String>(); 
    		pedia.add("setOnoma");
    		pedia.add("setEpwnymo");
    		pedia.add("setTopos_gennisis");
    		pedia.add("setOnoma_patera");
    		pedia.add("setOnoma_miteras");
    		pedia.add("setEpwnymo_patera");
    		pedia.add("setEpwnymo_miteras"); 
    		pedia.add("setGenos");
    		pedia.add("setDimos");
    		pedia.add("setId_nomos");
    		pedia.add("setSex"); 
    		if(lForm.getBirthday()!=null)
    			pedia.add("Birthday");
    		pedia.add("setId_epaggelma_patera");
    		pedia.add("setId_ypikootita");
    		pedia.add("setId_thriskeyma");
    		pedia.add("setOnoma_kidemona");
    		pedia.add("setEpwnymo_kidemona"); 
    		pedia.add("setAddress");
    		pedia.add("setId_poli_diamoni"); 
    		pedia.add("setTilefono");
    		pedia.add("setMobile");
    		pedia.add("setZip");
    		try
        	{
        		if(dbname.edit(table, pedia, lForm, id_mathiti,"no"))
        		{
        			try{
            			lForm.setNid_thriskeyma((Map<String,String>)dbname.fillMap("select id_thriskeyma, perigrafi from thriskeyma"));
            		}catch(Exception e){}
            		try{
            			lForm.setNid_ypikootita((Map<String,String>)dbname.fillMap("select id_ypikootita, perigrafi from ypikootita"));
            		}catch(Exception e){}
            		try{
            			lForm.setNid_epaggelma_patera((Map<String,String>)dbname.fillMap("select id_epaggelmata, perigrafi from epaggelmata"));
            		}catch(Exception e){}
            		try{
            			lForm.setNid_nomos((Map<String,String>)dbname.fillMap("select id_nomos, onoma from nomoi"));
            		}catch(Exception e){}
            		try{
            			Map<String,String> sex=new LinkedHashMap<String, String>();
            			sex.put("1","Άνδρας"); 
            			sex.put("2","Γυναίκα"); 
            			lForm.setNsex(sex); 
            		}catch(Exception e){}
        		}
        		String mess=new String("mathites_keni");
        		s.setAttribute("mess", mess);
        		s.setAttribute("form", lForm);
        	}catch(Exception e){
        		JOptionPane.showMessageDialog(null, e.getMessage()); 
        	}
    		return new ActionForward(".kyria");
    			
    			
    			
    			
        }else if(action.equals("query")){ 
    		StringBuffer sb=new StringBuffer(1024); 
    		String epwnymo=null;
    		mathitesForm lForm=(mathitesForm)s.getAttribute("form");
    		try{
    			epwnymo=request.getParameter("epwnymo"); 
    		}catch(Exception e){} 
    		epwnymo=epwnymo.toLowerCase();
            epwnymo=Character.toString(epwnymo.charAt(0)).toUpperCase()+epwnymo.substring(1);
            Map<String,String> lst=null;
    		lst=dbname.fillMap("select id_mathiti,concat(epwnymo,' ',onoma) from mathiti where epwnymo like \"" + epwnymo + "%\" order by epwnymo;");
    		if(lst.size()>0)
    		{
    			//JOptionPane.showMessageDialog(null,"2");
    			for (Map.Entry<String,String> entry : lst.entrySet()) 
    				sb.append(entry.getKey()+ "," +entry.getValue()+"\n");
    			out.println(sb.toString());
    			out.flush();
    		} 
    	    return null;	
    	}else if(action.equals("insert")){
    		//υπάρχουν τα στοιχεία του μαθητή, ενημερώνεται μόνο
    		mathitesForm lForm=(mathitesForm)s.getAttribute("form");
    		lForm.setId_mathiti(Integer.parseInt(request.getParameter("id")));
    		//an o μαθητής δεν φοιτά την ίδια σχολική χρονιά τότε να μπει 
    		int id_epas_sxol_etos_eidik=0; 
    		int id=0;
    		try{
    			id_epas_sxol_etos_eidik=dbname.getId("select id_epas_sxol_etos_eidik from epas_sxol_etos_eidik_tax where id_epas_sxol_etos_eidik_tax='"+Integer.toString(lForm.getId_epas_sxol_etos_eidik_tax()) + "'");
    		    id=dbname.getId("select et.id_epas_sxol_etos_eidik from epas_sxol_etos_eidik_tax as et, mathites_taxi as e2 where et.id_epas_sxol_etos_eidik='" +Integer.toString(id_epas_sxol_etos_eidik)+"' and et.id_epas_sxol_etos_eidik_tax=e2.id_epas_sxol_etos_eidik_tax and e2.id_mathiti='" +Integer.toString(lForm.getId_mathiti()) + "'");
    		}catch(Exception e){
    			//JOptionPane.showMessageDialog(null, Integer.toString(id));
    		}	
    		//JOptionPane.showMessageDialog(null, Integer.toString(id)); 
    		if(id<1)
    		{
    			String table="mathites_taxi";
    			List<String> pedia=new ArrayList<String>();  
    			pedia.add("Id_epas_sxol_etos_eidik_tax");  
    			pedia.add("Id_mathiti"); 
    			int id_mathiti_taxi=0;   
    			try{
    				id_mathiti_taxi=dbname.insert(table,pedia,lForm); 
    			}catch(Exception e){}
    			if(id_mathiti_taxi>0){ 
    				lForm.setId_mathiti_taxi(id_mathiti_taxi); 
    				lForm.setPerigrafi(dbname.getName("select concat(epwnymo,' ',onoma) from mathiti where id_mathiti='" +Integer.toString(lForm.getId_mathiti())+"'")); 
    				table="mathites_tmima"; 
    				pedia=null;      
    				pedia=new ArrayList<String>();
    				pedia.add("Id_epas_sxol_etos_eidik_tax_tmima"); 
    				pedia.add("Id_mathiti_taxi");
    				pedia.add("Perigrafi");
    				int id_mathiti_tmima=dbname.insert(table,pedia, lForm);
    				int id_epilogi_mathiti_tmima=dbname.add_epilogi(Integer.parseInt(((element)p.getViews().getActive()).getOnoma()), "mathites_tmima","id_mathiti_tmima",id_mathiti_tmima);//
    				dbname.add_epilogi_action(p.getUser(),id_epilogi_mathiti_tmima,22,12);
    				dbname.add_epilogi_action(p.getUser(),id_epilogi_mathiti_tmima,23,10);
    				dbname.add_epilogi_action(p.getUser(),id_epilogi_mathiti_tmima,24,11);
    				dbname.updateprofile(p,s,request);
    				out.println("ok");
    				out.flush();
    				return null;
    			}
			}else{
				out.println("Δεν μπορεί να μπει γιατί σπουδάζει");
				out.flush();
				return null;
			}
    		 
    	}else if(action.equals("delete"))
        {
    		int id_epilogi=Integer.parseInt(((element)p.getViews().getActive()).getOnoma());
    		int id_mathiti_tmima=dbname.getId("select id_pediu from view_epilogi where id_epilogi='" +Integer.toString(id_epilogi)+ "'");
    		int id_mathiti_taxi=dbname.getId("select id_mathiti_taxi from mathites_tmima where id_mathiti_tmima='" + Integer.toString(id_mathiti_tmima) +"'");
    		//dbname.deleteRecord("delete from mathites_tmima where id_mathiti_tmima='" + Integer.toString(id_mathiti_tmima) + "'");
    		
    		try{
    			if(dbname.deleteEpilogi(id_epilogi)){
    				dbname.updateprofile(p,s,request);
    				dbname.deleteRecord("delete from mathites_taxi where id_mathiti_taxi='" + Integer.toString(id_mathiti_taxi) + "'"); 
    			}
    			    
    		}catch(Exception e)
    		{
    			//JOptionPane.showMessageDialog(null, e.getMessage()); 
    		}
    		return new ActionForward(".kyria");
    		
    		
        }else if(action.equals("save")){ 
    		mathitesForm lForm=(mathitesForm)form;
    		lForm.setId_mathiti(((mathitesForm)s.getAttribute("form")).getId_mathiti());
    		ActionErrors errors = lForm.validate(mapping, request);
    		s.setAttribute("lathi", errors);
    		if(!errors.isEmpty())
            {
                s.setAttribute("form", lForm); 
                String mess=new String("mathites_lathi");
                s.setAttribute("mess", mess);
                return new ActionForward(".kyria");   
                 
            }
    		String table="mathiti";
    		List<String> pedia=new ArrayList<String>(); 
    		pedia.add("Onoma");
    		pedia.add("Epwnymo");
    		pedia.add("Topos_gennisis");
    		pedia.add("Onoma_patera");
    		pedia.add("Onoma_miteras");
    		pedia.add("Epwnymo_patera");
    		pedia.add("Epwnymo_miteras");
    		pedia.add("Genos");
    		pedia.add("Dimos"); 
    		pedia.add("Id_nomos");
    		pedia.add("Sex"); 
    		if(lForm.getBirthday()!=null)
    			pedia.add("Birthday");
    		pedia.add("Id_epaggelma_patera");
    		pedia.add("Id_ypikootita");
    		pedia.add("Id_thriskeyma");
    		pedia.add("Onoma_kidemona");
    		pedia.add("Epwnymo_kidemona"); 
    		pedia.add("Address");
    		pedia.add("Id_poli_diamoni"); 
    		pedia.add("Tilefono"); 
    		pedia.add("Mobile");
    		pedia.add("Zip");
    		int id_mathiti=0;  
    		try{
    			//JOptionPane.showMessageDialog(null,Integer.toString(lForm.getId_mathiti()));
    			id_mathiti=dbname.save(table, pedia, lForm,lForm.getId_mathiti(),"no"); 
    			
    		}catch(Exception e){
    			//JOptionPane.showMessageDialog(null, e.getMessage()+"EDO"+Integer.toString(id_mathiti)); 
    			
    		}
    		
    		try{
    			lForm.setNid_thriskeyma((Map<String,String>)dbname.fillMap("select id_thriskeyma, perigrafi from thriskeyma"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_ypikootita((Map<String,String>)dbname.fillMap("select id_ypikootita, perigrafi from ypikootita"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_epaggelma_patera((Map<String,String>)dbname.fillMap("select id_epaggelmata, perigrafi from epaggelmata"));
    		}catch(Exception e){}
    		try{
    			lForm.setNid_nomos((Map<String,String>)dbname.fillMap("select id_nomos, onoma from nomoi")); 
    		}catch(Exception e){}
    		try{
    			Map<String,String> sex=new LinkedHashMap<String, String>();
    			sex.put("1","Άνδρας"); 
    			sex.put("2","Γυναίκα");    
    			lForm.setNsex(sex); 
    		}catch(Exception e){} 
    		if(!(((mathitesForm)s.getAttribute("form")).getEpwnymo().equals(lForm.getEpwnymo()) && ((mathitesForm)s.getAttribute("form")).getOnoma().equals(lForm.getOnoma())))
    		{
    			dbname.updateRecord("update mathites_tmima inner join mathites_taxi ON (mathites_taxi.id_mathiti_taxi=mathites_tmima.id_mathiti_taxi) set perigrafi=\"" +lForm.getEpwnymo() + " " +lForm.getOnoma() + "\" where mathites_taxi.id_mathiti='" +lForm.getId_mathiti() + "'");
    			dbname.updateprofile(p,s,request);
    		} 
    		
    		s.setAttribute("form", lForm); 
    		String mess=new String("mathites_keni");
    		s.setAttribute("mess", mess);
    		
    		 
        } 
    		  
         
        return new ActionForward(".kyria"); 
        
        
          
        
    }
	
	
	
	
	
}
