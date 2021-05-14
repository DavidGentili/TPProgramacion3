package pacientes;

import personas.Domicilio;

/**
 * Representa un paciente del Rango Etareo Niño
 * 
 *
 */

public class Ninio extends Paciente {

	public Ninio(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	public Ninio(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
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
		return otro.comparaConNinio(this);
	}

	/**
	 * compara al ninio actual con otro ninio
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaConNinio(Paciente otro) {
		return this;
	}

	/**
	 * compara al ninio actual con un joven
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */

	@Override
	public Paciente comparaConJoven(Paciente otro) {
		return this;
	}

	/**
	 * compara al ninio actual con un mayor
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaConMayor(Paciente otro) {
		return otro;
	}

	@Override
	public String toString() {
		return super.toString() + " niño ";
	}

}
