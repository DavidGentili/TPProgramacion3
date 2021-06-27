package pedidos;

import asociado.Asociado;

public class PedidoTranslado extends Pedido {

	public PedidoTranslado(Asociado asociado) {
		super(asociado);
	}

	@Override
	public void efectuaPedido() {
		this.a.solicitaTranslado();
	}

}
