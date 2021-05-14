package clinica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import exceptions.ClinicaInexistenteExcepcion;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.MontoInvalidoException;
import exceptions.PosgradoNoRegistradoExceptions;
import habitaciones.Internacion;
import medicos.IMedico;
import medicos.MedicoFactory;
import pacientes.Paciente;
import pacientes.PacienteFactory;
import personas.Domicilio;
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

	public void agregaPaciente(String nombre, String apellido, int dni, int historiaClinica, String rangoEtario) {

		Paciente p = PacienteFactory.getInstance(rangoEtario, nombre, apellido, dni, historiaClinica);
		if (p != null) {
			if (!pacientesHist.containsKey(historiaClinica))
				pacientesHist.put(historiaClinica, p);
			colaEspera.add(p);
			this.reasignaEspera(p);
		} else
			System.out.println("rango etario inexistente");
	}

	public void agregaPaciente(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int historiaClinica, String rangoEtario) {

		Paciente p = PacienteFactory.getInstance(rangoEtario, nombre, apellido, dni, telefono, domicilio, ciudad,
				historiaClinica);
		if (p != null) {
			if (!pacientesHist.containsKey(historiaClinica))
				pacientesHist.put(historiaClinica, p);
			colaEspera.add(p);
			this.reasignaEspera(p);
		} else
			System.out.println("rango etario inexistente");

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
	public void agregaConsulta(Paciente p, int matricula, int cantidad) {
		
	}

	/**
	 * 
	 * 
	 * @param p        paciente
	 * @param tipo     tipo de sala
	 * @param cantidad cantidad de dias
	 */
	public void agregaInternacion(Paciente p, String tipo, int cantidad) {
		
	}

	public void facturaPaciente(Paciente p) {

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
