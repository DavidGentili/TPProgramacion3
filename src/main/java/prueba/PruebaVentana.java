package prueba;

import controlador.Controlador;
import exceptions.DomicilioInvalido;
import vista.VentanaClinica;

public class PruebaVentana {

	public static void main(String[] args) throws DomicilioInvalido {

		VentanaClinica ventana = new VentanaClinica();
		Controlador c = new Controlador(ventana, ventana, ventana, ventana, ventana);

	}

}
