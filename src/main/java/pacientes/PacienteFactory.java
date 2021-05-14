package pacientes;

import exceptions.TipoDePacienteIncorrectoException;
import personas.Domicilio;

/**
 * La clase se encarga de crear con metodos estaticos y devolver la instancia
 * solicitada de un paciente segun su rango etareo con el patron Factory
 * 
 *
 */
public class PacienteFactory {
	public PacienteFactory() {
	}

	/**
	 * devuelve una instancia del rango etare solicitado o lanza una excepcion
	 * 
	 * @param tipo            representa el rango etareo del paciente
	 * @param nombre          nombre del paciente
	 * @param apellido        apellido del paciente
	 * @param dni             dni del paciente
	 * @param telefono        telefono del paciente
	 * @param domicilio       domicilio del paciente
	 * @param ciudad          ciudad del paciente
	 * @param historiaClinica numero de historia clinica del paciente
	 * @return una instancia del paciente del rango etareo correspondiente
	 * @throws TipoDePacienteIncorrectoException si el rango etareo solicitado no se
	 *                                           encuentra
	 */
	public static Paciente getInstance(String tipo, String nombre, String apellido, int dni, String telefono,
			Domicilio domicilio, String ciudad, int historiaClinica) throws TipoDePacienteIncorrectoException {
		Paciente respuesta = null;
		if (tipo.equalsIgnoreCase("ninio") || tipo.equalsIgnoreCase("niño"))
			respuesta = new Ninio(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
		else if (tipo.equalsIgnoreCase("joven"))
			respuesta = new Joven(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
		else if (tipo.equalsIgnoreCase("mayor"))
			respuesta = new Mayor(nombre, apellido, dni, telefono, domicilio, ciudad, historiaClinica);
		else
			throw new TipoDePacienteIncorrectoException("Rango etareo ingresado incorrecto");
		return respuesta;
	}

	/**
	 * devuelve una instancia del rango etare solicitado o lanza una excepcion
	 * 
	 * @param tipo            representa el rango etareo del paciente
	 * @param nombre          nombre del paciente
	 * @param apellido        apellido del paciente
	 * @param dni             dni del paciente
	 * @param historiaClinica numero de historia clinica del paciente
	 * @return una instancia del paciente del rango etareo correspondiente
	 * @throws TipoDePacienteIncorrectoException si el rango etareo solicitado no se
	 *                                           encuentra
	 */
	public static Paciente getInstance(String tipo, String nombre, String apellido, int dni, int historiaClinica)
			throws TipoDePacienteIncorrectoException {
		Paciente respuesta = null;
		if (tipo.equalsIgnoreCase("ninio") || tipo.equalsIgnoreCase("niño"))
			respuesta = new Ninio(nombre, apellido, dni, historiaClinica);
		else if (tipo.equalsIgnoreCase("joven"))
			respuesta = new Joven(nombre, apellido, dni, historiaClinica);
		else if (tipo.equalsIgnoreCase("mayor"))
			respuesta = new Mayor(nombre, apellido, dni, historiaClinica);
		else
			throw new TipoDePacienteIncorrectoException("Rango etareo ingresado incorrecto");
		return respuesta;
	}

}
