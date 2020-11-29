package com.ipartek.formacion.ejemplomvc.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.ejemplomvc.accesodatos.Dao;
import com.ipartek.formacion.ejemplomvc.accesodatos.PerroDaoTreeMap;
import com.ipartek.formacion.ejemplomvc.modelos.Perro;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dao<Perro> dao = PerroDaoTreeMap.getInstancia();
		Iterable<Perro> perros = dao.listar();

		request.setAttribute("perros", perros);
		request.getRequestDispatcher("WEB-INF/vistas/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
