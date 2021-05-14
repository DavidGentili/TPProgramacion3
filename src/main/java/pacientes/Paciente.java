package pacientes;

import java.util.ArrayList;
import java.util.Iterator;

import personas.Domicilio;
import personas.Persona;
import prestaciones.Prestacion;

/**
 * Representa un paciente generico, no se puede instanciar
 * 
 *
 */
public abstract class Paciente extends Persona {
	protected int nroHistoriaClinica;
	protected ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();

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

	@Override
	public String toString() {
		return super.toString() + " nroHistoriaClinica=" + nroHistoriaClinica;
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
	public ArrayList<Prestacion> getPretaciones() {
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
