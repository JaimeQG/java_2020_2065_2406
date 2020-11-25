package com.ipartek.formacion.bases.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hola")
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String respuesta = "Hola ";

		if (nombre == null) {
			respuesta += "a todos";
		} else {
			respuesta += nombre;
		}

		response.setContentType("text/plain");
		response.getWriter().println(respuesta);

		response.getWriter().println(request.getHeader("User-Agent"));
		response.getWriter().println(request.getHeader("Host"));
		response.getWriter().println(request.getHeader("referer"));
		response.getWriter().println(request.getHeader("accept-language"));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
