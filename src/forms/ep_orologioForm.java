package forms;

import org.apache.struts.action.ActionForm;
import java.util.*;

public class ep_orologioForm extends ActionForm {
	
	private int id_epas_sxol_etos_eidik_tax_tmima;
	private int id_epas_sxol_etos_eidik_tax;
	private int id_epas_sxol_etos_eidik_tax_mathimata;
	private int id_epas_sxol_etos;
	private int id_orol_tmima;
	private int id_mathima;
	private int id_kathigitis;
	private String imera;
	private String ora;
	private Map<String,String> imeres;
	private Map<String, String> ores;
	private Map<String,String> mathimata;
	private List<ep_orologioForm.point> programma=null;
	private Map<String,String> kathigites;
	
	
	public int getId_kathigitis() {
		return id_kathigitis;
	}
	public void setId_kathigitis(int id_kathigitis) {
		this.id_kathigitis = id_kathigitis;
	}
	public void setId_epas_sxol_etos(int id_epas_sxol_etos) {
		this.id_epas_sxol_etos = id_epas_sxol_etos;
	}
	public int getId_epas_sxol_etos() {
		return id_epas_sxol_etos;
	}
	public Map<String, String> getKathigites() {
		return kathigites;
	}
	public void setKathigites(Map<String, String> kathigites) {
		this.kathigites = kathigites;
	}
	public void setProgramma(List<ep_orologioForm.point> programma) 
	{
		this.programma = programma;
	}
	public List<ep_orologioForm.point> getProgramma() 
	{
		return programma;
	}
	public void setOra(String ora) 
	{
		this.ora = ora;
	}
	public String getOra() 
	{
		return ora;
	}
	
	public void setId_mathima(int id_mathima) 
	{
		this.id_mathima = id_mathima;
	}
	public int getId_mathima() 
	{
		return id_mathima;
	}
	
	public String getImera() 
	{
		return imera;
	}
	public void setImera(String imera) 
	{
		this.imera = imera;
	}
	public Map<String, String> getImeres() 
	{
		return imeres;
	}
	
	public void setImeres(Map<String, String> imeres) 
	{
		this.imeres = imeres;
	}
	
	public Map<String, String> getOres() 
	{
		return ores;
	}
	public void setOres(Map<String, String> ores) 
	{
		this.ores = ores;
	}
	public int getId_epas_sxol_etos_eidik_tax_mathimata() 
	{
		return id_epas_sxol_etos_eidik_tax_mathimata;
	}
	public int getId_orol_tmima() 
	{
		return id_orol_tmima;
	}
	public int getId_epas_sxol_etos_eidik_tax_tmima() 
	{
		return id_epas_sxol_etos_eidik_tax_tmima;
	}
	public void setId_epas_sxol_etos_eidik_tax_mathimata(int id_epas_sxol_etos_eidik_tax_mathimata) 
	{
		this.id_epas_sxol_etos_eidik_tax_mathimata = id_epas_sxol_etos_eidik_tax_mathimata;
	}
	
	public void setId_epas_sxol_etos_eidik_tax_tmima(int id_epas_sxol_etos_eidik_tax_tmima) 
	{
		this.id_epas_sxol_etos_eidik_tax_tmima = id_epas_sxol_etos_eidik_tax_tmima;
	}
	public void setId_orol_tmima(int id_orol_tmima) 
	{
		this.id_orol_tmima = id_orol_tmima;
	}
	public ep_orologioForm() 
	{
		id_orol_tmima=0;
	}
	public void setMathimata(Map<String, String> mathimata) 
	{
		this.mathimata = mathimata;
	}
	public Map<String, String> getMathimata() 
	{
		return mathimata;
	}
	public int getId_epas_sxol_etos_eidik_tax() 
	{
		return id_epas_sxol_etos_eidik_tax;
	}
	public void setId_epas_sxol_etos_eidik_tax(int id_epas_sxol_etos_eidik_tax) 
	{
		this.id_epas_sxol_etos_eidik_tax = id_epas_sxol_etos_eidik_tax;
	}
	public class point
	{
		private String id_orol_tmima;
		private String imera;
		private String ora;
		private String id_epas_sxol_etos_eidik_tax_mathimata;
		private int id_kathigites_epas_sxol_etos;
		
		
		public void setImera(String imera) {
			this.imera = imera;
		}
		public String getOra() {
			return ora;
		}
		public void setOra(String ora) {
			this.ora = ora;
		}
		public String getImera() {
			return imera;
		}
		public String getId_epas_sxol_etos_eidik_tax_mathimata() {
			return id_epas_sxol_etos_eidik_tax_mathimata;
		}
		public void setId_epas_sxol_etos_eidik_tax_mathimata(String mathima) 
		{
			this.id_epas_sxol_etos_eidik_tax_mathimata = mathima;
		}
		public void setId_orol_tmima(String id_orol_tmima) {
			this.id_orol_tmima = id_orol_tmima;
		}
		public String getId_orol_tmima(){
			return id_orol_tmima;
		}
		
		public void setId_kathigites_epas_sxol_etos(int id_kathigites_epas_sxol_etos) {
			this.id_kathigites_epas_sxol_etos = id_kathigites_epas_sxol_etos;
		}
		public int getId_kathigites_epas_sxol_etos() 
		{
			return id_kathigites_epas_sxol_etos;
		}
		public Map<String, String> getKathigites() 
		{
			return kathigites;
		}
		
	}
}

