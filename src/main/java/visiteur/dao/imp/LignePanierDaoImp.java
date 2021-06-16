package visiteur.dao.imp;

import java.util.List;

import visiteur.dao.LignepanierDao;
import visiteur.models.Lignepanier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import db.connection.connection;

public class LignePanierDaoImp implements LignepanierDao {
	@Override
	public int maxid() {
		String query = "SELECT MAX(`id_panier`) FROM `lignepanier`";
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
	public void AjouteLignepanier(Lignepanier l) {
		String query = "INSERT INTO `lignepanier`(`id_panier`,`Ref_prod`,`Qte_cmd`,`id_user`) VALUES (?,?,?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(2, l.getRef_prod());
			ps.setObject(1, l.getId_panier());
			ps.setObject(3, l.getQte_cmd());
			ps.setObject(4, l.getId_user());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void SuppLignepanier(int id) {
		String query = "DELETE FROM `lignepanier` WHERE `id_panier`=?";
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
	public void SuppLignepanier(int id, int id_user) {
		String query = "DELETE FROM `lignepanier` WHERE `id_panier`=? AND `id_user`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(1, id);
			ps.setObject(2, id_user);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<Lignepanier> ListLignepanier() {
		String query = "SELECT `id_panier`,`Ref_prod`,`Qte_cmd`,`id_user` FROM `lignepanier`";
		List<Lignepanier> list_l = new ArrayList<Lignepanier>();
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lignepanier l = new Lignepanier();
				l.setQte_cmd((int) rs.getObject(3));
				l.setId_user((int) rs.getObject(4));
				l.setRef_prod((int) rs.getObject(2));
				l.setId_panier((int) rs.getObject(1));
				list_l.add(l);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_l;
	}
	

	@Override
	public List<Lignepanier> ListLignepanier(int id_user, int id_panier) {
		String query = "SELECT `id_panier`,`Ref_prod`,`Qte_cmd`,`id_user` FROM `lignepanier` WHERE `id_user`=? AND id_panier=?";
		List<Lignepanier> list_l = new ArrayList<Lignepanier>();
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(1, id_user);
			ps.setObject(2, id_panier);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lignepanier l = new Lignepanier();
				l.setQte_cmd((int) rs.getObject(3));
				l.setId_user((int) rs.getObject(4));
				l.setRef_prod((int) rs.getObject(2));
				l.setId_panier((int) rs.getObject(1));
				list_l.add(l);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_l;
	}

	@Override
	public void ModifLignepanier(Lignepanier l) {
		String query = "UPDATE `lignepanier` SET `Ref_prod`=?,`Qte_cmd`=?,`id_user`=? WHERE `id_panier`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setObject(1, l.getRef_prod());
			ps.setObject(4, l.getId_panier());
			ps.setObject(2, l.getQte_cmd());
			ps.setObject(3, l.getId_user());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Lignepanier GetLignepanier(int id) {
		String query = "SELECT `id_panier`,`Ref_prod`,`Qte_cmd`,`id_user` FROM `lignepanier`";
		Lignepanier l = null;
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l = new Lignepanier();
				l.setQte_cmd((int) rs.getObject(3));
				l.setId_user((int) rs.getObject(4));
				l.setRef_prod((int) rs.getObject(2));
				l.setId_panier((int) rs.getObject(1));

			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

}
