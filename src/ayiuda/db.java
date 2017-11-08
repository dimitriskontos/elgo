/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ayiuda;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.sql.ResultSetMetaData;
import java.util.*;

import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpSession;
import beans.profile;
import java.lang.reflect.*;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import forms.*; 

import java.util.*;
/**
 *
 * @author olga
 */
public class db <T> {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    public Statement getSt()
    {
        return st;
    }
    public Connection getCon()
    {
        return con;
    }
    
    
    
    public db(){
        
        super();
        try {
              Class.forName("com.mysql.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elgo?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8&user=root&password=dimis145");  
              //con=DriverManager.getConnection("jdbc:mysql://127.8.57.130:3306/elgo?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8&user=adminaCjnqVy&password=GSSUaZY-QjRV");
              st=con.createStatement();
              ResultSet rs=st.getResultSet();

        } catch(Exception e)
        {  }
   }
   public ResultSet getRs()
   {
       return rs;

   }
   public boolean updateRecord(String sql) throws SQLException
   {
       boolean result=false;
       Statement st;
       int rows;
       try {
           st=con.createStatement();
           rows = st.executeUpdate(sql);
           if(rows==1){
            result=true;
           }else
            st.close();
        }catch(Exception e)
        { 
        	throw new SQLException(e.getMessage());
        }
        return result;
   }
   
   public boolean deleteEpilogi(int id)  throws SQLException
   {
	   boolean result=false;
	   String sql="select pinakas,pedio,id_pediu from view_epilogi where id_epilogi='" + Integer.toString(id) +"';";
	   String pinakas=null;
	   String pedio=null;
	   Statement st1=null;
	   int rows=-1;
	   int id_pediu=-1;
	   ResultSet rs1=null;
	   try{
		   st1=getCon().createStatement();
		   rs1=st1.executeQuery(sql);
		   rs1.beforeFirst();
		   while(rs1.next())
		   {
			   pinakas=rs1.getString(1);
			   pedio=rs1.getString(2);
			   id_pediu=rs1.getInt(3);
		   }
		   rs1.close();
		   sql="delete from " + pinakas + " where " + pedio + "='" + id_pediu + "'";
		   rows=st1.executeUpdate(sql);
		   if(rows==1)
			   result=true;
		   sql="delete from view_epilogi where id_epilogi='" + Integer.toString(id) + "';";
		   rows=st1.executeUpdate(sql);
		   st.close();
	   }catch(Exception e){
		   st.close();
		   throw new SQLException(e.getMessage());
       }
	   return result;
   }
   
   public String getName(String sql) throws SQLException
   {
	   String result=null;
	   Statement st1=null;
	   ResultSet rs1=null;
	   try{
		   st1=getCon().createStatement();
		   rs1=st1.executeQuery(sql);
		   rs1.beforeFirst();
		   while(rs1.next())
		   	   result=rs1.getString(1);
		   rs1.close();
		   st1.close();
		   
	   }catch(Exception e)
	   {
		   rs1.close();
		   st1.close();
		   
	   }
	   return result;
	   
	   
   }
	   

   
   public int getId(String sql) throws SQLException
   {
	   int result=-1;
	   Statement st1=null;
	   ResultSet rs1=null;
	   try{
		   st1=getCon().createStatement();
		   rs1=st1.executeQuery(sql);
		   rs1.beforeFirst();
		   while(rs1.next())
		   	   result=rs1.getInt(1);
		   rs1.close();
		   st1.close();
		   
	   }catch(Exception e)
	   {
		   rs1.close();
		   st1.close();
		   
	   }
	   return result;
	   
	   
   }
   
   
   
   
   public void updateprofile(profile p, HttpSession s, HttpServletRequest request)  throws SQLException
   {
	   String sql="select epwnymo,psd from ipalliloi where id_name='" +p.getUser() + "';";
	   Statement st1=null;
	   int rows=-1;
	   int id_pediu=-1;
	   ResultSet psd=null;
	   try
	   {
		   st1=getCon().createStatement();
		   psd=st1.executeQuery(sql);
		   psd.beforeFirst();
		   while(psd.next())
			   p=utils.getProfile(request, psd.getString(1),psd.getString(2));
		   s.setAttribute("profile", p);
		   psd.close();
		   st1.close();
		   psd=null;
	   }catch(Exception e){
		   psd.close();
		   st1.close();
		   throw new SQLException(e.getMessage());
	   }
  }
    
   
   
   
   public boolean exist(String sql) throws SQLException
   {
	   boolean result=false;
	   Statement st1=null;
	   ResultSet rs1=null;
	   try{
		   st1=getCon().createStatement();
		   rs1=st1.executeQuery(sql);
		   rs1.beforeFirst();
		   while(rs1.next())
		   {
		   	   result=true;
		   	   break;
		   }
		   rs1.close();
		   st1.close();
		   
	   }catch(Exception e)
	   {
		   rs1.close();
		   st1.close();
		   
	   }
	   return result;
	   
	   
	   
	   
   }
   
   
   
