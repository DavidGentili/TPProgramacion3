package prestaciones;

import clinica.Clinica;
import exceptions.ClinicaInexistenteExcepcion;
import habitaciones.Habitacion;
import habitaciones.HabitacionCompartida;
import habitaciones.HabitacionPrivada;
import habitaciones.TerapiaIntensiva;
import medicos.IMedico;

public class PrestacionFactory {
	
	private static Clinica c;
	
//	public PrestacionFactory() {
//		try {
//			this.c= Clinica.getInstancia();
//		} catch (ClinicaInexistenteExcepcion e) {
//			
//		};
//	}
	
	 
	/**
	 * Pre: supone que segun el tipo que le mandaron, hay lugar disponible
	 * @param tipo
	 * @param cantidad
	 * @return
	 */
	public static Internacion getInternacion(String tipo, int cantidad) {
		Internacion respuesta=null;
		Habitacion hab=null;
		if(tipo.equalsIgnoreCase("intensiva"))
			hab = TerapiaIntensiva.getInstance();
		else if(tipo.equalsIgnoreCase("compartida"))
			hab = HabitacionCompartida.getInstance();
		else if(tipo.equalsIgnoreCase("privada"))
			hab = HabitacionPrivada.getInstance();
		return new Internacion(cantidad, hab);
	}
	
	public static Consulta getConsulta(IMedico medico, int cantidad) {
		return new Consulta(cantidad, medico);
	}

}
