package pacientes;

import personas.Domicilio;

public class Mayor extends Paciente implements IRangoEtareo {

	public Mayor(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Mayor(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	@Override
	public IRangoEtareo comparaIngreso(IRangoEtareo otro) {
		return otro.comparaConMayor(this);
	}

	@Override
	public IRangoEtareo comparaConNinio(IRangoEtareo otro) {
		return this;
	}

	@Override
	public IRangoEtareo comparaConJoven(IRangoEtareo otro) {
		return otro;
	}

	@Override
	public IRangoEtareo comparaConMayor(IRangoEtareo otro) {
		return this;
	}

}
