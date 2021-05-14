package pacientes;

import exceptions.TipoDePacienteIncorrectoException;
import personas.Domicilio;

public class PacienteFactory {
	public PacienteFactory() {
	}

	public static Paciente getInstance(String tipo, String nombre, String apellido, int dni, String telefono,
			Domicilio domicilio, String ciudad, int historiaClinica) throws TipoDePacienteIncorrectoException{
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

	public static Paciente getInstance(String tipo, String nombre, String apellido, int dni, int historiaClinica) throws TipoDePacienteIncorrectoException {
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
