package habitaciones;

/**
 * Representa a las habitaciones compartidas, gestiona el costo minimo y calcula
 * el costo pordia
 *
 */

class HabitacionCompartida implements IHabitacion {

	private static double costoHabitacionCompartida = 0;

	protected HabitacionCompartida() {
		super();
	}

	/**
	 * Retorna el costo minimo de habitacion compartida
	 * 
	 * @return monto minimo de la habitacion
	 */
	public static double getCostoHabitacionCompartida() {
		return costoHabitacionCompartida;
	}

	/**
	 * Define el costo minimo de la habitacion compartida <br>
	 * pre:<br>
	 * el monto debe ser positivo
	 * 
	 * @param costoHabitacionCompartida costo minimo de la habitacion
	 */
	public static void setCostoHabitacionCompartida(double costoHabitacionCompartida) {
		HabitacionCompartida.costoHabitacionCompartida = costoHabitacionCompartida;
	}

	/**
	 * Cacula el costo de la habitacion compartida
	 */
	public double calculaCosto(int cantidadDeDias) {
		double costo = costoHabitacionCompartida;
		return costo + costo * cantidadDeDias;
	}

	/**
	 * retorna el costo minimo de la habitacion compartida
	 */
	@Override
	public double getCostoMinimo() {
		return costoHabitacionCompartida;
	}

	@Override
	public String toString() {
		return "habitacion compartida";
	}

}
