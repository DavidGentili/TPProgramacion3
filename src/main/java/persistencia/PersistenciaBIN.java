package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersistenciaBIN implements IPersistencia<Serializable> {

	FileInputStream fileInput;
	FileOutputStream fileOutput;
	ObjectInputStream objInput;
	ObjectOutputStream objOutput;

	@Override
	public void abrirInput(String nombre) throws IOException {
		fileInput = new FileInputStream(nombre);
		objInput = new ObjectInputStream(fileInput);

	}

	@Override
	public void abirOutput(String nombre) throws IOException {
		fileOutput = new FileOutputStream(nombre);
		objOutput = new ObjectOutputStream(fileOutput);
	}

	@Override
	public void cerrarInput() throws IOException {
		if (objInput != null)
			objInput.close();

	}

	@Override
	public void cerrarOutput() throws IOException {
		if (objOutput != null)
			objOutput.close();
	}

	@Override
	public void escribir(Serializable objeto) throws IOException {
		if (objOutput != null)
			objOutput.writeObject(objeto);

	}

	@Override
	public Serializable leer() throws IOException, ClassNotFoundException {
		Serializable respuesta = null;
		if (objInput != null)
			respuesta = (Serializable) this.objInput.readObject();
		return respuesta;
	}

}
