package com.ipartek.formacion.supermercado.accesodatos;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default Iterable<T> obtenerBorrados() {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default T obtenerPorId(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default void crear(T objeto) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default T crearYObtener(T objeto) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default void modificar(T objeto) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default void eliminar(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}

	default void recuperar(Long id) {
		throw new AccesoDatosException("MÉTODO NO IMPLEMENTADO");
	}
}