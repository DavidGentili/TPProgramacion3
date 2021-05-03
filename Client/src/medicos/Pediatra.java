package medicos;

public class Pediatra extends DecoratorMedico {

	public Pediatra(IMedico encapsulado) {
		super(encapsulado);
	}


	public double getHonorario() {
		return this.getHonorario() * 1.07;
	}
}
