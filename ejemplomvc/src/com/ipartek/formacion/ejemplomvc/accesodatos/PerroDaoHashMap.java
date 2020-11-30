package com.ipartek.formacion.ejemplomvc.accesodatos;

import java.util.HashMap;
import java.util.Iterator;

import com.ipartek.formacion.ejemplomvc.modelos.Perro;

public class PerroDaoHashMap implements Dao<Perro> {

	private static int indice = 0;

	// INICIO SINGLETON
	private PerroDaoHashMap() { // Constructor
		Perro perro1 = new Perro("Scooby Doo", "Animado", 30);
		perro1.setId(1);
		perro1.setVacunado(true);

		Perro perro2 = new Perro("Scrappy Doo", "Animado", 5);
		perro2.setId(2);
		perro2.setVacunado(false);

		hmPerros.put(1, perro1);
		hmPerros.put(2, perro2);

		indice = 3;
	}

	private final static PerroDaoHashMap INSTANCIA = new PerroDaoHashMap();

	public static PerroDaoHashMap getInstancia() {
		return INSTANCIA;
	}
	// FIN SINGLETON

	private HashMap<Integer, Perro> hmPerros = new HashMap<>();

	@Override
	public Iterable<Perro> listar() {
		return hmPerros.values();
	}

	@Override
	public Perro recuperar(int id) {
		return hmPerros.get(id);
	}

	@Override
	public void crear(Perro perro) {
		// int id = perros.lastKey() != null ? perros.lastKey() + 1 : 1;
		// perro.setId(id);
		// perros.put(id, perro);

		boolean resultado = false;
		boolean encontrado = false;
		String nombrePerro = perro.getNombre();

		// buscar si existe el nombre en hashmap, recorriendo uno a uno todos los perros
		for (Iterator<Perro> iterator = hmPerros.values().iterator(); iterator.hasNext();) {

			Perro perroIteracion = iterator.next();
			if (nombrePerro.equalsIgnoreCase(perroIteracion.getNombre())) {
				encontrado = true;
				break;
			}

		} // for

		// si no existe, insertarlo y actulizar id
		if (!encontrado) {
			perro.setId(indice); // setear el id en el objeto
			hmPerros.put(indice, perro); // guardar objeto en hasmap
			indice++; // actualizar el indice para la sigueinte insercción
			resultado = true;
		}

	} // crear

	@Override
	public void modificar(Perro perro) {
		hmPerros.put(perro.getId(), perro);
	}

	@Override
	public void eliminar(int id) {
		hmPerros.remove(id);
	}

}