package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.dao.MarqueDao;
import admin.models.Marque;
import db.connection.connection;

public class MarqueDaoImp implements MarqueDao {
	@Override
	public int maxid() {
		String query = "SELECT MAX(`id_marque`) FROM `marque`";
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
	public void AjouteMarque(Marque m) {
		String query = "INSERT INTO `marque`(`id_marque`, `Lib_marque`) VALUES (?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, m.getId_marque());
			ps.setString(2, m.getLib_marque());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void SuppMarque(int id) {
		String query = "DELETE FROM `marque` WHERE `id_marque`=?";

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
	public List<Marque> ListMarque() {
		String query = "SELECT `id_marque`, `Lib_marque` FROM `marque`";
		Connection co = connection.getConnection();
		List<Marque> marqs = new ArrayList<Marque>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Marque m = new Marque(rs.getInt("id_marque"), rs.getString("Lib_marque"));
				marqs.add(m);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marqs;
	}

	@Override
	public void ModifMarque(Marque m) {
		String query = "UPDATE `marque` SET `Lib_marque`=? WHERE `id_marque`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setString(1, m.getLib_marque());
			ps.setInt(2, m.getId_marque());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Marque GetMarque(int id) {
		String query = "SELECT `id_marque`, `Lib_marque` FROM `marque` WHERE `id_marque`=? ";
		Connection co = connection.getConnection();
		Marque marq = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				marq = new Marque(rs.getInt("id_marque"), rs.getString("Lib_marque"));

			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marq;
	}
	@Override
	public String getMarqueName(int id) {
		String query = "SELECT `Lib_marque` FROM `marque` WHERE `id_marque`=? ";
		Connection co = connection.getConnection();
		String marq = "";
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				marq = rs.getString("Lib_marque");
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marq;
	}

}
