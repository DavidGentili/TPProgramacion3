package habitaciones;

import exceptions.TipoDeHabitacionIncorrectaException;

/**
 * Mediante el uso del patron Factory, se selecciona y se retorna una instancia
 * del tipo de habitacion
 *
 */

public class HabitacionFactory {

	/**
	 * Retorna una instancia del tipo de habitacion, si no encuentra el tipo propaga
	 * excepcion
	 * 
	 * @param tipo tipo de habitacion
	 * @return intancia de habitacion
	 * @throws TipoDeHabitacionIncorrectaException si no encuentra el tipo ingresado
	 */
	public static IHabitacion getInstance(String tipo) throws TipoDeHabitacionIncorrectaException {
		IHabitacion respuesta = null;
		if (tipo.equalsIgnoreCase("intensiva") || tipo.equalsIgnoreCase("terapia")
				|| tipo.equalsIgnoreCase("terapiaintensiva") || tipo.equalsIgnoreCase("terapia intensiva"))
			respuesta = new TerapiaIntensiva();
		else if (tipo.equalsIgnoreCase("compartida") || tipo.equalsIgnoreCase("habitacioncompartida")
				|| tipo.equalsIgnoreCase("habitacion compartida"))
			respuesta = new HabitacionCompartida();
		else if (tipo.equalsIgnoreCase("privada") || tipo.equalsIgnoreCase("habitacionprivada")
				|| tipo.equalsIgnoreCase("habitacion privada"))
			respuesta = new HabitacionPrivada();
		else
			throw new TipoDeHabitacionIncorrectaException("Tipo de habitacion no encontrada");
		return respuesta;
	}

}
