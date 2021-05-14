package habitaciones;

/**
 * Interface que describe el comportamiento de las habitaciones de internacion
 *
 * 
 */
public interface IHabitacion {
	/**
	 * Las habitaciones podran escribir su tipo
	 * 
	 * @return el tipo de habitacion
	 */
	String toString();

	/**
	 * retorna el costo de la internacion segun la cantidad de dias internados
	 * 
	 * @param cantDias cantidad de dias internados
	 * @return el costo total de internacion
	 */
	double calculaCosto(int cantDias);

	/**
	 * regresa el costo base del tipo de habitacion
	 * 
	 * @return costo minimo de la habitacion
	 */
	double getCostoMinimo();
}
