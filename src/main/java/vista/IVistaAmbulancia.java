package vista;

import java.awt.event.ActionListener;
import java.util.Iterator;

import pacientes.Paciente;

public interface IVistaAmbulancia {
	
	public void actualizaHistoricosAmbulancia(Iterator<Paciente> historicos);
	
	public void actualizaEstadoAmbulancia(String estado);
	
	public Paciente getPacienteAmbulancia();
	
	public void setActionListener(ActionListener a);

}
