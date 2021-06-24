package vista;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public interface IVistaConfiguraciones {

	void setActionListenerConfiguraciones(ActionListener listener);

	void SetWindowListenerConfiguraciones(WindowListener listener);

	void mostrarMensajeError(String mensaje);

	void actualizarCostosDeLaClinica(double habPrivada, double habCompartida, double terapia, double sueldo);

	void actualizarDatosDeLaClinia(String nombre, String telefono, String direccion, String ciudad);

	String getNuevoNombreClinica();

	String getNuevoTelefonoClinica();

	String getNuevaCalleClinica();

	String getNuevoNumeroClinica();

	String getCiudadClinica();

	String getNuevoCostoHabitacionPrivada();

	String getNuevoCostoHabitacionCompartida();

	String getNuevoCostoTerapiaIntensiva();

	String getNuevoSueldoBasicoMedicos();

	void limpiarCamposConfiguracion();

}
