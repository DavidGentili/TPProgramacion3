package pacientes;

public class Ni�o extends Paciente {

	public Ni�o(String nombre, String apellido, int DNI) {
		super(nombre, apellido, DNI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPaciente ingreso(IPaciente otro) {
		return otro.comparaConNi�o(this);
	}

	@Override
	public IPaciente comparaConNi�o(IPaciente otro) {
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
