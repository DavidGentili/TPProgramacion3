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
		if (Ambulancia.instancia == null)
			Ambulancia.instancia = new Ambulancia();
		return Ambulancia.instancia;
	}

	public IState getEstado() {
		return estado;
	}

	public void setEstado(IState estado) {
		this.estado = estado;
	}

	/**
	 * La ambulancia retorna su estado
	 * 
	 * @return
	 */
	public String informaEstado() {
		return this.estado.reportaEstado();
	}

	/**
	 * Este metodo intenta que la ambulancia regrese a la clinica, solo se ejecutara
	 * si su estado es distinto que disponible
	 */
	public synchronized void vuelveaClinica() {
		while (this.estado.reportaEstado().equalsIgnoreCase("Disponible en la clinica.")
				|| this.estado.reportaEstado().equalsIgnoreCase("Atendiendo a un paciente en su domicilio.")
				|| this.estado.reportaEstado().equals("En el taller."))
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

	/**
	 * Se le solicita a la ambulancia un translado, el cual solo podra efectuarse si
	 * la clinica esta disponible
	 */
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

	/**
	 * Se le solicita a la ambulancia una atencion en domicilio, el cual solo podra
	 * efectuarse si la ambulancia esta disponible
	 */
	public synchronized void solicitaAtencion() {
		while (!this.estado.reportaEstado().equalsIgnoreCase("Disponible en la clinica.")) {
			try {
				setChanged();
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

	/**
	 * Se le solicita a la ambulancia una reparacion, solo se ejecuta si la
	 * ambulancia esta disponible
	 */
	public synchronized void solicitaReparacion() {
		while (!this.estado.reportaEstado().equals("Disponible en la clinica.")) {
			try {
				setChanged();
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

	/**
	 * Se le solicita a la ambulancia que regrese de una atencion, el cual solo
	 * podra efectuarse si la ambulancia esta atendiendo un paciente
	 */
	public synchronized void vueltaAtencion() {
		while (!this.estado.reportaEstado().equalsIgnoreCase("Atendiendo a un paciente en su domicilio."))
			try {
				setChanged();
				this.notifyObservers("No puede volver de atender un paciente que nunca visito");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.estado.vuelveAtencion();
		setChanged();
		notifyObservers(this.estado.reportaEstado());
		notifyAll();
	}

	public synchronized void vueltaTaller() {
		while (!this.estado.reportaEstado().equalsIgnoreCase("En el taller."))
			try {
				setChanged();
				this.notifyObservers("No puede volver del taller si nunca fui");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		this.estado.vuelveReparacion();
		setChanged();
		notifyObservers(this.estado.reportaEstado());
		notifyAll();
	}
}
