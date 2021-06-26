package clinica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import pacientes.Paciente;
import prestaciones.Prestacion;

/**
 * Se encarga de generar una factura a un paciente, con las prestaciones
 * realizadas hasta ese momento
 * 
 *
 */
public class Factura implements Comparable, Serializable {
	private static int numero = 0;
	private int nroFactura;
	private GregorianCalendar fecha;
	private Paciente paciente;
	private double importeTotal = 0;
	private ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();

	/**
	 * Constructor vacio
	 */
	public Factura() {

	}

	/**
	 * retorna una factura con la fecha y el numero de factura, tambien clona las
	 * prestaciones del paciente, para reservarlas en la factura
	 * 
	 * @param fecha    fecha de la factura
	 * @param paciente paciente al que se le factura
	 */
	public Factura(GregorianCalendar fecha, Paciente paciente) {
		this.nroFactura = ++numero;
		this.fecha = fecha;
		this.paciente = paciente;
		ArrayList<Prestacion> aux = paciente.getPrestaciones();
		this.prestaciones = (ArrayList<Prestacion>) aux.clone();
		for (int i = 0; i < prestaciones.size(); i++) {
			prestaciones.set(i, (Prestacion) aux.get(i).clone());
		}
	}

	/**
	 * Retorna el numero de la factura
	 * 
	 * @return Numero de factura
	 */
	public int getNroFactura() {
		return nroFactura;
	}

	/**
	 * Muestra la factura realizada, y calcula el total de la factura
	 */
	public String muestraInformacion() {
		StringBuilder sb = new StringBuilder();
		double acumulador = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(" dd'/'MM'/'YY");
		sb.append("Nro:" + this.nroFactura + "	Fecha" + sdf.format(this.fecha.getTime()) + "\n");
		sb.append("|     Prestacion     |   Valor  |  Cantidad  |  Subtotal  |\n");
		Iterator it = prestaciones.iterator();
		while (it.hasNext()) {
			Prestacion prestacion = (Prestacion) it.next();
			acumulador += prestacion.getSubtotal();
			sb.append(prestacion.toString() + "\n");

		}
		sb.append("Total = " + acumulador);
		if (this.importeTotal == 0 && acumulador != 0) {
			this.importeTotal = acumulador;
		}
		return sb.toString();
	}

	/**
	 * Retorna el importe total de la factura
	 * 
	 * @return importe de la factura
	 */
	public double getImporteTotal() {
		return this.importeTotal;
	}

	/**
	 * Retorna en un Iterator las prestaciones que se cobraron
	 * 
	 * @return prestaciones que se cobraron
	 */
	public Iterator<Prestacion> getIteratorPrestaciones() {
		return prestaciones.iterator();
	}

	/**
	 * Retorna en un ArrayList las prestaciones de la factura
	 * 
	 * @return
	 */
	public ArrayList<Prestacion> getPrestaciones() {
		return this.prestaciones;
	}

	/**
	 * retorna la fecha de la factura
	 * 
	 * @return fecha de facturacion
	 */
	public GregorianCalendar getFecha() {
		return fecha;
	}

	/**
	 * Retorna el paciente al que se le hizo la factura
	 * 
	 * @return paciente al que se le facturo
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * Retorna el numero general de factura
	 * 
	 * @return Numero general de factura
	 */
	public static int getNumero() {
		return numero;
	}

	/**
	 * Establece el numero general de factura
	 * 
	 * @param numero Numero general de factura
	 */
	public static void setNumero(int numero) {
		Factura.numero = numero;
	}

	@Override
	public int compareTo(Object o) {
		Factura otra = (Factura) o;
		return this.fecha.compareTo(otra.fecha);
	}

	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public void setPrestaciones(ArrayList<Prestacion> prestaciones) {
		this.prestaciones = prestaciones;
	}

}
