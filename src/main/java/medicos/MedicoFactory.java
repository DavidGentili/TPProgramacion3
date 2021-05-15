package medicos;

import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoRegistradaExceptions;
import exceptions.PosgradoNoRegistradoExceptions;
import personas.Domicilio;

/**
 * Se encarga de crear un medico segun se especifique, con su especialidad, su
 * contratacion y su posgrado
 *
 */
public class MedicoFactory {

	public MedicoFactory() {
		super();
	}

	/**
	 * 
	 * @param nombre       Nombre del medico
	 * @param apellido     apellido del medico
	 * @param dni          DNI del medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico
	 * @param posgrado     Posgrado del Medico
	 * @param contratacion Contratacion del Medico
	 * @return una instancia del medico, con sus respectivas especialidades,
	 *         posgrados y atributos
	 * @throws ContratacionNoIndicadaExceptions   si la contratacion no esta
	 *                                            indicada
	 * @throws ContratacionNoRegistradaExceptions si la contratacion no esta
	 *                                            registrada
	 * @throws EspecialidadNoRegistradaExceptions si la especialidad no esta
	 *                                            registrada
	 * @throws PosgradoNoRegistradoExceptions     si el posgrado no esta registrado
	 */
	public static IMedico getInstancia(String nombre, String apellido, int dni, int matricula, String especialidad,
			String posgrado, String contratacion) throws ContratacionNoIndicadaExceptions,
			ContratacionNoRegistradaExceptions, EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions {
		if (contratacion != null) {
			IMedico respuesta = null;
			IMedico medicoBase = new Medico(nombre, apellido, dni, matricula);
			respuesta = MedicoFactory.especializa(medicoBase, especialidad, posgrado, contratacion);
			return respuesta;
		} else
			throw new ContratacionNoIndicadaExceptions("No se indico contratacion");
	}

	/**
	 * 
	 * @param nombre       Nombre del medico
	 * @param apellido     apellido del medico
	 * @param dni          DNI del medico
	 * @param telefono     telefono del medico
	 * @param domicilio    Domicilio del medico
	 * @param ciudad       Ciudad del medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico
	 * @param posgrado     Posgrado del Medico
	 * @param contratacion Contratacion del Medico
	 * @return una instancia del medico, con sus respectivas especialidades,
	 *         posgrados y atributos
	 * @throws ContratacionNoIndicadaExceptions   si la contratacion no esta
	 *                                            indicada
	 * @throws ContratacionNoRegistradaExceptions si la contratacion no esta
	 *                                            registrada
	 * @throws EspecialidadNoRegistradaExceptions si la especialidad no esta
	 *                                            registrada
	 * @throws PosgradoNoRegistradoExceptions     si el posgrado no esta registrado
	 */
	public static IMedico getInstancia(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int matricula, String especialidad, String posgrado, String contratacion)
			throws ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions {
		if (contratacion != null) {
			IMedico respuesta = null;
			IMedico medicoBase = new Medico(nombre, apellido, dni, telefono, domicilio, ciudad, matricula);
			respuesta = MedicoFactory.especializa(medicoBase, especialidad, posgrado, contratacion);
			return respuesta;
		} else
			throw new ContratacionNoIndicadaExceptions("No se indico contratacion");
	}

	/**
	 * 
	 * Se encarga de adicionar las especialidades, los posgrados y la contratacion
	 * segun corresponda
	 * 
	 * @param base         medico base creado
	 * @param especialidad Especialidad del medico
	 * @param posgrado     Posgrado del medico
	 * @param contratacion Contratacion del medico
	 * @return
	 * @throws ContratacionNoRegistradaExceptions
	 * @throws EspecialidadNoRegistradaExceptions
	 * @throws PosgradoNoRegistradoExceptions
	 */
	private static IMedico especializa(IMedico base, String especialidad, String posgrado, String contratacion)
			throws ContratacionNoRegistradaExceptions, EspecialidadNoRegistradaExceptions,
			PosgradoNoRegistradoExceptions {
		IMedico encapsulado = base;
		// Se crea el medico especializado o se lanza excepcion si no encuentra
		// especialidad
		if (especialidad != null) {
			switch (especialidad.toLowerCase()) {
			case "cirujano":
				encapsulado = new Cirujano(base);
				break;
			case "clinico":
				encapsulado = new Clinico(base);
				break;
			case "pediatra":
				encapsulado = new Pediatra(base);
				break;
			default:
				throw new EspecialidadNoRegistradaExceptions("Especialida ingresada no encontrada");
			}
			base = encapsulado;
		}
		// Se crea Medico con posgrado o se propaga excepcion si no se encuentra
		if (posgrado != null) {
			switch (posgrado.toLowerCase()) {
			case "magister":
				encapsulado = new Magister(base);
				break;
			case "doctorado":
				encapsulado = new Doctorado(base);
				break;
			default:
				throw new PosgradoNoRegistradoExceptions("Posgrado ingresado no encontrado");
			}
			base = encapsulado;
		}

		switch (contratacion.toLowerCase()) {
		case "temporario":
			encapsulado = new Temporario(base);
			break;
		case "permanente":
			encapsulado = new Permanente(base);
			break;
		default:
			throw new ContratacionNoRegistradaExceptions("Contratacion ingresada no encontrada");
		}

		return encapsulado;
	}
}
