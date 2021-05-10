package medicos;

import personas.Domicilio;

/**
 * 
 * Insteface con los metodos necesarios para que los elementos del patron
 * Decorator interactuen con su encapsulado
 *
 */
public interface IMedico {

	/**
	 * Retorna el Sueldo bruto del medico
	 * 
	 * @return
	 */
	double getSueldo();

	/**
	 * Retorna el nombre del medico
	 * 
	 * @return
	 */
	String getNombre();

	/**
	 * Retorna el apellido del medico
	 * 
	 * @return
	 */
	String getApellido();

	/**
	 * Retorna el DNI del medico
	 * 
	 * @return
	 */
	int getDni();

	/**
	 * Retorna la matricula del medico
	 * 
	 * @return
	 */
	int getMatricula();

	/**
	 * Retorna el telefono del medico
	 * 
	 * @return
	 */
	String getTelefono();

	/**
	 * Retorna el domicilio del medico
	 * 
	 * @return
	 */
	Domicilio getDomicilio();

	/**
	 * Retorna la ciudad del medico
	 * 
	 * @return
	 */
	String getCiudad();

	/**
	 * Retorna un string con la informacion del medico
	 * 
	 * @return
	 */
	String toString();

}
