package boletin1;
import java.io.File;
public class Comparador {
	public static void main(String[] args) {
		String sistemaOperativo = System.getProperty("os.name");
		String fichero1;
		String fichero2;
		File fFichero1 = null; //file.separator comprobar
		File fFichero2 = null;
		
			fichero1="."+File.separator +"src"+File.separator +"boletin1"+File.separator+"datos"+File.separator +args[0];
			fichero2="."+File.separator +"src"+File.separator +"boletin1"+File.separator+"datos"+File.separator +args[1];
			fFichero1= new File(fichero1);
			fFichero2= new File(fichero2);
			System.out.println("Hola");
			if(fFichero1.exists() && fFichero2.exists()) {
				System.out.println("Ambos existen");
			}
			else {
				System.err.println("Adios");
			}
	}
}