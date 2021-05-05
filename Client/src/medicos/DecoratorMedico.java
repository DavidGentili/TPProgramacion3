package medicos;

import personas.Domicilio;

//Se genera un decorator padre que se encarga de interactuar con el encapsulado,
//y estas interacciones se heredan a los hijos, 
public abstract class DecoratorMedico implements IMedico {

	protected IMedico encapsulado;

	@Override
	public DecoratorMedico(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}

	@Override
	public String getNombre() {
		return this.encapsulado.getNombre();
	}

	@Override
	public String getApellido() {
		return this.encapsulado.getApellido();
	}

	@Override
	public int getDNI() {
		return this.encapsulado.getDNI();
	}

	@Override
	public String getTelefono() {
		return this.encapsulado.getTelefono();
	}

	@Override
	public Domicilio getDomicilio() {
		return this.encapsulado.getDomicilio();
	}

	@Override
	public void setDomicilio(Domicilio domicilio) {
		this.encapsulado.setDomicilio(domicilio);
	}

	@Override
	public String getCiudad() {
		return this.encapsulado.getCiudad();
	}

}
