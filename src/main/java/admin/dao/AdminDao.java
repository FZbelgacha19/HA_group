package admin.dao;

import java.util.List;

import admin.models.Admin;

public interface AdminDao {
	
	public void AjouteAdmin(Admin a);
	public void SuppAdmin(int id);
	public List<Admin> ListAdmin();
	public void ModifAdmin(Admin a);
	public Admin GetAdmin(int id);
	Admin EstValide(String email, String MotPass, String type);
}
