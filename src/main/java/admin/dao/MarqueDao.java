package admin.dao;

import java.util.List;

import admin.models.Marque;

public interface MarqueDao {
	public void AjouteMarque(Marque m);
	public void SuppMarque(int id);
	public List<Marque> ListMarque();
	public void ModifMarque(Marque m);
	public Marque GetMarque(int id);
	public int maxid();
	public String getMarqueName(int id);
}
