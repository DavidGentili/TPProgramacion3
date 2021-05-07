package habitaciones;

public abstract class Habitacion implements IHabitacion {

	protected int nroHabitacion;

	public Habitacion(int identificador) {
		this.nroHabitacion = identificador;
	}

	public int getNroHabitacion() {
		return nroHabitacion;
	}

	public void setNroHabitacion(int identificador) {
		this.nroHabitacion = identificador;
	}

}
