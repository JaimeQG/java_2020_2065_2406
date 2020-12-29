package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.modelos.Cliente;

public class ClienteDaoMySql implements Dao<Cliente> {

	private static final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	private static final String SQL_SELECT = "SELECT * FROM clientes";
	private static final String SQL_SELECT_ID = "SELECT * FROM clientes WHERE id = ?";

	private static final String SQL_INSERT = "INSERT INTO clientes (nombre, apellidos, cif, fecha_nacimiento) VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE clientes SET nombre = ?, apellidos = ?, cif = ?, fecha_nacimiento = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM clientes WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	// SINGLETON

	private ClienteDaoMySql() {
	}

	private final static ClienteDaoMySql INSTANCIA = new ClienteDaoMySql();

	public static ClienteDaoMySql getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Cliente> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {

			ArrayList<Cliente> clientes = new ArrayList<>();
			Cliente cliente;

			while (rs.next()) {

				cliente = mapper(rs);
				clientes.add(cliente);
			}

			return clientes;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de clientes", e);
		}
	}

	@Override
	public Cliente obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Cliente cliente = null;

				if (rs.next()) {
					cliente = mapper(rs);
				}

				return cliente;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recibir el usuario " + id, e);
		}
	}

	@Override
	public void crear(Cliente cliente) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());
			ps.setString(3, cliente.getCif());

			Date fecha = null;
			if (cliente.getFechaNacimiento() != null) {
				fecha = Date.valueOf(cliente.getFechaNacimiento());
			}

			ps.setDate(4, fecha);

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido crear el cliente " + cliente, e);
		}

	}

	@Override
	public void modificar(Cliente cliente) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getApellidos());
			ps.setString(3, cliente.getCif());

			Date fecha = null;
			if (cliente.getFechaNacimiento() != null) {
				fecha = Date.valueOf(cliente.getFechaNacimiento());
			}

			ps.setDate(4, fecha);
			ps.setLong(5, cliente.getId());

			int numeroRegistrosModificados = ps.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException("Se han modificado " + numeroRegistrosModificados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar el cliente " + cliente, e);
		}

	}

	@Override
	public void eliminar(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {

			ps.setLong(1, id);

			int numeroRegistrosBorrados = ps.executeUpdate();

			if (numeroRegistrosBorrados != 1) {
				throw new AccesoDatosException("Se han borrado " + numeroRegistrosBorrados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido borrar el cliente " + id, e);
		}
	}

	/**
	 * Mapea un ResultSet a un objeto Producto
	 * 
	 * @param rs ResultSet de la consulta SQL
	 * @return Perro
	 * @throws SQLException
	 */
	private Cliente mapper(ResultSet rs) throws SQLException {

		Cliente cliente = new Cliente();

		cliente.setId(rs.getLong("id"));
		cliente.setNombre(rs.getString("nombre"));
		cliente.setApellidos(rs.getString("apellidos"));
		cliente.setCif(rs.getString("cif"));
		cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));

		return cliente;
	}
}
