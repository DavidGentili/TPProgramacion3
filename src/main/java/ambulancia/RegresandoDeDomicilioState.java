package ambulancia;

public class RegresandoDeDomicilioState implements IState {

	private Ambulancia a;

	public RegresandoDeDomicilioState(Ambulancia a) {
		this.a = a;
		this.a.setDisponible(false);
	}

	@Override
	public String reportaEstado() {
		return "Regresando de un domicilio sin paciente.";
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
	public void solicitaRetorno() {
		this.a.setEstado(new DisponibleState(a));

	}

}
