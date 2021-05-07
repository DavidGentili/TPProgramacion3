package medicos;

public class Pediatra extends DecoradorMedico {

	public Pediatra(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.07;
	}
	
	@Override
	public String toString() {
		return encapsulado.toString() + " Especialidad: Pediatra";
	}

}
