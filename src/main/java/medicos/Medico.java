package medicos;

import java.io.Serializable;

import personas.Domicilio;
import personas.Persona;

/**
 * 
 * Representa un medico generico, sin ningun tipo de especialidad ni estudio de
 * posgrado, ni una contratacion especifica, extiende de Persona e implementa la
 * interface IMedico
 *
 */
public class Medico extends Persona implements IMedico, Serializable {

	protected int matricula;
	protected static double sueldoBasico;

	/**
	 * Constructor vacio
	 */
	public Medico() {

	}

	/**
	 * se retorna un medico con sus datos totales para su creacion, un nombre, un
	 * apellido, un DNI un telefono de contacto un domicilio, y una ciudad.<br>
	 * pre: <br>
	 * El dni debe ser un entero positivo;<br>
	 * El nombre debe ser distinto de null y no debe ser un String vacio;<br>
	 * El apellido debe ser distinto de null y no debe ser un String vacio;<br>
	 * El telefono debe ser distinto de null y no debe ser un String vacio;<br>
	 * La ciudad debe ser distinta de null y no debe ser un String vacio;<br>
	 * La matricula debe ser un entero positivo;<br>
	 * pos: <br>
	 * Se retornara un tipo Medico ya instanciado
	 * 
	 * @param nombre    nombre del medico
	 * @param apellido  apellido del medico
	 * @param dni       dni del medico
	 * @param telefono  telefono del medico
	 * @param domicilio domicilio del medico
	 * @param ciudad    ciudad del medico
	 * @param matricula matricula del medico
	 */
	public Medico(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int matricula) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad);
		this.matricula = matricula;
	}

	/**
	 * * se retorna un medico con sus datos minimos para su creacion, un nombre, un
	 * apellido y un DNI, y una matricula<br>
	 * pre: <br>
	 * El dni debe ser un entero positivo;<br>
	 * El nombre debe ser distinto de null y no debe ser un String vacio;<br>
	 * El apellido debe ser distinto de null y no debe ser un String vacio;<br>
	 * La matricula debe ser un entero positivo;<br>
	 * pos: <br>
	 * Se retornara un tipo Medico ya instanciado
	 * 
	 * @param nombre    nombre del medico
	 * @param apellido  apellido del medico
	 * @param dni       dni del medico
	 * @param matricula matricula del medico
	 */
	public Medico(String nombre, String apellido, int dni, int matricula) {
		super(nombre, apellido, dni);
		this.matricula = matricula;
	}

	/**
	 * Se retorna un String con la informacion principal de la persona y su
	 * matricula
	 */
	@Override
	public String toString() {
		return super.toString() + " Nro de Matricula: " + matricula;
	}

	/**
	 * Se obtiene el sueldo basico de un medico
	 */
	@Override
	public double getSueldo() {
		return Medico.sueldoBasico;
	}

	/**
	 * Se adopta el valor ingresado para el sueldoBasico en caso de ser positivo,
	 * caso contrario se propaga una excepcion<br>
	 * pre:<br>
	 * El sueldo basico no puede ser negativo
	 * 
	 * @param sueldoBasico el sueldo basico de todo medico
	 */
	public static void setSueldoBasico(double sueldoBasico) {
		Medico.sueldoBasico = sueldoBasico;
	}

	/**
	 * Se regresa el valor del sueldo basico de un medico
	 * 
	 * @return valor del sueldo basico de un medico
	 */
	public static double getSueldoBasico() {
		return Medico.sueldoBasico;
	}

	/**
	 * Se regresa el la matricula del medico
	 */
	@Override
	public int getMatricula() {
		return this.matricula;
	}

	/**
	 * Se define la matricula del medico
	 * 
	 * @param matricula Matricula del medico
	 */
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

}
