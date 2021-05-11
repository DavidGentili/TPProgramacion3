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
	public IRangoEtareo comparaIngreso(IRangoEtareo otro) {
		return otro.comparaConNinio(this);
	}

	@Override
	public IRangoEtareo comparaConNinio(IRangoEtareo otro) {
		return this;
	}

	@Override
	public IRangoEtareo comparaConJoven(IRangoEtareo otro) {
		return this;
	}

	@Override
	public IRangoEtareo comparaConMayor(IRangoEtareo otro) {
		return otro;
	}

	@Override
	public String toString() {
		return super.toString() + " niño ";
	}

}
