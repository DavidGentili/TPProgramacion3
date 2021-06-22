package medicos;

/**
 * 
 * Representa un medico con el posgrado Magister, el cual esta implementando el
 * patron Decorator, usando como encapsulado a un DecoradorMedicoContratacion
 *
 */
public class Magister extends DecoradorMedicoPosgrado {

	public Magister(DecoradorMedicoContratacion encapsulado) {
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
	 * adicionando que tiene un posgrado Magister
	 */
	@Override
	public String toString() {
		return encapsulado.toString() + " Posgrado: Magister";
	}

}
