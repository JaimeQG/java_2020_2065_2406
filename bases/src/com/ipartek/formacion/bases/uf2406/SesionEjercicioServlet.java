package com.ipartek.formacion.bases.uf2406;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sesion-ejercicio")
public class SesionEjercicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		response.getWriter().println("Escritos datos de sesión: ");

		out.println(request.getHeader("user-agent")); // Navegador utilizado

		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = session.getAttribute(name).toString();
			out.println(name + " = " + value);
		}

		out.println(request.getMethod());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String nombre = request.getParameter("nombre"); // Nombre del usuario

		if (nombre != null && nombre.length() > 0) {
			session.setAttribute("Usuario", nombre);
		}

		Date created = new Date(session.getCreationTime()); // Fecha de la sesión
		session.setAttribute("fecha_sesion", created);

		response.sendRedirect("sesion-ejercicio");
	}

}
