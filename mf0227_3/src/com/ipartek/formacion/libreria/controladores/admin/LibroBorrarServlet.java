package com.ipartek.formacion.libreria.controladores.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.libreria.accesodatos.Dao;
import com.ipartek.formacion.libreria.accesodatos.LibroDaoTreeMap;
import com.ipartek.formacion.libreria.modelos.Libro;

@WebServlet("/admin/borrar")
public class LibroBorrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		Dao<Libro> dao = LibroDaoTreeMap.getInstancia();

		dao.eliminar(Long.parseLong(id));

		request.setAttribute("alertaTexto", "Borrado efectuado correctamente");
		request.setAttribute("alertaNivel", "success");

		request.getRequestDispatcher("/admin/index").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
