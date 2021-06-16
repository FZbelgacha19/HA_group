package admin.dao;

import java.util.List;

import admin.models.Commande;

public interface CommandeDao {
	public void AjouteCommande(Commande c);
	public void SuppCommande(int id);
	public List<Commande> ListCommande();
	public void ModifCommande(Commande c);
	public Commande GetCommande(int id);
	public int maxid();
}
