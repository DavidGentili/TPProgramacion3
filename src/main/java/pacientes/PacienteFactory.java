package pacientes;

import personas.Domicilio;

public class PacienteFactory {
	public PacienteFactory() {}
	
	public static IRangoEtareo getInstance(String tipo, String nombre, String apellido, int dni, String telefono, Domicilio domicilio, String ciudad, int historiaClinica) {
		IRangoEtareo respuesta=null;
		if(tipo.equalsIgnoreCase("ninio"))
			respuesta=new Ninio(nombre,apellido,dni,telefono,domicilio,ciudad,historiaClinica);
		else if(tipo.equalsIgnoreCase("joven"))
			respuesta=new Joven(nombre,apellido,dni,telefono,domicilio,ciudad,historiaClinica);
		else if(tipo.equalsIgnoreCase("mayor"))
			respuesta=new Mayor(nombre,apellido,dni,telefono,domicilio,ciudad,historiaClinica);
		return respuesta;
	}
	
	public static IRangoEtareo getInstance(String tipo, String nombre, String apellido, int dni, int historiaClinica){
		IRangoEtareo respuesta=null;
		if(tipo.equalsIgnoreCase("ninio"))
			respuesta=new Ninio(nombre,apellido,dni,historiaClinica);
		else if(tipo.equalsIgnoreCase("joven"))
			respuesta=new Joven(nombre,apellido,dni,historiaClinica);
		else if(tipo.equalsIgnoreCase("mayor"))
			respuesta=new Mayor(nombre,apellido,dni,historiaClinica);
		return respuesta;
	}

}
