package admin.models;

public class Marque {
	private int id_marque;
	private String Lib_marque;
	
	
	public Marque(int id_marque, String lib_marque) {
		super();
		this.id_marque = id_marque;
		Lib_marque = lib_marque;
	}
	public int getId_marque() {
		return id_marque;
	}
	public void setId_marque(int id_marque) {
		this.id_marque = id_marque;
	}
	public String getLib_marque() {
		return Lib_marque;
	}
	public void setLib_marque(String lib_marque) {
		Lib_marque = lib_marque;
	}
	
	
}
