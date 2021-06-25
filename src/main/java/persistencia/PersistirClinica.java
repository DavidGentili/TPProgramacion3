package persistencia;

import java.io.IOException;
import java.io.Serializable;

public class PersistirClinica {

	/**
	 * Se encarga de
	 * 
	 * @throws IOException
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
