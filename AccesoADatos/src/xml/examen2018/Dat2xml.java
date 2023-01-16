package xml.examen2018;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import boletin2.Empleado;

public class Dat2xml {
	public static void main(String[] args) {
		String direccion;
		File fichero;
		ObjectInputStream ois;
		FileInputStream fis;
		Empresa empre;
		ArrayList<Empresa> empAr = new ArrayList<>();
		if(args.length>0) {
			direccion=args[0];
			fichero = new File(direccion);
			if(fichero.exists()) {
				try {
					fis = new FileInputStream(fichero);
					ois = new ObjectInputStream(fis);
					while (fis.available() > 0) {
						empre = (Empresa) ois.readObject();
						System.out.println(empre.getCif());
					}
					
					ois.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				System.err.println("La dirección no corresponde con una dirección existente");
			}
		}
		else {
			System.err.println("No hay argumento");
		}
	}
}