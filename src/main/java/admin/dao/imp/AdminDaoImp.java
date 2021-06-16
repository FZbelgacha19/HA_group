package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import admin.dao.AdminDao;
import admin.models.Admin;

import db.connection.connection;

public class AdminDaoImp implements AdminDao {
	
	public int maxid() {
		String query = "SELECT MAX(`id`) FROM `admin`";
		Connection co = connection.getConnection();
		int i = 0;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				i = rs.getInt(1);
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void AjouteAdmin(Admin a) {
		String query = "INSERT INTO `admin`(`id`, `email`, `motdpass`, `nom_complete`, `Adress`,"
				+ "`Tele`, `dateNaissance`, `type`) " + "VALUES (?,?,?,?,?,?,?,?)";
		Connection co = connection.getConnection();
		try {
			int id = 1+maxid();
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getMotdpass());
			ps.setString(4, a.getNom_complete());
			ps.setString(5, a.getAdress());
			ps.setString(6, a.getTele());
			ps.setDate(7, a.getDateNaissance());
			ps.setString(8, a.getType());

			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void SuppAdmin(int id) {
		String query = "DELETE FROM `admin` WHERE `id`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Admin> ListAdmin() {
		String query = "SELECT `id`, `email`, `motdpass`, `nom_complete`, `Adress`, `Tele`,"
				+ " `dateNaissance`, `type` FROM `admin`";
		Connection co = connection.getConnection();
		List<Admin> admins = new ArrayList<Admin>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Admin a = new Admin(rs.getInt("id"),rs.getString("email"),
						rs.getString("motdpass"),rs.getString("nom_complete"),rs.getString("Adress"),rs.getString("Tele"),rs.getDate("dateNaissance"));
				admins.add(a);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admins;
	}

	@Override
	public void ModifAdmin(Admin a) {
		String query = "UPDATE `admin` SET `email`=?,"
				+ "`motdpass`=?,`nom_complete`=?,`Adress`=?,`Tele`=?,`dateNaissance`=?,"
				+ "`type`=? WHERE `id`=?";
		Connection co = connection.getConnection();
		try {
			
			PreparedStatement ps = co.prepareStatement(query);
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getMotdpass());
			ps.setString(3, a.getNom_complete());
			ps.setString(4, a.getAdress());
			ps.setString(5, a.getTele());
			ps.setDate(6, a.getDateNaissance());
			ps.setString(7, a.getType());
			ps.setInt(8, a.getId());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Admin GetAdmin(int id) {
		String query = "SELECT `id`, `email`, `motdpass`, `nom_complete`, `Adress`, `Tele`,"
				+ " `dateNaissance`, `type` FROM `admin` WHERE `id`=?";
		Connection co = connection.getConnection();
		Admin a = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				a = new Admin(rs.getInt("id"),rs.getString("email"),
						rs.getString("motdpass"),rs.getString("nom_complete"),rs.getString("Adress"),rs.getString("Tele"),rs.getDate("dateNaissance"));
		
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	@Override
	public Admin EstValide(String email, String MotPass, String type) {
		String query = "SELECT `id`, `email`, `motdpass`, `nom_complete`," + "`Adress`, `Tele`, `dateNaissance`, `type`"
				+ "FROM `Admin`" + "WHERE `email`=? AND `motdpass`=? AND `type`=?";
		Connection co = connection.getConnection();
		Admin u = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, MotPass);
			ps.setString(3, type);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				u = new Admin(rs.getString("email"), rs.getString("motdpass"), rs.getString("nom_complete"),
						rs.getString("Adress"), rs.getString("Tele"), rs.getDate("dateNaissance"));
				u.setType(rs.getString("type"));
				u.setId(rs.getInt("id"));

			}
			ps.close();

		} catch (Exception e) {

		}
		return u;
}
}
