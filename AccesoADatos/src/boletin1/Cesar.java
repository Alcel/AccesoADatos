package boletin1;
import java.io.*;
public class Cesar {
	public static void main(String[] args) throws IOException {
		File fichero = new File("./src/datos/"+args[0]);
		File ficheroAEscribir = new File("./src/datos/"+args[0]+".cifrado");
		FileReader lectorPrincipal = new FileReader(fichero);
		FileWriter escritorPrincipal = new FileWriter(ficheroAEscribir);
		BufferedReader lectorBuffered = new BufferedReader(lectorPrincipal);
		BufferedWriter escritorBuffered = new BufferedWriter(escritorPrincipal);
		String abcd ="ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		int num;
		char letra;
		char cifrado;
		boolean check=true;
		
		if(fichero.exists()&&args.length==2) {
			while((num= lectorBuffered.read())!=-1) {
				check=true;
				letra = (char)num;
				System.out.print(letra);
				for(int i=0;i<abcd.length();i++) {
					if(abcd.charAt(i)==letra) {
						if(i>=24&&i<27||i>=(24+27)&&i<(27+27)) {
							escritorBuffered.write(abcd.charAt(i-24));
							check=false;
						}
						else {
							escritorBuffered.write(abcd.charAt(i+3));
							check=false;
						}
					}
				}
				letra = (char)num;
				
				if(check) {
					System.out.println("si");
					escritorBuffered.write(letra);
					}	
				}
			}
		else {
			System.err.println("Error, no existe el archivo o la ruta esta mal");
		}
		escritorBuffered.close();
		}
}