package pacientes;

public interface IRangoEtareo {
	IRangoEtareo comparaIngreso(IRangoEtareo otro);

	IRangoEtareo comparaConNinio(IRangoEtareo otro);

	IRangoEtareo comparaConJoven(IRangoEtareo otro);

	IRangoEtareo comparaConMayor(IRangoEtareo otro);
}
