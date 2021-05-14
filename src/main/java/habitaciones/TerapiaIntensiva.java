package habitaciones;

class TerapiaIntensiva implements IHabitacion {

	private static double costoTerapiaIntensiva = 0;

	protected TerapiaIntensiva() {
		super();
	}

	public static double getCostoTerapiaIntensiva() {
		return costoTerapiaIntensiva;
	}

	public static void setCostoTerapiaIntensiva(double costoTerapiaIntensiva) {
		TerapiaIntensiva.costoTerapiaIntensiva = costoTerapiaIntensiva;
	}

	public double calculaCosto(int cantidadDeDias) {
		double costo = costoTerapiaIntensiva;
		return Math.pow(costo, cantidadDeDias);
	}
	
	@Override
	public double getCostoMinimo() {
		return costoTerapiaIntensiva;
	}

	@Override
	public String toString() {
		return "la sala de terapia intensiva";
	}

}
