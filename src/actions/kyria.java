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
import beans.profile;
import javax.servlet.http.HttpSession; 
import ayiuda.utils;
import forms.LoginForm;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
/**
 *
 * @author olga
 */
public class kyria extends org.apache.struts.action.Action {

    

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
            throws Exception 
    {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        LoginForm lForm=(LoginForm)form;
        HttpSession s=null;
        profile p=null;
        s= request.getSession();
        p=(profile)s.getAttribute("profile");
        
        if(p==null && (lForm.getUser()!=null &&lForm.getPsd()!=null))
        {
            p=utils.getProfile(request,lForm.getUser(),lForm.getPsd());
            s.setAttribute("profile", p);
        }else if(lForm.getStatus() == LoginForm.LOG_OFF){
                s = request.getSession();
                s.setAttribute("profile", null);
                lForm.clear();
        }
        return new ActionForward(".kyria");
    }
}
