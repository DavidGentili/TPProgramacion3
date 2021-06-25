package vista;

import java.awt.event.ActionListener;
import java.util.Iterator;

import pacientes.Paciente;

public interface IVistaAmbulancia {
	
	public void actualizaHistoricosAmbulancia(Iterator<Paciente> historicos);
	
	public void actualizaEstadoAmbulancia(String estado);
	
	public Paciente getPacienteAmbulancia();
	
	public void setActionListener(ActionListener a);

	public String getNombreAsociado();
	
	public String getApellidoAsociado();
	
	public String getTelefonoAsociado();
	
	public String getCalleDomicilioAsocidado();
	
	public String getNumeroDomicilioAsociado();
	
	public String getDNIAsociado();
	
	public void mostrarCartelsatisfactorio(String mensaje);
	
	public void mostrarMensajeError(String mensaje);
}
