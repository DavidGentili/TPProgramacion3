package pacientes;

import personas.Domicilio;

public class Ninio extends Paciente{

	public Ninio(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Ninio(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public Paciente comparaIngreso(Paciente otro) {
		return otro.comparaConNinio(this);
	}

	@Override
	public Paciente comparaConNinio(Paciente otro) {
		return this;
	}

	@Override
	public Paciente comparaConJoven(Paciente otro) {
		return this;
	}

	@Override
	public Paciente comparaConMayor(Paciente otro) {
		return otro;
	}

	@Override
	public String toString() {
		return super.toString() + " niño ";
	}

}
