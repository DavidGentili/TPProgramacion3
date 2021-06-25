package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

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
import exceptions.MontoInvalidoException;
import exceptions.PacienteNoAtendido;
import exceptions.PacienteNoEncontrado;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDeHabitacionIncorrectaException;
import pacientes.Paciente;
import pedidos.Pedido;
import pedidos.PedidoTranslado;
import persistencia.PersistirClinica;
import personas.Domicilio;
import vista.IVistaAmbulancia;
import vista.IVistaConfiguraciones;
import vista.IVistaFacturacion;
import vista.IVistaMedicos;

public class Controlador implements ActionListener, WindowListener, Observer {
	IVistaFacturacion ventanaFacturacion;
	IVistaMedicos ventanaMedicos;
	IVistaAmbulancia ventanaAmbulancia;
	IVistaConfiguraciones ventanaConfiguraciones;
	Clinica clinica;

	public Controlador(IVistaFacturacion ventanaFacturacion, IVistaMedicos ventanaMedicos,
			IVistaAmbulancia ventanaAmbulancia, IVistaConfiguraciones ventanaConfiguraciones) {
		PersistirClinica.restaurarClinica();
		this.ventanaFacturacion = ventanaFacturacion;
		this.ventanaMedicos = ventanaMedicos;
		this.ventanaConfiguraciones = ventanaConfiguraciones;
		this.clinica = Clinica.getInstancia();
		this.ventanaAmbulancia = ventanaAmbulancia;
		this.ventanaFacturacion.setActionListenerFacturacion(this);
		this.ventanaMedicos.setActionListenerMedicos(this);
		this.ventanaAmbulancia.setActionListener(this);
		this.ventanaFacturacion.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaFacturacion.actualizaListaPacientesEnAtencion(this.clinica.getIteratorEnAtencion());
		this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaAmbulancia.actualizaHistoricosAmbulancia(this.clinica.getIteratorPacientesHistoricos());
		this.ventanaConfiguraciones.setActionListenerConfiguraciones(this);
		this.ventanaConfiguraciones.SetWindowListenerConfiguraciones(this);
		this.ventanaAmbulancia.actualizaEstadoAmbulancia(this.clinica.getA().informaEstado());
		actualizarDatosConfiguracion();
		actualizarValoresConfiguracion();
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
		
		if (e.getActionCommand().equalsIgnoreCase("Llama Atencion")) {
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Agregar Asociado")) {
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Solicitar Reparacion")) {
			Paciente p = this.ventanaAmbulancia.getPacienteAmbulancia();
		}
		
		if (e.getActionCommand().equalsIgnoreCase("Restaurar Clinica")) {
			PersistirClinica.restaurarClinica();
			this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		}

		if (e.getActionCommand().equalsIgnoreCase("Almacenar Clinica")) {
			try {
				PersistirClinica.almacenarClinica();
			} catch (IOException e1) {
				this.ventanaConfiguraciones.mostrarMensajeError("No se pudo almacenar la clinica correctamente");
			}
		}

		if (e.getActionCommand().equalsIgnoreCase("Actualizar datos")) {
			actualizarDatosClinica();
		}

		if (e.getActionCommand().equalsIgnoreCase("Actualizar valores")) {
			actualizarValoresClinica();
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

	/**
	 * Se encarga de solicitarle los datos a la ventana mediante la interface
	 * IVentanaMedico <br>
	 * Pre: El nombre debe ser distinto de vacio y de nulo <br>
	 * el apellido debe ser distinto de vacio y de nulo <br>
	 * El Dni debe ser un entero positivo <br>
	 * La matricula debe ser un entero positivo
	 */
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

	/**
	 * Analiza si los datos secundarios de una persona estan en condiciones para el
	 * modelo
	 * 
	 * @param telefono Telefono de la persona
	 * @param ciudad   Ciudad de la persona
	 * @return true si los datos son coherentes con su contexto
	 */
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

	/**
	 * Se encarga de gestionar y enviar los datos de la clinica a actualizar en la
	 * ventana
	 */
	private void actualizarDatosConfiguracion() {
		String nombre, telefono, direccion, ciudad;
		nombre = telefono = direccion = ciudad = "";
		if (this.clinica.getNombre() != null)
			nombre = this.clinica.getNombre();
		if (this.clinica.getTelefono() != null)
			telefono = this.clinica.getTelefono();
		if (this.clinica.getDireccion() != null)
			direccion = this.clinica.getDireccion().getCalle() + " " + this.clinica.getDireccion().getNumero();
		if (this.clinica.getCiudad() != null)
			ciudad = this.clinica.getCiudad();
		this.ventanaConfiguraciones.actualizarDatosDeLaClinia(nombre, telefono, direccion, ciudad);
	}

	/**
	 * Se encarga de enviar los valores a actualizar en la ventana, solo actualiza
	 * aquellos valores correctos, los que se haya ingresado de forma erronea se
	 * mantien su valor original
	 */
	private void actualizarValoresConfiguracion() {
		this.ventanaConfiguraciones.actualizarCostosDeLaClinica(Clinica.getCostoHabitacionPrivada(),
				Clinica.getCostoHabitacionCompartida(), Clinica.getCostoTerapiaIntensiva(),
				Clinica.getSueldoBasicoMedico());
	}

	/**
	 * Se encarga de cargar los datos ingresados por la vista en el modelo
	 */
	private void actualizarDatosClinica() {
		String nombre, telefono, direccion, ciudad;
		nombre = this.ventanaConfiguraciones.getNuevoNombreClinica();
		if (!nombre.isBlank() && !nombre.isEmpty())
			Clinica.getInstancia().setNombre(nombre);
		telefono = this.ventanaConfiguraciones.getNuevoTelefonoClinica();
		try {
			int numeroDeTelefono = Integer.parseInt(telefono);
			if (numeroDeTelefono > 0)
				Clinica.getInstancia().setTelefono(telefono);
		} catch (NumberFormatException e) {
		}

		direccion = this.ventanaConfiguraciones.getNuevaCalleClinica();
		try {
			Domicilio nuevoDomicilio = new Domicilio(this.ventanaConfiguraciones.getNuevaCalleClinica(),
					Integer.parseInt(this.ventanaConfiguraciones.getNuevoNumeroClinica()));
			Clinica.getInstancia().setDireccion(nuevoDomicilio);
		} catch (NumberFormatException | DomicilioInvalido e) {

		}

		ciudad = this.ventanaConfiguraciones.getCiudadClinica();
		if (!ciudad.isBlank() && !ciudad.isEmpty())
			Clinica.getInstancia().setCiudad(ciudad);

		this.actualizarDatosConfiguracion();
		this.ventanaConfiguraciones.limpiarCamposConfiguracion();

	}

	/**
	 * Se encarga de cargar los valores ingresados mediante la vista en el modelo,
	 * solo actualiza aquellos valores correctos, los que se haya ingresado de forma
	 * erronea se mantien su valor original
	 */
	private void actualizarValoresClinica() {
		double habPrivada, habCompartida, terapia, sueldo;
		try {
			Clinica.setCostoHabitacionPrivada(
					retornaDouble(this.ventanaConfiguraciones.getNuevoCostoHabitacionPrivada()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		try {
			Clinica.setCostoHabitacionCompartida(
					retornaDouble(this.ventanaConfiguraciones.getNuevoCostoHabitacionCompartida()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		try {
			Clinica.setCostoTerapiaIntensiva(
					retornaDouble(this.ventanaConfiguraciones.getNuevoCostoTerapiaIntensiva()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		try {
			Clinica.setSueldoBasicoMedico(retornaDouble(this.ventanaConfiguraciones.getNuevoSueldoBasicoMedicos()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		this.actualizarValoresConfiguracion();
		this.ventanaConfiguraciones.limpiarCamposConfiguracion();

	}

	/**
	 * A partir de un string retorna su representado en Double, si no es corresponde
	 * a un valor numerico emite una excepcion
	 * 
	 * @param numeroString Un String que representa un Double
	 * @return El numero Double
	 * @throws NumberFormatException Si el String no representa un numero real
	 */
	private double retornaDouble(String numeroString) throws NumberFormatException {
		double numero;
		if (!numeroString.isBlank() && !numeroString.isEmpty()) {
			numero = Double.parseDouble(numeroString);
			return numero;
		} else
			throw new NumberFormatException();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {

		try {
			PersistirClinica.almacenarClinica();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {

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

	@Override
	public void update(Observable o, Object arg) {
		this.ventanaAmbulancia.actualizaEstadoAmbulancia((String) arg);
		
	}

}
