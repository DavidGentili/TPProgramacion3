package habitaciones;

import java.util.GregorianCalendar;

import pacientes.Paciente;

public interface IHabitacion {
	int getNroHabitacion();

	boolean hayEspacio();

	boolean estaVacia();

	void ingresaPaciente(Paciente paciente, GregorianCalendar fechaIngreso);

	RegistroInternacionPaciente egresaPaciente(GregorianCalendar fechaEgreso);

	Paciente getPaciente(int nroHistoriaClinica);

	boolean estaElPaciente(int nroHistoriaClinica);

	double getCosto();

}
