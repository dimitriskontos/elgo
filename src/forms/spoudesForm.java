package forms;

import java.util.*;


import org.apache.struts.action.ActionForm;



public class spoudesForm extends ActionForm {
	private int id_epas_sxol_etos_eidik_tax;
	private List<spoudesForm.mathProg> maths=null;
	private Map<String,String> mathimata;
	private int id_mathima; 
	private String taxi;
	private Map<String,String> n_typos;
	private String type;
	private int ores;
	public spoudesForm() 
	{
		// TODO Auto-generated constructor stub
		super();
		this.id_epas_sxol_etos_eidik_tax=0;
		//this.maths=new ArrayList<spoudesForm.mathProg>();
	}
	
	public int getOres() {
		return ores;
	}
	public void setOres(int ores) { 
		this.ores = ores;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId_mathima() {
		return id_mathima;
	}
	public void setId_mathima(int id_mathima) {
		this.id_mathima = id_mathima;
	}
	public mathProg mathProg()
	{
		return new mathProg();
	}
	
	public void setTaxi(String taxi) {
		this.taxi = taxi;
	}
	public String getTaxi() {
		return taxi;
	}
	public void setMathimata(Map<String, String> mathimata) {
		this.mathimata = mathimata;
	}
	public void setMaths(List<spoudesForm.mathProg> maths) {
		this.maths = maths;
	}
	public void setId_epas_sxol_etos_eidik_tax(int id_epas_sxol_etos_eidik_tax) {
		this.id_epas_sxol_etos_eidik_tax = id_epas_sxol_etos_eidik_tax;
	}
	public Map<String, String> getMathimata() {
		return mathimata;
	}
	public List<spoudesForm.mathProg> getMaths() {
		return this.maths;
	}
	public int getId_epas_sxol_etos_eidik_tax() {
		return id_epas_sxol_etos_eidik_tax;
	}
	public Map<String, String> getN_typos() {
		return n_typos;
	}
	public void setN_typos(Map<String, String> n_typos) {
		this.n_typos = n_typos;
	}
	
	
	public class mathProg
	{
		private int id_epas_sxol_etos_eidik_tax_mathimata;
		private int ores;
		private int id_mathima;  
		private String type;
		public int getId_epas_sxol_etos_eidik_tax_mathimata() 
		{
			return id_epas_sxol_etos_eidik_tax_mathimata;
		}
		
		   
		public void setId_epas_sxol_etos_eidik_tax_mathimata(int id_epas_sxol_etos_eidik_tax_mathimata) 
		{
			this.id_epas_sxol_etos_eidik_tax_mathimata = id_epas_sxol_etos_eidik_tax_mathimata;
		}
		public int getOres() {
			return ores;
		}
		public void setOres(int ores) 
		{
			this.ores = ores;
		}
		public int getId_mathima() 
		{
			return id_mathima;
		}
		public void setId_mathima(int id_mathima) 
		{
			this.id_mathima = id_mathima;
		}
		public String getType() {
			return this.type;
		}
		public void setType(String type) 
		{
			this.type = type;
		}
		
		
	}
	
	

}

enum Typoi{
	ergastirio,theoria;
}
