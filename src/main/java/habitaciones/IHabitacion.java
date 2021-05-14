package habitaciones;

public interface IHabitacion {
	String toString();

	double calculaCosto(int cantDias);

	double getCostoMinimo();
}
