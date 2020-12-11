package com.ipartek.formacion.libreria.accesodatos;

import java.util.TreeMap;

import com.ipartek.formacion.libreria.modelos.Usuario;

public class UsuarioDaoTreeMap implements Dao<Usuario> {

	private static TreeMap<Long, Usuario> usuarios = new TreeMap<>();

	static {
		usuarios.put(1L, new Usuario(1L, "administrador", "123456"));
		usuarios.put(2L, new Usuario(2L, "jaime@quintana", "password"));
	}

	// SINGLETON

	// Ponemos privado el constructor por defecto para que nadie pueda crear
	// instancias de esta clase de forma libre
	// Con esto evitamos la posibilidad de que nadie haga new de esta clase (salvo
	// esta clase en sí misma)
	private UsuarioDaoTreeMap() {
	}

	// Creamos el único objeto que va a existir de este tipo. Lo llamamos INSTANCIA
	private static UsuarioDaoTreeMap INSTANCIA = new UsuarioDaoTreeMap();

	// Creamos un método público que de acceso a la única instancia disponible
	// Desde otras clases deberemos hacer
	// ProductoDaoTreeMap dao = ProductoDaoTreeMap.getInstancia();
	// o mejor
	// Dao<Producto> dao = ProductoDaoTreeMap.getInstancia();
	public static UsuarioDaoTreeMap getInstancia() {
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

	}

	@Override
	public void modificar(Usuario usuario) {

		usuarios.put(usuario.getId(), usuario);

	}

	@Override
	public void eliminar(Long id) {

		usuarios.remove(id);
	}

	// Método específico de este DAO

	public Usuario obtenerPorNombre(String nombre) {
		for (Usuario usuario : usuarios.values()) {
			if (usuario.getNombre().equals(nombre)) {
				return usuario;
			}
		}

		return null;
	}

//		public Usuario obtenerPorEmail(String email) {
//			Usuario resultado = null;
//			
//			for(Usuario usuario: usuarios.values()) {
//				if(usuario.getEmail().equals(email)) {
//					resultado = usuario;
//					break;
//				}
//			}
//			
//			return resultado;
//		}
}
