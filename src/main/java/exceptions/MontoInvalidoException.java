package exceptions;

/**
 * Es una Excepcion que se lanza cuando el monto que se quiere ingresar es
 * negativo
 *
 */
public class MontoInvalidoException extends Exception {

	public MontoInvalidoException() {
		// TODO Auto-generated constructor stub
	}

	public MontoInvalidoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
