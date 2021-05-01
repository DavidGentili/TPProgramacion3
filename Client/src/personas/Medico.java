package personas;


public abstract class Medico extends Persona implements IMedico {

    private String matricula;

    public Medico(String nombre, String apellido, int DNI, String matricula) {
        super(nombre, apellido, DNI);
        this.matricula = matricula;
    }
}
