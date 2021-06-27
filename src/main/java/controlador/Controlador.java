package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import ambulancia.Ambulancia;
import asociado.Asociado;
import clinica.Clinica;
import exceptions.AsociadoNoEncontrado;
import exceptions.AsociadoYaExistente;
import exceptions.CantidadDeDiasErroneosException;
import exceptions.ColaDeEsperaVaciaException;
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
import exceptions.PacienteYaExistenteException;
import exceptions.PacienteYaIngresadoException;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDeHabitacionIncorrectaException;
import exceptions.TipoDePacienteIncorrectoException;
import pacientes.Paciente;
import pedidos.Temporizador;
import persistencia.PersistirClinica;
import personas.Domicilio;
import vista.IVistaAmbulancia;
import vista.IVistaConfiguraciones;
import vista.IVistaFacturacion;
import vista.IVistaMedicos;
import vista.IVistaPacientes;

public class Controlador implements ActionListener, WindowListener, Observer {
	IVistaFacturacion ventanaFacturacion;
	IVistaMedicos ventanaMedicos;
	IVistaAmbulancia ventanaAmbulancia;
	IVistaPacientes ventanaPacientes;
	IVistaConfiguraciones ventanaConfiguraciones;
	Clinica clinica;

	public Controlador(IVistaFacturacion ventanaFacturacion, IVistaMedicos ventanaMedicos,
			IVistaAmbulancia ventanaAmbulancia, IVistaPacientes ventanaPacientes,
			IVistaConfiguraciones ventanaConfiguraciones) {
		PersistirClinica.restaurarClinica();
		this.ventanaFacturacion = ventanaFacturacion;
		this.ventanaMedicos = ventanaMedicos;
		this.ventanaAmbulancia = ventanaAmbulancia;
		this.ventanaConfiguraciones = ventanaConfiguraciones;
		this.ventanaPacientes = ventanaPacientes;
		this.clinica = Clinica.getInstancia();

		Ambulancia.getInstance().addObserver(this);
		this.ventanaPacientes.SetActionListenerPacientes(this);
		this.ventanaFacturacion.setActionListenerFacturacion(this);
		this.ventanaMedicos.setActionListenerMedicos(this);
		this.ventanaAmbulancia.setActionListenerAmbulancia(this);
		this.ventanaFacturacion.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaFacturacion.actualizaListaPacientesEnAtencion(this.clinica.getIteratorEnAtencion());
		this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.ventanaAmbulancia.actualizaAsociados(this.clinica.getIteratorAsociados());
		this.ventanaConfiguraciones.setActionListenerConfiguraciones(this);
		this.ventanaConfiguraciones.SetWindowListenerConfiguraciones(this);
//		 this.ventanaAmbulancia.actualizaEstadoAmbulancia(this.clinica.getA().informaEstado());
		clinica.iniciarSimulacionAmbulancia();
		actualizarDatosConfiguracion();
		actualizarValoresConfiguracion();
		this.actualizaVentanaPacientes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equalsIgnoreCase("Agregar Atencion Medica")
				|| e.getActionCommand().equalsIgnoreCase("Agregar Internacion")) {
			this.agregaPrestacion(e.getActionCommand());
		}
		if (e.getActionCommand().equalsIgnoreCase("Facturar")) {
			this.RealizarFacturacion();
		}
		if (e.getActionCommand().equalsIgnoreCase("Agregar Medico")) {
			try {
				this.agregaMedico();
			} catch (MedicoYaAgregadoException | ContratacionNoRegistradaExceptions | EspecialidadNoRegistradaExceptions
					| PosgradoNoRegistradoExceptions | EspecialidadNoIndicadaException e1) {
				ventanaMedicos.mostrarMensajeError(e1.getMessage());
			}
			this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		}
		if (e.getActionCommand().equalsIgnoreCase("Llama Translado")) {
			Asociado asociado = this.ventanaAmbulancia.getAsociadoAmbulancia();
			if (asociado != null) {
				try {
					clinica.realizarPedidoTraslado(asociado);
				} catch (AsociadoNoEncontrado e1) {
					this.ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
				}
			} else
				this.ventanaAmbulancia.mostrarMensajeError("Debe seleccionar un Asociado para realizar esta accion");
		}

		if (e.getActionCommand().equalsIgnoreCase("Solicitar Reparacion")) {
			this.clinica.realizarPedidoDeReparacion();
		}

		if (e.getActionCommand().equalsIgnoreCase("Llama Atencion")) {
			Asociado asociado = this.ventanaAmbulancia.getAsociadoAmbulancia();
			if (asociado != null) {
				try {
					clinica.realizarPedidoAtencion(asociado);
				} catch (AsociadoNoEncontrado e1) {
					this.ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
				}
			} else
				this.ventanaAmbulancia.mostrarMensajeError("Debe seleccionar un Asociado para realizar esta accion");
		}

