package boletin2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Colegio {
	private static final String FICHERO =
		"." + File.separator +
		"src" + File.separator +
		"datos" + File.separator +
		"alumnado.dat";

	// Longitud del nombre
	private static final int NOMBRE_LENGTH = 20;
	
	// int (4) + String (NOMBRE_SIZE * 2) + float (4)
	// Cada caracter del String son 2 bytes, por eso se multiplica por 2
	//En otras palabras 4 bytes de int,4 de float y 2 por letra = 40 letras(char)
	private static final int REGISTRO_SIZE = 4 + (NOMBRE_LENGTH * 2) + 4;
	
	public static void main(String[] args) {
		Colegio.nuevoAlumno(11, "Albert", 5.8f);
		//Colegio.alumno(7);
		//Colegio.cambiaNota(500);
		//Colegio.alumno(5);
		//Colegio.borraAlumno(5);
		//Colegio.alumno(30);
	}
	
	/*
	 * Añade un nuevo alumno en el fichero alumnado.dat, en la posición que indica el identificador.
	 * Si ya existe un alumno con ese identificador o es un identificador incorrecto (menor que 0), avisa
	 * y no lo inserta
	 */
	public static void nuevoAlumno(int id, String nombre, float calificacion) {
		long posicion = (id - 1) * REGISTRO_SIZE;
		
		// El identificador es negativo
		if (posicion < 0) {
			System.err.println("No se ha podido crear nuevo alumno, el identificador es inválido");
		} else {
			File fichero = new File(FICHERO);
			
			try {
				RandomAccessFile rafFichero = new RandomAccessFile(fichero, "rw");
				int idFichero = 0;
				
				rafFichero.seek(posicion);
				
				// Comprobamos que el fichero no está vacío y
				// leemos el identificador de la posición actual
				if (rafFichero.length() > 0 && rafFichero.length()>posicion) {
					idFichero = rafFichero.readInt();
				}
				
				
				
				
				// El identificador ya existe
				if (rafFichero.getFilePointer() <= rafFichero.length() && idFichero != 0) {
					System.err.println("No se ha podido crear nuevo alumno, el identificador indicado ya existe");
				} else {
					// Volvemos a la posición en la que estábamos antes de leer el identificador
					rafFichero.seek(posicion);
					
					// Escribimos el id
					rafFichero.writeInt(id);
					
					// Ajustamos el nombre a NOMBRE_SIZE caracteres y lo escribimos
					StringBuilder sb = new StringBuilder(nombre);
					sb.setLength(NOMBRE_LENGTH);
					rafFichero.writeChars(sb.toString());

					// Escribimos la calificación
					rafFichero.writeFloat(calificacion);
				}
				
				rafFichero.close();
			} catch (FileNotFoundException e) {
				System.err.println("No se ha podido crear nuevo alumno, no se ha encontrado el fichero");
			} catch (IOException e) {
				System.err.println("No se ha podido crear nuevo alumno, error de E/S");
			}
		}
	}
	
	/*
	 * Muestra en pantalla los datos de alumno cuyo identificador se pasa
	 * como argumento. Si no existe el alumno, lo comunica
	 */
	public static void alumno(int id) {
		long posicion = (id - 1) * REGISTRO_SIZE;
		
		// El identificador es negativo
		if (posicion < 0) {
			System.err.println("No se ha podido consultar alumno, el identificador es inválido");
		} else {
			File fichero = new File(FICHERO);
			int idFichero = 0;
			char[] nombreChars = new char[NOMBRE_LENGTH];
			String nombre = "";
			
			try {
				RandomAccessFile rafFichero = new RandomAccessFile(fichero, "r");
				rafFichero.seek(posicion);

				// El identificador es demasiado grande
				if (rafFichero.getFilePointer() >= rafFichero.length()) {
					System.err.println("No se ha podido consultar alumno, el identificador es inválido");
				} else {
					idFichero = rafFichero.readInt();
					
					if (idFichero == 0) {
						System.err.println("No se ha podido consultar alumno, el identificador es inválido");
					} else {
						System.out.printf("%s\t%20s\t%s\n", "ID", "Nombre", "Calificación");
						
						// Leemos el identificador del alumno
						System.out.printf("%d\t", idFichero);
						
						// Leemos el nombre del alumno, carácter por carácter
						for (int i = 0; i < nombreChars.length; i++) {
							nombreChars[i] = rafFichero.readChar();
						}
						
						nombre = String.valueOf(nombreChars).trim();
						
						System.out.printf("%20s\t", nombre);
						
						// Leemos la calificación del alumno
						System.out.println(rafFichero.readFloat());
					}
				}
				
				rafFichero.close();
			} catch (FileNotFoundException e) {
				System.err.println("No se ha podido consultar alumno, no se ha encontrado el fichero");
			} catch (IOException e) {
				System.err.println("No se ha podido consultar alumno, error de E/S");
			}
		}
	}
	
	/*
	 * Sube 0,5 la calificación del alumno indicado, sin
	 * superar el valor 10. Si no existe el alumno, lo comunica.
	 */
	public static void cambiaNota(int id) {
		long posicion = (id - 1) * REGISTRO_SIZE;
		
		// El identificador es negativo
		if (posicion < 0) {
			System.err.println("No se ha podido cambiar la nota, el identificador es inválido");
		} else {
			File fichero = new File(FICHERO);
			
			try {
				RandomAccessFile rafFichero = new RandomAccessFile(fichero, "rw");
				long posNota;
				float calificacion = 0.0f;
				
				rafFichero.seek(posicion);
				
				// El identificador es demasiado grande
				if (rafFichero.getFilePointer() >= rafFichero.length()) {
					System.err.println("No se ha podido cambiar la nota, el identificador es inválido");
				} else {
					// Saltamos el identificador y el nombre del alumno
					posNota = rafFichero.skipBytes(4 + (NOMBRE_LENGTH * 2));
					calificacion = rafFichero.readFloat() + 0.5f;
					
					if (calificacion > 10.0f) {
						calificacion = 10.0f;
					}
					
					// Volvemos a ponernos sobre la posición de la calificación
					// ya que antes hemos leído un valor y hemos saltado 4 bytes
					rafFichero.seek(posicion + posNota);
					rafFichero.writeFloat(calificacion);
				}
				
				rafFichero.close();
			} catch (FileNotFoundException e) {
				System.err.println("No se ha podido cambiar la nota, no se ha encontrado el fichero");
			} catch (IOException e) {
				System.err.println("No se ha podido cambiar la nota, error de E/S");
			}
		}
	}
	
	/*
	 * Elimina el alumno indicado con un borrado
	 * lógico (cambiar su id a 0). Si no existe el alumno, lo comunica.
	 */
	public static void borraAlumno(int id) {
		long posicion = (id - 1) * REGISTRO_SIZE;
		
		// El identificador es negativo
		if (posicion < 0) {
			System.err.println("No se ha podido borrar alumno, el identificador es inválido");
		} else {
			File fichero = new File(FICHERO);
			
			try {
				RandomAccessFile rafFichero = new RandomAccessFile(fichero, "rw");
				rafFichero.seek(posicion);
				
				// El identificador es demasiado grande
				if (rafFichero.getFilePointer() >= rafFichero.length()) {
					System.err.println("No se ha podido borrar alumno, el identificador es inválido");
				} else {
					// Establecemos el identificador a 0 (borrado lógico)
					rafFichero.writeInt(0);
				}
				
				rafFichero.close();
			} catch (FileNotFoundException e) {
				System.err.println("No se ha podido borrar alumno, no se ha encontrado el fichero");
			} catch (IOException e) {
				System.err.println("No se ha podido borrar alumno, error de E/S");
			}
		}
	}
}
