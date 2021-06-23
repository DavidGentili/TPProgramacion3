package clinica;

import java.util.Observable;

import estados.DisponibleState;
import estados.IState;

public class Ambulancia extends Observable{
	private IState estado;

	public Ambulancia() {
		this.estado = new DisponibleState();
	}
	
	
}
