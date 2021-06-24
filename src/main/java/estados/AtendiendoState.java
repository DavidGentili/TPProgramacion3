package estados;

import clinica.Ambulancia;

public class AtendiendoState implements IState {
	
	private Ambulancia a;

	
	
	public AtendiendoState(Ambulancia a) {
		super();
		this.a = a;
	}

	@Override
	public String reportaEstado() {
		return "La ambulancia esta atendendio a un paciente en su casa ";
	}

	@Override
	public void llamaTranslado() {	}

	@Override
	public void necesitaReparacion() {
	}

	@Override
	public void llamaAtencion() {
	}

	@Override
	public void llegoClinica() {
		this.a.setEstado(new DisponibleState(this.a));
	}

}
