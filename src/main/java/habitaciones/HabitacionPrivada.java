package habitaciones;

public class HabitacionPrivada extends Habitacion {
	private static double costoHabitacionPrivada = 0;
	private static HabitacionPrivada Instance = null;

	public static HabitacionPrivada getInstance() {
		if (HabitacionPrivada.Instance == null)
			HabitacionPrivada.Instance = new HabitacionPrivada();

		return HabitacionPrivada.Instance;
	}

	private HabitacionPrivada() {
		super();
	}

	public static double getCostoHabitacionPrivada() {
		return costoHabitacionPrivada;
	}

	public static void setCostoHabitacionPrivada(double costoHabitacionPrivada) {
		HabitacionPrivada.costoHabitacionPrivada = costoHabitacionPrivada;
	}

	@Override
	public double getCosto(int cantidadDeDias) {
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

	@Override
	public String toString() {
		return "habitacion privada";
	}

}
