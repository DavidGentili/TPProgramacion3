package habitaciones;

public class HabitacionFactory {
	
	public static Habitacion getInstance(String tipo) {
		Habitacion respuesta=null;
		if(tipo.equalsIgnoreCase("intensiva"))
			respuesta = TerapiaIntensiva.getInstance();
		else if(tipo.equalsIgnoreCase("compartida"))
			respuesta = HabitacionCompartida.getInstance();
		else if(tipo.equalsIgnoreCase("privada"))
			respuesta = HabitacionPrivada.getInstance();
		return respuesta;
	}

}
