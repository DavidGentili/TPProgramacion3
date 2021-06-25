package vista;

import java.awt.event.ActionListener;
import java.util.Iterator;

import pacientes.Paciente;

public interface IVistaPacientes {
	void SetActionListenerPacientes(ActionListener listener);

	void actualizaListaEnAtencion(Iterator<Paciente> lista);

	void actualizaColaDeEspera(Iterator<Paciente> lista);

	void actualizaPacientesHistoricos(Iterator<Paciente> lista);

	void actualizaSalaPrivada(String paciente);

	void mostrarMensajeError(String mensaje);

	String getNombrePaciente();

	String getApellidoPaciente();

	int getDniPaciente();

	String getNroDeHistoriaClinicaPaciente();

	String getCallePaciente();

	String getNroDeCallePaciente();

	String getCiudadPaciente();

	String getTelefonoPaciente();

	String getRangoEtareo();

	Paciente getPacienteSeleccionadoEnPaciente();

	void LimpiarCamposPaciente();
}
