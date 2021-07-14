package baseDeDatos;

import java.sql.SQLException;

public class OperacionesBDMedicos {
	
	public static void creaTablaMedicos() {
		String sentenciasSQL[] ={
				"Nombre VARCHAR(15) NOT NULL",
				"Apellido VARCHAR(15) NOT NULL",
				"documento INT NOT NULL",
				"telefono VARCHAR(10)",
				"Calle VARCHAR(20)",
				"altura INT",
				"Ciudad VARCHAR(20)",
				"Matricula INT NOT NULL",
				"Especialidad VARCHAR(15) NOT NULL",
				"Contratacion VARCHAR(15)",
				"Posgrado VARCHAR(15)",
			};
		BaseDeDatos bd = BaseDeDatos.getInstance();
		if(!bd.existeTabla("Medicos")) {
			try {
				bd.creaTabla("Medicos", sentenciasSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
}
