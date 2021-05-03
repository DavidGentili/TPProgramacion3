package medicos;

import personas.Domicilio;

public interface IMedico {

	public abstract double getHonorario();
	public abstract String getNombre();
	public abstract String getApellido();
	public abstract int getDNI();
	public abstract String getTelefono();
	public abstract Domicilio getDomicilio();
	public abstract void setDomicilio(Domicilio domicilio);
	public abstract String getCiudad();
}
