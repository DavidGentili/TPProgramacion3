package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import asociado.Asociado;
import clinica.Ambulancia;
import clinica.Factura;
import medicos.IMedico;
import pacientes.Paciente;
import personas.Domicilio;

public class ClinicaDTO implements Serializable {

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
	private HashMap<Integer, Asociado> asociados = new HashMap<Integer, Asociado>();

	private double SueldoBasicoMedico;
	private double CostoBasicoHabitacionPrivada;
	private double CostoBasicohabitacionCompartida;
	private double CostoBasicoTerapiaIntensiva;
	private int NumeroTotalDeFacturas;

	public ClinicaDTO() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Domicilio getDireccion() {
		return direccion;
	}

	public void setDireccion(Domicilio direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public HashMap<Integer, IMedico> getMedicos() {
		return medicos;
	}

	public void setMedicos(HashMap<Integer, IMedico> medicos) {
		this.medicos = medicos;
	}

	public HashMap<Integer, Paciente> getPacientesHist() {
		return pacientesHist;
	}

	public void setPacientesHist(HashMap<Integer, Paciente> pacientesHist) {
		this.pacientesHist = pacientesHist;
	}

	public TreeSet<Factura> getHistorial() {
		return historial;
	}

	public void setHistorial(TreeSet<Factura> historial) {
		this.historial = historial;
	}

	public Paciente getSalaPrivada() {
		return salaPrivada;
	}

	public void setSalaPrivada(Paciente salaPrivada) {
		this.salaPrivada = salaPrivada;
	}

	public ArrayList<Paciente> getPatio() {
		return patio;
	}

	public void setPatio(ArrayList<Paciente> patio) {
		this.patio = patio;
	}

	public LinkedList<Paciente> getColaEspera() {
		return colaEspera;
	}

	public void setColaEspera(LinkedList<Paciente> colaEspera) {
		this.colaEspera = colaEspera;
	}

	public ArrayList<Paciente> getEnAtencion() {
		return enAtencion;
	}

	public void setEnAtencion(ArrayList<Paciente> enAtencion) {
		this.enAtencion = enAtencion;
	}

	public double getSueldoBasicoMedico() {
		return SueldoBasicoMedico;
	}

	public void setSueldoBasicoMedico(double sueldoBasicoMedico) {
		SueldoBasicoMedico = sueldoBasicoMedico;
	}

	public double getCostoBasicoHabitacionPrivada() {
		return CostoBasicoHabitacionPrivada;
	}

	public void setCostoBasicoHabitacionPrivada(double costoBasicoHabitacionPrivada) {
		CostoBasicoHabitacionPrivada = costoBasicoHabitacionPrivada;
	}

	public double getCostoBasicohabitacionCompartida() {
		return CostoBasicohabitacionCompartida;
	}

	public void setCostoBasicohabitacionCompartida(double costoBasicohabitacionCompartida) {
		CostoBasicohabitacionCompartida = costoBasicohabitacionCompartida;
	}

	public double getCostoBasicoTerapiaIntensiva() {
		return CostoBasicoTerapiaIntensiva;
	}

	public void setCostoBasicoTerapiaIntensiva(double costoBasicoTerapiaIntensiva) {
		CostoBasicoTerapiaIntensiva = costoBasicoTerapiaIntensiva;
	}

	public int getNumeroTotalDeFacturas() {
		return NumeroTotalDeFacturas;
	}

	public void setNumeroTotalDeFacturas(int numeroTotalDeFacturas) {
		NumeroTotalDeFacturas = numeroTotalDeFacturas;
	}
	
	public HashMap<Integer, Asociado> getAsociados() {
		return asociados;
	}

	public void setAsociados(HashMap<Integer, Asociado> asociados) {
		this.asociados = asociados;
	}

}
