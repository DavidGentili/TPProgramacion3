package pacientes;

public class Mayor extends Paciente {

	public Mayor(String nombre, String apellido, int DNI) {
		super(nombre, apellido, DNI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPaciente ingreso(IPaciente otro) {
		return otro.comparaConMayor(this);
	}

	@Override
	public IPaciente comparaConNiño(IPaciente otro) {
		return this;
	}

	@Override
	public IPaciente comparaConJoven(IPaciente otro) {
		return otro;
	}

	@Override
	public IPaciente comparaConMayor(IPaciente otro) {
		return this;
	}

}
