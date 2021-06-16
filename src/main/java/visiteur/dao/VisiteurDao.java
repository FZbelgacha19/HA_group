package visiteur.dao;

import java.util.List;

import visiteur.models.Visiteur;



public interface VisiteurDao {
	public void AjouteVisiteur(Visiteur v);
	public void SuppVisiteur(int id);
	public List<Visiteur> ListVisiteur();
	public void ModifVisiteur(Visiteur v);
	public Visiteur GetVisiteur(int id);
	public int maxid();
}
