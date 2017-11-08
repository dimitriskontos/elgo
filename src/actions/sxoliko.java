package actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;
import ayiuda.*;
import forms.sxolikoForm;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.JOptionPane;
import org.apache.struts.action.ActionErrors;

public class sxoliko extends Action {

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
        db<sxolikoForm> dbname=null;
        dbname=new db<sxolikoForm>();
        s.setAttribute("db", dbname);
        //}
        String action=request.getParameter("action");
    	if(action.equals("keni"))
    	{
    		sxolikoForm lForm=(sxolikoForm)form;
    		if(lForm.getId_epas_sxol_etos()!=0)
        		lForm=new sxolikoForm();
    		lForm.setId_kentro(dbname.getId("select id_kentro from foreis_ipalliloi where id_name='"+p.getUser()  + "' and ews is NULL"));
    		lForm.setName(dbname.getName("select onoma from foreis where id_kentro='" + lForm.getId_kentro() +"'"));   
    		lForm.setId_name(p.getUser());
    		try{
    			lForm.setN_sxolika_eti((Map<String,String>)dbname.fillMap("select id_sxol_etos, sxol_etos from sxol_etos"));
    		}catch(Exception e){}
    		try{
    			lForm.setN_eidikotites((Map<String,String>)dbname.fillMap("select id_eidikotita, perigrafi from eidikotites"));
    		}catch(Exception e){}
    		String mess=new String("sxoliko_keni");
            s.setAttribute("mess", mess);            
            s.setAttribute("form",lForm);
            return new ActionForward(".kyria");
    	}else if(action.equals("add")){ 
    		sxolikoForm lForm=(sxolikoForm)form;
    		ActionErrors errors = lForm.validate(mapping, request);
    		s.setAttribute("lathi", errors);
    		String sql=null;
    		if(!errors.isEmpty())
            {
                s.setAttribute("form", lForm);
                String mess=new String("sxoliko_lathi");
                s.setAttribute("mess", mess);
                return new ActionForward(".kyria");
                 
            }
    		String table="epas_sxol_etos";
    		List<String> pedia=new ArrayList<String>(); 
    		
    		pedia.add("Id_kentro");
    		pedia.add("Id_sxol_etos");
    		pedia.add("Id_name");
    		pedia.add("Perigrafi");
    		int id_epas_sxol_etos=-1;
    		int id_epas_sxol_etos_eidik=-1;
    		int id_epas_sxol_etos_eidik_taxA=-1;
    		int id_epas_sxol_etos_eidik_taxB=-1;
    		int id_epas_sxol_etos_eidik_tax_tmima=-1;
    		int id_epilogi_etos=-1;
    		int id_epilogi_etos_eidik=-1;
    		int id_epilogi_kathigites=-1; 
    		int id_epilogi_mathites=-1;
    		int id_epilogi_orologio=-1;
    		int id_epilogi_taxi=-1;
    		int id_epilogi_tmima=-1;  
    		try{
    			if(!dbname.exist("select * from epas_sxol_etos where id_kentro='" + lForm.getId_kentro() + "' and id_sxol_etos='" + lForm.getId_sxol_etos() + "'"))
    			{
    				
    				lForm.setPerigrafi(lForm.getName()+" " + dbname.getName("select sxol_etos from sxol_etos where id_sxol_etos='" + lForm.getId_sxol_etos() +"'"));
    				//ΞµΞΉΟƒΞ±Ξ³Ο‰Ξ³Ξ® ΟƒΟ„ΞΏΞ½ epas_sxol_etos 
    				id_epas_sxol_etos=dbname.insert(table,pedia,lForm);
    				//eisagogh ston view epilogi
    				id_epilogi_etos=dbname.add_epilogi(Integer.parseInt(((element)p.getViews().getActive()).getOnoma()),"epas_sxol_etos","id_epas_sxol_etos",id_epas_sxol_etos);
    				//syndesi kathigiton
        			id_epilogi_kathigites=dbname.add_epilogi(id_epilogi_etos, "dummies","id",2);	
        			//menu GIA KATHIGITES
        			dbname.add_epilogi_action(p.getUser(), id_epilogi_kathigites,17 , 5);
        			//enimerosi gia update, delete tou id_epilogi_etos ston dieythinti
    				dbname.add_epilogi_action(p.getUser(), id_epilogi_etos,15 , 4); 
    				dbname.add_epilogi_action(p.getUser(), id_epilogi_etos,16 , 3);
    			}else{
    				//prepei na parume to id_epas_sxol_etos
    				id_epas_sxol_etos=dbname.getId("select id_epas_sxol_etos from epas_sxol_etos where id_kentro='" + lForm.getId_kentro() + "' and id_sxol_etos='" + lForm.getId_sxol_etos() + "'");
    				//Ο€Ξ¬Ο�Ξµ Ο„ΞΏ id_epilogi_etos Ο€ΞΏΟ… ΞΈΞ± Ο…Ο€Ξ¬Ξ³ΞµΟ„Ξ±ΞΉ 
    				id_epilogi_etos=dbname.getId("select id_epilogi from view_epilogi where pinakas=\"" +"epas_sxol_etos" +"\" and pedio=\"" + "id_epas_sxol_etos" + "\" and id_pediu='" +  Integer.toString(id_epas_sxol_etos) + "\'");
    				
    			}
    			
    			if(!dbname.exist("select id_epas_sxol_etos_eidik from epas_sxol_etos_eidik where id_epas_sxol_etos='" + Integer.toString(id_epas_sxol_etos)+"' and eidikotita='" + Integer.toString(lForm.getEidikotita()) + "'"))
    			{
    				//ΞµΞΉΟƒΞ±Ξ³Ο‰Ξ³Ξ® ΟƒΟ„ΞΏΞ½ epas_sxol_etos_eidik
    				lForm.setId_epas_sxol_etos(id_epas_sxol_etos);
    				lForm.setPerigrafi(dbname.getName("select perigrafi from eidikotites where id_eidikotita='" + Integer.toString(lForm.getEidikotita()) + "'") );
    				table="epas_sxol_etos_eidik";
    				pedia=null;
    				pedia=new ArrayList<String>();
    				pedia.add("Id_epas_sxol_etos"); 
    				pedia.add("Eidikotita"); 
    				pedia.add("Perigrafi");
    				if(lForm.getEnarxi()!=null)
    					pedia.add("Enarxi");
    				if(lForm.getLixi()!=null) 
    					pedia.add("Lixi");
    				id_epas_sxol_etos_eidik=dbname.insert(table,pedia,lForm);
    				//ΞµΞΉΟƒΞ±Ξ³Ο‰Ξ³Ξ® ΟƒΟ„o view_epilogi tou epas_sxol_etos_eidik 
    				id_epilogi_etos_eidik=dbname.add_epilogi(id_epilogi_etos, "epas_sxol_etos_eidik","id_epas_sxol_etos_eidik",id_epas_sxol_etos_eidik);
    				dbname.add_epilogi_action(p.getUser(), id_epilogi_etos_eidik,21 ,9);
    				//ΞµΞΉΟƒΞ±Ξ³Ο‰Ξ³Ξ® ΟƒΟ„ΞΏΞ½ epas_sxol_etos_eidik_tax,epas_sxol_etos_eidik_tax_tmima
    				table="epas_sxol_etos_eidik_tax";
    				lForm.setId_epas_sxol_etos_eidik(id_epas_sxol_etos_eidik);
    				lForm.setTaxi("Ξ¤Ξ¬ΞΎΞ· Ξ‘'"); 
    				pedia=null; 
    				pedia=new ArrayList<String>();
    				pedia.add("Id_epas_sxol_etos_eidik");
    				pedia.add("Taxi");
    				id_epas_sxol_etos_eidik_taxA=dbname.insert(table, pedia, form);
    				//Ο„Ξ¬ΞΎΞ· Ξ‘ ΞΊΞ¬Ο„Ο‰ Ξ±Ο€Ο� id_epilogi_etos_eidik	
    				id_epilogi_taxi=dbname.add_epilogi(id_epilogi_etos_eidik, "dummies","id",6);//
    				dbname.add_epilogi_action(p.getUser(), id_epilogi_taxi,20 , 8);
    				//ΞµΞΉΟƒΞ®Ξ³Ξ±Ξ³Ξµ Ο„Ξ± Ο„ΞΌΞ®ΞΌΞ±Ο„Ξ± Ο„Ξ·Ο‚ Ο„Ξ¬ΞΎΞ·Ο‚ Ξ‘'
    				table="epas_sxol_etos_eidik_tax_tmima";
    				lForm.setId_epas_sxol_etos_eidik_tax(id_epas_sxol_etos_eidik_taxA);
    				for(int i=0;i<lForm.getTmimaA();i++)
    				{
    					lForm.setPerigrafi("Ξ¤ΞΌΞ®ΞΌΞ± " +Integer.toString(i+1));
    					pedia=null;
    					pedia=new ArrayList<String>();
    					pedia.add("Id_epas_sxol_etos_eidik_tax"); 
    					pedia.add("Perigrafi");
    					id_epas_sxol_etos_eidik_tax_tmima=dbname.insert(table, pedia, form);
    					//Ο„ΞΌΞ®ΞΌΞ±Ο„Ξ± ΞΊΞ¬Ο„Ο‰ Ξ±Ο€Ο� Ο„Ξ· Ο„Ξ¬ΞΎΞ· Ξ‘'
    					id_epilogi_tmima=dbname.add_epilogi(id_epilogi_taxi,"epas_sxol_etos_eidik_tax_tmima","id_epas_sxol_etos_eidik_tax_tmima",id_epas_sxol_etos_eidik_tax_tmima);
    					//Ξ�Ξ‘Ξ�Ξ—Ξ¤Ξ�Ο‚ Ξ�Ξ‘Ξ™ Ξ©Ξ΅Ξ�Ξ›Ξ�Ξ“Ξ™Ξ� Ξ“Ξ™Ξ‘ Ξ¤Ξ� Ξ¤Ξ�Ξ‰Ξ�Ξ‘ & MENU
    					id_epilogi_mathites=dbname.add_epilogi(id_epilogi_tmima, "dummies","id",3);	
    					//id_epilogi_orologio=dbname.add_epilogi(id_epilogi_tmima, "dummies","id",4);
    					dbname.add_epilogi_action(p.getUser(), id_epilogi_mathites,18, 6);
    					dbname.add_epilogi_action(p.getUser(), id_epilogi_tmima,19 , 7);
    					dbname.add_epilogi_action(p.getUser(), id_epilogi_tmima,28, 16);
    				}
    			
    				//Ο„Ξ± Ξ―Ξ΄ΞΉΞ± Ο„Ξ¬ΞΎΞ· Ξ’ 
    				table="epas_sxol_etos_eidik_tax";
    				lForm.setTaxi("Ξ¤Ξ¬ΞΎΞ· Ξ’'");
    				pedia=null;
    				pedia=new ArrayList<String>();
    				pedia.add("Id_epas_sxol_etos_eidik");
    				pedia.add("Taxi");
    				id_epas_sxol_etos_eidik_taxB=dbname.insert(table, pedia, form); 
    				//Ο„Ξ¬ΞΎΞ· B ΞΊΞ¬Ο„Ο‰ Ξ±Ο€Ο� id_epilogi_etos_eidik	
    				id_epilogi_taxi=dbname.add_epilogi(id_epilogi_etos_eidik, "dummies","id",7);//
    				dbname.add_epilogi_action(p.getUser(), id_epilogi_taxi,20 , 8); 			
    				//ΞµΞΉΟƒΞ®Ξ³Ξ±Ξ³Ξµ Ο„Ξ± Ο„ΞΌΞ®ΞΌΞ±Ο„Ξ± Ο„Ξ·Ο‚ Ο„Ξ¬ΞΎΞ·Ο‚ B'
    				table="epas_sxol_etos_eidik_tax_tmima";
    				lForm.setId_epas_sxol_etos_eidik_tax(id_epas_sxol_etos_eidik_taxB);
    				for(int i=0;i<lForm.getTmimaB();i++)
    				{
    					lForm.setPerigrafi("Ξ¤ΞΌΞ®ΞΌΞ± " +Integer.toString(i+1));
    					pedia=null;
    					pedia=new ArrayList<String>();
    					pedia.add("Id_epas_sxol_etos_eidik_tax");
    					pedia.add("Perigrafi");
    					id_epas_sxol_etos_eidik_tax_tmima=dbname.insert(table, pedia, form);
    					//Ο„ΞΌΞ®ΞΌΞ±Ο„Ξ± ΞΊΞ¬Ο„Ο‰ Ξ±Ο€Ο� Ο„Ξ· Ο„Ξ¬ΞΎΞ· B'
    					id_epilogi_tmima=dbname.add_epilogi(id_epilogi_taxi,"epas_sxol_etos_eidik_tax_tmima","id_epas_sxol_etos_eidik_tax_tmima",id_epas_sxol_etos_eidik_tax_tmima);
    					//Ξ�Ξ‘Ξ�Ξ—Ξ¤Ξ�Ο‚ Ξ�Ξ‘Ξ™ Ξ©Ξ΅Ξ�Ξ›Ξ�Ξ“Ξ™Ξ� Ξ“Ξ™Ξ‘ Ξ¤Ξ� Ξ¤Ξ�Ξ‰Ξ�Ξ‘ KAI MENU
    					id_epilogi_mathites=dbname.add_epilogi(id_epilogi_tmima, "dummies","id",3);	
    					//id_epilogi_orologio=dbname.add_epilogi(id_epilogi_tmima, "dummies","id",4);
    					dbname.add_epilogi_action(p.getUser(), id_epilogi_mathites,18 , 6);
    					dbname.add_epilogi_action(p.getUser(), id_epilogi_tmima,19 , 7); 
    					dbname.add_epilogi_action(p.getUser(), id_epilogi_tmima,28, 16);
    				}
    				dbname.updateprofile(p,s,request);
    			}
    		}catch(Exception e){
    			//JOptionPane.showMessageDialog(null, e.getMessage()); 
    		} 
    		return new ActionForward(".kyria");
    	}else if(action.equals("delete"))
        {
    		int id_epilogi=Integer.parseInt(((element)p.getViews().getActive()).getOnoma());
    		try{
    			if(dbname.deleteEpilogi(id_epilogi))
    			{
    				dbname.updateprofile(p,s,request);
    			}
    		}catch(Exception e)
    		{
    			//JOptionPane.showMessageDialog(null, e.getMessage());
    		}
    		return new ActionForward(".kyria");
    		
    		
        }else if(action.equals("edit"))
        {
        	
        	sxolikoForm lForm=null;
        	int id=((element)p.getViews().getActive()).getId();
        	String table="epas_sxol_etos";
    		lForm=(sxolikoForm)form; 
    		if (lForm==null)
    			lForm=new sxolikoForm();
    		List<String> pedia=new ArrayList<String>();
        	pedia.add("setId_kentro");
        	pedia.add("setId_sxol_etos");
        	pedia.add("setEnarxi");
        	pedia.add("setLixi");   
        	pedia.add("setId_name"); 
        	try
        	{
        		if(dbname.edit(table, pedia, lForm, id,"no"))//Ξ²Ξ¬Ξ»Ξµ Ο€ΞµΞ΄Ξ―Ξ± Ο„Ξ·Ο‚ Ξ²Ξ¬ΟƒΞ·Ο‚ ΟƒΟ„Ξ· Ο†Ο�Ο�ΞΌΞ±
        		{
        			lForm.setName(dbname.getName("select onoma from foreis where id_kentro='" + lForm.getId_kentro() +"'"));   
        			lForm.setId_epas_sxol_etos(id);
        			//gemise ta maps Ο„Ξ·Ο‚ Ο†Ο�Ο�ΞΌΞ±Ο‚
        			lForm.setN_eidikotites(dbname.fillMap("select id_eidikotita, perigrafi from eidikotites"));
        			lForm.setN_sxolika_eti(dbname.fillMap("select id_sxol_etos, sxol_etos from sxol_etos"));
        		}
        		String mess=new String("sxoliko_edit");
        		s.setAttribute("mess", mess);
        		s.setAttribute("form", lForm);   	
        	}catch(Exception e){
    			//JOptionPane.showMessageDialog(null, e.getMessage()); 
    		}  
        	return new ActionForward(".kyria");
        }
    	 
        return new ActionForward(".kyria");
        
        
          
        
    }
	
	
}






