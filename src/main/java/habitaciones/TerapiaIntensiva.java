package habitaciones;

/**
 * Representa a las terapia intensiva, gestiona el costo minimo y calcula el
 * costo pordia
 *
 */

public class TerapiaIntensiva implements IHabitacion {

	private static double costoTerapiaIntensiva = 0;

	public TerapiaIntensiva() {
		super();
	}

	/**
	 * Retorna el costo minimo de terapia intensiva
	 * 
	 * @return monto minimo de la terapia
	 */
	public static double getCostoTerapiaIntensiva() {
		return costoTerapiaIntensiva;
	}

	/**
	 * Define el costo minimo de la terapia intensiva <br>
	 * pre:<br>
	 * el monto debe ser positivo
	 * 
	 * @param costoHabitacionCompartida costo minimo de la terapia
	 */
	public static void setCostoTerapiaIntensiva(double costoTerapiaIntensiva) {
		TerapiaIntensiva.costoTerapiaIntensiva = costoTerapiaIntensiva;
	}

	/**
	 * Cacula el costo de la terapia intensiva
	 */
	public double calculaCosto(int cantidadDeDias) {
		double costo = costoTerapiaIntensiva;
		return Math.pow(costo, cantidadDeDias);
	}

	/**
	 * retorna el costo minimo de la terapia intensiva
	 */
	@Override
	public double getCostoMinimo() {
		return costoTerapiaIntensiva;
	}

	@Override
	public String toString() {
		return "la sala de terapia intensiva";
	}

}
