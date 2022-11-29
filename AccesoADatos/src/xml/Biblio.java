package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Biblio {
	private static String RUTA_FICHERO="./src/datos/biblioteca.xml";
	
	public static void main(String[] args) {
		listado();
	}
	
	public static void listado() {
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		try {
			db = dbf.newDocumentBuilder();
			documento = db.parse(new File(RUTA_FICHERO));
			NodeList lista=documento.getElementsByTagName("libro");
			
			for(int i=0;i<lista.getLength();i++) {
				Node nodo = lista.item(i);
				String nodoS=nodo.getFirstChild().getNodeValue();
				System.out.println(nodoS);
			}
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void extremos() {
		
	}
	public static void coautoria() {
		
	}
	public static void buscar(String isbn) {
		
	}
	public static void reducir() {
		
	}
}
