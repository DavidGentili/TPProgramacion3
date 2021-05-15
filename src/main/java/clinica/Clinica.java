package clinica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import exceptions.CantidadDeDiasErroneosException;
import exceptions.ClinicaInexistenteExcepcion;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.MontoInvalidoException;
import exceptions.NroHistoriaClinicaNoEncontrado;
import exceptions.PacienteNoAtendido;
import exceptions.PacienteNoEncontrado;
import exceptions.PosgradoNoRegistradoExceptions;
import exceptions.TipoDeHabitacionIncorrectaException;
import exceptions.TipoDePacienteIncorrectoException;
import habitaciones.Internacion;
import medicos.IMedico;
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

	private HashMap<Integer, IMedico> medicos = new HashMap<Integer, IMedico>();
	private HashMap<Integer, Paciente> pacientesHist = new HashMap<Integer, Paciente>();
	private TreeSet<Factura> historial = new TreeSet<Factura>();

	private Paciente salaPrivada = null;
	private ArrayList<Paciente> patio = new ArrayList<Paciente>();
	private LinkedList<Paciente> colaEspera = new LinkedList<Paciente>();

	private ArrayList<Paciente> enAtencion = new ArrayList<Paciente>();

	private Clinica(String nombre, Domicilio direccion, String telefono, String ciudad) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
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
	public static Clinica getInstancia() throws ClinicaInexistenteExcepcion {
		if (instancia != null)
			return instancia;
		else
			throw new ClinicaInexistenteExcepcion("La clinica no se ha inicializado");
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
	 * Nos permite crear y agregar un medico a la clinica
	 * 
	 * @param nombre       Nombre del Medico
	 * @param apellido     Apellido del Medico
	 * @param dni          DNI del Medico
	 * @param telefono     telefono del medico
	 * @param domicilio    domicilio del medico
	 * @param ciudad       ciudad del medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico
	 * @param posgrado     Posgrado del medico
	 * @param contratacion Contratacion del medico
	 * @throws MedicoYaAgregadoException          Si la matricula que se quiere
	 *                                            agregar ya existe en el sistema
	 * @throws ContratacionNoIndicadaExceptions   Si no se indico la contratacion
	 * @throws ContratacionNoRegistradaExceptions Si la contratacion indicada no
	 *                                            esta registrada
	 * @throws EspecialidadNoRegistradaExceptions Si la especialidad indicada no
	 *                                            esta registrada
	 * @throws PosgradoNoRegistradoExceptions     Si el posgrado indicado no esta
	 *                                            registrado
	 */
	public void agregaMedico(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int matricula, String especialidad, String posgrado, String contratacion)
			throws MedicoYaAgregadoException, ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions {
		IMedico medico;
		if (!medicos.containsKey(matricula)) {
			medico = MedicoFactory.getInstancia(nombre, apellido, dni, telefono, domicilio, ciudad, matricula,
					especialidad, posgrado, contratacion);
			medicos.put(matricula, medico);
		} else
			throw new MedicoYaAgregadoException("El medico que desea agregar ya existe");
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
	public void IngresaPaciente(int nroHistoriaClinica) throws NroHistoriaClinicaNoEncontrado {
		if (this.pacientesHist.containsKey(nroHistoriaClinica)) {
			Paciente p = this.pacientesHist.get(nroHistoriaClinica);
			colaEspera.add(p);
			this.reasignaEspera(p);
		} else
			throw new NroHistoriaClinicaNoEncontrado("No se encontro el numero de historia clinica");
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
	public void IngresaPaciente(String nombre, String apellido, int dni, int historiaClinica, String rangoEtario)
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
	public void IngresaPaciente(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
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
	 * un paciente hace una cantidad indicadas de consultas al medico indicado
	 * 
	 * @param p         paciente con consulta
	 * @param matricula matricula del medico
	 * @param cantidad  cantidad de consultas realizadas al medico
	 */
	public void agregaConsulta(Paciente paciente, IMedico medico, int cantidad)
			throws CantidadDeDiasErroneosException, PacienteNoEncontrado {
		if (cantidad >= 0) {
			if (this.enAtencion.contains(paciente)) {
				paciente.agregaPrestacion(creaConsulta(medico, cantidad));
			} else
				throw new PacienteNoEncontrado("No se encontro el paciente seleccionado en la lista de espera");
		} else
			throw new CantidadDeDiasErroneosException("No se pueden ingresar dias negativos para la internacion");
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
				throw new PacienteNoEncontrado("No se encontro el paciente seleccionado en la lista de espera");
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
	 * @throws PacienteNoEncontrado Si el paciente indicado no se encuentra
	 * @throws PacienteNoAtendido   Si el paciente no posee prestaciones
	 */
	public void facturaPaciente(Paciente paciente, GregorianCalendar fecha)
			throws PacienteNoEncontrado, PacienteNoAtendido {
		if (this.enAtencion.contains(paciente)) {
			if (!paciente.getPretaciones().isEmpty()) {
				Factura factura = new Factura(fecha, paciente);
				this.enAtencion.remove(paciente);
				paciente.limpiaPrestaciones();
				this.historial.add(factura);
			} else
				throw new PacienteNoAtendido("El paciente no tiene prestaciones realizadas");
		} else
			throw new PacienteNoEncontrado("No se encontro el paciente seleccionado en la lista de espera");
	}

	/**
	 * Crea un reporte de actividad medica, entre dos fechas, con el monto total
	 * ganado, y las atenciones realizadas.
	 * 
	 * @param medico Medico sobre el cual se realiza el reporte
	 * @param inicio Fecha inicial de periodo
	 * @param fin    Fecha final del periodo
	 */
	public void reporteActividadMedica(IMedico medico, GregorianCalendar inicio, GregorianCalendar fin) {
		Iterator<Factura> itFacturas = this.historial.iterator();
		double acum = 0, valor;
		GregorianCalendar fecha = null;
		Prestacion prestaciones;
		Factura factura = itFacturas.next();
		System.out.println("REPORTE DEL MEDICO: " + medico.getApellido() + " matricula " + medico.getMatricula());
		while (itFacturas.hasNext()) {
			if (factura.getFecha().compareTo(inicio) >= 0 && factura.getFecha().compareTo(fin) <= 0) {
				Iterator<Prestacion> itPrestaciones = factura.getPrestaciones();
				while (itPrestaciones.hasNext()) {
					prestaciones = itPrestaciones.next();
					if (prestaciones.getDescripcion().equalsIgnoreCase(medico.getApellido())) {
						Consulta aux = (Consulta) prestaciones;
						if (medico.equals(aux.getMedico())) {
							if (!fecha.equals(factura.getFecha())) {
								fecha = factura.getFecha();
								System.out.println(fecha);
							}
							valor = medico.getSueldo() * prestaciones.getCantidad();
							System.out.println(factura.getPaciente().getApellido() + " " + valor);
							acum += valor;
						}
					}
				}
			}
		}
		System.out.println("El importe total del medico es:" + acum);
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
			throw new MontoInvalidoException("El monto de la habitacion Compartida debe ser positivo");
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
			throw new MontoInvalidoException("El monto de la habitacion Privada debe ser positivo");
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
			throw new MontoInvalidoException("El monto de la Terapia Intensiva debe ser positivo");
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
	public static double getCostInternacion() {
		return Internacion.getCostoTerapiaIntensiva();
	}
}
