package medicos;

public class Pediatra extends DecoratorMedico {

	public Pediatra(IMedico encapsulado) {
		super(encapsulado);
	}

	@Override
	public double getHonorario() {
		return this.getHonorario() * 1.07;
	}
}
