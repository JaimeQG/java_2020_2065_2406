package com.ipartek.formacion.bases.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hola/*")
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String respuesta = "Hola ";
		String nombre = request.getParameter("nombre");

		if (nombre == null) {
			respuesta += "a todos";
		} else {
			respuesta += nombre;
		}

		response.setContentType("text/plain");

		PrintWriter out = response.getWriter();
		out.println(respuesta);

		out.println(request.getHeader("referer"));
		out.println(request.getHeader("accept-language"));
		out.println(request.getHeader("user-agent"));

		out.println(request.getMethod());
		out.println(request.getPathInfo());
		out.println(request.getRemoteAddr());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
