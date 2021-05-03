package pacientes;

public class Niño extends Paciente {

	public Niño(String nombre, String apellido, int DNI) {
		super(nombre, apellido, DNI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPaciente ingreso(IPaciente otro) {
		return otro.comparaConNiño(this);
	}

	@Override
	public IPaciente comparaConNiño(IPaciente otro) {
		return this;
	}

	@Override
	public IPaciente comparaConJoven(IPaciente otro) {
		return this;
	}

	@Override
	public IPaciente comparaConMayor(IPaciente otro) {
		return otro;
	}

}
