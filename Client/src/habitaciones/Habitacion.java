package habitaciones;

import pacientes.Paciente;


public abstract class Habitacion implements IHabitacion{
    protected Paciente paciente;
    private int numero;
    protected double costoInicial;


    public Habitacion(Paciente paciente, int numero, double costoInicial) {
        this.paciente = paciente;
        this.numero = numero;
        this.costoInicial = costoInicial;
    }
}
