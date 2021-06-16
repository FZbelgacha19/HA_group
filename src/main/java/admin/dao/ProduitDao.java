package admin.dao;

import java.util.List;

import admin.models.Produit;

public interface ProduitDao {
	public void AjouteProduit(Produit p);
	public void SuppProduit(int id);
	public List<Produit> ListProduit();
	public List<Produit> ListProduit(int cat, int marq);
	public List<Produit> ListProduitParCat(int cat);
	public List<Produit> ListProduitParMarque(int marq);
	public void ModifProduit(Produit p);
	public Produit GetProduit(int id);
	public String GetNameProduit(int id);
	
}