   public boolean deleteRecord(String sql)
   {
       boolean result=false;
       Statement st;
       int rows;
       try {
           st=con.createStatement();
           rows = st.executeUpdate(sql);
           if(rows==1)
            result=true;
           st.close();
        }catch(Exception e)
        {}
        return result;
   }

    public  void getRecordset(String s)  throws SQLException
    {
        try{
            st=con.createStatement();
            rs=st.executeQuery(s);
       }catch (Exception e)
       { 
    	   
       }
    }
    public boolean Existuser(String table, String s1, String s2) throws SQLException
    {

        boolean result=false;
        PreparedStatement st1;
        ResultSet rs1;
        try{
            st1=con.prepareStatement("select name, pwd from " + table + " where name = ? and pwd =?");
            st1.setString(1,s1);
            st1.setString(2,s2);
            rs1=st1.executeQuery();
            rs1.first();
            if((s1.equals(rs1.getString("name"))) && (s2.equals(rs1.getString("pwd"))))
                    result=true;
            st1.close();
            rs1.close();
        }catch (Exception e)
        { 
        	
        }
        return result;
    }
    
    //
    
    
    
   //eisagogi sto pinaka view_epilogi 
   public int add_epilogi(int ypagete, String pinakas,String pedio, int id_pedio) throws SQLException
   {
	   PreparedStatement st=null;
	   Statement st1;
	   String sql=null;
       ResultSet rs1=null;
       int result=-1;
	   try
	   {
		    sql="insert into view_epilogi(pinakas,pedio,id_pediu,ypagete) values(?,?,?,?)";
		    st=getCon().prepareStatement(sql);
		    st.setString(1, pinakas);
		    st.setString(2,pedio);
		    st.setInt(3, id_pedio);
		    st.setInt(4,ypagete);
		    st.executeUpdate();
        	st.close();
        	
	   }catch(Exception e)
	   {
		   throw new SQLException("no insert to view_epilogi");
	   }
	   sql="select id_epilogi from view_epilogi where pinakas=\"" + pinakas + "\" and pedio=\"" +pedio +"\" and id_pediu='" + id_pedio + "'";
	   try{
		   st1=getCon().createStatement();
		   rs1=st1.executeQuery(sql);
		   rs1.beforeFirst();
		   while(rs1.next())
			   result=rs1.getInt(1);
		   rs1.close();
		   st1.close();
	   }catch(Exception e){
		   throw new SQLException("no get id_epilogi");
	   }
	   return result;
   }
    
    
    public <T> boolean edit(String table, List<String> fields, T form, int id,String pedio) throws SQLException
    {
    	boolean result=false;
    	Iterator<String> it=fields.iterator();
    	
    	Statement st1;
  	    ResultSet rs1=null;
    	String sql="select ";
    	String tmp=null;
    	while(it.hasNext())
    	{
    		tmp=it.next();
    		sql+=tmp.substring(3,tmp.length()).toLowerCase() + ",";
    	}
    	sql=sql.substring(0,sql.length()-1);	
    	if(pedio.equals("no"))
    		sql+=" from " + table + " where id_" + table + "='" + Integer.toString(id) + "'";
    	else
    		sql+=" from " + table + " where " + pedio + "='" + Integer.toString(id) + "'";
    	Object o=null;
    	Method m=null;
    	//Method[] methods=null; 
    	Iterator<String> it1=fields.iterator();
    	Class<?>[] parameterTypes=null;
    	int i=0;
    	try{
    	   st1=getCon().createStatement();
 		   rs1=st1.executeQuery(sql);
 		   rs1.beforeFirst();
    	   while(rs1.next())
    	   {
    		   //methods=form.getClass().getMethods();
    		   while(it1.hasNext())
    		   {
    			   tmp=(String)it1.next();
    			   try{
    				   for(Method m1 : form.getClass().getMethods())
    					   if(m1.getName().equals(tmp))
    					   {
    						   m=m1;
    						   break; 
    					   }
    			   }catch(Exception e)   
    			   {
    				   throw new SQLException("1");
    			   }
    			   parameterTypes = m.getParameterTypes();
    			   if(parameterTypes[0].getName().endsWith("Integer"))
    			   {
    				  try{ 
    					  i++;
    					  //JOptionPane.showMessageDialog(null,tmp+"int" );
    					  m.invoke(form,rs1.getInt(i));
    					  
    				  }catch(Exception e){}
    				  
    			  }    			  
    			  else if(m.getParameterTypes()[0].getName().endsWith("Date"))
    			  {
    				  try{
    					  i++;
    					  m.invoke(form,rs1.getDate(i));
    				  }catch(Exception e){}
    			  }
    			  else  
   			   	  {  
   				   	try{
   				   		i++; 
   				     	//JOptionPane.showMessageDialog(null,m.getParameterTypes()[0].getName() );
   				   		if(m.getParameterTypes()[0].getName().equals("int"))
   				   	    	m.invoke(form,rs1.getInt(i));
   				   	    else
   				   	    	m.invoke(form,rs1.getString(i));
   				   		 
   				   	}catch(Exception e){
   				   		 
   				   	} 
   			   	  }
    			   
    		   } 
    	   }
    	   result=true;	 
    	}catch(Exception e){
    		String r=null; 
    		throw new SQLException();      
    	}  
    	return result;
    }
    
