package prestaciones;

import medicos.IMedico;

public class Consulta extends Prestacion {

	private IMedico m;
	
	//getSueldo es el getHonorario?
		public Consulta(int cantidad, IMedico m) {
			super(cantidad, m.getNombre()+" "+m.getApellido(), m.getSueldo());
			this.m = m;
		}
		@Override
		public double calcularSubtotal() {
			return this.m.getSueldo()*this.cantidad;
		}
}
