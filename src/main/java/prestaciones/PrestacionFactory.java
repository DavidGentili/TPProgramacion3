package prestaciones;

import clinica.Clinica;
import exceptions.ClinicaInexistenteExcepcion;
import habitaciones.Habitacion;
import medicos.IMedico;

public class PrestacionFactory {
	
	private static Clinica c;
	
	public PrestacionFactory() {
		try {
			this.c= Clinica.getInstancia();
		} catch (ClinicaInexistenteExcepcion e) {
			
		};
	}
	
	 
	/**
	 * Pre: supone que segun el tipo que le mandaron, hay lugar disponible
	 * @param tipo
	 * @param cantidad
	 * @return
	 */
	public static Internacion getInternacion(String tipo, int cantidad, Habitacion hab) {
		Internacion respuesta=null;
		
		return respuesta;
	}
	
	public static Consulta getConsulta(IMedico medico, int cantidad) {
		return new Consulta(cantidad, medico);
	}

}
