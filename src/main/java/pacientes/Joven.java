package pacientes;

import personas.Domicilio;

public class Joven extends Paciente{

	public Joven(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Joven(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public Paciente comparaIngreso(Paciente otro) {
		return otro.comparaConJoven(this);
	}

	@Override
	public Paciente comparaConNinio(Paciente otro) {
		return otro;
	}

	@Override
	public Paciente comparaConJoven(Paciente otro) {
		return this;
	}

	@Override
	public Paciente comparaConMayor(Paciente otro) {
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + " Joven ";
	}

}
