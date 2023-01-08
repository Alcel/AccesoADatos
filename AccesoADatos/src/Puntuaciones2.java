

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;



public class Puntuaciones2 {
	private static String RUTA_A_ESCRIBIR = "./src/datos/puntuaciones.xml";
	private static String RUTA_A_LEER = "./src/datos/puntuaciones.dat";
	
	public static void main(String[] args) {
		File fichero = new File(RUTA_A_LEER);
		FileInputStream fisFichero;
		ObjectInputStream oisFichero;
		Puntuacion punt;
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<String> minutos = new ArrayList<>();
		ArrayList<String> segundos = new ArrayList<>();
		ArrayList<String> fechas = new ArrayList<>();
		String numeros = "";
		Date fecha = new Date();
		Calendar calendario;
		SimpleDateFormat formato;
		
		
		if(fichero.exists()) {
			try {
				fisFichero = new FileInputStream(fichero);
				oisFichero = new ObjectInputStream(fisFichero);
				while (fisFichero.available() > 0) {
					punt =(Puntuacion) oisFichero.readObject();
					nombres.add(punt.getNombre());
					numeros = String.valueOf(punt.getSegundos());
					segundos.add(numeros);
					numeros = String.valueOf(punt.getMinutos());
					minutos.add(numeros);
					fecha = punt.getFecha().getTime();
					formato= new SimpleDateFormat("dd/MM/yy");
					//Arreglar
					numeros = formato.format(fecha);
					fechas.add(numeros);
				}
				System.out.println(nombres.get(2)+" "+minutos.get(2)+" "+segundos.get(2)+" "+fechas.get(2));
				
				oisFichero.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("Clase no encontrada");
			}
			
			
		}
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			DOMImplementation arbol = db.getDOMImplementation();

			Document documento = arbol.createDocument("puntuaciones.xsd", "puntuaciones", null);
			documento.setXmlVersion("1.0");
			Element eltoRaiz = documento.getDocumentElement();
			Element elemntAux; 
			Element puntuacion; 
			Element tiempo; 
		

			for (int i =0;i<nombres.size();i++) {

					
					puntuacion = documento.createElement("puntuacion");

					
					eltoRaiz.appendChild(puntuacion);

					elemntAux = documento.createElement("nombre");
				    puntuacion.appendChild(elemntAux);
				    
					Text textoAux = documento.createTextNode(nombres.get(i));
					elemntAux.appendChild(textoAux);
					
					tiempo = documento.createElement("tiempo");
					puntuacion.appendChild(tiempo);
					elemntAux = documento.createElement("min");
					tiempo.appendChild(elemntAux);
					textoAux = documento.createTextNode(minutos.get(i));
					elemntAux.appendChild(textoAux);
					elemntAux = documento.createElement("seg");
					tiempo.appendChild(elemntAux);
					textoAux = documento.createTextNode(segundos.get(i));
					elemntAux.appendChild(textoAux);
					elemntAux = documento.createElement("fecha");
					textoAux = documento.createTextNode(fechas.get(i));
					puntuacion.appendChild(elemntAux);
					elemntAux.appendChild(textoAux);
					
					
					
				}
			

			
			Transformer conversor = TransformerFactory.newInstance().newTransformer();

			conversor.setOutputProperty(OutputKeys.INDENT, "yes");
			conversor.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			
			Source fuente = new DOMSource(documento);
			
			Result destino = new StreamResult(new File(RUTA_A_ESCRIBIR));

			conversor.transform(fuente, destino);
			
		} catch (TransformerException e) {
			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
