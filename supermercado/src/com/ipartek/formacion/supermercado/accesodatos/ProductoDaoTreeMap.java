package com.ipartek.formacion.supermercado.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.supermercado.modelos.Producto;

public class ProductoDaoTreeMap implements Dao<Producto> {

	private static TreeMap<Long, Producto> productos = new TreeMap<>();

	static {
		productos.put(1L,
				new Producto(1L, "Beefeater", "Botella de ginebra que tle mola cannnnntidad",
						"http://placeimg.com/640/480/tech?1", new BigDecimal("12.95"), 20, "Litro",
						new BigDecimal("18.50"), 1));
		productos.put(2L,
				new Producto(2L, "Beefeater light",
						"Botella de ginebra que tle mola cannnnntidad perrroooo más blarata",
						"http://placeimg.com/640/480/tech?2", new BigDecimal("7.90"), null, "Litro",
						new BigDecimal("18.50"), 1));

		for (Long id = 3L; id <= 12L; id++) {
			productos.put(id,
					new Producto(id, "Producto " + id, "Descripción " + id, "http://placeimg.com/640/480/tech?" + id,
							new BigDecimal(11 * id), id.intValue(), "Unidad", new BigDecimal(10 * id), id.intValue()));
		}
	}

	// SINGLETON

	// Ponemos privado el constructor por defecto para que nadie pueda crear
	// instancias de esta clase de forma libre
	// Con esto evitamos la posibilidad de que nadie haga new de esta clase (salvo
	// esta clase en sí misma)
	private ProductoDaoTreeMap() {
	}

	// Creamos el único objeto que va a existir de este tipo. Lo llamamos INSTANCIA
	private static ProductoDaoTreeMap INSTANCIA = new ProductoDaoTreeMap();

	// Creamos un método público que de acceso a la única instancia disponible
	// Desde otras clases deberemos hacer
	// ProductoDaoTreeMap dao = ProductoDaoTreeMap.getInstancia();
	// o mejor
	// Dao<Producto> dao = ProductoDaoTreeMap.getInstancia();
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
	public void eliminar(Long id) {

		productos.remove(id);

	}

}
