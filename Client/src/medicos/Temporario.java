package medicos;

public class Temporario extends DecoratorMedico {

	public Temporario(IMedico encapsulado) {
		super(encapsulado);
	}


	public double getHonorario() {
		return this.encapsulado.getHonorario()*1.05;
	}
}
