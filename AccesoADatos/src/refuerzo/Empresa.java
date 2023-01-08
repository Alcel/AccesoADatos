package refuerzo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Empresa {
	private static final String DATOS = "./src/datos/AD16_AleatorioEmple.dat";
	// String, int, String y double. : nombre, edad, cargo, sueldo
	// todos los Strings se habían ajustado a 10 (nombres) o 14 caracteres (cargos),
	// antes de escribirlos al fichero.
	private static final int NOMBRE_LENGTH = 10;
	private static final int CARGO_LENGTH = 14;
	// Cada empleado cupara int(4)+NOMBRE_LENGTH*2+CARGO_LENGTH*2+8=64 bytes
	private static final int TAMANIO_REGISTRO = 64;

	public static void main(String[] args) {

		//modificador(11,"Juan",200.3);
		//eliminador(2);
		intercambiador(1,2);
		lector();
		

	}

	public static void eliminador(int id) { 
		File fichero = new File(DATOS);
		long num = (id - 1) * 64;
		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			raf.seek(num);
			raf.writeInt(0);
			raf.close();
			/*
			 * while(raf.getFilePointer()<raf.length()) { raf.skipBytes(64);
			 * num=raf.readInt(); raf.writeInt(num-1);
			 * 
			 * 
			 * }
			 */

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void lector() {
		int numEmpleado;
		int edad;
		String sNombre;
		String sCargo;
		Double sueldo;
		char[] nombre = new char[NOMBRE_LENGTH];
		char[] cargo = new char[CARGO_LENGTH];
		File fichero = new File(DATOS);
		Boolean noDes = true;
		try {
			RandomAccessFile raf = new RandomAccessFile(fichero, "r");
			while (raf.getFilePointer() < raf.length()) {
				numEmpleado = raf.readInt();
				if (numEmpleado >= 0) {
					
					
					
					for (int i = 0; i < NOMBRE_LENGTH; i++) {
						nombre[i] = raf.readChar();
					}
					sNombre = String.valueOf(nombre).trim();
					edad = raf.readInt();
					for (int i = 0; i < cargo.length; i++) {
						cargo[i] = raf.readChar();
					}
					sCargo = String.valueOf(cargo).trim();
					sueldo = raf.readDouble();
					if(numEmpleado!=0 && noDes) {
					System.out.printf("\n%11d%10s%5d%15s  %5.2f", numEmpleado, sNombre, edad, sCargo, sueldo);}
					else{
						noDes=false;
						if(numEmpleado!=0) {
						System.out.printf("\n%11d%10s%5d%15s  %5.2f", numEmpleado-1, sNombre, edad, sCargo, sueldo);}
					}
				}  
			}    
			raf.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void modificador(int id, String nuevoNombre, double nuevoSueldo) { 
		long posicion = 0;
		if (id > 0) {
			File fichero = new File(DATOS);

			try {
				RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
				posicion = (id - 1) * TAMANIO_REGISTRO;
				raf.seek(posicion);
				raf.skipBytes(4);

				for (int i = 0; i < NOMBRE_LENGTH - nuevoNombre.length(); i++) {
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

		} else {
			System.err.println("Este metodo no esta hecho para eliminar. Introduzca un id mayor que 0.");
		}
	}

	public static void intercambiador(int idEmp1, int idEmp2) { /* Esta a medio hacer */
		//Varibales funcionales
		long posicion = 0;
		File fichero = new File(DATOS);
		//Variables que usaremos para almacenar los datos de ambos empleados
		
		int emp1Edad;
		Double emp1Sueldo;
		char[] emp1Nombre = new char[NOMBRE_LENGTH];
		char[] emp1Cargo = new char[CARGO_LENGTH];
		String sEmp1Nombre;
		String sEmp1Cargo;
		
		int emp2Edad;
		Double emp2Sueldo;
		char[] emp2Nombre = new char[NOMBRE_LENGTH];
		char[] emp2Cargo = new char[CARGO_LENGTH];
		String sEmp2Nombre;
		String sEmp2Cargo;
		
		//Leemos el archivo recuperando los datos que queremos intercambiar
		try { 
			
			//Empleado 1
			RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
			raf.seek((idEmp1-1)*64);
			raf.skipBytes(4);
			for(int i=0; i<NOMBRE_LENGTH;i++) {
				emp1Nombre[i]=raf.readChar(); 
			}
			sEmp1Nombre = String.valueOf(emp1Nombre).trim();
			emp1Edad = raf.readInt(); 
			for(int i = 0;i<emp1Cargo.length;i++) {
				emp1Cargo[i]=raf.readChar(); 
			} 
			sEmp1Cargo = String.valueOf(emp1Cargo).trim();
			emp1Sueldo = raf.readDouble();
			
			//Empleado 2
			raf.seek((idEmp2-1)*64);
			raf.skipBytes(4);
			for(int i=0; i<NOMBRE_LENGTH;i++) {
				emp2Nombre[i]=raf.readChar(); 
			}
			sEmp2Nombre = String.valueOf(emp2Nombre).trim();
			emp2Edad = raf.readInt(); 
			for(int i = 0;i<emp2Cargo.length;i++) {
				emp2Cargo[i]=raf.readChar(); 
			} 
			
			sEmp2Cargo = String.valueOf(emp2Cargo).trim();
			
			emp2Sueldo = raf.readDouble();
			
			
			//Escribo el emp1
			posicion = (idEmp2-1) * TAMANIO_REGISTRO;
			raf.seek(posicion);
			raf.writeInt(idEmp2);
			
			for (int i = 0; i < NOMBRE_LENGTH - sEmp1Nombre.length(); i++) {
				raf.writeChars(" ");
			}
			raf.writeChars(sEmp1Nombre);
			
			raf.writeInt(emp1Edad);
			
			for (int i = 0; i < CARGO_LENGTH - sEmp1Cargo.length(); i++) {
				raf.writeChars(" ");
			}
			raf.writeChars(sEmp1Cargo);
			raf.writeDouble(emp1Sueldo);
			
			//Escribo el emp2
			
			posicion = (idEmp1-1) * TAMANIO_REGISTRO;
			raf.seek(posicion);
			raf.writeInt(idEmp1);
			
			for (int i = 0; i < NOMBRE_LENGTH - sEmp2Nombre.length(); i++) {
				raf.writeChars(" ");
			}
			raf.writeChars(sEmp2Nombre);
			raf.writeInt(emp2Edad);
			for (int i = 0; i < CARGO_LENGTH - sEmp2Cargo.length(); i++) {
				raf.writeChars(" ");
			}
			
			
			raf.writeChars(sEmp2Cargo);
			raf.writeDouble(emp2Sueldo);

			raf.close();


		} catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
				e) {e.printStackTrace(); }

	}
}