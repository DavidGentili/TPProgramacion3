package personas;

public class Personas {
	protected String nombre;
	protected String apellido;
	protected int dni;
	protected String telefono;
	protected Domicilio domicilio;
	protected String ciudad;

	public Personas(String nombre, String apellido, int dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public Personas(String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getDni() {
		return dni;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + " DNI:" + dni + " Telefono: " + telefono + " Domicilio: " + domicilio
				+ " Ciudad: " + ciudad;
	}

}
