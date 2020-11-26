package com.ipartek.formacion.bases.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sesion-escribir")
public class SesionWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		session.setAttribute("email", email);
		session.setAttribute("password", password);

		response.setContentType("text/plain");
		response.getWriter().println("Escritos datos de sesión: ");
		response.getWriter().println("email: " + email);
		response.getWriter().println("password: " + password);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
