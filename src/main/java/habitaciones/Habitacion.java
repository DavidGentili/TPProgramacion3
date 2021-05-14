package habitaciones;

public abstract class Habitacion {

	private static int histHabitaciones = 0;
	protected int nroHabitacion;

	public Habitacion() {
		this.nroHabitacion = ++Habitacion.histHabitaciones;
	}

	public int getNroHabitacion() {
		return nroHabitacion;
	}

	public void setNroHabitacion(int identificador) {
		this.nroHabitacion = identificador;
	}

	public abstract double getCosto(int cantidadDeDias);

	@Override
	public abstract String toString();
	
	

}
