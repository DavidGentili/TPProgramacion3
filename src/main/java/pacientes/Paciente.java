package pacientes;

import java.util.ArrayList;

import personas.Domicilio;
import personas.Persona;
import prestaciones.IPrestacion;

public class Paciente extends Persona {
	protected int nroHistoriaClinica;
	protected ArrayList<IPrestacion> prestaciones = new ArrayList<IPrestacion>();

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

	public void agregarPrestacion(IPrestacion prestacion) {
		this.prestaciones.add(prestacion);
	}

	public void vaciarPrestaciones() {
		prestaciones.clear();
	}

	public ArrayList<IPrestacion> getPrestaciones() {
		return prestaciones;
	}

	@Override
	public String toString() {
		return super.toString() + " nroHistoriaClinica=" + nroHistoriaClinica;
	}

}
