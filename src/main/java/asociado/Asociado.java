package asociado;

import personas.Domicilio;
import personas.Persona;

public class Asociado extends Persona {

	/**
	 * Constructor vacio
	 */
	public Asociado() {
	}

	/**
	 * Instancia una asociado con todos sus valores
	 * 
	 * @param nombre    Nombre del asociado
	 * @param apellido  Apellido del asociado
	 * @param dni       Dni del asociado
	 * @param telefono  Telefono del asociado
	 * @param domicilio Domicilio del asociado
	 */
	public Asociado(String nombre, String apellido, int dni, String telefono, Domicilio domicilio) {
		super(nombre, apellido, dni, telefono, domicilio);
	}

	public void SolicitarTraslado() {

	}

	public void SolicitarAtencionADomicilio() {

	}

	/**
	 * Se utiliza el mismo criterio que para la persona, si dos asociados son la
	 * misma persona, entonces son el mismo asociado
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Se utiliza el mismo criterio que para la persona, si dos asociados son la
	 * misma persona, entonces son el mismo asociado
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
