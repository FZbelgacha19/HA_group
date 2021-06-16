package defaultpack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import visiteur.models.Visiteur;

public class dao {
	
	public static void main(String[] args) {
		Class cl = Visiteur.class;
		
	    Field[] fields = cl.getDeclaredFields();
	    String[] name = cl.getName().split("\\.");
	    String n = name[2];
	    String typ = fields[0].getType().getSimpleName();
	    String ajout = "public void Ajout"+n+"("+n+" "+n.charAt(0)+"){\n}";
	    String update = "public void Modife"+n+"("+n+" "+n.charAt(0)+"){\n}";
	    String delet = "public void Supp"+n+"("+typ+" id_"+n.charAt(0)+"){\n}";
	    String select = "public List<"+n+"> Select"+n+"(){\n return null; \n}";
	    
	    System.out.println(ajout);
	    System.out.println(update);
	    System.out.println(delet);
	    System.out.println(select);
	    
	   
}}
