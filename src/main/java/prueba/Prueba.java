package prueba;

import java.util.GregorianCalendar;

import clinica.Clinica;
import exceptions.CantidadDeDiasErroneosException;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.DomicilioInvalido;
import exceptions.EspecialidadNoIndicadaException;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.FechaInvalidaException;
import exceptions.MedicoNoEncontradoException;
import exceptions.MedicoYaAgregadoException;
import exceptions.MontoInvalidoException;
import exceptions.PacienteNoAtendido;
import exceptions.PacienteNoEncontrado;
import exceptions.PacienteYaExistenteException;
import exceptions.PacienteYaIngresadoException;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDeHabitacionIncorrectaException;
import exceptions.TipoDePacienteIncorrectoException;
import personas.Domicilio;

public class Prueba {

	public static void main(String[] args) throws EspecialidadNoIndicadaException {
		Clinica colon = null;
		Domicilio dom = null;
		try {
			Clinica.setSueldoBasicoMedico(1000);
			Clinica.setCostoHabitacionCompartida(1000);
			Clinica.setCostoHabitacionPrivada(1500);
			Clinica.setCostoTerapiaIntensiva(2200);
			dom = new Domicilio("Colon", 2350);
			colon = Clinica.getInstancia("Colon", dom, "2234791040", "Mar del Plata");
			colon.agregaMedico("Migue", "Sosa", 99999999, 10536, "Cirujano", "Magister", "Permanente");
			colon.agregaPaciente("Javier", "Rodriguez", 44444444, 10531, "Joven");
			colon.agregaPaciente("Mario", "Martinez", 55555555, 11302, "Ninio");
			colon.atiendeSiguiente();
			colon.agregaConsulta(colon.getPaciente(10531), 10536, 2);
			colon.agregaInternacion(colon.getPaciente(10531), "habitacion privada", 2);

			System.out.println(colon.facturaPaciente(colon.getPaciente(10531), new GregorianCalendar(2021, 5, 15)));

			System.out.println();
			System.out.println(colon.reporteActividadMedica(colon.getMedico(10536), new GregorianCalendar(2021, 5, 10),
					new GregorianCalendar(2021, 5, 25)));
		} catch (DomicilioInvalido | MedicoYaAgregadoException | ContratacionNoRegistradaExceptions
				| EspecialidadNoRegistradaExceptions | PosgradoNoRegistradoExceptions | MontoInvalidoException
				| TipoDePacienteIncorrectoException | CantidadDeDiasErroneosException | PacienteNoEncontrado
				| MedicoNoEncontradoException | TipoDeHabitacionIncorrectaException | PacienteNoAtendido
				| FechaInvalidaException | PacienteYaExistenteException | PacienteYaIngresadoException e) {
			System.out.println(e.getMessage());
		}

	}
}
