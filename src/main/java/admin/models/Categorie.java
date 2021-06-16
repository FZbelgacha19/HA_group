package admin.models;

public class Categorie {
	private int code_cat;
	private String Nom_cat;
	
	
	public Categorie(int code_cat, String nom_cat) {
		super();
		this.code_cat = code_cat;
		Nom_cat = nom_cat;
	}
	public int getCode_cat() {
		return code_cat;
	}
	public void setCode_cat(int code_cat) {
		this.code_cat = code_cat;
	}
	public String getNom_cat() {
		return Nom_cat;
	}
	public void setNom_cat(String nom_cat) {
		Nom_cat = nom_cat;
	}
	
	
}
