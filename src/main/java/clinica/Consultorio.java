package clinica;

import medicos.IMedico;
import pacientes.Paciente;

public class Consultorio {
	protected IMedico medico = null;
	protected Paciente paciente = null;
	protected int idConsultorio;

	public Consultorio(int id) {
		this.idConsultorio = id;
	}

	public IMedico getMedico() {
		return medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public int getIdConsultorio() {
		return idConsultorio;
	}

	public void setIdConsultorio(int idConsultorio) {
		this.idConsultorio = idConsultorio;
	}

	public void ingresaMedico(IMedico medico) {
		if (this.medico == null)
			this.medico = medico;
		// else exception;
	}

	public void egresaMedico() {
		this.medico = null;
	}

	public void IngresaPaciente(Paciente paciente) {
		if (this.paciente == null)
			this.paciente = paciente;
		// else exception;
	}
	
	public void egresaPaciente() {
		this.paciente = null;
	}

	@Override
	public String toString() {
		String respuesta = null;
		respuesta = "Consultorio: " + this.idConsultorio;
		if (this.medico != null) {
			respuesta += " Medico: " + this.medico.toString();
			if (paciente != null)
				respuesta += " Paciente: " + this.paciente.toString();
			else
				respuesta += " sin paciente";
		} else
			respuesta += " esta vacio";
		return respuesta;
	}

}
