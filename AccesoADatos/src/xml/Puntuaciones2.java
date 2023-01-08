package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

import anexo.Puntuacion;


public class Puntuaciones2 {
	private static String RUTA_A_ESCRIBIR = "./src/datos/puntuaciones.xml";
	private static String RUTA_A_LEER = "./src/datos/puntuaciones.dat";
	
	public static void main(String[] args) {
		File fichero = new File(RUTA_A_LEER);
		FileInputStream fisFichero;
		ObjectInputStream oisFichero;
		Puntuacion punt;
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<String> minutos = new ArrayList<>();
		ArrayList<String> segundos = new ArrayList<>();
		ArrayList<String> fecha = new ArrayList<>();
		String numeros = "";
		
		if(fichero.exists()) {
			try {
				fisFichero = new FileInputStream(fichero);
				oisFichero = new ObjectInputStream(fisFichero);
				while (fisFichero.available() > 0) {
					punt =(Puntuacion) oisFichero.readObject();
					nombres.add(punt.getNombre());
					numeros = String.valueOf(punt.getSegundos());
					segundos.add(numeros);
					
				}
				
				oisFichero.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Clase no encontrada");
			}
		}
		
		
	}
}
