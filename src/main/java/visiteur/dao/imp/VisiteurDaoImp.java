package visiteur.dao.imp;

import java.util.List;

import visiteur.dao.VisiteurDao;
import visiteur.models.Visiteur;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.connection.connection;

public class VisiteurDaoImp implements VisiteurDao {
	@Override
	public int maxid() {
		String query = "SELECT MAX(`id_Visiteur`) FROM `visiteur`";
		int id = 999;
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public void AjouteVisiteur(Visiteur v) {
		String query = "INSERT INTO `visiteur`(`id_Visiteur`) VALUES (?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(1, v.getId_Visiteur());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void SuppVisiteur(int id) {
		String query = "DELETE FROM `visiteur` WHERE `id_Visiteur`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(1, id);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Visiteur> ListVisiteur() {
		String query = "SELECT `id_Visiteur` FROM `visiteur`";
		List<Visiteur> list_v = new ArrayList<Visiteur>();
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Visiteur v = new Visiteur((int) rs.getObject(1));

				list_v.add(v);
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_v;
	}

	@Override
	public void ModifVisiteur(Visiteur v) {
		String query = "UPDATE `visiteur` SET `id_Visiteur`=? WHERE `id_Visiteur`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(1, v.getId_Visiteur());
			ps.setObject(2, v.getId_Visiteur());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Visiteur GetVisiteur(int id) {
		String query = "SELECT `id_Visiteur` FROM `visiteur` WHERE `id_Visiteur`=?";
		Visiteur v = null;
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				v = new Visiteur((int) rs.getObject(1));
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

}
