package boletin2;
import java.io.Serializable;

/* Esta clase implementa la interfaz Serializable
 * Gracias a eso, los objetos de esta clase pueden
 * transformarse en una cadena de bits y
 * ser enviados/recibidos a través de streams
 */
public class AD12_Empleado implements Serializable {
	private static final long serialVersionUID = 7496839162089392298L;
	private String nombre;
	private int edad;
	private String cargo;
	private double sueldo;

	public AD12_Empleado(String nombre, int edad, String cargo, double sueldo) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.cargo = cargo;
		this.sueldo = sueldo;
	}

	public AD12_Empleado() {
		this.nombre = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}
