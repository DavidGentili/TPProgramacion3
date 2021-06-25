package pedidos;

import clinica.Ambulancia;
import personas.Domicilio;

public class PedidoAtencion extends Pedido {

	public PedidoAtencion(String nombre, String apellido,int dni,Domicilio domicilio) {
		super(nombre, apellido,dni,domicilio);
	}

	@Override
	public void efectuaPedido() {
		this.a.solicitaAtencion();
	}

}
