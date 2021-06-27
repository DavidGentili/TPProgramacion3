package ambulancia;

public class TransladandoState implements IState {

	private Ambulancia a;

	public TransladandoState(Ambulancia a) {
		this.a = a;
		this.a.setDisponible(false);
	}

	@Override
	public String reportaEstado() {
		return "Transladando paciente a la clinica.";
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

	}

}
