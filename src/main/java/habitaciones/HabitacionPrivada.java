package habitaciones;

public class HabitacionPrivada extends Habitacion {
	private static double costoHabitacionPrivada = 0;

	public HabitacionPrivada() {
		super(1);
	}

	public static double getCostoHabitacionPrivada() {
		return costoHabitacionPrivada;
	}

	public static void setCostoHabitacionPrivada(double costoHabitacionPrivada) {
		HabitacionPrivada.costoHabitacionPrivada = costoHabitacionPrivada;
	}

	@Override
	protected double getCosto(int cantidadDeDias) {
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
	protected boolean aceptaTipo(String tipo) {
		if (tipo.equalsIgnoreCase("Habitacion Privada"))
			return true;
		else
			return false;
	}

}
