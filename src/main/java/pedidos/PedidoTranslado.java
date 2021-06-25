package pedidos;

import asociado.Asociado;
import clinica.Ambulancia;
import personas.Domicilio;

public class PedidoTranslado extends Pedido {

	public PedidoTranslado(Asociado asociado) {
		super(asociado);
	}

	@Override
	public void efectuaPedido() {
		this.a.solicitaTranslado();
		System.out.println("Estoy solicitando un transaldo\n"+this.a.informaEstado());
	}

}
