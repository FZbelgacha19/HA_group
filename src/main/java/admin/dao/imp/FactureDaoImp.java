package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.dao.FactureDao;
import admin.models.Facture;
import db.connection.connection;

public class FactureDaoImp implements FactureDao {
	@Override
	public int maxid() {
		String query = "SELECT MAX(`id_facture`) FROM `facture`";
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
	public void AjouteFacture(Facture f) {
		String query = "INSERT INTO `facture`(`id_facture`, `Ref_cmd`, `Etat_fact`, `id_user`)"
				+ " VALUES (?,?,?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, f.getId_facture());
			ps.setInt(2, f.getRef_cmd());
			ps.setString(3, f.getEtat_fact());
			ps.setInt(4, f.getId_user());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void SuppFacture(int id) {
		String query = "DELETE FROM `facture` WHERE `id_facture`=?";
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
	public List<Facture> ListFacture() {
		String query =" SELECT `id_facture`, `Ref_cmd`, `id_user`, `Etat_fact` FROM `facture`" ;
		Connection co = connection.getConnection();
		List<Facture> Fa = new ArrayList<Facture>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Facture f = new Facture(rs.getInt("id_facture"), rs.getInt("Ref_cmd"), rs.getInt("id_user"),rs.getString("`Etat_fact"));
				Fa.add(f);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Fa ;
	}
		

	@Override
	public void ModifFacture(Facture f) {
		String query ="UPDATE `facture` SET `Ref_cmd`=?,`id_user`=?,`Etat_fact`=? WHERE `id_facture`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			
			ps.setInt(1, f. getRef_cmd());
			ps.setInt(2, f.getId_user());
			ps.setString(3, f.getEtat_fact());
			ps.setInt(4, f.getId_facture());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			
		}
	
	}

	@Override
	public Facture GetFacture(int id) {
		
		String query = "SELECT `id_facture`, `Ref_cmd`, `id_user`, `Etat_fact` FROM `facture` WHERE `id_facture`=?";
		Connection co = connection.getConnection();
		Facture f = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				f = new Facture (rs.getInt("id_facture"), rs.getInt("Ref_cmd"), rs.getInt("id_user"),rs.getString("Etat_fact"));
			}
			
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}


}
