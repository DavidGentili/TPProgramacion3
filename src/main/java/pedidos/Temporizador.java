package pedidos;

import ambulancia.Ambulancia;

public class Temporizador extends Thread {

	private boolean encendido;

	public Temporizador() {
		encendido = true;
	}

	@Override
	public void run() {
		while (encendido) {
			espera();
			Ambulancia.getInstance().solicitaRetorno();
		}
	}

	protected void espera() {
		try {
			sleep(tiempoRandom());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna un tiempo en Milisegundos entre 5000 ms y 1000ms
	 * 
	 * @return tiempo en Milisegundo
	 */
	private static long tiempoRandom() {
		long numero;
		do {
			numero = (long) (10000 * Math.random());
		} while (numero > 4000);
		return numero;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

}
