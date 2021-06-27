package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import clinica.Clinica;
import exceptions.DomicilioInvalido;
import exceptions.MontoInvalidoException;
import persistencia.PersistirClinica;
import personas.Domicilio;
import vista.IVistaConfiguraciones;

public class ControladorConfiguraciones implements ActionListener, WindowListener {

	IVistaConfiguraciones ventanaConfiguraciones;
	Clinica clinica;

	public ControladorConfiguraciones(IVistaConfiguraciones ventanaConfiguraciones) {
		this.clinica = Clinica.getInstancia();
		this.ventanaConfiguraciones = ventanaConfiguraciones;

		this.ventanaConfiguraciones.setActionListenerConfiguraciones(this);
		this.ventanaConfiguraciones.SetWindowListenerConfiguraciones(this);

		PersistirClinica.restaurarClinica();

		actualizarDatosConfiguracion();
		actualizarValoresConfiguracion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equalsIgnoreCase("Restaurar Clinica")) {
			PersistirClinica.restaurarClinica();
		} else if (e.getActionCommand().equalsIgnoreCase("Almacenar Clinica")) {
			try {
				PersistirClinica.almacenarClinica();
			} catch (IOException e1) {
				this.ventanaConfiguraciones.mostrarMensajeError("No se pudo almacenar la clinica correctamente");
			}
		} else if (e.getActionCommand().equalsIgnoreCase("Actualizar datos")) {
			actualizarDatosClinica();
		} else if (e.getActionCommand().equalsIgnoreCase("Actualizar valores")) {
			actualizarValoresClinica();
		}

	}

	/**
	 * Se encarga de cargar los datos ingresados por la vista en el modelo
	 */
	private void actualizarDatosClinica() {
		String nombre, telefono, direccion, ciudad;
		nombre = this.ventanaConfiguraciones.getNuevoNombreClinica();
		if (!nombre.isBlank() && !nombre.isEmpty())
			Clinica.getInstancia().setNombre(nombre);
		telefono = this.ventanaConfiguraciones.getNuevoTelefonoClinica();
		try {
			int numeroDeTelefono = Integer.parseInt(telefono);
			if (numeroDeTelefono > 0)
				Clinica.getInstancia().setTelefono(telefono);
		} catch (NumberFormatException e) {
		}

		direccion = this.ventanaConfiguraciones.getNuevaCalleClinica();
		try {
			Domicilio nuevoDomicilio = new Domicilio(this.ventanaConfiguraciones.getNuevaCalleClinica(),
					Integer.parseInt(this.ventanaConfiguraciones.getNuevoNumeroClinica()));
			Clinica.getInstancia().setDireccion(nuevoDomicilio);
		} catch (NumberFormatException | DomicilioInvalido e) {

		}

		ciudad = this.ventanaConfiguraciones.getCiudadClinica();
		if (!ciudad.isBlank() && !ciudad.isEmpty())
			Clinica.getInstancia().setCiudad(ciudad);

		this.actualizarDatosConfiguracion();
		this.ventanaConfiguraciones.limpiarCamposConfiguracion();

	}

	/**
	 * Se encarga de cargar los valores ingresados mediante la vista en el modelo,
	 * solo actualiza aquellos valores correctos, los que se haya ingresado de forma
	 * erronea se mantien su valor original
	 */
	private void actualizarValoresClinica() {
		double habPrivada, habCompartida, terapia, sueldo;
		try {
			Clinica.setCostoHabitacionPrivada(
					retornaDouble(this.ventanaConfiguraciones.getNuevoCostoHabitacionPrivada()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		try {
			Clinica.setCostoHabitacionCompartida(
					retornaDouble(this.ventanaConfiguraciones.getNuevoCostoHabitacionCompartida()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		try {
			Clinica.setCostoTerapiaIntensiva(
					retornaDouble(this.ventanaConfiguraciones.getNuevoCostoTerapiaIntensiva()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		try {
			Clinica.setSueldoBasicoMedico(retornaDouble(this.ventanaConfiguraciones.getNuevoSueldoBasicoMedicos()));
		} catch (MontoInvalidoException | NumberFormatException e) {
		}

		this.actualizarValoresConfiguracion();
		this.ventanaConfiguraciones.limpiarCamposConfiguracion();

	}

	/**
	 * A partir de un string retorna su representado en Double, si no es corresponde
	 * a un valor numerico emite una excepcion
	 * 
	 * @param numeroString Un String que representa un Double
	 * @return El numero Double
	 * @throws NumberFormatException Si el String no representa un numero real
	 */
	private double retornaDouble(String numeroString) throws NumberFormatException {
		double numero;
		if (!numeroString.isBlank() && !numeroString.isEmpty()) {
			numero = Double.parseDouble(numeroString);
			return numero;
		} else
			throw new NumberFormatException();
	}

	/**
	 * Se encarga de gestionar y enviar los datos de la clinica a actualizar en la
	 * ventana
	 */
	private void actualizarDatosConfiguracion() {
		String nombre, telefono, direccion, ciudad;
		nombre = telefono = direccion = ciudad = "";
		if (this.clinica.getNombre() != null)
			nombre = this.clinica.getNombre();
		if (this.clinica.getTelefono() != null)
			telefono = this.clinica.getTelefono();
		if (this.clinica.getDireccion() != null)
			direccion = this.clinica.getDireccion().getCalle() + " " + this.clinica.getDireccion().getNumero();
		if (this.clinica.getCiudad() != null)
			ciudad = this.clinica.getCiudad();
		this.ventanaConfiguraciones.actualizarDatosDeLaClinia(nombre, telefono, direccion, ciudad);
	}

	/**
	 * Se encarga de enviar los valores a actualizar en la ventana, solo actualiza
	 * aquellos valores correctos, los que se haya ingresado de forma erronea se
	 * mantien su valor original
	 */
	private void actualizarValoresConfiguracion() {
		this.ventanaConfiguraciones.actualizarCostosDeLaClinica(Clinica.getCostoHabitacionPrivada(),
				Clinica.getCostoHabitacionCompartida(), Clinica.getCostoTerapiaIntensiva(),
				Clinica.getSueldoBasicoMedico());
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			PersistirClinica.almacenarClinica();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
