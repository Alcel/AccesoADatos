package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Biblio {
	private static String RUTA_FICHERO="./src/datos/biblioteca.xml";
	private static String NUEVA_RUTA_FICHERO="./src/datos/biblioteca2.xml";

	public static void main(String[] args) {
		String[] autores = {"Juan","Alfonso"};
		String[] autoras = {"Juana","Alfonsa"};
		File ruta = new File(RUTA_FICHERO);
		if(ruta.exists()) {
			//listado();
			//extremos();
			//coautoria();
			//buscar("84-415-1576-X");
			//aumentar("El vivir","Amaya",22.2, autores,autoras);
			//reducir();
		}
		else {
			System.err.println("La ruta introducida es incorrecta pues no existe");
		}
		
		/*Optimizar metodos que usan variante de listado(), listado usa un array para los autores debido al orden
		orden innecesario en el resto de metodos*/
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
			String[][] autores = new String[15][15];

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
			e.printStackTrace();
		} catch (IOException e) {
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
			e.printStackTrace();
		} catch (IOException e) {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	public static void buscar(String isbn) {
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		String isbnGet="";
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
				if(libro.hasAttributes()) {
					isbnGet=libro.getAttributes().getNamedItem("isbn").getNodeValue();
				}
				if(isbnGet.equals(isbn)) {
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
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void reducir() {
		/*Recojo los datos de titulo autor y precio todo sin atributos y lo almaceno
		despues tomo esos datos y creo un nuevo archivo de nombre biblioteca2.xml
			-Libro
				-titulo
				-autor
				-precio y moneda
		 *
		 */
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
			ArrayList<String> titulos = new ArrayList<>();
			ArrayList<String> precios = new ArrayList<>();
			Element etiquetaXML;
			Element libroXML;
			Text textoXML;

			for(int j =0;j<lista.getLength();j++) {
				libro =lista.item(j);
				listalibros = libro.getChildNodes();
				for(int i=0;i<listalibros.getLength();i++) {
					Node nodo = listalibros.item(i);
					if(nodo.getNodeType()==Node.ELEMENT_NODE) {
						if(nodo.getNodeName().equals("titulo")) {
							titulos.add(nodo.getFirstChild().getNodeValue());
						}
						if(nodo.getNodeType()==Node.ELEMENT_NODE) {
							if(nodo.getNodeName().equals("precio")) {
								if(nodo.getAttributes().getNamedItem("moneda")!=null) {
									precios.add(nodo.getFirstChild().getNodeValue()+" "+nodo.getAttributes().getNamedItem("moneda").getNodeValue());
								}
								else {
									precios.add(nodo.getFirstChild().getNodeValue());
								}

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
			}
			DOMImplementation arbol = db.getDOMImplementation();
			Document documentoCreado = arbol.createDocument(null, "biblioteca", null);
			documento.setXmlVersion("1.0");
			Element eltoRaiz = documentoCreado.getDocumentElement();
			for(int i=0;i<titulos.size();i++) {
				libroXML = documentoCreado.createElement("libro");
				eltoRaiz.appendChild(libroXML);
				etiquetaXML = documentoCreado.createElement("titulo");
				libroXML.appendChild(etiquetaXML);
				textoXML = documentoCreado.createTextNode(titulos.get(i));
				etiquetaXML.appendChild(textoXML);
				for(int y=0;y<autores[i].length;y++) {
					if(autores[i][y]!=null) {
						etiquetaXML = documentoCreado.createElement("autor");
						libroXML.appendChild(etiquetaXML);
						textoXML = documentoCreado.createTextNode(autores[i][y]);
						etiquetaXML.appendChild(textoXML);}
				}
				etiquetaXML = documentoCreado.createElement("precio");
				libroXML.appendChild(etiquetaXML);

				textoXML = documentoCreado.createTextNode(precios.get(i));
				etiquetaXML.appendChild(textoXML);
			}
			Transformer conversor = TransformerFactory.newInstance().newTransformer();

			conversor.setOutputProperty(OutputKeys.INDENT, "yes");
			conversor.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			Source fuente = new DOMSource(documentoCreado);

			StreamResult destino = new StreamResult(new File(NUEVA_RUTA_FICHERO));

			conversor.transform(fuente, destino);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}


	}
	public static void aumentar(String titulo, String editorial, double precio, String[]
			autores, String[] autoras) {
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document documento;
		Element root;
		Element libro;
		Element elementoXML;
		Element autorXML;
		
		String valor = String.valueOf(precio);
		

		try {
			
			db = dbf.newDocumentBuilder();
			documento = db.parse(new File(RUTA_FICHERO));
			root = documento.getDocumentElement();
			libro = documento.createElement("libro");
			elementoXML = documento.createElement("titulo");

			Text textoXML = documento.createTextNode(titulo);

			elementoXML.appendChild(textoXML);
			libro.appendChild(elementoXML);
			elementoXML = documento.createElement("autores");
			libro.appendChild(elementoXML);
			
			
			//Metodo para eliminar espacios
			XPath xp = XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xp.evaluate("//text()[normalize-space(.)='']", documento, XPathConstants.NODESET);
			
			for (int i=0; i < nl.getLength(); ++i) {
			    Node node = nl.item(i);
			    node.getParentNode().removeChild(node);
			}
			//**************************************
			for(int i=0;i<autores.length;i++) {
				autorXML = documento.createElement("autor");
				textoXML = documento.createTextNode(autores[i]);
				autorXML.appendChild(textoXML);
				elementoXML.appendChild(autorXML);
				autorXML.setAttribute("sexo","masculino");
			}
			for(int i=0;i<autoras.length;i++) {
				autorXML = documento.createElement("autor");
				textoXML = documento.createTextNode(autoras[i]);
				autorXML.appendChild(textoXML);
				elementoXML.appendChild(autorXML);
				autorXML.setAttribute("sexo","femenino");
			}

			elementoXML = documento.createElement("genero");
			libro.appendChild(elementoXML);
			elementoXML = documento.createElement("editorial");
			libro.appendChild(elementoXML);
			elementoXML = documento.createElement("precio");
			textoXML = documento.createTextNode(valor);
			elementoXML.appendChild(textoXML);
			libro.appendChild(elementoXML);
			root.appendChild(libro);
			Transformer conversor = TransformerFactory.newInstance().newTransformer();

			conversor.setOutputProperty(OutputKeys.INDENT, "yes");
			conversor.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			Source fuente = new DOMSource(documento);

			StreamResult destino = new StreamResult(new File(RUTA_FICHERO));

			conversor.transform(fuente, destino);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
