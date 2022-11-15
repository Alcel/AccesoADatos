package refuerzo;

import java.io.File;
import java.io.FileNotFoundException;

public class Empresa {
	private static final String DATOS="./src/datos/AD16_AleatorioEmple.dat";
	//String, int, String y double. : nombre, edad, cargo, sueldo
	//todos los Strings se habían ajustado a 10 (nombres) o 14 caracteres (cargos), antes de escribirlos al fichero.
	private static final int NOMBRE_LENGTH = 10;
	private static final int CARGO_LENGTH = 14;
	//Cada empleado cupara int(4)+NOMBRE_LENGTH*2+CARGO_LENGTH*2+8=64 bytes
	private static final int TAMANIO_REGISTRO = 64;
	
	public static void main(String[] args) {
		
	}
	
	public static void eliminador (int id) {
		
	}
	
	public static void lector () {
		File fichero;
		fichero= new File(DATOS);
		
	}
	
	public static void modificador (int id, String nuevoNombre, double nuevoSueldo) {
		
	}
	public static void intercambiador (int idEmp1, int idEmp2) {
		
	}

}
