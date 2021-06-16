package defaultpack;

import java.lang.reflect.Field;


import visiteur.models.Visiteur;

public class seqrch {

	public static void main(String[] args) {
		 Class<?> cl = Visiteur.class;
		 Field[] fields = cl.getDeclaredFields();
		 String select = "String query = \"SELECT ";
		 String selectpart2 = "WHERE ";
		 for(int i=0;i<fields.length;i++){
		    	String fe = fields[i].getName();
		    	select+="`"+fe+"`";
		    	selectpart2 +="`"+fe+"`"+"=? ";
		    	if(i != fields.length-1) {
		    		select+=",";
		    		selectpart2+="\nOR";
		    	}
		    	
		    }
		 selectpart2+="\";";
		  System.out.println(select);
		  System.out.println(selectpart2);
	}

}
