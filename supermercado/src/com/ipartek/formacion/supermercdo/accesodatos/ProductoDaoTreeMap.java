package com.ipartek.formacion.supermercdo.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.supermercado.modelos.Producto;

public class ProductoDaoTreeMap implements Dao<Producto> {

	private static TreeMap<Long, Producto> productos = new TreeMap<>();

	static {
		productos.put(1L, new Producto(1L, "Beefeater", "Botella de ginebra", "beefeater.jpg", new BigDecimal("12.95"),
				20, "Litro", new BigDecimal("18.50"), 1));
		productos.put(2L, new Producto(2L, "Beefeater light", "Botella de ginebra", "beefeaterlight.jpg",
				new BigDecimal("7.90"), 20, "Litro", new BigDecimal("18.50"), 1));

		for (Long id = 3L; id <= 12L; id++) {

		}
	}

	// INICIO SINGLETON

	private ProductoDaoTreeMap() {
	} // Constructor

	private static ProductoDaoTreeMap INSTANCIA = new ProductoDaoTreeMap();

	public static ProductoDaoTreeMap getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Producto> obtenerTodos() {

		return productos.values();
	}

	@Override
	public Producto obtenerPorId(Long id) {

		return productos.get(id);
	}

	@Override
	public void crear(Producto producto) {

		Long id = productos.size() == 0 ? 1L : productos.lastKey() + 1L;
		producto.setId(id);
		productos.put(id, producto);

	}

	@Override
	public void modificar(Producto producto) {
		productos.put(producto.getId(), producto);

	}

	@Override
	public void eliminar(Producto objeto) {

	}

}
