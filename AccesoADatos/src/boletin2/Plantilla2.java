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
		
		Empleado manu = new Empleado("Dam",4,25980,false);
		nuevoEmpleado(manu);
		//muestraEmpleados();
		
	}
	
	public static void nuevoEmpleado (Empleado emp) throws IOException, ClassNotFoundException {
		FileOutputStream escritor = new FileOutputStream(plantilla2,true);
		ObjectOutputStream escritorObjetos = new ObjectOutputStream(escritor);
		
		
		
		FileInputStream lector = new FileInputStream(plantilla2);
		ObjectInputStream lectorObjetos = new ObjectInputStream(lector);
		
		Empleado empleado;
		ArrayList<Empleado> lista = new ArrayList<>();
		
		
			
		while(lector.available()>0) {
			System.out.println("Hola");
			 empleado = (Empleado) lectorObjetos.readObject();
			 System.out.printf("Nombre: %8s\t edad: %d\t cargo: %15s\t sueldo: %.2f\n", empleado.getNombre(),
						empleado.getEdad(), empleado.isJubilado(), empleado.getSueldo());
			 lista.add(empleado);
		}
		empleado= emp;
		lista.add(empleado);
		lectorObjetos.close();
		lector.close();
		
		for(int i=0;i!=lista.size();i++) {
			
		}
		
		escritorObjetos.writeObject(emp);
		
		escritorObjetos.close();
		escritor.close();
		
		
		
	
		
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
