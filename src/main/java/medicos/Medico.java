package medicos;

import personas.Domicilio;
import personas.Persona;

public class Medico extends Persona implements IMedico {

	protected int matricula;
	protected static double sueldoBasico;

	public Medico(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int matricula) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad);
		this.matricula = matricula;
	}

	public Medico(String nombre, String apellido, int dni, int matricula) {
		super(nombre, apellido, dni);
		this.matricula = matricula;
	}

	public int getMatricula() {
		return matricula;
	}

	@Override
	public String toString() {
		return super.toString() + " Nro de Matricula: " + matricula;
	}

	@Override
	public double getSueldo() {
		return Medico.sueldoBasico;
	}

	public static void setSueldoBasico(double sueldoBasico) {
		Medico.sueldoBasico = sueldoBasico;
	}

	public static double getSueldoBasico() {
		return Medico.sueldoBasico;
	}

}
