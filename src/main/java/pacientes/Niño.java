package pacientes;

import personas.Domicilio;

public class Niño extends Paciente implements IRangoHetareo {

	public Niño(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Niño(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public IRangoHetareo comparaIngreso(IRangoHetareo otro) {
		return otro.comparaConNiño(this);
	}

	@Override
	public IRangoHetareo comparaConNiño(IRangoHetareo otro) {
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
