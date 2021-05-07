package medicos;

public class Cirujano extends DecoradorMedico {

	public Cirujano(IMedico encapsulado) {
		super(encapsulado);
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.1;
	}

	@Override
	public String toString() {
		return encapsulado.toString() + " Especialidad: Cirujano";
	}

}
