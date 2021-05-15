package clinica;

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
public class Factura implements Comparable {
	private static int numero = 0;
	private int nroFactura;
	private GregorianCalendar fecha;
	private Paciente paciente;
	private double importeTotal = 0;
	private ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();

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
		ArrayList<Prestacion> aux = paciente.getPretaciones();
		this.prestaciones = (ArrayList<Prestacion>) aux.clone();
		for (int i = 0; i < prestaciones.size(); i++) {
			prestaciones.set(i, (Prestacion) aux.get(i).clone());
		}
		this.muestraInformacion();
	}

	/**
	 * Muestra la factura realizada, y calcula el total de la factura
	 */
	public void muestraInformacion() {
		double acumulador = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(" dd'/'MM'/'YY");
		System.out.println("Nro:" + this.nroFactura + "	Fecha" + sdf.format(this.fecha.getTime()));
		System.out.println("|     Prestacion     |   Valor  |  Cantidad  |  Subtotal  |\n");
		Iterator it = prestaciones.iterator();
		while (it.hasNext()) {
			Prestacion prestacion = (Prestacion) it.next();
			acumulador += prestacion.getSubtotal();
			System.out.println(prestacion.toString());

		}
		System.out.println();
		System.out.println("Total = " + acumulador);
		if (this.importeTotal == 0 && acumulador != 0) {
			this.importeTotal = acumulador;
		}
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
	 * Retorna las prestaciones que se cobraron
	 * 
	 * @return prestaciones que se cobraron
	 */
	public Iterator<Prestacion> getPrestaciones() {
		return prestaciones.iterator();
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

	@Override
	public int compareTo(Object o) {
		Factura otra = (Factura) o;
		return this.fecha.compareTo(otra.fecha);
	}

}
