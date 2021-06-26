package pedidos;

import clinica.Ambulancia;

public class Temporizador extends Thread {

	@Override
	public void run() {
		while (true) {
			espera();
			Ambulancia.getInstance().vuelveaClinica();;
		}
	}

	protected void espera() {
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
