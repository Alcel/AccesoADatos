package repaso.examenNoviembre;

import java.io.Serializable;

public class Alumno implements Serializable {
	private static final long serialVersionUID = 4089273565140374300L;
	private String nombre;
	private String grupo;
	

	private double media;
	
	public Alumno() {
		super();
	}
	
	public Alumno(String nombre,String grupo,double media) {
		super();
		this.nombre=nombre;
		this.grupo=grupo;
		this.media=media;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", grupo=" + grupo + ", media=" + media + "]";
	}

}
