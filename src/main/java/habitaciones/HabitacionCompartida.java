package habitaciones;

public class HabitacionCompartida extends Habitacion {

	private static double costoHabitacionCompartida = 0;

	public HabitacionCompartida(int identificador) {
		super(identificador, 2);
	}

	public static double getCostoHabitacionCompartida() {
		return costoHabitacionCompartida;
	}

	public static void setCostoHabitacionCompartida(double costoHabitacionCompartida) {
		HabitacionCompartida.costoHabitacionCompartida = costoHabitacionCompartida;
	}

	@Override
	protected double getCosto(int cantidadDeDias) {
		double costo = costoHabitacionCompartida;
		return costo + costo * cantidadDeDias;
	}

}
