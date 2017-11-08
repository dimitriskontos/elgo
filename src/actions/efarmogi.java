/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import ayiuda.db;
import beans.profile;
import forms.sxolikoForm;
import forms.spoudesForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.tiles.ComponentContext;
import forms.mathitesForm;

/**
 *
 * @author olga
 */
public class efarmogi extends org.apache.struts.tiles.actions.TilesAction {

    
    
   
    
    
    
    
    @Override
    public ActionForward execute(ComponentContext context, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession s=null;
        profile pr=null;
        s=request.getSession();
        db dbname;
        dbname=(db)s.getAttribute("db");
        if(dbname==null)
        {   dbname=new db();
            s.setAttribute("db", dbname);
        }
        pr=(profile)s.getAttribute("profile");
        if((pr==null)|| (pr!=null && (pr.getViews()==null)))
            return mapping.findForward("failure1");
        else{
          try{
              
              if(((String)s.getAttribute("mess")).equals("progspoudon_keni"))
              {
                spoudesForm lform=(spoudesForm)s.getAttribute("form");
                //s.setAttribute("form", lform);
                s.setAttribute("mess",null);
                return mapping.findForward("keni8");
              }else if(((String)s.getAttribute("mess")).equals("sxoliko_lathi"))
              {
                 sxolikoForm lform=(sxolikoForm)s.getAttribute("form");
            	 ActionErrors errors=(ActionErrors)s.getAttribute("lathi");
            	 //ActionMessages messages =(ActionMessages) s.getAttribute("err1");
                 saveErrors(request,errors);
                 //saveMessages(request, messages);
                 return mapping.findForward("keni0");
              }else if(((String)s.getAttribute("mess")).equals("tipota"))
              {
                  return mapping.findForward("failure1");
              }
              else if(((String)s.getAttribute("mess")).equals("sxoliko_keni"))
              {
                  s.setAttribute("mess",null);
                  return mapping.findForward("keni0");
               }else if(((String)s.getAttribute("mess")).equals("mathites_keni"))
               {
                   s.setAttribute("mess",null);
                   return mapping.findForward("keni7");
               }else if(((String)s.getAttribute("mess")).equals("kathigites_keni"))
               {
                    s.setAttribute("mess",null);
                    return mapping.findForward("keni9");
               }else if(((String)s.getAttribute("mess")).equals("anath_math")) 
               {
                   s.setAttribute("mess",null);
                   return mapping.findForward("keni10");
               }else if(((String)s.getAttribute("mess")).equals("eporol_keni")) 
               {
                   s.setAttribute("mess",null);
                   return mapping.findForward("keni11");
               }
               else if(((String)s.getAttribute("mess")).equals("epparus_keni")) 
               {
                   s.setAttribute("mess",null);
                   return mapping.findForward("keni12");
               }else if(((String)s.getAttribute("mess")).equals("mathites_lathi"))
               {
                  mathitesForm lForm=(mathitesForm)s.getAttribute("form");
              	  ActionErrors errors=(ActionErrors)s.getAttribute("lathi");
              	  saveErrors(request,errors);
                  return mapping.findForward("keni7");
                }	
               return mapping.findForward("failure1");
          }catch(Exception e)
          {
              return mapping.findForward("failure1");
          }
        }
     }
}
 
