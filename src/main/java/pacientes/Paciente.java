package pacientes;

import java.util.ArrayList;
import java.util.Iterator;

import personas.Domicilio;
import personas.Persona;
import prestaciones.Prestacion;

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

	public int getNroHistoriaClinica() {
		return this.nroHistoriaClinica;
	}

	@Override
	public String toString() {
		return super.toString() + " nroHistoriaClinica=" + nroHistoriaClinica;
	}

	public void agregaPrestacion(Prestacion prestacion) {
		this.prestaciones.add(prestacion);
	}

	public ArrayList<Prestacion> getPretaciones() {
		return prestaciones;
	}

	/**
	 * <br>
	 * pre:<br>
	 * El paciente ya no debe estar mas en la lista de atencion.
	 */
	public void limpiaPrestaciones() {
		prestaciones.clear();
	}

	public abstract Paciente comparaIngreso(Paciente otro);

	public abstract Paciente comparaConNinio(Paciente otro);

	public abstract Paciente comparaConJoven(Paciente otro);

	public abstract Paciente comparaConMayor(Paciente otro);

}
