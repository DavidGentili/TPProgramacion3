package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.GregorianCalendar;

import clinica.Clinica;
import exceptions.CantidadDeDiasErroneosException;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.DomicilioInvalido;
import exceptions.EspecialidadNoIndicadaException;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.FechaInvalidaException;
import exceptions.MedicoNoEncontradoException;
import exceptions.MedicoYaAgregadoException;
import exceptions.PacienteNoAtendido;
import exceptions.PacienteNoEncontrado;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDeHabitacionIncorrectaException;
import persistencia.PersistirClinica;
import personas.Domicilio;
import vista.IVistaAmbulancia;
import vista.IVistaFacturacion;
import vista.IVistaMedicos;

public class Controlador implements ActionListener, WindowListener {
	IVistaFacturacion ventanaFacturacion;
	IVistaMedicos ventanaMedicos;
	IVistaAmbulancia ventanaAmbulancia;
	Clinica clinica;

	public Controlador(IVistaFacturacion ventanaFacturacion, IVistaMedicos ventanaMedicos,
			IVistaAmbulancia ventanaAmbulancia) {
		PersistirClinica.restaurarClinica();
		this.ventanaFacturacion = ventanaFacturacion;
		this.ventanaMedicos = ventanaMedicos;
		this.clinica = Clinica.getInstancia();
		this.ventanaAmbulancia = ventanaAmbulancia;
		this.ventanaFacturacion.setActionListenerFacturacion(this);
		this.ventanaMedicos.setActionListenerMedicos(this);
		this.ventanaFacturacion.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaFacturacion.actualizaListaPacientesEnAtencion(this.clinica.getIteratorEnAtencion());
		this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaAmbulancia.actualizaHistoricosAmbulancia(this.clinica.getIteratorPacientesHistoricos());

	}

	@Override
	public void actionPerformed(ActionEvent e) { // cuando hagamos el modulo de agregar pacientes, llamar al actualiza
													// Lista pacientes del la pestaña ambulancia
		if (e.getActionCommand().equalsIgnoreCase("Agregar Atencion Medica")
				|| e.getActionCommand().equalsIgnoreCase("Agregar Internacion")) {
			this.agregaPrestacion(e.getActionCommand());
		}
		if (e.getActionCommand().equalsIgnoreCase("Facturar")) {
			this.RealizarFacturacion();
		}
		if (e.getActionCommand().equalsIgnoreCase("Agregar Medico")) {
			this.agregaMedico();
			this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		}
		if (e.getActionCommand().equalsIgnoreCase("Llama Translado")) {

		}
		this.ventanaFacturacion.limpiarCamposFacturacion();
		this.ventanaMedicos.limpiarCamposMedicos();
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
				this.clinica.agregaInternacion(this.ventanaFacturacion.getPaciente(),
						this.ventanaFacturacion.getHabitacion(), numero);
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

	private void agregaMedico() {

		try {
			if (this.datosSecundariosCorrectos(ventanaMedicos.getTelefonoMedico(), ventanaMedicos.getCiudadMedico())) {
				Domicilio aux = new Domicilio(ventanaMedicos.getCalleMedico(),
						Integer.parseInt(ventanaMedicos.getNumeroDeDomicilioMedico()));
				this.clinica.agregaMedico(ventanaMedicos.getNombreMedico(), ventanaMedicos.getApellidoMedico(),
						ventanaMedicos.getDniMedico(), ventanaMedicos.getTelefonoMedico(), aux,
						ventanaMedicos.getCiudadMedico(), ventanaMedicos.getMatricula(),
						ventanaMedicos.getEspecialidad(), ventanaMedicos.getPosgrado(),
						ventanaMedicos.getContratacion());

			} else
				this.clinica.agregaMedico(ventanaMedicos.getNombreMedico(), ventanaMedicos.getApellidoMedico(),
						ventanaMedicos.getDniMedico(), ventanaMedicos.getMatricula(), ventanaMedicos.getEspecialidad(),
						ventanaMedicos.getPosgrado(), ventanaMedicos.getContratacion());

		} catch (MedicoYaAgregadoException | ContratacionNoIndicadaExceptions | ContratacionNoRegistradaExceptions
				| EspecialidadNoRegistradaExceptions | PosgradoNoRegistradoExceptions | EspecialidadNoIndicadaException
				| DomicilioInvalido e) {
			ventanaMedicos.mostrarMensajeError(e.getMessage());
		}
	}

	private boolean datosSecundariosCorrectos(String telefono, String ciudad) {
		int numeroDeTelefono;
		try {
			numeroDeTelefono = Integer.parseInt(telefono);
			if (numeroDeTelefono > 0) {

				if (ciudad != null && !ciudad.isBlank() && !ciudad.isEmpty()) {
					return true;
				} else
					return false;

			} else
				return false;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		/*try {
			PersistirClinica.almacenarClinica();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}*/
		System.out.println("Closing");

	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Closed");

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
