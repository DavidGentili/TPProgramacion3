package habitaciones;

class HabitacionCompartida implements IHabitacion {

	private static double costoHabitacionCompartida = 0;

	protected HabitacionCompartida() {
		super();
	}

	public static double getCostoHabitacionCompartida() {
		return costoHabitacionCompartida;
	}

	public static void setCostoHabitacionCompartida(double costoHabitacionCompartida) {
		HabitacionCompartida.costoHabitacionCompartida = costoHabitacionCompartida;
	}

	public double calculaCosto(int cantidadDeDias) {
		double costo = costoHabitacionCompartida;
		return costo + costo * cantidadDeDias;
	}

	@Override
	public double getCostoMinimo() {
		return costoHabitacionCompartida;
	}

	@Override
	public String toString() {
		return "habitacion compartida";
	}

}
