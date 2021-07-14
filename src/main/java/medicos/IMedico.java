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
	 * @return sueldo del medico
	 */
	double getSueldo();

	/**
	 * Retorna el nombre del medico
	 * 
	 * @return nombre del medico
	 */
	String getNombre();

	/**
	 * Retorna el apellido del medico
	 * 
	 * @return apellido del medico
	 */
	String getApellido();

	/**
	 * Retorna el DNI del medico
	 * 
	 * @return dni del medico
	 */
	int getDni();

	/**
	 * Retorna la matricula del medico
	 * 
	 * @return matricula del medico
	 */
	int getMatricula();

	/**
	 * Retorna el telefono del medico
	 * 
	 * @return telefono del medico
	 */
	String getTelefono();

	/**
	 * Retorna el domicilio del medico
	 * 
	 * @return domicilio del medico
	 */
	Domicilio getDomicilio();

	/**
	 * Retorna la ciudad del medico
	 * 
	 * @return ciudad del medico
	 */
	String getCiudad();

	/**
	 * Retorna un string con la informacion del medico
	 * 
	 * @return descripcion del medico
	 */
	String toString();

	/**
	 * Retorna la especialidad del medico
	 * 
	 * @return Especialidad del medico
	 */
	String getEspecialidad();

	/**
	 * Retorna la contratacion del medico
	 * 
	 * @return Contratacion del medico
	 */
	String getContratacion();

	/**
	 * Retorna el posgrado del medico
	 * 
	 * @return Posgrado del medico
	 */
	String getPosgrado();

}
