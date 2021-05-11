package habitaciones;

public class TerapiaIntensiva extends Habitacion {

	private static double costoTerapiaIntensiva = 0;

	public TerapiaIntensiva(int identificador, int limite) {
		super(identificador, limite);
	}

	public static double getCostoTerapiaIntensiva() {
		return costoTerapiaIntensiva;
	}

	public static void setCostoTerapiaIntensiva(double costoTerapiaIntensiva) {
		TerapiaIntensiva.costoTerapiaIntensiva = costoTerapiaIntensiva;
	}

	@Override
	protected double getCosto(int cantidadDeDias) {
		double costo = costoTerapiaIntensiva;
		return Math.pow(costo, cantidadDeDias);
	}

}
