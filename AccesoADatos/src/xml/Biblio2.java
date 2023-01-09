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
	GestorListado nuevoGestoListado = new GestorListado();	
	try {
		SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
		GestorListado miGestorListado = new GestorListado();
		procesadorXML.parse(new File(RUTA_FICHERO), miGestorListado);
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
	
	public GestorListado() {
		super();
	}
	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		elemento = nombreC;
	}
	


	public void characters(char[] ch, int start, int length) throws SAXException {
		// Quitamos los saltos de línea y tabuladores y lo imprimimos
		String car = new String(ch, start, length);
		String [][] autores = new String[30][30];
		int i=0;
		int y=0;
		car = car.replaceAll("[\t\n]", "");
		//System.out.println(elemento);
		
		if(car.trim().length()>0) {
			
			if(elemento=="titulo") {
				System.out.print(car);
			}
			if(elemento=="editorial") {
				System.out.println("  (" + car+")");
			}
			if(elemento=="autor") {
				autores[i][y]=car;
				y++;
			}
			//for(y<autores)
			System.out.println(autores);
			i++;
			
			
		}
		
	}
}

class GestorExtremos extends org.xml.sax.helpers.DefaultHandler{

}
class GestorCoautoria extends org.xml.sax.helpers.DefaultHandler{

}
class GestorBuscar extends org.xml.sax.helpers.DefaultHandler{

}