package medicos;

public class Doctorado extends DecoradorMedico {

	public Doctorado(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.1;
	}

}
