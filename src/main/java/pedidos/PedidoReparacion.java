package pedidos;

import ambulancia.Ambulancia;
import asociado.Asociado;

public class PedidoReparacion extends Thread {

	Ambulancia ambulancia = Ambulancia.getInstance();

	public PedidoReparacion() {

	}

	@Override
	public void run() {
		this.ambulancia.solicitaReparacion();
	}

}
