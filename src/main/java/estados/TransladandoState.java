package estados;

import clinica.Ambulancia;

public class TransladandoState implements IState {

	private Ambulancia a;
	
	public TransladandoState(Ambulancia a) {
		super();
		this.a = a;
	}

	@Override
	public String reportaEstado() {
		return "La ambulancia se encuentra transladando a un paciente";
	}

	@Override
	public void llamaTranslado() {
		// TODO Auto-generated method stub

	}

	@Override
	public void necesitaReparacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llamaAtencion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void llegoClinica() {
		this.a.setEstado(new DisponibleState(this.a));
	}

}
