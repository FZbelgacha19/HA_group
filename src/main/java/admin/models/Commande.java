package admin.models;

public class Commande {
	private int Ref_cmd;
	private String Date_cmd;
	private double prix;
	private int id_panier;
	private int qte;
	
	
	public Commande(int ref_cmd, String date_cmd, double prix, int id_panier, int qte) {
		super();
		Ref_cmd = ref_cmd;
		Date_cmd = date_cmd;
		this.prix = prix;
		this.id_panier = id_panier;
		this.qte = qte;
	}
	public int getRef_cmd() {
		return Ref_cmd;
	}
	public void setRef_cmd(int ref_cmd) {
		Ref_cmd = ref_cmd;
	}
	public String getDate_cmd() {
		return Date_cmd;
	}
	public void setDate_cmd(String date_cmd) {
		Date_cmd = date_cmd;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getId_panier() {
		return id_panier;
	}
	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	@Override
	public String toString() {
		return "Commande [Ref_cmd=" + Ref_cmd + ", Date_cmd=" + Date_cmd + ", prix=" + prix + ", id_panier=" + id_panier
				+ ", qte=" + qte + "]";
	}
	
	
}
