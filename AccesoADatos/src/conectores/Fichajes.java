package conectores;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Fichajes {
	public static void main(String[] args) {
		//equipos();
		//presidentes();
		//jugadores();
		//partidos();
		//goles();
		//nuevoPresidente("fffffff6", "Alberto", "Mantinez", "2020-02-02", 2020);
		//presidentes();
		//nuevoJugador("J0013", "Panza", "2010-03-04", "Delantero", "EQ001");
		//jugadores();
		//goleadores();
		//jugadores();
		//mFichajes("EQ004");
		//jugadores();
		//clasificacion();
		
	}
	public static void equipos() {
		//Muestra todos los datos de todos los equipos.

		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          rs=st.executeQuery("Select * From Equipos");
	          while (rs.next()) {                
	              System.out.println(rs.getString("Codigo")+" "+rs.getString("Nombre")+" "+rs.getString("Estadio")+" "+rs.getString("Aforo")
	              +" "+rs.getString("Fundacion")+" "+rs.getString("Ciudad")+" "+rs.getString("Presidente"));
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
	}
    public static void presidentes() {
    	//Muestra todos los datos de todos los presidentes.
    	Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          rs=st.executeQuery("Select * From Presidentes");
	          while (rs.next()) {                
	              System.out.println(rs.getString("Dni")+" "+rs.getString("Nombre")+" "+rs.getString("Apellidos")+" "+rs.getString("Fecha_nac")+" "+rs.getString("Anno"));
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
    }
	public static void jugadores() {
		//Muestra todos los datos de todos los jugadores.
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          rs=st.executeQuery("Select * From Jugadores");
	          while (rs.next()) {                
	              System.out.println(rs.getString("Codigo")+" "+rs.getString("Nombre")+" "+rs.getString("Fecha")+" "+rs.getString("Posicion")
	              +" "+rs.getString("Cod_equipo"));
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
		
	}
	public static void partidos() {
		//Muestra todos los datos de todos los partidos.
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          rs=st.executeQuery("Select * From Partidos");
	          while (rs.next()) {                
	              System.out.println(rs.getString("Codigo")+" "+rs.getString("Fecha")+" "+rs.getString("Equipo_local")
	              +" "+rs.getString("Equipo_visitante"));
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
		
	}
	public static void goles() {
		//Muestra todos los datos de todos los goles.
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          rs=st.executeQuery("Select * From Goles");
	          while (rs.next()) {                
	              System.out.println(rs.getString("Cod_partido")+" "+rs.getString("Minuto")+" "+rs.getString("Descripcion")
	              +" "+rs.getString("Cod_jugador")+" "+rs.getString("PropiaPuerta"));
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
	}
	public static void nuevoPresidente(String dni, String nombre, String
	apellidos, String fecha_nac, int anno) {
		//Inserta un nuevo registro en la tabla Presidentes. Se deben usar obligatoriamente los marcadores de posición.
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          
	          stmt = con.prepareStatement("INSERT INTO Presidentes(Dni, Nombre, Apellidos, Fecha_nac,Anno) VALUES (?, ?, ?,?,?)");

	          stmt.setString(1, dni);
	          stmt.setString(2, nombre);
	          stmt.setString(3,apellidos);
	          stmt.setString(4, fecha_nac);
	          stmt.setInt(5, anno);

	          stmt.executeUpdate();
	          
	          
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
	}
	public static void nuevoJugador(String codigo, String nombre, String fecha,
	String posicion, String equipo) {
		//Inserta un nuevo registro en la tabla Jugadores. Se deben usar obligatoriamente los marcadores de posición.
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
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
	          
	          stmt = con.prepareStatement("INSERT INTO Jugadores(Codigo, Nombre, Fecha, Posicion,Cod_equipo) VALUES (?, ?, ?,?,?)");

	          stmt.setString(1, codigo);
	          stmt.setString(2, nombre);
	          stmt.setString(3,fecha);
	          stmt.setString(4, posicion);
	          stmt.setString(5, equipo);

	          stmt.executeUpdate();
	          
	          
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
	}
	public static void goleadores() {
		//Muestra el código y nombre de todos los jugadores y su número de goles.
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
	      Statement st;
	      ResultSet rs;
	      ResultSet rs1;
	      ArrayList<Integer> lista = new ArrayList<Integer>();
	      int num =0;
	      String aux="";
	      String nombre="";
	      Boolean primer = true;
	      Boolean siguiente = false;
	    
	      try {
	          st=(Statement) con.createStatement();
	          
	          
	          rs=st.executeQuery("Select * From Jugadores,Goles where Jugadores.Codigo = Goles.Cod_jugador order by Jugadores.Codigo");
	          while (rs.next()) {
	        	  if(primer) {
	        		  num++;
	        		  primer = false;
	        		  
	        	  }else if(aux.equals(rs.getString("Codigo")
	        			  ) ||(aux!=rs.getString("Codigo")&&siguiente)){
	        		  siguiente = false;
	        		  num++;
	        		  
	        	  }
	        	  else if(aux!=rs.getString("Codigo")) {
	        		  System.out.println(aux+" "+nombre+" Goles= "+num);
	        		  siguiente = true;
	        		  num=1;
	        	  }

	        	
	        	  aux=rs.getString("Codigo");
	        	  nombre = rs.getString("Nombre");
	        			  
	              
	              
	              
	          }
	          con.close();
	      } catch (Exception e) {System.out.print(e);
	      }
		
	}
	public static void mFichajes(String equipo) {
		/*Recibe un equipo y cambia a ese equipo a
	todos los jugadores cuyo nombre empieza por M y tengan más de 2 goles. Debe hacerlo usando
	el procedimiento almacenado fichaje y marcadores de posición.*/
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
		  CallableStatement cs;
		  Statement st;
		 
			try {
				st=(Statement) con.createStatement();
				
				ResultSet rs;
			    
		    	  rs=st.executeQuery("Select Codigo From Jugadores group by Codigo");
		    	  cs=con.prepareCall("{CALL fichaje(?,?,?)}");
		    	  while(rs.next()) {
		    		  cs.setString(1, rs.getString("Codigo"));
		    		  cs.setString(2, equipo);
		    		  cs.setInt(3, 2);
		    		  cs.executeUpdate();		  
		    	  }
		          con.close();
		     
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void clasificacion() {
		/* Muestra en pantalla los nombres de todos los
	equipos y el número de goles. Debe hacerlo usando la función almacenada golesEquipo y
	marcadores de posición*/
		Connection con = null;
		try {
			
	  	  Class.forName("com.mysql.cj.jdbc.Driver");
	  
	  	  con=DriverManager.getConnection("jdbc:mysql://localhost/liga","usuario","usuario");
	    } catch (SQLException e) {
	        System.err.println("Error:" +e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Conectores cn=new Conectores();
	      
		  
		  Statement st;
		  Statement stFun;
		
			try {
				st=(Statement) con.createStatement();
				stFun=(Statement) con.createStatement();
				
				ResultSet rs;
				String sql = "{ ? = call golesEquipo (?) } ";
	    		// Preparamos la llamada
	    		CallableStatement llamada = con.prepareCall(sql);
	    		int numGoles;
			    
		    	 rs=st.executeQuery("Select Codigo,Nombre From Equipos group by Codigo");
		    	  while(rs.next()) {
		    		  
		    		// Establecemos los marcadores y registramos la salida
		    		llamada.registerOutParameter(1, Types.INTEGER);
		    		llamada.setString(2, rs.getString(1));
		    		llamada.executeUpdate();
		    		numGoles =llamada.getInt(1);
		    		System.out.println(rs.getString(2)+" "+Integer.toString(numGoles));  
		    		  	  
		    	  }
		          con.close();
		     
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
