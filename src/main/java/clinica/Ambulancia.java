package clinica;

import java.util.Observable;

import estados.DisponibleState;
import estados.IState;

public class Ambulancia extends Observable{
	private IState estado;

	public Ambulancia() {
		this.estado = new DisponibleState(this);
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

	public synchronized void devuelveAmbulancia() {
		while(this.estado.reportaEstado().equalsIgnoreCase("La ambulancia se encuentra disponible en la clinica"))
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.estado.llegoClinica();
		setChanged();
		notifyObservers(this.estado.reportaEstado());
	}
	
	public synchronized void llamanTranslado() {
		while(!this.estado.reportaEstado().equalsIgnoreCase("La ambulancia se encuentra disponible en la clinica"))
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.estado.llamaTranslado();
		setChanged();
		notifyObservers(this.estado.reportaEstado());
	}
	
}
