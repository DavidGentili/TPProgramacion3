package prestaciones;

import medicos.IMedico;

/**
 * 
 * Es una prestacion, especifica donde un medico atiende a un paciente, por eso
 * se almacena el medico que presto el servicio
 *
 */
public class Consulta extends Prestacion {

	protected IMedico medico;

	public Consulta(String descripcion, double valor, int cantidad, double subtotal, IMedico medico) {
		super(descripcion, valor, cantidad, subtotal);
		this.medico = medico;
	}

	/**
	 * Se regresa el medico que presto el servicio
	 * 
	 * @return el medico interviniente
	 */
	public IMedico getMedico() {
		return medico;
	}

}
