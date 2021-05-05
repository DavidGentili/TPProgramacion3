package medicos;

public class Magister extends DecoradorMedico {

	public Magister(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.05;
	}

}
