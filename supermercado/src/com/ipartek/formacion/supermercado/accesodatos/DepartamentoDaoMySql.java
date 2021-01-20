package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ipartek.formacion.supermercado.modelos.Departamento;

public class DepartamentoDaoMySql implements Dao<Departamento> {

	private static final String SQL_SELECT = "SELECT * FROM departamentos";
	private static final String SQL_INSERT = "INSERT INTO departamentos (nombre, descripcion) VALUES (?,?)";
	private static final String SQL_TEST = "{call departamentos_test(?)}";
	private static final String SQL_DELETE = "DELETE FROM departamentos WHERE id = ?";

	private DataSource dataSource;

	// SINGLETON
	private DepartamentoDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/supermercado");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el JNDI de supermercado", e);
		}
	}

	private final static DepartamentoDaoMySql INSTANCIA = new DepartamentoDaoMySql();

	public static DepartamentoDaoMySql getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	private Connection obtenerConexion() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al obtener la conexión", e);
		}
	}

	@Override
	public Iterable<Departamento> obtenerTodos() {
		try (Connection con = obtenerConexion();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {

			ArrayList<Departamento> departamentos = new ArrayList<>();
			Departamento departamento;

			while (rs.next()) {
				departamento = new Departamento(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));

				departamentos.add(departamento);
			}

			return departamentos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de departamentos", e);
		}
	}

	@Override
	public void crear(Departamento departamento) {
		try (Connection con = obtenerConexion(); CallableStatement cs = con.prepareCall(SQL_TEST);) {

			cs.setString(1, departamento.getNombre());
			// cs.setString(2, departamento.getDescripcion());
			// cs.setString(3, "@resultado");

			// int numeroRegistrosInsertados = cs.executeUpdate();
			String numeroRegistros = null;

			try (ResultSet rs = cs.executeQuery()) {

				if (rs.next()) {
					numeroRegistros = rs.getString(1);
					System.out.println("Columna: " + numeroRegistros);
				}
				// if (numeroRegistrosInsertados != 1) {
				// throw new AccesoDatosException("Se han insertado " +
				// numeroRegistrosInsertados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el departamento " + departamento, e);
		}
	}

	@Override
	public Departamento crearYObtener(Departamento departamento) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

			ps.setString(1, departamento.getNombre());
			ps.setString(2, departamento.getDescripcion());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					departamento.setId(generatedKeys.getLong(1)); // Columna 1
				} else {
					throw new AccesoDatosException("Error al buscar el ID generado de departamento");
				}
			}

			return departamento;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el departamento " + departamento, e);
		}
	}

	@Override
	public void eliminar(Long id) {
		try (Connection con = obtenerConexion(); CallableStatement cs = con.prepareCall(SQL_DELETE);) {

			cs.setLong(1, id);

			int numeroRegistrosBorrados = cs.executeUpdate();

			if (numeroRegistrosBorrados != 1) {
				throw new AccesoDatosException("Se han borrado " + numeroRegistrosBorrados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el departamento " + id, e);
		}
	}
}