package boletin2;

import java.io.Serializable;

public class Empleado implements Serializable {
	private static final long serialVersionUID = 4089173595140384300L;
	private String nombre;  //2*c
	private int edad;		//4
	private double sueldo;  //8
	private boolean jubilado; //4
	
	public Empleado() {
		super();
	}

	public Empleado(String nombre, int edad, double sueldo, boolean jubilado) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.sueldo = sueldo;
		this.jubilado = jubilado;
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

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public boolean isJubilado() {
		return jubilado;
	}

	public void setJubilado(boolean jubilado) {
		this.jubilado = jubilado;
	}
}
