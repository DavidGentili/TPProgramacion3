package habitaciones;

/**
 * Representa a las habitaciones privada, gestiona el costo minimo y calcula el
 * costo pordia
 *
 */

public class HabitacionPrivada implements IHabitacion {
	private static double costoHabitacionPrivada = 0;

	public HabitacionPrivada() {
		super();
	}

	/**
	 * Retorna el costo minimo de habitacion privada
	 * 
	 * @return monto minimo de la habitacion
	 */

	public static double getCostoHabitacionPrivada() {
		return costoHabitacionPrivada;
	}

	/**
	 * Define el costo minimo de la habitacion privada <br>
	 * pre:<br>
	 * el monto debe ser positivo
	 * 
	 * @param costoHabitacionCompartida costo minimo de la habitacion
	 */
	public static void setCostoHabitacionPrivada(double costoHabitacionPrivada) {
		HabitacionPrivada.costoHabitacionPrivada = costoHabitacionPrivada;
	}

	/**
	 * Cacula el costo de la habitacion privada
	 */
	public double calculaCosto(int cantidadDeDias) {
		double costo = costoHabitacionPrivada;
		if (cantidadDeDias == 1)
			costo *= 2;
		else {
			if (cantidadDeDias > 1 && cantidadDeDias < 6)
				costo += cantidadDeDias * costoHabitacionPrivada * 1.3;
			else {
				if (cantidadDeDias > 6)
					costo += cantidadDeDias * costoHabitacionPrivada * 2;
			}
		}
		return costo;
	}

	/**
	 * retorna el costo minimo de la habitacion privada
	 */
	@Override
	public double getCostoMinimo() {
		return costoHabitacionPrivada;
	}

	@Override
	public String toString() {
		return "habitacion privada";
	}

}
