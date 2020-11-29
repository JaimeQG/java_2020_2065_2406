package com.ipartek.formacion.ejemplomvc.accesodatos;

public interface Dao<T> {
	Iterable<T> listar();

	T recuperar(int id);

	void crear(T objeto);

	void modificar(T objeto);

	void eliminar(int id);
}
