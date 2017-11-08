/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.action.ActionMessage;
//import beans.*;
import java.util.List;
import org.apache.struts.tiles.beans.*;

/**
 *
 * @author Administrator
 */
public class ektelesiForm extends org.apache.struts.action.ActionForm {
    
    private List<MenuItem> m=null;

    public List<MenuItem> getM() {
        return m;
    }

    public void setM(List<MenuItem> m) {
        this.m = m;
    }

    

    /**
     *
     */
    public ektelesiForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return errors;
    }
}
