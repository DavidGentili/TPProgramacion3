package medicos;

import personas.Domicilio;

public interface IMedico {
	double getSueldo();

	String getNombre();

	String getApellido();

	int getDni();
	
	int getMatricula();

	String getTelefono();

	Domicilio getDomicilio();

	String getCiudad();

	String toString();

}
