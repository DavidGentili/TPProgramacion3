package clinica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import asociado.Asociado;
import exceptions.AsociadoNoEncontrado;
import exceptions.AsociadoYaExistente;
import exceptions.CantidadDeDiasErroneosException;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoIndicadaException;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.FechaInvalidaException;
import exceptions.MedicoNoEncontradoException;
import exceptions.MedicoYaAgregadoException;
import exceptions.MontoInvalidoException;
import exceptions.NroHistoriaClinicaNoEncontrado;
import exceptions.PacienteNoAtendido;
import exceptions.PacienteNoEncontrado;
import exceptions.PacienteYaExistenteException;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDeHabitacionIncorrectaException;
import exceptions.TipoDePacienteIncorrectoException;
import habitaciones.Internacion;
import medicos.IMedico;
import medicos.Medico;
import medicos.MedicoFactory;
import pacientes.Paciente;
import pacientes.PacienteFactory;
import personas.Domicilio;
import prestaciones.Consulta;
import prestaciones.Prestacion;

/**
 * Es la clase encargada de nuclear e integrar los modulos, la clase contenedora
 * de todos los elementos del proyecto
 *
 */
public class Clinica {
	private static Clinica instancia = null;

	private String nombre;
	private Domicilio direccion;
	private String telefono;
	private String ciudad;
	private Ambulancia a;

	private HashMap<Integer, IMedico> medicos = new HashMap<Integer, IMedico>();
	private HashMap<Integer, Paciente> pacientesHist = new HashMap<Integer, Paciente>();
	private TreeSet<Factura> historial = new TreeSet<Factura>();
	private HashMap<Integer, Asociado> asociados = new HashMap<Integer, Asociado>();

	private Paciente salaPrivada = null;
	private ArrayList<Paciente> patio = new ArrayList<Paciente>();
	private LinkedList<Paciente> colaEspera = new LinkedList<Paciente>();

	private ArrayList<Paciente> enAtencion = new ArrayList<Paciente>();

	private Clinica() {

	}

