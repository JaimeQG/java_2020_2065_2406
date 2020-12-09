package com.ipartek.formacion.uf2406.accesodatos;

/* Data Access Object */
public interface Dao<T> {

	Iterable<T> obtenerTodos();

	T obtenerPorId(Long id);

	void crear(T objeto);

	void modificar(T objeto);

	void eliminar(Long id);
}