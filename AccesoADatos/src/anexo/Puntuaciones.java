package anexo;


import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import boletin2.Empleado;

public class Puntuaciones {
	private static File fiPuntData = new File("./src/datos/puntuaciones.dat");
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Calendar fecha = new GregorianCalendar(215, 11, 5);
		Puntuacion alberto = new Puntuacion("Alberto",10,41,fecha);
		nuevo(alberto);
		muestraPuntuaciones();
		
		
	}
	
	public static boolean nuevo (Puntuacion nuevaPuntuacion) throws IOException, ClassNotFoundException {
		//Declaro las variables, incluidos los stream
		
		ArrayList<String> nombre = new ArrayList<String>();
		ArrayList<Integer> minutos = new ArrayList<Integer>();
		ArrayList<Integer> segundos = new ArrayList<Integer>();
		ArrayList<Calendar> fecha = new ArrayList<Calendar>();
		int contador=0;
		boolean resultado=false;
		boolean orden=false;
		int numMayorQue=0;
		Puntuacion auxPunt;
		fiPuntData.createNewFile();
		long tamanio =fiPuntData.length();
		FileOutputStream fosNuevo =null;
		ObjectOutputStream oosNuevo = null;
		FileInputStream fisNuevo = new FileInputStream(fiPuntData);
		ObjectInputStream oisNuevo = null;
		
		
		
		
		//Declare las variables, incluidos los stream
		
		if(tamanio!=0) {
			
			//Comprovamos si el archivo tiene datos o si esta en blanco
			
			//En este caso tiene datos, por lo que primero recopilamos tidos los datos que tiene
			
			 oisNuevo = new ObjectInputStream(fisNuevo);
			while(fisNuevo.available()>0) {
				auxPunt = (Puntuacion) oisNuevo.readObject();
				
				nombre.add(auxPunt.getNombre());
				minutos.add(auxPunt.getMinutos());
				segundos.add(auxPunt.getSegundos());
				fecha.add(auxPunt.getFecha());
				contador++;
			}
			oisNuevo.close();
			fosNuevo = new FileOutputStream(fiPuntData);
			oosNuevo=new ObjectOutputStream(fosNuevo);
			
			if(nombre.size()==5) { //Si la lista ya esta llena
				for (int i = 0; i < nombre.size(); i++) {
					if(((minutos.get(i)*60)+segundos.get(i))<((nuevaPuntuacion.getMinutos()*60)+nuevaPuntuacion.getSegundos())) {
						orden=true;
					}
				}
				if(orden) {
					for (int i = 0; i < nombre.size(); i++) {
						auxPunt = new Puntuacion(nombre.get(i), minutos.get(i), segundos.get(i), fecha.get(i));
						oosNuevo.writeObject(auxPunt);
					}
					
					//Y ahora los volvemos a introducir junto a la nueva puntuacion
					
					auxPunt = new Puntuacion(nuevaPuntuacion.getNombre(),nuevaPuntuacion.getMinutos(), 
							nuevaPuntuacion.getSegundos(), nuevaPuntuacion.getFecha());
					oosNuevo.writeObject(auxPunt);
					oosNuevo.close();
				}
				else {
					auxPunt = new Puntuacion(nuevaPuntuacion.getNombre(),nuevaPuntuacion.getMinutos(), 
							nuevaPuntuacion.getSegundos(), nuevaPuntuacion.getFecha());
					oosNuevo.writeObject(auxPunt);
					for (int i = 0; i < nombre.size(); i++) {
						auxPunt = new Puntuacion(nombre.get(i), minutos.get(i), segundos.get(i), fecha.get(i));
						oosNuevo.writeObject(auxPunt);
					}
					
					//Y ahora los volvemos a introducir junto a la nueva puntuacion
					
					
					oosNuevo.close();
				}
				
			}else {//Si la lista no esta llena (por hacer)
				for (int i = 0; i < nombre.size(); i++) {
					if(((minutos.get(i)*60)+segundos.get(i))<((nuevaPuntuacion.getMinutos()*60)+nuevaPuntuacion.getSegundos())) {
						orden=true;
					}
				}
				if(orden) {
					for (int i = 0; i < nombre.size(); i++) {
						auxPunt = new Puntuacion(nombre.get(i), minutos.get(i), segundos.get(i), fecha.get(i));
						oosNuevo.writeObject(auxPunt);
					}
					
					//Y ahora los volvemos a introducir junto a la nueva puntuacion
					
					auxPunt = new Puntuacion(nuevaPuntuacion.getNombre(),nuevaPuntuacion.getMinutos(), 
							nuevaPuntuacion.getSegundos(), nuevaPuntuacion.getFecha());
					oosNuevo.writeObject(auxPunt);
					oosNuevo.close();
				}
				else {
					auxPunt = new Puntuacion(nuevaPuntuacion.getNombre(),nuevaPuntuacion.getMinutos(), 
							nuevaPuntuacion.getSegundos(), nuevaPuntuacion.getFecha());
					oosNuevo.writeObject(auxPunt);
					for (int i = 0; i < nombre.size(); i++) {
						auxPunt = new Puntuacion(nombre.get(i), minutos.get(i), segundos.get(i), fecha.get(i));
						oosNuevo.writeObject(auxPunt);
					}
					
					//Y ahora los volvemos a introducir junto a la nueva puntuacion
					
					
					oosNuevo.close();
				}
			}
			

			
			
			
		
		}else {
			
			//En este caso el archivo esta en blanco por lo que introducimos directamente la nueva puntuacion
			fosNuevo = new FileOutputStream(fiPuntData);
			oosNuevo=new ObjectOutputStream(fosNuevo);
				auxPunt = new Puntuacion(nuevaPuntuacion.getNombre(),nuevaPuntuacion.getMinutos(), 
						nuevaPuntuacion.getSegundos(), nuevaPuntuacion.getFecha());
				oosNuevo.writeObject(auxPunt);
				oosNuevo.close();
				fosNuevo.close();
		}
		
		return resultado;
	}
	public static void muestraPuntuaciones() throws IOException, ClassNotFoundException {

	
		int contador=0;
		boolean resultado=false;
		Puntuacion auxPunt;
		
		long tamanio =fiPuntData.length();
		
		FileInputStream fisNuevo = new FileInputStream(fiPuntData);
		ObjectInputStream oisNuevo = null;
		oisNuevo = new ObjectInputStream(fisNuevo);
		while(fisNuevo.available()>0) {
			auxPunt = (Puntuacion) oisNuevo.readObject();
			System.out.println("Nombre: "+auxPunt.getNombre()+" Minutos: "+auxPunt.getMinutos()+
					" Segundos: "+auxPunt.getSegundos()+" Fecha: "+auxPunt.getFecha().getWeekYear());		
		}
		oisNuevo.close();
	}
}