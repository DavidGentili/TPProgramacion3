package exceptions;

/**
 * Es una Excepcion que se lanza cuando se quiere obtener la instancia de la
 * clinica sin antes haberla instanciado
 *
 */
public class ClinicaInexistenteExcepcion extends Exception {

	public ClinicaInexistenteExcepcion() {
	}

	public ClinicaInexistenteExcepcion(String message) {
		super(message);
	}

}
