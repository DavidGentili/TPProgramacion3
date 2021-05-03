package medicos;

import personas.Persona;

public class Medico extends Persona implements IMedico {

	private String matricula;
	private static double honorarioBasico = 100;

	public Medico(String nombre, String apellido, int DNI, String matricula) {
		super(nombre, apellido, DNI);
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
