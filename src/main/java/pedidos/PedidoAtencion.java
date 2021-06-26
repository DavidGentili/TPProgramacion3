package pedidos;

import asociado.Asociado;

public class PedidoAtencion extends Pedido {

	public PedidoAtencion(Asociado asociado) {
		super(asociado);
	}

	@Override
	public void efectuaPedido() {
		this.a.solicitaAtencion();
		System.out.println("Estoy pidiemdo atencion");
	}

}