	private Clinica(String nombre, Domicilio direccion, String telefono, String ciudad) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.a = Ambulancia.getInstance();
	}

	/**
	 * Nos retorna una unica instancia de la clinica, en caso de no estar
	 * inicializada se toman los valores por parametros, de lo contrario, los ya
	 * previamente ingresados
	 * 
	 * @param nombre    Nombre de la clinica
	 * @param direccion Direccion de la clinica
	 * @param telefono  Telefono de la clinica
	 * @param ciudad    ciudad de la clinica
	 * @return una instancia inicializada de la clinica
	 */
	public static Clinica getInstancia(String nombre, Domicilio direccion, String telefono, String ciudad) {
		if (instancia == null)
			instancia = new Clinica(nombre, direccion, telefono, ciudad);
		return instancia;
	}

	/**
	 * Nos retorna una instancia de la clinica, en caso de no estar inicializada,
	 * nos propaga una excepcion
	 * 
	 * @return una instancia de la clinica
	 * @throws ClinicaInexistenteExcepcion si la clinica no esta previamente
	 *                                     inicializada
	 */
	public static Clinica getInstancia() {
		if (instancia == null)
			instancia = new Clinica();
		return instancia;
	}

	/**
	 * Retorna el telefono de la clinica
	 * 
	 * @return telefono de la clinica
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Nos permite medoficar el telefono de la clinica
	 * 
	 * @param telefono telefono de la clinica
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Nos retorna el nombre de la clinica
	 * 
	 * @return nombre de la clinica
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * nos regresa la direccion de la clinica
	 * 
	 * @return Direccion de la clinica
	 */
	public Domicilio getDireccion() {
		return direccion;
	}

	/**
	 * Nos retorna la ciudad donde esta ubicada la clinica
	 * 
	 * @return Ciudad de la clinica
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Retonra una paciente segun su numero de historia clinica, si no lo encuentra
	 * propaga excepcion
	 * 
	 * @param historiaClinica El numero de historia clinica del paciente buscado
	 * @return El paciente buscado
	 * @throws PacienteNoEncontrado si no encuentra la paciente buscado
	 */
	public Paciente getPaciente(int historiaClinica) throws PacienteNoEncontrado {
		Paciente p = null;
		if (this.pacientesHist.containsKey(historiaClinica))
			p = this.pacientesHist.get(historiaClinica);
		else
			throw new PacienteNoEncontrado("No se encontro el paciente que se buscaba");
		return p;
	}

	/**
	 * Se regresa un medico correspondiente con una matricula ingresada, si no se
	 * encuentra se propaga excepcion
	 * 
	 * @param matricula Matricula del medico buscado
	 * @return El medico con la matricula buscada
	 * @throws MedicoNoEncontradoException Si el medico no esta registrado en la
	 *                                     clinica
	 */
	public IMedico getMedico(int matricula) throws MedicoNoEncontradoException {
		IMedico m = null;
		if (this.medicos.containsKey(matricula))
			m = this.medicos.get(matricula);
		else
			throw new MedicoNoEncontradoException("La matricula ingresada no se encuentra registrada en la clinica");
		return m;
	}

	/**
	 * Se retorna un iterator con los medicos registrados en al clinica
	 * 
	 * @return Medicos registrados en la clinica
	 */
	public Iterator<IMedico> getIteratorMedicos() {
		ArrayList<IMedico> aux = new ArrayList<IMedico>();
		for (Integer i : this.medicos.keySet()) {
			aux.add(this.medicos.get(i));
		}
		return aux.iterator();
	}

	/**
	 * Se retorna un iterator con los pacientes registrados en la clinica
	 * 
	 * @return los pacientes registrados en la clinica
	 */
	public Iterator<Paciente> getIteratorPacientesHistoricos() {
		ArrayList<Paciente> aux = new ArrayList<Paciente>();
		for (Integer i : this.pacientesHist.keySet()) {
			aux.add(this.pacientesHist.get(i));
		}
		return aux.iterator();
	}

	/**
	 * Se retorna un iterator con las facturas generadas
	 * 
	 * @return las facturas generadas
	 */
	public Iterator<Factura> getIteratorFacturas() {
		return this.historial.iterator();
	}

	/**
	 * Se retorna el paciente de la sala privada, si no hay nadie se retorna null
	 * 
	 * @return el paciente de la sala privada o null
	 */
	public Paciente getSalaPrivada() {
		return this.salaPrivada;
	}

	/**
	 * Se retorna un iterator con los pacientes que se encuentran en el patio
	 * 
	 * @return los pacientes del patio
	 */
	public Iterator<Paciente> getIteratorPatio() {
		return this.patio.iterator();
	}

	/**
	 * Se retorna un iterator con los pacientes que se encuentran en atencion
	 * 
	 * @return los pacientes en atencion
	 */
	public Iterator<Paciente> getIteratorEnAtencion() {
		return this.enAtencion.iterator();
	}

	public Iterator<Asociado> getIteratorAsociados() {
		ArrayList<Asociado> aux = new ArrayList<Asociado>();
		for (Integer i : this.asociados.keySet()) {
			aux.add(this.asociados.get(i));
		}
		return aux.iterator();
	}

	/**
	 * Nos permite crear y agregar un medico a la clinica
	 * 
	 * @param nombre       Nombre del Medico
	 * @param apellido     Apellido del Medico
	 * @param dni          DNI del Medico
	 * @param telefono     telefono del medico
	 * @param domicilio    domicilio del medico
	 * @param ciudad       ciudad del medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico |Cirujano|Clinico|Pediatra|
	 * @param posgrado     Posgrado del medico |Magister|Doctorado|
	 * @param contratacion Contratacion del medico |Temporario|Permanente|
	 * @throws MedicoYaAgregadoException          Si la matricula que se quiere
	 *                                            agregar ya existe en el sistema
	 * @throws ContratacionNoIndicadaExceptions   Si no se indico la contratacion
	 * @throws ContratacionNoRegistradaExceptions Si la contratacion indicada no
	 *                                            esta registrada
	 * @throws EspecialidadNoRegistradaExceptions Si la especialidad indicada no
	 *                                            esta registrada
	 * @throws PosgradoNoRegistradoExceptions     Si el posgrado indicado no esta
	 *                                            registrado
	 * @throws EspecialidadNoIndicadaException    Si la especialidad no esta
	 *                                            indicada
	 */
	public void agregaMedico(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int matricula, String especialidad, String posgrado, String contratacion)
			throws MedicoYaAgregadoException, ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions, EspecialidadNoIndicadaException {
		IMedico medico;
		if (!medicos.containsKey(matricula)) {
			medico = MedicoFactory.getInstancia(nombre, apellido, dni, telefono, domicilio, ciudad, matricula,
					especialidad, posgrado, contratacion);
			medicos.put(matricula, medico);
		} else
			throw new MedicoYaAgregadoException("El medico que desea agregar ya existe");
	}

	/**
	 * Nos permite crear y agregar un medico a la clinica
	 * 
	 * @param nombre       Nombre del Medico
	 * @param apellido     Apellido del Medico
	 * @param dni          DNI del Medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico |Cirujano|Clinico|Pediatra|
	 * @param posgrado     Posgrado del medico |Magister|Doctorado|
	 * @param contratacion Contratacion del medico |Temporario|Permanente|
	 * @throws MedicoYaAgregadoException          Si la matricula que se quiere
	 *                                            agregar ya existe en el sistema
	 * @throws ContratacionNoIndicadaExceptions   Si no se indico la contratacion
	 * @throws ContratacionNoRegistradaExceptions Si la contratacion indicada no
	 *                                            esta registrada
	 * @throws EspecialidadNoRegistradaExceptions Si la especialidad indicada no
	 *                                            esta registrada
	 * @throws PosgradoNoRegistradoExceptions     Si el posgrado indicado no esta
	 *                                            registrado
	 * @throws EspecialidadNoIndicadaException    si no esta indicada la
	 *                                            especialidad
	 */
	public void agregaMedico(String nombre, String apellido, int dni, int matricula, String especialidad,
			String posgrado, String contratacion)
			throws MedicoYaAgregadoException, ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions, EspecialidadNoIndicadaException {
		IMedico medico;
		if (!medicos.containsKey(matricula)) {
			medico = MedicoFactory.getInstancia(nombre, apellido, dni, matricula, especialidad, posgrado, contratacion);
			medicos.put(matricula, medico);
		} else
			throw new MedicoYaAgregadoException("El medico que desea agregar ya existe");
	}

	/**
	 * Nos permite agregar un paciente a los registros de la clinica
	 * 
	 * @param nombre               nombre del paciente
	 * @param apellido             Apellido del paciente
	 * @param dni                  Dni del paciente
	 * @param nroDeHistoriaClinica Nro de historia clinica del paciente
	 * @param rangoEtareo          Rango etareo del paciente
	 * @throws PacienteYaExistenteException      Si el paciente que se quiere
	 *                                           ingresar ya existe
	 * @throws TipoDePacienteIncorrectoException Si el rango etareo ingresado no
	 *                                           esta registrado
	 */
	public void agregaPaciente(String nombre, String apellido, int dni, int nroDeHistoriaClinica, String rangoEtareo)
			throws PacienteYaExistenteException, TipoDePacienteIncorrectoException {
		if (!this.pacientesHist.containsKey(nroDeHistoriaClinica)) {
			Paciente p = PacienteFactory.getInstance(rangoEtareo, nombre, apellido, dni, nroDeHistoriaClinica);
			this.pacientesHist.put(nroDeHistoriaClinica, p);
		} else
			throw new PacienteYaExistenteException("El paciente que quiere agregar ya existe");
	}

	/**
	 * Nos permite agregar un paciente a los registros de la clinica
	 * 
	 * @param nombre               nombre del paciente
	 * @param apellido             Apellido del paciente
	 * @param dni                  Dni del paciente
	 * @param telefono             Telefono del paciente
	 * @param domicilio            Domicilio del paciente
	 * @param ciudad               Ciudad del paciente
	 * @param nroDeHistoriaClinica Nro de historia clinica del paciente
	 * @param rangoEtareo          Rango etareo del paciente
	 * @throws PacienteYaExistenteException      Si el paciente que se quiere
	 *                                           ingresar ya existe
	 * @throws TipoDePacienteIncorrectoException Si el rango etareo ingresado no
	 *                                           esta registrado
	 */
	public void agregaPaciente(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int nroDeHistoriaClinica, String rangoEtareo)
			throws PacienteYaExistenteException, TipoDePacienteIncorrectoException {
		if (!this.pacientesHist.containsKey(nroDeHistoriaClinica)) {
			Paciente p = PacienteFactory.getInstance(rangoEtareo, nombre, apellido, dni, telefono, direccion, ciudad,
					nroDeHistoriaClinica);
			this.pacientesHist.put(nroDeHistoriaClinica, p);
		} else
			throw new PacienteYaExistenteException("El paciente que quiere agregar ya existe");
	}

	/**
	 * Nos permite ingresar un paciente a la clinica, y colocarlo en la cola de
	 * espera, siempre y cuando se haya atendido previamente en la misma
	 * 
	 * @param nroHistoriaClinica Numero de historia clinica del paciente
	 * @throws NroHistoriaClinicaNoEncontrado Si el numero de historia clinica
	 *                                        ingresado no corresponde a ningun
	 *                                        paciente registrado
	 */
	public void ingresaPaciente(int nroHistoriaClinica) throws NroHistoriaClinicaNoEncontrado {
		if (this.pacientesHist.containsKey(nroHistoriaClinica)) {
			Paciente p = this.pacientesHist.get(nroHistoriaClinica);
			colaEspera.add(p);
			this.reasignaEspera(p);
		} else
			throw new NroHistoriaClinicaNoEncontrado("No se encontro el numero de historia clinica");
	}

	/**
	 * Nos permite ingresar un paciente a la clinica, en caso que ese paciente no
	 * este registrado en la clinica se emite una excepcion
	 * 
	 * @param paciente Paciente a ingresar
	 * @throws NroHistoriaClinicaNoEncontrado Si el paciente no esta registrado en
	 *                                        la clinica
	 */
	public void ingresaPaciente(Paciente paciente) throws NroHistoriaClinicaNoEncontrado {
		if (this.pacientesHist.containsKey(paciente.getNroHistoriaClinica())) {
			colaEspera.add(paciente);
			this.reasignaEspera(paciente);
		} else
			throw new NroHistoriaClinicaNoEncontrado(
					"El paciente ingresado no se encuentra en los registro de la clinica");
	}

	/**
	 * Nos permite ingresar un paciente a la clinica, en caso de existir el numero
	 * de historia clinica, se usa el paciente existente, de lo contrario se crea un
	 * paciente nuevo y se agrega al sistema
	 * 
	 * @param nombre          Nombre del paciente
	 * @param apellido        Apellido del paciente
	 * @param dni             DNI del paciente
	 * @param historiaClinica Numero de historia clinica del paciente
	 * @param rangoEtario     Rango Etareo del paciente |Ninio|Joven|Mayor
	 * @throws TipoDePacienteIncorrectoException Si el tipo de rango etareo
	 *                                           ingresado no se encuentra
	 */
	public void ingresaPaciente(String nombre, String apellido, int dni, int historiaClinica, String rangoEtario)
			throws TipoDePacienteIncorrectoException {
		Paciente p = null;
		if (!this.pacientesHist.containsKey(historiaClinica))
			p = PacienteFactory.getInstance(rangoEtario, nombre, apellido, dni, historiaClinica);
		else
			p = this.pacientesHist.get(historiaClinica);
		this.pacientesHist.put(p.getNroHistoriaClinica(), p);
		colaEspera.add(p);
		this.reasignaEspera(p);

	}

	/**
	 * Nos permite ingresar un paciente a la clinica, en caso de existir el numero
	 * de historia clinica, se usa el paciente existente, de lo contrario se crea un
	 * paciente nuevo y se agrega al sistema
	 * 
	 * @param nombre          Nombre del paciente
	 * @param apellido        Apellido del paciente
	 * @param dni             DNI del paciente
	 * @param telefono        Telefono de contacto del paciente
	 * @param domicilio       Domicilio del paciente
	 * @param ciudad          Ciudad del paciente
	 * @param historiaClinica Numero de historia clinica del paciente
	 * @param rangoEtario     Rango Etareo del paciente |Ninio|Joven|Mayor
	 * @throws TipoDePacienteIncorrectoException Si el tipo de rango etareo
	 *                                           ingresado no se encuentra
	 */
	public void ingresaPaciente(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int historiaClinica, String rangoEtario) throws TipoDePacienteIncorrectoException {
		Paciente p = null;
		if (!this.pacientesHist.containsKey(historiaClinica))
			p = PacienteFactory.getInstance(rangoEtario, nombre, apellido, dni, telefono, domicilio, ciudad,
					historiaClinica);
		else
			p = this.pacientesHist.get(historiaClinica);
		this.pacientesHist.put(p.getNroHistoriaClinica(), p);
		colaEspera.add(p);
		this.reasignaEspera(p);

	}

	/**
	 * Se encarga de agregar un Asociado a la estructura de la clinica
	 * 
	 * @param asociado Asociado a agregar
	 * @throws AsociadoYaExistente Si el asociado que se quiere agregar ya se
	 *                             encuentra en el sistema
	 */
	public void agregaAsociado(Asociado asociado) throws AsociadoYaExistente {
		if (!this.asociados.containsKey(asociado.getDni())) {
			this.asociados.put(asociado.getDni(), asociado);
		} else
			throw new AsociadoYaExistente("El asociado que intenta agregar ya se encuentra");
	}

	/**
	 * Se encarga de crear un asociado y agregarlos a la estructura de asociados de
	 * la clinica
	 * 
	 * @param nombre    nombre del asociado
	 * @param apellido  Apellido del asociado
	 * @param dni       Dni del asociado
	 * @param domicilio Domicilio del asociado
	 * @param telefono  Telefono del asociado
	 * @throws AsociadoYaExistente Si el asociado que se intenta eliminar no se
	 *                             encuentra
	 */
	public void agregaAsociado(String nombre, String apellido, int dni, Domicilio domicilio, String telefono)
			throws AsociadoYaExistente {
		if (!this.asociados.containsKey(dni)) {
			Asociado asociado = new Asociado(nombre, apellido, dni, telefono, domicilio);
			this.asociados.put(dni, asociado);
		} else
			throw new AsociadoYaExistente("El asociado que intenta agregar ya se encuentra");
	}

	public void eliminaAsociado(Asociado asociado) throws AsociadoNoEncontrado {
		if (this.asociados.containsKey(asociado.getDni())) {
			this.asociados.remove(asociado.getDni());
		} else
			throw new AsociadoNoEncontrado("No se encuentra el asociado ingresado");
	}

	public void eliminaAsociado(int dniDelAsociado) throws AsociadoNoEncontrado {
		if (this.asociados.containsKey(dniDelAsociado)) {
			this.asociados.remove(dniDelAsociado);
		} else
			throw new AsociadoNoEncontrado("No se encuentra el asociado ingresado");
	}

	/**
	 * Asigna un paciente recien llegado a la sala privada o el patio, en caso de
	 * haber un paciente en la sala de espera privada, elige cual de los dos va a la
	 * sala de espera y cual va al patio
	 * 
	 * @param p el paciente que acaba de ingresar a la clinica
	 */
	public void reasignaEspera(Paciente p) {
		Paciente aux;
		if (this.salaPrivada == null)
			this.salaPrivada = p;
		else {
			aux = this.salaPrivada; // guardo en una variable auxiliar lo q habia
			this.salaPrivada = this.salaPrivada.comparaIngreso(p); // comparo
			if (aux == this.salaPrivada) // si sigue siendo igual es porq p debe ir al patio
				this.patio.add(p);
			else
				this.patio.add(aux);
		}
	}

	/**
	 * Localiza al siguiente paciente en ser atendido y lo retira del patio o de la
	 * sala de espera
	 * 
	 * @throws IndexOutOfBoundsException : Si la cola de espera esta vacia
	 */

	public void atiendeSiguiente() throws IndexOutOfBoundsException {
		Paciente aux = this.colaEspera.removeFirst();
		if (this.salaPrivada == aux)
			this.salaPrivada = null;
		else {
			this.patio.remove(aux);
		}
		this.enAtencion.add(aux);
	}

	/**
	 * Agrega una cantidad determinadas de consultas entre un medico y un paciente
	 * 
	 * @param paciente Paciente que realizo la consutla
	 * @param medico   Medico que practico la consulta
	 * @param cantidad cantidad de consultas
	 * @throws CantidadDeDiasErroneosException Si la cantidad de consultas no es
	 *                                         positivas
	 * @throws PacienteNoEncontrado            Si no se encuentra el paciente
	 *                                         ingresado
	 * @throws MedicoNoEncontradoException     si no se encuentra el medico indicado
	 */
	public void agregaConsulta(Paciente paciente, IMedico medico, int cantidad)
			throws CantidadDeDiasErroneosException, PacienteNoEncontrado, MedicoNoEncontradoException {
		if (medico != null && this.medicos.containsKey(medico.getMatricula())) {
			if (cantidad > 0) {
				if (paciente != null && this.enAtencion.contains(paciente)) {
					paciente.agregaPrestacion(creaConsulta(medico, cantidad));
				} else
					throw new PacienteNoEncontrado(
							"debe seleccionar un paciente correcto de la lista de 'en atencion'");
			} else
				throw new CantidadDeDiasErroneosException("No se pueden ingresar dias negativos para la internacion");
		} else
			throw new MedicoNoEncontradoException("debe seleccionar un medico correcto");
	}

	/**
	 * Agrega una cantidad determinadas de consultas entre un medico y un paciente
	 * 
	 * @param paciente  Paciente que realizo la consutla
	 * @param matricula La matricula del medico ingresado
	 * @param cantidad  cantidad de consultas
	 * @throws CantidadDeDiasErroneosException Si la cantidad de consultas no es
	 *                                         positivas
	 * @throws PacienteNoEncontrado            Si no se encuentra el paciente
	 *                                         ingresado
	 * @throws MedicoNoEncontradoException     si no se encuentra el medico indicado
	 */
	public void agregaConsulta(Paciente paciente, int matricula, int cantidad)
			throws CantidadDeDiasErroneosException, PacienteNoEncontrado, MedicoNoEncontradoException {
		if (this.medicos.containsKey(matricula)) {
			if (cantidad >= 0) {
				if (this.enAtencion.contains(paciente)) {
					paciente.agregaPrestacion(creaConsulta(this.medicos.get(matricula), cantidad));
				} else
					throw new PacienteNoEncontrado(
							"debe seleccionar un paciente correcto de la lista de 'en atencion'");
			} else
				throw new CantidadDeDiasErroneosException("No se pueden ingresar dias negativos para la internacion");
		} else
			throw new MedicoNoEncontradoException("debe seleccionar un medico correcto");
	}

	/**
	 * Crea una prestacion de tipo consulta con el medico y la cantida de dias
	 * 
	 * @param medico   El medico que practica la consulta
	 * @param cantidad La cantidad de consultas realizadas
	 * @return La prestacion respectivamente cargada
	 */
	private Prestacion creaConsulta(IMedico medico, int cantidad) {
		Consulta consulta = null;
		double valor = 0;
		valor = medico.getSueldo() * 1.2;
		consulta = new Consulta(medico.getApellido(), valor, cantidad, valor * cantidad, medico);
		return consulta;
	}

	/**
	 * Se encarga de agregar una prestacion con la informacion de una internacion
	 * 
	 * @param paciente paciente
	 * @param tipo     tipo de sala
	 * @param cantidad cantidad de dias
	 * @throws TipoDeHabitacionIncorrectaException - si no se encontro el tipo de
	 *                                             habitacion <br>
	 * @throws CantidadDeDiasErroneosException     - si el numero de dias es
	 *                                             negativo<br>
	 * @throws PacienteNoEncontrado                - Si el paciente no se encuentra
	 *                                             en la lista de EnAtencion
	 */
	public void agregaInternacion(Paciente paciente, String tipo, int cantidad)
			throws TipoDeHabitacionIncorrectaException, CantidadDeDiasErroneosException, PacienteNoEncontrado {
		if (cantidad >= 0) {
			if (this.enAtencion.contains(paciente)) {
				paciente.agregaPrestacion(Internacion.getInstancia().getPrestacion(cantidad, tipo));
			} else
				throw new PacienteNoEncontrado("debe seleccionar un paciente correcto de la lista de 'en atendidos'");
		} else
			throw new CantidadDeDiasErroneosException("No se pueden ingresar dias negativos para la internacion");
	}

	/**
	 * Selecciona un paciente de la lista de EnAtencion, le realiza la factura, lo
	 * retira y elimina las prestaciones que tenia registradas, almacenando una
	 * copia de las prestaciones en la factura
	 * 
	 * @param paciente El paciente al que se le hace la factura
	 * @param fecha    La fecha en la que se realiza una factura
	 * @throws PacienteNoEncontrado   Si el paciente indicado no se encuentra
	 * @throws PacienteNoAtendido     Si el paciente no posee prestaciones
	 * @throws FechaInvalidaException
	 */
	public String facturaPaciente(Paciente paciente, GregorianCalendar fecha)
			throws PacienteNoEncontrado, PacienteNoAtendido, FechaInvalidaException {
		if (fecha.compareTo(new GregorianCalendar()) <= 0) {
			if (this.enAtencion.contains(paciente)) {
				if (!paciente.getPrestaciones().isEmpty()) {
					Factura factura = new Factura(fecha, paciente);
					this.enAtencion.remove(paciente);
					paciente.limpiaPrestaciones();
					this.historial.add(factura);
					return factura.muestraInformacion();
				} else
					throw new PacienteNoAtendido("el paciente no tiene prestaciones realizadas");
			} else
				throw new PacienteNoEncontrado(
						"debe seleccionar un paciente perteneciente a la lista de 'en atendidos'");
		} else
			throw new FechaInvalidaException("La fecha es incorrecta");

	}

	/**
	 * Crea un reporte de actividad medica, entre dos fechas, con el monto total
	 * ganado, y las atenciones realizadas.
	 * 
	 * @param medico Medico sobre el cual se realiza el reporte
	 * @param inicio Fecha inicial de periodo
	 * @param fin    Fecha final del periodo
	 */
	public String reporteActividadMedica(IMedico medico, GregorianCalendar inicio, GregorianCalendar fin) {
		Iterator<Factura> itFacturas = this.historial.iterator();
		double acum = 0, valor;
		GregorianCalendar fecha = new GregorianCalendar(2000, 0, 1);// Se pone una fecha previa para evitar un hacer una
																	// comparacion con un null en la primera iteracion
		SimpleDateFormat sdf = new SimpleDateFormat("dd'/'MM'/'yy");
		Prestacion prestaciones;
		Factura factura;
		StringBuilder sb = new StringBuilder();

		sb.append("REPORTE DEL MEDICO: " + medico.getApellido() + " matricula " + medico.getMatricula() + "\n");
		while (itFacturas.hasNext()) {
			factura = itFacturas.next();
			if (factura.getFecha().compareTo(inicio) >= 0 && factura.getFecha().compareTo(fin) <= 0) {
				Iterator<Prestacion> itPrestaciones = factura.getPrestaciones();
				while (itPrestaciones.hasNext()) {
					prestaciones = itPrestaciones.next();
					if (prestaciones.getDescripcion().equalsIgnoreCase(medico.getApellido())) {
						Consulta aux = (Consulta) prestaciones;
						if (medico.equals(aux.getMedico())) {
							if (!fecha.equals(factura.getFecha())) {
								fecha = factura.getFecha();
								sb.append(sdf.format(fecha.getTime()) + "\n");
							}
							valor = medico.getSueldo() * prestaciones.getCantidad();
							sb.append("|  Paciente  |  Cantidad  |   Valor   |\n");
							sb.append(factura.getPaciente().getApellido() + "        " + prestaciones.getCantidad()
									+ "        " + valor + "\n");
							acum += valor;
						}
					}
				}
			}
		}
		sb.append("\nEl importe total del medico es: " + acum);
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Clinica " + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", ciudad=" + ciudad;
	}

	/**
	 * Define el costo de la habitacion compartida
	 * 
	 * @param monto El monto de la habitacion compartida
	 * @throws MontoInvalidoException si el monto es negativo;
	 */
	public static void setCostoHabitacionCompartida(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Internacion.setCostoHabitacionCompartida(monto);
		else
			throw new MontoInvalidoException("El monto de la habitacion Compartida no puede ser negativo");
	}

	/**
	 * Define el costo de la habitacion privada
	 * 
	 * @param monto El monto de la habitacion privada
	 * @throws MontoInvalidoException si el monto es negativo;
	 */
	public static void setCostoHabitacionPrivada(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Internacion.setCostoHabitacionPrivada(monto);
		else
			throw new MontoInvalidoException("El monto de la habitacion Privada no puede ser negativo");
	}

	/**
	 * Define el costo de la terapia intensiva
	 * 
	 * @param monto El monto de la terapia intensiva
	 * @throws MontoInvalidoException si el monto es negativo;
	 */
	public static void setCostoTerapiaIntensiva(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Internacion.setCostoTerapiaIntensiva(monto);
		else
			throw new MontoInvalidoException("El monto de la Terapia Intensiva no puede ser negativo");
	}

	/**
	 * Define el monto del sueldo basico de los medico
	 * 
	 * @param monto el monto del sueldo basico de los medicos
	 * @throws MontoInvalidoException Si el monto
	 */
	public static void setSueldoBasicoMedico(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Medico.setSueldoBasico(monto);
		else
			throw new MontoInvalidoException("El monto del sueldo basico de los medicos no puede ser negativo");
	}

	/**
	 * Nos retorna el costo basico de la habitacion compartida
	 * 
	 * @return el costo basico de la habitacion compartida
	 */
	public static double getCostoHabitacionCompartida() {
		return Internacion.getCostoHabitacionCompartida();
	}

	/**
	 * Nos retorna el costo basico de la habitacion privada
	 * 
	 * @return el costo basico de la habitacion privada
	 */
	public static double getCostoHabitacionPrivada() {
		return Internacion.getCostoHabitacionPrivada();
	}

	/**
	 * Nos retorna el costo basico de la terapia intensiva
	 * 
	 * @return el costo basico de la terapia intensiva
	 */
	public static double getCostoTerapiaIntensiva() {
		return Internacion.getCostoTerapiaIntensiva();
	}

	/**
	 * Retorna el sueldo basico de los medicos
	 * 
	 * @return Sueldo basico de los medico
	 */
	public static double getSueldoBasicoMedico() {
		return Medico.getSueldoBasico();
	}

	/**
	 * Retorna la ambulancia de la clinica
	 * 
	 * @return Ambulancia de la clinica
	 */
	public Ambulancia getA() {
		return this.a;
	}

	/**
	 * Determina la ambulancia de la clinica
	 * 
	 * @param a Ambulancia de la clinica
	 */
	public void setA(Ambulancia a) {
		this.a = a;
	}

	/**
	 * Retonra el un HashMap con los pacientes historicos
	 * 
	 * @return Pacientes historicos
	 */
	public HashMap<Integer, Paciente> getPacientesHist() {
		return pacientesHist;
	}

	/**
	 * Determina un HashMap de pacientes historicos
	 * 
	 * @param pacientesHist Pacientes historicos
	 */
	public void setPacientesHist(HashMap<Integer, Paciente> pacientesHist) {
		this.pacientesHist = pacientesHist;
	}

	/**
	 * Retotna un TreeSet de Facturas realizadas
	 * 
	 * @return Facturas realizadas
	 */
	public TreeSet<Factura> getHistorial() {
		return historial;
	}

	/**
	 * Determina un TreeSet de Facturas realizadas
	 * 
	 * @param historial Facturas realizadas
	 */
	public void setHistorial(TreeSet<Factura> historial) {
		this.historial = historial;
	}

	/**
	 * Retorna LinkedList con la cola de espera
	 * 
	 * @return cola de espera
	 */
	public LinkedList<Paciente> getColaEspera() {
		return colaEspera;
	}

	/**
	 * determina la cola de espera
	 * 
	 * @param colaEspera Cola de espera de pacientes
	 */
	public void setColaEspera(LinkedList<Paciente> colaEspera) {
		this.colaEspera = colaEspera;
	}

	/**
	 * Determina el nombre de la clinica
	 * 
	 * @param nombre Nombre de la clinica
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Determina la direccion de la clinica
	 * 
	 * @param direccion Direccion de la clinica
	 */
	public void setDireccion(Domicilio direccion) {
		this.direccion = direccion;
	}

	/**
	 * Determina la ciudad de la clinica
	 * 
	 * @param ciudad Ciudad de la clinica
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Determina los medicos de la clinica
	 * 
	 * @param medicos HashMap de medicos
	 */
	public void setMedicos(HashMap<Integer, IMedico> medicos) {
		this.medicos = medicos;
	}

	/**
	 * Determina el paciente que integra la sala privada
	 * 
	 * @param salaPrivada Paciente de la sala privada
	 */
	public void setSalaPrivada(Paciente salaPrivada) {
		this.salaPrivada = salaPrivada;
	}

	/**
	 * Determina el patio de la sala de espera
	 * 
	 * @param patio patio de sala de espera
	 */
	public void setPatio(ArrayList<Paciente> patio) {
		this.patio = patio;
	}

	/**
	 * Determina los pacientes en atencion
	 * 
	 * @param enAtencion Pacientes en atencion
	 */
	public void setEnAtencion(ArrayList<Paciente> enAtencion) {
		this.enAtencion = enAtencion;
	}

	/**
	 * Retorna un HashMap<Integer,IMedico> con los medicos
	 * 
	 * @return medicos de la clinica
	 */
	public HashMap<Integer, IMedico> getMedicos() {
		return medicos;
	}

	/**
	 * Retorna un arrayList con los pacientes del patio
	 * 
	 * @return Pacientes del patio
	 */
	public ArrayList<Paciente> getPatio() {
		return patio;
	}

	/**
	 * Retorna un ArrayList con los pacientes en atencion
	 * 
	 * @return Pacientes en atencion
	 */
	public ArrayList<Paciente> getEnAtencion() {
		return enAtencion;
	}

	/**
	 * Retorna un HashMap con los asociados de la clinica
	 * 
	 * @return HashMap de asociados
	 */
	public HashMap<Integer, Asociado> getAsociados() {
		return asociados;
	}

	/**
	 * Determina un HashMap con los asociados de la clinica
	 * 
	 * @param asociados HashMap de asociados
	 */
	public void setAsociados(HashMap<Integer, Asociado> asociados) {
		this.asociados = asociados;
	}

}
