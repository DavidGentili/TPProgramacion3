package pacientes;

import personas.Domicilio;

public class Ninio extends Paciente implements IRangoHetareo {

	public Ninio(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Ninio(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public IRangoHetareo comparaIngreso(IRangoHetareo otro) {
		return otro.comparaConNinio(this);
	}

	@Override
	public IRangoHetareo comparaConNinio(IRangoHetareo otro) {
		return this;
	}

	@Override
	public IRangoHetareo comparaConJoven(IRangoHetareo otro) {
		return this;
	}

	@Override
	public IRangoHetareo comparaConMayor(IRangoHetareo otro) {
		return otro;
	}

}
