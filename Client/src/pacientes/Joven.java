package pacientes;

public class Joven extends Paciente {

	public Joven(String nombre, String apellido, int DNI) {
		super(nombre, apellido, DNI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPaciente ingreso(IPaciente otro) {
		return otro.comparaConJoven(this);
	}

	@Override
	public IPaciente comparaConNiño(IPaciente otro) {
		return otro;
	}

	@Override
	public IPaciente comparaConJoven(IPaciente otro) {
		return this;
	}

	@Override
	public IPaciente comparaConMayor(IPaciente otro) {
		return this;
	}

}
