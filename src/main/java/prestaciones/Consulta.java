package prestaciones;

import medicos.IMedico;

public class Consulta extends Prestacion {

	protected IMedico medico;

	public Consulta(String descripcion, int cantidad, double valor, double subtotal, IMedico medico) {
		super(descripcion, cantidad, valor, subtotal);
		this.medico = medico;
	}

	public IMedico getMedico() {
		return medico;
	}

}
