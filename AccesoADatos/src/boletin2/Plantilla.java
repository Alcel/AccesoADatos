package boletin2;

import java.io.*;
import java.util.ArrayList;

public class Plantilla {
	public static void main(String[] args) throws IOException {
		//nuevoEmpleado("Ivan 'El ingles' Rodriguez",39,2200,true);
		muestraEmpleados();
		//muestraEmpleado(0);
		//eliminaEmpleado(0);
	}
	
	public static void nuevoEmpleado (String nombre, int edad, double sueldo, boolean jubilado) throws IOException {
		File archivo = new File("./src/datos/plantilla.dat");
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
		File archivo = new File("./src/datos/plantilla.dat");
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
		File archivo = new File("./src/datos/plantilla.dat");
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
	
	public static void eliminaEmpleado(int orden) throws IOException {
		/*Array y no añadir de vuelta*/
		File archivo = new File("./src/datos/plantilla.dat");
		FileInputStream flujoIn = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(flujoIn); 
		ArrayList<String> nombre = new ArrayList<String>();
		ArrayList<Integer> edad = new ArrayList<Integer>();
		ArrayList<Double> sueldo = new ArrayList<Double>();
		ArrayList<Boolean> jubilado = new ArrayList<Boolean>();
	
		while(dis.available()>0) {
		nombre.add(dis.readUTF());
		edad.add(dis.readInt());
		sueldo.add(dis.readDouble());
		jubilado.add(dis.readBoolean());
		
		}
		
		System.out.println(nombre);
		dis.close();
		flujoIn.close();
		
		
	}

}
