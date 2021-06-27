package ambulancia;

public class AtendiendoState implements IState {

	private Ambulancia a;

	public AtendiendoState(Ambulancia a) {
		this.a = a;
		this.a.setDisponible(false);
	}

	@Override
	public String reportaEstado() {
		return "Atendiendo a un paciente en su domicilio.";
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
