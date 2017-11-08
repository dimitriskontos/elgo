package forms;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class mathitesForm extends ActionForm {

	
	
	private	 Integer id_mathiti; 
	private String onoma;
	private String epwnymo;
	private String topos_gennisis;
	private String onoma_patera;
	private String onoma_miteras;
	private String epwnymo_patera;
	private String epwnymo_miteras;
	private String genos;
	private String dimos;
	private int id_nomos;
	private String am;
	private int sex;
	private String im_gennisi;
	private int id_epaggelma_patera;
	private int id_ypikootita;
	private int id_thriskeyma;
	private String onoma_kidemona;
	private String epwnymo_kidemona;
	private String address;
	private String id_poli_diamoni;
	private String tilefono;
	private String mobile;
	private String zip; 
	private int id_mathiti_taxi;
	private int id_mathiti_tmima;
	private int id_epas_sxol_etos_eidik_tax;
	private int id_epas_sxol_etos_eidik_tax_tmima;
	private String perigrafi;
	private Map<String,String> nid_ypikootita;
	private Map<String,String> nid_thriskeyma;
	private Map<String,String> nid_epaggelma_patera;
	private Map<String,String> nsex;
	
	private Map<String,String> nid_nomos;
	public void setNid_ypikootita(Map<String, String> nid_ypikootita) {
		this.nid_ypikootita = nid_ypikootita;
	}
	public void setNsex(Map<String, String> nsex) {
		this.nsex = nsex;
	}
	public Map<String, String> getNsex() {
		return nsex;
	}
	public void setNid_thriskeyma(Map<String, String> nid_thriskeyma) {
		this.nid_thriskeyma = nid_thriskeyma;
	}
	public void setNid_epaggelma_patera(Map<String, String> nid_epaggelma_patera) {
		this.nid_epaggelma_patera = nid_epaggelma_patera;
	}
	
	public Map<String, String> getNid_epaggelma_patera() {
		return nid_epaggelma_patera;
	}
	public Map<String, String> getNid_thriskeyma() {
		return nid_thriskeyma;
	}
	public Map<String, String> getNid_ypikootita() {
		return nid_ypikootita;
	}
	
	public String getPerigrafi() {
		return perigrafi;
	}
	public void setPerigrafi(String perigrafi) {
		this.perigrafi = perigrafi;
	}
	
	
	public String getIm_gennisi() {
		return im_gennisi;
	}
	public void setIm_gennisi(String im_gennisi) {
		this.im_gennisi = im_gennisi;
	}
	
	public void setNid_nomos(Map<String, String> nid_nomos) {
		this.nid_nomos = nid_nomos;
	}
	public Map<String, String> getNid_nomos() {
		return nid_nomos;
	}
	public Date getBirthday() {
   		java.text.DateFormat dt = new java.text.SimpleDateFormat("dd/MM/yyyy");
        Date apoo=null;
        String apo1=null;
        try{
         	apoo=dt.parse(getIm_gennisi());
        }catch(Exception e){apoo=null;}
        return apoo;
      
     }
   	    	
   	public void setBirthday(Date enarxi)
   	{
   		java.text.DateFormat dt1=new java.text.SimpleDateFormat("dd/MM/yyyy");
   		try{
   			im_gennisi=dt1.format(enarxi);
   		}catch(Exception e){}
   	}

	public String getAddress() {
		return address;
	}
   	
   	
   	public String getAm() {
		return am;
	}
   	public String getDimos() {
		return dimos;
	}
   	public String getEpwnymo() {
		return epwnymo;
	}
   	
   	public String getEpwnymo_kidemona() {
		return epwnymo_kidemona;
	}
   	public String getGenos() {
		return genos;
	}
   	public String getId_poli_diamoni() {
		return id_poli_diamoni;
	}
   	public String getMobile() {
		return mobile;
	}
   	public String getOnoma() {
		return onoma;
	}
   	public String getOnoma_miteras() {
		return onoma_miteras;
	}
   	public String getOnoma_kidemona() {
		return onoma_kidemona;
	}
   	public String getOnoma_patera() {
		return onoma_patera;
	}
   	public String getTopos_gennisis() {
		return topos_gennisis;
	}
   	public String getZip() {
		return zip;
	}
   	public String getTilefono() {
		return tilefono;
	}
   	public int getSex() {
		return sex;
	}
   	public String getEpwnymo_miteras() {
		return epwnymo_miteras;
	}
   	public String getEpwnymo_patera() {
		return epwnymo_patera;
	}
   	public int getId_epaggelma_patera() {
		return id_epaggelma_patera;
	}
   	public Integer getId_mathiti() {
		return id_mathiti;
	}
   	public int getId_thriskeyma() {
		return id_thriskeyma;
	}
   	public int getId_ypikootita() {
		return id_ypikootita;
	}
   	public int getId_nomos() {
		return id_nomos;
	}
   	public void setAddress(String address) {
		this.address = address;
	}
   	public void setAm(String am) {
		this.am = am;
	}
   	public void setDimos(String dimos) {
		this.dimos = dimos;
	}
   	
   	public void setEpwnymo(String epwnymo) {
		this.epwnymo = epwnymo;
	}
   	public void setEpwnymo_kidemona(String epwnymo_kidemona) {
		this.epwnymo_kidemona = epwnymo_kidemona;
	}
   	public void setEpwnymo_patera(String epwnymo_patera) {
		this.epwnymo_patera = epwnymo_patera;
	}
   	public void setGenos(String genos) {
		this.genos = genos;
	}
   	public void setOnoma(String onoma) {
		this.onoma = onoma;
	}
   	public void setOnoma_patera(String onoma_patera) {
		this.onoma_patera = onoma_patera;
	}
   	public void setZip(String zip) {
		this.zip = zip;
	}
   	public void setTopos_gennisis(String topos_gennisis) {
		this.topos_gennisis = topos_gennisis;
	}
   	public void setTilefono(String tilefono) {
		this.tilefono = tilefono;
	}
   	public void setOnoma_miteras(String onoma_miteras) {
		this.onoma_miteras = onoma_miteras;
	}
   	public void setOnoma_kidemona(String onoma_kidemona) {
		this.onoma_kidemona = onoma_kidemona;
	}
   	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
   	public void setSex(int sex) {
		this.sex = sex;
	}
   	public void setId_ypikootita(int id_ypikootita) {
		this.id_ypikootita = id_ypikootita;
	}
   	public void setId_thriskeyma(int id_thriskeyma) {
		this.id_thriskeyma = id_thriskeyma;
	}
   	public void setId_poli_diamoni(String id_poli_diamoni) {
		this.id_poli_diamoni = id_poli_diamoni;
	}
   	public void setId_nomos(int id_nomos) {
		this.id_nomos = id_nomos;
	}
   	public void setId_mathiti(Integer id_mathiti) {
		this.id_mathiti = id_mathiti;
	}
   	public void setEpwnymo_miteras(String epwnymo_miteras) {
		this.epwnymo_miteras = epwnymo_miteras;
	}
   	public void setId_epaggelma_patera(int id_epaggelma_patera) {
		this.id_epaggelma_patera = id_epaggelma_patera;
	}
   	public void setId_mathiti_taxi(int id_mathiti_taxi) {
		this.id_mathiti_taxi = id_mathiti_taxi; 
	}
   	public int getId_mathiti_taxi() {
		return id_mathiti_taxi;
	}
   	public int getId_mathiti_tmima() {
		return id_mathiti_tmima;
	}
   	public void setId_mathiti_tmima(int id_mathiti_tmima) {
		this.id_mathiti_tmima = id_mathiti_tmima;
	}
   	
   	public void setId_epas_sxol_etos_eidik_tax(int id_epas_sxol_etos_eidik_tax) {
		this.id_epas_sxol_etos_eidik_tax = id_epas_sxol_etos_eidik_tax;
	}
   	public void setId_epas_sxol_etos_eidik_tax_tmima(int id_epas_sxol_etos_eidik_tax_tmima) 
   	{
		this.id_epas_sxol_etos_eidik_tax_tmima = id_epas_sxol_etos_eidik_tax_tmima;
	}
   	public int getId_epas_sxol_etos_eidik_tax() 
   	{
		return id_epas_sxol_etos_eidik_tax;
	}
   	public int getId_epas_sxol_etos_eidik_tax_tmima() 
   	{
		return id_epas_sxol_etos_eidik_tax_tmima;
	}
   	
   	public mathitesForm() 
   	{
		// TODO Auto-generated constructor stub
   		id_mathiti=0;
   		id_mathiti_taxi=0;
   		sex=1;
	}
   	
	@Override
	public ActionErrors validate(ActionMapping mapping,	HttpServletRequest request)  
	{
		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();
        if (this.epwnymo.isEmpty()) 
        {
        	errors.add("epwnymo", new ActionMessage("mathitis.epwnymo.required"));
       	// JOptionPane.showMessageDialog(null, getSxolika_eti());
         	
        }
        if (this.onoma.isEmpty()) 
        {
         	errors.add("onoma", new ActionMessage("mathitis.onoma.required"));
         	//JOptionPane.showMessageDialog(null,getEidikotita());
        }
        
        return errors;
	}
	
	
	
}
