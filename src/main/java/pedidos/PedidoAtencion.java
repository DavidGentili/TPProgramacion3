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

	@Override
	public void devuelveAmbulancia() {
		this.a.vueltaAtencion();
		
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.a.vuelveaClinica();
		
	}

}
