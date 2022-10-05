package boletin1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copion {
	private static String entrada;
	private static String salida;
	private static String palabra;
	
	public Copion(String entrada,String salida,String palabra) {
		this.entrada=entrada;
		this.salida=salida;
		this.palabra=palabra;
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader input = new BufferedReader(new FileReader(entrada));
			BufferedWriter output = new BufferedWriter(new FileWriter(salida));
			String line = input.readLine();
			
			
				input.close();
				output.close();
			}
		catch(IOException ex) {
			System.out.println("Error");
		}
	}

}
