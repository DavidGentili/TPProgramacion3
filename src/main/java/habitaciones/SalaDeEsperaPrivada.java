package habitaciones;

import pacientes.Paciente;

public class SalaDeEsperaPrivada {
	private Paciente paciente;
	private static SalaDeEsperaPrivada instance=null;
	
	private SalaDeEsperaPrivada() {};
	
	public static SalaDeEsperaPrivada getInstance() {
		if(instance==null)
			instance=new SalaDeEsperaPrivada();
		return instance;
	}
	
}
