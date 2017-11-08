package beans;
import java.util.Map;

public class usersRights {
	private int id_name;
	private String onoma;
	private String epwnymo;
	private Map<String, String> rights;
	
	public String getEpwnymo() {
		return epwnymo;
	}
	public int getId_name() {
		return id_name;
	}
	public String getOnoma() {
		return onoma;
	}
	public void setEpwnymo(String epwnymo) {
		this.epwnymo = epwnymo;
	}
	public void setId_name(int id_name) {
		this.id_name = id_name;
	}
	public void setOnoma(String onoma) {
		this.onoma = onoma;
	}
	public Map<String, String> getRights() {
		return rights;
	}
	public void setRights(Map<String, String> rights) {
		this.rights = rights;
	}
	public usersRights(int id_name,String onoma, String epwnymo) {
		// TODO Auto-generated constructor stub
		this.id_name=id_name;
		this.epwnymo=epwnymo;
		this.onoma=onoma;
		this.rights=null;
		
		
	}
}
