package habitaciones;

import java.util.GregorianCalendar;

import pacientes.Paciente;

public class RegistroInternacionPaciente {

	protected Paciente paciente;
	protected GregorianCalendar fechaIngreso;
	protected GregorianCalendar fechaEgreso;
	protected double costoInternacion;
	protected String tipoHabitacion;

	public RegistroInternacionPaciente(Paciente paciente, GregorianCalendar fechaIngreso) {
		this.paciente = paciente;
		this.fechaIngreso = fechaIngreso;
	}

	public GregorianCalendar getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(GregorianCalendar fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public GregorianCalendar getFechaIngreso() {
		return fechaIngreso;
	}

	public double getCostoInternacion() {
		return costoInternacion;
	}

	public void setCostoInternacion(double costoInternacion) {
		this.costoInternacion = costoInternacion;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	
}
