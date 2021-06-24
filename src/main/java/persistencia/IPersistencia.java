package persistencia;

import java.io.IOException;
import java.io.Serializable;

public interface IPersistencia <E> {
	void abrirInput(String nombre) throws IOException;
	void abirOutput(String nombre) throws IOException;
	void cerrarInput() throws IOException;
	void cerrarOutput() throws IOException;
	void escribir(E objeto) throws IOException;
	E leer()throws IOException, ClassNotFoundException;

}
