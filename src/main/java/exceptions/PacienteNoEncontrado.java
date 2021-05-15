package exceptions;

/**
 * Es una Excepcion que se lanza cuando el paciente solicitado no se encuentra
 *
 */


public class PacienteNoEncontrado extends Exception {
	public PacienteNoEncontrado() {
		// TODO Auto-generated constructor stub
	}

	public PacienteNoEncontrado(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
