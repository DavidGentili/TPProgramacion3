package clinica;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.ClinicaInexistenteExcepcion;
import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.MedicoYaAgregadoException;
import exceptions.PosgradoNoRegistradoExceptions;
import medicos.IMedico;
import medicos.MedicoFactory;
import pacientes.Paciente;
import personas.Domicilio;

public class Clinica {

	private String nombre;
	private Domicilio direccion;
	private String telefono;
	private String ciudad;
	private static Clinica instancia = null;
	private HashMap<Integer, IMedico> medicos = new HashMap<Integer, IMedico>(); 
	private HashMap<Integer, Paciente> pacientesHist = new HashMap<Integer, Paciente>();
	private ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
	private ArrayList<Paciente> colaEspera = new ArrayList<Paciente>();

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
			throw new ClinicaInexistenteExcepcion("La clinica no se ha inicializado");// Deberiamos propagar una excepcion
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
	
	public void agregaMedico(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad, int matricula, String especialidad, String posgrado, String contratacion) throws MedicoYaAgregadoException, ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions, EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions {
		IMedico medico;
		if(!medicos.containsKey(matricula)) {
			medico=MedicoFactory.getInstancia(nombre, apellido, dni, telefono, domicilio, ciudad, matricula, especialidad, posgrado, contratacion);
			medicos.put(matricula, medico);
		}
		else
			throw new MedicoYaAgregadoException("El medico que desea agregar ya existe");
	}
	
	public void agregaPaciente() {
		
	}

}
