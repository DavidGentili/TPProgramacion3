package medicos;


/**
 * 
 * Representa un medico Clinico, el cual esta implementando el patron Decorator,
 * usando como encapsulado a un IMedico, representando la especialidad Pediatria
 *
 */
public class Pediatra extends DecoradorMedico {

	public Pediatra(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna el sueldo del medico encapsulado, mas el 7%
	 */
	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.07;
	}
	
	/**
	 * Se encarga de retornar un string con los datos del medico encapsulado,
	 * adicionando que es un pediatra
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Especialidad: Pediatra";
	}

}
