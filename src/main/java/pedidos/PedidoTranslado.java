package pedidos;

import clinica.Ambulancia;

public class PedidoTranslado extends Pedido {

	public PedidoTranslado(String nombre, String apellido, Ambulancia a) {
		super(nombre, apellido, a);
	}

	@Override
	public void efectuaPedido() {
		//this.a.llamanTranslado();
	}
	
	

}
