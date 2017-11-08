/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.util.List;


/**
 *
 * @author olga
 */
public class profile {
    private epilogi views;
    private int user;
    private String username;
    
    public profile(epilogi views, int user,String username)
    {
        this.views=views;
        this.user=user;
        this.username=username;
    }
    public profile(int user,String username)
    {
        this.views=null;
        this.user=user;
        this.username=username;
    }
    public int getUser()
    {
        return user;
    }
    public epilogi getViews()
    {
        return views;
    }
    public void setViews(epilogi views)
    {
        this.views=views;
    }
    
    
    
    
    
}
