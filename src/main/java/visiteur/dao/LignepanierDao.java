package visiteur.dao;

import java.util.List;

import visiteur.models.Lignepanier;

public interface LignepanierDao {
	public void AjouteLignepanier(Lignepanier l);
	public void SuppLignepanier(int id);
	public List<Lignepanier> ListLignepanier();
	public void ModifLignepanier(Lignepanier l);
	public Lignepanier GetLignepanier(int id);
	public int maxid();
	public List<Lignepanier> ListLignepanier(int id_user, int id_panier);
	public void SuppLignepanier(int id, int id_user);
}

