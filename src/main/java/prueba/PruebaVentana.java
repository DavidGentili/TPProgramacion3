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

	public static void main(String[] args) throws DomicilioInvalido {

		VentanaClinica ventana = new VentanaClinica();
		Controlador c = new Controlador(ventana, ventana, ventana, ventana, ventana);

	}

}
