package com.ipartek.formacion.ifcd0112.accesodatos;

import java.math.BigDecimal;
import java.util.TreeMap;

import com.ipartek.formacion.ifcd0112.modelos.Libro;

public class LibroDaoTreeMap implements Dao<Libro> {

	private static TreeMap<Long, Libro> libreria = new TreeMap<>();

	static {
		libreria.put(1L, new Libro(1L, "El Quijote", new BigDecimal("12.95"), 5));
		libreria.put(2L, new Libro(2L, "Hamlet", new BigDecimal("25.95"), 10));
		libreria.put(3L, new Libro(3L, "Los favoritos de Midas", new BigDecimal("42.95"), 0));
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
