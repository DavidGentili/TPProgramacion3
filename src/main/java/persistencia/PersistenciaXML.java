package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class PersistenciaXML implements IPersistencia<Serializable> {
	private FileInputStream fileInput;
	private FileOutputStream fileOutput;
	private XMLDecoder decoder;
	private XMLEncoder encoder;

	@Override
	public void abrirInput(String nombre) throws IOException {
		fileInput = new FileInputStream(nombre);
		decoder = new XMLDecoder(fileInput);
	}

	@Override
	public void abirOutput(String nombre) throws IOException {
		fileOutput = new FileOutputStream(nombre);
		encoder = new XMLEncoder(fileOutput);

	}

	@Override
	public void cerrarInput() throws IOException {
		if (this.decoder != null)
			this.decoder.close();

	}

	@Override
	public void cerrarOutput() throws IOException {
		if (this.encoder != null)
			this.encoder.close();
	}

	@Override
	public void escribir(Serializable objeto) throws IOException {
		if (this.encoder != null)
			encoder.writeObject(objeto);
	}

	@Override
	public Serializable leer() throws IOException, ClassNotFoundException {
		Serializable aux = null;
		if (decoder != null)
			aux = (Serializable) decoder.readObject();
		return aux;
	}



}
