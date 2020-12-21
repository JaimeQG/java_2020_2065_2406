package com.ipartek.formacion.supermercado.accesodatos;

import com.ipartek.formacion.supermercado.modelos.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Usuario obtenerPorEmail(String email);
}
