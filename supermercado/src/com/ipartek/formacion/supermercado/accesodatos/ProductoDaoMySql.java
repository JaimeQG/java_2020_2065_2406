package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.supermercado.modelos.Producto;

public class ProductoDaoMySql implements Dao<Producto> {

	private static final String URL = "jdbc:mysql://localhost:3306/supermercado?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	private static final String SQL_SELECT = "SELECT * FROM productos";
	private static final String SQL_SELECT_ID = "SELECT * FROM productos WHERE id = ?";

	private static final String SQL_INSERT = "INSERT INTO usuarios (email, password) VALUES (?, ?)";
	private static final String SQL_UPDATE = "UPDATE usuarios SET email = ?, password = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE facturas_has_productos, productos FROM productos JOIN facturas_has_productos ON productos.id=facturas_has_productos.productos_id WHERE productos.id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC para MySQL", e);
		}
	}

	// SINGLETON

	private ProductoDaoMySql() {
	}

	private final static ProductoDaoMySql INSTANCIA = new ProductoDaoMySql();

	public static ProductoDaoMySql getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Producto> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(SQL_SELECT)) {

			ArrayList<Producto> productos = new ArrayList<>();
			Producto producto;

			while (rs.next()) {

				producto = mapper(rs);
				productos.add(producto);
			}

			return productos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de productos", e);
		}
	}

	@Override
	public Producto obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				Producto producto = null;

				if (rs.next()) {
					producto = mapper(rs);
				}

				return producto;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recibir el usuario " + id, e);
		}
	}

	@Override
	public void crear(Producto objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(Producto objeto) {
		// TODO Auto-generated method stub

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
			throw new AccesoDatosException("No se ha podido borrar el producto " + id, e);
		}

	}

	/**
	 * Mapea un ResultSet a un objeto Producto
	 * 
	 * @param rs ResultSet de la consulta SQL
	 * @return Perro
	 * @throws SQLException
	 */
	private Producto mapper(ResultSet rs) throws SQLException {

		Producto producto = new Producto();

		producto.setId(rs.getLong("id"));
		producto.setNombre(rs.getString("nombre"));
		producto.setDescripcion(rs.getString("descripcion"));
		producto.setUrlImagen(rs.getString("url_imagen"));
		producto.setPrecio(rs.getBigDecimal("precio"));
		producto.setDescuento(rs.getInt("descuento"));
		producto.setUnidadMedida(rs.getString("unidad_medida"));
		producto.setPrecioUnidadMedida(rs.getBigDecimal("precio_unidad_medida"));
		producto.setCantidad(rs.getInt("cantidad"));

		return producto;
	}
}
