package boletin2;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/* Ejemplo de uso de ObjectInputStream.
 * Recorremos el fichero leyendo los objetos
 * e imprimimos los datos en pantalla.
 */
public class AD13_LeerFichObject {
	public final static String RUTA_FICHERO = "./src/datos/archivo";

	public static void main(String[] args) {
		Empleado emple;

		try {
			// Asociamos un objeto File al fichero del sistema
			File fichero = new File(RUTA_FICHERO);
			// Necesitamos un FileInputStream para asociarlo al File
			FileInputStream fisFichero = new FileInputStream(fichero);
			// Necesitamos un ObjectInputStream para leer objetos
			ObjectInputStream oisFichero = new ObjectInputStream(fisFichero);

			/*
			 * Observa que el bucle está controlado por el método available() pero aplicado
			 * al FileInputStream que devuelve 0 cuando no hay más para leer Si lo aplicas a
			 * ObjectInputStream devuelve 0 cuando el número de objetos es desconocido y, en
			 * este caso, nunca entraría en el bucle
			 */
			while (fisFichero.available() > 0) {
				// La lectura del fichero devuelve un objeto sin concretar
				// Hacemos un casting para la clase de objeto que esperamos leer
				emple = (Empleado) oisFichero.readObject();
				System.out.printf("Nombre: %8s\t edad: %d\t cargo: %15s\t sueldo: %.2f\n", emple.getNombre(),
						emple.getEdad(), emple.isJubilado(), emple.getSueldo());
			}
			// Cerramos el flujo
			oisFichero.close();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		} catch (ClassNotFoundException cnfE) {
			cnfE.printStackTrace();
		}
	}
}
