package exceptions;

/**
 * Es una Excepcion que se lanza cuando se quiere facturar a un paciente no
 * atendido
 *
 */
public class PacienteNoAtendido extends Exception {

	public PacienteNoAtendido() {
		// TODO Auto-generated constructor stub
	}

	public PacienteNoAtendido(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
