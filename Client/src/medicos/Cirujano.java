package medicos;

public class Cirujano extends DecoratorMedico {

	public Cirujano(IMedico encapsulado) {
		super(encapsulado);
	}

	@Override
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.1;
	}
}
