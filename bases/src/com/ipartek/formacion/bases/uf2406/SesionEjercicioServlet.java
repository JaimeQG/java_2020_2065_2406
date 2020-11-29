package com.ipartek.formacion.bases.uf2406;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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

		response.setContentType("text/plain");

		PrintWriter out = response.getWriter();

		out.println(request.getHeader("referer"));
		out.println(request.getHeader("accept-language"));
		out.println(request.getHeader("user-agent"));

		out.println(request.getMethod());
		out.println(request.getPathInfo());
		out.println(request.getRemoteAddr());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String nombre = request.getParameter("nombre");

		if (nombre != null && nombre.length() > 0) {
			String dataValue = request.getParameter("dataValue");
			session.setAttribute(nombre, dataValue);
		}

		Date created = new Date(session.getCreationTime());
		session.setAttribute("fecha", created);

		response.sendRedirect("sesion-ejercicio");
	}

}
