package boletin1;
import java.io.File;
public class Comparador {
	public static void main(String[] args) {
		String sistemaOperativo = System.getProperty("os.name");
		String fichero1;
		String fichero2;
		File fFichero1 = null; //file.separator comprobar
		File fFichero2 = null;
		if(sistemaOperativo.startsWith("Win")) {
			fichero1=".\\datos\\"+args[0]+".txt";
			fichero2=".\\datos\\"+args[1]+".txt";
			fFichero1= new File(fichero1);
			fFichero2= new File(fichero2);
			System.out.println("Hola");
			if(fFichero1.exists() && fFichero2.exists()) {
				System.out.println("Ambos existen");
			}
			else {
				System.err.println("Alguna de las rutas no existe");
			}
		}
		else {
			fichero1="./datos/"+args[0]+".txt";
			fichero2="./datos/"+args[1]+".txt";
		}
	}
}