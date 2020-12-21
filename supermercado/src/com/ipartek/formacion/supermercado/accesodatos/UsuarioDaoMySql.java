package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.modelos.Usuario;

public class UsuarioDaoMySql implements DaoUsuario {
	private static final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	private static final String SQL_SELECT = "SELECT * FROM usuarios";
	private static final String SQL_SELECT_ID = "SELECT * FROM usuarios WHERE id = ?";
	private static final String SQL_SELECT_EMAIL = "SELECT * FROM usuarios WHERE email = ?";

	private static final String SQL_INSERT = "INSERT INTO usuarios (email, password) VALUES (?, ?)";
	private static final String SQL_UPDATE = "UPDATE usuarios SET email = ?, password = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	// SINGLETON

	private UsuarioDaoMySql() {
	}

	private final static UsuarioDaoMySql INSTANCIA = new UsuarioDaoMySql();

	public static UsuarioDaoMySql getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON
	@Override
	public Iterable<Usuario> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {

			ArrayList<Usuario> usuarios = new ArrayList<>();
			Usuario usuario;

			while (rs.next()) {
				usuario = new Usuario(rs.getLong("id"), rs.getString("email"), rs.getString("password"));

				usuarios.add(usuario);
			}

			return usuarios;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de usuarios", e);
		}
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Usuario usuario = null;

				if (rs.next()) {
					usuario = new Usuario(rs.getLong("id"), rs.getString("email"), rs.getString("password"));
				}

				return usuario;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recibir el usuario " + id, e);
		}
	}

	@Override
	public Usuario obtenerPorEmail(String email) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_EMAIL);) {

			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {
				Usuario usuario = null;

				if (rs.next()) {
					usuario = new Usuario(rs.getLong("id"), rs.getString("email"), rs.getString("password"));
				}

				return usuario;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recibir el usuario " + email, e);
		}
	}

	@Override
	public void crear(Usuario usuario) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_INSERT);) {

			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getPassword());

			int numeroRegistrosInsertados = ps.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el usuario " + usuario, e);
		}
	}

	@Override
	public void modificar(Usuario usuario) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_UPDATE);) {

			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getPassword());
			ps.setLong(3, usuario.getId());

			int numeroRegistrosModificados = ps.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException("Se han modificado " + numeroRegistrosModificados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar el usuario " + usuario, e);
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
			throw new AccesoDatosException("No se ha podido borrar el usuario " + id, e);
		}
	}

}