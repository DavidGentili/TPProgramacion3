package ambulancia;

public class DisponibleState implements IState {
	private Ambulancia a;

	public DisponibleState(Ambulancia a) {
		this.a = a;
		this.a.setDisponible(true);
	}

	@Override
	public String reportaEstado() {
		return "Disponible en la clinica.";
	}

	@Override
	public void solicitaTranslado() {
		this.a.setEstado(new TransladandoState(this.a));
	}

	@Override
	public void solicitaReparacion() {
		this.a.setEstado(new EnElTallerState(this.a));
	}

	@Override
	public void solicitaAtencion() {
		this.a.setEstado(new AtendiendoState(this.a));
	}

	@Override
	public void solicitaRetorno() {
		
	}

}
