package medicos;

import personas.Domicilio;

public interface IMedico {
	double getSueldo();

	String getNombre();

	String getApellido();

	int getDni();

	String getTelefono();

	Domicilio getDomicilio();

	String getCiudad();

}
