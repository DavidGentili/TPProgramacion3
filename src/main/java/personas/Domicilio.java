package personas;

import java.io.Serializable;

import exceptions.DomicilioInvalido;

/**
 * Es una clase que se utiliza para conceptualizar un domicilio donde vive una
 * persona
 * 
 *
 */
public class Domicilio implements Serializable {
	private String calle;
	private int numero;

	/**
	 * Constructor vacio
	 */
	public Domicilio() {

	}

	/**
	 * Se encarga se crear una instancia de un domicilio
	 * 
	 * @param calle  calle del domicilio
	 * @param numero altura del domicilio
	 * @throws DomicilioInvalido si el numero es negativo o la calle es un string
	 *                           vacio o nulo
	 */
	public Domicilio(String calle, int numero) throws DomicilioInvalido {
		super();
		if (calle != null && !calle.isBlank() && !calle.isEmpty())
			this.calle = calle;
		else
			throw new DomicilioInvalido("La calle debe ser distinta de blanco");
		if (numero > 0)
			this.numero = numero;
		else
			throw new DomicilioInvalido("el numero debe ser un entero positivo");
	}

	/**
	 * Regresa la calle del domicilio
	 * 
	 * @return calle del domicilio
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Regresa altura del domicilio
	 * 
	 * @return altura del domicilio
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Retorna un string con la descripcion de la ubicacion del domicilio
	 */
	@Override
	public String toString() {
		return calle + " " + numero;
	}

	/**
	 * Asigna una calle al domicilio
	 * 
	 * @param calle Calle del domicilio
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Asigna una altura al domicilio
	 * 
	 * @param numero Numero de la altura del domicilio
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

}
