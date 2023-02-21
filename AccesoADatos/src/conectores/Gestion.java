package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

public class Gestion {
	public static void main(String[] args) {
		//visitas();
		//nuevoDoctor("00000001A","Jose Luis", 119, "general");
		//doctores();
		//nuevoPaciente("00007451K","Juan Alberto","112");
		
	}
	public static void doctores() {
		Connection con = null;
		try {
			
	  	  Class.forName("org.sqlite.JDBC");
	  
	        con=DriverManager.getConnection("jdbc:sqlite:src/db/consultorio.SQLite.db");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
	      Statement st;
	      ResultSet rs;
	      try {
	          st=(Statement) con.createStatement();
	          rs=st.executeQuery("Select * From Doctores");
	          while (rs.next()) {                
	              System.out.println(rs.getString("Nif")+" "+rs.getString("Nombre")+" "+rs.getString("Edad")+" "+rs.getString("Especialidad"));
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
	}
	public static void pacientes() {
			Connection con = null;
			try {
				
		  	  Class.forName("org.sqlite.JDBC");
		  
		        con=DriverManager.getConnection("jdbc:sqlite:src/db/consultorio.SQLite.db");
		    } catch (SQLException e) {
		        System.err.println("Error:" +e);
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  Conectores cn=new Conectores();
		      
		      Statement st;
		      ResultSet rs;
		      try {
		          st=(Statement) con.createStatement();
		          rs=st.executeQuery("Select * From Pacientes");
		          while (rs.next()) {                
		              System.out.println(rs.getString("Nif")+" "+rs.getString("Nombre")+" "+rs.getString("Telefono"));
		          }
		          con.close();
		      } catch (Exception e) {System.out.print(e);
		      }
		
	}
	public static void visitas() {
		Connection con = null;
			try {
				
		  	  Class.forName("org.sqlite.JDBC");
		  
		        con=DriverManager.getConnection("jdbc:sqlite:src/db/consultorio.SQLite.db");
		    } catch (SQLException e) {
		        System.err.println("Error:" +e);
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  Conectores cn=new Conectores();
		      
		      Statement st;
		      ResultSet rs;
		      try {
		          st=(Statement) con.createStatement();
		          rs=st.executeQuery("Select * From Visitas");
		          while (rs.next()) {                
		              System.out.println(rs.getString("Nif_doctor")+" "+rs.getString("Nif_paciente")+" "+rs.getString("Fecha"));
		          }
		          con.close();
		      } catch (Exception e) {System.out.print(e);
		      }
		
	}
	public static void nuevoDoctor(String nif, String nombre, int edad, String
			especialidad) {
		Connection con = null;
		try {
			
	  	  Class.forName("org.sqlite.JDBC");
	  
	        con=DriverManager.getConnection("jdbc:sqlite:src/db/consultorio.SQLite.db");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
	      PreparedStatement stmt;
	      ResultSet rs;
	      try {
	         
	          stmt = con.prepareStatement("INSERT INTO Doctores(Nif, Nombre, Edad, Especialidad) VALUES (?, ?, ?,?)");

	          stmt.setString(1, nif);
	          stmt.setString(2, nombre);
	          stmt.setInt(3,edad);
	          stmt.setString(4, especialidad);

	          stmt.executeUpdate();
	          
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
		
	}
	public static void nuevoPaciente(String nif, String nombre, String tfno) {
		//Inserta un nuevo registro en la tabla Pacientes.
		Connection con = null;
		try {
			
	  	  Class.forName("org.sqlite.JDBC");
	  
	        con=DriverManager.getConnection("jdbc:sqlite:src/db/consultorio.SQLite.db");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
	      PreparedStatement stmt;
	      ResultSet rs;
	      try {
	         
	          stmt = con.prepareStatement("INSERT INTO Pacientes(Nif, Nombre, Tfno) VALUES (?, ?, ?)");

	          stmt.setString(1, nif);
	          stmt.setString(2, nombre);
	          stmt.setString(3,tfno);

	          stmt.executeUpdate();
	          
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
		
	}
	public static void nuevaVisita(String doctor, String paciente, String fecha){
		//Inserta un nuevo registro en la tabla Visitas.
		Connection con = null;
		try {
			
	  	  Class.forName("org.sqlite.JDBC");
	  
	        con=DriverManager.getConnection("jdbc:sqlite:src/db/consultorio.SQLite.db");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
	      PreparedStatement stmt;
	      ResultSet rs;
	      try {
	         
	          stmt = con.prepareStatement("INSERT INTO Doctores(Nif, Nombre, Fecha) VALUES (?, ?, ?)");

	          stmt.setString(1, doctor);
	          stmt.setString(2, paciente);
	          stmt.setString(3, fecha);

	          stmt.executeUpdate();
	          
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
	}
	public static void cumpleanos(String doctor) {
		//aumenta la edad en 1 del doctor indicado por DNI.
	}
		
	 public static void tfnoFijo() {
		 //modifica los 3 primeros dígitos del teléfono de todos los pacientes a 950.
	 }
	
	

}
