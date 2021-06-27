package ambulancia;

import pedidos.Pedido;
import pedidos.PedidoReparacion;

public class Operario {

	public Operario() {
	}

	public void SolicitarReparacion() {
		PedidoReparacion pedido = new PedidoReparacion();
		pedido.start();
	}

}
