package vista;

import java.awt.event.ActionListener;
import java.util.Iterator;

import asociado.Asociado;
import pacientes.Paciente;

public interface IVistaAmbulancia {
	
	public void actualizaAsociados(Iterator<Asociado> historicos);
	
	public void actualizaEstadoAmbulancia(String estado);
	
	public Asociado getAsociadoAmbulancia();
	
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
