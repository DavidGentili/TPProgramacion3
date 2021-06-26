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
	public void vuelveaClinica() {
		this.a.setEstado(new DisponibleState(this.a));
	}

	@Override
	public void vuelveAtencion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vuelveReparacion() {
		// TODO Auto-generated method stub
		
	}

}
