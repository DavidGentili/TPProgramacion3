package vista;

import java.awt.event.ActionListener;
import java.util.Iterator;

import medicos.IMedico;
import pacientes.Paciente;

public interface IVistaFacturacion {
	void actualizaListaMedicos(Iterator<IMedico> lista);

	void actualizaListaPacientesEnAtencion(Iterator<Paciente> lista);

	void mostrarMensajeError(String mensaje);

	void mostrarCartelsatisfactorio(String mensaje);

	void limpiarCamposFacturacion();

	String getCantidad();

	Paciente getPaciente();

	IMedico getMedicoFacturacion();

	String getHabitacion();

	void mostrarFactura(String factura);

	void setActionListenerFacturacion(ActionListener listener);
}
