package exceptions;

/**
 * Es una Excepcion que se lanza cuando el tipo de rango etareo del paciente
 * ingresado es incorrecto
 *
 */

public class TipoDePacienteIncorrectoException extends Exception {

	public TipoDePacienteIncorrectoException() {
		// TODO Auto-generated constructor stub
	}

	public TipoDePacienteIncorrectoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
