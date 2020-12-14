package com.ipartek.formacion.ifcd0112.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.ifcd0112.modelos.Libro;

public class LibroDaoTreeMap implements Dao<Libro> {

	private static TreeMap<Long, Libro> libreria = new TreeMap<>();

	static {
		libreria.put(1L, new Libro(1L, "Crimen y Castigo", new BigDecimal("12.95"), 5));
		libreria.put(2L, new Libro(2L, "Cien años de soledad", new BigDecimal("25.95"), 10));
		libreria.put(3L, new Libro(3L, "Los santos inocentes", new BigDecimal("32.95"), 15));
		libreria.put(4L, new Libro(4L, "Los favoritos de Midas", new BigDecimal("42.95"), 5));
		libreria.put(5L, new Libro(5L, "El Quijote", new BigDecimal("59.95"), 2));
		libreria.put(6L, new Libro(6L, "Hamlet", new BigDecimal("62.95"), 5));
	}

	// SINGLETON
	private LibroDaoTreeMap() {
	}

	private static LibroDaoTreeMap INSTANCIA = new LibroDaoTreeMap();

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

}
