package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import ambulancia.Ambulancia;
import asociado.Asociado;
import clinica.Clinica;
import exceptions.AsociadoNoEncontrado;
import exceptions.AsociadoYaExistente;
import exceptions.DomicilioInvalido;
import personas.Domicilio;
import vista.IVistaAmbulancia;

@SuppressWarnings("deprecation")
public class ControladorAmbulancia implements ActionListener, Observer {

	IVistaAmbulancia ventanaAmbulancia;
	Clinica clinica;

	public ControladorAmbulancia(IVistaAmbulancia ventanaAmbulancia) {
		this.ventanaAmbulancia = ventanaAmbulancia;
		this.clinica = Clinica.getInstancia();

		Ambulancia.getInstance().addObserver(this);

		this.ventanaAmbulancia.setActionListenerAmbulancia(this);
		this.ventanaAmbulancia.actualizaAsociados(this.clinica.getIteratorAsociados());
		clinica.iniciarSimulacionAmbulancia();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equalsIgnoreCase("Llama Translado")) {
			Asociado asociado = this.ventanaAmbulancia.getAsociadoAmbulancia();
			if (asociado != null) {
				try {
					clinica.realizarPedidoTraslado(asociado);
				} catch (AsociadoNoEncontrado e1) {
					this.ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
				}
			} else
				this.ventanaAmbulancia.mostrarMensajeError("Debe seleccionar un Asociado para realizar esta accion");
		} else if (e.getActionCommand().equalsIgnoreCase("Llama Atencion")) {
			Asociado asociado = this.ventanaAmbulancia.getAsociadoAmbulancia();
			if (asociado != null) {
				try {
					clinica.realizarPedidoAtencion(asociado);
				} catch (AsociadoNoEncontrado e1) {
					this.ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
				}
			} else
				this.ventanaAmbulancia.mostrarMensajeError("Debe seleccionar un Asociado para realizar esta accion");
		} else if (e.getActionCommand().equalsIgnoreCase("Solicitar Reparacion")) {
			this.clinica.realizarPedidoDeReparacion();
		} else if (e.getActionCommand().equalsIgnoreCase("Agregar Asociado")) {
			this.agregaAsociado();
		} else if (e.getActionCommand().equalsIgnoreCase("Eliminar Asociado")) {
			if (ventanaAmbulancia.getAsociadoAmbulancia() != null) {
				try {
					clinica.eliminaAsociado(ventanaAmbulancia.getAsociadoAmbulancia());
					ventanaAmbulancia.actualizaAsociados(clinica.getIteratorAsociados());
				} catch (AsociadoNoEncontrado e1) {
					ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
				}
			} else
				ventanaAmbulancia.mostrarMensajeError("Debe seleccionar un asociado a elminar");
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		this.ventanaAmbulancia.actualizaEstadoAmbulancia((String) arg);
	}

	private void agregaAsociado() {
		int nroCalle, dni;
		String telefono, nombre, apellido, calle;

		try {
			nombre = this.ventanaAmbulancia.getNombreAsociado();
			apellido = this.ventanaAmbulancia.getApellidoAsociado();
			telefono = this.ventanaAmbulancia.getTelefonoAsociado();
			calle = this.ventanaAmbulancia.getCalleDomicilioAsocidado();
			nroCalle = Integer.parseInt(this.ventanaAmbulancia.getNumeroDomicilioAsociado());
			dni = Integer.parseInt(this.ventanaAmbulancia.getDNIAsociado());
			if (this.chequeaString(nombre))
				if (this.chequeaString(apellido))
					if (this.chequeaString(calle)) {
						// this.ventanaAmbulancia.mostrarCartelsatisfactorio("El registro del
						// asociado");
						Domicilio aux = new Domicilio(calle, nroCalle);
						this.clinica.agregaAsociado(nombre, apellido, dni, aux, telefono);
						this.ventanaAmbulancia.actualizaAsociados(this.clinica.getIteratorAsociados());
					} else
						this.ventanaAmbulancia.mostrarMensajeError("Error en el campo Calle");
				else
					this.ventanaAmbulancia.mostrarMensajeError("Error en el campo Apellido");
			else
				this.ventanaAmbulancia.mostrarMensajeError("Error en el campo nombre");

		} catch (NumberFormatException ex) {
			this.ventanaAmbulancia.mostrarMensajeError("Formato de los numeros incorrectos");
		} catch (DomicilioInvalido e1) {
			this.ventanaAmbulancia.mostrarMensajeError(e1.getMessage());
		} catch (AsociadoYaExistente e2) {
			this.ventanaAmbulancia.mostrarMensajeError(e2.getMessage());
		}
	}

	private boolean chequeaString(String campo) {
		return (campo.isBlank() || campo.isEmpty() || campo == null) ? false : true;
	}

}
