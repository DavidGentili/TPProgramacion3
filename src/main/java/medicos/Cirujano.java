package medicos;

public class Cirujano extends DecoradorMedico {

	public Cirujano(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.1;
	}

}
