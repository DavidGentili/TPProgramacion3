package habitaciones;

public class TerapiaIntensiva extends Habitacion {

	private static double costoTerapiaIntensiva = 0;

	public TerapiaIntensiva(int limite) {
		super(limite);
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
	
	@Override
	protected boolean aceptaTipo(String tipo) {
		if (tipo.equalsIgnoreCase("Terapia Intensiva"))
			return true;
		else
			return false;
	}

}
