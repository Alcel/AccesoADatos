package boletin2;

import java.io.*;

public class Plantilla {
	public static void main(String[] args) throws IOException {
		nuevoEmpleado("Juan",23,1200,false);
	}
	
	public static void nuevoEmpleado (String nombre, int edad, double sueldo, boolean jubilado) throws IOException {
		File archivo = new File("./src/datos/plantilla.dat.txt");
		archivo.createNewFile();
		
		FileOutputStream flujoOut = new FileOutputStream(archivo);
		flujoOut.write(nombre);
		
		flujoOut.close();
	}
	
	public static void muestraEmpleados () {
		
	}
	
	public static void muestraEmpleado (int orden) {
		
	}
	
	public static void eliminaEmpleado(int orden) {
		
	}

}
