package conectores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * Ejemplo de acceso a una BD HSQLDB
 * Observa que en el conector es necesaria la barra final
 * 
 */
public class AD04_JDBCHsqldb {
	public static final String DRIVER = "org.hsqldb.jdbcDriver";
	public static final String CONECTOR = "jdbc:hsqldb:file:src/db/liga.Hsqldb/";
	
	public static void main(String[] argvs) {
		try {
			// Cargar el driver
			Class.forName(DRIVER);

			// Establecer la conexión con la BD
			Connection conexion = DriverManager.getConnection(CONECTOR);

			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet respuesta = sentencia.executeQuery("SELECT * FROM Equipos");

			// Recorremos la respuesta para visualizar cada fila
			while (respuesta.next()) {
				System.out.println(respuesta.getString("Nombre") + " " + respuesta.getString("Ciudad"));
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
}
