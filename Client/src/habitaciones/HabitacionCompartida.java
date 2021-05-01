package habitaciones;

import personas.Paciente;

public class HabitacionCompartida extends Habitacion {
    private Paciente otroPaciente;

    public HabitacionCompartida(Paciente paciente, int numero, double costoInicial, Paciente otroPaciente) {
        super(paciente, numero, costoInicial);
        this.otroPaciente=otroPaciente;
    }
}
