package forms;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import javax.swing.JOptionPane;
import java.util.Date;

public class sxolikoForm extends org.apache.struts.action.ActionForm{
	
	private Integer id_sxol_etos;
    private Map<String,String> n_sxolika_eti;
    
    private Map<String,String> n_eidikotites;
    private Integer eidikotita;
    
    private Integer tmimaA;
    private String taxi;
    private Integer tmimaB;
    //xristis kataxorisis
    private int id_name;
    private String apo;
    private String eos;
	private Integer id_kentro;
	private String epas;
	//onoma forea
	private String name;
	//key epas_sxol_etos
	private Integer id_epas_sxol_etos;
	//key epas_sxol_etos_eid
	private Integer id_epas_sxol_etos_eidik;
    private String perigrafi;
	private Integer id_epas_sxol_etos_eidik_tax;
	
	
	public Integer getId_epas_sxol_etos_eidik_tax()
	{
		return id_epas_sxol_etos_eidik_tax;
	}
	
	public void setId_epas_sxol_etos_eidik_tax(Integer id_epas_sxol_etos_eidik_tax)
	{
		
		this.id_epas_sxol_etos_eidik_tax=id_epas_sxol_etos_eidik_tax;
		
		
	}
	
	public Integer getId_epas_sxol_etos()
	{
	   return id_epas_sxol_etos;
	}	
	
   public void setId_epas_sxol_etos(Integer id_epas_sxol_etos)
   {
	   this.id_epas_sxol_etos=id_epas_sxol_etos;
   }
	
   public Integer getId_sxol_etos()
   {
	   return id_sxol_etos;
   }
  
   
   
   
   
   public Integer getId_epas_sxol_etos_eidik()
   {
	   return id_epas_sxol_etos_eidik;
   }
   
   public void setId_epas_sxol_etos_eidik(Integer id_epas_sxol_etos_eidik)
   {
	   
	   this.id_epas_sxol_etos_eidik=id_epas_sxol_etos_eidik;
   }
   
   
   public Map<String, String> getN_sxolika_eti() {
	   return n_sxolika_eti;
   }

   public void setN_sxolika_eti(Map<String, String> n_sxolika_eti) {
	   this.n_sxolika_eti = n_sxolika_eti;
   }

   public Map<String, String> getN_eidikotites() {
	   return n_eidikotites;
   }

   public void setN_eidikotites(Map<String, String> n_eidikotites) {
	   this.n_eidikotites = n_eidikotites;
   }

   public Integer getEidikotita() {
	   return eidikotita;
   }

   public void setEidikotita(Integer eidikotita) {
	   this.eidikotita = eidikotita;
   }

   public String getTaxi() {
	   return taxi;
   }

   public void setTaxi(String taxi) {
	   this.taxi = taxi;
   }
   
      public Integer getId_name() {
	   return id_name;
   }

   public void setId_name(Integer id_name) {
	   this.id_name = id_name;
   }
   
   

   public Integer getId_kentro() {
	   return id_kentro;
   }

   public void setId_kentro(Integer id_kentro) {
	   this.id_kentro = id_kentro;
   }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId_sxol_etos(Integer id_sxol_etos) {
		this.id_sxol_etos = id_sxol_etos;
	}
	 
   	public sxolikoForm()
   	{
   		this.id_kentro=0;
   		this.eidikotita=0;
   		this.id_sxol_etos=0;
   		this.id_epas_sxol_etos=0;
   		this.tmimaA=1;
   		this.tmimaB=1;
   		
   				
   		
   	}
	
   	public Date getEnarxi() {
   		java.text.DateFormat dt = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        Date apoo=null;
        String apo1=null;
        try{
         	apoo=dt.parse(getApo());
        }catch(Exception e){apoo=null;}
        return apoo;
      
     }
   	  
   	
   	public void setEnarxi(Date enarxi)
   	{
   		java.text.DateFormat dt1=new java.text.SimpleDateFormat("dd/MM/yyyy");
   		try{
   			apo=dt1.format(enarxi);
   		}catch(Exception e){}
   	}

         
   	public Date getLixi() 
   	{
   		java.text.DateFormat dt = new java.text.SimpleDateFormat("dd/MM/yyyy");
        Date eos=null;
   		try{
       	   eos=dt.parse(getEos());
          }catch(Exception e){eos=null;}
         return eos;
   	}
   	public void setLixi(Date lixi)
   	{
   		java.text.DateFormat dt1=new java.text.SimpleDateFormat("dd/MM/yyyy");
   		try{
   			eos=dt1.format(lixi);
   		}catch(Exception e){}
   	}
   	
   	
   	public String getApo() {
 	   return apo;
    }

    public void setApo(String apo) {
 	   this.apo = apo;
    }
    public String getEos() 
    {
  	   return eos;
    }

    public void setEos(String eos) 
    {
  	   this.eos = eos;
    }
    
     public Integer getTmimaA() {
    	   return tmimaA;
     }

     public void setTmimaA(Integer tmimaA) {
       this.tmimaA = tmimaA;
     }
     
     public Integer getTmimaB() {
  	   return tmimaB;
     }

     public void setTmimaB(Integer tmimaB) {
  	   this.tmimaB = tmimaB;
     }
     
     public String getPerigrafi()
     {
    	 return this.perigrafi;
     }
     public void setPerigrafi(String perigrafi)
     {
    	 this.perigrafi=perigrafi;
     }
     
     public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
     {
         ActionErrors errors = new ActionErrors();
         if (this.getId_sxol_etos()==0) 
         {
          	
        	 errors.add("sxolika_eti", new ActionMessage("sxoliko.etos.required"));
        	// JOptionPane.showMessageDialog(null, getSxolika_eti());
          	
         }
         if (this.getEidikotita()==0) 
         {
          	errors.add("eidikotita", new ActionMessage("sxoliko.eidikotita.required"));
          	//JOptionPane.showMessageDialog(null,getEidikotita());
         }
         //1 σε τμηματα
         try{
        	 if(this.getTmimaA()<1)
        		 setTmimaA(1);
         }catch(Exception e){
        	 setTmimaA(1);
         }
         try{
        	 if(this.getTmimaB()<1)
        		 setTmimaB(1);
         }catch(Exception e){
        	 setTmimaB(1);
         }
         
         
        
         return errors;
         
         
     }
     
       
       
}

//public class 

