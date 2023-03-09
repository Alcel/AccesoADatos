package conectores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conectores {

    
  public Conectores(){
      
  }
  public static void main(String[] args) {
    //pediatras();
    //recientes();
	mayores();
	//telefonos();
  }
  private static void mayores() {
	  String DRIVER = "org.hsqldb.jdbcDriver";
		String CONECTOR = "jdbc:hsqldb:file:src/db/consultorio.Hsqldb/";
		
			try {
				// Cargar el driver
				Class.forName(DRIVER);

				// Establecer la conexión con la BD
				Connection conexion = DriverManager.getConnection(CONECTOR);

				// Preparar la consulta
				Statement sentencia = conexion.createStatement();
				ResultSet respuesta = sentencia.executeQuery("SELECT * FROM Doctores where edad>50");

				// Recorremos la respuesta para visualizar cada fila
				while (respuesta.next()) {
					System.out.println(respuesta.getString("Nombre")+" "+respuesta.getString("Especialidad"));
				}

				respuesta.close();
				sentencia.close();
				conexion.close();

			} catch (ClassNotFoundException cnfE) {
				cnfE.printStackTrace();
			}  catch (SQLException sqlE) {
				System.err.println("\nHubo problemas con la base de datos:");
				System.err.println("Mensaje:\t"+sqlE.getMessage());
				System.err.println("Estado SQL:\t"+sqlE.getSQLState());
				System.err.println("Codigo Error:\t"+sqlE.getErrorCode());
			}
		
	
}
private static void recientes() {
	  final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	  final String CONECTOR = "jdbc:derby:src/db/consultorio.Apache";

		
			try {
				// Cargar el driver
				Class.forName(DRIVER);

				// Establecer la conexión con la BD
				Connection conexion = DriverManager.getConnection(CONECTOR);

				// Preparar la consulta
				Statement sentencia = conexion.createStatement();
				ResultSet respuesta = sentencia.executeQuery("SELECT * FROM Pacientes,Visitas Where Pacientes.Nif=Visitas.Nif_paciente and Visitas.Fecha >= '2015-06-01'");

				// Recorremos la respuesta para visualizar cada fila
				while (respuesta.next()) {
					System.out.println(respuesta.getString("Nombre"));
				}

				respuesta.close();
				sentencia.close();
				conexion.close();

			} catch (ClassNotFoundException cnfE) {
				cnfE.printStackTrace();
			}  catch (SQLException sqlE) {
				System.err.println("\nHubo problemas con la base de datos:");
				System.err.println("Mensaje:\t"+sqlE.getMessage());
				System.err.println("Estado SQL:\t"+sqlE.getSQLState());
				System.err.println("Codigo Error:\t"+sqlE.getErrorCode());
			}
		
	
	
  }
public static void pediatras() {
	Connection con = null;
	try {
		
  	  Class.forName("org..JDBC");
  
        con=DriverManager.getConnection("jdbc::src/db/consultorio..db");
        System.out.println("conexion establecida");
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
          rs=st.executeQuery("Select * From Doctores where Especialidad = 'pediatra'");
          while (rs.next()) {                
              System.out.println(rs.getString("Nombre"));
          }
          con.close();
      } catch (Exception e) {System.out.print(e);
      }
	  
  }
public static void telefonos() {
	String DRIVER = "org.h2.Driver";
    String CONECTOR = "jdbc:h2:./src/db/consultorio.H2";
	try {
		// Cargar el driver
		Class.forName(DRIVER);

		// Establecer la conexión con la BD
		Connection conexion = DriverManager.getConnection(CONECTOR);

		// Preparar la consulta
		Statement sentencia = conexion.createStatement();
		ResultSet respuesta = sentencia.executeQuery("SELECT count(Nif_doctor) FROM Visitas");

		// Recorremos la respuesta para visualizar cada fila
		while (respuesta.next()) {
			System.out.println(respuesta.getString(1));
		}

		respuesta.close();
		sentencia.close();
		conexion.close();

	} catch (ClassNotFoundException cnfE) {
		cnfE.printStackTrace();
	} catch (SQLException sqlE) {
		System.err.println("\nHubo problemas con la base de datos:");
		System.err.println("Mensaje:\t"+sqlE.getMessage());
		System.err.println("Estado SQL:\t"+sqlE.getSQLState());
		System.err.println("Codigo Error:\t"+sqlE.getErrorCode());
	}
}

}
