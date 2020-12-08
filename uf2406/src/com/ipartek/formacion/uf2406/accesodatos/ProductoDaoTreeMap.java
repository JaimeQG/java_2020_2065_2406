package com.ipartek.formacion.uf2406.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.uf2406.modelos.Producto;

public class ProductoDaoTreeMap implements Dao<Producto> {

	private static TreeMap<Long, Producto> productos = new TreeMap<>();

	static {
		productos.put(111L, new Producto(111L, "motores", new BigDecimal("34347"), 0));
		productos.put(222L, new Producto(222L, "escoba", new BigDecimal("760"), 0));
		productos.put(333L, new Producto(333L, "lapiz", new BigDecimal("123"), 0));
		productos.put(444L, new Producto(444L, "goma", new BigDecimal("224"), 0));
		productos.put(555L, new Producto(555L, "papel", new BigDecimal("322"), 0));
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
