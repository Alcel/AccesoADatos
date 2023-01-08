package xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Biblio2 {
	private static String RUTA_FICHERO ="./src/datos/biblioteca.xml";
	public static void main(String[] args) {
	GestorListado nuevoGestoListado = new GestorListado(RUTA_FICHERO);	
	}
}
class GestorListado extends org.xml.sax.helpers.DefaultHandler {
	static String ruta;
	public GestorListado(String ruta) {
	this.ruta=ruta;
	}
	public static void main(String[] args) {
		//SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
		//GestorSAX miGestor = new GestorSAX();
		//procesadorXML.parse(new File(ruta), miGestor);
		
	}
}

class GestorExtremos extends org.xml.sax.helpers.DefaultHandler{

}
class GestorCoautoria extends org.xml.sax.helpers.DefaultHandler{

}
class GestorBuscar extends org.xml.sax.helpers.DefaultHandler{

}