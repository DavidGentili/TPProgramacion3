package habitaciones;

public class HabitacionFactory {
	
	public static Habitacion getInstance(String tipo) {
		Habitacion respuesta=null;
		if(tipo.equalsIgnoreCase("intensiva"))
			respuesta= new TerapiaIntensiva(10);
		else if(tipo.equalsIgnoreCase("compartida"))
			respuesta=new HabitacionCompartida();
		else if(tipo.equalsIgnoreCase("privada"))
			respuesta = new HabitacionPrivada();
		return respuesta;
	}

}
