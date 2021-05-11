package clinica;

import java.util.GregorianCalendar;
import java.util.Iterator;

import exceptions.ClinicaInexistenteExcepcion;
import pacientes.Paciente;
import prestaciones.IPrestacion;

public class Factura {
	private static int numero=0;
	private int nroFactura;
	private GregorianCalendar fecha;
	private Paciente paciente;
	private double importeTotal=0;
	
	public Factura(GregorianCalendar fecha, Paciente paciente) throws ClinicaInexistenteExcepcion {
		this.nroFactura=++numero;
		this.fecha = fecha;
		this.paciente = paciente;
		this.muestraInformacion();
	}

	public void muestraInformacion() throws ClinicaInexistenteExcepcion {
		System.out.println("|  Prestacion  |   Valor  |  Cantidad  |  Subtotal  |\n");
		Iterator<IPrestacion> it = this.paciente.getPrestaciones().iterator();
		while (it.hasNext()) {
			this.importeTotal+=it.next().calcularSubtotal();
			System.out.println(it.next().toString());
		}
		System.out.println("Total = "+this.importeTotal);
		Clinica.getInstancia().actualizarHistorial(this);;
	}
	public double getImporteTotal() {
		return this.importeTotal;
	}
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	

}
