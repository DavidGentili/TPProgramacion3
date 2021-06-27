package persistencia;

import java.io.IOException;
import java.io.Serializable;

/**
 * Es una clase que utiliza dos metodos estaticos, para persistir y recuperar la
 * instancia actual de la clinica
 *
 */
public class PersistirClinica {

	/**
	 * Se encarga de almacenar los valores que se necesitan persistir en clinica en
	 * un archivo binario
	 * 
	 * @throws IOException Si hay algun error al abrir o cerrar el archivo
	 */
	public static void almacenarClinica() throws IOException {
		ClinicaDTO nueva = ConversorDTO.clinicaToDTO();

		IPersistencia<Serializable> binaria = new PersistenciaBIN();
		binaria.abrirOutput("Clinica.bin");
		binaria.escribir(nueva);
		binaria.cerrarOutput();

		/*
		 * IPersistencia<Serializable> xml = new PersistenciaXML();
		 * xml.abrirOutput("Clinica.xml"); xml.escribir(nueva); xml.cerrarOutput();
		 */
	}

	/**
	 * Se encarga de restaurar una clinica, con los valores leidos de un archivo
	 * binario
	 */
	public static void restaurarClinica() {
		ClinicaDTO nueva = null;
		// IPersistencia<Serializable> xml = new PersistenciaXML();
		IPersistencia<Serializable> binaria = new PersistenciaBIN();
		try {
			binaria.abrirInput("Clinica.bin");
			nueva = (ClinicaDTO) binaria.leer();
			binaria.cerrarInput();
			ConversorDTO.DTOtoClinica(nueva);
			/*
			 * xml.abrirInput("Clinica.xml"); nueva = (ClinicaDTO) xml.leer();
			 * xml.cerrarInput(); ConversorDTO.DTOtoClinica(nueva);
			 */
		} catch (ClassNotFoundException | IOException e) {

		}

	}
}
