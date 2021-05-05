package pacientes;

public interface IRangoHetareo {
	IRangoHetareo comparaIngreso(IRangoHetareo otro);

	IRangoHetareo comparaConNiño(IRangoHetareo otro);

	IRangoHetareo comparaConJoven(IRangoHetareo otro);

	IRangoHetareo comparaConMayor(IRangoHetareo otro);
}
