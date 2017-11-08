/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;
import beans.profile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import beans.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.beans.*;
import org.apache.struts.tiles.Controller;
/**
 *
 * @author olga
 */
public class menu extends org.apache.struts.tiles.actions.TilesAction implements Controller {
    @Override
    public void perform(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        /*context.putAttribute("title", "hi");
        List<SimpleMenuItem> l=new ArrayList<SimpleMenuItem>();
        SimpleMenuItem m=new SimpleMenuItem();
        m.setValue("bdfg");
        m.setLink("/bgs");
        l.add(m);
        context.putAttribute("items",l );*/
        try{
            execute(context,request,response,getServlet().getServletContext());
        }catch(Exception e){
            
        }
        
    }

    @Override
    public void execute(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession s=null;
        profile pr=null;
        s=request.getSession();
        pr=(profile)s.getAttribute("profile");
        if((pr==null)|| (pr!=null && (pr.getViews()==null)))
            return;
        else{
            // epForm=(epilogesForm)s.getAttribute("epilogesForm");
            //if(epForm==null)
            //    return;
            context.putAttribute("he", "Μενού");
            
            List<MenuItem> l=((element)pr.getViews().getActive()).getMenu();
            //List<MenuItem> l=null;
            //SimpleMenuItem M=new SimpleMenuItem();
            //M.setValue("dHBDF");
            //M.setLink("XGVDS");
            //List<MenuItem> l=new ArrayList<MenuItem>();
            //l.add(M);
            context.putAttribute("items",l);
            s.setAttribute("menu",l);
            //String epils=request.getParameter("epils");
            //if(!((element)pr.getViews().getActive()).getOnoma().matches(epils))
            //{
            //    pr.getViews().setActive(epils);
                
            //}
            return;
            
        }
    }
    
    
    
    
    
    
}
