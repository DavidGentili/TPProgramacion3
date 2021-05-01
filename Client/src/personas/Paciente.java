package personas;


public class Paciente extends Persona implements IPaciente{
    private int nroHistoriaClinica;


    public Paciente(String nombre, String apellido, int DNI) {
        super(nombre, apellido, DNI);
    }
}
