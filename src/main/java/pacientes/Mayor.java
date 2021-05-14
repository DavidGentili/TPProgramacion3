package pacientes;

import personas.Domicilio;

/**
 * Representa un paciente del Rango Etareo Mayor
 * 
 *
 */

public class Mayor extends Paciente {

	public Mayor(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Mayor(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
	}

	/**
	 * compara entre dos pacientes cual tiene prioridad en la sala de espera privada
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaIngreso(Paciente otro) {
		return otro.comparaConMayor(this);
	}

	/**
	 * compara al mayor actual con un ninio
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaConNinio(Paciente otro) {
		return this;
	}

	/**
	 * compara al mayor actual con un joven
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaConJoven(Paciente otro) {
		return otro;
	}

	/**
	 * compara al mayor actual con otro mayor
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaConMayor(Paciente otro) {
		return this;
	}

	@Override
	public String toString() {
		return super.toString() + " Mayor";
	}

}
