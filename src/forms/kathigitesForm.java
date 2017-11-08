package forms;

import org.apache.struts.action.ActionForm;
import java.util.*;




public class kathigitesForm extends ActionForm {

	private int id_ekpaideyti;  
	private String ar_taytotitas;
	private String epwnymo;
	private String onoma;
	private String patrwnymo;
	private String logariasmos;
	private String afm;
	private String email;
	private int msc;
	private int phd;
	private int foreas;
	private String tilefono;
	private String address; 
	private int id_eidikotita;
	private int id_proslipsis;
	private int id_ekpaideyseis;
	private String perigrafi;
	private int id_kathigites_epas_sxol_etos;
	private int id_kathigites_epas_sxol_etos_mathimata;
	private int id_epas_sxol_etos;
	private int sex;
	private int id_forea;
	private String poli;
	private Map<String,String> nid_proslipsis;
	private Map<String,String> nid_katigoria;
	private Map<String,String> nid_eidikotita;
	private Map<String,String> pol;
	private Map<String,String> nsex;
	private Map<String, String> foreis;
	private int is_ypal;
	private Map<String,String> kath_maths;
	private Map<String,String> in_maths;
	private int id_mathima;
	private String zip;
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getZip() {
		return zip;
	}
	 
	public void setPoli(String poli) {
		this.poli = poli;
	}
	public String getPoli() {
		return poli;
	}
	public int getId_forea() {
		return id_forea;
	}
	public void setId_forea(int id_forea) {
		this.id_forea = id_forea;
	}
	public int getForeas() 
	{
		return foreas;
	}
	public void setForeas(int foreas) 
	{
		this.foreas = foreas;
	}
	public void setForeis(Map<String, String> foreis) 
	{
		this.foreis = foreis;
	}
	public Map<String, String> getForeis() 
	{
		return foreis;
	}
	public void setPol(Map<String, String> pol) 
	{
		this.pol = pol;
	}
	
