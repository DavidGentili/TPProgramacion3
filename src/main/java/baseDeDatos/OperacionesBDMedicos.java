package baseDeDatos;

import java.sql.SQLException;

import medicos.IMedico;

public class OperacionesBDMedicos {

	public static void creaTablaMedicos() {
		String sentenciasSQL[] = { "Nombre VARCHAR(15) NOT NULL", "Apellido VARCHAR(15) NOT NULL",
				"documento INT NOT NULL", "telefono VARCHAR(10)", "Calle VARCHAR(20)", "altura INT",
				"Ciudad VARCHAR(20)", "Matricula INT NOT NULL", "Especialidad VARCHAR(15) NOT NULL",
				"Contratacion VARCHAR(15)", "Posgrado VARCHAR(15)", "PRIMARY KEY(Matricula)" };
		BaseDeDatos bd = BaseDeDatos.getInstance();
		if (!bd.existeTabla("Medicos")) {
			try {
				bd.creaTabla("Medicos", sentenciasSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void agregaMedico(IMedico medico) {
		String sql = "insert into Medicos VALUES (";
		sql += "'" + medico.getNombre() + "', '" + medico.getApellido() + "', " + medico.getDni() + ", '"
				+ medico.getTelefono() + "', '" + medico.getDomicilio().getCalle() + "', "
				+ medico.getDomicilio().getNumero() + ", '" + medico.getCiudad() + "'," + medico.getMatricula() + ", '" + medico.getEspecialidad()
				+ "', '" + medico.getContratacion() + "', '" + medico.getPosgrado() + "')";
		BaseDeDatos bd = BaseDeDatos.getInstance();
		System.out.println(sql);
		if(bd.existeTabla("Medicos")) {
			try {
				bd.agregar(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
