package habitaciones;

import pacientes.Paciente;

public class SalaDeEsperaPrivada {
	private Paciente paciente=null;
	private static SalaDeEsperaPrivada instance=null;
	
	private SalaDeEsperaPrivada() {};
	
	public static SalaDeEsperaPrivada getInstance() {
		if(instance==null)
			instance=new SalaDeEsperaPrivada();
		return instance;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	
}
