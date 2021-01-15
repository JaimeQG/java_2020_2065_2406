package com.ipartek.formacion.supermercado.controladores.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.accesodatos.Dao;
import com.ipartek.formacion.supermercado.controladores.Configuracion;
import com.ipartek.formacion.supermercado.modelos.Producto;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/admin/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String borrados = request.getParameter("borrados");
		// request.setAttribute("productos",
		// ProductoDaoTreeMap.getInstancia().obtenerTodos());
		// request.setAttribute("productos",
		// ProductoDaoMySql.getInstancia().obtenerTodos());
		Dao<Producto> dao = Configuracion.daoProductos;

		Iterable<Producto> productos = borrados != null ? dao.obtenerBorrados() : dao.obtenerTodos();

		request.setAttribute("borrados", borrados);
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("/WEB-INF/vistas/admin/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
