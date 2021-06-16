package defaultpack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;


import visiteur.models.Visiteur;

public class prepqrstat {
public static void main(String[] args) {
	Class<?> cl = Visiteur.class;
	
    Field[] fields = cl.getDeclaredFields();

    Method[] allMethods = cl.getDeclaredMethods() ;
    String[] name = cl.getName().split("\\.");
    char n = name[2].charAt(0);
    String co = "Connection co = connection.getConnection();\n";
    String ps = "try{\nPreparedStatement ps = co.prepareStatement(query);\n";
    String setters="";
    int i = 1;
   
    for (Method m : allMethods) {
    		String meth = m.getName();
    		if(meth.startsWith("get")) {
    			ps+="ps.setObject(,"+n+"."+meth+"());\n";
    			//i+=1;
    		}else {
    			setters+=n+"."+meth+"(rs.);\n";
    		}
    	
    }
    ps+="ps.execute();\r\n"
    		+ "ps.close();\n} catch (Exception e) {\r\n"
    		+ "// TODO: handle exception\r\n"
    		+ "}";
    System.out.println(co+""+ps);
    System.out.println(setters);
}
}
