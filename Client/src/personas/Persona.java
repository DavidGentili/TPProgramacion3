package personas;


public abstract class Persona {
    private String nombre;
    private String apellido;
    private int DNI;
    private String telefono;
    private String Domicilio;
    private String Ciudad;


    public Persona(String nombre, String apellido, int DNI) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
    }
}
