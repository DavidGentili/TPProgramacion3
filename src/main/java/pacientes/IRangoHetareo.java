package pacientes;

public interface IRangoHetareo {
	IRangoHetareo comparaIngreso(IRangoHetareo otro);

	IRangoHetareo comparaConNinio(IRangoHetareo otro);

	IRangoHetareo comparaConJoven(IRangoHetareo otro);

	IRangoHetareo comparaConMayor(IRangoHetareo otro);
}
