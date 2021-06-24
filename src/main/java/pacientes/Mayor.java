package pacientes;

import java.io.Serializable;

import personas.Domicilio;

/**
 * Representa un paciente del Rango Etareo Mayor
 * 
 *
 */

public class Mayor extends Paciente implements Serializable {

	/**
	 * Constructor vacio
	 */
	public Mayor() {

	}

	/**
	 * Retorna un paciente Mayor con sus datos Basicos
	 * 
	 * @param nombre          Nombre del mayor
	 * @param apellido        Apellido del mayor
	 * @param dni             Dni del mayor
	 * @param historiaClinica Nuero de historia Clinica del mayor
	 */
	public Mayor(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	/**
	 * Retorna un paciente mayor con sus datos completos
	 * 
	 * @param nombre          Nombre del mayor
	 * @param apellido        Apellido del mayor
	 * @param dni             Dni del mayor
	 * @param telefono        Telefono del mayor
	 * @param domicilio       Domicilio del mayor
	 * @param ciudad          Ciudad del mayor
	 * @param historiaClinica Nuero de historia Clinica del mayor
	 */
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
