package medicos;

import personas.Domicilio;

/**
 * Es la clase abstracta padre de los decoradores concretos de Posgrados.
 * utiliza como encapsulado un DecoradorMedicoContratacion para el patron decorator
 *
 */

public abstract class DecoradorMedicoPosgrado implements IMedico {

	/**
	 * representa el medico con su contratacion indicada.
	 * 
	 * @aggregation composite
	 */
	protected DecoradorMedicoContratacion encapsulado;

	/**
	 * 
	 * @param encapsulado un medico con contratacion
	 */
	public DecoradorMedicoPosgrado(DecoradorMedicoContratacion encapsulado) {
		this.encapsulado = encapsulado;
	}

	/**
	 * Retorna el sueldo del medico encapsulado
	 */
	@Override
	public abstract double getSueldo();

	/**
	 * Retorna el nombre del medico encapsulado
	 */
	@Override
	public String getNombre() {
		return encapsulado.getNombre();
	}

	/**
	 * Retorna el apellido del medico encapsulado
	 */
	@Override
	public String getApellido() {
		return encapsulado.getApellido();
	}

	/**
	 * Retorna el DNI del medico encapsulado
	 */
	@Override
	public int getDni() {
		return encapsulado.getDni();
	}

	/**
	 * Retorna el telefono del medico encapsulado
	 */
	@Override
	public String getTelefono() {
		return encapsulado.getTelefono();
	}

	/**
	 * Retorna el domicilio del medico encapsulado
	 */
	@Override
	public Domicilio getDomicilio() {
		return encapsulado.getDomicilio();
	}

	/**
	 * Retorna la ciudad del medico encapsulado
	 */
	@Override
	public String getCiudad() {
		return encapsulado.getCiudad();
	}

	/**
	 * Retorna un string con la informacion del medico encapsulado
	 */
	@Override
	public String toString() {
		return encapsulado.toString();
	}

	@Override
	public int getMatricula() {
		// TODO Auto-generated method stub
		return encapsulado.getMatricula();
	}
}
