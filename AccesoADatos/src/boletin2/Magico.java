package boletin2;

import java.io.DataInputStream;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import org.xml.sax.ErrorHandler;

import anexo.FileType;

public class Magico {
	public static void main(String[] args) {
		String RUTA ="./src/datos/"+args[0];
		String RUTATIPOS ="./src/datos/tipos.dat";
		FileType tipo;
		File archivoIntro = new File(RUTA);
		File tipos = new File(RUTATIPOS);	
		FileInputStream fisFichero=null;
		DataInputStream dataFichero=null;
		ObjectInputStream oisFichero=null;
		int numMagico =0;
		boolean conoceTipo=false;
		boolean extension=false;
		int puntoPos=args[0].lastIndexOf('.');
		String extensionLetra=(args[0].subSequence(puntoPos+1,args[0].length())).toString();
		String descripcion="";
		String extensionCorr = null;
		int[] numPropio = new int[4];
		int[] numEntrante= new int[4];
		
	
		//¿Existe el archivo?
		
		if(archivoIntro.exists()) {
			
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
						System.out.println(extensionCorr);
						System.out.println(extensionLetra);
						
						if(extensionLetra.equals(extensionCorr) ) {
							extension=true;
						}
						conoceTipo=true;
						
					}
				}
				oisFichero.close();
			} catch (IOException ioE) {
				ioE.printStackTrace();
			} catch (ClassNotFoundException cnfE) {
				cnfE.printStackTrace();
			}
			if(conoceTipo) {
				
				System.out.println("Contenido: \""+descripcion+"\"");
				if(extension) {
					System.out.println("Extension correcta");
				}
				else {
					System.out.println("Extension erronea, se propone "+extensionCorr);
				}
			}
			else {
				System.out.println("Contenido: desconocido");
				
				
			}
		}
		else {
			System.err.println("El archivo no existe");
		}
	}
}
