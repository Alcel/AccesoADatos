package refuerzo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Empresa {
	private static final String DATOS="./src/datos/AD16_AleatorioEmple.dat";
	//String, int, String y double. : nombre, edad, cargo, sueldo
	//todos los Strings se habían ajustado a 10 (nombres) o 14 caracteres (cargos), antes de escribirlos al fichero.
	private static final int NOMBRE_LENGTH = 10;
	private static final int CARGO_LENGTH = 14;
	//Cada empleado cupara int(4)+NOMBRE_LENGTH*2+CARGO_LENGTH*2+8=64 bytes
	private static final int TAMANIO_REGISTRO = 64;
	
	public static void main(String[] args) {
		
		//modificador(7,"Rudolfito",2002.3);
		eliminador(6);
		lector();
		
	}
	
	public static void eliminador (int id) {
		File fichero = new File(DATOS);
		int num;
		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			raf.seek(id*64);
			raf.writeInt(0);
			/*while(raf.getFilePointer()<raf.length()) {
				raf.skipBytes(64);
				num=raf.readInt();
				raf.writeInt(num-1);
				
			}*/
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void lector () {
		int numEmpleado;
		int edad;
		String sNombre;
		String sCargo;
		Double sueldo;
		char [] nombre = new char[NOMBRE_LENGTH];
		char [] cargo = new char[CARGO_LENGTH];
		File fichero = new File(DATOS);
		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "r");
			while(raf.getFilePointer()<raf.length()) {
				numEmpleado = raf.readInt();
				for(int i=0; i<NOMBRE_LENGTH;i++) {
					nombre[i]=raf.readChar();
				}
				sNombre = String.valueOf(nombre).trim();
				edad = raf.readInt();
				for(int i = 0; i<cargo.length;i++) {
					cargo[i]=raf.readChar();
				}
				sCargo = String.valueOf(cargo).trim();
				sueldo = raf.readDouble();
				System.out.printf("\n%11d%10s%5d%15s  %5.2f",numEmpleado, sNombre, edad, sCargo, sueldo);
			}
			raf.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void modificador (int id, String nuevoNombre, double nuevoSueldo) { //Arreglar problema. 
		long posicion=0;
		if(id>0) {
			File fichero = new File(DATOS);
			
			try {
				RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
				posicion=(id-1)*TAMANIO_REGISTRO;
				raf.seek(posicion);
				raf.skipBytes(4);
				
				for(int i=0;i<NOMBRE_LENGTH-nuevoNombre.length();i++) {
					raf.writeChars(" ");
				}
				raf.writeChars(nuevoNombre);
				raf.skipBytes(4);
				raf.skipBytes(28);
				raf.writeDouble(nuevoSueldo);
				raf.close();
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			System.err.println("Este metodo no esta hecho para eliminar. Introduzca un id mayor que 0.");
		}
	}
	public static void intercambiador (int idEmp1, int idEmp2) {
	}
}