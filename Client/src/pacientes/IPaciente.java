package pacientes;

public interface IPaciente {
	
	public abstract IPaciente ingreso(IPaciente otro);
	public abstract IPaciente comparaConNi�o(IPaciente otro);
	public abstract IPaciente comparaConJoven(IPaciente otro);
	public abstract IPaciente comparaConMayor(IPaciente otro);
}
