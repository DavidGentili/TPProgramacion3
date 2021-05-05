package medicos;

public class Clinico extends DecoratorMedico {

	public Clinico(IMedico encapsulado) {
		super(encapsulado);
	}

	@Override
	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.05;
	}
}
