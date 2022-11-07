package boletin1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*******************************************/
/* Este programa hace el cifrado correctamente,
 * pero no nombra el fichero de salida según las instrucciones.
 */
public class Cifrador {
	public static void main(String[] args) throws IOException {
		File fichero = new File("./src/datos/"+args[0]);
		String nombreaAr=args[0].substring(0,args[0].lastIndexOf("."));
		File ficheroAEscribir = new File("./src/datos/"+nombreaAr+".nuevo.txt");
		FileReader lectorPrincipal = new FileReader(fichero);
		FileWriter escritorPrincipal = new FileWriter(ficheroAEscribir);
		BufferedReader lectorBuffered = new BufferedReader(lectorPrincipal);
		BufferedWriter escritorBuffered = new BufferedWriter(escritorPrincipal);
		String abcd ="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		int numPosiciones=0;
		int num;
		int calculo=0;
		boolean signo=false;
		
		char letra;
		char cifrado;
		boolean check=true;
		boolean numCorrecto = true;
		ficheroAEscribir.createNewFile();
		try {Integer.parseInt(args[1]);}
		catch(Exception ent) {
			numCorrecto=false;
		}
		
		if(fichero.exists()&&args.length==2&&numCorrecto) {
			numPosiciones = Integer.parseInt(args[1]);
			if(numPosiciones<0) {
				signo=false;
			}else {
				signo=true;
			}
			while((num= lectorBuffered.read())!=-1) {
				check=true;
				letra = (char)num;
				
				for(int i=0;i<abcd.length();i++) {
					/*if(signo) {
						calculo=i-numPosiciones;
					}
					else {
						calculo=i+numPosiciones;
					}*/
					
					/*Terminar , que la suma entre el numero de posiciones mas i no sea mayor que el numero de caracteres*/
					
					if(abcd.charAt(i)==letra) {
						calculo=i+numPosiciones;
				
						while(calculo>abcd.length()) {
							calculo=calculo-27;
							System.out.print(calculo);
						}
						
						while(calculo<0) {
								calculo+=27;
						}
						
						if(calculo<abcd.length()) {
							escritorBuffered.write(abcd.charAt(calculo));
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