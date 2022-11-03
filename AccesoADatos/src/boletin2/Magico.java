package boletin2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import anexo.FileType;

public class Magico {
	
		public static void main(String[] args) {
		String ruta ="./src/datos/"+args[0];
		String rutaTipos ="./src/datos/tipos.dat";
		FileType tipo;
		File archivoIntro = new File(ruta);
		File tipos = new File(rutaTipos);	
		FileInputStream fisFichero=null;
		DataInputStream dataFichero=null;
		ObjectInputStream oisFichero=null;
		boolean conoceTipo=false;
		boolean extension=false;
		int puntoPos=args[0].lastIndexOf('.');
		String extensionLetra=(args[0].subSequence(puntoPos+1,args[0].length())).toString();
		String descripcion="";
		String extensionCorr = null;
		int[] numPropio = new int[4];
		
		//¿Existe el archivo y tiene solo un comando?
		if(archivoIntro.exists() && args.length==1) {//Si
			
			try {
				fisFichero = new FileInputStream(archivoIntro);	
				dataFichero= new DataInputStream(fisFichero);
				
				for(int i=0;i<numPropio.length;i++) {
					numPropio[i]=fisFichero.read();
				}
				fisFichero = new FileInputStream(tipos);
				
				oisFichero = new ObjectInputStream(fisFichero);			
				
				while (fisFichero.available() > 0) {	
					
					tipo = (FileType) oisFichero.readObject();
					if(Arrays.equals(numPropio, tipo.getMagico())) {
						descripcion=tipo.getDescripcion();
						
						extensionCorr=tipo.getExtension();
						if(extensionLetra.equals(extensionCorr) ) {
							extension=true;
						}
						conoceTipo=true;
						
					}
				}
				oisFichero.close();
				fisFichero.close();
			} catch (IOException ioE) {
				ioE.printStackTrace();
			} catch (ClassNotFoundException cnfE) {
				cnfE.printStackTrace();
			}
			if(conoceTipo) {
				
				System.out.println("Contenido: \""+descripcion+"\"");
				if(extension) {
					System.out.println("Extension: correcta.");
				}
				else {
					System.out.println("Extension: errónea, se propone: "+extensionCorr);
				}
			}
			else {
				System.out.println("Contenido: desconocido");
				
			}
		}
		else {//No
			System.err.println("Ha ocurrido un error por el o los siguientes posibles motivos:");
			if(args.length!=1) {
				System.err.println("Ha entroducido "+args.length+" elementos como comandos. "
						+ "Ha de introducir solo un comando.");
			}
			if(!archivoIntro.exists()) {
				System.err.println("La ruta "+archivoIntro+" no es correcta, porfavor revisela");
			}
			
		}
	}
}
