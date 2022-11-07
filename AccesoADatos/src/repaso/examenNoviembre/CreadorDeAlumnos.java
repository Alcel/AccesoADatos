package repaso.examenNoviembre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreadorDeAlumnos {
	public static void main(String[] args) {
		String rutaNombre= "./src/repaso/datos/NombresComas.txt";
		String rutaGrupos= "./src/repaso/datos/GruposSaltosdeLinea.txt";
		String rutaMedias= "./src/repaso/datos/MediasComas.txt";
		String rutaNuevosAlumnos= "./src/repaso/datos/MediasComas.txt";
		ArrayList<Alumno> alumno;
		File nuevosAlumnos = new File(rutaNuevosAlumnos);
		File nombre = new File(rutaNombre);
		File grupos = new File(rutaGrupos);
		File medias = new File(rutaMedias);
		FileReader lectorNombre=null;
		FileReader lectorGrupos=null;
		FileReader lectorMedias=null;
		BufferedReader brNombre=null;
		BufferedReader brGrupos=null;
		BufferedReader brMedias=null;
		
		/*Captura de errores: dar valor a readers y nuevo archivo*/
		try {
		nuevosAlumnos.createNewFile();
		}
		catch(IOException io) {
			System.err.println("Error al crear un nuevo archivo");
		}
		try{
			lectorNombre = new FileReader(rutaNombre);
			lectorGrupos = new FileReader(rutaGrupos);
			lectorMedias = new FileReader(rutaMedias);
		}
		catch(FileNotFoundException fnfe) {
			System.err.println("Archivo no encontrado. Compruebe las rutas.");
		}
		/*-------------------------------------------------------------------*/
		
		
		
		brNombre = new BufferedReader(lectorNombre);
		brGrupos = new BufferedReader(lectorGrupos);
		brMedias = new BufferedReader(lectorMedias);
		
		
		try {
			System.out.println(brNombre.readLine());
			System.out.println(brMedias.readLine()); 
		} 
		catch (IOException e) {
			System.err.println("Error en lectura");
		}	
	}
}