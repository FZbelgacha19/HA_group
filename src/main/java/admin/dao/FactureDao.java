package admin.dao;

import java.util.List;

import admin.models.Facture;

public interface FactureDao {
	public void AjouteFacture(Facture f);
	public void SuppFacture(int id);
	public List<Facture> ListFacture();
	public void ModifFacture(Facture f);
	public Facture GetFacture(int id);
	public int maxid();
}
