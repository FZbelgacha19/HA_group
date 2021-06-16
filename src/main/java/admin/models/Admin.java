package admin.models;

import java.sql.Date;

public class Admin {
	private int id;
	private String email ;
	private String motdpass ;
	private String nom_complete;
	private String Adress;
	private String Tele;
	private Date dateNaissance;
	private String type;
	
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String email, String motdpass, String nom_complete, String adress, String tele,
			Date dateNaissance) {
		super();
		this.id = id;
		this.email = email;
		this.motdpass = motdpass;
		this.nom_complete = nom_complete;
		Adress = adress;
		Tele = tele;
		this.dateNaissance = dateNaissance;
		this.type = "Admin";
	}
	public Admin(String email, String motdpass, String nom_complete, String adress, String tele,
			Date dateNaissance) {
		super();
		this.email = email;
		this.motdpass = motdpass;
		this.nom_complete = nom_complete;
		Adress = adress;
		Tele = tele;
		this.dateNaissance = dateNaissance;
		this.type = "Admin";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotdpass() {
		return motdpass;
	}
	public void setMotdpass(String motdpass) {
		this.motdpass = motdpass;
	}
	public String getNom_complete() {
		return nom_complete;
	}
	public void setNom_complete(String nom_complete) {
		this.nom_complete = nom_complete;
	}
	public String getAdress() {
		return Adress;
	}
	public void setAdress(String adress) {
		Adress = adress;
	}
	public String getTele() {
		return Tele;
	}
	public void setTele(String tele) {
		Tele = tele;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
