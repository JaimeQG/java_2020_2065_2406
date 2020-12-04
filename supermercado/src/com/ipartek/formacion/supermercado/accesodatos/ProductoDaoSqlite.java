package com.ipartek.formacion.supermercado.accesodatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import com.ipartek.formacion.supermercado.modelos.Producto;

public class ProductoDaoSqlite implements Dao<Producto> {

	private static TreeMap<Long, Producto> productos = new TreeMap<>();

	// SINGLETON

	// Ponemos privado el constructor por defecto para que nadie pueda crear
	// instancias de esta clase de forma libre
	// Con esto evitamos la posibilidad de que nadie haga new de esta clase (salvo
	// esta clase en sí misma)
	private ProductoDaoSqlite() {
	}

	// Creamos el único objeto que va a existir de este tipo. Lo llamamos INSTANCIA
	private static ProductoDaoSqlite INSTANCIA = new ProductoDaoSqlite();

	// Creamos un método público que de acceso a la única instancia disponible
	// Desde otras clases deberemos hacer
	// ProductoDaoSqlite dao = ProductoDaoSqlite.getInstancia();
	// o mejor
	// Dao<Producto> dao = ProductoDaoSqlite.getInstancia();
	public static ProductoDaoSqlite getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Producto obtenerPorId(Long id) {

		Producto producto = null;
		final String SQL = "SELECT id, nombre, raza, peso, vacunado, historia FROM perro WHERE id = ?;";

		try (Connection conn = ConectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(SQL);) {

			pst.setLong(1, id); // sustituimos el 1� ? de la SQL por el parametro id

			System.out.println(pst);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {

					producto = mapper(rs);

				} // if
			} // 2� try

		} catch (Exception e) {
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public void crear(Producto producto) {

		final String SQL = "INSERT INTO perro (nombre, raza, peso, vacunado, historia) VALUES (?, ?, ?,?,?);";

		try (Connection conn = ConectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, producto.getNombre());
			pst.setString(2, producto.getDescripcion());
			pst.setString(3, producto.getUrlImagen());
			pst.setBigDecimal(4, producto.getPrecio());
			pst.setInt(5, producto.getDescuento());
			pst.setString(6, producto.getUnidadMedida());
			pst.setBigDecimal(7, producto.getPrecioUnidadMedida());
			pst.setInt(8, producto.getCantidad());

			int affectedsRows = pst.executeUpdate();

			// recuperar el ultimo id generado
			if (affectedsRows == 1) {
				try (ResultSet rsKeys = pst.getGeneratedKeys()) {
					if (rsKeys.next()) {
						Long id = rsKeys.getLong(1);
						producto.setId(id);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modificar(Producto producto) {

		final String SQL = "UPDATE perro SET nombre = ? , peso = ?, raza = ? , historia = ?, vacunado = ? WHERE id = ?;";
		try (Connection conn = ConectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(SQL);) {

			pst.setString(1, producto.getNombre());
			pst.setString(2, producto.getDescripcion());
			pst.setString(3, producto.getUrlImagen());
			pst.setBigDecimal(4, producto.getPrecio());
			pst.setInt(5, producto.getDescuento());
			pst.setString(6, producto.getUnidadMedida());
			pst.setBigDecimal(7, producto.getPrecioUnidadMedida());
			pst.setInt(8, producto.getCantidad());

			pst.setLong(9, producto.getId());

			pst.executeUpdate(); // CUIDADO no usar executeQuery

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void eliminar(Long id) {

		boolean resul = false;
		final String SQL = "DELETE FROM perro WHERE id = ?;";
		try (Connection conn = ConectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(SQL);) {

			pst.setLong(1, id);

			if (1 == pst.executeUpdate()) {
				resul = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getLastId() {
		int resultado = 0;
		final String SQL = "SELECT id FROM perro ORDER BY id DESC LIMIT 1;";
		try (Connection conn = ConectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL);
				ResultSet rs = pst.executeQuery();) {

			if (rs.next()) {
				resultado = rs.getInt("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Mapea un ResultSet a un objeto Perro
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
		producto.setUrlImagen(rs.getString("setUrlImagen"));
		producto.setPrecio(rs.getBigDecimal("precio"));
		producto.setDescuento(rs.getInt("descuento"));
		producto.setUnidadMedida(rs.getString("unidadMedida"));
		producto.setPrecioUnidadMedida(rs.getBigDecimal("precioUnidadMedida"));
		producto.setCantidad(rs.getInt("cantidad"));

		return producto;
	}

	/**
	 * Numero de registros de la tabla
	 * 
	 * @param @return Numero de registros de la tabla @throws
	 */
	public int countDBRows() {
		int resultado = 0;
		final String SQL = "SELECT id FROM perro;";
		try (Connection conn = ConectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				// Obtienes la data que necesitas...
				resultado++;
			}

			/*
			 * try { boolean ultimo = rs.last(); int total = 0; if (ultimo) { total =
			 * rs.getRow(); } } catch (Exception e) { e.printStackTrace(); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		// final String SQL = "SELECT id, nombre, raza, peso, vacunado, historia FROM
		// perro ORDER BY nombre ASC;";
		final String SQL = "SELECT id, nombre, raza, peso, vacunado, historia FROM perro ORDER BY id;";

		// ArrayList<Producto> productos = new ArrayList<Producto>();

		try (Connection conn = ConectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL);
				ResultSet rs = pst.executeQuery();) {

			System.out.println("***" + pst);

			while (rs.next()) {

				/*
				 * Producto producto = new Producto();
				 * 
				 * p.setId(rs.getInt("id")); p.setNombre(rs.getString("nombre"));
				 * p.setRaza(rs.getString("raza")); p.setPeso(rs.getFloat("peso"));
				 * p.setVacunado(rs.getBoolean("vacunado"));
				 * p.setHistoria(rs.getString("historia"));
				 */

				// perro = mapper(rs);
				// perros.add(perro);

				// productos.add(mapper(rs));

				productos.put(rs.getLong("id"), mapper(rs));

			} // while

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos.values();
	}

}