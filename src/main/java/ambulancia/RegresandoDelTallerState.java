package ambulancia;

public class RegresandoDelTallerState implements IState {

	private Ambulancia a;

	public RegresandoDelTallerState(Ambulancia a) {
		this.a = a;
		this.a.setDisponible(false);
	}

	@Override
	public String reportaEstado() {
		return "Regresando del taller.";
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
