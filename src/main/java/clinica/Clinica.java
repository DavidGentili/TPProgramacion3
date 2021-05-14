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

	public static Clinica getInstancia(String nombre, Domicilio direccion, String telefono, String ciudad) {
		if (instancia == null)
			instancia = new Clinica(nombre, direccion, telefono, ciudad);
		return instancia;
	}

	public static Clinica getInstancia() throws ClinicaInexistenteExcepcion {
		if (instancia != null)
			return instancia;
		else
			throw new ClinicaInexistenteExcepcion("La clinica no se ha inicializado");
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public Domicilio getDireccion() {
		return direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

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

	public void IngresaPaciente(int nroHistoriaClinica) throws NroHistoriaClinicaNoEncontrado {
		if (this.pacientesHist.containsKey(nroHistoriaClinica)) {
			Paciente p = this.pacientesHist.get(nroHistoriaClinica);
			colaEspera.add(p);
			this.reasignaEspera(p);
		} else
			throw new NroHistoriaClinicaNoEncontrado("No se encontro el numero de historia clinica");
	}

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
	 * Asigna un paciente recien llegado a la sala privada o el patio
	 * 
	 * @param p
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
	 * @throws IndexOutOfBoundsException : lo lanzara si la cola esta vacia
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
	 * un paciente hace una consulta
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

	private Prestacion creaConsulta(IMedico medico, int cantidad) {
		Consulta consulta = null;
		double valor = 0;
		valor = medico.getSueldo() * 1.2;
		consulta = new Consulta(medico.getApellido(), valor, cantidad, valor * cantidad, medico);
		return consulta;
	}

	/**
	 * 
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

	public static void setCostoHabitacionCompartida(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Internacion.setCostoHabitacionCompartida(monto);
		else
			throw new MontoInvalidoException("El monto de la habitacion Compartida debe ser positivo");
	}

	public static void setCostoHabitacionPrivada(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Internacion.setCostoHabitacionPrivada(monto);
		else
			throw new MontoInvalidoException("El monto de la habitacion Privada debe ser positivo");
	}

	public static void setCostoTerapiaIntensiva(double monto) throws MontoInvalidoException {
		if (monto >= 0)
			Internacion.setCostoTerapiaIntensiva(monto);
		else
			throw new MontoInvalidoException("El monto de la Terapia Intensiva debe ser positivo");
	}

	public static double getCostoHabitacionCompartida() {
		return Internacion.getCostoHabitacionCompartida();
	}

	public static double getCostoHabitacionPrivada() {
		return Internacion.getCostoHabitacionPrivada();
	}

	public static double getCostInternacion() {
		return Internacion.getCostoTerapiaIntensiva();
	}
}
