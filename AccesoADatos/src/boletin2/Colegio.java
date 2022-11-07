package boletin2;

import java.io.File;
import java.io.IOException;

public class Colegio {
	private static String ruta ="./src/datos/alumno.dat";
	private static File f = new File(ruta);
	public static void main(String[] args) {
		try {
			f.createNewFile();
		} catch (IOException e) {
			System.err.println("Error el creacion de archivo");
		}
		
		
	}
	public static void nuevoAlumno(int id, String nombre, float calificacion) {
		
	}
	public static void alumno(int id) {
		
	}
	public static void cambiaNota(int id) {
		
	}
	public static void borraAlumno(int id) {
		
	}

}
