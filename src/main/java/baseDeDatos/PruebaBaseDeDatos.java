package baseDeDatos;

import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.DomicilioInvalido;
import exceptions.EspecialidadNoIndicadaException;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.PosgradoNoRegistradoExceptions;
import medicos.IMedico;
import medicos.MedicoFactory;
import personas.Domicilio;

public class PruebaBaseDeDatos {

	public static void main(String[] args) throws DomicilioInvalido, ContratacionNoRegistradaExceptions, EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions, EspecialidadNoIndicadaException {
		Domicilio dom = new Domicilio("Colon",531);
		IMedico m = MedicoFactory.getInstancia("Mario","Lopez",12351541,"2236141414", dom,"Mar del Plata",12345,"Cirujano","Temporario","Doctorado");
		OperacionesBDMedicos.creaTablaMedicos();
		OperacionesBDMedicos.agregaMedico(m);
	}
	

}
