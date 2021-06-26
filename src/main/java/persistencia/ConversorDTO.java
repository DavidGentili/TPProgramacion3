package persistencia;

import java.util.Iterator;
import java.util.TreeSet;

import clinica.Clinica;
import clinica.Factura;
import exceptions.MontoInvalidoException;

/**
 * Clase auxiliar que se utiliza para alvergar los metodos que convierten las
 * Clases DTO a la clase original y su inversa
 * 
 *
 */
public class ConversorDTO {

	/**
	 * Convierte una Clinica a una ClinicaDTO con todos los datos que nos interezan
	 * persistir
	 * 
	 * @return ClinicaDTO que poseera la informacion
	 */

	public static ClinicaDTO clinicaToDTO() {
		ClinicaDTO nueva = new ClinicaDTO();
		Clinica clinica = Clinica.getInstancia();
		nueva.setNombre(clinica.getNombre());
		nueva.setDireccion(clinica.getDireccion());
		nueva.setTelefono(clinica.getTelefono());
		nueva.setCiudad(clinica.getCiudad());
		nueva.setMedicos(clinica.getMedicos());
		nueva.setPacientesHist(clinica.getPacientesHist());
		nueva.setHistorial(clinica.getHistorial());
		nueva.setSalaPrivada(clinica.getSalaPrivada());
		nueva.setColaEspera(clinica.getColaEspera());
		nueva.setEnAtencion(clinica.getEnAtencion());
		nueva.setAsociados(clinica.getAsociados());

		nueva.setSueldoBasicoMedico(Clinica.getSueldoBasicoMedico());
		nueva.setCostoBasicohabitacionCompartida(Clinica.getCostoHabitacionCompartida());
		nueva.setCostoBasicoHabitacionPrivada(Clinica.getCostoHabitacionPrivada());
		nueva.setCostoBasicoTerapiaIntensiva(Clinica.getCostoTerapiaIntensiva());
		nueva.setNumeroTotalDeFacturas(Factura.getNumero());

		return nueva;
	}

	/**
	 * Se encarga de instancia una clinica con los valores de una clinica DTO
	 * 
	 * @param clinicaDTO ClinicaDTO que posee la informacion
	 */
	public static void DTOtoClinica(ClinicaDTO clinicaDTO) {
		Clinica clinica = Clinica.getInstancia(clinicaDTO.getNombre(), clinicaDTO.getDireccion(),
				clinicaDTO.getTelefono(), clinicaDTO.getCiudad());
		clinica.setMedicos(clinicaDTO.getMedicos());
		clinica.setPacientesHist(clinicaDTO.getPacientesHist());
		clinica.setHistorial(clinicaDTO.getHistorial());
		clinica.setSalaPrivada(clinicaDTO.getSalaPrivada());
		clinica.setColaEspera(clinicaDTO.getColaEspera());
		clinica.setEnAtencion(clinicaDTO.getEnAtencion());
		clinica.setAsociados(clinicaDTO.getAsociados());

		Factura.setNumero(clinicaDTO.getNumeroTotalDeFacturas());

		try {
			Clinica.setSueldoBasicoMedico(clinicaDTO.getSueldoBasicoMedico());
			Clinica.setCostoHabitacionPrivada(clinicaDTO.getCostoBasicoHabitacionPrivada());
			Clinica.setCostoHabitacionCompartida(clinicaDTO.getCostoBasicohabitacionCompartida());
			Clinica.setCostoTerapiaIntensiva(clinicaDTO.getCostoBasicoTerapiaIntensiva());
		} catch (MontoInvalidoException e) {
			e.printStackTrace();
		}

	}

}
