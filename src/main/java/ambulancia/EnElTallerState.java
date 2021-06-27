package ambulancia;

public class EnElTallerState implements IState {

	private Ambulancia a;

	public EnElTallerState(Ambulancia a) {
		this.a = a;
		this.a.setDisponible(false);
	}

	@Override
	public String reportaEstado() {
		return "En el taller.";
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
		this.a.setEstado(new RegresandoDelTallerState(a));

	}

}
