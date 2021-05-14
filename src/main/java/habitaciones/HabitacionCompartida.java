package habitaciones;

public class HabitacionCompartida extends Habitacion {

	private static double costoHabitacionCompartida = 0;
	private static HabitacionCompartida Instance = null;
	
	public static HabitacionCompartida getInstance(){
		if(HabitacionCompartida.Instance==null)
			HabitacionCompartida.Instance= new HabitacionCompartida();
		
		return HabitacionCompartida.Instance;
	}
	
	private HabitacionCompartida() {
		super();
	}

	public static double getCostoHabitacionCompartida() {
		return costoHabitacionCompartida;
	}

	public static void setCostoHabitacionCompartida(double costoHabitacionCompartida) {
		HabitacionCompartida.costoHabitacionCompartida = costoHabitacionCompartida;
	}

	@Override
	public double getCosto(int cantidadDeDias) {
		double costo = costoHabitacionCompartida;
		return costo + costo * cantidadDeDias;
	}

	@Override
	public String toString() {
		return "habitacion compartida";
	}

}
