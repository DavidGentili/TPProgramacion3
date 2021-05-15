package prestaciones;

/**
 * La clase se encargar de almacenar una prestacion, que puede ser una
 * internacion o una consulta clinica, reservando su descripcion, el valor base
 * y su valor total. Ademas almacena la cantidad de dias en caso de ser
 * internacion o la cantidad de consultas en caso de ser una consulta
 *
 */
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

	/**
	 * Retorna la descripcion de la prestacion, que puede ser el
	 * 
	 * @return la descripcion de la prestacion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Retorn la cantidad, si es una internacion equivale a los dias internado, si
	 * es una consulta medica equivale a la cantidad de consultas
	 * 
	 * @return la cantidad de dias o de consultas
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Retorna el valor base del tratamiento
	 * 
	 * @return el valor base de la prestacion
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Devuelve el total de la prestacion
	 * 
	 * @return el total de la prestacion
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * Retorna una cadena que describe la prestacion
	 */
	@Override
	public String toString() {
		String patron = " %-20s %10.1f %12d %12.1f";
		return String.format(patron, this.descripcion, this.valor, this.cantidad, this.subtotal);
	}

	/**
	 * Permite clonar el objeto prestacion
	 */
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
