package clinica;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import exceptions.ClinicaInexistenteExcepcion;
import pacientes.Paciente;
import prestaciones.Prestacion;

public class Factura {
	private static int numero = 0;
	private int nroFactura;
	private GregorianCalendar fecha;
	private Paciente paciente;
	private double importeTotal = 0;
	private ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();

	public Factura(GregorianCalendar fecha, Paciente paciente) {
		this.nroFactura = ++numero;
		this.fecha = fecha;
		this.paciente = paciente;

		this.muestraInformacion();
	}

	public void muestraInformacion() {
		System.out.println("Nro:" + this.nroFactura + "Fecha" + this.fecha);
		System.out.println("|  Prestacion  |   Valor  |  Cantidad  |  Subtotal  |\n");
		Iterator it = prestaciones.iterator();
		while (it.hasNext()) {
			Prestacion prestacion= (Prestacion) it.next();
			this.importeTotal+=prestacion.getSubtotal();
			System.out.println(prestacion.toString());
			
		}
		System.out.println("Total = " + this.importeTotal);
	}

	public double getImporteTotal() {
		return this.importeTotal;
	}

	public void addPrestacion(Prestacion prestacion) {
		this.prestaciones.add(prestacion);
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

}
