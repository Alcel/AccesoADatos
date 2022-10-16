package boletin1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cifrador {
	public static void main(String[] args) throws IOException {
		File fichero = new File("./src/datos/"+args[0]);
		File ficheroAEscribir = new File("./src/datos/"+args[0]+".nuevo");
		FileReader lectorPrincipal = new FileReader(fichero);
		FileWriter escritorPrincipal = new FileWriter(ficheroAEscribir);
		BufferedReader lectorBuffered = new BufferedReader(lectorPrincipal);
		BufferedWriter escritorBuffered = new BufferedWriter(escritorPrincipal);
		String abcd ="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		int numPosiciones=0;
		int num;
		int suma=0;
		char letra;
		char cifrado;
		boolean check=true;
		boolean numCorrecto = true;
		try {Integer.parseInt(args[1]);}
		catch(Exception ent) {
			numCorrecto=false;
		}
		
		if(fichero.exists()&&args.length==2&&numCorrecto) {
			numPosiciones = Integer.parseInt(args[1]);
			while((num= lectorBuffered.read())!=-1) {
				check=true;
				letra = (char)num;
				
				for(int i=0;i<abcd.length();i++) {
					suma=i+numPosiciones;
					/*Terminar , que la suma entre el numero de posiciones mas i no sea mayor que el numero de caracteres*/
					
					if(abcd.charAt(i)==letra) {
						
				
						while(suma>abcd.length()) {
							suma=suma-27;
							System.out.print(suma);
						}
						if(suma<abcd.length()) {
							escritorBuffered.write(abcd.charAt(suma));
						}
						
						
						//System.out.print(abcd.charAt(suma));
						check=false;
					}
				}
				letra = (char)num;
				
				if(check) {
					System.out.print(" ");
					escritorBuffered.write(letra);
					}	
				}
			}
		else {
			System.err.println("Error");
			System.err.println("Compruebe la ruta del fichero, o el numero o tipo de argumentos");
		}
		escritorBuffered.close();
		}
}