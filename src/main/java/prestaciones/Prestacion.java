package prestaciones;

public class Prestacion implements Cloneable {

	protected String descripcion;
	protected int cantidad;
	protected double valor;
	protected double subtotal;

	public Prestacion(String descripcion, double valor, int cantidad, double subtotal) {
		this.descripcion = descripcion;
		this.valor = valor;
		this.cantidad = cantidad;
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

	@Override
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return o;
	}

}
