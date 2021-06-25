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

	String getNombrePaciente();

	String getApellidoPaciente();

	int getDniPaciente();

	int getNroDeHistoriaClinicaPaciente();

	String getCallePaciente();

	String getNroDeCallePaciente();

	String getCiudadPaciente();

	String getTelefonoPaciente();

	String getRangoEtareo();

	void LimpiarCamposPaciente();
}
