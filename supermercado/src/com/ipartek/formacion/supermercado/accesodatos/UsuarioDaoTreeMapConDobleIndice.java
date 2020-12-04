package com.ipartek.formacion.supermercado.accesodatos;

import java.util.TreeMap;

import com.ipartek.formacion.supermercado.modelos.Usuario;

public class UsuarioDaoTreeMapConDobleIndice implements Dao<Usuario> {
	private static TreeMap<Long, Usuario> usuarios = new TreeMap<>();
	private static TreeMap<String, Usuario> usuariosPorEmail = new TreeMap<>();

	static {
		Usuario javier = new Usuario(1L, "javier@lete.com", "contra");

		usuarios.put(1L, javier);
		usuariosPorEmail.put("javier@lete.com", javier);

		usuarios.put(2L, new Usuario(2L, "pepe@perez.com", "perez"));
		usuariosPorEmail.put("pepe@perez.com", usuarios.get(2L));
	}

	// SINGLETON
	private UsuarioDaoTreeMapConDobleIndice() {
	}

	private static UsuarioDaoTreeMapConDobleIndice INSTANCIA = new UsuarioDaoTreeMapConDobleIndice();

	public static UsuarioDaoTreeMapConDobleIndice getInstancia() {
		return INSTANCIA;
	}

	// FIN SINGLETON
	@Override
	public Iterable<Usuario> obtenerTodos() {
		return usuarios.values();
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		return usuarios.get(id);
	}

	@Override
	public void crear(Usuario usuario) {
		Long id = usuarios.size() == 0 ? 1L : usuarios.lastKey() + 1L;
		usuario.setId(id);
		usuarios.put(id, usuario);
		usuariosPorEmail.put(usuario.getEmail(), usuario);
	}

	@Override
	public void modificar(Usuario usuario) {
		usuarios.put(usuario.getId(), usuario);

		for (Usuario u : usuariosPorEmail.values()) {
			if (u.getId() == usuario.getId()) {
				usuariosPorEmail.put(u.getEmail(), usuario);
			}
		}
	}

	@Override
	public void eliminar(Long id) {
//		Usuario u = usuarios.get(id);
//		String email = u.getEmail();
//		usuariosPorEmail.remove(email);

		usuariosPorEmail.remove(usuarios.get(id).getEmail());

		usuarios.remove(id);
	}

	// Método específico de este DAO

	public Usuario obtenerPorEmail(String email) {
		return usuariosPorEmail.get(email);
	}
}
