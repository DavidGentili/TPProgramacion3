package vista;

import java.awt.event.ActionListener;
import java.util.Iterator;

import medicos.IMedico;

public interface IVistaMedicos {

	void setActionListenerMedicos(ActionListener listener);

	void actualizaListaMedicos(Iterator<IMedico> lista);

	void mostrarMensajeError(String mensaje);

	String getNombreMedico();

	String getApellidoMedico();

	int getMatricula();

	int getDniMedico();

	String getCalleMedico();

	String getNumeroDeDomicilioMedico();

	String getCiudadMedico();

	String getTelefonoMedico();

	void limpiarCamposMedicos();

	String getEspecialidad();

	String getPosgrado();

	String getContratacion();

}
