package personas;

import exceptions.DomicilioInvalido;

/**
 * Es una clase que se utiliza para conceptualizar un domicilio donde vive una
 * persona
 * 
 *
 */
public class Domicilio {
	private String calle;
	private int numero;

	public Domicilio(String calle, int numero) throws DomicilioInvalido {
		super();
		if (!calle.isBlank())
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

}
