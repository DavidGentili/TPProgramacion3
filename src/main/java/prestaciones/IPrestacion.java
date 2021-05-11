package prestaciones;

import java.util.GregorianCalendar;

public interface IPrestacion {

	double calcularSubtotal(int cantidad);
	
	void setFecha(GregorianCalendar fecha);

}
