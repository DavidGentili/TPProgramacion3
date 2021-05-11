package prestaciones;

import java.util.GregorianCalendar;

public abstract class Prestacion implements IPrestacion, Comparable<Prestacion> {
	
	protected int cantidad;
	protected String nombreMedicoOSala;
	protected double valor;
	protected GregorianCalendar fecha;

	public Prestacion(int cantidad, String nombreMedicoOSala, double valor) {
		this.cantidad = cantidad;
		this.nombreMedicoOSala = nombreMedicoOSala;
		this.valor = valor;
	}
	
	public GregorianCalendar getFecha() {
		return fecha;
	}

	@Override
	public void setFecha(GregorianCalendar fecha) {
		this.fecha=fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getNombreMedicoOSala() {
		return nombreMedicoOSala;
	}

	public double getValor() {
		return valor;
	}

	public double getSubtotal() {
		return this.valor * this.cantidad;
	}

	@Override
	public String toString() {
		return nombreMedicoOSala + valor + cantidad + valor * cantidad;
	}

	@Override
	public int compareTo(Prestacion o) {
		return this.fecha.compareTo(o.fecha);
	}
	
	

}
