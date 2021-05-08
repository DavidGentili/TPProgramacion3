package clinica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.ClinicaInexistenteExcepcion;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.PosgradoNoRegistradoExceptions;
import medicos.IMedico;
import medicos.MedicoFactory;
import pacientes.IRangoEtareo;
import pacientes.PacienteFactory;
import personas.Domicilio;

public class Clinica {
	private static Clinica instancia = null;

	private String nombre;
	private Domicilio direccion;
	private String telefono;
	private String ciudad;
	private IRangoEtareo salaPrivada = null;

	private HashMap<Integer, IMedico> medicos = new HashMap<Integer, IMedico>();
	private HashMap<Integer, IRangoEtareo> pacientesHist = new HashMap<Integer, IRangoEtareo>();
	// private ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
	private ArrayList<IRangoEtareo> colaEspera = new ArrayList<IRangoEtareo>();
	private ArrayList<IRangoEtareo> patio = new ArrayList<IRangoEtareo>();
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
			throw new ClinicaInexistenteExcepcion("La clinica no se ha inicializado");// Deberiamos propagar una
																						// excepcion
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

	public void atiendeSiguiente() {
		IRangoEtareo aux = this.colaEspera.remove(0);
		if (this.salaPrivada == aux)
			this.salaPrivada = null;
		else {
			this.patio.remove(aux);
		}
		this.enAtencion.add(aux);
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
		Iterator it = this.colaEspera.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	public void estadoSalaPrivada() {
		System.out.println("En la sala de espera privada se encuentra " + this.salaPrivada.toString());
	}

	public void imprimePatio() {
		Iterator it = this.patio.iterator();
		System.out.println("En el patio se encuentran");
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	public void imprimeAtendidos() {
		Iterator it = this.enAtencion.iterator();
		System.out.println("Los pacientes que se encuentran siendo atendidos son");
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}
