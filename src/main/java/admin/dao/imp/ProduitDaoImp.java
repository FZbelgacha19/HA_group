package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.dao.ProduitDao;

import admin.models.Produit;
import db.connection.connection;

public class ProduitDaoImp implements ProduitDao {
	/*@Override
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
	}*/
	
	@Override
	public void AjouteProduit(Produit p) {
		String query = "INSERT INTO `produit`(`Ref_pr`, `Nom`, `Description`, `Prix`, `Image`, `qte`, "
				+ "`marque`, `cat`) " + "VALUES (?,?,?,?,?,?,?,?)";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			//int id = 1 + ListProduit().size();
			ps.setInt(1, p.getRef_pr());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getDescription());
			ps.setDouble(4, p.getPrix());
			ps.setString(5, p.getImage());
			ps.setInt(6, p.getQte());
			ps.setInt(7, p.getMarque());
			ps.setInt(8, p.getCat());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void SuppProduit(int id) {
		String query = "DELETE FROM `produit` WHERE `Ref_pr`=?";
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
	public List<Produit> ListProduit() {
		String query = "SELECT `Ref_pr`, `Nom`, `Description`, `Prix`,"
				+ "`Image`, `qte`, `marque`, `cat` FROM `produit`";
		Connection co = connection.getConnection();
		List<Produit> prods = new ArrayList<Produit>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produit p = new Produit(rs.getInt("Ref_pr"), rs.getString("Nom"), rs.getString("Description"),
						rs.getDouble("Prix"), rs.getString("Image"), rs.getInt("qte"), rs.getInt("marque"),
						rs.getInt("cat"));
				prods.add(p);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public void ModifProduit(Produit p) {
		String query = "UPDATE `produit` SET `Nom`=?," + "`Description`=?,`Prix`=?,"
				+ "`Image`=?,`qte`=?,`marque`=?,`cat`=? WHERE `Ref_pr`=?";
		Connection co = connection.getConnection();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setString(1, p.getNom());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrix());
			ps.setString(4, p.getImage());
			ps.setInt(5, p.getQte());
			ps.setInt(6, p.getMarque());
			ps.setInt(7, p.getCat());
			ps.setInt(8, p.getRef_pr());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Produit GetProduit(int id) {
		String query = "SELECT `Ref_pr`, `Nom`, `Description`, `Prix`,"
				+ "`Image`, `qte`, `marque`, `cat` FROM `produit` WHERE `Ref_pr`=? ";
		Connection co = connection.getConnection();
		Produit prod = null;
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prod = new Produit(rs.getInt("Ref_pr"), rs.getString("Nom"), rs.getString("Description"),
						rs.getDouble("Prix"), rs.getString("Image"), rs.getInt("qte"), rs.getInt("marque"),
						rs.getInt("cat"));
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}

	
	@Override
	public String GetNameProduit(int id) {
		String query = "SELECT `Nom` FROM `produit` WHERE `Ref_pr`=? ";
		Connection co = connection.getConnection();
		String prod = "";
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prod = rs.getString("Nom");
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}
	
	
	@Override
	public List<Produit> ListProduit(int cat, int marq) {
		String query = "SELECT `Ref_pr`, `Nom`, `Description`, `Prix`,"
				+ "`Image`, `qte`, `marque`, `cat` FROM `produit` WHERE `cat`=? AND `marque`=?";
		Connection co = connection.getConnection();
		List<Produit> prods = new ArrayList<Produit>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, cat);
			ps.setInt(2, marq);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produit p = new Produit(rs.getInt("Ref_pr"), rs.getString("Nom"), rs.getString("Description"),
						rs.getDouble("Prix"), rs.getString("Image"), rs.getInt("qte"), rs.getInt("marque"),
						rs.getInt("cat"));
				prods.add(p);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public List<Produit> ListProduitParCat(int cat) {
		String query = "SELECT `Ref_pr`, `Nom`, `Description`, `Prix`,"
				+ "`Image`, `qte`, `marque`, `cat` FROM `produit` WHERE `cat`=?";
		Connection co = connection.getConnection();
		List<Produit> prods = new ArrayList<Produit>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, cat);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produit p = new Produit(rs.getInt("Ref_pr"), rs.getString("Nom"), rs.getString("Description"),
						rs.getDouble("Prix"), rs.getString("Image"), rs.getInt("qte"), rs.getInt("marque"),
						rs.getInt("cat"));
				prods.add(p);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public List<Produit> ListProduitParMarque(int marq) {
		String query = "SELECT `Ref_pr`, `Nom`, `Description`, `Prix`,"
				+ "`Image`, `qte`, `marque`, `cat` FROM `produit` WHERE `marque`=?";
		Connection co = connection.getConnection();
		List<Produit> prods = new ArrayList<Produit>();
		try {
			PreparedStatement ps = co.prepareStatement(query);
			ps.setInt(1, marq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produit p = new Produit(rs.getInt("Ref_pr"), rs.getString("Nom"), rs.getString("Description"),
						rs.getDouble("Prix"), rs.getString("Image"), rs.getInt("qte"), rs.getInt("marque"),
						rs.getInt("cat"));
				prods.add(p);
			}
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prods;
	}

}
