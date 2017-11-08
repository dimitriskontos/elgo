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
import beans.*;
/**
 *
 * @author olga
 */
public class allagi extends org.apache.struts.action.Action {

    

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
            
            String active=null;
            String onoma=null;
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            HttpSession s=null;
            profile pr=null;
            s=request.getSession();
            pr=(profile)s.getAttribute("profile");
            if((pr==null)|| (pr!=null && (pr.getViews()==null)))
                return new ActionForward(".kyria");
            else{
                active=request.getParameter("active");
                onoma=request.getParameter("onoma");
                if(((element)pr.getViews().getActive()).getOnoma().matches(onoma))
                    return new ActionForward(".kyria");
                else{
                    pr.getViews().setActive(onoma);
                    s.setAttribute("mess", "tipota");
                    return new ActionForward(".kyria");
                }
                
            }
        
        
        
        
    }
}
