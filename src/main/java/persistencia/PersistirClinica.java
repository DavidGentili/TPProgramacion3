package persistencia;

import java.io.IOException;
import java.io.Serializable;

public class PersistirClinica {

	/**
	 *Se encarga de
	 * @throws IOException
	 */
	public static void almacenarClinica() throws IOException {
		ClinicaDTO nueva = ConversorDTO.clinicaToDTO();
		IPersistencia <Serializable> binaria = new PersistenciaBIN();
		binaria.abirOutput("Clinica.bin");
		binaria.escribir(nueva);
		binaria.cerrarOutput();
	}

	/**
	 * Se encarga de restaurar una clinica, con los valores leidos de un archivo binario
	 */
	public static void restaurarClinica() {
		ClinicaDTO nueva = null;
		IPersistencia <Serializable> binaria = new PersistenciaBIN();
		try {
			binaria.abrirInput("Clinica.bin");
			nueva = (ClinicaDTO) binaria.leer();
			binaria.cerrarInput();
			ConversorDTO.DTOtoClinica(nueva);
		} catch (ClassNotFoundException | IOException e) {
			
		}
		
		
	}
}
