package prestaciones;

public abstract class Prestacion implements IPrestacion {
	protected int cantidad;
	protected String nombreMedicoOSala;
	protected double valor;

	public Prestacion(int cantidad, String nombreMedicoOSala, double valor) {
		this.cantidad = cantidad;
		this.nombreMedicoOSala = nombreMedicoOSala;
		this.valor = valor;
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

}
