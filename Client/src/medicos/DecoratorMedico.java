package medicos;

import personas.Domicilio;

//Se genera un decorator padre que se encarga de interactuar con el encapsulado,
//y estas interacciones se heredan a los hijos, 
public abstract class DecoratorMedico implements IMedico {

	protected IMedico encapsulado;

	public DecoratorMedico(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}

	public String getNombre() {
		return this.encapsulado.getNombre();
	}

	public String getApellido() {
		return this.encapsulado.getApellido();
	}

	public int getDNI() {
		return this.encapsulado.getDNI();
	}

	public String getTelefono() {
		return this.encapsulado.getTelefono();
	}

	public Domicilio getDomicilio() {
		return this.encapsulado.getDomicilio();
	}

	public void setDomicilio(Domicilio domicilio) {
		this.encapsulado.setDomicilio(domicilio);
	}


	public String getCiudad() {
		return this.encapsulado.getCiudad();
	}

}
