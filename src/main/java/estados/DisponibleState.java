package estados;

import clinica.Ambulancia;

public class DisponibleState implements IState {
	private Ambulancia a;

	public DisponibleState(Ambulancia a) {
		this.a = a;
	}

	@Override
	public String reportaEstado() {
		return "La ambulancia se encuentra disponible en la clinica";
	}

	@Override
	public void llamaTranslado() {
		this.a.setEstado(new TransladandoState(this.a));
	}

	@Override
	public void necesitaReparacion() {
		//this.a.setEstado(new ReparandoState(this.a));
	}

	@Override
	public void llamaAtencion() {
		this.a.setEstado(new AtendiendoState(this.a));
	}

	@Override
	public void llegoClinica() {}
	
	
	
	
}
