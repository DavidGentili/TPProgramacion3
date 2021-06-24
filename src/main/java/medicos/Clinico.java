package medicos;

import java.io.Serializable;

import personas.Domicilio;

/**
 * 
 * Representa un medico concreto con la especialidad medico clinico.
 *
 */
public class Clinico extends Medico implements Serializable{
	
	/**
	 * Constructor vacio
	 */
	public Clinico(){
		
	}

	/**
	 * Utilizando los mimso parametros que su padre, instancia un medico concreto con la especialidad Clinica
	 * @param nombre Nombre del medico
	 * @param apellido Apellido del medico
	 * @param dni Dni del medico
	 * @param matricula Matricula del medico
	 */
	public Clinico(String nombre, String apellido, int dni, int matricula) {
		super(nombre, apellido, dni, matricula);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * Utilizando los mimso parametros que su padre, instancia un medico concreto con la especialidad Clinica
	 * @param nombre Nombre del medico
	 * @param apellido Apellido del medico
	 * @param dni Dni del medico
	 * @param telefono telefono del medico
	 * @param domicilio Domicilio del medico
	 * @param ciudad Ciudad del medico
	 * @param matricula Matricula del medico
	 */
	public Clinico(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad,
			int matricula) {
		super(nombre, apellido, dni, telefono, domicilio, ciudad, matricula);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna el sueldo del medico basico mas el 5%
	 */
	@Override
	public double getSueldo() {
		return super.getSueldo() * 1.05;
	}

	/**
	 * Se encarga de retornar un string con los datos del medico basico,
	 * adicionando que es un clinico
	 */
	@Override
	public String toString() {
		return super.toString() + " Especialidad: Clinico";
	}

}
