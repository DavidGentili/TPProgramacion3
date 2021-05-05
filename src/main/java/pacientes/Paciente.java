package pacientes;

import personas.Domicilio;
import personas.Persona;

public class Paciente extends Persona {
	protected int nroHistoriaClinica;

	public Paciente(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni);
		this.nroHistoriaClinica = historiaClinica;
	}

	public Paciente(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad);
		this.nroHistoriaClinica = historiaClinica;
	}

	public int getNroHistoriaClinica() {
		return this.nroHistoriaClinica;
	}

}
