package pacientes;

import personas.Domicilio;

public class Mayor extends Paciente implements IRangoHetareo {

	public Mayor(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Mayor(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public IRangoHetareo comparaIngreso(IRangoHetareo otro) {
		return otro.comparaConMayor(this);
	}

	@Override
	public IRangoHetareo comparaConNiño(IRangoHetareo otro) {
		return this;
	}

	@Override
	public IRangoHetareo comparaConJoven(IRangoHetareo otro) {
		return otro;
	}

	@Override
	public IRangoHetareo comparaConMayor(IRangoHetareo otro) {
		return this;
	}

}
