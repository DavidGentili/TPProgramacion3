package medicos;

public class Permanente extends DecoratorMedico {

	public Permanente(IMedico encapsulado) {
		super(encapsulado);
	}


	public double getHonorario() {
		return this.encapsulado.getHonorario()*1.1;
	}

}
