package xml.examen2018;
import java.io.Serializable;

public class Empresa implements Serializable {
	private static final long serialVersionUID = 170042333L;
	private String cif;
	private String nombre;
	private String localidad;
	private String encargado1;
	private String encargado2;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEncargado1() {
		return encargado1;
	}

	public void setEncargado1(String encargado1) {
		this.encargado1 = encargado1;
	}

	public String getEncargado2() {
		return encargado2;
	}

	public void setEncargado2(String encargado2) {
		this.encargado2 = encargado2;
	}

	public String toString() {
		String cadena;
		
		cadena = "\t"+this.cif+", "+this.nombre+", "+this.localidad+"\n\t"+this.encargado1+", "+this.encargado2;
		
		return cadena;
	}
}
