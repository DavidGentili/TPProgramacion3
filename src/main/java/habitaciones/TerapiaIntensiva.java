package habitaciones;

public class TerapiaIntensiva extends Habitacion {

	private static double costoTerapiaIntensiva = 0;
	private static TerapiaIntensiva Instance = null;
	
	public static TerapiaIntensiva getInstance(){
		if(TerapiaIntensiva.Instance==null)
			TerapiaIntensiva.Instance= new TerapiaIntensiva();
		
		return TerapiaIntensiva.Instance;
	}

	private TerapiaIntensiva() {
		super();
	}

	public static double getCostoTerapiaIntensiva() {
		return costoTerapiaIntensiva;
	}

	public static void setCostoTerapiaIntensiva(double costoTerapiaIntensiva) {
		TerapiaIntensiva.costoTerapiaIntensiva = costoTerapiaIntensiva;
	}

	@Override
	public double getCosto(int cantidadDeDias) {
		double costo = costoTerapiaIntensiva;
		return Math.pow(costo, cantidadDeDias);
	}

	@Override
	public String toString() {
		return "la sala de terapia intensiva";
	}

}
