package medicos;

/**
 * 
 * Representa un medico Cirujano, el cual esta implementando el patron
 * Decorator, usando como encapsulado a un IMedico,
 */
public class Cirujano extends DecoradorMedico {

	public Cirujano(IMedico encapsulado) {
		super(encapsulado);
	}

	/**
	 * Retorna el sueldo del medico encapsulado, mas el 10%
	 */
	@Override
	public double getSueldo() {
		return encapsulado.getSueldo() * 1.1;
	}

	/**
	 * Se encarga de retornar un string con los datos del medico encapsulado,
	 * adicionando que es un cirujano
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Especialidad: Cirujano";
	}

}
