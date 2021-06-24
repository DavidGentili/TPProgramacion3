package personas;

import java.io.Serializable;

/**
 * Es la clase base de las personas, toda representacion de una persona fisica
 * dentro del proyecto es una extension de esta clase
 *
 */

public class Persona implements Serializable {
	protected String nombre;
	protected String apellido;
	protected int dni;
	protected String telefono;
	protected Domicilio domicilio;
	protected String ciudad;

	/**
	 * crea una instancia de una persona con sus datos basicos <br>
	 * pre: el nombre y el apellido deben ser distintos de null y de vacio, el DNI
	 * debe ser un numero positivo
	 * 
	 * @param nombre   nombre de la persona
	 * @param apellido Apellido de la persona
	 * @param dni      Dni de la persona
	 * 
	 */
	public Persona(String nombre, String apellido, int dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = null;
		this.domicilio = null;
		this.ciudad = null;
	}

	/**
	 * crea una instancia de una persona con sus datos basicos <br>
	 * pre: el nombre y el apellido deben ser distintos de null y de vacio, el DNI
	 * debe ser un numero positivo
	 * 
	 * 
	 * @param nombre    nombre de la persona
	 * @param apellido  Apellido de la persona
	 * @param dni       Dni de la persona
	 * @param telefono  telefono de la persona
	 * @param domicilio domicilio de la persona
	 * @param ciudad    Ciudad de la persona
	 */
	public Persona(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
	}

	/**
	 * Retorna el telefono de la persona, en caso de no tener retorna null
	 * 
	 * @return retorna el telefono o null
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Nos permite cambiar el telefono de una persona<br>
	 * pre: el telefono debe ser una string distinto de vacio y debe tener solo
	 * caracteres numericos<br>
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Permite obtener el domicilio de una persona
	 * 
	 * @return domicilio de la persona
	 */
	public Domicilio getDomicilio() {
		return domicilio;
	}

	/**
	 * Permite modificar el domicilio de una persona<br>
	 * pre: el domicilio de ser valido
	 * 
	 * @param domicilio
	 */
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Nos permite obtener la ciudad de la persona
	 * 
	 * @return ciudad de la persona
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Nos permite modificar la ciudad de la persona<br>
	 * pre: la ciudad debe ser distinta de blanco<br>
	 * 
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * nos rotorna el nombre de la persona
	 * 
	 * @return nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Nos retorna el apellido de la persona
	 * 
	 * @return apellido de la persona
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * nos retorna el dni de la persona
	 * 
	 * @return dni de la persona
	 */
	public int getDni() {
		return dni;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

}
