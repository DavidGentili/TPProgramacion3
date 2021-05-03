package medicos;

public class Doctorado extends DecoratorMedico {

	public Doctorado(IMedico encapsulado) {
		super(encapsulado);
	}

	public double getHonorario() {
		return this.encapsulado.getHonorario() * 1.1;
	}
}
