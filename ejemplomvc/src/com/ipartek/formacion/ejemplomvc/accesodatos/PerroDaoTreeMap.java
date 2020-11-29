package com.ipartek.formacion.ejemplomvc.accesodatos;

import java.util.TreeMap;

import com.ipartek.formacion.ejemplomvc.modelos.Perro;

public class PerroDaoTreeMap implements Dao<Perro> {

	// INICIO SINGLETON
	private PerroDaoTreeMap() { // Constructor
		Perro perro1 = new Perro("Scooby Doo", "Animado", 30);
		perro1.setId(1);
		perro1.setVacunado(true);

		Perro perro2 = new Perro("Scrappy Doo", "Animado", 5);
		perro2.setId(2);
		perro2.setVacunado(false);

		perros.put(1, perro1);
		perros.put(2, perro2);
	}

	private final static PerroDaoTreeMap INSTANCIA = new PerroDaoTreeMap();

	public static PerroDaoTreeMap getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	private TreeMap<Integer, Perro> perros = new TreeMap<>();

	@Override
	public Iterable<Perro> listar() {
		return perros.values();
	}

	@Override
	public Perro recuperar(int id) {
		return perros.get(id);
	}

	@Override
	public void crear(Perro perro) {
		int id = perros.lastKey() != null ? perros.lastKey() + 1 : 1;
		perro.setId(id);
		perros.put(id, perro);
	}

	@Override
	public void modificar(Perro perro) {
		perros.put(perro.getId(), perro);
	}

	@Override
	public void eliminar(int id) {
		perros.remove(id);
	}

}