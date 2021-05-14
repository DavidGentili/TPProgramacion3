package habitaciones;

class HabitacionPrivada implements IHabitacion {
	private static double costoHabitacionPrivada = 0;

	protected HabitacionPrivada() {
		super();
	}

	public static double getCostoHabitacionPrivada() {
		return costoHabitacionPrivada;
	}

	public static void setCostoHabitacionPrivada(double costoHabitacionPrivada) {
		HabitacionPrivada.costoHabitacionPrivada = costoHabitacionPrivada;
	}

	public double calculaCosto(int cantidadDeDias) {
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
	public double getCostoMinimo() {
		return costoHabitacionPrivada;
	}
	
	@Override
	public String toString() {
		return "habitacion privada";
	}

}