    public Map<String,String> fillMap(String sql) throws SQLException
    {
    	Map<String,String> fill=new LinkedHashMap<String, String>();
    	Statement st1=null; 
 	    ResultSet rs1=null;
 	    String key=null;
 	    String value=null;
 	    try{
		   st1=getCon().createStatement(); 
		   rs1=st1.executeQuery(sql);
		   rs1.beforeFirst();
		   
		   while(rs1.next())
		   {
			   ResultSetMetaData rsmd = rs1.getMetaData();
			   for(int i=0;i<rsmd.getColumnCount();i++)
			   {
				   if(rsmd.getColumnType(1)==Types.VARCHAR)
					   key=rs1.getString(1);
				   else
					   key=Integer.toString(rs1.getInt(1));
				   
				   if(rsmd.getColumnType(2)==Types.VARCHAR)
					   value=rs1.getString(2);
				   else
					   value=Integer.toString(rs1.getInt(2));
			  }
			  fill.put(key, value);
		   }
		   rs1.close();
		   st1.close();
 	    }catch(Exception e){
 	    	if(rs1!=null)
 	    		rs1.close();    
 	    	if(st1!=null)
 	    		st1.close();
 	    } 
 	    return fill;
    }
    
    
    
    public <T> int insert(String table, List<String> fields, T form) throws SQLException
    {
    	int result=-1;
        PreparedStatement st=null;
        Statement st1;
 	    ResultSet rs1=null;
        String sql= "insert into " + table + "(";
        String tmp=null;
        Iterator<String> it=fields.iterator();
        int i=0;
        int j;
        while(it.hasNext())
        {
        	tmp=(String)it.next();
        	sql+=tmp.toLowerCase() + ",";
        	++i;
        }
        sql=sql.substring(0, sql.length()-1);
        sql+=") values(";
        for(j=0;j<i-1;j++)
        	sql+="?,";
        sql+="?);";	
        Iterator<String> it1=fields.iterator();
        i=1;
        Object o=null;
        st=getCon().prepareStatement(sql);
        java.text.DateFormat dt1=new java.text.SimpleDateFormat("yyyy/MM/dd");
        while(it1.hasNext())
        {
            	try{
             		tmp=(String)it1.next();
             		o=form.getClass().getMethod("get"+tmp).invoke(form);
             	}catch(Exception e4){
             		throw new SQLException(o.getClass().getName() + "-"+tmp);
             	} 
             	if(o.getClass().getName().endsWith("String"))
             		st.setString(i++,(String)o);
             	else if(o.getClass().getName().endsWith("Integer"))
             		st.setInt(i++,(Integer)o);
             	else
             	{
             		String d=null;
             		java.sql.Date r=null;
             		try{
                   	   d =dt1.format(o);
                     }catch(Exception e){
                    	 throw new SQLException(o.getClass().getName() + "-"+tmp);
                    	 //d=null;
                    }
                    if(d!=null)
                    {
                    	try{
                    		r=new java.sql.Date(dt1.parse(d).getTime());
                    	}catch(Exception e6)
                    	{
                    		r=null;
                    	}
                    }
             		st.setDate(i++,r);
             	}
        }
        try{
        	st.executeUpdate();
        	st.close();
        	//return true;
        }catch(Exception e){
        	
        	throw new SQLException(e.getMessage());
        	//return result;
        	
        }
       sql="select auto_increment from INFORMATION_SCHEMA.TABLES where table_name=\"" + table + "\""; 
 	   try{
 		   st1=getCon().createStatement();
 		   rs1=st1.executeQuery(sql);
 		   rs1.beforeFirst();
 		   while(rs1.next())
 			   result=rs1.getInt(1)-1;
 		   rs1.close();
 		   st1.close(); 
 	   }catch(Exception e){
 		   throw new SQLException("no id_"+ table);
 	   }
 	   
 	   return result;
   }
   