	public Map<String, String> getPol() 
	{
		return pol;
	}
	public void setAfm(String afm) 
	{
		this.afm = afm;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getAfm() 
	{
		return afm;
	}
	public String getEmail() 
	{
		return email;
	}
	
	private int id_epas_sxol_etos_eidik_tax_mathimata;
	
	
	public Map<String, String> getIn_maths() {
		return in_maths;
	}
	public void setIn_maths(Map<String, String> in_maths) {
		this.in_maths = in_maths;
	}
	
	public int getId_epas_sxol_etos_eidik_tax_mathimata() {
		return id_epas_sxol_etos_eidik_tax_mathimata;
	}
	
	public void setId_epas_sxol_etos_eidik_tax_mathimata(
			int id_epas_sxol_etos_eidik_tax_mathimata) {
		this.id_epas_sxol_etos_eidik_tax_mathimata = id_epas_sxol_etos_eidik_tax_mathimata;
	}
	
	public Map<String, String> getKath_maths() {
		return kath_maths;
	}
	public void setKath_maths(Map<String, String> kath_maths) {
		this.kath_maths = kath_maths;
	}
	
	public void setId_mathima(int id_mathima) {
		this.id_mathima = id_mathima;
	}
	
	public int getId_mathima() {
		return id_mathima;
	}
	 
		
	
	public void setPerigrafi(String perigrafi) {
		this.perigrafi = perigrafi;
	}
	public String getPerigrafi() {
		return perigrafi;
	}
	public void setIs_ypal(int is_ypal) {
		this.is_ypal = is_ypal;
	}
	public int getIs_ypal() {
		return is_ypal;
	}
	
	public void setId_eidikotita(int id_eidikotita) {
		this.id_eidikotita = id_eidikotita;
	}
	public int getId_eidikotita() 
	{
		return id_eidikotita;
	}
	public Map<String, String> getNid_eidikotita() 
	{
		return nid_eidikotita;
	}
	public void setNid_eidikotita(Map<String, String> nid_eidikotita) {
		this.nid_eidikotita = nid_eidikotita;
	}
	
	public kathigitesForm() {
		// TODO Auto-generated constructor stub
		super();
		this.id_ekpaideyti=0; 
		sex=1;
		phd=1;
		msc=1;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getSex() {
		return sex;
	}
	
	public int getId_epas_sxol_etos()  
	{
		return id_epas_sxol_etos;
	}
	public void setId_epas_sxol_etos(int id_epas_sxol_etos)   
	{
		this.id_epas_sxol_etos = id_epas_sxol_etos;
	}
	
	public void setId_proslipsis(int id_proslipsis) {
		this.id_proslipsis = id_proslipsis;
	}
	public int getId_proslipsis() {
		return id_proslipsis;
	}
	
	public void setNid_katigoria(Map<String, String> nid_katigoria) {
		this.nid_katigoria = nid_katigoria;
	}
	public void setNid_proslipsis(Map<String, String> nid_proslipsis) {
		this.nid_proslipsis = nid_proslipsis;
	}
	public Map<String, String> getNid_katigoria() {
		return nid_katigoria;
	}
	public Map<String, String> getNid_proslipsis() {
		return nid_proslipsis;
	} 
	
	
	public int getId_kathigites_epas_sxol_etos() {
		return id_kathigites_epas_sxol_etos;
	}
	public int getId_kathigites_epas_sxol_etos_mathimata() {
		return id_kathigites_epas_sxol_etos_mathimata;
	}
	public void setId_kathigites_epas_sxol_etos(int id_kathigites_epas_sxol_etos) {
		this.id_kathigites_epas_sxol_etos = id_kathigites_epas_sxol_etos;
	}
	
	public void setId_kathigites_epas_sxol_etos_mathimata(int id_kathigites_epas_sxol_etos_mathimata) {
		this.id_kathigites_epas_sxol_etos_mathimata = id_kathigites_epas_sxol_etos_mathimata;
	}
	
	public void setId_ekpaideyseis(int id_ekpaideyseis) {
		this.id_ekpaideyseis = id_ekpaideyseis;
	}
	
	
	
	public String getAddress() {
		return address;
	}
	public String getAr_taytotitas() {
		return ar_taytotitas;
	}
	public int getId_ekpaideyti() {
		return this.id_ekpaideyti;
	}
	public int getId_ekpaideyseis() {
		return id_ekpaideyseis;
	}
	public int getPhd() {
		return phd;
	}
	public String getPatrwnymo() {
		return patrwnymo;
	}
	public String getTilefono() {
		return tilefono;
	}
	public int getMsc() {
		return msc;
	}
	public String getLogariasmos() {
		return logariasmos;
	}
	public String getEpwnymo() {
		return epwnymo;
	}
	public String getOnoma() {
		return onoma;
	}
	public int getEidikotita() {
		return id_eidikotita;
	}
	public void setTilefono(String tilefono) {
		this.tilefono = tilefono;
	}
	public void setEpwnymo(String epwnymo) {
		this.epwnymo = epwnymo;
	}
	public void setId_ekpaideyti(int id_ekpaideyti) {
		this.id_ekpaideyti = id_ekpaideyti;
	}
	public void setPatrwnymo(String patrwnymo) {
		this.patrwnymo = patrwnymo;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEidikotita(int id_eidikotita) {
		this.id_eidikotita = id_eidikotita;
	}
	public void setAr_taytotitas(String ar_taytotitas) {
		this.ar_taytotitas = ar_taytotitas;
	}
	public void setPhd(int phd) {
		this.phd = phd;
	}
	public void setOnoma(String onoma) {
		this.onoma = onoma;
	}
	public void setMsc(int msc) {
		this.msc = msc;
	}
	
	
	public void setLogariasmos(String logariasmos) {
		this.logariasmos = logariasmos;
	}
	
	public void setNsex(Map<String, String> nsex) {
		this.nsex = nsex;
	}
	public Map<String, String> getNsex() {
		return nsex;
	}
	
	
	
	
	
}
