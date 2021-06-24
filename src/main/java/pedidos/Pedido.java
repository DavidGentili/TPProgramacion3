package pedidos;

import clinica.Ambulancia;

public abstract class Pedido extends Thread {
	protected String nombre;
	protected String apellido;
	protected Ambulancia a;

	public Pedido(String nombre, String apellido, Ambulancia a) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.a = a;
	}
	
	public void devuelveAmbulancia() {
		a.devuelveAmbulancia();
	}
	
	public abstract void efectuaPedido();

	@Override
	public void run() {
		this.efectuaPedido();
		
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.devuelveAmbulancia();
	}
	
	
}
