/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Administrator
 */
public interface kombos {
    //public String getkombosName();
    //public String getkombosValue();
    //public void setkombosValue(String kombosValue);
    //public short getkombosType();
    
    
   //public kombos getParentKombos();
    
    //public kombos getFirstChild();
   // public kombos getLastChild();
   // public kombos getPreviousSibling();
    //public kombos getNextSibling();
    
    //public epilogi getEpilogi();
    
    public boolean hasChlidNodes();
    public boolean hasMenu();
    
    //public kombosList getChildNodes();
    //public boolean isActive();
    //
    
    
    
    
    
    //kombos type constants
    public static final short ELEMENT_KOMBOS=1;
    public static final short EPILOGI_KOMBOS=2;
    
    
    
}
