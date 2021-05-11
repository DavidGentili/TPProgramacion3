package habitaciones;

import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

import exceptions.FechaInvalidaException;
import pacientes.Paciente;

public class RegistroPaciente {

	protected Paciente paciente;
	protected GregorianCalendar fechaIngreso;
	protected GregorianCalendar fechaEgreso;

	public RegistroPaciente(Paciente paciente, GregorianCalendar fechaIngreso) {
		this.paciente = paciente;
		this.fechaIngreso = fechaIngreso;
	}

	public GregorianCalendar getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(GregorianCalendar fechaEgreso) throws FechaInvalidaException {
		if (fechaIngreso.compareTo(fechaEgreso) <= 0)
			this.fechaEgreso = fechaEgreso;
		else
			throw new FechaInvalidaException("La fecha ingresada es incorrecta");
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public GregorianCalendar getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * pre:La fecha de egreso debe estar cargada.
	 * pos:Se devuelven la cantidad de dias internado.
	 * @return
	 */
	public int getCantidadDeDias() {
		int diferencia = (int) ( (this.fechaEgreso.getTime().getTime() - this.getFechaIngreso().getTime().getTime())/86400000 );
		return diferencia;
	}

}
