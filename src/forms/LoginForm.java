/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Administrator
 */
public class LoginForm extends org.apache.struts.action.ActionForm {
    
    public static final int UNDEFINED = 0;
    public static final int LOGGED_ON = 1;
    public static final int LOG_OFF = 2;
    public static final int REGULAR = 3;
    private int status;
    private String user;
    private String psd;
    private boolean hasErrors;
    /**
     *
     */
    public void setStatus(int i){ status = i; }
    public int getStatus(){ return status; }
    public void setUser(String s){ user = s; }
    public String getUser(){ return user; }
    public void setPsd(String s){psd = s; }
    public String getPsd(){ return psd; }
    public int getStatusRegular(){ return REGULAR; }
    public int getStatusLogOff() { return LOG_OFF; }
    public void setHasErrors(boolean error){hasErrors=true;}
    public void clear(){
        status = UNDEFINED;
        hasErrors = false;
        user =psd = null;
}
    
    public boolean hasErrors(){ 
        return hasErrors; 
    }
    
    public LoginForm() {
        super();
        // TODO Auto-generated constructor stub
        status = UNDEFINED;
        hasErrors = false;
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        try{
            request.setCharacterEncoding("utf-8");
            
        }catch(Exception e){}
        ActionErrors errors = new ActionErrors();
        if (user == null || user.length() < 1) 
            errors.add("user", new ActionMessage("error.name.required"));
        else if(psd == null || psd.length()<1) 
            errors.add("psd", new ActionMessage("error.psd.required"));
        
        if(!errors.isEmpty()){
            hasErrors = true;
            return errors;
        }
        hasErrors = false;
        return null;
    }

    
        
    
}
