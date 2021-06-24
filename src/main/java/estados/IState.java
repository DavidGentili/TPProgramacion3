package estados;

public interface IState {
	
	public String reportaEstado();
	
	public void llamaTranslado();
	
	public void necesitaReparacion();
	
	public void llamaAtencion();
	
	public void llegoClinica();

}
