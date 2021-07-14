package baseDeDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			if(i!=0)
				sql += ", ";
			sql += parametrosSQL[i];
		}
		sql += ")";
		System.out.println(sql);
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

	public void agregar(String sql) {
		try {
			conectar();
			sentencia = conexion.createStatement();
			sentencia.execute(sql);
		} catch (SQLException e) {

		}
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

}
