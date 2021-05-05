package medicos;

import personas.Domicilio;

public abstract class DecoradorMedico implements IMedico {

	protected IMedico encapsulado;

	public DecoradorMedico(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}

	@Override
	public abstract double getSueldo();

	@Override
	public String getNombre() {
		return encapsulado.getNombre();
	}

	@Override
	public String getApellido() {
		return encapsulado.getNombre();
	}

	@Override
	public int getDni() {
		return encapsulado.getDni();
	}

	@Override
	public String getTelefono() {
		return encapsulado.getTelefono();
	}

	@Override
	public Domicilio getDomicilio() {
		return encapsulado.getDomicilio();
	}

	@Override
	public String getCiudad() {
		return encapsulado.getCiudad();
	}

}
