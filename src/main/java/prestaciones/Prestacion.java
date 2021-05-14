package prestaciones;

import java.util.GregorianCalendar;

public class Prestacion {

	protected String descripcion;
	protected int cantidad;
	protected double valor;
	protected double subtotal;

	public Prestacion(String descripcion, int cantidad, double valor, double subtotal) {
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.valor = valor;
		this.subtotal = subtotal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getValor() {
		return valor;
	}

	public double getSubtotal() {
		return subtotal;
	}

	@Override
	public String toString() {
		return descripcion + " " + valor + " " + cantidad + " " + subtotal;
	}

}
