package habitaciones;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import exceptions.FechaInvalidaException;
import exceptions.HabitacionLlena;
import exceptions.HabitacionVacia;
import exceptions.PacienteNoEncontrado;
import pacientes.Paciente;

public abstract class Habitacion {

	protected int nroHabitacion;
	protected int limite;// Limite de personas dentro de la habitacion parametro pasado por hijo
	protected HashMap<Integer, RegistroPaciente> pacientes = new HashMap<Integer, RegistroPaciente>();

	public Habitacion(int identificador, int limite) {
		this.nroHabitacion = identificador;
		this.limite = limite;
	}

	public int getNroHabitacion() {
		return nroHabitacion;
	}

	public void setNroHabitacion(int identificador) {
		this.nroHabitacion = identificador;
	}

	public void ingresaPaciente(Paciente paciente, GregorianCalendar fechaIngreso) throws HabitacionLlena {
		if (pacientes.size() < this.limite) {
			this.pacientes.put(paciente.getNroHistoriaClinica(), new RegistroPaciente(paciente, fechaIngreso));
		} else
			throw new HabitacionLlena("La habitacion a la cual desea agregar un paciente esta llena");
	}

	public double egresaPaciente(Paciente paciente, GregorianCalendar fechaEgreso)
			throws PacienteNoEncontrado, HabitacionVacia, FechaInvalidaException {
		if (this.pacientes.size() > 0) {
			if (this.pacientes.containsKey(paciente.getNroHistoriaClinica())) {
				RegistroPaciente registro = null;
				double costo = 0;
				registro = this.pacientes.get(paciente.getNroHistoriaClinica());
				registro.setFechaEgreso(fechaEgreso);
				costo = this.getCosto(registro.getCantidadDeDias());
				this.pacientes.remove(paciente.getNroHistoriaClinica());
				return costo;
			} else
				throw new PacienteNoEncontrado("El paciente que intenta retirar no se encuentra en esta habitacion");
		} else
			throw new HabitacionVacia("La habitacion de la cual quiere extraer un paciente esta vacia");
	}

	public Paciente getPaciente(int nroHistoriaClinica) throws PacienteNoEncontrado {
		if (this.pacientes.containsKey(nroHistoriaClinica))
			return this.pacientes.get(nroHistoriaClinica).getPaciente();
		else
			throw new PacienteNoEncontrado("El paciente que busca no se encuentra en esta habitacion");
	}

	public abstract double getCosto(int cantidadDeDias);

	public Iterator getPacientesdeLaHabitacion() {
		ArrayList<Paciente> aux = new ArrayList<Paciente>();
		for(Integer i : pacientes.keySet())
			aux.add(pacientes.get(i).getPaciente());
		return aux.iterator();
	}

}
