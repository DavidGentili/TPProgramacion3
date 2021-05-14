package habitaciones;

import exceptions.TipoDeHabitacionIncorrectaException;
import prestaciones.Prestacion;

public class Internacion {

	public static Internacion instancia = null;

	private Internacion() {

	}

	public static Internacion getInstancia() {
		if (instancia == null)
			instancia = new Internacion();
		return instancia;
	}

	public Prestacion getPrestacion(int cantDias, String tipo) throws TipoDeHabitacionIncorrectaException {
		IHabitacion habitacion = this.gethabitacion(tipo);
		Prestacion prestacion = new Prestacion(habitacion.toString(), habitacion.getCostoMinimo(), cantDias,
				habitacion.calculaCosto(cantDias));
		return prestacion;
	}

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

	public static void setCostoHabitacionCompartida(double monto) {
		HabitacionCompartida.setCostoHabitacionCompartida(monto);
	}

	public static void setCostoHabitacionPrivada(double monto) {
		HabitacionPrivada.setCostoHabitacionPrivada(monto);
	}

	public static void setCostoTerapiaIntensiva(double monto) {
		TerapiaIntensiva.setCostoTerapiaIntensiva(monto);
	}

	public static double getCostoHabitacionCompartida() {
		return HabitacionCompartida.getCostoHabitacionCompartida();
	}

	public static double getCostoHabitacionPrivada() {
		return HabitacionPrivada.getCostoHabitacionPrivada();
	}

	public static double getCostoTerapiaIntensiva() {
		return TerapiaIntensiva.getCostoTerapiaIntensiva();
	}

}
