package prestaciones;

import medicos.IMedico;

public class Consulta extends Prestacion {

	private IMedico m;

	/**
	 * Constructor, ya almacera en el atributo valor segun la cantidad de consultas
	 * @param cantidad
	 * @param m
	 */
	public Consulta(int cantidad, IMedico m) {
		super(cantidad, m.getSueldo() * cantidad);
		this.m = m;
	}

	
	/**
	 * Retorna lo que cobra el medico, ya habiendo calculado el 20% de aumento
	 * @param cantidad
	 */
	@Override
	public double calcularSubtotal() {
		return super.calcularSubtotal() * 1.2;
	}

	@Override
	public String toString() {
		return "El paciente se atendio con " + m.getNombre() + " " + m.getApellido() + " unas" + this.cantidad
				+ " veces";
	}

}
