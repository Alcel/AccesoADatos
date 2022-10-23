package boletin2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/* Ejemplo de ObjectOutputStream
 * Se escriben los objetos de la clase AD12_Empleado,
 * que es serializable, en un fichero, a través 
 * de un stream de salida.
 */
public class AD12_EscribirFichObjeto {
	public final static String RUTA_FICHERO = "./src/datos/archivo";

	public static void main(String[] args) {
		// Inicializamos los datos en arrays
		String nombres[] = { "Lola", "Rodolfo", "Carmen", "Luís", "Guillermo", "Benito", "Olga", "Oscar", "Concha" };
		int edades[] = { 27, 32, 40, 22, 55, 31, 41, 45, 62 };
		Boolean cargos[] = { false, true, true, false, false, false,
				false, true, false };
		double sueldos[] = { 900.28, 900.43, 1100.65, 1000.75, 1600.86, 1100.43, 2500.12, 1400.40, 2300.53 };

		Empleado emple;

		try {
			// Asociamos un objeto File al fichero del sistema
			File fichero = new File(RUTA_FICHERO);
			// Necesitamos un FileOutputStream para asociarlo al File
			FileOutputStream fosFichero = new FileOutputStream(fichero);
			// Creamos un stream para objetos
			ObjectOutputStream oosFichero = new ObjectOutputStream(fosFichero);

			// Recorremos los arrays creando objetos y los enviamos al stream
			for (int i = 0; i < edades.length; i++) {
				emple = new Empleado(nombres[i], edades[i], sueldos[i], cargos[i]);
				oosFichero.writeObject(emple);
			}
			// Cerramos el flujo
			oosFichero.close();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}
	}
}
