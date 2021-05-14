package prestaciones;

import java.util.GregorianCalendar;

public abstract class Prestacion implements IPrestacion, Comparable<Prestacion> {
	
	protected int cantidad;
	protected double valor;
	protected GregorianCalendar fecha;

	/**
	 * La prestacion ya contendra el valor segun la cantidad de dias
	 * @param cantidad
	 * @param valor
	 */
	public Prestacion(int cantidad, double valor) {
		this.cantidad = cantidad;
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

	public double getValor() {
		return valor;
	}
	
	@Override
	public double calcularSubtotal() {
		return this.valor;
	}

	@Override
	public abstract String toString(); 

	@Override
	public int compareTo(Prestacion o) {
		return this.fecha.compareTo(o.fecha);
	}
	
	

}
