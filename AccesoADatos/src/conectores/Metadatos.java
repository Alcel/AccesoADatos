package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Metadatos {
	
	public static void main(String[] args) {
		Connection con = null;	
	
	try {
		
	  	  Class.forName("org.sqlite.JDBC");
	  
	        con=DriverManager.getConnection("jdbc:sqlite:src/db/Jardineria");
	        
	        System.out.println("conexion establecida");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
	      Statement st;
	      ResultSet filas;
	      ResultSet rs;
	      int contador=0;
	      System.out.println("Tablas:");
	      
	      
	      try {
	    	  
	          st=(Statement) con.createStatement();
	          rs=con.getMetaData().getTables(null, null, "%", null);
	          
	          while (rs.next()) {          
	        	  if(!rs.getString(3).startsWith("sqlite_")) {
	        		  filas =st.executeQuery("SELECT count(*) FROM "+rs.getString(3));
	              System.out.println("	Tabla "+rs.getString(3)+": "+filas.getString(1)+" registros");
	              for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
	            	 // System.out.println(rs.getMetaData().getCol);
	              }
	              
	              
	              contador++;
	              
	              }
	        	  
	          }
	          rs=con.getMetaData().getProcedures(null, null, null);
	          while(rs.next()) {
	        	  System.out.println("Subprogramas soportados/ no soportados");
	        	  System.out.println(rs.getString("PROCEDURE_NAME")+" "+rs.getString("PROCEDURE_TYPE"));
	          }
	          con.close();
	          System.out.println();
	          System.out.println("Total de tablas: "+contador);
	      } catch (Exception e) {System.out.print(e);
	      }
		  
	}
}
