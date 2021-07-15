package baseDeDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.DomicilioInvalido;
import medicos.IMedico;
import persistencia.MedicoDAO;
import personas.Domicilio;

public class BaseDeDatos {
	private static BaseDeDatos instancia = null;
	private Connection conexion;
	private Statement sentencia;
	private PreparedStatement ps;

	private BaseDeDatos() {
		this.cargarDriver();
	}

	public static BaseDeDatos getInstance() {
		if (instancia == null)
			instancia = new BaseDeDatos();
		return instancia;
	}

	public void cargarDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void conectar() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/Clinica", "david", "david1234");
		} catch (SQLException e) {
			Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);

		}
	}

	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void creaTabla(String tabla, String[] parametrosSQL) throws SQLException {
		conectar();
		sentencia = conexion.createStatement();
		String sql = "create table " + tabla + "(";
		for (int i = 0; i < parametrosSQL.length; i++) {
			if (i != 0)
				sql += ", ";
			sql += parametrosSQL[i];
		}
		sql += ")";
		sentencia.execute(sql);
		desconectar();
	}

	public void eliminarTabla(String tabla) {
		conectar();
		try {
			sentencia = conexion.createStatement();
			sentencia.execute("drop table " + tabla);
			sentencia.close();
		} catch (SQLException e) {

		} finally {
			desconectar();
		}
	}

	public boolean existeTabla(String tabla) {
		boolean respuesta = false;
		try {
			conectar();
			DatabaseMetaData md = conexion.getMetaData();
			ResultSet rs = md.getTables(null, null, tabla, null);
			if (rs.next())
				respuesta = true;
		} catch (SQLException e) {
			Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, e);
		}
		desconectar();
		return respuesta;
	}

	@SuppressWarnings("finally")
	public ResultSet consultar(String sql) {
		ResultSet respuesta = null;
		try {
			conectar();
			sentencia = conexion.createStatement();
			respuesta = sentencia.executeQuery(sql);
		} catch (SQLException e) {

		} finally {
			desconectar();
			return respuesta;
		}
	}

	public void creaTablaMedicos() {
		String sentenciasSQL[] = { "Nombre VARCHAR(15) NOT NULL", "Apellido VARCHAR(15) NOT NULL",
				"documento INT NOT NULL", "telefono VARCHAR(10)", "Calle VARCHAR(20)", "altura INT",
				"Ciudad VARCHAR(20)", "Matricula INT NOT NULL", "Especialidad VARCHAR(15) NOT NULL",
				"Contratacion VARCHAR(15)", "Posgrado VARCHAR(15)", "PRIMARY KEY(Matricula)" };
		if (!this.existeTabla("Medicos")) {
			try {
				this.creaTabla("Medicos", sentenciasSQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void agregaMedico(IMedico medico) {
		String sql = "insert into Medicos VALUES (";
		sql += "'" + medico.getNombre() + "', '" + medico.getApellido() + "', " + medico.getDni() + ", '"
				+ medico.getTelefono() + "', '" + medico.getDomicilio().getCalle() + "', "
				+ medico.getDomicilio().getNumero() + ", '" + medico.getCiudad() + "'," + medico.getMatricula() + ", '"
				+ medico.getEspecialidad() + "', '" + medico.getContratacion() + "', '" + medico.getPosgrado() + "')";

		if (existeTabla("Medicos")) {
			try {
				conectar();
				sentencia = conexion.createStatement();
				sentencia.execute(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<IMedico> getListaMedicos() throws SQLException {
		ArrayList<IMedico> lista = new ArrayList<IMedico>();
		MedicoDAO aux = null;
		IMedico medico = null;
		String sql = "select * from Medicos";
		ResultSet res = BaseDeDatos.getInstance().consultar(sql);
		while (res.next()) {
			aux = new MedicoDAO();
			aux.setNombre(res.getString("Nombre"));
			aux.setApellido(res.getString("Apellido"));
			aux.setDni(res.getInt("Documento"));
			aux.setTelefono(res.getString("Telefono"));
			try {
				aux.setDomicilio(new Domicilio(res.getString("Calle"), res.getInt("Altura")));
			} catch (NumberFormatException | DomicilioInvalido | SQLException e) {
				aux.setDomicilio(null);
			}
			aux.setCiudad(res.getString("Ciudad"));
			aux.setMatricula(res.getInt("Matricula"));
			aux.setEspecialidad(res.getString("Especialidad"));
			aux.setContratacion(res.getString("Contratacion"));
			aux.setPosgrado(res.getString("Posgrado"));
			lista.add(aux.getMedico());
		}
		BaseDeDatos.getInstance().desconectar();

		return lista;
	}

}
