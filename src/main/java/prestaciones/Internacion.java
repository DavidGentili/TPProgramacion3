package prestaciones;

import habitaciones.Habitacion;

public class Internacion extends Prestacion {
	
	private Habitacion hab;
	
	/**
	 * Constructor, ya almacera en el atributo valor segun la cantidad de dias internados
	 * @param cantidad
	 * @param hab
	 */
	public Internacion(int cantidad, Habitacion hab) {  
		super(cantidad,hab.getCosto(cantidad));
		this.hab = hab;
	}

	@Override
	public String toString() {
		return "La internacion fue de " + this.cantidad + " dias en una"+ this.hab.toString() +"  con un valor de " +this.valor;
	}

}
