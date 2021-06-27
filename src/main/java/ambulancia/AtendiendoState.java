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
	public void vuelveaClinica() {
	}

	@Override
	public void vuelveAtencion() {
		this.a.setEstado(new RegresandoDeDomicilioState(a));
		
	}

	@Override
	public void vuelveReparacion() {
		// TODO Auto-generated method stub
		
	}

}
