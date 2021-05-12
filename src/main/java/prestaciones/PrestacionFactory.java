package prestaciones;

import clinica.Clinica;
import exceptions.ClinicaInexistenteExcepcion;

public class PrestacionFactory {
	
	private Clinica c;
	
	public PrestacionFactory() {
		try {
			this.c= Clinica.getInstancia();
		} catch (ClinicaInexistenteExcepcion e) {
			
		};
	}
	
	 
	
	public Internacion getInternacion(String tipo, int cantidad) {
		Internacion respueta=null;
		
		return respuesta;
	}
	
	public Internacion getConsulta(String nombreMedico, int cantidad) {
		Internacion respueta=null;
		
		return respuesta;
	}

}
