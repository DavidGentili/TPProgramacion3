package prestaciones;

import medicos.IMedico;

public class Consulta extends Prestacion {

	protected IMedico medico;

	public Consulta(String descripcion, double valor, int cantidad,double subtotal, IMedico medico) {
		super(descripcion, valor, cantidad, subtotal);
		this.medico = medico;
	}

	public IMedico getMedico() {
		return medico;
	}

}
