package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ipartek.formacion.supermercado.modelos.Departamento;
import com.ipartek.formacion.supermercado.modelos.Producto;

public class ProductoDaoMySql implements Dao<Producto> {

	private static final String SQL_SELECT = "{call productos_obtener_todos()}";
	private static final String SQL_SELECT_BORRADOS = "{call productos_obtener_borrados()}";
	private static final String SQL_SELECT_ID = "{call productos_obtener_por_id(?)}";

	private static final String SQL_INSERT = "{call productos_insertar(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String SQL_UPDATE = "{call productos_modificar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	private static final String SQL_DELETE = "{call productos_borrar(?)}";
	private static final String SQL_UNDELETE = "{call productos_recuperar(?)}";

	private DataSource dataSource;

	// SINGLETON

	private ProductoDaoMySql() {
		try {
			InitialContext initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/supermercado");
		} catch (NamingException e) {
			throw new AccesoDatosException("No se ha encontrado el JNDI de supermercado", e);
		}
	}

	private final static ProductoDaoMySql INSTANCIA = new ProductoDaoMySql();

	public static ProductoDaoMySql getInstancia() {
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
	public Iterable<Producto> obtenerTodos() {
		return obtenerRegistros(SQL_SELECT);
	}

	@Override
	public Iterable<Producto> obtenerBorrados() {
		return obtenerRegistros(SQL_SELECT_BORRADOS);
	}

	private Iterable<Producto> obtenerRegistros(String consulta) {
		try (Connection con = obtenerConexion();
				CallableStatement cs = con.prepareCall(consulta);
				ResultSet rs = cs.executeQuery()) {

			ArrayList<Producto> productos = new ArrayList<>();
			Producto producto;
			Departamento departamento;

			while (rs.next()) {

				producto = mapper(rs);
				/*
				 * producto = new Producto(rs.getLong("p.id"), rs.getString("p.nombre"),
				 * rs.getString("p.descripcion"), rs.getString("p.url_imagen"),
				 * rs.getBigDecimal("p.precio"), rs.getInt("p.descuento"),
				 * rs.getString("p.unidad_medida"), rs.getBigDecimal("p.precio_unidad_medida"),
				 * rs.getInt("p.cantidad"));
				 */

				departamento = new Departamento(rs.getLong("d.id"), rs.getString("d.nombre"),
						rs.getString("d.descripcion"));

				producto.setDepartamento(departamento);

				productos.add(producto);
			}

			return productos;
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido consultar la lista de producto", e);
		}
	}

	@Override
	public Producto obtenerPorId(Long id) {
		try (Connection con = obtenerConexion(); CallableStatement cs = con.prepareCall(SQL_SELECT_ID);) {

			cs.setLong(1, id);

			try (ResultSet rs = cs.executeQuery()) {
				Producto producto = null;
				Departamento departamento;

				if (rs.next()) {
					producto = new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"),
							rs.getString("url_imagen"), rs.getBigDecimal("precio"), rs.getInt("descuento"),
							rs.getString("unidad_medida"), rs.getBigDecimal("precio_unidad_medida"),
							rs.getInt("cantidad"));

					departamento = new Departamento(rs.getLong("d.id"), rs.getString("d.nombre"),
							rs.getString("d.descripcion"));

					producto.setDepartamento(departamento);
				}

				return producto;
			}

		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recibir el producto " + id, e);
		}
	}

	@Override
	public void crear(Producto producto) {
		try (Connection con = obtenerConexion(); CallableStatement cs = con.prepareCall(SQL_INSERT);) {

			cs.setString(1, producto.getNombre());
			cs.setString(2, producto.getDescripcion());
			cs.setString(3, producto.getUrlImagen());
			cs.setBigDecimal(4, producto.getPrecio());
			cs.setInt(5, producto.getDescuento());
			cs.setString(6, producto.getUnidadMedida());
			cs.setBigDecimal(7, producto.getPrecioUnidadMedida());
			cs.setInt(8, producto.getCantidad());
			cs.setLong(9, producto.getDepartamento().getId());

			int numeroRegistrosInsertados = cs.executeUpdate();

			if (numeroRegistrosInsertados != 1) {
				throw new AccesoDatosException("Se han insertado " + numeroRegistrosInsertados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido insertar el producto " + producto, e);
		}
	}

	@Override
	public void modificar(Producto producto) {
		try (Connection con = obtenerConexion(); CallableStatement cs = con.prepareCall(SQL_UPDATE);) {

			cs.setString(1, producto.getNombre());
			cs.setString(2, producto.getDescripcion());
			cs.setString(3, producto.getUrlImagen());
			cs.setBigDecimal(4, producto.getPrecio());
			cs.setInt(5, producto.getDescuento());
			cs.setString(6, producto.getUnidadMedida());
			cs.setBigDecimal(7, producto.getPrecioUnidadMedida());
			cs.setInt(8, producto.getCantidad());
			cs.setLong(9, producto.getDepartamento().getId());
			cs.setLong(10, producto.getId());

			int numeroRegistrosModificados = cs.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException("Se han modificado " + numeroRegistrosModificados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido modificar el producto " + producto, e);
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
			throw new AccesoDatosException("No se ha podido borrar el producto " + id, e);
		}
	}

	@Override
	public void recuperar(Long id) {
		try (Connection con = obtenerConexion(); CallableStatement cs = con.prepareCall(SQL_UNDELETE);) {

			cs.setLong(1, id);

			int numeroRegistrosBorrados = cs.executeUpdate();

			if (numeroRegistrosBorrados != 1) {
				throw new AccesoDatosException("Se han recuperado " + numeroRegistrosBorrados + " registros");
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("No se ha podido recuperado el producto " + id, e);
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
