package defaultpack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import visiteur.models.*;


public class PrepStatquery {
 public static void main(String[] args) {
	 Class<?> cl = Lignepanier.class;
		System.out.println("\n"
				+ "import java.sql.Connection;\r\n"
				+ "import java.sql.Date;\r\n"
				+ "import java.sql.PreparedStatement;\r\n"
				+ "import java.sql.ResultSet;\r\n"
				+ "import java.util.ArrayList;\r\n"
				+ "import db.connection.connection;"
				+ "\n\n");
	    Field[] fields = cl.getDeclaredFields();
	    String[] name = cl.getName().split("\\.");
	    String n = name[2].toLowerCase();
	    String insertpart1="\nString query = \"INSERT INTO `"+n+"`(";
	    String insertpart2="VALUES (";
	    String update="String query = \"UPDATE `"+n+"` SET ";
	    String delete = "String query = \"DELETE FROM `"+n+"` WHERE `"+fields[0].getName()+"`=?\";";
	    String select = "String query = \"SELECT ";

	    for(int i=0;i<fields.length;i++){
	    	String fe = fields[i].getName();
	    	insertpart1+="`"+fe+"`";
	    	insertpart2+="?";
	    	select+="`"+fe+"`";
	    	if(i != 0) {
	    		update +="`"+fe+"`=?";
	    	}
	    	if(i != fields.length-1) {
	    		insertpart1+=",";
	    		insertpart2+=",";
	    		select+=",";
	    		if(i != 0)
	    			update+=",";
	    	}
	    	
	    }
	    insertpart1+=")";
	    insertpart2+=")\";";
	    update+=" WHERE `"+fields[0].getName()+"`=?\";";
	    select+=" FROM `"+n+"`\";\n";
	    System.out.println(insertpart1+" "+insertpart2);
	    System.out.println(update);
	    System.out.println(delete);
	    System.out.println(select);
	    
	    System.out.println();
	    System.out.println();
	    char nn = name[2].toLowerCase().charAt(0);
	    Method[] allMethods = cl.getDeclaredMethods() ;
	    String co = "Connection co = connection.getConnection();\n";
	    String ps1 = "try{\nPreparedStatement ps = co.prepareStatement(query);\n";
	    String result="List<"+name[2]+"> list_"+nn+" = new ArrayList<"+name[2]+">();\n"+co
	    		+ "ResultSet rs  = ps.executeQuery();\n"
	    		+ "while(rs.next()) {\r\n"
	    		+ ""+name[2]+" "+nn+" = new "+name[2]+"();\n";
	    
	    //int i = 1;
	   
	    for (Method m : allMethods) {
	    		String meth = m.getName();
	    		if(meth.startsWith("get")) {
	    			ps1+="ps.setObject(,"+nn+"."+meth+"());\n";
	    			//i+=1;
	    		}else {
	    			result+=nn+"."+meth+"(rs.getObject());\n";
	    		}
	    	
	    }
	    ps1+="ps.execute();\r\n"
	    		+ "ps.close();\n} catch (Exception e) {\r\n"
	    		+ "e.printStackTrace();\r\n"
	    		+ "}";
	    result+="list_"+nn+".add("+nn+");\n}";
	    System.out.println(co+""+ps1);
	    System.out.println();
	    System.out.println();
	    
	    System.out.println(result);
	}

}
