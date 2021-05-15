package exceptions;

/**
 * Es una Excepcion que se lanza cuando el medico que se quiere agregar ya se
 * encuentra en al clinica
 *
 */
public class MedicoYaAgregadoException extends Exception {

	public MedicoYaAgregadoException() {
	}

	public MedicoYaAgregadoException(String message) {
		super(message);
	}

}
