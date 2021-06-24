package pacientes;

import java.io.Serializable;

import personas.Domicilio;

/**
 * Representa un paciente del Rango Etareo Niño
 * 
 *
 */

public class Ninio extends Paciente implements Serializable {

	/**
	 * Constructor vacio
	 */
	public Ninio() {

	}

	/**
	 * Retorna un paciente Ninio con sus datos basicos de persona
	 * 
	 * @param nombre          Nombre del Ninio
	 * @param apellido        Apellido del Ninio
	 * @param dni             Dni del Ninio
	 * @param historiaClinica Nro de historia Clinica del ninio
	 */
	public Ninio(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni, historiaClinica);
	}

	/**
	 * Retorna un paciente Ninio con sus datos completos
	 * 
	 * @param nombre          Nombre del ninio
	 * @param apellido        Apellido del ninio
	 * @param dni             Dni del ninio
	 * @param telefono        Telefono del ninio
	 * @param domicilio       Domicilio del ninio
	 * @param ciudad          Ciudad del ninio
	 * @param historiaClinica Nuero de historia Clinica del ninio
	 */
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
