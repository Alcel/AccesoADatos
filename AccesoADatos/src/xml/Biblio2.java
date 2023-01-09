package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class Biblio2 {
	private static String RUTA_FICHERO ="./src/datos/biblioteca.xml";
	public static void main(String[] args) {
	
//	try {
//		SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
//		GestorListado miGestorListado = new GestorListado();
//		procesadorXML.parse(new File(RUTA_FICHERO), miGestorListado);
//	} catch (ParserConfigurationException e) {
//		e.printStackTrace();
//	} catch (SAXException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	try {
//		SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
//		GestorExtremos miGestorExtremos = new GestorExtremos();
//		procesadorXML.parse(new File(RUTA_FICHERO), miGestorExtremos);
//	} catch (ParserConfigurationException e) {
//		e.printStackTrace();
//	} catch (SAXException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
	
	
	try {
		SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
		GestorCoautoria miGestorCoautoria = new GestorCoautoria();
		procesadorXML.parse(new File(RUTA_FICHERO), miGestorCoautoria);
	} catch (ParserConfigurationException e) {
		e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
}
class GestorListado extends org.xml.sax.helpers.DefaultHandler {
	static String ruta;
	static String elemento;
	ArrayList <String> autores = new ArrayList<String>();
	public GestorListado() {
		super();
	}
	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		elemento = nombreC;
	}
	


	public void characters(char[] ch, int start, int length) throws SAXException {
		// Quitamos los saltos de línea y tabuladores y lo imprimimos
		String car = new String(ch, start, length);
		
		
		
		car = car.replaceAll("[\t\n]", "");
		//System.out.println(elemento);
		
		if(car.trim().length()>0) {
			
			if(elemento=="titulo") {
				System.out.print(car);
			}
			if(elemento=="editorial") {
				System.out.println("  (" + car+")");
				for(int i=0;i<autores.size();i++) {
						System.out.println("  -"+autores.get(i));
				}
				autores.clear();
			}
			if(elemento=="autor") {
				autores.add(car);
			}
		}
	}
}

class GestorExtremos extends org.xml.sax.helpers.DefaultHandler{
	static String ruta;
	static String elemento;
	float precioAux=0F;
	float minPrecio=1000.0F;
	float maxPrecio=0.0F;
	String nomMen ="";
	String nomMay = "";
	String nomTmp = "";
	
	public GestorExtremos() {
		super();
	}
	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		elemento = nombreC;
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		float convPre=0.0F;
		
		String car = new String(ch, start, length);
		car = car.replaceAll("[\t\n]", "");
		
		if(car.trim().length()>0) {
			
			if(elemento=="titulo") {
				nomTmp=car;
			}
			
			if(elemento=="precio") {
				convPre=Float.parseFloat(car);
				if(convPre>maxPrecio) {
					maxPrecio=convPre;
					nomMay=nomTmp;
				}
				if(convPre<minPrecio) {
					minPrecio=convPre;
					nomMen=nomTmp;
					
				}
			}
		}
	}
	public void endDocument() {
		System.out.println("El más barato:"+nomMen +", "+minPrecio);
		System.out.println("El más caro:"+nomMay +", "+maxPrecio);
	}
	
}
class GestorCoautoria extends org.xml.sax.helpers.DefaultHandler{
	static String ruta;
	static String elemento;
	float precioAux=0F;
	ArrayList <String> coautados = new ArrayList<String>();
	String nomTmp="";
	int cant=0;
	Boolean fin=true;
	
	public GestorCoautoria() {
		super();
	}
	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		elemento = nombreC;
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		float convPre=0.0F;
		
		
		String car = new String(ch, start, length);
		car = car.replaceAll("[\t\n]", "");
		
		if(car.trim().length()>0) {
			
			if(elemento=="titulo") {
				nomTmp=car;
				fin=true;
				cant=0;
			}
			
			if(elemento=="autor") {
				cant++;
			}
			if(cant>1) {
				for(int i=0;i<coautados.size();i++) {
					if(coautados.get(i)==nomTmp) {

						fin=false;
					}
					
				}
				if(fin) {
					coautados.add(nomTmp);
					
				}
			}
		}
	}
	public void endDocument() {
		for(int i=0;i<coautados.size();i++) {
			System.out.println(coautados.get(i));
		}
	}
}
class GestorBuscar extends org.xml.sax.helpers.DefaultHandler{

}