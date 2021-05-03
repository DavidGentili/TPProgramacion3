package personas;

public abstract class Persona {
	private String nombre;
	private String apellido;
	private int DNI;
	private String telefono;
	private Domicilio domicilio;
	private String ciudad;

	public Persona(String nombre, String apellido, int DNI) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.DNI = DNI;
	}

	public Persona(String nombre, String apellido, int dNI, String telefono, Domicilio domicilio, String ciudad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
	}

	public Persona(String nombre, String apellido, int dNI, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.telefono = telefono;
	}

	public Persona(String nombre, String apellido, int dNI, Domicilio domicilio, String ciudad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getDNI() {
		return DNI;
	}

	public String getTelefono() {
		return telefono;
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

}
