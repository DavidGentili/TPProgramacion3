package medicos;

import exceptions.ContratacionNoIndicadaExceptions;
import exceptions.ContratacionNoRegistradaExceptions;
import exceptions.EspecialidadNoIndicadaException;
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
			String posgrado, String contratacion) throws ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions, EspecialidadNoIndicadaException {
		IMedico respuesta = null;
		if (especialidad != null) {
			IMedico medicoEspecializado = null;
			respuesta = medicoEspecializado = MedicoFactory.especializa(nombre, apellido, dni, matricula, especialidad);
			if (contratacion != null) {
				DecoradorMedicoContratacion medicoConContratacion = null;
				respuesta = medicoConContratacion = MedicoFactory.agregaContratacion(medicoEspecializado, contratacion);
				if (posgrado != null) {
					DecoradorMedicoPosgrado medicoPosgrado = null;
					respuesta = medicoPosgrado = MedicoFactory.agregaPosgrado(medicoConContratacion, posgrado);
				}
			}
			return respuesta;
		} else
			throw new EspecialidadNoIndicadaException("No se indico especialidad");
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
	 * @throws EspecialidadNoIndicadaException    si la especialidad indicada no se
	 *                                            encuentra
	 */
	public static IMedico getInstancia(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int matricula, String especialidad, String posgrado, String contratacion)
			throws ContratacionNoIndicadaExceptions, ContratacionNoRegistradaExceptions,
			EspecialidadNoRegistradaExceptions, PosgradoNoRegistradoExceptions, EspecialidadNoIndicadaException {
		IMedico respuesta = null;
		if (especialidad != null) {
			IMedico medicoEspecializado = null;
			respuesta = medicoEspecializado = MedicoFactory.especializa(nombre, apellido, dni, telefono, domicilio,
					ciudad, matricula, especialidad);
			if (contratacion != null) {
				DecoradorMedicoContratacion medicoConContratacion = null;
				respuesta = medicoConContratacion = MedicoFactory.agregaContratacion(medicoEspecializado, contratacion);
				if (posgrado != null) {
					DecoradorMedicoPosgrado medicoPosgrado = null;
					respuesta = medicoPosgrado = MedicoFactory.agregaPosgrado(medicoConContratacion, posgrado);
				}
			}
			return respuesta;
		} else
			throw new EspecialidadNoIndicadaException("No se indico especialidad");
	}

	/**
	 * Retorna un medico concreto con su respectiva especialidad
	 * 
	 * @param nombre       Nombre del medico
	 * @param apellido     apellido del medico
	 * @param dni          Dni del medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico
	 * @return IMedico especializado
	 * @throws EspecialidadNoRegistradaExceptions Si la especialidad ingresada no
	 *                                            corresponde con las resgistradas.
	 */
	private static IMedico especializa(String nombre, String apellido, int dni, int matricula, String especialidad)
			throws EspecialidadNoRegistradaExceptions {
		IMedico respuesta = null;
		if (especialidad.equalsIgnoreCase("pediatra"))
			respuesta = new Pediatra(nombre, apellido, dni, matricula);
		else {
			if (especialidad.equalsIgnoreCase("cirujano"))
				respuesta = new Cirujano(nombre, apellido, dni, matricula);
			else {
				if (especialidad.equalsIgnoreCase("clinico"))
					respuesta = new Clinico(nombre, apellido, dni, matricula);
				else
					throw new EspecialidadNoRegistradaExceptions("Especialidad ingresada no encontrada");
			}
		}
		return respuesta;
	}

	/**
	 * Retorna un medico concreto con su respectiva especialidad
	 * 
	 * @param nombre       Nombre del medico
	 * @param apellido     apellido del medico
	 * @param dni          Dni del medico
	 * @param telefono     Telefono del medico
	 * @param domicilio    Domicilio del medico
	 * @param ciudad       Ciudad del medico
	 * @param matricula    Matricula del medico
	 * @param especialidad Especialidad del medico
	 * @return IMedico especializado
	 * @throws EspecialidadNoRegistradaExceptions Si la especialidad ingresada no
	 *                                            corresponde con las resgistradas.
	 */
	private static IMedico especializa(String nombre, String apellido, int dni, String telefono, Domicilio domicilio,
			String ciudad, int matricula, String especialidad) throws EspecialidadNoRegistradaExceptions {
		IMedico respuesta = null;
		if (especialidad.equalsIgnoreCase("pediatra"))
			respuesta = new Pediatra(nombre, apellido, dni, telefono, domicilio, ciudad, matricula);
		else {
			if (especialidad.equalsIgnoreCase("cirujano"))
				respuesta = new Cirujano(nombre, apellido, dni, telefono, domicilio, ciudad, matricula);
			else {
				if (especialidad.equalsIgnoreCase("clinico"))
					respuesta = new Clinico(nombre, apellido, dni, telefono, domicilio, ciudad, matricula);
				else
					throw new EspecialidadNoRegistradaExceptions("Especialidad ingresada no encontrada");
			}
		}
		return respuesta;
	}

	/**
	 * Retorna un medico decorado con su respectiva contratacion
	 * 
	 * @param medicoEspecializado un medico ya instanciado con su contratacion
	 * @param contratacion        un tipo de contratacion
	 * @return un DecoradorMedicoContratacion, con el medico decorado con su
	 *         contratacion
	 * @throws ContratacionNoRegistradaExceptions si la contratacion indicada no
	 *                                            esta registrada.
	 */
	private static DecoradorMedicoContratacion agregaContratacion(IMedico medicoEspecializado, String contratacion)
			throws ContratacionNoRegistradaExceptions {
		DecoradorMedicoContratacion respuesta = null;
		if (contratacion.equalsIgnoreCase("Temporario"))
			respuesta = new Temporario(medicoEspecializado);
		else {
			if (contratacion.equalsIgnoreCase("Permanente"))
				respuesta = new Permanente(medicoEspecializado);
			else
				throw new ContratacionNoRegistradaExceptions("contratacion ingresada no encontrada");
		}
		return respuesta;
	}

	/**
	 * Retorna un medicon con su respectivo posgrado
	 * 
	 * @param medicoConContratacion Un medico decorado con su contratacion
	 * @param posgrado              el posgrado con el que se desea decorar
	 * @return Un medico decorado con su posgrado
	 * @throws PosgradoNoRegistradoExceptions Si el posgrado ingresado no se
	 *                                        encentra registrado
	 */
	private static DecoradorMedicoPosgrado agregaPosgrado(DecoradorMedicoContratacion medicoConContratacion,
			String posgrado) throws PosgradoNoRegistradoExceptions {
		DecoradorMedicoPosgrado respuesta = null;
		if (posgrado.equalsIgnoreCase("Magister"))
			respuesta = new Magister(medicoConContratacion);
		else {
			if (posgrado.equalsIgnoreCase("Doctorado"))
				respuesta = new Doctorado(medicoConContratacion);
			else
				throw new PosgradoNoRegistradoExceptions("posgrado ingresado no encontrado");
		}
		return respuesta;
	}
}
