package prestaciones;

import habitaciones.Habitacion;

public class Internacion extends Prestacion {
	
	private Habitacion hab;
	
	public Internacion(int cantidad, Habitacion hab) {  //LO DE HABITACION SE REFIERE A LAS HABITACIONES DE ATENCION , despues lo tengo que cambiar
		super(cantidad,"Nombre de la habitacion , privada,comp, o intensiva",999);//999=costo de la hab poner ahi
		this.hab = hab;
	}

	@Override
	public double calcularSubtotal(int cantidad) {
		// TODO Auto-generated method stub
		return this.hab.getCosto(cantidad);
	}

}
