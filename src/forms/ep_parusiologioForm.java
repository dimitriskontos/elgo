package forms;

import java.util.*;

import org.apache.struts.action.ActionForm;



public class ep_parusiologioForm extends ActionForm {
	
	private int id_epas_sxol_etos_eidik_tax_tmima;
	private int id_epas_sxol_etos_eidik_tax;
	private int id_epas_sxol_etos;
	private Map<String, String> mathites;
	private Map<String, String> ores;
	private Map<String, String> kathigites;
	private Map<String, String> mathimata;
	private List<parusies> synolo;
	private int id_mathiti_tmima;
	private int id_kathigites_epas_sxol_etos;
	private int id_parusiol_kathig;
	private int id_orol_tmima;
	private String imerominia;
	private String parusia;
	private int is_orol;
	List<orolo> lst;
	public void setIs_orol(int is_orol) {
		this.is_orol = is_orol;
	} 
	public int getIs_orol() {
		return is_orol;
	}
	public ep_parusiologioForm() 
	{
		// TODO Auto-generated constructor stub
		id_epas_sxol_etos_eidik_tax_tmima=0;
		id_epas_sxol_etos_eidik_tax=0;
		id_epas_sxol_etos=0;
		mathites=new HashMap<String,String>();
		ores=new HashMap<String,String>();
		mathimata=new HashMap<String,String>();
		kathigites=new HashMap<String,String>();
	}
	public String getParusia() 
	{
		return parusia;
	}
	public void setParusia(String parusia) 
	{
		this.parusia = parusia;
	}
	public int getId_epas_sxol_etos() 
	{
		return id_epas_sxol_etos;
	}
	public void setId_epas_sxol_etos(int id_epas_sxol_etos) 
	{
		this.id_epas_sxol_etos = id_epas_sxol_etos;
	}
	
	public int getId_parusiol_kathig() {
		return id_parusiol_kathig;
	}
	public void setId_parusiol_kathig(int id_parusiol_kathig) {
		this.id_parusiol_kathig = id_parusiol_kathig;
	}
	public void setId_epas_sxol_etos_eidik_tax_tmima(int id_epas_sxol_etos_eidik_tax_tmima) 
	{
		this.id_epas_sxol_etos_eidik_tax_tmima = id_epas_sxol_etos_eidik_tax_tmima;
	}
	public void setId_epas_sxol_etos_eidik_tax(int id_epas_sxol_etos_eidik_tax) 
	{
		this.id_epas_sxol_etos_eidik_tax = id_epas_sxol_etos_eidik_tax;
	}
	public int getId_epas_sxol_etos_eidik_tax() 
	{
		return id_epas_sxol_etos_eidik_tax;
	}
	public int getId_epas_sxol_etos_eidik_tax_tmima() 
	{
		return id_epas_sxol_etos_eidik_tax_tmima;
	}
	
	public void setSynolo(List<parusies> synolo) 
	{
		this.synolo = synolo;
	}
	public List<parusies> getSynolo() 
	{
		return synolo;
	}
	
	public void setImera(Date imera) 
	{
		java.text.DateFormat dt1=new java.text.SimpleDateFormat("dd/MM/yyyy");
   		try{
   			imerominia=dt1.format(imera);
   		}catch(Exception e){}
	}
	public void setId_kathigites_epas_sxol_etos(int id_kathigites_epas_sxol_etos) 
	{
		this.id_kathigites_epas_sxol_etos = id_kathigites_epas_sxol_etos;
	}
	public String getImerominia() {
		return imerominia;
	}
	public void setImerominia(String imerominia) {
		this.imerominia = imerominia;
	}
	public void setId_mathiti_tmima(int id_mathiti_tmima) 
	{
		this.id_mathiti_tmima = id_mathiti_tmima;
	}
	public void setId_orol_tmima(int id_orol_tmima) 
	{
		this.id_orol_tmima = id_orol_tmima;
	}
	public Date getImera() 
	{
		java.text.DateFormat dt = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        Date apoo=null;
        String apo1=null;
        try{
         	apoo=dt.parse(getImerominia());
        }catch(Exception e){apoo=null;}
        return apoo;
	}
	
	public int getId_kathigites_epas_sxol_etos() 
	{
		return id_kathigites_epas_sxol_etos;
	}
	public int getId_mathiti_tmima() 
	{
		return id_mathiti_tmima;
	}
	public int getId_orol_tmima() 
	{
		return id_orol_tmima;
	}
	
	public void setKathigites(Map<String, String> kathigites) 
	{
		this.kathigites = kathigites;
	}
	public void setMathites(Map<String, String> mathites) 
	{
		this.mathites = mathites;
	}
	public void setOres(Map<String, String> ores) 
	{
		this.ores = ores;
	}
	public Map<String, String> getKathigites() 
	{
		return kathigites;
	}
	public Map<String, String> getOres() 
	{
		return ores;
	}
	public Map<String, String> getMathites() 
	{
		return mathites;
	}
		
	public Map<String, String> getMathimata() 
	{
		return mathimata;
	}
	public void setMathimata(Map<String, String> mathimata) 
	{
		this.mathimata = mathimata;
	}
	
	public List<orolo> getLst() 
	{
		return lst;
	}
	public void setLst(List<orolo> lst) 
	{
		this.lst = lst;
	}
	
	public class parusies
	{
		
		
	}
	public class orolo implements Comparable<orolo> 
	{
		private int id_orol_tmima;
		private String ora;
		private int id_kathigites_epas_sxol_etos;
		private int id_epas_sxol_etos_eikik_tax_mathimata;
		private Map<String,String> apontes;
		private boolean isEdit;
		private int id_parusiol_kathig;
		public boolean getIsEdit() {
			return isEdit;
		}
		public void setIsEdit(boolean isEdit) {
			this.isEdit = isEdit;
		}
		public Map<String,String> getApontes() 
		{
			return apontes;
		}
		public void setApontes(Map<String,String> apontes) 
		{
			this.apontes = apontes;
		}
		
		public void setId_parusiol_kathig(int id_parusiol_kathig) {
			this.id_parusiol_kathig = id_parusiol_kathig;
		}
		public int getId_parusiol_kathig() {
			return id_parusiol_kathig;
		}
		public void setOra(String ora) 
		{
			this.ora = ora;
		}
		public void setId_epas_sxol_etos_eikik_tax_mathimata(int id_epas_sxol_etos_eikik_tax_mathimata) 
		{
			this.id_epas_sxol_etos_eikik_tax_mathimata = id_epas_sxol_etos_eikik_tax_mathimata;
		}
		public void setId_kathigites_epas_sxol_etos(int id_kathigites_epas_sxol_etos) 
		{
			this.id_kathigites_epas_sxol_etos = id_kathigites_epas_sxol_etos;
		}
		public int getId_epas_sxol_etos_eikik_tax_mathimata() 
		{
			return id_epas_sxol_etos_eikik_tax_mathimata;
		}
		public int getId_kathigites_epas_sxol_etos() {
			return id_kathigites_epas_sxol_etos;
		}
		public String getOra() 
		{
			return ora;
		}
		public int getId_orol_tmima() 
		{
			return id_orol_tmima;
		}
		public void setId_orol_tmima(int id_orol_tmima) 
		{
			this.id_orol_tmima = id_orol_tmima;
		}
		 public int compareTo(orolo other) 
		 {
		        return ora.compareTo(other.getOra());
		 }
		
	}
	

}
