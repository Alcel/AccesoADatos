package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Biblio {
	private static String RUTA_FICHERO="./src/datos/biblioteca.xml";

	public static void main(String[] args) {
		//listado();
		//extremos();
	coautoria();
	}

	public static void listado() {
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		try {
			db = dbf.newDocumentBuilder();
			documento = db.parse(new File(RUTA_FICHERO));
			NodeList lista=documento.getElementsByTagName("libro");
			NodeList listaAutores;
			NodeList listaAutor;
			Node libro;
			Node autor;
			NodeList listalibros;
			String[][] autores =  new String[50][50];

			for(int j =0;j<lista.getLength();j++) {
				libro =lista.item(j);
				listalibros = libro.getChildNodes();
				for(int i=0;i<listalibros.getLength();i++) {
					Node nodo = listalibros.item(i);
					if(nodo.getNodeType()==Node.ELEMENT_NODE) {
						if(nodo.getNodeName().equals("titulo")) {
							System.out.print(nodo.getFirstChild().getNodeValue());
						}
						if(nodo.getNodeName().equals("editorial")) {
							System.out.println("  ("+nodo.getFirstChild().getNodeValue()+")");
							for(int z=0;z<autores.length;z++) {
								if(autores[j][z]!=null) {
									System.out.println("  -"+autores[j][z]);
								}
							}
							System.out.println();
						}
						if(nodo.getNodeName().equals("autores")) {
							listaAutores=nodo.getChildNodes();
							for(int y=0;y<listaAutores.getLength();y++) {
								autor=listaAutores.item(y);
								if(autor.getNodeType()==Node.ELEMENT_NODE) {
									autores[j][y]=autor.getFirstChild().getNodeValue();
								}
							}
						}
					}
				}
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
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		Float pCaro=0F;
		Float pBarato=100F;
		Float precio=0F;
		String nCaro="";
		String nBarato="";
		String nombre="";
		try {
			db = dbf.newDocumentBuilder();
			documento = db.parse(new File(RUTA_FICHERO));
			NodeList lista=documento.getElementsByTagName("libro");
			Node libro;
			NodeList listalibros;
			
			
			

			for(int j =0;j<lista.getLength();j++) {
				libro =lista.item(j);
				listalibros = libro.getChildNodes();
				for(int i=0;i<listalibros.getLength();i++) {
					Node nodo = listalibros.item(i);
					if(nodo.getNodeType()==Node.ELEMENT_NODE) {
						if(nodo.getNodeName().equals("titulo")) {
							nombre=nodo.getFirstChild().getNodeValue();
						}
						if(nodo.getNodeName().equals("precio")) {
							precio=Float.valueOf(nodo.getFirstChild().getNodeValue());
							
							if(precio<pBarato) {
								pBarato=precio;
								nBarato=nombre;
							}
							if(precio>pCaro) {
								pCaro=precio;
								nCaro=nombre;
							}
						}

					}
					

				}

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
		
		System.out.println("El mas barato: "+nBarato+", "+pBarato);
		System.out.println("El mas caro: "+nCaro+", "+pCaro);
		

	}
	public static void coautoria() {
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		System.out.println("Los libros con coautoria son:");
		try {
			db = dbf.newDocumentBuilder();
			documento = db.parse(new File(RUTA_FICHERO));
			NodeList lista=documento.getElementsByTagName("libro");
			NodeList listaAutores;
			NodeList listaAutor;
			Node libro;
			Node autor;
			NodeList listalibros;
			String titulo="";
			int cantidad=0;
			

			for(int j =0;j<lista.getLength();j++) {
				
				libro =lista.item(j);
				listalibros = libro.getChildNodes();
				for(int i=0;i<listalibros.getLength();i++) {
					Node nodo = listalibros.item(i);
					if(nodo.getNodeType()==Node.ELEMENT_NODE) {
						if(nodo.getNodeName().equals("titulo")) {
							titulo=nodo.getFirstChild().getNodeValue();
						}
						
						}
						if(nodo.getNodeName().equals("autores")) {
							listaAutores=nodo.getChildNodes();
							for(int y=0;y<listaAutores.getLength();y++) {
								autor=listaAutores.item(y);
								if(autor.getNodeType()==Node.ELEMENT_NODE) {
									cantidad++;
								}
							}
							
						}
						if(cantidad>2) {
							System.out.println("-"+titulo);
							cantidad=0;
						

					}

				}

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
	public static void buscar(String isbn) {

	}
	public static void reducir() {

	}
	public static void aumentar(String titulo, String editorial, double precio, String[]
			autores, String[] autoras) {
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		Element root;
		Element libro;
		Element tituloXML;
		Element autoresXML;
		Element autorXML;
		Element generoXML;
		Element editorialXML;
		Element precioXML;
		
		
		try {
			db = dbf.newDocumentBuilder();
			documento = db.parse(new File(RUTA_FICHERO));
			root = documento.getDocumentElement();
			libro = documento.createElement("libro");
			
			tituloXML = documento.createElement("titulo");
			tituloXML.setTextContent(titulo);
			libro.appendChild(tituloXML);
			
			autoresXML = documento.createElement("autores");
			
			for(int i=0;i<autores.length;i++) {
				autorXML = documento.createElement("autor");
				autoresXML.appendChild(autorXML);
				autoresXML.setAttribute("sexo","masculino");
				
			}
			for(int i=0;i<autoras.length;i++) {
				autorXML = documento.createElement("autor");
				autoresXML.appendChild(autorXML);
				autoresXML.setAttribute("sexo","femenino");
			}
			
			generoXML = documento.createElement("genero");
			editorialXML = documento.createElement("editorial");
			precioXML = documento.createElement("precio");
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void quitarEspaciosEnBlanco(Node node) {
		NodeList listaHijos = node.getChildNodes();

		for (int i = 0; i < listaHijos.getLength(); ++i) {
			Node hijo = listaHijos.item(i);

			if (hijo.getNodeType() == Node.TEXT_NODE) {
				hijo.setTextContent(hijo.getTextContent().trim());
			}

			quitarEspaciosEnBlanco(hijo);
		}
	}
}
