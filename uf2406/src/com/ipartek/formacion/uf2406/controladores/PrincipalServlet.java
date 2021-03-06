package com.ipartek.formacion.uf2406.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.uf2406.accesodatos.Dao;
import com.ipartek.formacion.uf2406.accesodatos.ProductoDaoTreeMap;
import com.ipartek.formacion.uf2406.modelos.Producto;

/**
 * Servlet implementation class PrincipalServlet
 */
@WebServlet("/principal")
public class PrincipalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dao<Producto> dao = ProductoDaoTreeMap.getInstancia();
		Iterable<Producto> productos = dao.obtenerTodos();
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("/WEB-INF/vistas/principal.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
