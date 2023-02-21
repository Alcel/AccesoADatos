import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class BiblioSaxEx {
	private static String RUTA_FICHERO ="./src/datos/biblioteca.xml";
	public static void main(String[] args) {
		try {
			SAXParser procesadorXML = SAXParserFactory.newInstance().newSAXParser();
			GestorFemenino miGestorFemenino = new GestorFemenino();
			procesadorXML.parse(new File(RUTA_FICHERO), miGestorFemenino);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

class GestorFemenino extends org.xml.sax.helpers.DefaultHandler{
	static String ruta;
	static String elemento;
	static int tamanio;
	static boolean gen=false;
	static String guard="";
	
	static int i=2;

	
	public GestorFemenino() {
		super();
		System.out.println("Libros en los que participa al menos una mujer:");
	}
	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		elemento = nombreC;
		tamanio=atts.getLength();
	
		if(atts!=null) {
		if(atts.getValue(0)!=null) {
			if(atts.getValue(0).toString().equals("femenino")) {
				gen=true;
				}		
			}
		}
	}


	public void characters(char[] ch, int start, int length) throws SAXException {
		String car = new String(ch, start, length);
		car = car.replaceAll("[\t\n]", "");
			if(gen) {
				if(elemento=="titulo"&&car.trim()!="") {
					System.out.println((" -"+(car)+guard));
					gen=false;
				}
				if(elemento=="editorial"&&car.trim()!="") {
					guard=("  (" + car+")");
			}	
		}
	}
	public void endElement(String uri, String nombre, String nombreC) {}
		
}
