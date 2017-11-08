/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.util.List;
import org.apache.struts.tiles.beans.*;

/**
 *
 * @author Administrator
 */
public class element implements kombos{
    
	 private String onoma;
	    //private boolean active;
	    //το μενου του
	    private List<MenuItem> Menu=null;
	    
	    //λιστα με παιδια του
	    private List<kombos> list=null;
	    
	    //an einai root ή οχι
	    private short typos;
	    
	    //thesi pu exei to element
	    private int index;
	    
	    //ο γονεας του
	    private kombos parent;
	    private int id;
    
    private String label;
    public element(){}
    public element(String onoma,String label,List<MenuItem> Menu,List<kombos> list,short typos)
    {
        this.onoma=onoma;
        this.list=list;
        this.Menu=Menu;
        this.typos=typos;
        this.label=label;
        this.parent=null;        
    }
    public List<MenuItem> getMenu() {
        return Menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setParent(kombos parent) {
        this.parent = parent;
    }

    public kombos getParent() {
        return parent;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String getOnoma() {
        return onoma;
    }
    public List<kombos> getList() {
        return list;
    }
    public void setList(List<kombos> list) {
        this.list = list;
    }

    public void setOmoma(String onoma) {
        this.onoma = onoma;
    }

    public void setMenu(List<MenuItem> Menu) {
        this.Menu = Menu;
    }
    
    public void setTypos(short typos) {
        this.typos = typos;
    }

    public short getTypos() {
        return typos;
    }
    
    public void setIndex(int index) 
    {
        this.index = index;
    }

    public int getIndex() 
    {
        return index;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
    @Override
    public boolean hasMenu() 
    {
        return !(Menu==null);
    }

    

    @Override
    public boolean hasChlidNodes() {
        return !(list==null);
    }

    

    

    
    
    
    
    
    
    
    
    
}
