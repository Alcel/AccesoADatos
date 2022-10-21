package boletin2;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Plantilla2 {
	private static File plantilla2 = new File("./src/datos/plantilla2.dat"); 
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Empleado manu = new Empleado("Damfaq",49,2598700,false);
		nuevoEmpleado(manu);
		//muestraEmpleados();
		
	}
	
	public static void nuevoEmpleado (Empleado emp) throws IOException, ClassNotFoundException {
		FileOutputStream escritor = new FileOutputStream(plantilla2,true);
		ObjectOutputStream escritorObjetos = new ObjectOutputStream(escritor);
		Empleado empleado = new Empleado();
		ArrayList<Empleado> lista = new ArrayList<>();
		FileInputStream lector = new FileInputStream(plantilla2);
		ObjectInputStream lectorObjetos = new ObjectInputStream(lector);
		
		if(lectorObjetos.available()>0) {
			empleado =(Empleado) lectorObjetos.readObject();
			while(lector.available()!=-1) {
				
				lista.add(empleado);
				}
			for(int i=0;i<lista.size();i++) {
				escritorObjetos.writeUTF(lista.get(i).getNombre());
				escritorObjetos.writeInt(lista.get(i).getEdad());
				escritorObjetos.writeDouble(lista.get(i).getSueldo());
				escritorObjetos.writeBoolean(lista.get(i).isJubilado());
			}
	
		
				
			lectorObjetos.close();
			lector.close();
			
			escritorObjetos.writeObject(emp);
			escritorObjetos.close();
			escritor.close();
		}
		else {
			System.err.println("No hay objeto alguno");
			escritorObjetos.writeObject(emp);
		}
		
	}
	public static void muestraEmpleados () throws IOException, ClassNotFoundException {
		Empleado empleado = new Empleado();
		FileInputStream lector = new FileInputStream(plantilla2);
		ObjectInputStream lectorObjetos = new ObjectInputStream(lector);
		while(lectorObjetos.available()!=-1) {
			empleado =(Empleado) lectorObjetos.readObject();
			System.out.println("Nombre: "+empleado.getNombre());
			System.out.println("Edad: "+empleado.getEdad());
			System.out.println("Sueldo: "+empleado.getSueldo());
			System.out.println("Jubilado: "+empleado.isJubilado());
			}
			
		lectorObjetos.close();
		lector.close();
	}
	public static void muestraEmpleado (int orden) {
		
	}
	public static void eliminaEmpleado(int orden) {
		
	}
}
