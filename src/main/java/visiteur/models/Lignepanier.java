package visiteur.models;

public class Lignepanier {
	private int id_panier, Ref_prod, Qte_cmd, id_user;

	public Lignepanier(int id_panier, int ref_prod, int qte_cmd, int id_user) {
		super();
		this.id_panier = id_panier;
		Ref_prod = ref_prod;
		Qte_cmd = qte_cmd;
		this.id_user = id_user;
	}

	public Lignepanier() {
		// TODO Auto-generated constructor stub
	}

	public int getId_panier() {
		return id_panier;
	}

	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}

	public int getRef_prod() {
		return Ref_prod;
	}

	public void setRef_prod(int ref_prod) {
		Ref_prod = ref_prod;
	}

	public int getQte_cmd() {
		return Qte_cmd;
	}

	public void setQte_cmd(int qte_cmd) {
		Qte_cmd = qte_cmd;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	
}
