package admin.models;

public class Produit {
	private int Ref_pr;
	private String Nom;
	private String Description;
	private double Prix;
	private String Image;
	private int qte;
	private int marque;
	private int cat;
	
	
	
	
	public Produit(int ref_pr, String nom, String description, double prix, String image, int qte, int marque,
			int cat) {
		super();
		Ref_pr = ref_pr;
		Nom = nom;
		Description = description;
		Prix = prix;
		Image = image;
		this.qte = qte;
		this.marque = marque;
		this.cat = cat;
	}




	public int getRef_pr() {
		return Ref_pr;
	}




	public void setRef_pr(int ref_pr) {
		Ref_pr = ref_pr;
	}




	public String getNom() {
		return Nom;
	}




	public void setNom(String nom) {
		Nom = nom;
	}




	public String getDescription() {
		return Description;
	}




	public void setDescription(String description) {
		Description = description;
	}




	public double getPrix() {
		return Prix;
	}




	public void setPrix(double prix) {
		Prix = prix;
	}




	public String getImage() {
		return Image;
	}




	public void setImage(String image) {
		Image = image;
	}




	public int getQte() {
		return qte;
	}




	public void setQte(int qte) {
		this.qte = qte;
	}




	public int getMarque() {
		return marque;
	}




	public void setMarque(int marque) {
		this.marque = marque;
	}




	public int getCat() {
		return cat;
	}




	public void setCat(int cat) {
		this.cat = cat;
	}




	@Override
	public String toString() {
		return "Produit [Ref_pr=" + Ref_pr + ", Nom=" + Nom + ", Description=" + Description + ", Prix=" + Prix
				+ ", Image=" + Image + ", qte=" + qte + ", marque=" + marque + ", cat=" + cat + "]";
	}
	
	
	
}
