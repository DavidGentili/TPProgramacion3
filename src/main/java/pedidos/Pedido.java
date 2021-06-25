package pedidos;

import clinica.Ambulancia;
import personas.Domicilio;

public abstract class Pedido extends Thread {
	protected String nombre;
	protected String apellido;
	protected int dni;
	protected Domicilio domicilio;
	protected static Ambulancia a = Ambulancia.getInstance();

	public Pedido(String nombre, String apellido, int dni, Domicilio domicilio) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni=dni;
		this.domicilio=domicilio;
	}

	public void devuelveAmbulancia() {
		this.a.vuelveaClinica();
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

		this.a.vuelveaClinica();
	}

}
