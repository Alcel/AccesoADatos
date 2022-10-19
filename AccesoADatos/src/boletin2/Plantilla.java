package boletin2;

import java.io.*;

public class Plantilla {
	public static void main(String[] args) throws IOException {
		//nuevoEmpleado("Ivan 'El ingles' Rodriguez",39,2200,true);
		//muestraEmpleados();
		muestraEmpleado(2);
	}
	
	public static void nuevoEmpleado (String nombre, int edad, double sueldo, boolean jubilado) throws IOException {
		File archivo = new File("./src/datos/plantilla.dat.txt");
		archivo.createNewFile();
		
		FileOutputStream flujoOut = new FileOutputStream(archivo, true);
		
		DataOutputStream dos = new DataOutputStream(flujoOut);
		
		dos.writeUTF(nombre);
		dos.writeInt(edad);
		dos.writeDouble(sueldo);
		dos.writeBoolean(jubilado);
		
		
		dos.close();
		flujoOut.close();
	}
	
	public static void muestraEmpleados () throws IOException {
		File archivo = new File("./src/datos/plantilla.dat.txt");
		FileInputStream flujoIn = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(flujoIn); 
	
		while(dis.available()>0) {
		
		System.out.print(dis.readUTF()); 
		System.out.print(dis.readInt()); 
		System.out.print(dis.readDouble());
		System.out.println(dis.readBoolean()); 
		}
		dis.close();
		flujoIn.close();
	}
	
	public static void muestraEmpleado (int orden) throws IOException {
		File archivo = new File("./src/datos/plantilla.dat.txt");
		FileInputStream flujoIn = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(flujoIn); 
		for(int i=0;i!=orden;i++) {
			dis.readUTF();
			dis.readInt();
			dis.readDouble();
			dis.readBoolean();
		}

		System.out.print(dis.readUTF()); 
		System.out.print(dis.readInt()); 
		System.out.print(dis.readDouble());
		System.out.println(dis.readBoolean()); 
		dis.close();
		flujoIn.close();
	}
	
	public static void eliminaEmpleado(int orden) {
		
	}

}
