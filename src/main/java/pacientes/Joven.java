package pacientes;

import personas.Domicilio;

public class Joven extends Paciente implements IRangoHetareo {

	public Joven(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Joven(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public IRangoHetareo comparaIngreso(IRangoHetareo otro) {
		return otro.comparaConJoven(this);
	}

	@Override
	public IRangoHetareo comparaConNiño(IRangoHetareo otro) {
		return otro;
	}

	@Override
	public IRangoHetareo comparaConJoven(IRangoHetareo otro) {
		return this;
	}

	@Override
	public IRangoHetareo comparaConMayor(IRangoHetareo otro) {
		return this;
	}

}