		if (e.getActionCommand().equalsIgnoreCase("Agregar Asociado")) {
			this.agregaAsociado();
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

		if (e.getActionCommand().equalsIgnoreCase("Ingresa Paciente")) {
			this.ingresarPaciente();
			this.actualizaVentanaPacientes();
		}

		if (e.getActionCommand().equalsIgnoreCase("Agregar Paciente")) {
			try {
				this.agregaPaciente();
				this.actualizaVentanaPacientes();
			} catch (PacienteYaExistenteException | TipoDePacienteIncorrectoException | PacienteNoEncontrado
					| PacienteYaIngresadoException e1) {
				ventanaPacientes.mostrarMensajeError(e1.getMessage());
			}
		}

		if (e.getActionCommand().equalsIgnoreCase("Atender Siguiente")) {
			try {
				clinica.atiendeSiguiente();
			} catch (ColaDeEsperaVaciaException e1) {
				ventanaPacientes.mostrarMensajeError(e1.getMessage());
			}

			this.actualizaVentanaPacientes();
		}

		if (e.getActionCommand().equalsIgnoreCase("Eliminar Asociado")) {
			if (ventanaAmbulancia.getAsociadoAmbulancia() != null) {
				try {
					clinica.eliminaAsociado(ventanaAmbulancia.getAsociadoAmbulancia());
					ventanaAmbulancia.actualizaAsociados(clinica.getIteratorAsociados());
				} catch (AsociadoNoEncontrado e1) {
					ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
				}
			} else
				ventanaAmbulancia.mostrarMensajeError("Debe seleccionar un asociado a elminar");
		}

		this.ventanaFacturacion.limpiarCamposFacturacion();
		this.ventanaMedicos.limpiarCamposMedicos();
	}

