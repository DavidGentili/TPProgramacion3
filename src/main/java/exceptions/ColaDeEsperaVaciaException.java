package exceptions;

/**
 * se emite cuando se quiere atender a un paciente, pero la cola de espera esta
 * vacia
 *
 */
public class ColaDeEsperaVaciaException extends Exception {

	/**
	 * 
	 */
	public ColaDeEsperaVaciaException() {
		// TODO Auto-generated constructor stub
	}

	public ColaDeEsperaVaciaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
