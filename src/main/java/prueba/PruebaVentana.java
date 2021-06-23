package prueba;

import clinica.Clinica;
import controlador.Controlador;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.DomicilioInvalido;
import exceptions.EspecialidadNoIndicadaException;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.MontoInvalidoException;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDePacienteIncorrectoException;
import personas.Domicilio;
import vista.VentanaClinica;

public class PruebaVentana {

	public static void main(String[] args) {
		VentanaClinica ventana = new VentanaClinica();
		Clinica colon = null;
		Domicilio dom = null;
		try {
			Clinica.setSueldoBasicoMedico(1000);
			Clinica.setCostoHabitacionCompartida(1000);
			Clinica.setCostoHabitacionPrivada(1500);
			Clinica.setCostoTerapiaIntensiva(2200);
			dom = new Domicilio("Colon", 2350);
			colon = Clinica.getInstancia("Colon", dom, "2234791040", "Mar del Plata");
			colon.agregaMedico("Miguel", "Sosa", 99999999, 10536, "Cirujano", "Magister", "Permanente");
			colon.ingresaPaciente("Javier", "Rodriguez", 44444444, 10531, "Joven");
			colon.ingresaPaciente("Mario", "Martinez", 55555555, 11302, "Ninio");
			colon.atiendeSiguiente();
			Controlador c = new Controlador(ventana,ventana, ventana, colon);
		} catch (DomicilioInvalido | MedicoYaAgregadoException | ContratacionNoIndicadaExceptions
				| ContratacionNoRegistradaExceptions | EspecialidadNoRegistradaExceptions
				| PosgradoNoRegistradoExceptions | MontoInvalidoException | TipoDePacienteIncorrectoException
				| EspecialidadNoIndicadaException e) {
			System.out.println(e.getMessage());
		}

	}

}
