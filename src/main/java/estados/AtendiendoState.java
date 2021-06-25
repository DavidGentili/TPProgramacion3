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
		return "Atendendio a un paciente en su domicilio.";
	}

	@Override
	public void solicitaTranslado() {
	}

	@Override
	public void solicitaReparacion() {
	}

	@Override
	public void solicitaAtencion() {
	}

	@Override
	public void vuelveaClinica() {
		this.a.setEstado(new TransladandoState(this.a));
	}

}
