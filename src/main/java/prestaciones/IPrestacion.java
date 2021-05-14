package prestaciones;

import java.util.GregorianCalendar;

public interface IPrestacion {

	double calcularSubtotal();
	
	void setFecha(GregorianCalendar fecha);

}