	private boolean chequeaString(String campo) {
		return (campo.isBlank() || campo.isEmpty() || campo == null) ? false : true;
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
	 * IVentanaMedico y agrega el medico con los datos que tenga<br>
	 * Pre: El nombre debe ser distinto de vacio y de nulo <br>
	 * el apellido debe ser distinto de vacio y de nulo <br>
	 * El Dni debe ser un entero positivo <br>
	 * La matricula debe ser un entero positivo
	 * 
	 * @throws EspecialidadNoIndicadaException    Si la especialidad no esta
	 *                                            indicada
	 * @throws PosgradoNoRegistradoExceptions     Si el posgrado no esta registrado
	 * @throws EspecialidadNoRegistradaExceptions Si la especialidad no esta
	 *                                            registrada
	 * @throws ContratacionNoRegistradaExceptions Si la contratacion no esta
	 *                                            registrada
	 * @throws MedicoYaAgregadoException          si el medico ya esta agregado
	 */
	private void agregaMedico() throws MedicoYaAgregadoException, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions, EspecialidadNoIndicadaException {
		String nombre = ventanaMedicos.getNombreMedico();
		String apellido = ventanaMedicos.getApellidoMedico();
		int dni = ventanaMedicos.getDniMedico();
		String telefono = ventanaMedicos.getTelefonoMedico();
		String ciudad = ventanaMedicos.getCiudadMedico();
		int matricula = ventanaMedicos.getMatricula();
		String especialidad = ventanaMedicos.getEspecialidad();
		String posgrado = ventanaMedicos.getPosgrado();
		String contratacion = ventanaMedicos.getContratacion();
		try {
			Domicilio aux = new Domicilio(ventanaMedicos.getCalleMedico(),
					Integer.parseInt(ventanaMedicos.getNumeroDeDomicilioMedico()));
			if (this.datosSecundariosCorrectos(telefono, ciudad))
				this.clinica.agregaMedico(nombre, apellido, dni, telefono, aux, ciudad, matricula, especialidad,
						posgrado, contratacion);
			else
				this.clinica.agregaMedico(nombre, apellido, dni, matricula, especialidad, posgrado, contratacion);
		} catch (NumberFormatException | DomicilioInvalido e) {
			this.clinica.agregaMedico(nombre, apellido, dni, matricula, especialidad, posgrado, contratacion);
		}
	}

	/**
	 * Analiza si los datos secundarios de una persona estan en condiciones para el
	 * modelo
	 * 
	 * @param telefono Telefono de la persona
	 * @param ciudad   Ciudad de la persona
	 * @return si los datos son coherentes con su contexto
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

	/**
	 * Se encarga de ingresar un paciente a la clinica, primero usa el seleccionado
	 * por lista, si ninguno esta seleccionado por lista, utilza el numero de
	 * historia clinica ingresado. Si no logra por ninguno de los dos medios obtener
	 * la informacion retorna un mensaje de error
	 */
	private void ingresarPaciente() {
		Paciente paciente = this.ventanaPacientes.getPacienteSeleccionadoEnPaciente();
		if (paciente != null) {
			try {
				this.clinica.ingresaPaciente(paciente);
			} catch (PacienteNoEncontrado | PacienteYaIngresadoException e) {
				this.ventanaPacientes.mostrarMensajeError(e.getMessage());
			}
		} else {
			try {
				int historiaClinica = Integer.parseInt(this.ventanaPacientes.getNroDeHistoriaClinicaPaciente());
				if (historiaClinica > 0)
					try {
						this.clinica.ingresaPaciente(historiaClinica);
					} catch (PacienteNoEncontrado | PacienteYaIngresadoException e) {
						this.ventanaPacientes.mostrarMensajeError(e.getMessage());
					}
				else
					this.ventanaPacientes.mostrarMensajeError("El numero de historia Clinica debe ser positivo");
			} catch (NumberFormatException e) {
				this.ventanaPacientes.mostrarMensajeError(
						"Debe seleccionar un paciente del historial o ingresar un numero de historia clinica valido");
			}
		}
	}

	public void agregaPaciente() throws PacienteYaExistenteException, TipoDePacienteIncorrectoException,
			PacienteNoEncontrado, PacienteYaIngresadoException {
		String nombre = ventanaPacientes.getNombrePaciente();
		String apellido = ventanaPacientes.getApellidoPaciente();
		int dni = ventanaPacientes.getDniPaciente();
		String telefono = ventanaPacientes.getTelefonoPaciente();
		String ciudad = ventanaPacientes.getCiudadPaciente();
		String rangoEtareo = ventanaPacientes.getRangoEtareo();

		try {
			int nroHistClin = Integer.parseInt(ventanaPacientes.getNroDeHistoriaClinicaPaciente());
			try {
				Domicilio aux = new Domicilio(ventanaPacientes.getCallePaciente(),
						Integer.parseInt(ventanaPacientes.getNroDeCallePaciente()));
				if (this.datosSecundariosCorrectos(telefono, ciudad))
					clinica.agregaPaciente(nombre, apellido, dni, telefono, aux, ciudad, nroHistClin, rangoEtareo);
				else
					clinica.agregaPaciente(nombre, apellido, dni, nroHistClin, rangoEtareo);
			} catch (NumberFormatException | DomicilioInvalido e1) {
				clinica.agregaPaciente(nombre, apellido, dni, nroHistClin, rangoEtareo);
			}
		} catch (NumberFormatException e) {
			ventanaPacientes.mostrarMensajeError("El numero de historia clinica debe ser un entero positivo");
		}

	}

	private void actualizaVentanaPacientes() {
		this.ventanaPacientes.actualizaColaDeEspera(clinica.getIteratorColaDeEspera());
		this.ventanaPacientes.actualizaListaEnAtencion(clinica.getIteratorEnAtencion());
		this.ventanaPacientes.actualizaPacientesHistoricos(clinica.getIteratorPacientesHistoricos());
		if (clinica.getSalaPrivada() != null)
			this.ventanaPacientes.actualizaSalaPrivada(clinica.getSalaPrivada().toString());
		else
			this.ventanaPacientes.actualizaSalaPrivada("");
		this.ventanaPacientes.LimpiarCamposPaciente();
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

		this.clinica.terminaSimulacionAmbulancia();
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

	public void agregaAsociado() {
		int nroCalle, dni;
		String telefono, nombre, apellido, calle;

		try {
			nombre = this.ventanaAmbulancia.getNombreAsociado();
			apellido = this.ventanaAmbulancia.getApellidoAsociado();
			telefono = this.ventanaAmbulancia.getTelefonoAsociado();
			calle = this.ventanaAmbulancia.getCalleDomicilioAsocidado();
			nroCalle = Integer.parseInt(this.ventanaAmbulancia.getNumeroDomicilioAsociado());
			dni = Integer.parseInt(this.ventanaAmbulancia.getDNIAsociado());
			if (this.chequeaString(nombre))
				if (this.chequeaString(apellido))
					if (this.chequeaString(calle)) {
						//this.ventanaAmbulancia.mostrarCartelsatisfactorio("El registro del asociado");
						Domicilio aux = new Domicilio(calle, nroCalle);
						this.clinica.agregaAsociado(nombre, apellido, dni, aux, telefono);
						this.ventanaAmbulancia.actualizaAsociados(this.clinica.getIteratorAsociados());
					} else
						this.ventanaAmbulancia.mostrarMensajeError("Error en el campo Calle");
				else
					this.ventanaAmbulancia.mostrarMensajeError("Error en el campo Apellido");
			else
				this.ventanaAmbulancia.mostrarMensajeError("Error en el campo nombre");

		} catch (NumberFormatException ex) {
			this.ventanaAmbulancia.mostrarMensajeError("Formato de los numeros incorrectos");
		} catch (DomicilioInvalido e1) {
			this.ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
		} catch (AsociadoYaExistente e2) {
			this.ventanaAmbulancia.mostrarMensajeError(e2.getMessage());
		}
	}

}
