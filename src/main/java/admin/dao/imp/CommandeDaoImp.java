package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.dao.CommandeDao;
import admin.models.Commande;
import db.connection.connection;

public class CommandeDaoImp implements CommandeDao {
	
	@Override
	public int maxid() {
		String query = "SELECT MAX(`Ref_cmd`) FROM `commande`";
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
	public void AjouteCommande(Commande c) {
		String query = "INSERT INTO `commande`(`Ref_cmd`, `Date_cmd`, `prix`, `id_panier`, `qte`) VALUES (?,?,?,?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, c.getRef_cmd());
			ps.setString(2, c.getDate_cmd());
			ps.setDouble(3, c.getPrix());
			ps.setInt(4, c.getId_panier());
			ps.setInt(5, c.getQte());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void SuppCommande(int id) {
		String query = "DELETE FROM `commande` WHERE 'Ref_cmd`=?";

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
	public List<Commande> ListCommande() {
		String query = "SELECT `Ref_cmd`, `Date_cmd`, `prix`, `id_panier`, `qte` FROM `commande`";
		Connection co = connection.getConnection();
		List<Commande> com = new ArrayList<Commande>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Commande c = new Commande(rs.getInt("Ref_cmd"), rs.getString("Date_cmd"), rs.getDouble("prix"),
						rs.getInt("id_panier"), rs.getInt("qte"));
				com.add(c);
			}
	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return com;

	}

	@Override
	public void ModifCommande(Commande c) {
		String query = "UPDATE `commande` SET `Ref_cmd`=?,`Date_cmd`=?,`prix`=?,`id_panier`=?,`qte`=? WHERE `Ref_cmd`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, c.getRef_cmd());
			ps.setString(2, c.getDate_cmd());
			ps.setDouble(3, c.getPrix());
			ps.setInt(4, c.getId_panier());
			ps.setInt(5, c.getQte());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Commande GetCommande(int id) {
		String query = "SELECT `Ref_cmd`, `Date_cmd`, `prix`, `id_panier`, `qte` FROM `commande` WHERE `Ref_cmd`=?";
		Connection co = connection.getConnection();
		Commande c = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new Commande(rs.getInt("Ref_cmd"), rs.getString("Date_cmd"), rs.getDouble("prix"),
						rs.getInt("id_panier"), rs.getInt("qte"));
			}
		
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}
