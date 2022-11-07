package repaso.examenNoviembre;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class CreadorDeAlumnos {
	
	public static String readFile(String path, Charset encoding) throws IOException {
        return Files.readString(Paths.get(path), encoding);
    }
	
	public static void main(String[] args) {
		String rutaNombre= "./src/repaso/datos/NombresComas.txt";
		String rutaGrupos= "./src/repaso/datos/GruposSaltosdeLinea.txt";
		String rutaMedias= "./src/repaso/datos/MediasComas.txt";
		String rutaNuevosAlumnos= "./src/repaso/datos/MediasComas.txt";
		String textoTomado;
		ArrayList <String> nombresLista;
		ArrayList <String> mediasLista;
		ArrayList <String> gruposLista;
		ArrayList<Alumno> alumnoLista;
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
			
			System.out.println(brMedias.readLine()); 
			textoTomado= brNombre.readLine();
			nombresLista = new ArrayList<String>(Arrays.asList(textoTomado.split(",")));
			System.out.println(nombresLista.get(2));
			textoTomado="";
			textoTomado = readFile(rutaGrupos, StandardCharsets.UTF_8);
			gruposLista = new ArrayList<String>(Arrays.asList(textoTomado.split("\\n")));
			System.out.println(gruposLista.get(5));
			
		} 
		catch (IOException e) {
			System.err.println("Error en lectura");
		}	
	
	}
}