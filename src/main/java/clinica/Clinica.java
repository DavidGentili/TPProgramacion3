package clinica;

import java.util.ArrayList;
import java.util.HashMap;

import medicos.IMedico;
import pacientes.Paciente;
import personas.Domicilio;

public class Clinica {

	private String nombre;
	private Domicilio direccion;
	private String telefono;
	private String ciudad;
	private static Clinica instancia = null;
	private HashMap<Integer, IMedico> medicos = new HashMap<Integer, IMedico>();
	private HashMap<Integer, Paciente> pacientes = new HashMap<Integer, Paciente>();
	private ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();

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

	public static Clinica getInstancia() {
		if (instancia != null)
			return instancia;
		else
			return null; // Deberiamos propagar una excepcion
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

}
