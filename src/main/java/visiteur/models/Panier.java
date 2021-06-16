package visiteur.models;

public class Panier {
	private int id_panier, Ref_pr;

	public Panier(int id_panier, int ref_pr) {
		super();
		this.id_panier = id_panier;
		Ref_pr = ref_pr;
	}

	public Panier() {
		// TODO Auto-generated constructor stub
	}

	public int getId_panier() {
		return id_panier;
	}

	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}

	public int getRef_pr() {
		return Ref_pr;
	}

	public void setRef_pr(int ref_pr) {
		Ref_pr = ref_pr;
	}
	
	
}
