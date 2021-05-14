package pacientes;

import personas.Domicilio;

public class Mayor extends Paciente {

	public Mayor(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Mayor(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public Paciente comparaIngreso(Paciente otro) {
		return otro.comparaConMayor(this);
	}

	@Override
	public Paciente comparaConNinio(Paciente otro) {
		return this;
	}

	@Override
	public Paciente comparaConJoven(Paciente otro) {
		return otro;
	}

	@Override
	public Paciente comparaConMayor(Paciente otro) {
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + " Mayor";
	}

}
