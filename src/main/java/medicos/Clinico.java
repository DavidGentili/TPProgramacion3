package medicos;

/**
 * 
 * Representa un medico Clinico, el cual esta implementando el patron Decorator,
 * usando como encapsulado a un IMedico,
 *
 */
public class Clinico extends DecoradorMedico {

	public Clinico(IMedico encapsulado) {
		super(encapsulado);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna el sueldo del encapsulado mas el 5%
	 */
	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.05;
	}

	/**
	 * Se encarga de retornar un string con los datos del medico encapsulado,
	 * adicionando que es un clinico
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Especialidad: Clinico";
	}

}
