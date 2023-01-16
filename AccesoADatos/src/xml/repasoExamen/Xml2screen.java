package xml.repasoExamen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class Xml2screen {
	public static void main(String[] args) {
		String direccion="";
		File fichero;
		if(args.length>0) {
			direccion=args[0];
			fichero= new File(direccion);
			if(fichero.exists()) {
				try {
					SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
					Gestor miGestorListado = new Gestor();
					procesadorXML.parse(new File(direccion), miGestorListado);
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}else {
				System.err.println("No existe un archivo con esa direccion");
			}
		}else {
			System.err.println("No hay argumentos");
		}
	}

}
class Gestor extends org.xml.sax.helpers.DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println(qName+":");
		System.out.println(); 
		//Usar un contador que luego sirva para hacer un for que imprima espacios
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}
	
}
