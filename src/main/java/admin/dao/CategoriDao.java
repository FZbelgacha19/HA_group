package admin.dao;

import java.util.List;

import admin.models.Categorie;

public interface CategoriDao {

	public void AjouteCategorie(Categorie c);
	public void SuppCategorie(int id);
	public List<Categorie> ListCategorie();
	public void ModifCategorie(Categorie c);
	public Categorie GetCategorie(int id);
	public int maxid();
	public String getCatName(int id);
}
