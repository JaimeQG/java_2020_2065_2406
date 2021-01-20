package com.ipartek.formacion.medicamentos.accesodatos;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default void crear(T objeto) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default void eliminar(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default T crearYObtener(T objeto) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default int numeroRegistros() {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}
}