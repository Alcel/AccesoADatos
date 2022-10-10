package boletin1;
import java.io.File;
import java.io.IOException;
public class File1 {
	static File DIRECTORIOS1= new File ("~/Pruebas/Primera/Tercera");
	static File DIRECTORIOS2= new File ("~/Pruebas/Segunda");
	static File UNO= new File("~/Pruebas/Primera/uno.txt");
	static File DOS= new File("~/Pruebas/Segunda/dos.txt");
	static File TRES= new File("~/Pruebas/Primera/Tercera/tres.txt");
	
	public static void crear() {
		String sistemaOperativo = System.getProperty("os.name");
		if(sistemaOperativo.equals("Linux")) {
			System.out.println("Es un sistema linux");
			DIRECTORIOS1.mkdirs();
			DIRECTORIOS2.mkdirs();
			try{
				UNO.createNewFile();
				DOS.createNewFile();
				TRES.createNewFile();
				
			}
			catch(IOException ex) {
				System.out.println("Fallo");
			}
			
		}
		else{System.out.println("No es un sistema linux");	
		}
	}
	public static boolean crear2 () {
		boolean resol=false;
		if(DIRECTORIOS1.exists()&&DIRECTORIOS2.exists()&&UNO.exists()&&DOS.exists()&&TRES.exists()) {
			resol=true;
		}
		System.out.println(resol);
		return resol;
	}
	
	
	public static void eliminar() {
		File pruebas = new File("~/Pruebas");
		File prime = new File("~/Pruebas/Primera");
		TRES.delete();
		DIRECTORIOS1.delete();
		DOS.delete();
		DIRECTORIOS2.delete();
		UNO.delete();
		prime.delete();
		pruebas.delete();

	}
	public static void main(String[] args) {
		crear();
		eliminar();
		crear2();
	}

}
