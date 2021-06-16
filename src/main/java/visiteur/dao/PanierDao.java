package visiteur.dao;

import java.util.HashMap;
import java.util.List;

import visiteur.models.Panier;

public interface PanierDao {
	public void AjoutePanier(Panier p);
	public void SuppPanier(int id);
	public List<Panier> ListPanier();
	public void ModifPanier(Panier p);
	public Panier GetPanier(int id);
	public int maxid();
	public HashMap<Integer, Integer> GetTotalCommande();
	public void SuppPanier();
}
