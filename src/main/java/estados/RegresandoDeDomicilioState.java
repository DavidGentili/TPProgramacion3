package estados;

import clinica.Ambulancia;

public class RegresandoDeDomicilioState implements IState {

	private Ambulancia a;

	public RegresandoDeDomicilioState(Ambulancia a) {
		super();
		this.a = a;
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
