package medicos;

public class Temporario extends DecoradorMedico {

	public Temporario(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.05;
	}

	@Override
	public String toString() {
		return encapsulado.toString() + " Contratacion: Temporario";
	}

}
