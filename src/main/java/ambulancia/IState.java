package ambulancia;

public interface IState {

	public String reportaEstado();

	public void solicitaTranslado();

	public void solicitaReparacion();

	public void solicitaAtencion();

	public void solicitaRetorno();
}