    public <T> int save(String table, List<String> fields, T form,int id,String pedio) throws SQLException
    {
        int result=-1;
        PreparedStatement st=null;
        Statement st1;
 	    ResultSet rs1=null;
        String sql= "update " + table + " set ";
        String tmp=null;
        Iterator<String> it=fields.iterator();
        int i=0;
        int j;
        while(it.hasNext())
        {
        	tmp=(String)it.next();
        	sql+=tmp.toLowerCase() + "=?, ";
        	++i;
        }
        sql=sql.substring(0, sql.length()-2);
        if(pedio.equals("no"))
        	sql+=" where id_" +table + "=?";
        else
        	sql+=" where " + pedio + "=?";
        //
        Iterator<String> it1=fields.iterator();
        i=1;
        Object o=null;
        st=getCon().prepareStatement(sql);
        java.text.DateFormat dt1=new java.text.SimpleDateFormat("yyyy/MM/dd");  
        while(it1.hasNext())
        {
            	 
        		try{
             		tmp=(String)it1.next();
             		o=form.getClass().getMethod("get"+tmp).invoke(form);
             	}catch(Exception e4){
             		throw new SQLException(o.getClass().getName() + "-"+tmp);
             	} 
             	if(o.getClass().getName().endsWith("Integer"))
             	{
             		
             		st.setInt(i++,(Integer)o);
             		
             	}
             	else if(o.getClass().getName().endsWith("Date"))
             	{
             		String d=null;
             		java.sql.Date r=null;
             		try{
                   	   d =dt1.format(o);
                     }catch(Exception e){
                    	 throw new SQLException(o.getClass().getName() + "-"+tmp);
                    	 //d=null;
                    }
                    if(d!=null)
                    {
                    	try{
                    		r=new java.sql.Date(dt1.parse(d).getTime());
                    	}catch(Exception e6)
                    	{
                    		r=null;
                    	}
                    } 
             		st.setDate(i++,r);
             		
             	}else{
             		st.setString(i++,(String)o);
             		//JOptionPane.showMessageDialog(null,(String)o )
             	}
        }
        try{
        	st.setInt(i,id);
        	result=st.executeUpdate();
        	st.close();
        	return result;
        }catch(Exception e){
        	
        	throw new SQLException(e.getMessage());
        	//return result;
        	
        }
       	   
 	   
   }
    
    
    
    
   public void add_epilogi_action(int user, int id_epilogi, int id_menu, int id_action_param)  throws SQLException
   {
	   boolean result=false;
	   PreparedStatement st=null;
	   //Statement st1;
	   String sql=null;
	   try{
		   
		   if(!exist("select * from view_epilogi_ypal where id_name='" +Integer.toString(user) + "' and id_epilogi='" + Integer.toString(id_epilogi) + "'"))
		   {
			   sql="insert into view_epilogi_ypal(id_name,id_epilogi) values(?,?)";
			   st=getCon().prepareStatement(sql);
			   st.setInt(1, user);
			   st.setInt(2,id_epilogi);
			   st.executeUpdate();
			   st.close();
		   }
	   }catch(Exception e){
		   st.close();
		   throw new SQLException(e.getMessage());
	   } 
	   try{
		   sql="insert into view_epilogi_ypal_action(id_name,id_epilogi,id_action_param,id_menu) values(?,?,?,?)";
		   st=getCon().prepareStatement(sql);
		   st.setInt(1, user);
		   st.setInt(2, id_epilogi);
		   st.setInt(3,id_action_param);
		   st.setInt(4, id_menu);
		   st.executeUpdate();
		   st.close(); 
		       
	   }catch(Exception e){
		   throw new SQLException("no add view_epilogi_ypal_action");
	   }
	   
   }
   public List<T> fillList(String table,String field,int value, List<String> fields, String className)  throws SQLException
   {
	   List<T> lst=new ArrayList<T>();
	   String sql="select ";
	   String tmp=null;
	   Iterator<String> it=fields.iterator();
	   Iterator<String> it1=fields.iterator();
	   Statement st1=null;
	   ResultSet rs1=null; 
	   T ob=null; 
       Method m=null;
       Class<?>[] parameterTypes=null;  
   	   int i=0;
       while(it.hasNext())
       {
    	   tmp=(String)it.next();
       	   sql+=tmp.substring(3,tmp.length()).toLowerCase() + ",";
       }
       sql=sql.substring(0, sql.length()-1); 
       sql+=" from " + table + " where " + field + "='" + Integer.toString(value) + "'"; 
       
       try{
    	   st1=getCon().createStatement();
 		   rs1=st1.executeQuery(sql);  
 		   rs1.beforeFirst(); 
    	   String delim="$";
    	   StringTokenizer st=new StringTokenizer(className, delim); 
    	   String outClass=(String)st.nextElement(); 
    	   Class<?> enclosing=Class.forName(outClass);  
 		   Object outer=enclosing.newInstance();
 		   while(rs1.next()) 
    	   {
 			   Class<?> inner=Class.forName(className); 
    		   Constructor<?> ctor=inner.getDeclaredConstructor(enclosing);
    		   ob=(T)ctor.newInstance(outer);
    		   i=0;
    		   while(it1.hasNext())
    		   {
    			   tmp=(String)it1.next();
    			   try{ 
    				   for(Method m1 : ob.getClass().getMethods())
    				   {
    					   
    					   if(m1.getName().equals(tmp))
    					   {
    						   
    						   m=m1;
    						   break;   
    					   }
    					   
    				   
    				   }
    			   }catch(Exception e)    
    			   {  
    				   throw new SQLException("1" +e.getMessage()); 
    			   }
    			   m.setAccessible(true);
    			   if(m.getGenericParameterTypes()[0].toString().endsWith("String"))
    			   {
    				   
    				  //JOptionPane.showMessageDialog(null,parameterTypes[0].getName());  
    				  m.invoke(ob,rs1.getString(++i));
    				   
    			   }
    			   if(m.getGenericParameterTypes()[0].toString().endsWith("int"))
    			   {
    				  //JOptionPane.showMessageDialog(null,parameterTypes[0].toString());   
    				  m.invoke(ob,rs1.getInt(++i));
    				  
    			   }
    			   else if(m.getGenericParameterTypes()[0].toString().endsWith("Date"))
    			   {
    				  //JOptionPane.showMessageDialog(null,parameterTypes[0].toString());  
    				  m.invoke(ob,rs1.getDate(++i));
    				   
    			   }
    		   }
    		   lst.add(ob);
    		   it1=fields.iterator();
    	   }
    	   rs1.close();   
    	   st1.close();
       }catch(Exception e){
    	   throw new SQLException(null,new String("nn")+e.getMessage()); 
       }
       
       return lst;
   }
    
    
    
    
    public boolean insertKategories(String name, String perigrafi)  throws SQLException
    {
        boolean result=false;
        String sql="insert into kategories (name,perigrafi) values('" + name + "' , '" + perigrafi + "')";
        Statement st;
        int rows;
        try {
            st=con.createStatement();
            rows = st.executeUpdate(sql);
            if(rows==1)
                result=true;
            st.close();

        }catch(Exception e)
        {}
        return result;
    }
    
