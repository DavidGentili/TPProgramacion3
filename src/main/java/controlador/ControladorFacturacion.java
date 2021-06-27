package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import clinica.Clinica;
import exceptions.CantidadDeDiasErroneosException;
import exceptions.FechaInvalidaException;
import exceptions.MedicoNoEncontradoException;
import exceptions.PacienteNoAtendido;
import exceptions.PacienteNoEncontrado;
import exceptions.TipoDeHabitacionIncorrectaException;
import habitaciones.HabitacionCompartida;
import habitaciones.HabitacionPrivada;
import habitaciones.TerapiaIntensiva;
import pacientes.Paciente;
import vista.IVistaFacturacion;

public class ControladorFacturacion implements ActionListener {

	IVistaFacturacion ventanaFacturacion;
	Clinica clinica;

	public ControladorFacturacion(IVistaFacturacion ventanaFacturacion) {
		this.ventanaFacturacion = ventanaFacturacion;
		clinica = Clinica.getInstancia();

		this.ventanaFacturacion.setActionListenerFacturacion(this);
		this.ventanaFacturacion.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaFacturacion.actualizaListaPacientesEnAtencion(this.clinica.getIteratorEnAtencion());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Agregar Atencion Medica")
				|| e.getActionCommand().equalsIgnoreCase("Agregar Internacion")) {
			this.agregaPrestacion(e.getActionCommand());
		} else if (e.getActionCommand().equalsIgnoreCase("Facturar")) {
			this.RealizarFacturacion();
		}

		this.ventanaFacturacion.limpiarCamposFacturacion();

	}

	/**
	 * Se encarga de agregar una prestacion, y de gestionar que mensajes debe emitir
	 * la ventana en caso de que los valores sean incorrectos. El metodo solo
	 * analiza si el valor cantidad es numerico, los siguientes valores son
	 * analisados por los metodos correspondientes de la clase Clinica.
	 * 
	 * @param command El valor del ActionComand ingresado, utilizado para distinguir
	 *                el tipo de prestacion
	 */
	private void agregaPrestacion(String command) {
		try {
			int numero = Integer.parseInt(ventanaFacturacion.getCantidad());
			if (command.equalsIgnoreCase("Agregar Atencion Medica")) {
				this.clinica.agregaConsulta(ventanaFacturacion.getPaciente(), ventanaFacturacion.getMedicoFacturacion(),
						numero);
				this.ventanaFacturacion.mostrarCartelsatisfactorio("agregar una consulta");
			} else {
				agregarInternacion(this.ventanaFacturacion.getPaciente(),
						Integer.parseInt(this.ventanaFacturacion.getCantidad()));
				this.ventanaFacturacion.mostrarCartelsatisfactorio("agregar una internacion");
			}
		} catch (NumberFormatException e1) {
			this.ventanaFacturacion.mostrarMensajeError("el valor de cantidad debe ser numerico");
		} catch (CantidadDeDiasErroneosException | PacienteNoEncontrado | MedicoNoEncontradoException
				| TipoDeHabitacionIncorrectaException e) {
			this.ventanaFacturacion.mostrarMensajeError(e.getMessage());
		}
	}

	/**
	 * Se realiza la facturacion al paciente, se presupone utiliza para facturar la
	 * fecha del sistema al momento de intentar facturar. En caso de que la clase
	 * clinica propague una excepcion, se trasmite el mensaje a la ventana con un
	 * cartel de error
	 */
	private void RealizarFacturacion() {
		try {
			String factura = null;
			factura = this.clinica.facturaPaciente(this.ventanaFacturacion.getPaciente(), new GregorianCalendar());
			this.ventanaFacturacion.actualizaListaPacientesEnAtencion(this.clinica.getIteratorEnAtencion());
			this.ventanaFacturacion.mostrarFactura(factura);
		} catch (PacienteNoEncontrado | PacienteNoAtendido | FechaInvalidaException e) {
			this.ventanaFacturacion.mostrarMensajeError(e.getMessage());
		}
	}

	public void agregarInternacion(Paciente paciente, int cantidad)
			throws CantidadDeDiasErroneosException, PacienteNoEncontrado, TipoDeHabitacionIncorrectaException {
		String tipoHabitacion = this.ventanaFacturacion.getHabitacion();
		if (tipoHabitacion.equalsIgnoreCase("Habitacion Privada")) {
			clinica.agregaInternacion(paciente, new HabitacionPrivada(), cantidad);
		} else if (tipoHabitacion.equalsIgnoreCase("Habitacion Compartida")) {
			clinica.agregaInternacion(paciente, new HabitacionCompartida(), cantidad);
		} else if (tipoHabitacion.equalsIgnoreCase("Terapia Intensiva")) {
			clinica.agregaInternacion(paciente, new TerapiaIntensiva(), cantidad);
		} else
			throw new TipoDeHabitacionIncorrectaException("Debe seleccionar un tipo correcto de habitacion");
	}

}
