package habitaciones;

import exceptions.TipoDeHabitacionIncorrectaException;
import prestaciones.Prestacion;

/**
 * 
 * Es una clase que se encarga de gestionar las internaciones, permitiendo
 * obtener una prestacion por internacion de manera adecuada
 *
 */

public class Internacion {

	public static Internacion instancia = null;

	private Internacion() {

	}

	/**
	 * Se utiliza el patron singleton para instanciar la clase
	 * 
	 * @return una unica instancia de la clase Internacion
	 */
	public static Internacion getInstancia() {
		if (instancia == null)
			instancia = new Internacion();
		return instancia;
	}

	/**
	 * Retorna una prestacion de una internacion, segun el tipo de habitacion y la
	 * cantidad de dias indicados, en caso de no ser posible propaga una excepcion
	 * 
	 * @param cantDias cantidad de dias de internacion
	 * @param tipo     el tipo de habitacion en al que se interno el paciente
	 * @return Una prestacion, con el tipo, y el monto adecuado
	 * @throws TipoDeHabitacionIncorrectaException si no se encuentra el tipo de
	 *                                             habitacion indicado
	 */
	public Prestacion getPrestacion(int cantDias, String tipo) throws TipoDeHabitacionIncorrectaException {
		IHabitacion habitacion = this.gethabitacion(tipo);
		Prestacion prestacion = new Prestacion(habitacion.toString(), habitacion.getCostoMinimo(), cantDias,
				habitacion.calculaCosto(cantDias));
		return prestacion;
	}

	/**
	 * Retorna una instancia de la habitacion indicada, de no encontrar el tipo,
	 * retorna una excepcion
	 * 
	 * @param tipo tipo de habitacion
	 * @return instancia de habitacion
	 * @throws TipoDeHabitacionIncorrectaException si no se encuentra el tipo de
	 *                                             habitacion indicado
	 */
	private static IHabitacion gethabitacion(String tipo) throws TipoDeHabitacionIncorrectaException {
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

	/**
	 * Determina el costo de la habitacion compartida <br>
	 * pre:<br>
	 * El monto debe ser positivo
	 * 
	 * @param monto monto basico de la habitacion
	 */
	public static void setCostoHabitacionCompartida(double monto) {
		HabitacionCompartida.setCostoHabitacionCompartida(monto);
	}

	/**
	 * Determina el costo de la habitacion privada <br>
	 * pre:<br>
	 * El monto debe ser positivo
	 * 
	 * @param monto monto basico de la habitacion
	 */
	public static void setCostoHabitacionPrivada(double monto) {
		HabitacionPrivada.setCostoHabitacionPrivada(monto);
	}

	/**
	 * Determina el costo de la terapia intensiva <br>
	 * pre:<br>
	 * El monto debe ser positivo
	 * 
	 * @param monto monto basico de la terapia
	 */
	public static void setCostoTerapiaIntensiva(double monto) {
		TerapiaIntensiva.setCostoTerapiaIntensiva(monto);
	}

	/**
	 * regresa el costo basico de la habitacion compartida
	 * 
	 * @return costo basico de la habitacion
	 */
	public static double getCostoHabitacionCompartida() {
		return HabitacionCompartida.getCostoHabitacionCompartida();
	}

	/**
	 * regresa el costo basico de la habitacion privada
	 * 
	 * @return costo basico de la habitacion
	 */
	public static double getCostoHabitacionPrivada() {
		return HabitacionPrivada.getCostoHabitacionPrivada();
	}

	/**
	 * regresa el costo basico de la terapia intensiva
	 * 
	 * @return costo basico de la terapia
	 */
	public static double getCostoTerapiaIntensiva() {
		return TerapiaIntensiva.getCostoTerapiaIntensiva();
	}

}
