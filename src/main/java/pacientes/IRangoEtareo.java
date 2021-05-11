package pacientes;

import java.util.ArrayList;

import prestaciones.IPrestacion;

public interface IRangoEtareo {
	IRangoEtareo comparaIngreso(IRangoEtareo otro);

	IRangoEtareo comparaConNinio(IRangoEtareo otro);

	IRangoEtareo comparaConJoven(IRangoEtareo otro);

	IRangoEtareo comparaConMayor(IRangoEtareo otro);
	
	void agregarPrestacion(IPrestacion prestacion);
	
	public ArrayList<IPrestacion> getPrestaciones();
	
}
