
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** La clase Puntuacion contiene una puntuación de jugador
 * en el juego del Buscaminas
 * 
 * @author administrador
 */
public class Puntuacion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int minutos;
	private int segundos;
	private Calendar fecha;
	
	/** Constructor
	 * 
	 * @param nombre - del jugador
	 * @param minutos - que ha empleado en resolverlo
	 * @param segundos - que ha empleado en resolverlo
	 * @param fecha - en la que se ha producido la puntuación
	 */
	public Puntuacion (String nombre, int minutos, int segundos, Calendar fecha) {
		super();
		this.nombre=nombre;
		this.minutos=minutos;
		this.segundos=segundos;
		this.fecha=fecha;
	}
	
	/**
	 * Método que determina si una puntuación es mejor que otra.
	 * Se entiende que es mejor la puntuación en la que menos tiempo se invierte.
	 * @param p - Puntuación con la que se compara.
	 * @return true: si esta puntuación es mejor. false: en caso contrario
	 */
	public boolean mejorQue(Puntuacion p) {
		boolean mejor = false;
		
		if ((this.minutos < p.minutos) || ((this.minutos==p.minutos) && (this.segundos<p.segundos))) {
			mejor = true;
		}
		
		return mejor;
	}
	
	/** Convierte esta puntuación en una cadena
	 * 
	 */
	@Override
	public String toString() {
		String cadena = "";
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		cadena = this.nombre+"\t\t"+this.minutos+"m y "+this.segundos+"s\t\t"+formato.format(this.fecha.getTime());
		return cadena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
}
