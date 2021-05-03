package pacientes;

import personas.Persona;

public abstract class Paciente extends Persona implements IPaciente {
	private int nroHistoriaClinica;// un static autoincrimentable?

	public Paciente(String nombre, String apellido, int DNI) {
		super(nombre, apellido, DNI);
	}

	public abstract IPaciente ingreso(IPaciente otro);

	public abstract IPaciente comparaConNiño(IPaciente otro);

	public abstract IPaciente comparaConJoven(IPaciente otro);

	public abstract IPaciente comparaConMayor(IPaciente otro);

	public int getNumeroHistoriaClinica() {
		return this.nroHistoriaClinica;
	}
}
