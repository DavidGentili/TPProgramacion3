package pedidos;

import asociado.Asociado;
import clinica.Ambulancia;

public abstract class Pedido extends Thread {
	protected Asociado asociado;
	protected static Ambulancia a = Ambulancia.getInstance();

	public Pedido(Asociado asociado) {
		super();
		this.asociado = asociado;
	}

	public abstract void devuelveAmbulancia();

	public abstract void efectuaPedido();

	@Override
	public void run() {
		this.efectuaPedido();

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.devuelveAmbulancia();
	}

}
