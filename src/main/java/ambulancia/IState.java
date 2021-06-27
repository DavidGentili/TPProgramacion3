package estados;

public interface IState {
	
	public String reportaEstado();
	
	public void solicitaTranslado();
	
	public void solicitaReparacion();
	
	public void solicitaAtencion();
	
	public void vuelveAtencion();
	
	public void vuelveReparacion();
	
	public void vuelveaClinica();

}
