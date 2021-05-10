package medicos;

/**
 * 
 * Representa un medico con el posgrado Doctorado, el cual esta implementando el
 * patron Decorator, usando como encapsulado a un IMedico
 *
 */
public class Doctorado extends DecoradorMedico {

	public Doctorado(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna el sueldo del encapsulado mas el 10%
	 */
	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.1;
	}

	/**
	 * Se encarga de retornar un string con los datos del medico encapsulado,
	 * adicionando que tiene un posgrado Doctorado
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Posgrado: Doctorado";
	}

}
