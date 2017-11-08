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

import beans.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import java.util.Stack;


/**
 *
 * @author olga
 */
public final class epiloges extends org.apache.struts.tiles.actions.TilesAction implements Controller {

    
   
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
        else{//epilogesForm epForm=(epilogesForm)s.getAttribute("epilogesForm");
            //if(epForm==null)
            //    return;
            //String selected=(String)request.getAttribute("active");
            
            context.putAttribute("title", "Επιλογές");
            List<simplemen> l=new ArrayList<simplemen>();
            kombos current=null;
            kombos previous=null;
            kombos current_l=null;
            Iterator<kombos> it=null;
            Stack<kombos> stack=new Stack<kombos>(); 
            simplemen m=null;
            stack.push(pr.getViews().getRoot());
            List<kombos> mark=new ArrayList<kombos>();
            Iterator<kombos> r=null;
            kombos tmp=null;
            boolean find=false;
            previous=pr.getViews().getRoot();
            int n=0;
            while(!stack.isEmpty())
            {
                
                current=(element)stack.pop();
                /*ελεγχος αν είναι μαρκαρισμένος*/
                r=mark.iterator();
                find=false;
                while(r.hasNext())
                {
                    tmp=(kombos)r.next();
                    if(((element)tmp).getOnoma().matches(((element)current).getOnoma()))
                    {
                        find=true;
                        r=null;
                        break;
                    }
                }
                if(find)
                    continue;
                else
                {    /*markarise*/
                    mark.add(current);
                }
                
                if(current.hasChlidNodes())
                    it=((element)current).getList().iterator();
                else
                    it=(new ArrayList<kombos>()).iterator();
                    
                /*bale τη λίστα του στη στοίβα*/
                while(it.hasNext())
                {
                    
                    current_l=(kombos)it.next();
                    stack.push(current_l);
                }
                /*an exei menu εμφάνισετοn*/
                if(current!=(element)pr.getViews().getRoot())
                {
                	if(current.hasMenu())
                    {
                		m=new simplemen();
                		if(((element)current).getParent()==previous)
                		{
                			n=n+1;
                		}
                		else{
                			simplemen t1=null;
                    		Iterator<simplemen> listas=l.iterator();
                			boolean brika=false;
                    		while(listas.hasNext())
                			{
                				t1=(simplemen)listas.next();
                				if(t1.getIcon().equals(((element)((element)current).getParent()).getOnoma()))
                				{
                					n=Integer.parseInt(t1.getTooltip())+1;
                					((simplemen)l.get(l.indexOf(t1))).setHasChild(true);
                					brika=true;
                					listas=null;
                					break;
                							
                				}
                			}
                			if(!brika)
                				n=2;
                		}
                		              		
                		if(((element)current).getParent()==previous)
                		{
                			simplemen t1=null;
                			try{
                				t1=(simplemen)l.remove((l.size()-1));//.setHasChild(true);
                				t1.setHasChild(true);
                				l.add(t1);
                			}catch(Exception e){}
                			
                		}
                        	
                		            		
                        
                		
                		
                        if((element)pr.getViews().getActive()==current)
                            m.setLink("yes");
                        else
                            m.setLink("no");
                        m.setValue(((element)current).getLabel());
                        m.setIcon(((element)current).getOnoma());
                        Iterator i=l.iterator();
                        boolean f=false;
                        while(i.hasNext())
                        {
                        	simplemen temp=(simplemen)i.next();
                            if(((simplemen)temp).getIcon().matches(((element)((element)current).getParent()).getOnoma()))
                            {
                                f=true;
                                //n=Integer.parseInt(((simplemen)temp).getTooltip());
                                //JOptionPane.showMessageDialog(null, n);
                                //n++;
                                //m.setTooltip(Integer.toString(n));
                            }
                        }
                        i=null;
                        
                        m.setTooltip(Integer.toString(n));
                        l.add(m);
                        
                        
                    }
                	previous=current;
                    
                    
                }
                
            }
            
            context.putAttribute("items",l);
            
        }
                    
              
                
                
            
            
            /*
            List<MenuItem> l=new ArrayList<MenuItem>();
            Iterator<kombos> it=((element)pr.getViews().getRoot()).getList().iterator();
            while(it.hasNext())
            {
                SimpleMenuItem m=new SimpleMenuItem();
                element e=(element)it.next();
                if((element)pr.getViews().getActive()==e)
                    m.setLink("yes");
                else
                    m.setLink("no");
                m.setValue(e.getLabel());
                m.setIcon(e.getOnoma());
                
                l.add(m);
            }
            context.putAttribute("items",l);
            //String epils=request.getParameter("epils");
            //if(!((element)pr.getViews().getActive()).getOnoma().matches(epils))
           /// {
            //    pr.getViews().setActive(epils);
                
            //}*/
            
        }
    }
    
 
    
    
    
    
    
    

