package pedidos;

import ambulancia.Ambulancia;
import asociado.Asociado;

public abstract class Pedido extends Thread {
	protected Asociado asociado;
	protected static Ambulancia a = Ambulancia.getInstance();

	public Pedido(Asociado asociado) {
		this.asociado = asociado;
	}

	public abstract void efectuaPedido();

	@Override
	public void run() {
		this.efectuaPedido();

	}

}
