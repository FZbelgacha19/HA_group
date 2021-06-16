package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.dao.CategoriDao;
import admin.models.Categorie;
import db.connection.connection;

public class CategorieDaoImp implements CategoriDao {
	
	@Override
	public int maxid() {
		String query = "SELECT MAX(`code_cat`) FROM `categories`";
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
	public void AjouteCategorie(Categorie c) {
		String query = "INSERT INTO `categories`(`code_cat`, `Nom_cat`) VALUES (?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, c.getCode_cat());
			ps.setString(2, c.getNom_cat());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void SuppCategorie(int id) {
		String query = "DELETE FROM `categories` WHERE `code_cat`=?";

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
	public List<Categorie> ListCategorie() {
		String query = "SELECT `code_cat`, `Nom_cat` FROM `categories`";
		Connection co = connection.getConnection();
		List<Categorie> cats = new ArrayList<Categorie>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categorie c = new Categorie(rs.getInt("code_cat"), rs.getString("Nom_cat"));
				cats.add(c);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cats;
	}

	@Override
	public void ModifCategorie(Categorie c) {
		String query = "UPDATE `categories` SET `Nom_cat`=? WHERE `code_cat`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setString(1, c.getNom_cat());
			ps.setInt(2, c.getCode_cat());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	// prend categorie par id 
	public Categorie GetCategorie(int id) {
		String query = "SELECT `code_cat`, `Nom_cat` FROM `categories` WHERE `code_cat`=?";
		Connection co = connection.getConnection();
		Categorie c = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Categorie(rs.getInt("code_cat"), rs.getString("Nom_cat"));
			}
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	

	@Override 
	public String getCatName(int id) {
		String query = "SELECT `Nom_cat` FROM `categories` WHERE `code_cat`=?";
		Connection co = connection.getConnection();
		String c = "";
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = rs.getString("Nom_cat");
			}
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}
