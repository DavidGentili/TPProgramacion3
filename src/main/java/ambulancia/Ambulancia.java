package ambulancia;

import java.util.Observable;

public class Ambulancia extends Observable implements IState {
	private static Ambulancia instancia = null;
	private IState estado;
	private boolean disponible = false;

	private Ambulancia() {
		this.estado = new DisponibleState(this);
	}

	/**
	 * Retorna una instancia de la ambulancia, si no esta incializada, invoca al
	 * constructor y retorna la instancia
	 * 
	 * @return
	 */
	public static Ambulancia getInstance() {
		if (Ambulancia.instancia == null)
			Ambulancia.instancia = new Ambulancia();
		return Ambulancia.instancia;
	}

	/**
	 * Permite modificar el estado de la ambulancia
	 * 
	 * @param nuevo estado de la ambulancia
	 */
	protected void setEstado(IState estado) {
		this.estado = estado;
	}

	/**
	 * La ambulancia retorna su estado, transmitiendo la responsabilidad a su
	 * variable IState
	 * 
	 * @return string que descrobe el estado actual de la ambulancia
	 */
	@Override
	public String reportaEstado() {
		return this.estado.reportaEstado();
	}

	/**
	 * Este metodo intenta que la ambulancia regrese a la clinica, solo se ejecutara
	 * si su estado es distinto que disponible
	 */
	public synchronized void solicitaRetorno() {
		if (!disponible) {
			this.estado.solicitaRetorno();
			setChanged();
			notifyObservers(this.estado.reportaEstado());
			notifyAll();
		}
		while (disponible) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Se le solicita a la ambulancia un translado, el cual solo podra efectuarse si
	 * la clinica esta disponible
	 */
	public synchronized void solicitaTranslado() {
		if (!disponible) {
			this.setChanged();
			this.notifyObservers("Imposible transladar al paciente a clinica en este momento");
			while (!disponible) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
		if (!disponible) {
			this.setChanged();
			this.notifyObservers("Imposible realizar una atencion en el domicilio en este momento");
			while (!disponible) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		if (!disponible) {
			this.setChanged();
			this.notifyObservers("Imposible reparar la ambulancia en este momento.");
			while (!disponible) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		this.estado.solicitaReparacion();
		this.setChanged();
		this.notifyObservers(this.estado.reportaEstado());
		notifyAll();

	}

	/**
	 * Permite definir el estado de disponibilidad de la ambulancia
	 * 
	 * @param disponible nuevo estado de disponibilidad
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
