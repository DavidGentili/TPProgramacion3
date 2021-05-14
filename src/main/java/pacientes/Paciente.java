package pacientes;

import personas.Domicilio;
import personas.Persona;

public abstract class Paciente extends Persona{
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

	@Override
	public String toString() {
		return super.toString() + " nroHistoriaClinica=" + nroHistoriaClinica;
	}

	
	public abstract Paciente comparaIngreso(Paciente otro);

	
	public abstract Paciente comparaConNinio(Paciente otro);

	
	public abstract Paciente comparaConJoven(Paciente otro) ;

	public abstract Paciente comparaConMayor(Paciente otro);

}
