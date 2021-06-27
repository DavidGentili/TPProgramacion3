package prueba;

import controlador.ControladorAmbulancia;
import controlador.ControladorConfiguraciones;
import controlador.ControladorFacturacion;
import controlador.ControladorMedicosYPacientes;
import exceptions.DomicilioInvalido;
import vista.VentanaClinica;

public class PruebaVentana {

	public static void main(String[] args) throws DomicilioInvalido {

		VentanaClinica ventana = new VentanaClinica();
		ControladorConfiguraciones cConfiguraciones = new ControladorConfiguraciones(ventana);
		ControladorMedicosYPacientes cMedicosYPacientes = new ControladorMedicosYPacientes(ventana, ventana);
		ControladorFacturacion cFacturacion = new ControladorFacturacion(ventana);
		ControladorAmbulancia cAmbulancia = new ControladorAmbulancia(ventana);

	}

}
