package admin.models;

public class Facture {
	private int id_facture,Ref_cmd,id_user;
	private String Etat_fact;
	
	
	public Facture(int id_facture, int ref_cmd, int id_user, String etat_fact) {
		super();
		this.id_facture = id_facture;
		Ref_cmd = ref_cmd;
		this.id_user = id_user;
		Etat_fact = etat_fact;
	}
	public int getId_facture() {
		return id_facture;
	}
	public void setId_facture(int id_facture) {
		this.id_facture = id_facture;
	}
	public int getRef_cmd() {
		return Ref_cmd;
	}
	public void setRef_cmd(int ref_cmd) {
		Ref_cmd = ref_cmd;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getEtat_fact() {
		return Etat_fact;
	}
	public void setEtat_fact(String etat_fact) {
		Etat_fact = etat_fact;
	}
	@Override
	public String toString() {
		return "Facture [id_facture=" + id_facture + ", Ref_cmd=" + Ref_cmd + ", id_user=" + id_user + ", Etat_fact="
				+ Etat_fact + "]";
	}
	
	
}
		