    public int inserts(String table,String value)  throws SQLException
    {
    	int result=-1;
        Statement st=null;
        ResultSet rs=null;
        String sql="insert into " + table + " (perigrafi) values('" +  value + "')";
        int rows;
        try {
            st=con.createStatement();
            rows = st.executeUpdate(sql);
            if(rows==1)
            {
               String s="select auto_increment from INFORMATION_SCHEMA.TABLES where table_name=\"" + table + "\""; 
          	   try{
          		   st=null;
          		   st=con.createStatement();
          		   rs=st.executeQuery(s);
          		   rs.beforeFirst();
          		   while(rs.next())
          			   result=rs.getInt(1)-1; 
          		   rs.close();
          		   st.close(); 
          	   }catch(Exception e){
          		   rs.close();
        		   st.close(); 
          		   throw new SQLException("no id_"+ table);
          	   }
            }else{
          	   
            	st.close();
            	return result;  
            }
                  

        }catch(Exception e)
        {
        	st.close();
        	throw new SQLException("no id_"+ table);
        	
        }
        return result;
    }
    
    public boolean insertrecord(String sql) throws SQLException{
    	
    	boolean result=false;
    	Statement st=null;
    	int rows;
    	try{
    		st=con.createStatement();
            rows = st.executeUpdate(sql);
            if(rows==1)
            	result= true;
            else{
            	throw new SQLException("no insert");
            }
            st.close();
    	}catch(Exception e){
    		throw new SQLException(e.getMessage());
    	}
    	
    	return result;
    	
    	
    	
    }
    
    
   
}
