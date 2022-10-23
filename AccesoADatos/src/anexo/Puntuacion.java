package anexo;

import java.io.Serializable;
import java.util.Calendar;

public class Puntuacion implements Serializable{
	private static final long serialVersionUID = 4089173565140384300L;
	private String nombre;
	private int minutos;
	private int segundos;
	private Calendar fecha;
	
	public Puntuacion() {
		super();
	}

	public Puntuacion(String nombre, int minutos, int segundos, Calendar fecha) {
		super();
		this.nombre = nombre;
		this.minutos = minutos;
		this.segundos = segundos;
		this.fecha = fecha;
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

	public boolean mejorQue(Puntuacion p) {
		boolean resultado;
		int tiempoLocal;
		int tiempoParametro;
		tiempoParametro=(p.minutos*60)+p.segundos;
		tiempoLocal=(minutos*60)+segundos;
		if(tiempoParametro<tiempoLocal) {
			resultado=true;
		}
		else {
			resultado=false;
		}
		
		return resultado;
		
	}
	
	public String toString() {
		return "La puntuacion "+nombre+", tiene un tiempo de "+minutos+" minutos y "+segundos+" segundos."
				+ " Esta puntuacion fue realizada el "+fecha;
		
	}
}
