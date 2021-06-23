package vista;

import java.util.Iterator;

import clinica.Ambulancia;
import pacientes.Paciente;

public interface IVistaAmbulancia {
	public void setAmbulancia(Ambulancia a);
	
	public void actualizaHistoricosAmbulancia(Iterator<Paciente> historicos);
	
	public Paciente getPacienteAmbulancia();

}
