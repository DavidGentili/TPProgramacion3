package pedidos;

import asociado.Asociado;

public class PedidoReparacion extends Pedido {

	public PedidoReparacion(Asociado asociado) {
		super(asociado);
	}

	@Override
	public void efectuaPedido() {
		this.a.solicitaReparacion();
	}

	@Override
	public void devuelveAmbulancia() {
		this.a.vueltaTaller();
		
	}

}
