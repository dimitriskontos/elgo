/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import forms.LoginForm;
import javax.servlet.http.HttpSession;
import beans.profile;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
/**
 *
 * @author Administrator
 */
public class loginaction extends org.apache.struts.action.Action {

    
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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        LoginForm lForm=(LoginForm)form;
        
        
        HttpSession s=null;
        if(lForm.getStatus()==LoginForm.UNDEFINED)
            return mapping.findForward("regular");
        if(lForm.getStatus()==LoginForm.LOGGED_ON)
            return mapping.findForward("success");
        if(lForm.getStatus() == LoginForm.LOG_OFF){
            try{
                s = request.getSession();
                s.setAttribute("profile", null);
                lForm.clear();
            }catch(Exception e){}
            return mapping.findForward("regular");
            
        }
        if(lForm.getStatus() == LoginForm.REGULAR){
            if(lForm.hasErrors()){
                return mapping.findForward("regular");
            }else{
                s = request.getSession();
                profile pr=null;
                pr=(profile)s.getAttribute("profile");
                ActionMessages messages = new ActionMessages();
                
                if(pr==null){
                    messages.add("err", new ActionMessage("error.name"));
                    request.setAttribute("err", messages);
                    return mapping.findForward("regular");
                } else if(pr.getViews()==null){
                    messages.add("err", new ActionMessage("error.name.actions"));        
                    request.setAttribute("err", messages);
                    return mapping.findForward("success");
                }else
                    return mapping.findForward("success");
            }
        }
        return mapping.findForward("regular");
    }
}