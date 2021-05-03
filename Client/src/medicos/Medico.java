package medicos;

import personas.Domicilio;
import personas.Persona;

public class Medico extends Persona implements IMedico {

	private String matricula;
	private static double honorarioBasico = 100;

	public Medico(String nombre, String apellido, int DNI, String matricula) {
		super(nombre, apellido, DNI);
		this.matricula = matricula;
	}

	public Medico(String nombre, String apellido, int DNI, String telefono, Domicilio domicilio, String ciudad,
			String matricula) {
		super(nombre, apellido, DNI, telefono, domicilio, ciudad);
		this.matricula = matricula;
	}

	public Medico(String nombre, String apellido, int DNI, Domicilio domicilio, String ciudad, String matricula) {
		super(nombre, apellido, DNI, domicilio, ciudad);
		this.matricula = matricula;
	}

	public Medico(String nombre, String apellido, int DNI, String telefono, String matricula) {
		super(nombre, apellido, DNI, telefono);
		this.matricula = matricula;
	}

	// Este metodo retornara el hornorario basico de los medicos
	public double getHonorario() {
		return honorarioBasico;
	}

	public String getMatricula() {
		return this.matricula;
	}
}
