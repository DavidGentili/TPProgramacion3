package medicos;

/**
 * 
 * Representa un medico con la condicion de contratacion Permanente, el cual
 * esta implementando el patron Decorator, usando como encapsulado a un IMedico
 *
 */
public class Permanente extends DecoradorMedicoContratacion {

	/**
	 * Constructor vacio
	 */
	public Permanente() {

	}

	/**
	 * Instancia un medico con la contratacion permanente
	 * 
	 * @param encapsulado Medico Encapsulado
	 */
	public Permanente(IMedico encapsulado) {
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
	 * adicionando que esta contratado de forma permanente
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Permanente";
	}

}
