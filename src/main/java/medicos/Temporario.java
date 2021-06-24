package medicos;

/**
 * 
 * Representa un medico con la condicion de contratacion Temporaria, el cual
 * esta implementando el patron Decorator, usando como encapsulado a un IMedico
 *
 */
public class Temporario extends DecoradorMedicoContratacion {

	/**
	 * Contructor vacio
	 */
	public Temporario() {

	}

	/**
	 * instancia un medico con la contratacion temporaria
	 * 
	 * @param encapsulado Medico Encapsulado
	 */
	public Temporario(IMedico encapsulado) {
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
	 * adicionando que esta contratado de forma temporario
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Temporario";
	}

}
