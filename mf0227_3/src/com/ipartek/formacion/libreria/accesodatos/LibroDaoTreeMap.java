package com.ipartek.formacion.libreria.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.libreria.modelos.Libro;

public class LibroDaoTreeMap implements Dao<Libro> {

	private static TreeMap<Long, Libro> libreria = new TreeMap<>();

	static {
		libreria.put(1L, new Libro(1L, "Los futbolísimos. El misterio del jugador número 13", new BigDecimal("12.95"),
				5, "Roberto Santiago", "imgs/libros/imagen11.jpg"));
		libreria.put(2L, new Libro(2L, "Los futbolísimos. El misterio del obelisco mágico", new BigDecimal("12.95"), 10,
				"Roberto Santiago", "imgs/libros/imagen22.jpg"));
		libreria.put(3L, new Libro(3L, "Los futbolísimos. El misterio del tesoro pirata", new BigDecimal("12.95"), 15,
				"Roberto Santiago", "imgs/libros/imagen10.jpg"));
		libreria.put(4L, new Libro(4L, "Los favoritos de Midas", new BigDecimal("12.95"), 5, "Jack Lemond",
				"imgs/libros/imagen_default.jpg"));
	}

	// SINGLETON

	// Ponemos privado el constructor por defecto para que nadie pueda crear
	// instancias de esta clase de forma libre
	// Con esto evitamos la posibilidad de que nadie haga new de esta clase (salvo
	// esta clase en sí misma)
	private LibroDaoTreeMap() {
	}

	// Creamos el único objeto que va a existir de este tipo. Lo llamamos INSTANCIA
	private static LibroDaoTreeMap INSTANCIA = new LibroDaoTreeMap();

	// Creamos un método público que de acceso a la única instancia disponible
	// Desde otras clases deberemos hacer
	// ProductoDaoTreeMap dao = ProductoDaoTreeMap.getInstancia();
	// o mejor
	// Dao<Producto> dao = ProductoDaoTreeMap.getInstancia();
	public static LibroDaoTreeMap getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON

	@Override
	public Iterable<Libro> obtenerTodos() {

		return libreria.values();
	}

	@Override
	public Libro obtenerPorId(Long id) {
		// Devolver un clon del producto al obtenerPorId en el DAO
		return libreria.get(id);
	}

	@Override
	public void crear(Libro libro) {

		Long id = libreria.size() == 0 ? 1L : libreria.lastKey() + 1L;
		libro.setId(id);
		libreria.put(id, libro);

	}

	@Override
	public void modificar(Libro libro) {

		libreria.put(libro.getId(), libro);

	}

	@Override
	public void eliminar(Long id) {

		libreria.remove(id);

	}

	public int numeroRegistros() {

		return libreria.size();
	}

}
