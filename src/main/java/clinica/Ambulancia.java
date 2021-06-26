package clinica;

import java.util.Observable;

import estados.DisponibleState;
import estados.IState;

public class Ambulancia extends Observable {
	private static Ambulancia instancia = null;
	private IState estado;

	private Ambulancia() {
		this.estado = new DisponibleState(this);
	}
	
	public static Ambulancia getInstance() {
		if(Ambulancia.instancia==null)
			 Ambulancia.instancia= new Ambulancia();
		return Ambulancia.instancia;
	}

	public IState getEstado() {
		return estado;
	}

	public void setEstado(IState estado) {
		this.estado = estado;
	}

	public String informaEstado() {
		return this.estado.reportaEstado();
	}

	public synchronized void vuelveaClinica() {
		while (this.estado.reportaEstado().equalsIgnoreCase("Disponible en la clinica."))
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.estado.vuelveaClinica();
		setChanged();
		notifyObservers(this.estado.reportaEstado());
		notifyAll();
	}

	public synchronized void solicitaTranslado() {
		while (!this.estado.reportaEstado().equalsIgnoreCase("Disponible en la clinica."))
			try {
				this.setChanged();
				this.notifyObservers("Imposible transladar al paciente a clinica. La ambulancia se encuentra "
						+ this.estado.reportaEstado());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.estado.solicitaTranslado();
		this.setChanged();
		this.notifyObservers(this.estado.reportaEstado());
		notifyAll();
	}

	public synchronized void solicitaAtencion() {
		while (this.estado.reportaEstado().equals("Transladando paciente a la clinica.")
				|| this.estado.reportaEstado().equals("Atendiendo a un paciente en su domicilio.")
				|| this.estado.reportaEstado().equals("En el taller.")
				|| this.estado.reportaEstado().equals("Regresando del taller.")) {
			try {
				this.notifyObservers(
						"Imposible ir al domicilio. La ambulancia se encuentra " + this.estado.reportaEstado());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.estado.solicitaAtencion();
		this.setChanged();
		this.notifyObservers(this.estado.reportaEstado());
		notifyAll();

	}

	public synchronized void solicitaReparacion() {
		while (!this.estado.reportaEstado().equals("Disponible en la clinica.")) {
			try {
				if (!this.estado.reportaEstado().equals("En el taller."))
					this.notifyObservers(
							"Imposible reparar la ambulancia. La misma se encuentra " + this.estado.reportaEstado());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.estado.solicitaReparacion();
		this.setChanged();
		this.notifyObservers(this.estado.reportaEstado());
		notifyAll();

	}

}
