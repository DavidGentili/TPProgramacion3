package clinica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

import exceptions.ClinicaInexistenteExcepcion;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.PosgradoNoRegistradoExceptions;
import habitaciones.Habitacion;
import habitaciones.HabitacionCompartida;
import habitaciones.HabitacionPrivada;
import habitaciones.TerapiaIntensiva;
import medicos.IMedico;
import medicos.MedicoFactory;
import pacientes.IRangoEtareo;
import pacientes.PacienteFactory;
import personas.Domicilio;
import prestaciones.Consulta;
import prestaciones.Internacion;
import prestaciones.Prestacion;
import prestaciones.PrestacionFactory;

public class Clinica {
	private static Clinica instancia = null;

	private String nombre;
	private Domicilio direccion;
	private String telefono;
	private String ciudad;
	
	private HashMap<Integer, IMedico> medicos = new HashMap<Integer, IMedico>();
	private HashMap<Integer, IRangoEtareo> pacientesHist = new HashMap<Integer, IRangoEtareo>();
	private TreeSet<Prestacion> historial = new TreeSet<Prestacion>();
	
	private IRangoEtareo salaPrivada = null;
	private ArrayList<IRangoEtareo> patio = new ArrayList<IRangoEtareo>();
	private LinkedList<IRangoEtareo> colaEspera = new LinkedList<IRangoEtareo>();
	
	private ArrayList<IRangoEtareo> enAtencion = new ArrayList<IRangoEtareo>();

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

	public ArrayList<IRangoEtareo> getEnAtencion() {
		return enAtencion;
	}

	public HashMap<Integer, IMedico> getMedicos() {
		return medicos;
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

		IRangoEtareo p = PacienteFactory.getInstance(rangoEtario, nombre, apellido, dni, historiaClinica);
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

		IRangoEtareo p = PacienteFactory.getInstance(rangoEtario, nombre, apellido, dni, telefono, domicilio, ciudad,
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
	public void reasignaEspera(IRangoEtareo p) {
		IRangoEtareo aux;
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
		IRangoEtareo aux = this.colaEspera.removeFirst();
		if (this.salaPrivada == aux)
			this.salaPrivada = null;
		else {
			this.patio.remove(aux);
		}
		this.enAtencion.add(aux);
	}

	/**
	 * un paciente hace una consulta
	 * @param p paciente con consulta
	 * @param matricula matricula del medico
	 * @param cantidad cantidad de consultas realizadas al medico
	 */
	public void agregaConsulta(IRangoEtareo p, int matricula, int cantidad) {
		IMedico medico=this.medicos.get(matricula);
		Consulta c = PrestacionFactory.getConsulta(medico, cantidad);
		p.getPrestaciones().add(c);
		this.historial.add(c);
	}

	/**
	 * interna a un paciente, debe buscar si hay alguna sala del tipo que espera libre
	 * @param p paciente
	 * @param tipo tipo de sala
	 * @param cantidad cantidad de dias
	 */
	public void agregaInternacion(IRangoEtareo p, String tipo, int cantidad) {
		Habitacion hab=null;
		//aca hay que buscar alguna habitacion del tipo que se quiere, y sino largar una excepcion
		Internacion i = PrestacionFactory.getInternacion(tipo, cantidad);
		p.getPrestaciones().add(i);
		this.historial.add(i);
	}

	@Override
	public String toString() {
		return "Clinica " + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", ciudad=" + ciudad;
	}

	public void imprimerMedicos() {
		Iterator it = this.medicos.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getValue().toString());
		}
	}

	public void imprimeHistoricoPacientes() {
		Iterator it = this.pacientesHist.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getValue().toString());
		}
	}

	public void imprimeCola() {
		if (this.colaEspera.isEmpty())
			System.out.println("No hay pacientes por ser antedidos");
		Iterator it = this.colaEspera.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	public void estadoSalaPrivada() {
		if (this.salaPrivada == null)
			System.out.println("La sala privada esta vacia");
		else
			System.out.println("En la sala de espera privada se encuentra " + this.salaPrivada.toString());
	}

	public void imprimePatio() {
		if (this.patio.isEmpty())
			System.out.println("No hay pacientes en el patio");
		else {
			Iterator it = this.patio.iterator();
			System.out.println("En el patio se encuentran");
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
		}

	}

	public void imprimeAtendidos() {
		if (this.enAtencion.isEmpty())
			System.out.println("No hay pacientes siendo atendidos");
		else {
			Iterator it = this.enAtencion.iterator();
			System.out.println("Los pacientes que se encuentran siendo atendidos son");
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
		}

	}

	public void actualizarHistorial(Factura f) {
		Iterator it = f.getPaciente().getPrestaciones().iterator();
	}

	public void setCostoHabitacionCompartida(double monto) {
		HabitacionCompartida.setCostoHabitacionCompartida(monto);
	}

	public void setCostoHabitacionPrivada(double monto) {
		HabitacionPrivada.setCostoHabitacionPrivada(monto);
	}

	public void setCostoTerapiaIntensiva(double monto) {
		TerapiaIntensiva.setCostoTerapiaIntensiva(monto);
	}

	public double getCostoHabitacionCompartida() {
		return HabitacionCompartida.getCostoHabitacionCompartida();
	}

	public double getCostoHabitacionPrivada() {
		return HabitacionPrivada.getCostoHabitacionPrivada();
	}

	public double getCostoTerapiaIntensiva() {
		return TerapiaIntensiva.getCostoTerapiaIntensiva();
	}

}
