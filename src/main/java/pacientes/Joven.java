package pacientes;

import java.io.Serializable;

import personas.Domicilio;

/**
 * Representa un paciente del Rango Etareo Mayor
 * 
 *
 */

public class Joven extends Paciente {

	/**
	 * Constructor vacio
	 */
	public Joven() {

	}

	/**
	 * Retorna un paciente Joven con sus datos Basicos
	 * 
	 * @param nombre          Nombre del joven
	 * @param apellido        Apellido del joven
	 * @param dni             Dni del joven
	 * @param historiaClinica Nuero de historia Clinica del joven
	 */
	public Joven(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	
	/**
	 * Retorna un paciente joven con sus datos completos
	 * 
	 * @param nombre          Nombre del joven
	 * @param apellido        Apellido del joven
	 * @param dni             Dni del joven
	 * @param telefono        Telefono del joven
	 * @param domicilio       Domicilio del joven
	 * @param ciudad          Ciudad del joven
	 * @param historiaClinica Nuero de historia Clinica del joven
	 */
	public Joven(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
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
		return otro.comparaConJoven(this);
	}

	/**
	 * compara al joven actual con un ninio
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	@Override
	public Paciente comparaConNinio(Paciente otro) {
		return otro;
	}

	/**
	 * compara al joven actual con otro joven
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */

	@Override
	public Paciente comparaConJoven(Paciente otro) {
		return this;
	}

	/**
	 * compara al joven actual con un mayor
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
		return super.toString() + " Joven ";
	}

}
