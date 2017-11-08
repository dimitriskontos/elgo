package ayiuda;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.tiles.beans.MenuItem;
import org.apache.struts.tiles.beans.SimpleMenuItem;

import beans.element;
import beans.epilogi;
import beans.kombos;
import beans.profile;

public class utils {

	
	/*������� �� ������ ��� user */
	static public profile getProfile(HttpServletRequest s, String user, String psd)
    {
        profile pr=null;
        String sql=null;
        ResultSet rs_user=null;
        ResultSet rs_epilogi=null;
        ResultSet rs_menu=null;
        ResultSet rs_men=null;
        ResultSet rs_action=null;
        ResultSet rs_act=null;
        ResultSet rs_link=null;
        ResultSet rs_param=null;
        ResultSet rs_view=null;
        ResultSet rs_project=null;
        ResultSet rs_perigrafi=null;
                
        db dbname=null;
        dbname=(db)s.getAttribute("db");
        /*Den ���� session �������� db  kai ������*/
        if(dbname==null)
        {
            dbname=new db();
            s.getSession().setAttribute("db", dbname);
        }
        sql="select id_name,epwnymo from ipalliloi where epwnymo=\"" +user +"\" and psd=\"" +psd + "\";";
        
        
        
        pr=(profile)s.getAttribute("profile");
        /* �� ���� ��� session profile �� ��� �� ����� update */ 
        if(pr!=null)
            pr=null;
        try{
            dbname.getRecordset(sql);
            rs_user=dbname.getRs(); 
        }catch(Exception e){e.printStackTrace();}        
        try{
            rs_user.beforeFirst();
            if(!rs_user.next())/*��� ����� ���������*/
            {
                
                rs_user.close();
                rs_user=null;
                return null;
            }
            else{/* ����� ���������, ������ �� ���� resources */
                sql="select id_epilogi from view_epilogi_ypal where id_name='" + rs_user.getInt(1) +"';";
                dbname.getRecordset(sql);
                rs_epilogi=dbname.getRs();
                rs_epilogi.beforeFirst();
                ActionMessages messages = new ActionMessages();
                epilogi e=null;
                if(!rs_epilogi.next()) /* ��� ���� resources, ��������� �� ���� profile */ 
                {
                    pr=new profile(rs_user.getInt(1),rs_user.getString(2));
                    rs_user.close();
                    rs_user=null;
                    return pr;
                    
                }
                else{/*���� resources*/
                    
                	e=new epilogi();
                    rs_epilogi.beforeFirst();
                    while(rs_epilogi.next())//gia ���� id �������� ������� menu �� action
                    {    
                        //JOptionPane.showMessageDialog(null, "gia resourc");
                    	/*�������� �����*/
                        String sql_menu="select id_action_param,id_menu from view_epilogi_ypal_action where id_name='" + rs_user.getInt(1) +"' and id_epilogi='" + rs_epilogi.getInt(1) + "' order by id_menu;";
                        dbname.getRecordset(sql_menu);
                        rs_menu=dbname.getRs();
                        int id_menu_c=0;
                        int id_menu_p=0;
                        rs_menu.beforeFirst();
                        List<MenuItem> lmenu=new ArrayList<MenuItem>();
                        List<kombos> lkombos=new ArrayList<kombos>();
                        String value=null;
                        String value1=null;
                        String label=null;
                        SimpleMenuItem m;
                        String sql_perigrafi=null;
                        while(rs_menu.next())//��� �� ���� ����� ��� id_epilogi
                        {
                            id_menu_c= rs_menu.getInt(2);            
                            String sql_action="select id_param,id_action from parametroi_acton where id_par_action='" +rs_menu.getInt(1) + "';";
                            dbname.getRecordset(sql_action);
                            rs_action=dbname.getRs();
                            rs_action.first();
                            if(id_menu_p!=id_menu_c)//pare neo menu
                            {
                            	
                            	if(id_menu_p!=0)/* ������� 1� ����, ��� �� ��������� �� label ��� values */
                                {
                            		m=new SimpleMenuItem();
                                    m.setValue(label);
                                    m.setLink(value);
                                    lmenu.add(m);
                                }
                                String sql_m="select name,label from menu where id_menu='" + id_menu_c +"'";
                                dbname.getRecordset(sql_m);  
                                rs_men=dbname.getRs();
                                rs_men.beforeFirst();
                                if(rs_men.next())
                                {
                                    label=rs_men.getString(2);
                                }
                                rs_men.close();
                                rs_men=null;
                                //name=rs_men.getString(1);
                                String sql_act="select onoma,label from actions where id_action='" + rs_action.getInt(2) + "';";
                                dbname.getRecordset(sql_act);
                                rs_act=dbname.getRs();
                                rs_act.beforeFirst();
                                if(rs_act.next())
                                {
                                    value=rs_act.getString(2)+ "?";
                                }
                                rs_act.close();
                                rs_act=null;
                                //id_menu_p=id_menu_c;
                            }
                            rs_action.beforeFirst();
                            while(rs_action.next())
                            {
                                String sql_param="select onoma,value from parametroi where id_param='" +rs_action.getInt(1) +"';";
                                dbname.getRecordset(sql_param);
                                rs_param=dbname.getRs();
                                rs_param.beforeFirst();
                                while(rs_param.next())
                                {
                                   value+=rs_param.getString(1) +"="+rs_param.getString(2);
                                }
                                rs_param.close();
                                rs_param=null;
                            }
                                
                            
                            rs_action.close();
                            rs_action=null;
                            //������ �� ����� kai ����� ���� �������
                            id_menu_p=id_menu_c;
                            
                        }
                        rs_menu.close();
                        rs_menu=null;
                        m=new SimpleMenuItem();
                        m.setValue(label);
                        m.setLink(value);
                        lmenu.add(m);
                        //������ ��� �������
                        
                        
                        String sql_epilogi="select pinakas,pedio,id_pediu from view_epilogi where id_epilogi='" + rs_epilogi.getInt(1) +"';";
                        dbname.getRecordset(sql_epilogi);
                        rs_view=dbname.getRs(); 
                        rs_view.first();
                        sql_perigrafi="select perigrafi from " +rs_view.getString(1) + " where " + rs_view.getString(2) + "='" + rs_view.getInt(3) + "';";
                        dbname.getRecordset(sql_perigrafi);
                        rs_perigrafi=dbname.getRs(); 
                        rs_perigrafi.first();       
                        value1=rs_perigrafi.getString(1);
                        /*ftiakse kombo*/
                        kombos el=e.getName(Integer.toString(rs_epilogi.getInt(1)));
                        
                        //an oxi ������ ����� ��������
                        if(el==null)
                        {
                        	el=new element(Integer.toString(rs_epilogi.getInt(1)),value1,lmenu,null,kombos.ELEMENT_KOMBOS);
                        	//JOptionPane.showMessageDialog(null, "ftiaxno el");
                        	((element)el).setId(rs_view.getInt(3));
                        
                        }
                        
                        
                      //an den yparxei parapanw goneas vale to el sth ����� ����
                        String s1=null;
                      // //�� ��� ���� �������� o root ��������
                        List<kombos> lk=((element)e.getRoot()).getList();
                        if(lk==null)
                        {
                        	//JOptionPane.showMessageDialog(null, "DEN exo liSTA RIZAS");
                            lk=new ArrayList<kombos>();
                            ((element)e.getRoot()).setList(lk);
                        }
                        
                       // JOptionPane.showMessageDialog(null, "exo liSTA RIZAS");
                        CallableStatement callSt=null;
                         try{
                                
                        	 	callSt=dbname.getCon().prepareCall("{?=call progonoi(?)}");
                                callSt.setInt(2, rs_epilogi.getInt(1));
                                callSt.registerOutParameter(1, Types.VARCHAR);
                                //JOptionPane.showMessageDialog(null, "PRIN KALESMA");
                                callSt.execute();
                                //JOptionPane.showMessageDialog(null, "META KALESMA");
                                if(callSt.getString(1).isEmpty())
                                    s1="";
                                else{
                                    s1=callSt.getString(1);
                                    
                                }
                                 //callSt.close();
                            //rs_project.getString("t");
                                //JOptionPane.showMessageDialog(null, s1);
                                callSt.close();
                                //rs_project.getString("t");
                           }catch(Exception rr){ 
                        	   JOptionPane.showMessageDialog(null, " callable");
                           }
                           kombos current=el;    
                                if(!(s1==""))
                                {
                                	//JOptionPane.showMessageDialog(null,"bika if");
                                	StringTokenizer st = new StringTokenizer(s1, ","); 
                                    while(st.hasMoreTokens()) 
                                    {
                                        
                                        String name =st.nextToken();
                                        kombos el1=null;
                                        el1=(element)e.getName(name);  
                                        if(el1==null)
                                        {
                                            String s2="select pinakas,pedio,id_pediu from view_epilogi where id_epilogi='" + Integer.parseInt(name) +"';";
                                            dbname.getRecordset(s2);
                                            ResultSet rs=dbname.getRs(); 
                                            rs.first();
                                            String s3="select perigrafi from " +rs.getString(1) + " where " + rs.getString(2) + "='" + rs.getInt(3) + "';";
                                            dbname.getRecordset(s3);
                                            ResultSet rs1=dbname.getRs(); 
                                            rs1.first();       
                                            String v1=rs1.getString(1);
                                            el1=new element(name,v1,null,null,kombos.ELEMENT_KOMBOS);
                                            rs.close();
                                            rs=null;
                                            rs1.close();
                                            rs1=null;
                                        }
                                        if(!((element)el1).hasChlidNodes())
                                         ((element)el1).setList(new ArrayList<kombos>());
                                        ((element)current).setParent(el1);
                                        ((element)el1).getList().add(current);
                                        current=el1;
                                    }
                                    kombos el3=null;
                                    el3=(element)e.getName(((element)current).getOnoma());
                                    if(el3==null)
                                    {
                                        ((element)current).setParent(e.getRoot());
                                        lk.add(current);
                                        e.setActive(Integer.toString(rs_epilogi.getInt(1)));
                                        ((element)e.getRoot()).setList(lk);
                                    }
                                    /*Iterator<kombos> it=((element)e.getRoot()).getList().iterator();
                                    kombos tmp=null;
                                    boolean l=false;
                                    while(it.hasNext())
                                    {
                                        tmp=(kombos)it.next();
                                        if(((element)tmp).getOnoma().equals(((element)current).getOnoma()))
                                        {
                                            l=true;
                                            break;
                                        }
                                    
                                    }
                                    if(!l)
                                    {
                                        ((element)current).setParent(e.getRoot());
                                        lk.add(current);
                                        e.setActive(Integer.toString(rs_epilogi.getInt(1)));
                                        ((element)e.getRoot()).setList(lk);
                                    }*/
                                }else{
                                	//JOptionPane.showMessageDialog(null,"bika else");
                                	kombos el3=null;
                                    el3=(element)e.getName(((element)current).getOnoma());
                                    
                                    if(el3==null)
                                    {
                                    
                                    	//JOptionPane.showMessageDialog(null,"den exo current");
                                             	((element)el).setParent(e.getRoot());
                                    	//b;ale to el
                                    	lk.add(el);
                                    	//b;ale th l;ista sto root
                                    	
                                    	e.setActive(Integer.toString(rs_epilogi.getInt(1)));
                                    	((element)e.getRoot()).setList(lk);
                                    }
                                    /*Iterator<kombos> it=((element)e.getRoot()).getList().iterator();
                                kombos tmp=null;
                                boolean l=false;
                                while(it.hasNext())
                                {
                                    tmp=(kombos)it.next();
                                    if(((element)tmp).getOnoma().equals(((element)el).getOnoma()))
                                    {
                                        l=true;
                                        break;
                                    }
                                    
                                }
                                if(!l)
                                {
                                    ((element)el).setParent(e.getRoot());
                                    //b;ale to el
                                    lk.add(el);
                                    //b;ale th l;ista sto root
                                    ((element)e.getRoot()).setList(lk);
                                    e.setActive(Integer.toString(rs_epilogi.getInt(1)));
                                }*/
                             
                                }
                        
                    }
                    pr=new profile(e,rs_user.getInt(1),rs_user.getString(2));
                    rs_epilogi.close();
                    rs_epilogi=null;
                     //epilogesForm f=new epilogesForm();
                    //f.setE(((element)e.getRoot()).getList());
                    //f.setAc(e.getActive());
                    //s.getSession().setAttribute("epilogesForm", f);
                }
                return pr;
            }
        }catch(Exception e)
        {
            ActionMessages messages = new ActionMessages();
            messages.add("err1", new ActionMessage(e.getMessage()));        
            s.setAttribute("err1", messages);
            return null;
        }
       
    }
	static public void updateprofile(profile p,db dbname,HttpServletRequest request, HttpSession s)
	{
		String sql=null;
		sql="select epwnymo,psd from ipalliloi where id_name='" +p.getUser() + "';";
        ResultSet psd=null;
        try{
        	dbname.getRecordset(sql);
        	psd=dbname.getRs();
        	psd.beforeFirst();
        	while(psd.next())
        		p=utils.getProfile(request, psd.getString(1),psd.getString(2));
        	s.setAttribute("profile", p);
        	psd.close();
        }catch(Exception e){
        	
        }
        	
        
		
		
	}
}
