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

	public void devuelveAmbulancia() {
		this.a.vuelveaClinica();
		System.out.println("Estoy devolviendo la ambulancia");
	}

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
