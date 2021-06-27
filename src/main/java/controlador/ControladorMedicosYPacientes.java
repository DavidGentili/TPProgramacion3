package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clinica.Clinica;
import exceptions.ColaDeEsperaVaciaException;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.DomicilioInvalido;
import exceptions.EspecialidadNoIndicadaException;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.PacienteNoEncontrado;
import exceptions.PacienteYaExistenteException;
import exceptions.PacienteYaIngresadoException;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDePacienteIncorrectoException;
import pacientes.Paciente;
import personas.Domicilio;
import vista.IVistaMedicos;
import vista.IVistaPacientes;

public class ControladorMedicosYPacientes implements ActionListener {

	IVistaPacientes ventanaPacientes;
	IVistaMedicos ventanaMedicos;
	Clinica clinica;

	public ControladorMedicosYPacientes(IVistaPacientes ventanaPacientes, IVistaMedicos ventanaMedicos) {
		this.ventanaMedicos = ventanaMedicos;
		this.ventanaPacientes = ventanaPacientes;
		this.clinica = Clinica.getInstancia();

		this.ventanaPacientes.SetActionListenerPacientes(this);
		this.ventanaMedicos.setActionListenerMedicos(this);

		this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		this.actualizaVentanaPacientes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Agregar Medico")) {
			try {
				this.agregaMedico();
				this.ventanaMedicos.limpiarCamposMedicos();
			} catch (MedicoYaAgregadoException | ContratacionNoRegistradaExceptions | EspecialidadNoRegistradaExceptions
					| PosgradoNoRegistradoExceptions | EspecialidadNoIndicadaException e1) {
				ventanaMedicos.mostrarMensajeError(e1.getMessage());
			}
			this.ventanaMedicos.actualizaListaMedicos(this.clinica.getIteratorMedicos());
		} else if (e.getActionCommand().equalsIgnoreCase("Ingresa Paciente")) {
			this.ingresarPaciente();
			this.actualizaVentanaPacientes();
		} else if (e.getActionCommand().equalsIgnoreCase("Agregar Paciente")) {
			try {
				this.agregaPaciente();
				this.actualizaVentanaPacientes();
			} catch (PacienteYaExistenteException | TipoDePacienteIncorrectoException | PacienteNoEncontrado
					| PacienteYaIngresadoException e1) {
				ventanaPacientes.mostrarMensajeError(e1.getMessage());
			}
		} else if (e.getActionCommand().equalsIgnoreCase("Atender Siguiente")) {
			try {
				clinica.atiendeSiguiente();
				this.actualizaVentanaPacientes();
			} catch (ColaDeEsperaVaciaException e1) {
				ventanaPacientes.mostrarMensajeError(e1.getMessage());
			}
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

	/**
	 * se encarga de agregar un paciente al sistema de la clinica, y ademas se
	 * encarga de ingresarlo a la cola de espera
	 * 
	 * @throws PacienteYaExistenteException      si el paciente ya existe
	 * @throws TipoDePacienteIncorrectoException si el tipo de paciente es
	 *                                           incorrecto
	 * @throws PacienteNoEncontrado              si el paciente que se quiere
	 *                                           ingresar no esta registrado
	 * @throws PacienteYaIngresadoException      si el paciente que se quiere
	 *                                           ingresar ya esta ingresado
	 */
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
	 * Se encarga de actualizar los elementos de una vista que implemente la
	 * interface IVistaPaciente,
	 */
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

}
