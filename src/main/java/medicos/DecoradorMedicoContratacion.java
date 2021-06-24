package medicos;

import java.io.Serializable;

import personas.Domicilio;

/**
 * 
 * Es la clase abstracta padre de los decoradores concretos de contratacion.
 * Utiliza como encapsulado un IMedico para la posterior implementacion del
 * patron decorator
 *
 */
public abstract class DecoradorMedicoContratacion implements IMedico, Serializable {

	/**
	 * representa un medico con su respectiva especialidad.
	 * 
	 * @aggregation composite
	 */
	protected IMedico encapsulado;

	/**
	 * Constructor vacio
	 */
	public DecoradorMedicoContratacion() {

	}

	/**
	 * 
	 * @param encapsulado un medico
	 */
	public DecoradorMedicoContratacion(IMedico encapsulado) {
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

	/**
	 * retorna el Encapsulado
	 * 
	 * @return Medico Encapsulado
	 */
	public IMedico getEncapsulado() {
		return encapsulado;
	}

	/**
	 * Define el medico Encapsulado
	 * 
	 * @param encapsulado Medico Encapsulado
	 */
	public void setEncapsulado(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}

}
