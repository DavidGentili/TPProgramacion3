package pacientes;

import java.io.Serializable;
import java.util.ArrayList;

import personas.Domicilio;
import personas.Persona;
import prestaciones.Prestacion;

/**
 * Representa un paciente generico, no se puede instanciar
 * 
 *
 */
public abstract class Paciente extends Persona implements Serializable {
	protected int nroHistoriaClinica;
	protected ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();

	/**
	 * Constructor vacio
	 */
	public Paciente() {

	}

	public Paciente(String nombre, String apellido, int dni, int historiaClinica) {
		super(nombre, apellido, dni);
		this.nroHistoriaClinica = historiaClinica;
	}

	public Paciente(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int historiaClinica) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad);
		this.nroHistoriaClinica = historiaClinica;
	}

	/**
	 * retorna el numero de historia clinica
	 * 
	 * @return numero de historia clinica
	 */
	public int getNroHistoriaClinica() {
		return this.nroHistoriaClinica;
	}

	/**
	 * Determina las prestaciones de un paciente
	 * 
	 * @param prestaciones Prestaciones de un paciente
	 */
	public void setPrestaciones(ArrayList<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}

	/**
	 * Determina el numero de historia clinica del paciente
	 * 
	 * @param nroHistoriaClinica Numero de historia clinica del paciente
	 */
	public void setNroHistoriaClinica(int nroHistoriaClinica) {
		this.nroHistoriaClinica = nroHistoriaClinica;
	}

	/**
	 * Retorna un string con los datos de persona y el numero de historia clinica
	 */
	@Override
	public String toString() {
		return super.toString() + " Historia clinica" + nroHistoriaClinica;
	}

	/**
	 * Agrega una prestacion sin importar el tipo prestaciones que ha recibido
	 * 
	 * @param prestacion
	 */
	public void agregaPrestacion(Prestacion prestacion) {
		this.prestaciones.add(prestacion);
	}

	/**
	 * Retorna las prestaciones que ha recibido el paciente
	 * 
	 * @return prestaciones recibidas
	 */
	public ArrayList<Prestacion> getPrestaciones() {
		return prestaciones;
	}

	/**
	 * elimina las prestaciones recibidas <br>
	 * pre:<br>
	 * El paciente ya no debe estar mas en la lista de atencion.
	 */
	public void limpiaPrestaciones() {
		prestaciones.clear();
	}

	/**
	 * compara entre dos pacientes cual tiene prioridad en la sala de espera privada
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	public abstract Paciente comparaIngreso(Paciente otro);

	/**
	 * compara el paciente actual con un niño
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	public abstract Paciente comparaConNinio(Paciente otro);

	/**
	 * compara el paciente actual con un joven
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	public abstract Paciente comparaConJoven(Paciente otro);

	/**
	 * compara el paciente actual con un mayor
	 * 
	 * @param otro otro paciente a comparar
	 * @return el paciente prioritario
	 */
	public abstract Paciente comparaConMayor(Paciente otro);

}
