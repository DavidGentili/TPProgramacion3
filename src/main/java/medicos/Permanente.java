package medicos;

public class Permanente extends DecoradorMedico {

	public Permanente(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.1;
	}

	@Override
	public String toString() {
		return encapsulado.toString() + " Contratacion: Permanente";
	}

}
