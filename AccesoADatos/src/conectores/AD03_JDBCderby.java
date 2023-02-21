package conectores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/* Ejemplo de conexión a una BD Apache Derby
 */ 
public class AD03_JDBCderby {
//	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver"; //Funciona con derby 10.11.1
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String CONECTOR = "jdbc:derby:src/db/consultorio.Apache";

	public static void main(String[] argvs) {
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
}
