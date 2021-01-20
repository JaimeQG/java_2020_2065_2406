package com.ipartek.formacion.medicamentos.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.medicamentos.modelos.Medicamento;

public class MedicamentoDaoMySql implements Dao<Medicamento> {
	private static final String URL = "jdbc:mysql://localhost:3306/medicamentos?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	private static final String SQL_SELECT = "SELECT * FROM medicamentos";
	private static final String SQL_SELECT_ID = "SELECT * FROM medicamentos WHERE id = ?";
	private static final String SQL_COUNT_ID = "SELECT COUNT(id) FROM medicamentos";

	private static final String SQL_INSERT = "INSERT INTO medicamentos (referencia, nombre, precio) VALUES (?, ?,?)";
	private static final String SQL_DELETE = "DELETE FROM medicamentos WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	// SINGLETON

	private MedicamentoDaoMySql() {

	}

	private final static MedicamentoDaoMySql INSTANCIA = new MedicamentoDaoMySql();

	public static MedicamentoDaoMySql getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Medicamento> obtenerTodos() {
		return obtenerRegistros(SQL_SELECT);
	}

	private Iterable<Medicamento> obtenerRegistros(String consulta) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(consulta)) {

			ArrayList<Medicamento> medicamentos = new ArrayList<>();
			Medicamento medicamento;

			while (rs.next()) {

				medicamento = mapper(rs);
				medicamentos.add(medicamento);
			}

			return medicamentos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de medicamentos", e);
		}
	}

	@Override
	public Medicamento obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Medicamento medicamento = null;

				if (rs.next()) {
					medicamento = mapper(rs);
				}

				return medicamento;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recibir el producto " + id, e);
		}
	}

	@Override
	public void crear(Medicamento medicamento) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, medicamento.getReferencia());
			ps.setString(2, medicamento.getNombre());
			ps.setBigDecimal(3, medicamento.getPrecio());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el producto " + medicamento, e);
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
			throw new AccesoDatosException("No se ha podido borrar el medicamento " + id, e);
		}
	}

	@Override
	public Medicamento crearYObtener(Medicamento medicamento) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

			ps.setString(1, medicamento.getReferencia());
			ps.setString(2, medicamento.getNombre());
			ps.setBigDecimal(3, medicamento.getPrecio());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					medicamento.setId(generatedKeys.getLong(1)); // Columna 1
				} else {
					throw new AccesoDatosException("Error al buscar el ID generado de medicamento");
				}
			}

			return medicamento;

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el medicamento " + medicamento, e);
		}
	}

	@Override
	public int numeroRegistros() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_COUNT_ID)) {

			int countRegistros = 0;

			while (rs.next()) {

				countRegistros = rs.getInt(1);
			}

			return countRegistros;

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de medicamentos", e);
		}
	}

	/**
	 * Mapea un ResultSet a un objeto Medicamento
	 * 
	 * @param rs ResultSet de la consulta SQL
	 * @return Medicamento
	 * @throws SQLException
	 */
	private Medicamento mapper(ResultSet rs) throws SQLException {

		Medicamento medicamento = new Medicamento(null, null, null, null);

		medicamento.setId(rs.getLong("id"));
		medicamento.setReferencia(rs.getString("referencia"));
		medicamento.setNombre(rs.getString("nombre"));
		medicamento.setPrecio(rs.getBigDecimal("precio"));

		return medicamento;
	}
}
