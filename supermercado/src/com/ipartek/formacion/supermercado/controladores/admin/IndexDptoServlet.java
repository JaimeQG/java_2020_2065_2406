package com.ipartek.formacion.supermercado.controladores.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.supermercado.accesodatos.DepartamentoDaoMySql;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/admin/indexDpto")
public class IndexDptoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setAttribute("productos",
		// ProductoDaoTreeMap.getInstancia().obtenerTodos());
		request.setAttribute("departamentos", DepartamentoDaoMySql.getInstancia().obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/admin/indexDpto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
