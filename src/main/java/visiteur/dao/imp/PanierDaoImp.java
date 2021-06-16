package visiteur.dao.imp;

import java.util.List;

import visiteur.dao.PanierDao;
import visiteur.models.Panier;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import db.connection.connection;

public class PanierDaoImp implements PanierDao {
	@Override
	public int maxid() {
		String query = "SELECT MAX(`id_panier`) FROM `panier`";
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
	public void AjoutePanier(Panier p) {
		String query = "INSERT INTO `panier`(`id_panier`,`Ref_pr`) VALUES (?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(2, p.getRef_pr());
			ps.setObject(1, p.getId_panier());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void SuppPanier(int id) {
		String query = "DELETE FROM `panier` WHERE `id_panier`=?";
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
	public void SuppPanier() {
		String query = "DELETE FROM `panier`";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Panier> ListPanier() {
		String query = "SELECT `id_panier`,`Ref_pr` FROM `panier`";
		List<Panier> list_p = new ArrayList<Panier>();
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Panier p = new Panier();
				p.setId_panier((int) rs.getObject(1));
				p.setRef_pr((int) rs.getObject(2));
				list_p.add(p);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_p;

	}

	@Override
	public void ModifPanier(Panier p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Panier GetPanier(int id) {
		String query = "SELECT `id_panier`,`Ref_pr` FROM `panier`";
		Panier p = new Panier();
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Panier();
				p.setId_panier((int) rs.getObject(1));
				p.setRef_pr((int) rs.getObject(2));

			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}


	@Override
	public HashMap<Integer, Integer> GetTotalCommande(){
		String query = "SELECT COUNT(*) AS total, Ref_pr FROM panier GROUP BY Ref_pr";
		Connection co = connection.getConnection();
		HashMap<Integer, Integer> listeC = new HashMap<Integer, Integer>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listeC.put(rs.getInt("Ref_pr"), rs.getInt("total"));
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeC;
		
	}
}
