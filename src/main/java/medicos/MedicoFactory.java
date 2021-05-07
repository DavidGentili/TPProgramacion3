package medicos;

import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.PosgradoNoRegistradoExceptions;
import personas.Domicilio;

public class MedicoFactory {

	public MedicoFactory() {
		super();
	}

	public static IMedico getInstancia(String nombre, String apellido, int dni, int matricula, String especialidad,
			String contratacion, String posgrado) throws ContratacionNoIndicadaExceptions,
			ContratacionNoRegistradaExceptions, EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions {
		if (contratacion != null) {
			IMedico respuesta = null;
			IMedico medicoBase = new Medico(nombre, apellido, dni, matricula);
			respuesta = MedicoFactory.especializa(medicoBase, especialidad, contratacion, posgrado);
			return respuesta;
		} else
			throw new ContratacionNoIndicadaExceptions("No se indico contratacion");
	}

	public static IMedico getInstancia(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int matricula, String especialidad, String contratacion, String posgrado)
			throws ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions {
		if (contratacion != null) {
			IMedico respuesta = null;
			IMedico medicoBase = new Medico(nombre, apellido, dni, telefono, domicilio, ciudad, matricula);
			respuesta = MedicoFactory.especializa(medicoBase, especialidad, contratacion, posgrado);
			return respuesta;
		} else
			throw new ContratacionNoIndicadaExceptions("No se indico contratacion");
	}

	private static IMedico especializa(IMedico base, String especialidad, String contratacion, String posgrado)
			throws ContratacionNoRegistradaExceptions, EspecialidadNoRegistradaExceptions,
			PosgradoNoRegistradoExceptions {
		IMedico encapsulado = base;
		// Se crea el medico especializado o se lanza excepcion si no encuentra
		// especialidad
		if (especialidad != null) {
			switch (especialidad) {
			case "Cirujano":
				encapsulado = new Cirujano(base);
				break;
			case "Clinico":
				encapsulado = new Clinico(base);
				break;
			case "Pediatra":
				encapsulado = new Pediatra(base);
				break;
			default:
				throw new EspecialidadNoRegistradaExceptions("Especialida ingresada no encontrada");
			}
			base = encapsulado;
		}
		// Se crea Medico con posgrado o se propaga excepcion si no se encuentra
		if (posgrado != null) {
			switch (posgrado) {
			case "Magister":
				encapsulado = new Magister(base);
				break;
			case "Doctorado":
				encapsulado = new Doctorado(base);
				break;
			default:
				throw new PosgradoNoRegistradoExceptions("Posgrado ingresado no encontrado");
			}
			base = encapsulado;
		}

		switch (contratacion) {
		case "Temporario":
			encapsulado = new Temporario(base);
			break;
		case "Permanente":
			encapsulado = new Permanente(base);
			break;
		default:
			throw new ContratacionNoRegistradaExceptions("Contratacion ingresada no encontrada");
		}

		return encapsulado;
	}
}
