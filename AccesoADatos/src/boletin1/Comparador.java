package boletin1;
import java.io.*;
public class Comparador {
	public static void main(String[] args) {
		String sistemaOperativo = System.getProperty("os.name");
		String fichero1 = "."+File.separator +"src"+File.separator +"boletin1"+File.separator+"datos"+File.separator +args[0];
		String fichero2 ="."+File.separator +"src"+File.separator +"boletin1"+File.separator+"datos"+File.separator +args[1];
		File fFichero1 = new File(fichero1);
		File fFichero2 = new File(fichero2); 
		FileReader reader1 = null;
		String thisLine=null;
		int numero=0;
		try {
			reader1 = new FileReader(fichero1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileReader reader2 = null;
		try {
			reader2 = new FileReader(fichero2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader primer = new BufferedReader(reader1);
 		BufferedReader segun = new BufferedReader(reader2); 
			
			System.out.println("Hola");
			if(fFichero1.exists() && fFichero2.exists()) {
				try {
					while((thisLine = primer.readLine())!=null && numero==0) {
						if(primer.readLine()!=segun.readLine()) {
							//TODO
							//System.err.println("Error en linea"+/*TERMINAR*/);
							numero=1;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {

		    
				System.err.println("Alguna ruta no es correcta");
			}
	}
}